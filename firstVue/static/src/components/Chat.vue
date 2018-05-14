<template>
<div style="background-image: url('/static/back.jpg')">
  <div><mains style="float: left" v-bind:data = "mes"  v-bind:fortest="fortest" @changeUser="changeUser" @setFlag="setFlag"></mains></div>
  <div v-show="flag"><info @back="back"></info></div>
  <div v-show="!flag">
    <el-container>
      <el-header>
       <div><img :src="userImg"
                 class="header-pic" alt="暂未选择会话"/>{{this.nowuid}}</div>
      </el-header>
      <el-main>
        <Scroll :data = "nowmessagelist"
                ref = "meslist"
                class="bubble-div shadow"
                :scrollToEndFlag = "scrollToEndFlag"
        >
          <ul class="message-ul">

            <li v-for="item in nowmessagelist" :class="item.from === from?'bubble-item clearfix':'bubble-item'">
              <div><p v-show="item.from !== from" class="show-left-time">{{item.time}}</p></div>
              <div><p v-show="item.from === from" class="show-right-time">{{item.time}}</p></div>
              <div class="messagebox" >
                <img  v-show="item.from !== from" :src="userImg" class="left-pic">
                <img  v-show="item.from === from" :src="imageUrl" class="right-pic">
                  <span>
        <span :class="item.from === from?'bubble right-bubble':'bubble left-bubble'">
         <span v-if="item.imgUrl === null || item.imgUrl === '' " v-html="item.content"></span>
          <img v-else
               :src = item.imgUrl class="img-message"/>
            <span class="bottom-level"></span>
            <span class="top-level"></span>
        </span>
          </span>
             </div>
            </li>
          </ul>
        </Scroll>
      </el-main>
   <el-footer style="height: 160px">
     <div class="chat-style" >
       <div class = "input-length">
         <div class="edit-emoji">
           <edit-emoji
             ref= "editemoji"
             @contentChange="OnInput"
             @sendImg="sendImg"
             @sendout="sendOut"
             @askCut="askCut"
             :init-text="message"
           ></edit-emoji>
         </div>
         <div class="button-local">
           <el-button type="primary"
                      @click="sendOut"
                      :plain="true">发送</el-button>
           <el-button type="primary" @click="login">断开连接</el-button>
         </div>
       </div>
     </div>
   </el-footer>
    </el-container>
  </div>
  </div>

</template>

<script>
import {getConnect} from '../webScoket/webScoket.js'
import Scroll from '../base/scroll'
import VueEdit from '../components/edit'
import EditEmoji from '../components/KindeditorEmoji'
import mains from '../components/changeInfo'
import info from '../components/information'
export default {
  name: 'Chat',
  data () {
    return {
      ws: null,
      message: '',
      messagelist: [],
      nowmessagelist: [],
      from: '',
      newmessage: '',
      to: '',
      imageUrl: '', /* 头像 */
      uid: [],
      imgUrl: '', /* 图片内容 */
      cid: '',
      nowuid: '',
      scrollToEndFlag: true,
      inputMessage: '',
      flag: false,
      userImg: '',
      mes: [{
        label: '排队中',
        children: [],
        disabled: true
      },
      {
        label: '服务中',
        children: [],
        disabled: true
      }
      ],
      // --for循环测试遍历用户
      fortest: [
        {
          'username': '彼岸花',
          'id': '2015210002',
          'url': '/static/4b679a65-0911-4cca-a0bc-d948af8e53d4u.jpg',
          'name': '王铁柱',
          'gender': '男',
          'age': '20'
        },
        {
          'username': '新房客',
          'id': '2015210003',
          'url': '/static/4b679a65-0911-4cca-a0bc-d948af8e53d4u.jpg',
          'name': '田二妞',
          'gender': '女',
          'age': '20'
        }
      ]
    }
  },
  mounted: function () {
    this.from = sessionStorage.getItem('cid')
    this.cid = sessionStorage.getItem('cid')
    this.initws()
    this.show()
  },
  methods: {
    show () {
      this.$refs.meslist.refresh()
    },
    login () {
      this.nowmessagelist = ''
      for (var i = 0; i < this.mes[1].children.length; i++) {
        if (this.mes[1].children[i].label === this.nowuid) {
          this.mes[1].children.splice(i, 1)
        }
      }
      this.nowuid = ''
      this.message = '#close#'
      this.sendOut()
      this.message = ''
    },
    sendOut () {
      if ((this.message === null || this.message === '') && (this.imgUrl === null || this.imgUrl === '')) {
        this.$message({
          message: '消息不能为空！',
          type: 'warning'
        })
        return
      }
      this.imageUrl = sessionStorage.getItem('images')
      this.sendMessage()
      this.message = ''
      this.imgUrl = ''
      this.$refs.editemoji.clearContent()
    },
    sendMessage () {
      if (this.ws === null) {
        this.$message({
          message: '连接失败！',
          type: 'warning'
        })
        return
      }
      this.ws.send(JSON.stringify({
        message: {
          content: this.message,
          from: this.from,
          to: this.to,
          imgUrl: this.imgUrl,
          time: this.getDateFull()
        },
        type: 'message'
      }))
      sessionStorage.setItem('messagelist', this.messagelist)
    },
    initws () {
      this.ws = getConnect(this.from)
      this.ws.onopen = (event) => {
        console.log('连接成功!')
      }
      this.ws.onmessage = (event) => {
        this.analysisMessage(event.data)
      }
    },
    analysisMessage (message) {
      message = JSON.parse(message)
      if (message.type === 'message') {
        if (this.uid.length === 0) {
          this.uid.push(message.message.from) // 加入用户id
          this.mes[1].children.push({ // 添加到服务中
            label: message.message.from
          })
          if (message.message.content[0] === '#') {
            var mess = ['']
            var index = -1
            for (var i = 0; i < message.message.content.length; i++) {
              if (message.message.content[i] === '#') {
                index++
                mess[index] = ''
                continue
              }
              mess[index] += message.message.content[i]
            }
            this.fortest.push({
              'username': mess[0],
              'id': message.message.from,
              'url': mess[3],
              'name': mess[4],
              'gender': mess[1],
              'age': mess[2]
            })
            this.userImg = mess[3]
          } else this.messagelist.push(message.message)
        } else {
          if (message.message.from !== sessionStorage.getItem('cid')) {
            for (var i = 0; i < this.uid.length; i++) {
              if (this.uid[i] === message.message.from) {
                this.messagelist.push(message.message)
                break
              }
              if (i === this.uid.length - 1) {
                this.uid.push(message.message.from) // 加入用户id
                this.mes[1].children.push({ // 添加到服务中
                  label: message.message.from
                })

                if (message.message.content[0] === '#') {
                  var mess = ['']
                  var index = -1
                  for (var i = 0; i < message.message.content.length; i++) {
                    if (message.message.content[i] === '#') {
                      index++
                      mess[index] = ''
                      continue
                    }
                    mess[index] += message.message.content[i]
                  }
                  this.fortest.push({
                    'username': mess[0],
                    'id': message.message.from,
                    'url': mess[3],
                    'name': mess[4],
                    'gender': mess[1],
                    'age': mess[2]
                  })
                  this.userImg = mess[3]
                } else {
                  this.messagelist.push(message.message)
                }
                break
              }
            }
          }
        }

        if (this.nowuid === message.message.from || this.cid === message.message.from) {
          this.nowmessagelist.push(message.message)
          this.show()
        } else {
          this.newmessage = '向您发送了一条新消息！'
        }
        if (message.message.from !== sessionStorage.getItem('cid')) { this.to = message.message.from }
        this.$axios.post('/api/saveChat',
          { content: message.message.content,
            from: message.message.from,
            to: message.message.to,
            imgUrl: message.message.imgUrl
          }).then(response => {
        })
      }
    },
    joinUer (message) {

    },
    getDateFull () {
      var date = ''
      var hours = new Date().getHours()
      var min = new Date().getMinutes()
      var size = 2
      if (min.toString().length < size) { date = hours + ':0' + min } else { date = hours + ':' + min }
      return date
    },
    OnInput (inner) {
      this.message = inner
    },
    sendImg (imgUrl) {
      this.imgUrl = 'http://localhost:8081/static/' + imgUrl
      this.sendOut()
      this.refresh()
    },
    changeUser (userid) {
      this.flag = false
      this.newmessage = ''
      this.nowmessagelist = []
      this.nowuid = userid
      this.to = userid
      for (var i = 0; i < this.messagelist.length; i++) {
        if ((this.cid === this.messagelist[i].from && this.nowuid === this.messagelist[i].to) ||
          (this.cid === this.messagelist[i].to && this.nowuid === this.messagelist[i].from)) {
          this.nowmessagelist.push(this.messagelist[i])
        }
      }
    },
    setFlag (val) {
      this.flag = val
    },
    back (val) {
      this.flag = val
    },
    askCut (val) {
      this.message = '$askCut$'
      this.sendOut()
      this.message = ''
    }
  },
  watch: {
    newmessage (n, o) {
      if (this.newmessage !== '' && this.newmessage !== null) {
        const h = this.$createElement
        this.$notify({
          title: '客户' + this.to,
          message: h('i', { style: 'color: teal'}, this.newmessage)
        })
      }
    }
  },
  components: {
    Scroll,
    VueEdit,
    mains,
    EditEmoji,
    info
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style scoped >
.input-length {
  width: 100%;
  margin: 0 auto;
  height: 50%;
  margin-right:0px ;

}
.edit {
  height: 100px;
}
.button-local {
  display: inline;
  float: right;
  padding-left: 5%;
}
.bubble-div{
  width: 100%;
  margin: 0 auto;
  height: 99%;
  border-bottom: 0;
  overflow: hidden;

}

.bubble-item {
  width: 100%;
  margin-bottom: 10px;
  line-height: 20px;
}
.bubble {
  max-width: 65%;
  position: relative;
  line-height: 30px;
  padding-left: 10px;
  padding-bottom: 3px;
  border-radius: 3px;
  margin-top: 15px;
  padding-right: 10px;
  display: inline-block;
  word-wrap:break-word;
  box-shadow: 0px 6px 5px -5px black;

}
.left-bubble {
  position: relative;
  margin-top: 1px;
  margin-bottom: 10px;
  border: 1px solid #00b6b6;
  background-color: #f8fdfc;
  text-align: left;
  margin-left: 20px;
  float: left;
}
.left-pic{
  margin-left: 0;
  float: left;
  width: 40px;
  height: 40px;
  border-radius: 20px;
  position: relative;
}
.right-pic{
  margin-right: 0;
  float: right;
  width: 40px;
  height: 40px;
  border-radius: 20px;
  position: relative;
}
.left-bubble .bottom-level{
  position: absolute;
  top: 10px;/*位置*/
  left: -10px;/*位置*/
  border-top: 10px solid #00b6b6;/*控制箭头方向*/
  border-left: 10px solid transparent;
}
.left-bubble .top-level {
  position: absolute;
  top: 11px;/*位置*/
  left: -8px;/*位置*/
  border-top: 10px solid #f8fdfc;/*控制箭头方向*/
  border-left: 10px solid transparent;

  z-index: 100;
}
.right-bubble {
  position: relative;
  margin-right: 20px;
  float: right;
  color: white;
  text-align: left;
  margin-top: 1px;
  margin-bottom: 10px;
  background-color: #6ed0ce;
}
.right-bubble .bottom-level {
  position: absolute;
  bottom: 11px;
  right: -10px;
  border-bottom: 10px solid #6ed0ce;
  border-right: 10px solid transparent;
}
.right-bubble .top-level {
  position: absolute;
  bottom: 12px;
  right: -8px;
  border-bottom: 10px solid #6ed0ce;
  border-right: 10px solid transparent;
  z-index: 100;
}
.clearfix:after {
  visibility: hidden;
  display: block;
  font-size: 0;
  content: " ";
  clear: both;
  height: 0;
}
.message-ul{
  margin-top: -10px;
  padding: 0;
}

.el-header {
   background-color: #E9EEF3;
   color: #333;
   text-align: center;
   line-height: 60px;
   width:80%;
   border-right: 1px solid #6ed0ce;

 }
.el-footer {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 60px;
  width:80%;
  height: 250px;
  border: 1px solid #6ed0ce;
  border-top: 1px solid #999999;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;

}

.el-main {
  background-color: rgba(187, 223, 242, 0.75);
  color: #333;
  text-align: center;
  line-height: 160px;
  height: 360px;
  width:80%;
  border-right: 1px solid #6ed0ce;
  border-top: 1px solid #999999;
}
.chat-style{
    width: 100%;
    margin: 0;
  }
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 30px;
  margin: 0;
}
.header-pic{
  width: 40px;
  height: 40px;
  border-radius: 20px;
  margin-top: 2px;
}
.show-left-time{
  text-align: left;
  color: #999999;
  font-size: smaller;
  margin-bottom: 1px;
}
.show-right-time{
  text-align: right;
  color: #999999;
  font-size: smaller;
  margin-bottom: 1px;
}
.messagebox{
  height:60px;
  max-height:90px;
  word-wrap:break-word;
  font-size: smaller;
  color: black;
}
.img-message{
  height: auto;
  width: auto;
  max-width: 75%;
  max-hegith: 75%;
}
</style>
