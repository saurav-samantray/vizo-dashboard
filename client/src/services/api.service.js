import axios from 'axios';
import { TokenService } from '../services/storage.service';
import store from '../store/store.js';
import { AUTH_LOGOUT } from '../store/actions';

const ApiService = {
  init(baseURL) {
    axios.defaults.baseURL = baseURL;
    axios.defaults.headers.common = {
      Accept: 'application/json',
      'Content-Type': 'application/json'
    };
    //store.dispatch(AUTH_INIT)
  },

  setHeader() {
    if (TokenService.getAuthToken() != null) {
      console.log('authorization token avaiable, setting to axios header');
      axios.defaults.headers.common['Authorization'] = `Bearer ${TokenService.getAuthToken()}`;
    } else {
      console.log('Authorization token not available');
    }
  },

  setRefreshHeader() {
    //axios.defaults.headers.common['Authorization'] = `Bearer ${TokenService.getRefreshToken()}`;
  },

  removeHeader() {
    axios.defaults.headers.common = {};
  },

  get(resource, params) {
    return axios.get(resource, { params: params });
  },

  post(resource, data) {
    return axios.post(resource, data);
  },

  put(resource, data) {
    return axios.put(resource, data);
  },

  delete(resource) {
    return axios.delete(resource);
  },

  /**
   * Perform a custom Axios request.
   *
   * data is an object containing the following properties:
   *  - method
   *  - url
   *  - data ... request payload
   *  - auth (optional)
   *    - username
   *    - password
   **/
  customRequest(data) {
    return axios(data);
  },

  // Stores the 401 interceptor position
  _401interceptor: null,

  mount401Interceptor() {
    console.log('Mounting the 401 interceptor');
    this._401interceptor = axios.interceptors.response.use(
      function(response) {
        return response;
      },
      function(error) {
        console.log('Intercepted 401');
        if (error.request.status == 401) {
          store.dispatch(AUTH_LOGOUT);
        }

        // If error was not 401 just reject as is
        return Promise.reject(error);
      }
    );
  },

  unmount401Interceptor() {
    // Eject the interceptor
    axios.interceptors.response.eject(this._401interceptor);
  }
};

export default ApiService;
