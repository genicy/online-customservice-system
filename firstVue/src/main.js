// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'

import login from './components/login'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import axios from 'axios'
import chat from './components/Chat'
import 'element-ui/lib/theme-chalk/index.css'
import store from './vuex/store'



Vue.config.productionTip = false

/* eslint-disable no-new */
Vue.use(VueRouter)
Vue.use(ElementUI)
let router = new VueRouter({
  routes:
    [
      {
        path:'/',
        component:login
      },
      {
        path:'/chat',
        component:chat
      }
    ]
})

Vue.prototype.$axios=axios;
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
