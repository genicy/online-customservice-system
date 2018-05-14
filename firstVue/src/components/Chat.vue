<template>
  <div class="hello" >
    <dialog-rate v-bind:isShow="dialogshow" @getScore = "getScore" ></dialog-rate>
    <el-dialog
      title="提示"
      @close="centerDialogagree"
      :visible.sync="centerDialogVisible"
      width="80%"
      center>
      <span>客服正在请求截图，是否同意？</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="centerDialogVisible = false,per = true">确 定</el-button>
  </span>
    </el-dialog>
    <div id="main" class="test">
    </div>
  <div class="show-style">
    <el-container>
      <el-header>
        <div v-show="changes" class="show-img"><img :src="imageUrl" height="35px" width="35px"/></div>
        <div v-show="!changes" class="show-img"><img src="/static/robot.jpg" height="35px" width="35px"/></div>
        <div class="float-left">
          <div v-show="changes" class="show-cid">{{cid}}</div>
          <div v-show="!changes" class="show-cid">机器客服小白</div>
          <div class="show-mes">&nbsp;&nbsp;&nbsp;有什么问题您可向我提问哦</div>
        </div>
        <div class="show-back">
          <i class="el-icon-d-arrow-right" @click="change" id="back"></i>
        </div>
        </el-header>
      <el-main>
        <div v-show="!changes" class="robot-style">如果您嫌我太笨的话也可以<el-button type="text" @click="open" style="color: #6ed0ce">联系人工客服</el-button></div>
        <Scroll :data = "messagelist"
                ref = "meslist"
                class="bubbleDiv shadow"
                :scrollToEndFlag = "scrollToEndFlag"
        >
          <ul class="message-ul">
            <li v-for = "item in messagelist" :class="item.from === from?'bubbleItem clearfix':'bubbleItem'">
              <span v-if="item.from===from" class="right-time" >{{item.time}}</span>
              <span v-else class="left-time">{{item.time}}</span>
        <span v-if="!changes&&item.from !== from" class="bubble leftBubble">
          <span v-if = "item.imgUrl === null || item.imgUrl === '' " v-html="item.content"></span>
          <img v-else
               :src = item.imgUrl class="img-message"/>
            <span class="bottomLevel"></span>
            <span class="topLevel"></span>
        </span>
           <span v-if="changes&&item.from !== from" class="bubble leftBubble1">
             <span v-if = "item.imgUrl === null || item.imgUrl === '' " v-html="item.content"></span>
          <img v-else
               :src = item.imgUrl class="img-message"/>
            <span class="bottomLevel"></span>
            <span class="topLevel"></span>
           </span>
           <span v-if="item.from === from" class="bubble rightBubble">
             <span v-if = "item.imgUrl === null || item.imgUrl === '' " v-html="item.content"></span>
          <img v-else
               :src = item.imgUrl class="img-message"/>
            <span class="bottomLevel"></span>
            <span class="topLevel"></span>
           </span>

            </li>
          </ul>
        </Scroll>
      </el-main>
   <el-footer style="height: 140px">
     <div class="chat-style">

       <div class = "input-length">
         <div class="edit-emoji">
           <edit-emoji
             ref= "editemoji"
             @contentChange="OnInput"
             @sendImg="sendImg"
             @sendout="sendOut"
             :init-text="message"
           ></edit-emoji>
         </div>
         <div class="button-local" >
           <el-button type="primary"
                      @click="sendOut"
                      class="show-button"
                      :plain="true">发送</el-button>
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
import Dialog from '../components/Dialog'


export default {
  name: 'Chat',

  data () {
    return {
      askcut: '$askCut$',
      ws: null,
      message: '',
      messagelist: [],
      from: '',
      to: '',
      scrollToEndFlag: true,
      imgUrl: '',
      cid: '',
      inputMessage: '',
      flag: true,
      changes: false,
      centerDialogVisible: false,
      per: false,
      dialogshow: false,
      sendUser: false,
      imageUrl: ''
    }
  },

  methods: {
    show () {
      this.$refs.meslist.refresh()
    },
    login () {
      this.changes = false
      this.ws.close()
      this.dialogshow = true
    },
    sendOut () {
      console.log(this.cid)
      if ((this.message === null || this.message === '') && (this.imgUrl === null || this.imgUrl === '')) {
        this.$message({
          message: '消息不能为空！',
          type: 'warning'
        })
        return
      }
      if (!this.changes) {
        this.messagelist.push({
          content: this.message,
          from: this.from,
          to: '',
          imgUrl: '',
          time: this.getDateFull()
        })
        this.$axios.post('/api/replyRobot', {
          question: this.message
        }).then(response => {
          this.messagelist.push({
            content: response.data.answer,
            from: '',
            to: this.uid,
            imgUrl: '',
            time: this.getDateFull()
          })
        })
      } else {
        this.sendMessage()
      }
      this.imgUrl = ''
      this.message = ''
      this.$refs.editemoji.clearContent()
    },
    sendImg (imgUrl) {
      this.imgUrl = 'http://localhost:8082/static/' + imgUrl
      this.sendOut()
      this.refresh()
    },
    centerDialogagree () {
      setTimeout(() => {
        this.$axios.post('/api/askCut')
          .then(response => {
            this.imgUrl = 'http://localhost:8082/static/' + response.data.images
            this.sendOut()
            this.message = ''
            this.per = false
          })
      }, 500)
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
    },
    getScore (score) {
      let customservice1 = {
        cid: this.to,
        judge: score
      }
      this.$axios.post('/api/updatejudege.action', customservice1)
        .then((res) => {
          console.log(res.data)
        }, (err) => {
        })
      this.dialogshow = false
      this.cid = ''
      sessionStorage.removeItem('cid')
      sessionStorage.removeItem('sendUser')
      this.messagelist = []
      this.$emit('init', this.flag)
    },
    initws () {
      this.ws = getConnect(this.from)
      this.ws.onopen = (event) => {
        if (!this.sendUser) {
          var user = JSON.parse(sessionStorage.getItem('user'))
          this.message = '#' + user[1] + '#' +
            user[2] + '#' + user[3] + '#' + user[4] + '#' +
            user[5]
          this.sendOut()
          this.message = ''
          this.messagelist = []
          sessionStorage.setItem('sendUser', true)
        }
      }
      this.ws.onmessage = (event) => {
        this.analysisMessage(event.data)
      }
    },
    analysisMessage (message) {
      message = JSON.parse(message)
      if (message.type === 'message') {
        if (message.message.content === '#close#') {
          this.messagelist = []
          this.login()
        }
        if (message.message.content === this.askcut) {
          this.askCut()
        } else {
          var array = message.message.content.split('#')
          if (array.length < 5) {
            this.messagelist.push(message.message)
            sessionStorage.setItem('messagelist', JSON.stringify(this.messagelist))
          }
        }
      }
    },
    getDateFull () {
      var date = ''
      var hours = new Date().getHours()
      var min = new Date().getMinutes()
      var size = 2
      if (min.toString().length < size) { date = hours + ':0' + min } else { date = hours + ':' + min }
      return date
    },
    change () {
      this.cid = sessionStorage.getItem('cid')
      this.from = sessionStorage.getItem('uid')
      this.to = this.cid
      this.$emit('change', this.flag)
    },
    OnInput (inner) {
      this.message = inner
    },
    open () {
      this.from = sessionStorage.getItem('uid')
      this.$axios.post('/api/UserLogin', {
        cid: this.from
      }).then((response) => {
        this.$store.commit('setCid', response.data.cid)
        this.cid = response.data.cid
        sessionStorage.setItem('imageUrl', response.data.images)
        this.imageUrl = response.data.images
        this.changes = true
        this.imageUrl = sessionStorage.getItem('imageUrl')
        this.cid = sessionStorage.getItem('cid')
        this.to = this.cid
        sessionStorage.setItem('changes', this.changes)
        this.initws()
      })
    },
    askCut () {
      this.centerDialogVisible = true
    }
  },
  mounted: function () {
    this.imageUrl = sessionStorage.getItem('imageUrl')
    this.from = sessionStorage.getItem('uid')
    this.changes = sessionStorage.getItem('changes')
    this.to = sessionStorage.getItem('cid')
    this.cid = this.to
    this.sendUser = sessionStorage.getItem('sendUser')
    if (JSON.parse(sessionStorage.getItem('messagelist') !== '' && JSON.parse(sessionStorage.getItem('messagelist') !== null))) {
      this.messagelist = JSON.parse(sessionStorage.getItem('messagelist'))
    }

    this.initws()
    this.show()
  },
  computed: {

  },

  components: {
    Scroll,
    VueEdit,
    EditEmoji,
    DialogRate: Dialog
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped >
.input-length {
  width: 100%;
  margin: 0 auto;
  height: 40%;
  margin-left:0;
}
.edit {
  height: 80px;
}
.button-local {
  display: inline;
  float: right;
  padding-right: 5%;

}
.bubbleDiv {
  width: 100%;
  margin-bottom: 0 auto;
  height: 81%;
  border-bottom: 0;
  overflow: hidden;
  background-color: #E4E7ED;

}

.bubbleItem {
  width: 100%;
  margin-bottom: 10px;
  line-height: 20px;
}
.bubble {
  box-shadow: 0px 6px 5px -5px black;
  max-width: 75%;
  position: relative;
  line-height: 30px;
  padding-right: 10px;
  padding-bottom: 3px;
  border-radius: 3px;
  margin-top: 15px;
  padding-left: 10px;
  display: inline-block;
  word-wrap:break-word;
}
.leftBubble {
  position:relative;
  margin-top: 1px;
  margin-bottom: 10px;
  margin-left: 10px;
  float: left;
  border: 1px solid #00b6b6;
  background-color: #f8fdfc;
  font-size: smaller;
  text-align: left;
}
.left-time{
  margin-right:250px;
  font-size: smaller;
  line-height: 20px;
  color: darkgrey;
}
.leftBubble1 {
  position: relative;
  margin-top: 1px;
  margin-bottom: 10px;
  margin-left: 0;
  float: left;
  border: 1px solid #00b6b6;
  background-color: #f8fdfc;
  font-size: smaller;
  text-align: left;
}
.rightBubble {
  position: relative;
  margin-right: 15px;
  float: right;
  margin-top: 1px;
  margin-bottom: 10px;
  background-color: #6ed0ce;
  color: white;
  font-size: smaller;
  text-align: left;
}
.leftBubble .bottomLevel{
  position: absolute;
  top: 10px;
  left: -10px;
  border-top: 10px solid #00b6b6;
  border-left: 10px solid transparent;
}
.leftBubble .topLevel {
  position: absolute;
  top: 11px;
  left: -8px;
  border-top: 10px solid #f8fdfc;
  border-left: 10px solid transparent;
  z-index: 100;
}

.rightBubble .bottomLevel {
  position: absolute;
  bottom: 11px;
  right: -10px;
  border-bottom: 10px solid #6ed0ce;
  border-right: 10px solid transparent;
}
.rightBubble .topLevel {
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
  margin-top: 10px;
  padding: 0;
}
.el-header {
   background-color: #6ed0ce;
   color: white;
   text-align: center;
   line-height: 40px;
   width:320px;

}
.el-footer {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 40px;
  width:320px;
  height: 80px;

}

.el-main {
  background-color: #E4E7ED;
  color: #333;
  text-align: center;
  line-height: 160px;
  padding-top: 0;
  height: 250px;
  width:320px;
  border-bottom: 1px solid #C0C4CC;
}
.show-style{
  /*float: right;
  width:320px;
  height:220px;
  color:#FFF;
  background:#54a7ff;
  position:fixed;
  right:10px;
  top:100px;
  border-radius:10px;
  z-index:1000*/
}
.show-img{
  float: left;
  margin-top: 10px;
  border-radius: 5px;
}
.float-left{
  float: left;
}
.show-cid{
  float: top;
  font-size: medium;
  height: 20px
}
.show-mes{
  margin-top:1px;
  font-size: smaller;
}
.show-back{
  float: right;
  color: darkgrey;
  position:absolute;
  right:10px; top:8px;
  cursor:pointer;
  font-size:20px;
  font-family:Verdana, Geneva, sans-serif;
}
.right-time{
  margin-left:205px;
  font-size: smaller;
  line-height: 20px;
  color: darkgrey
}
.left-time{
  margin-right:250px;
  font-size: smaller;
  line-height: 20px;
  color: darkgrey;
}
.show-button{
  height: 25px;
  line-height: 4px;
  color:#aaa;
  border: 1px solid #C0C4CC
}
.robot-style{
  text-align: center;
  font-size: smaller;
  line-height: 20px;
  color: darkgrey;
  border-bottom: 1px solid #aaa;
  border-radius: 5px;
  padding-top: 1px;
  z-index: 1000;
}
.img-message{
  height: 80px;
  width: 120px;
}
#back{
  float: right;
  margin-left: 20%;
  margin-top: 70px;
}
</style>
