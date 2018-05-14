// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import changePassword from './components/changePassword'
import mains from './components/changeInfo'
import defaultpage from './components/login'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import axios from 'axios'
import 'element-ui/lib/theme-chalk/index.css'
import store from './vuex/store'
import helloworld from './components/HelloWorld'
import info from './components/information'
import chat from './components/Chat'

Vue.config.productionTip = false

/* eslint-disable no-new */
Vue.use(VueRouter)
Vue.use(ElementUI)
let router = new VueRouter({
  routes:
    [
      {
        path: '/',
        component: defaultpage
      },
      {
        path: '/changePassword',
        component: changePassword
      },
      {

        path: '/mains',
        component: mains
      },
      {
        path: '/helloworld',
        component: helloworld
      },
      {
        path: '/info',
        component: info
      },
      {
        path: '/chat',
        component: chat
      }

    ]
})

Vue.prototype.$axios = axios
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
