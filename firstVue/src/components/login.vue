<template>
  <div>

    <div id="main" class="test">
    </div>
    <div v-show="!flag" id='cs_box'>
      <div class='cs_img'></div>
      <button  class="btn" @click="commit">{{showinfo}}</button>
    </div>
    <div v-show="flag">
      <chat @init="init" @change = "change" v-bind:csid="cid" v-bind:imageUrl="imageUrl"></chat>
    </div>

  </div>
</template>

<script>
import chat from '../components/Chat'
import {ID} from '../api/createId'
export default {
  name: 'HelloWorld',
  data () {
    return {
      showinfo: '联系客服',
    	note: {
    		margin: '0',
    		padding: '0',
        backgroundRepeat: 'no-repeat',
        backgroundPosition: 'center',
        width: '100%',
        height: '100%',
        backgroundSize: 'cover'
      },
      username: '',
      password: '',
      user_error: '',
      key_error: '',
      error: '',
      flag: false,
      imageUrl: '',
      cid: ''
    }
  },

  mounted: function () {
    this.flag = sessionStorage.getItem('flag')
    this.getUser()
  },

  methods: {
    commit () {
      if (sessionStorage.getItem('cid') !== '' && sessionStorage.getItem('cid') !== null) {
        this.flag = true
        this.showinfo = '展开'
      }
      if (this.username === null || this.username === '') {
        this.username = ID()
        this.$message({
          showClose: true,
          message: '您现在为游客身份'
        })
        console.log(this.username)
      }
      this.flag = true
      this.$store.commit('setUid', this.username)
      sessionStorage.setItem('flag', this.flag)
      this.showinfo = '展开'
    },
    change (val) {
      this.flag = false
      sessionStorage.setItem('flag', this.flag)
    },
    init (val) {
      this.username = sessionStorage.getItem('uid')
      sessionStorage.removeItem('cid')
      sessionStorage.removeItem('imageUrl')
      sessionStorage.removeItem('changes')
      sessionStorage.setItem('flag', false)
      this.flag = false
      this.showinfo = '联系客服'
    },
    getUser () {
      let that = this
      window.addEventListener('message', function (e) {
        if (e.source === window.parent) {
          that.username = event.data[0]
          sessionStorage.setItem('user', JSON.stringify(event.data))
        }
      }, false
      )
    }

  },
  components: {
    chat
  },
  computed: function () {

  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.hello{
	width: 100vw;
	height: 100vh;
}
#main{
	margin-top: 0px;
  margin: 0 auto 0 auto;
  text-align: center;
}
h3{
	padding-top: 150px;
}
#error{
	color:red;
}
#login{
	padding-top: 50px;
}
#username{
	padding-left: 10px;
}
span{
	padding-top: 30px;
	position: fixed;
	color: red;
	font-size: 17px;
}
input::-webkit-input-placeholder{
            color:#fff;
        }
        input::-moz-placeholder{   /* Mozilla Firefox 19+ */
            color:#fff;
        }
        input:-moz-placeholder{    /* Mozilla Firefox 4 to 18 */
            color:#fff;
        }
        input:-ms-input-placeholder{  /* Internet Explorer 10-11 */
            color:#fff;
        }
input{
	width: 270px;
    height: 42px;
    margin-top: 25px;
    padding: 0 15px;
    background: #2d2d2d; /* browsers that don't support rgba */
    background: rgba(45,45,45,.15);
    -moz-border-radius: 6px;
    -webkit-border-radius: 8px;
    border-radius: 6px;
    border: 1px solid #3d3d3d; /* browsers that don't support rgba */
    border: 1px solid rgba(255,255,255,.15);
    -moz-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    -webkit-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    font-family: 'PT Sans', Helvetica, Arial, sans-serif;
    font-size: 14px;
    color: #fff;
    text-shadow: 0 1px 2px rgba(0,0,0,.1);
    -o-transition: all .2s;
    -moz-transition: all .2s;
    -webkit-transition: all .2s;
    -ms-transition: all .2s;
}
button {
    cursor: pointer;
    width: 300px;
    height: 44px;
    margin-top: 25px;
    padding: 0;
    background: #ef4300;
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    border: 1px solid #ff730e;
    -moz-box-shadow:
        0 15px 30px 0 rgba(255,255,255,.25) inset,
        0 2px 7px 0 rgba(0,0,0,.2);
    -webkit-box-shadow:
        0 15px 30px 0 rgba(255,255,255,.25) inset,
        0 2px 7px 0 rgba(0,0,0,.2);
    box-shadow:
        0 15px 30px 0 rgba(255,255,255,.25) inset,
        0 2px 7px 0 rgba(0,0,0,.2);
    font-family: 'PT Sans', Helvetica, Arial, sans-serif;
    font-size: 14px;
    font-weight: 700;
    color: #fff;
    text-shadow: 0 1px 2px rgba(0,0,0,.1);
    -o-transition: all .2s;
    -moz-transition: all .2s;
    -webkit-transition: all .2s;
    -ms-transition: all .2s;
}

button:hover {
    -moz-box-shadow:
        0 15px 30px 0 rgba(255,255,255,.15) inset,
        0 2px 7px 0 rgba(0,0,0,.2);
    -webkit-box-shadow:
        0 15px 30px 0 rgba(255,255,255,.15) inset,
        0 2px 7px 0 rgba(0,0,0,.2);
    box-shadow:
        0 15px 30px 0 rgba(255,255,255,.15) inset,
        0 2px 7px 0 rgba(0,0,0,.2);
}

button:active {
    -moz-box-shadow:
        0 15px 30px 0 rgba(255,255,255,.15) inset,
        0 2px 7px 0 rgba(0,0,0,.2);
    -webkit-box-shadow:
        0 15px 30px 0 rgba(255,255,255,.15) inset,
        0 2px 7px 0 rgba(0,0,0,.2);
    box-shadow:
        0 5px 8px 0 rgba(0,0,0,.1) inset,
        0 1px 4px 0 rgba(0,0,0,.1);

    border: 0px solid #ef4300;
}
#cs_box {width:120px; height:220px; color:#FFF; background:#6ed0ce; position:fixed; right:10px; top:100px; border-radius:10px; z-index:1000}
#cs_box span {height:20px; line-height:20px;display:block;}
.cs_close {color:#FFF; position:absolute; right:10px; top:8px; cursor:pointer; font-size:20px;font-family:Verdana, Geneva, sans-serif}
.cs_title {font-size:14px; margin:10px; font-weight:bold;}
.cs_img {width:100px; height:100px; background:#FFF; margin:10px;background-image:url(http://demo.lanrenzhijia.com/2014/service1119/images/lanrenzhijia.jpg) }
.cs_info {font-size:12px; margin:0px 10px; overflow:hidden; text-align:center;color:#FFF;width:60%; height:25px; }
.cs_btn {width:100px; height:25px; margin:5px 10px; border-radius:5px; font-size:12px; line-height:25px; text-align:center; cursor:pointer;}
.btn {width:100px; height:25px; margin:5px 10px; border-radius:5px; font-size:12px; line-height:25px; text-align:center; cursor:pointer;}
.test{}
</style>
