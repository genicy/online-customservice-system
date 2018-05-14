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
import info from './components/information'
import chat from './components/Chat'
import Layout from './components/Layout'
import Flow from './components/flow'
import echarts from 'echarts'
import customerInfo from './components/CustomerInfo'
import register from './components/Registration'
import VueLazyLoad from 'vue-lazyload'
import history from './components/history'
import robots from './components/robots'
Vue.use(VueLazyLoad, {
  error: './static/error.png',
  loading: './static/loading.gif'
})

Vue.prototype.$echarts = echarts
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
        path: '/info',
        component: info
      },
      {
        path: '/chat',
        component: chat
      },
      {
        path: '/layout',
        component: Layout,
        children: [
          {
            path: '/',
            component: Flow
          },
          {
            path: 'admininfo',
            component: customerInfo
          },
          {
            path: 'history',
            component: history
          },
          {
            path: 'robots',
            component: robots
          }

        ]
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
