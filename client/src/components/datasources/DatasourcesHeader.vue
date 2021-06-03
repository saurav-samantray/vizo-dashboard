<template>
  <div>
    <q-toolbar>
      <q-toolbar-title>
        <div class="text-h5">Datasources</div>
      </q-toolbar-title>
      <q-btn flat round dense icon="refresh" class="q-mr-md text-h6" @click="fetchDatasources"></q-btn>
      <q-btn flat round dense icon="add" class="q-mr-xs text-h6" @click="toggle"></q-btn>
    </q-toolbar>
    <AddDatasource :addDS.sync="addDS" :fetchDatasources="fetchDatasources"></AddDatasource>
  </div>
</template>

<script>
import AddDatasource from './AddDatasource';

export default {
  name: 'DatasourcesHeader',
  methods: {
    toggle() {
      this.addDS = true;
    },
    refreshDatasources() {
      this.fetchDatasources();
    }
  },
  props: ['fetchDatasources'],
  data() {
    return {
      addDS: false,
      availableDatasources: [
        { name: 'searchMetrics', type: 'mongoDB', connected: true },
        { name: 'cartMetrics', type: 'MySQL', connected: false }
      ]
    };
  },
  components: {
    AddDatasource
  }
};
</script>

<style scoped>
.dashboard-select {
  width: 100px;
}
</style>
