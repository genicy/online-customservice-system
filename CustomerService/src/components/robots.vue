<template>
<div class="order-wrap">
<h3>机器人配置</h3>
<div class="bordera"></div>
<div class="order-list-choose">
  <div class="order-list-option">
    <div class="g-form-btn">

      <button id="button" class="button" @click="dialogFormVisible = true"  >确认添加</button>

<el-dialog title="添加机器人" :visible.sync="dialogFormVisible">
  <el-form :model="form">
    <el-form-item label="关键词：" :label-width="formLabelWidth">
      <el-input v-model="form.keyword" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="回复信息：" :label-width="formLabelWidth">
      <el-input type="textarea" v-model="form.reply" :rows="2"
  placeholder="请输入内容" auto-complete="off"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogCommit()" >确 定</el-button>
  </div>
</el-dialog>

</div>

</div>
<div class="g-form-check">
    <div class="g-form-check-note"><span >启用/关闭机器人</span></div>
    <div class="testswitch">
              <input class="testswitch-checkbox" @click="checkRobot" v-model="checkboxData" id="onoffswitch" type="checkbox">
              <label class="testswitch-label" v-model="flag" for="onoffswitch">
                  <span class="testswitch-inner"  data-on="ON" data-off="OFF"></span>
                  <span class="testswitch-switch" ></span>
              </label>
    </div>
</div>
</div>
<div class="order-list-table">
  <table>
    <tr>
      <th v-for="head in tableHeads" @click="changeOrderType(head)" :class="{active:head.active}">{{ head.label }}</th>
    </tr>
    <tr v-for="(item,index) in tableData" :key="item.times">
      <td v-for="head in tableHeads">{{ item[head.key] }}</td>
      <el-button type="primary" @click="deleteRobots(index)" icon="el-icon-delete">删除</el-button>
      <el-button type="primary" @click="updateDialogForm(index)" icon="el-icon-edit">修改</el-button>

      <el-dialog title="修改信息" :visible.sync="updateDialogFormVisible">
  <el-form :model="form">
    <el-form-item label="关键词：" :label-width="formLabelWidth">
      <el-input v-model="updateKeyword" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="回复信息：" :label-width="formLabelWidth">
      <el-input type="textarea" v-model="updateReply" :rows="2" auto-complete="off"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="updateDialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="updateDialogCommit(index)" >确 定</el-button>
  </div>
</el-dialog>

    </tr>
  </table>

</div>

</div>
</template>

<script>

import _ from 'lodash'
export default {
  components: {

  },
  data () {
    return {
      dialogFormVisible: false,
      updateDialogFormVisible: false,
      form: {
        keyword: '',
        reply: ''
      },
      tableHeads: [
        {
          label: '序号',
          key: 'id'
        },
        {
          label: '关键词',
          key: 'keyword'
        },
        {
          label: '回复信息',
          key: 'answer'
        },
        {
          label: '修改时间',
          key: 'updateTime'
        }
      ],
      currentOrder: 'asc',
      tableData: [],
      checkboxData: ''
    }
  },
  watch: {
    query () {
      this.getList()
    }
  },
  methods: {
    updateDialogForm (index) {
      this.updateDialogFormVisible = true
      this.updateKeyword = this.tableData[index].keyword
      this.updateReply = this.tableData[index].answer
    },
    updateDialogCommit (index) {
      this.$axios.post('/api/updateKAndA', {
        keyword: this.updateKeyword,
        answer: this.updateReply,
        id: this.tableData[index].id,
        rid: 1
      }).then((response) => {
        this.tableData = response.data
      })
      this.updateDialogFormVisible = false
    },
    checkRobot () {
      var status
      if (this.checkboxData) {
        status = 1
      } else {
        status = 0
      }
      this.$axios.post('/api/setRobotStatus', {
        status: status,
        rid: 1

      }).then((response) => {
        this.tableData = response.data
      })
    },
    dialogCommit () {
      this.$axios.post('/api/addRobot', {
        keyword: this.form.keyword,
        answer: this.form.reply,
        rid: 1

      }).then((response) => {
        this.tableData = response.data
      })
      this.dialogFormVisible = false
    },
    deleteRobots (index) {
      this.$axios.post('/api/deleteRobot.action', {
        rid: this.tableData[index].rid,
        id: this.tableData[index].id

      }).then((response) => {
        this.tableData = response.data
      })
    },
    closeDialog (attr) {
      this[attr] = false
    },
    addmessage () {
      this.isShowAboutDialog = true
    },
    successMessage () {
      this.isShowSuccDialog = true
    },
    productChange (obj) {
      this.productId = obj.value
      this.getList()
    },
    getList () {
      let reqParams = {
        query: this.query,
        startDate: this.startDate,
        endDate: this.endDate
      }
      this.$axios.post('/safely/getVacationList', reqParams)
        .then((res) => {
          if (res.data.status === 401) {
            localStorage.clear()
            this.$router.push({path: '/safely'})
          } else if (res.data.status === 400) {
            this.errmessage = '输入错误'
          } else if (res.data.status === 401) {
            localStorage.clear()
            this.$router.push({path: '/safely'})
          } else {
            this.errmessage = ''
            this.tableData = res.data.list
          }
        }, (err) => {

        })
    },
    changeOrderType (headItem) {
      this.tableHeads.map((item) => {
        item.active = false
        return item
      })
      headItem.active = true
      if (this.currentOrder === 'asc') {
        this.currentOrder = 'desc'
      } else if (this.currentOrder === 'desc') {
        this.currentOrder = 'asc'
      }
      this.tableData = _.orderBy(this.tableData, headItem.key, this.currentOrder)
    },
    insertSuccess () {
      this.closeDialog('isShowAboutDialog')
      setTimeout(() => {

      }, 1000)
      this.successMessage()
      setTimeout(() => {
        this.closeDialog('isShowSuccDialog')
      }, 3000)
      this.getList()
    }
  },
  mounted: function () {
    let Vrid = 1
    this.$axios.post('/api/selectAllRobot', {
      rid: Vrid
    }).then((response) => {
      this.tableData = response.data
    })

    this.$axios.post('/api/selectRobotStatus', {
      rid: Vrid
    }).then((response) => {
      if (response.data.status == 0) {
        this.checkboxData = true
      } else {
        this.checkboxData = false
      }
    })
    this.getList()
  }
}
</script>

<style scoped>
.button{
  margin-top: 10px;
  margin-left:0;
  color: #fef4e9;
  border: solid 1px #da7c0c;
  background: #f78d1d;
  background: -webkit-gradient(linear, left top, left bottom, from(#faa51a), to(#f47a20));
  display: inline-block;
  outline: none;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
  font: 16px/100% 'Microsoft yahei',Arial, Helvetica, sans-serif;
  padding: .5em 2em .55em;
  text-shadow: 0 1px 1px rgba(0,0,0,.3);
  -webkit-border-radius: .5em;
  -moz-border-radius: .5em;
  border-radius: .5em;
  -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);
  -moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);
  box-shadow: 0 1px 2px rgba(0,0,0,.2);
}
.bordera{
border:0.5px solid #4fc08d;
}
.addRobot-textarea{
  margin-top: 20px;
}
.g-form-check{
  float: right;
}
.g-form-check-note{
  margin-top: 20px;
  float: left;
}
.testswitch {
  position: relative;
  float: right;
  width: 90px;
  margin: 15px;
  -webkit-user-select:none;
  -moz-user-select:none;
  -ms-user-select: none;
}

.testswitch-checkbox {
  display: none;
}

.testswitch-label {
  display: block;
  overflow: hidden;
  cursor: pointer;
  border: 2px solid #999999;
  border-radius: 20px;
}

.testswitch-inner {
  display: block;
  width: 200%;
  margin-left: -100%;
  transition: margin 0.3s ease-in 0s;
}

.testswitch-inner::before, .testswitch-inner::after {
  display: block;
  float: right;
  width: 50%;
  height: 30px;
  padding: 0;
  line-height: 30px;
  font-size: 14px;
  color: white;
  font-family:
  Trebuchet, Arial, sans-serif;
  font-weight: bold;
  box-sizing: border-box;
}

.testswitch-inner::after {
  content: attr(data-on);
  padding-left: 10px;
  background-color: #00e500;
  color: #FFFFFF;
}

.testswitch-inner::before {
  content: attr(data-off);
  padding-right: 10px;
  background-color: #EEEEEE;
  color: #999999;
  text-align: right;
}

.testswitch-switch {
  position: absolute;
  display: block;
  width: 22px;
  height: 22px;
  margin: 4px;
  background: #FFFFFF;
  bottom: 0;
  right: 56px;
  border: 2px solid #999999;
  border-radius: 20px;
  transition: all 0.3s ease-in 0s;
}

.testswitch-checkbox:checked + .testswitch-label .testswitch-inner {
  margin-left: 0;
}

.testswitch-checkbox:checked + .testswitch-label .testswitch-switch {
  right: 0px;
}
.order-wrap {
width: 100%;
min-height: 800px;
margin: 20px auto;
overflow: hidden;
}
.order-wrap h3 {
font-size: 20px;
font-weight: bold;
margin-bottom: 20px;
}
.order-query {
height: 25px;
line-height: 25px;
border: 1px solid #e3e3e3;
outline: none;
text-indent: 10px;
}
.order-list-option {
display: inline-block;
padding-left: 15px;
}
.order-list-option:first-child {
padding-left: 0;
}
.order-list-table {
margin-top: 20px;
}
.order-list-table table {
width: 100%;
background: #fff;
}
.order-list-table td,
.order-list-table th {
border: 1px solid #e3e3e3;
text-align: center;
padding: 10px 0;
}
.order-list-table th {
background: #4fc08d;
color: #fff;
border: 1px solid #4fc08d;
cursor: pointer;
}
.order-list-table th.active {
background: #35495e;
}
.errmessage {
color: red;
}
</style>
