<template>
  <div class="hello" >
	<div id="main" :style ="note">
		<h3>Welcome to use custom service Chatting Online!</h3>
		<div id="login">
		<div id="username">
		<input type="text" v-model=username placeholder="Username"><span>{{user_error}}</span>
		</div>
		<div id="key">
		<input type="password" v-model=password placeholder="Password"  @keyup.enter="commit">
		<span>{{key_error}}</span><span>{{error}}</span>
		</div>
      <button id="commit" @click="commit">Login in</button>
		</div>
	</div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
    	note: {
    		margin: '0',
    		padding: '0',

        backgroundImage: 'url(' + require('../assets/3.jpg') + ')',
        backgroundRepeat: 'no-repeat',
        backgroundPosition: 'center',
        width: '100%',
        height: '100%',
        backgroundSize: 'cover'
      },
      mailaddress: '',
      username: '',
      password: '',
      user_error: '',
      key_error: '',
      error: '',
      status: 0,
      imageUrl: ''
    }
  },

  mounted: {

  },

  methods: {
    commit () {
      if (this.username == null || this.username == '') {
        this.user_error = '用户名不能为空'
      } else {
        this.user_error = ''
      }
      if (this.password == null || this.password == '') {
        this.key_error = '密码不能为空'
      } else {
        this.key_error = ''
      }
      if (this.password != null && this.password != '' && this.username != null && this.password != '') {
        let that = this
			  this.$axios.post('/api/CustomerLogin', {
          mailaddress: this.username,
          password: this.password
        }).then((response) => {
          this.status = response.data.status1
          this.error = response.data.errorMessage
          if (this.status === 0) {
            sessionStorage.setItem('images', '/static/' + response.data.images)
            this.$store.commit('setCid', response.data.cid)
            this.$router.push('/chat')
          }
        }, (err) => {
        })
      }
  		}
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
	color: #fff;
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
</style>
