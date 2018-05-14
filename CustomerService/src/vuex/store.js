import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    cid: 0,
    images: '',
    aid: 0
  },
  mutations: {
    setImg (state, img) {
      state.images = img
      localStorage.setItem('images', img)
    },
    setCid (state, cid) {
      state.cid = cid
      localStorage.setItem('cid', cid)
    },
    setAid (state, aid) {
      state.aid = aid
      localStorage.setItem('aid', aid)
    }
  },
  getters: {
    getCid (state) {
      return state.cid
    },
    getImg (state) {
      return state.images
    },
    getAid (state) {
      return state.aid
    }
  }
})
