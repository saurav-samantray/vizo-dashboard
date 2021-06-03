import { AuthService, AuthenticationError } from '@/services/auth.service';
import { TokenService } from '@/services/storage.service';
import router from '@/router';

import {
  AUTH_INIT,
  GENERATE_AUTH_TOKEN_REQUEST,
  GENERATE_AUTH_TOKEN_REQUEST_SUCCESS,
  GENERATE_AUTH_TOKEN_REQUEST_ERROR,
  AUTH_LOGOUT,
  AUTH_LOGOUT_ERROR
} from '../actions.js';

const state = {
  authenticating: false,
  authToken: TokenService.getAuthToken(),
  authenticationErrorCode: 0,
  authenticationError: '',
  authTokenRequestUserName: '',
  subject: '',
  sessionExpired: false,
  roles: []
};

const getters = {
  loggedIn: state => (state.authToken ? true : false),

  subject: state => state.subject,

  roles: state => state.roles,

  hasRole: state => role => state.roles.includes(role),

  authenticationStatus: state => (state.authTokenRequestUserName ? 'Auth Token Generated for ' + state.authTokenRequestUserName : ''),

  authenticationErrorCode: state => state.authenticationErrorCode,

  authenticationError: state => state.authenticationError,

  authenticating: state => state.authenticating,

  sessionExpired: state => state.sessionExpired
};

const actions = {
  async [GENERATE_AUTH_TOKEN_REQUEST]({ commit }, payload) {
    console.log('GENERATE_AUTH_TOKEN_REQUEST: ', payload);
    commit(GENERATE_AUTH_TOKEN_REQUEST);
    try {
      const auth = await AuthService.generateAuthToken(payload.username, payload.password);
      console.log('Generate auth token API response :', auth);
      commit(GENERATE_AUTH_TOKEN_REQUEST_SUCCESS, {
        username: auth.username,
        authToken: auth.token
      });

      router.push(router.history.current.query.redirect || '/');
    } catch (e) {
      if (e instanceof AuthenticationError) {
        commit(GENERATE_AUTH_TOKEN_REQUEST_ERROR, {
          errorCode: e.errorCode,
          errorMessage: e.message
        });
      }
      return false;
    }
  },
  async [AUTH_LOGOUT]({ commit }) {
    console.log('dispatch AUTH_LOGOUT');
    try {
      await AuthService.logout();
    } catch (e) {
      if (e instanceof AuthenticationError) {
        commit(AUTH_LOGOUT_ERROR, {
          errorCode: e.errorCode,
          errorMessage: e.message
        });
      }
    } finally {
      commit(AUTH_LOGOUT);
      router.push('/login');
    }
  }
};

const mutations = {
  [GENERATE_AUTH_TOKEN_REQUEST]: state => {
    console.log('GENERATE_AUTH_TOKEN_REQUEST');
    state.authenticating = true;
    state.authenticationError = '';
    state.authenticationErrorCode = 0;
    state.authTokenRequestUserName = '';
    state.authToken = '';
  },
  [GENERATE_AUTH_TOKEN_REQUEST_SUCCESS]: (state, { username, authToken }) => {
    console.log('GENERATE_AUTH_TOKEN_REQUEST_SUCCESS');
    state.authenticating = false;
    state.authenticationError = '';
    state.authenticationErrorCode = 0;
    state.authTokenRequestUserName = username;
    state.authToken = authToken;
  },
  [GENERATE_AUTH_TOKEN_REQUEST_ERROR]: (state, { errorCode, errorMessage }) => {
    console.log('GENERATE_AUTH_TOKEN_REQUEST_ERROR');
    state.authenticating = false;
    state.authenticationErrorCode = errorCode;
    state.authenticationError = errorMessage;
  },
  [AUTH_LOGOUT]: state => {
    console.log('AUTH_LOGOUT');
    state.authenticationErrorCode = '';
    state.authenticationError = '';
    state.authTokenRequestUserName = '';
    state.authToken = '';
  },
  [AUTH_LOGOUT_ERROR]: (state, { errorCode, errorMessage }) => {
    console.log('AUTH_LOGOUT_ERROR');
    state.authenticating = false;
    state.authenticationErrorCode = errorCode;
    state.authenticationError = errorMessage;
  }
};

export default {
  namespaced: false,
  state,
  getters,
  actions,
  mutations
};
