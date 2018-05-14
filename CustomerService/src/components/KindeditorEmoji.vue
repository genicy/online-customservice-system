<template >
  <div class="emoji-temp" v-bind:imgUrll="imgUrl">
    <div class="emoji-wrap" v-on:mouseleave="leaveEmojiWrap" v-show="showEmojiFlag">
      <span class="preview-wrap" :class="previewPosition" v-show="previewFlag">
        <img v-bind:src="previewSrc" alt="">
      </span>
      <span class="emoji-item"
            v-for="item in emojiList"
            v-bind:data-src="item.src"
            v-bind:data-alt="item.imgName"
            v-bind:key="item.imgName"
            v-on:mouseover="previewEmoji"
            v-on:click.stop="selectEmoji">
        <!--加上alt可以复制出来路径-->
        <img v-bind:src="item.src"
             v-bind:alt="item.imgName"
             v-bind:data-src="item.src"
             v-bind:data-alt="item.imgName">
      </span>
    </div>
    <div class="picture">
    <i class="el-icon-picture" id="point">
      </i>
    </div >
    <div class="uploadimg"><input type="file" name="avatar" accept="image/gif,image/jpeg,image/jpg,image/png"
                @change="update($event)" ref="avatarInput" class="input-file">
    </div>
    <div class="oper-item">
      <span v-on:click.stop="showEmojiFlag = !showEmojiFlag">
        <i class="icon-emoji"></i>
      </span>
      <span><img src="/static/cut.png" class="cut-icon" @click="askCut"/></span>
    </div>

    <div @keyup.enter="sendout" contenteditable class="show-select-wrap" v-html="textContent" v-on:click.stop="focusContent"
         v-on:input="inputChange" >
    </div>

  </div>
</template>
<script>
export default {
  name: 'KindeditorEmoji',
  props: [
    'initText'// 输入框的初始内容
  ],
  data () {
    return {
      imgUrl: '',
      title: 'emoji',
      previewFlag: false, // 预览标志
      previewPosition: 'preview-right', // 预览的class
      previewSrc: '', // 预览表情的地址
      showEmojiFlag: false, // 显示表情区域标志
      textContent: this.initText
    }
  },
  mounted () {
    const self = this
    // 给window绑定隐藏表情选择框
    self.bodyListener = (e) => {
      self.showEmojiFlag = false
    }
    window.document.addEventListener('click', this.bodyListener, false)
  },
  beforeDestroy () {
    window.document.removeEventListener('click', this.bodyListener)
  },
  methods: {
    // 表情预览右上角或者左上角
    previewEmoji: function (event) {
      this.previewFlag = true
      this.previewSrc = event.target.dataset.src
      if (event.target.offsetLeft > document.querySelector('.emoji-wrap').clientWidth / 2) {
        this.previewPosition = 'preview-left'
      } else {
        this.previewPosition = 'preview-right'
      }
    },
    // 选中表情
    selectEmoji: function (event) {
      this.textContent = document.querySelector('.show-select-wrap').innerHTML + '<img src=' + event.target.dataset.src + '>'
      this.$emit('contentChange', this.textContent)
    },
    // 输入域聚焦
    focusContent: function (event) {
      this.showEmojiFlag = false
    },
    // 离开表情选择区域，隐藏左上角或者右上角的预览
    leaveEmojiWrap: function (event) {
      this.previewFlag = false
    },
    // 监听输入框变化
    inputChange: function (event) {
      this.$emit('contentChange', document.querySelector('.show-select-wrap').innerHTML)
    },
    // 清空
    clearContent: function () {
      this.textContent = ''
      document.querySelector('.show-select-wrap').innerHTML = ''
    },
    update (event) {
      let file = event.target.files[0]
      let param = new FormData() // 创建form对象
      param.append('file', file, file.name)// 通过append向form对象添加数据
      this.$axios.post('/api/uploadImgs', param)
        .then(response => {
          this.$emit('sendImg', response.data.images)
        })
    },
    sendout () {
      this.$emit('sendout', '')
    },
    askCut () {
      this.$message({showClose: true, message: '正在请求截图'})
      this.$emit('askCut', '')
    }
  },
  computed: {
    // 初始化表情列表
    emojiList: function () {
      var arr = []
      var total = 73
      for (var i = 1; i < total; i++) {
        if (i < 10) {
          arr.push(
            {
              src: '/static/img/emoji_00' + i + '.png',
              imgName: '[emoji_00' + i + '.png]'
            }
          )
        } else if (i >= 10 && i < 100) {
          arr.push(
            {
              src: '/static/img/emoji_0' + i + '.png',
              imgName: '[emoji_0' + i + '.png]'
            }
          )
        } else {
          arr.push(
            {
              src: '/static/img/emoji_' + i + '.png',
              imgName: '[emoji_' + i + '.png]'
            }
          )
        }
      }
      return arr
    }
  }

}

</script>
<style>
  .emoji-temp{
    position: relative;

  }
  .oper-item{
    text-align: left;
    padding: 0px;
    font-size: 0;
    line-height: 20%;
    width: 100%;
  }
  .icon-emoji{
    display: inline-block;
    width: 25px;
    height: 22px;
    background: url("./../assets/button/button13.png") no-repeat;
    cursor: pointer;
    margin-top: 5px;
  }
  .icon-emoji:hover{
    background: url("./../assets/button/button13a.png") no-repeat;
  }
  .emoji-wrap{
    width: 450px;
    padding: 2px;
    background-color: #F0F0EE;
    box-shadow: 1px 1px 3px #ddd;
    -moz-box-shadow: 1px 1px 3px #ddd;
    -webkit-box-shadow: 1px 1px 3px #ddd;
    filter: progid:DXImageTransform.Microsoft.Shadow(color='#A0A0A0', Direction=135, Strength=3);
    overflow: hidden;
    position: absolute;
    top: -400px;
    left: 0;
  }
  .emoji-wrap span.preview-wrap{
    position: absolute;
    text-align: center;
    margin: 2px;
    padding: 10px;
    top: 0;
    left: 0;
    border: 1px solid #A0A0A0;
    background-color: #FFFFFF;
  }
  .emoji-wrap span.preview-wrap.preview-left{
    left: 0;
    right: auto;
  }
  .emoji-wrap span.preview-wrap.preview-right{
    left: auto;
    right: 0;
  }
  .emoji-wrap span.emoji-item{
    float: left;
    box-sizing: border-box;
    padding: 5px;
    background: #fff;
    border: 1px solid #fff;
    font-size: 0;
    cursor: pointer;
  }
  .emoji-wrap span.emoji-item:hover{
    border: 1px solid #5690D2;
    background-color: #E9EFF6;
  }
  .show-select-wrap{
    width: 100%;
    height: 80px;
    max-height: 80px;
    line-height: 20px;
    box-sizing: border-box;
    padding: 5px;
    text-align: left;
    font-size: smaller;
    overflow: auto;
  }
  .picture{
    float: right;
    margin-left: 50px;
    line-height: 30px;
    position:absolute;
  }
  .uploadimg{
    float: right;
    margin-left: 50px;
    line-height: 30px;
    position:absolute;
  }
  #point{
    cursor: pointer;
  }
  .input-file{
    opacity: 0;
    width: 20px;
  }
  .cut-icon{
    float: right;
    margin-left: 60px;
    line-height: 30px;
    position:absolute;
    height: 22px;
    width: 22px;
    margin-top: 5px;
    cursor: pointer;
  }
</style>
