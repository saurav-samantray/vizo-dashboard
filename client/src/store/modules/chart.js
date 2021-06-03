//import { AuthService, AuthenticationError } from '@/services/auth.service';
import { ChartService, ChartError } from '@/services/chart.service';
//import { TokenService } from '@/services/storage.service';
//import router from '@/router';

import { GENERATE_CHART_PREVIEW, GENERATE_CHART_PREVIEW_SUCCESS } from '../actions.js';

const dummyData = {
  id: 'Pie',
  name: 'Pie',
  option: {
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    series: [
      {
        name: 'Vizo Pie Chart',
        type: 'pie',
        radius: '70%',
        center: ['50%', '60%'],
        data: [
          {
            name: 'AddToCart',
            value: 4
          },
          {
            name: 'View',
            value: 3
          },
          {
            name: 'PlaceOrder',
            value: 2
          }
        ]
      }
    ]
  }
};

const state = {
  generatingChartPreview: false,
  previewChartData: {}
};

const getters = {
  previewChartData: state => state.previewChartData,
  generatingChartPreview: state => state.generatingChartPreview
};

const actions = {
  async [GENERATE_CHART_PREVIEW]({ commit }, { payload: payload, type: type }) {
    console.log('GENERATE_CHART_PREVIEW: ', payload);
    commit(GENERATE_CHART_PREVIEW);
    try {
      let data;

      if (type === 'preview') {
        data = await ChartService.previewPieChart(payload);
      } else if (type === 'saved') {
        data = await ChartService.getPieChartData(payload);
      }
      console.log('API success');
      commit(GENERATE_CHART_PREVIEW_SUCCESS, {
        data: data
      });

      //router.push(router.history.current.query.redirect || '/');
    } catch (e) {
      console.error(e);
      return false;
    }
  }
};

const mutations = {
  [GENERATE_CHART_PREVIEW]: state => {
    console.log('GENERATE_CHART_PREVIEW');
    state.generatingChartPreview = true;
    state.previewChartData = {};
  },
  [GENERATE_CHART_PREVIEW_SUCCESS]: (state, { data }) => {
    console.log('GENERATE_CHART_PREVIEW_SUCCESS');
    state.generatingChartPreview = false;
    state.previewChartData = data;
  }
};

export default {
  namespaced: false,
  state,
  getters,
  actions,
  mutations
};
