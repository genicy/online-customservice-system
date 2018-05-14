<template>
  <div>
    <div class="header">
      <div>
        <span class="title-font">客服综评</span>
      </div>
      <div>
        <el-button @click="addcustomer">添加客服</el-button>
      </div>
    </div>
    <div>
      <el-table :data="customerService" height='500px' v-loading="loading">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="drop-data">
              <el-form-item label="客服编号:">
                <span>{{ props.row.cid }}</span>
              </el-form-item>
              <el-form-item label="性别:">
                <span v-if="props.row.gender === 0">女</span>
                <span v-else>男</span>
              </el-form-item>
              <el-form-item label="年龄:">
                <span>{{ props.row.age }}</span>
              </el-form-item>
              <el-form-item label="邮箱地址:">
                <span>{{ props.row.mailaddress }}</span>
              </el-form-item>
              <el-form-item label="电话号码:">
                <span>{{ props.row.telno}}</span>
              </el-form-item>
              <el-form-item label="综合评分:">
                <span>{{ props.row.judge }}</span>
              </el-form-item>
              <el-form-item label="已服务人数:">
                <span>{{ props.row.serivcNnum }}</span>
              </el-form-item>
              <el-form-item label="注册时间:">
                <span>{{ props.row.regTime }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="images" label="头像" width="180">
          <template slot-scope="scope">
            <img v-if="scope.row.images==='图片路径'||scope.row.images===''" src="/static/loading.gif" class="picture"/>
            <img v-else :src="'/static/'+scope.row.images" class="picture" />
          </template>
        </el-table-column>
        <el-table-column prop="cid" label="客服编号" width="200">
        </el-table-column>
        <el-table-column prop="judge" label="综合评分" width="300" sortable>
          <template slot-scope="scope">
            <el-rate v-model="scope.row.judge" disabled show-score text-color="#ff9900" score-template="{value}">
            </el-rate>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" :filters="[{ text: '在线', value: 1 }, { text: '离线', value: 0 }]" :filter-method="filterStatus" filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status === 0 ? 'info' : 'success'" close-transition v-if="scope.row.status === 0">离线</el-tag>
            <el-tag :type="scope.row.status === 0 ? 'info' : 'success'" close-transition v-else>在线</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog title="添加客服" :visible.sync="isShow" @close="onclose">
      <div class="form">
        <span class="label-mail">邮箱:</span>
        &nbsp;&nbsp;&nbsp;
        <el-input placeholder="请输入邮箱" v-model="mailaddress">
          <template slot="append">@qq.com</template>
        </el-input>
        <span v-if="!mailErrors.status">
          <i class="el-icon-error"></i>
          <span class="g-form-error">{{ mailErrors.errorText }}</span>
        </span>
      </div>
      <div class="form">
        <span class="label-mail">号码:</span>
        &nbsp;&nbsp;&nbsp;
        <el-input placeholder="请输入电话号码" v-model="telno">
        </el-input>
        <span v-if="!telnoErrors.status">
          <i class="el-icon-error"></i>
          <span class="g-form-error">{{ telnoErrors.errorText }}</span>
        </span>
      </div>
      <div class="form">
        <span class="label-mail">负责人:</span>
        <el-input placeholder="请输入名字" v-model="duty">
        </el-input>
        <span v-if="!dutyErrors.status">
          <i class="el-icon-error"></i>
          <span class="g-form-error">{{ dutyErrors.errorText }}</span>
        </span>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="isShow = false">取 消</el-button>
        <el-button type="primary" @click="submit" :plain="true">确 定</el-button>
      </div>
      <span v-if="errorText !== ''">
          <i class="el-icon-error"></i>
          <span class="g-form-error">{{ errorText }}</span>
      </span>
    </el-dialog>
  </div>
</template>
<script type="text/javascript">
export default {
  data() {
    return {
      customerService: [],
      intelval: null,
      loading: true,
      isShow: false,
      mailaddress: '',
      telno: '',
      duty: '',
      mailFlag: false,
      telnoFlag: false,
      dutyFlag: false,
      errorText: ''

    }
  },
  methods: {
    filterStatus(value, row) {
      return row.status === value;
    },
    showCustomerService() {
      this.$axios.get('/api/showservice.action')
        .then((res) => {
            this.loading = false
            this.customerService = res.data
          },
          (err) => {
            console.log("error")
          })
    },
    addcustomer() {
      this.isShow = true
    },
    onclose () {
      this.mailaddress= ''
      this.telno= ''
      this.duty= ''
      this.mailFlag= false
      this.telnoFlag= false
      this.dutyFlag= false
      this.errorText = ''
    },
    submit () {
      if(!this.mailErrors.success || !this.telnoErrors.success || !this.dutyErrors.success) {
        this.errorText = "部分选项未通过"
      }else {
        let activation = {
        mailaddress: this.mailaddress + '@qq.com',
        telno: this.telno,
        duty: this.duty
      }
      this.$axios.post('/api/register.action',activation)
      .then((res)=> {
        console.log(res.data)
        if(res.data === 'ok') {
          this.$message({
          message: '发送成功!',
          type: 'success'
        })
          this.isShow = false
        }else {
          this.errorText = res.data
        }
      },
      (err)=> {

      })
      }

    }
  },
  mounted() {
    this.showCustomerService()
    clearInterval(this.intelval);
    this.intelval = setInterval(() => {
      this.showCustomerService()
    }, 30000)

  },
  computed: {
    mailErrors () {
      this.errorText=''
      let errorText, status, success
      var szReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
      if (this.mailaddress === '') {
        status = false
        success = false
        errorText = '邮箱不能为空'
      }
      else if (!szReg.test(this.mailaddress+'@qq.com')) {
        status = false
        success = false
        errorText = '邮箱格式不正确'
      }else {
        status = true
        errorText = ''
        success = true
      }
      if (!this.mailFlag) {
        errorText = ''
        status = true
        this.mailFlag = true
      }
      return {
        status,
        errorText,
        success
      }
    },
    telnoErrors () {
      this.errorText=''
      let errorText, status, success
      var szReg = /^1[3|4|5|8][0-9]\d{4,8}$/
      if (this.telno === '') {
        status = false
        success = false
        errorText = '号码不能为空'
      }
      else if (!szReg.test(this.telno)) {
        status = false
        success = false
        errorText = '号码格式不正确'
      }else {
        status = true
        success = true
        errorText = ''
      }
      if (!this.telnoFlag) {
        errorText = ''
        status = true
        this.telnoFlag = true
      }
      return {
        status,
        errorText,
        success
      }
    },
    dutyErrors () {
      this.errorText=''
      let errorText, status, success
      if (this.duty === '') {
        status = false
        success = true
        errorText = '负责人不能为空'
      }
      else {
        status = true
        errorText = ''
        success = true
      }
      if (!this.dutyFlag) {
        errorText = ''
        status = true
        this.dutyFlag = true
      }
      return {
        status,
        errorText,
        success
      }
    }
  },
}

</script>
<style type="text/css" scoped>
.title-font {
  font-size: 20px;
  font-family: "Hiragino Sans GB";
  font-weight: bold;
}

el-table {
  width: 70%;
}

.drop-data {
  font-size: 0;
}

.drop-data label {
  width: 90px;
  color: #99a9bf;
}

.drop-data .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}

.picture {
  width: 60px;
  height: 60px;
  border-radius: 42px;
}

.header {
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.el-input {
  width: 50%;
}

.label-mail {
  font-size: 18px;
}
.form {
  margin-top: 3%;
}
.g-form-error {
  color: red;
}
.el-icon-error {
  color: red;
}
</style>
