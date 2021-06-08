<template>
  <q-layout>
    <q-page-container>
      <q-page class="ub-page"> 
        <div class="row">
          <div class="col">
            <div class="text-h4 text-center">Query Builder</div>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <query-group :name="groupName" :group="group"></query-group>
          </div>
        </div>
        <div class="row justify-end">
          <div class="col-2">
            <q-btn color="primary" unelevated @click="generate">generate</q-btn>
          </div>
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
import QueryGroup from '../components/queryBuilder/QueryGroup';

export default {
  name: 'query-builder',
  components: { QueryGroup },
  data() {
    return {
      name: 'QueryBuilder',
      groupName: ' Primary Group',
      group: { rules: [], children: [] }
    };
  },
  methods: {
    generate(){
      console.log(this.group);
    }
  },
  computed: {},
  mounted() {
    this.$q.dark.set(this.dark);
  },
  watch: {
    group: function() {
      console.log(this.group);
    }
  }
};
</script>

<style lang="scss">
.ub-qb-card {
  width: 100%;
  max-width: 800px;
  .ub-login-hdr {
    height: 200px;
    background: #0f2027; /* fallback for old browsers */
    background: -webkit-linear-gradient(to right, #2c5364, #203a43, #0f2027); /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(to right, #2c5364, #203a43, #0f2027); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  }
}
</style>
