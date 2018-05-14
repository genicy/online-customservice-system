import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    cid: 0,
    images: ''
  },
  mutations: {
    setImg (state, img) {
      state.images = img
      sessionStorage.setItem('images', img)
    },
    setCid (state, cid) {
      state.cid = cid
      sessionStorage.setItem('cid', cid)
    }
  },
  getters: {
    getCid (state) {
      return state.cid
    },
    getImg (state) {
      return state.images
    }
  }
})

