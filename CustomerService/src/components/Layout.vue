<template>
  <div>
    <el-container class="main">
      <el-aside :width="tabWidth+'px'" ref="aside">
        <div>
          <div class="isClossTab" @click="isClossTabFun">
            <i :class="isCollapse?'el-icon-d-arrow-right':'el-icon-d-arrow-left'"></i>
          </div>
          <el-menu :class="'menu'" default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
            <el-menu-item index="1">
              <i class="el-icon-loading"></i>
              <router-link :to="{ path: '/layout/' }" tag="span" solt="title">
                流量监控
              </router-link>
            </el-menu-item>
            <el-menu-item index="2">
              <i class="el-icon-setting"></i>
              <router-link :to="{ path: '/layout/robots' }" tag="span" solt="title">
                机器人设置
              </router-link>
            </el-menu-item>
            <el-menu-item index="3">
              <i class="el-icon-tickets"></i>
              <router-link :to="{ path: '/layout/admininfo' }" tag="span" solt="title">
                客服综评
              </router-link>
            </el-menu-item>
            <el-menu-item index="4">
              <i class="el-icon-view"></i>
              <router-link :to="{ path: '/layout/history' }" tag="span" solt="title">
                历史会话
              </router-link>
            </el-menu-item>
          </el-menu>
        </div>
      </el-aside>
      <el-container>
        <el-header class="main-header">
          <el-dropdown>
            <span class="el-dropdown-link">
                            <img src="" alt="">
                        </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>
        <el-main>
          <div>
            <keep-alive>
              <router-view></router-view>
            </keep-alive>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<style>
* {
  padding: 0;
  margin: 0;
}

</style>
<style scoped lang="scss">
$header-height:60px;
$background-color: #545c64;
$color: #FFF;

.main {
  height: 100vh;
  min-width: 800px;
  min-height: 600px;
  overflow: hidden;

  aside {
    overflow: visible;
    height: 100%;
    background-color: $background-color;
    color: $color;

    .isClossTab {
      width: 100%;
      height: $header-height;
      cursor: pointer;
      font-size: 25px;
      text-align: center;
      line-height: $header-height;
      font-weight: bold;
      border-right: 1px solid #807c7c;
      box-sizing: border-box;
    }
    .menu {
      width: 100%;
      border-right: 0;
    }
  }

  .main-header {
    background-color: $background-color;
    color: $color;

    .el-dropdown {
      cursor: pointer;
      float: right;
    }
    .el-dropdown-link {

      img {
        $imgMargin: (($header-height - 50)/2);
        display: inline-block;
        width: 50px;
        height: 50px;
        border-radius: 25px;
        background-color: #FFF;
        margin-top: $imgMargin;
      }
    }
  }
  .crumbs {
    margin-bottom: 20px;
  }
}

</style>
<script>
export default {
  data() {
    return {
      isCollapse: false,
      tabWidth: 200,
      intelval: null
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClick(tab, event) {
      console.log(tab.name, event);
    },
    isClossTabFun() {
      clearInterval(this.intelval);
      if (!this.isCollapse) {
        this.intelval = setInterval(() => {
          if (this.tabWidth <= 64)
            clearInterval(this.intelval);
          this.tabWidth -= 1;
        }, 1);
      } else {
        this.tabWidth = 200;
      }
      this.isCollapse = !this.isCollapse;
    }
  }
}

</script>
