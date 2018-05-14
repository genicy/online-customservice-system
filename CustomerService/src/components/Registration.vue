<template>
  <div>
    <span class="title-font">添加客服</span>
    </br>
    </br>
    <el-row class="tac">
      <el-col :span="8">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="邮箱地址" prop="mailaddress">
            <el-input type="text" v-model="ruleForm.mailaddress" auto-complete="on"></el-input>
          </el-form-item>
          <el-form-item style="" label="性别:" prop="gender">
            <el-radio v-model="ruleForm.gender" label="1">男</el-radio>
            <el-radio v-model="ruleForm.gender" label="0">女</el-radio>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input type="text" v-model="ruleForm.age" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    let validatemailaddress = (rule, value, callback) => {
      let regExp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
      if (value === '') {
        callback(new Error('请输入邮箱!'))
      } else if (regExp.test(value) === false) {
        callback(new Error('邮箱格式不正确，请输入正确的邮箱地址！'))
      } else {
        callback()
      }
    }
    let validategender = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入性别！'))
      } else {
        callback()
      }
    }
    let validateage = (rule, value, callback) => {
      let regExp = /^\+?[1-9][0-9]*$/
      if (value === '') {
        callback(new Error('请输入年龄！'))
      } else if (regExp.test(value) === false) {
        callback(new Error('年龄只能为正整数！'))
      } else {
        callback()
      }
    }
    return {
      url: '/api/register.action',
      ruleForm: {
        mailaddress: '',
        gender: '1',
        age: ''
      },
      rules: {
        mailaddress: [
          { required: true, message: '请输入邮箱！', trigger: 'blur' },
          { validator: validatemailaddress, trigger: 'blur' }
        ],
        gender: [
          { validator: validategender, trigger: 'blur' }
        ],
        age: [
          { validator: validateage, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    //  提交按钮事件
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        this.$axios.post(this.url, this.ruleForm)
          .then((response) => {
            if (response.data.errorMessage === null) {
              this.$message.success('注册成功！')
            } else {
              this.$message.warning('该邮箱已被注册！')
            }


            //  跳转到登录页

          })
      })
    },
    // 重置按钮事件
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}

</script>
<style scoped>
.title-font {
  font-size: 20px;
  font-family: "Hiragino Sans GB";
  font-weight: bold;
}

</style>
