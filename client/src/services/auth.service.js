import ApiService from './api.service';
import { TokenService } from './storage.service';

class AuthenticationError extends Error {
  constructor(errorCode, message) {
    super(message);
    this.name = this.constructor.name;
    this.message = message;
    this.errorCode = errorCode;
  }
}

const AuthService = {
  generateAuthToken: async function(username, password) {
    const requestData = {
      method: 'post',
      url: '/api/token/generate-token',
      data: {
        username: username,
        password: password
      }
    };

    try {
      const response = await ApiService.customRequest(requestData);
      console.log('Generate Auth token success: ', response.data);
      TokenService.saveAuthToken(response.data.token);

      ApiService.setHeader();
      ApiService.mount401Interceptor();
      return response.data;
    } catch (e) {
      throw new AuthenticationError(e.response.status, e.response.data.error);
    }
  },

  requestJWT: async function(token) {
    const requestData = {
      method: 'post',
      url: 'auth/token',
      data: {
        grant_type: 'token',
        token: token
      }
    };

    try {
      const response = await ApiService.customRequest(requestData);

      TokenService.saveToken(response.data.access_token);
      TokenService.saveRefreshToken(response.data.refresh_token);

      ApiService.setHeader();
      ApiService.mount401Interceptor();

      return response.data;
    } catch (e) {
      throw new AuthenticationError(e.response.status, e.response.data.error);
    }
  },

  refreshToken: async function() {
    const requestData = {
      method: 'post',
      url: 'auth/refresh',
      data: {
        grant_type: 'refresh'
      }
    };

    try {
      ApiService.setRefreshHeader();
      const response = await ApiService.customRequest(requestData);

      TokenService.saveToken(response.data.access_token);
      TokenService.saveRefreshToken(response.data.refresh_token);
      ApiService.setHeader();

      return response.data;
    } catch (e) {
      throw new AuthenticationError(e.response.status, e.response.data.error);
    }
  },

  logout: async function() {
    ApiService.unmount401Interceptor();
    ApiService.removeHeader();
    TokenService.removeAuthToken();
  }
};

export default AuthService;

export { AuthService, AuthenticationError };
