// Standard methods for views that use vue-good-table
export const notifications = {
  methods: {
    positive: function(msg) {
      this.$q.notify({
        type: 'positive',
        message: msg
      });
    },
    negative: function(msg) {
      this.$q.notify({
        type: 'negative',
        message: msg
      });
    },
    info: function(msg) {
      this.$q.notify({
        type: 'info',
        message: msg
      });
    }
  }
};
