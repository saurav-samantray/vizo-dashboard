import ApiService from './api.service';

class DatasourcesError extends Error {
  constructor(errorCode, message) {
    super(message);
    this.name = this.constructor.name;
    this.errorCode = errorCode;
    this.message = message;
  }
}

const DatasourcesService = {
  getDatasources: async function() {
    try {
      const response = await ApiService.get('/api/datasources');
      console.log(response);
      return response.data;
    } catch (e) {
      console.error(e);
      return response.data;
      //throw new DatasourcesError(e.response.status, e.response.data.error);
    }
  },

  save: async function(datasource) {
    try {
      const response = await ApiService.post('/api/datasources', datasource);
      //console.log(response)
      return response.data;
    } catch (e) {
      throw new DatasourcesError(e.response.status, e.response.data.error);
    }
  },

  delete: async function(id) {
    try {
      const response = await ApiService.delete('/api/datasources/' + id);
      return response.data;
    } catch (e) {
      throw new DatasourcesError(e.response.status, e.response.data.error);
    }
  },

  update: async function(datasource) {
    try {
      const response = await ApiService.put('/api/datasources/' + datasource.id, datasource);
      return response.data;
    } catch (e) {
      throw new DatasourcesError(e.response.status, e.response.data.error);
    }
  },

  getAllKeys: async function(id) {
    try {
      const response = await ApiService.get(`/api/datasources/${id}/keys`);
      return response.data;
    } catch (e) {
      throw new DatasourcesError(e.response.status, e.response.data.error);
    }
  }
};

export default DatasourcesService;

export { DatasourcesService, DatasourcesError };
