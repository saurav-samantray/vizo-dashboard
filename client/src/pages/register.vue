<template>
  <q-layout>
    <q-page-container>
      <q-page class="flex flex-center">
        <q-card class="ub-login-card">
          <q-card-section class="text-center">
            <div class="text-h4">Register</div>
          </q-card-section>

          <q-card-section>
            <q-card-section>
              <q-input v-model="firstName" clearable label="First Name" />
              <q-input v-model="lastName" clearable label="Last Name" />
              <q-input v-model="email" clearable label="Email Address" />
              <q-input v-model="username" clearable label="Username" />

              <q-input v-model="username" clearable label="Username" />
              <q-input v-model="password" type="password" clearable label="password" />
            </q-card-section>
          </q-card-section>

          <q-card-actions align="right">
            <!-- <q-btn flat>Register</q-btn> -->
            <q-btn color="primary" unelevated @click="submit">Register</q-btn>
          </q-card-actions>
        </q-card>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import { GENERATE_AUTH_TOKEN_REQUEST } from '@/store/actions';

export default {
  data() {
    return {
      firstName: '',
      lastName: '',
      email: '',
      username: '',
      password: ''
    };
  },
  methods: {
    ...mapActions([GENERATE_AUTH_TOKEN_REQUEST]),
    submit() {
      const payload = { username: this.username, password: this.password };
      console.log('Submitting form : ', payload);
      this.GENERATE_AUTH_TOKEN_REQUEST(payload);
    }
  },
  computed: {
    ...mapGetters(['authenticating', 'authenticationStatus', 'authenticationError', 'authenticationErrorCode']),
    dark: {
      get() {
        return this.$store.state.layout.dark;
      }
    }
  },
  mounted() {
    this.$q.dark.set(this.dark);
  }
};
</script>

<style lang="scss">
.ub-login-card {
  width: 100%;
  max-width: 400px;
  .ub-login-hdr {
    height: 200px;
    background: #0f2027; /* fallback for old browsers */
    background: -webkit-linear-gradient(to right, #2c5364, #203a43, #0f2027); /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(to right, #2c5364, #203a43, #0f2027); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  }
}
</style>
