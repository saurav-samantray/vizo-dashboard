import Vue from 'vue';
import Vuex from 'vuex';
import layout from './modules/layout';
import auth from './modules/auth';
import chart from './modules/chart';
Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  modules: {
    layout,
    auth,
    chart
  },
  mutations: {},
  actions: {}
});
