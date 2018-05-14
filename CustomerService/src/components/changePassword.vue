<template>
<div>
    <div style="float: left"><maincomponents >
    </maincomponents></div>

      <div><el-container>
        <el-header>
          <i class="el-icon-d-arrow-left" @click="back" id="back-icon" />修改密码</el-header>
        <el-main> <el-steps :active="active" finish-status="success" align-center >
          <el-step title="输入旧密码" style="line-height: 10px;"></el-step>
          <el-step title="输入新密码" style="line-height: 10px"></el-step>
          <el-step title="再次输入" style="line-height: 10px"></el-step>
        </el-steps>
          <el-form ref="form" :model="form" label-width="10%" style="margin-top: 100px">
          <el-form-item label="" >
            <el-input v-model="password" style="width: 300px"></el-input>
          </el-form-item>
          </el-form>
          <el-button style="margin-top: 12px;" @click="next">下一步</el-button></el-main>
      </el-container>
     </div>
</div>
</template>

<script>
import maincomponents from '../components/changeInfo.vue'
export default {
  name: 'changePassword',
  components: {maincomponents},
  data () {
    return {
      active: 0,
      password: '',
      newpassword: '',
      flag: false
    }
  },
  methods: {
    next () {
      if (this.active == 0) {
        if (this.password == '') {
          this.$message({
            showClose: true,
            message: '密码不能为空',
            type: 'warning'
          });
          this.active = 0
        } else {
          this.$axios.post('/api/checkpwd', {
            cid: localStorage.getItem('cid'),
            password: this.password
          }).then((response) => {
            this.flag = response.data.cid
            if (this.flag == undefined) {
              this.active = 0,
                this.$message({
                  showClose: true,
                  message: '密码输入错误',
                  type: 'warning'
                });
              this.password = ''
            } else {
              this.password = '',
              this.active++
            }
          })
        }
      } else if (this.active == 1) {
        if (this.password == '') {
          alert('新密码不能为空')
          active = 1
        } else if (this.password.length < 6 || this.password.length > 12) {
          alert('长度不能大于6位小于12位')
          active = 1
        } else {
          this.newpassword = this.password,
          this.password = '',
          this.active++
        }
      } else if (this.active == 2) {
        if (this.password !== this.newpassword) {
          this.password = '',
          this.newpassword = '',
          this.active = 1
          this.$message({
            showClose: true,
            message: '两次密码不一致',
            type: 'warning'
          });
        } else if (this.password === this.newpassword) {
          this.$axios.post('/api/setNewPwd', {
            cid: localStorage.getItem('cid'),
            password: this.newpassword
          }).then((response) => {
            if (response.data.password != undefined) {
              this.active++
              this.$message({
                showClose: true,
                message: '修改成功',
                type: 'success'
              });
            }
          })
        }
      }
      if (this.active > 2) this.active = 0
    },
    back () {
      this.$router.push('/chat')
    }
  }
}

</script>

<style scoped>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    width:80%;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;

  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
    height: 540px;
    width:80%;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>
