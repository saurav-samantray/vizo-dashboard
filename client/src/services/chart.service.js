import ApiService from './api.service';

class ChartError extends Error {
  constructor(errorCode, message, errorFields) {
    super(message);
    this.name = this.constructor.name;
    this.errorCode = errorCode;
    this.message = message;
    this.fields = errorFields;
  }
}

const ChartService = {
  getCharts: async function() {
    try {
      const response = await ApiService.get('/api/charts');
      //console.log(response)
      return response.data;
    } catch (e) {
      throw new ChartError(e.response.status, e.response.data.error);
    }
  },
  getPieChart: async function() {
    try {
      const response = await ApiService.get('/pie');
      //console.log(response)
      return response.data;
    } catch (e) {
      throw new ChartError(e.response.status, e.response.data.error);
    }
  },
  previewPieChart: async function(payload) {
    try {
      const response = await ApiService.post('/api/charts/pie/preview', payload);
      //console.log(response)
      return response.data;
    } catch (e) {
      throw new ChartError(e.response.status, e.response.data.error);
    }
  }
};

export default ChartService;

export { ChartService, ChartError };
