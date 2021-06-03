import ApiService from './api.service';

class DashboardError extends Error {
  constructor(errorCode, message, errorFields) {
    super(message);
    this.name = this.constructor.name;
    this.errorCode = errorCode;
    this.message = message;
    this.fields = errorFields;
  }
}

const DashboardService = {
  getAll: async function() {
    try {
      const response = await ApiService.get('/api/dashboards');
      //console.log(response)
      return response.data;
    } catch (e) {
      throw new ChartError(e.response.status, e.response.data.error);
    }
  },
  save: async function(payload) {
    try {
      const response = await ApiService.post('/api/dashboards', payload);
      //console.log(response)
      return response.data;
    } catch (e) {
      throw new ChartError(e.response.status, e.response.data.error);
    }
  }
};

export default DashboardService;

export { DashboardService, DashboardError };
