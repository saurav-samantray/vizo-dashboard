const AUTH_TOKEN_KEY = 'auth_token';

const TokenService = {
  getAuthToken() {
    console.log('Fetching auth token from local storage');
    return localStorage.getItem(AUTH_TOKEN_KEY);
  },

  saveAuthToken(authToken) {
    console.log('Saving auth token: ', authToken);
    localStorage.setItem(AUTH_TOKEN_KEY, authToken);
  },

  removeAuthToken() {
    localStorage.removeItem(AUTH_TOKEN_KEY);
  }
};

export { TokenService };
