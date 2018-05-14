/**

 *  @author：夏震
 *  @time : 2018.3.31
 *   客服个人信息界面
 **/

<template>

<div>
  <el-container>
    <el-header>
      <i class="el-icon-d-arrow-left" @click="back" id="back-icon" />个人信息</el-header>
    <el-main>
      <el-form ref="form" :model="form" label-width="150px">
        <el-form-item label="邮箱">
          <el-input v-model="mailaddress" class="info" ></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="telno" class="info" ></el-input>
        </el-form-item>

        <el-form-item label="接待人数">
          <el-select v-model="form.region" placeholder="上限5人" class="info" >
            <el-option label="一个人" value="1"></el-option>
            <el-option label="二个人" value="2"></el-option>
            <el-option label="三个人" value="3"></el-option>
            <el-option label="四个人" value="4"></el-option>
            <el-option label="五个人" value="5"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="切换状态">
          <el-switch v-model="status"></el-switch>在线
        </el-form-item>

        <el-form-item label="我的评价">
          <el-rate
            v-model="judge"
            disabled
            show-score
            text-color="#ff9900"
            score-template="{value}">
          </el-rate>
        </el-form-item>
        <el-form-item label="服务人数">
          <el-input v-model="number" class="info"  disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="注册时间">
          <el-input v-model="regtime" class="info"  disabled="true"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即修改</el-button>
          <el-button type="primary" @click="changePassword">修改密码</el-button>

        </el-form-item>
      </el-form>
     </el-main>

  </el-container>

  </div>

</template>

<script>
import mains from '../components/changeinfo.vue'
export default {
  components: {
    mains
  },
  data () {
    return {
      telno: '',
      mailaddress: '',
      cid: '',
      status: '',
      judge: '',
      number: '',
      value: '',
      regtime: '',
      currentDate: new Date(),
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  methods: {
    open3 () {
      let that = this
      this.$prompt('请输入邮箱', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ mailaddress }) => {
        this.$message({
          type: 'success',
          message: '你的邮箱是: ' + mailaddress
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        })
      })
    },
    open4 () {
      this.$prompt('请输入你的联系方式', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ telno }) => {
        this.$message({
          type: 'success',
          message: '你的联系方式是是: ' + value

        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        })
      })
    },
    changePassword () {
      this.$router.push('/changePassword')
    },
    back () {
      this.$emit('back', false)
    },
    onSubmit () {
      var StatusNum
      if (this.status) {
        StatusNum = 1
      } else {
        StatusNum = 0
      }
      this.$axios.post('/api/updateInfor', {
        cid: sessionStorage.getItem('cid'),
        telno: this.telno,
        maxNum: this.form.region,
        serviceStatus: StatusNum
      }).then((response) => {
        this.telno = response.data.telno,
        this.form.region = response.data.maxNum
        if (response.data.serviceStatus == 1) {
          this.status = true
        } else {
          this.status = false
        }
      })
    }

  },
  mounted: function () {
    this.$axios.post('/api/getInfo', {
      cid: sessionStorage.getItem('cid')
    }).then((response) => {
      this.cid = response.data.cid,
      this.mailaddress = response.data.mailaddress,
      this.telno = response.data.telno,
      this.status = response.data.status,
      this.judge = response.data.judge,
      this.number = response.data.serviceNnum,
      this.regtime = response.data.regTime
    })
    this.$axios.post('/api/selectInfo', {
      cid: sessionStorage.getItem('cid')
    }).then((response) => {
      this.telno = response.data.telno,
      this.form.region = response.data.maxNum
      if (response.data.serviceStatus == 1) {
        this.status = true
      } else {
        this.status = false
      }
    })
  }
}
</script>

<style>

  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
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
.showinfo{
  height: 20px;
  line-height: 20px;
  float:left;
  margin-left: 10%;
  margin-right: 10%;
}
  .switch{
    margin: 0px;
    height:80px;
    margin-left: 10%;
  }
#back-icon{
  float: left;
  font-size: x-large;
  margin-top: 20px;
  cursor:pointer;
}
.info{
  width: 300px;
}
</style>
