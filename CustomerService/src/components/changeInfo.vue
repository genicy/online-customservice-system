<template xmlns="http://www.w3.org/1999/html">
  <div>

    <router-view></router-view>
    <el-container>
      <el-aside>
        <div class="images">
          <h5 style="color: aliceblue"><img v-if="imageUrl!==null" :src="imageUrl" height="40" width="40" @click="connection"/>
            <img v-else :src="imageUrl" height="40" width="40" @click="connection"/> &nbsp;&nbsp; {{cid}}

          </h5>
        </div>
        <div class="input">
          <el-input
            placeholder="请输入内容"
            prefix-icon="el-icon-search"
            v-model="input">
          </el-input>
        </div>
        <!--标签切换 -->

        <div class="service" >
          <el-tabs v-model="activeName" @tab-click="handleClick" >

            <el-tab-pane label= "用户" ><p v-for="ft in fortest" class="show-user"  >
              <el-dropdown  trigger="click" >
      <span class="el-dropdown-link" >
        <img :src="ft.url" class="show-user-pic" >{{ft.username}}
      </span>
                <el-dropdown-menu slot="dropdown">
                  <img :src="ft.url" class="show-user-pic1">
                  <el-dropdown-item>用户id：{{ft.id}}</el-dropdown-item>
                  <el-dropdown-item>姓名：{{ft.name}}</el-dropdown-item>
                  <el-dropdown-item>性别：{{ft.gender}}</el-dropdown-item>
                  <el-dropdown-item>年龄：{{ft.age}}</el-dropdown-item>
                  <span  @click=connections(ft.id)><el-dropdown-item class="select-chat" >发起会话</el-dropdown-item></span>
                </el-dropdown-menu>
              </el-dropdown>
            </p>
            </el-tab-pane>

            <el-tab-pane label="会话" >

              <el-tree :data="data" :props="defaultProps"
                       highlight-current node-key ="id"
                       ref="tree" @node-click="handleNodeClick" ></el-tree>
            </el-tab-pane>

            <el-tab-pane label="修改头像" >
              <div>
                <div style="margin-top: 0;"><img src="/static/userimg.PNG" height="100%" width="100%" /></div>
             <div style="margin-top: -400px;"><input type="file" name="avatar" accept="image/gif,image/jpeg,image/jpg,image/png"
                           @change="update($event)" style="opacity: 0;cursor: pointer"> </div>

              </div>

            </el-tab-pane>

          </el-tabs>

        </div>

      </el-aside>

    </el-container>
  </div>
</template>

<script>
export default {
  name: 'change-info',
  props: ['data', 'fortest'],
  data () {
    return {
      activeName: 'second',
      url: '',
      imageUrl: '',
      cid: '',
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  mounted: function (file) {
    this.cid = localStorage.getItem('cid')
    this.imageUrl = localStorage.getItem('images')
    console.log(this.imageUrl)
  },
  methods: {
    handleClick (tab, event) {
    },
    handleNodeClick (tab) {
      this.$emit('changeUser', tab.label)
    },
    connections (val) {
      this.$emit('changeUser', val)
    },
    toChat: function () {
      this.$router.push({path: '/test'})
    },

    update (event) {
      let file = event.target.files[0]
      let param = new FormData() // 创建form对象
      param.append('file', file, file.name)// 通过append向form对象添加数据
      this.$axios.post('/api/uploadImg', param)
        .then(response => {
          this.imageUrl = response.data.images
          localStorage.setItem('images', '/static/' + this.imageUrl)
          this.$axios.post('/api/setImg', {
            cid: this.cid,
            images: this.imageUrl
          }).then(response => {

          })
          this.imageUrl = localStorage.getItem('images')
        })
    },
    connection () {
      this.$store.commit('setCid', this.cid)
      this.$emit('setFlag', true)
    }

  },
  mounted: function () {
    this.imageUrl = localStorage.getItem('images')
    this.cid = localStorage.getItem('cid')
  },
  watch: {
    data: function (newData, oldData) {
    }
  }
}
</script>

<style scoped>
  .el-aside {
    background-color: #6ed0ce;
    color: #333;
    text-align: center;
    line-height: 400px;
    margin-left: 100px;
    height: 580px;
    width: 250px;
    margin-top: 0;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
    height:390px;
    width: 700px;
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
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  .images{
    height:20px;
    width:140px;
    margin-left: 20px;
    margin-top:10px;
    line-height: 20px;
  }
  .input{
    margin-left: 10%;
    line-height: 150px;
    width: 200px;
    margin-bottom: 0;
    height: 100px;
  }
  .service{
    line-height: 20px;
  }
  .item {
    margin-top: 10px;
    margin-right: 40px;
  }
  .show-user{
    margin-left:0;
    border-bottom:1px solid #999999;
  }
  .show-user-pic{
    height: 40px;
    width: 40px;
  }
  .show-user-pic1{
    height: 280px;
    width: 280px;
  }
  .select-chat{
    float: right;
    color: #0074D9;
  }
  input{
    height: 360px;
    filter:alpha(opacity=0);
  }
</style>
