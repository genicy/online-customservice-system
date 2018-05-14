<template>
  <div>
    <span class="title-font">聊天历史查看</span>
    <el-row :gutter="0">
      <el-col :span="7" :offset="0">
        <div>
          <div>
            <el-table ref="singleTable" :data="customService" highlight-current-row @cell-click="handleCurrentCustomer" height='500px' style="width: 100%" v-loading="loadingcustomer">
              <el-table-column prop="images" label="头像" width="150">
                <template slot-scope="scope">
                  <img v-if="scope.row.images==='图片路径'||scope.row.images===''" src="/static/loading.gif" class="picture"/>
                  <img v-else :src="'/static/'+scope.row.images" class="picture" />
                </template>
              </el-table-column>
              <el-table-column prop="cid" label="客服编号" width="140">
              </el-table-column>
            </el-table>
          </div>
        </div>
      </el-col>
      <el-col :span="10" :offset="0">
        <div>
          <el-table ref="singleTable" :data="servicetouser" highlight-current-row @cell-click="handleCurrentUser" height='500px' style="width: 100%" v-loading="loadinguser">
            <el-table-column prop="images" label="头像" width="110">
              <template slot-scope="scope">
                <img src="/static/loading.gif" class="picture" />
              </template>
            </el-table-column>
            <el-table-column prop="uid" label="用户编号" width="130">
            </el-table-column>
            <el-table-column prop="formattime" label="结束时间" width="160">
            </el-table-column>
          </el-table>
        </div>
      </el-col>
      <el-col :span="6" :offset="0">
        <div>
          <el-card class="box-card" shadow="never" v-loading="loadinghistory">
            <div slot="header" class="clearfix">
              <span>聊天历史</span>
            </div>
            <div v-for="o in chatlist" class="text item">
              <div>
                <div>
                  <span v-if="o.dir === 1" class="uid-color">{{o.uid}}</span>
                  <span v-else class="cid-color">{{o.cid}}</span> &nbsp;&nbsp;
                  <span :class="o.dir === 1?'uid-color':'cid-color'">{{o.formattime}}</span>
                </div>
                </br>
                <div>
                  <img v-if="o.cchat.indexOf('jpg') > 0 || o.cchat.indexOf('png') > 0 || o.cchat.indexOf('bmp') > 0" :src="o.cchat" />
                  <span v-else :class="o.dir === 1?'uid-color':'cid-color'" class="content" v-html="o.cchat"></span>
                </div>
              </div>
              </br>
            </div>
          </el-card>
          <div>
            <div class="page-ation">
              <el-pagination small layout="prev, pager, next" :total="totalNum" :page-size=1 @current-change="currentPage">
              </el-pagination>
            </div>
            <div class="block">
              <el-date-picker v-model="indate" type="date" placeholder="选择日期" :picker-options="pickerOptions">
              </el-date-picker>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      imageUrl: '/static/voids.jpg',
      customService: [],
      servicetouser: [],
      chatlist: [],
      currentCustomer: null,
      currentUser: null,
      totalNum: 0,
      indate: '',
      loadingcustomer: true,
      loadinguser: false,
      loadinghistory: false,
      getcurrentpage: null,
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        }
      }
    }
  },

  methods: {
    handleCurrentCustomer(val) {
      this.loadinguser = true
      this.currentCustomer = val
      this.getUser(val)
    },
    getUser(val) {
      this.$axios.get(`/api/showcustom.action/${val.cid}`)
        .then((res) => {
          this.servicetouser = res.data
          if (res.data.length === 0) {
            this.totalNum = 0
          }
          this.loadinguser = false
        }, (err) => {
          console.log(err)
          this.loadinguser = false
        })
    },
    showCustomerService() {
      this.$axios.get('/api/showservice.action')
        .then((res) => {
            this.loadingcustomer = false
            this.loadinguser = false
            this.customService = res.data
          },
          (err) => {
            console.log("error")
          })
    },
    handleCurrentUser(val) {
      this.currentUser = val
      this.getChat(null)
    },
    getChat(curPage, page = null) {
      this.loadinghistory = true
      this.$axios.post("/api/showchat.action", {
          uid: this.currentUser.uid,
          cid: this.currentCustomer.cid,
          currentPage: curPage,
          num: page
        })
        .then((res) => {
          this.chatlist = res.data.recordList
          this.totalNum = res.data.recordCount
          this.getcurrentpage = res.data.currentPage
          this.loadinghistory = false
        }, (err) => {
          console.log(err)
          this.loadinghistory = false
        })
    },
    currentPage(val) {
      this.getChat(this.getcurrentpage, val)
    }
  },
  mounted() {
    this.showCustomerService()
    clearInterval(this.intelval);
    this.intelval = setInterval(() => {
      this.showCustomerService()
      this.getUser(this.currentCustomer)
    }, 30000)
  },
  watch: {
    indate() {
      console.log("hello")
    },
    currentCustomer() {
      this.chatlist = []
      this.getcurrentpage = null
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

.el-row {
  margin-top: 40px;
  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}

.picture {
  width: 60px;
  height: 60px;
  border-radius: 42px;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 20px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  width: 400px;
  height: 490px;
  overflow-y: scroll;
}

.page-ation {
  float: left;
  margin-top: 10px;
}

.uid-color {
  color: #409EFF;
}

.cid-color {
  color: #67C23A;
}

.content {
  max-width: 400
}

</style>
