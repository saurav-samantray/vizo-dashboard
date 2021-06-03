<template>
  <div>
    <q-toolbar>
      <q-toolbar-title>
        <div class="text-h5">Chart</div>
      </q-toolbar-title>
      <q-space />
      <q-select class="col-grow" v-model="selectedChart" :options="availableCharts" label="Available Charts" />
      <!-- <q-separator/>
      <q-btn flat round dense icon="refresh" class="q-mr-md text-h6" @click="fetchDatasources"></q-btn>
      <q-btn flat round dense icon="add" class="q-mr-xs text-h6" @click="toggle"></q-btn> -->
    </q-toolbar>
  </div>
</template>

<script>
//import AddDatasource from './AddDatasource';

export default {
  name: 'ChartPreviewHeader',
  methods: {},
  props: ['availableCharts'],
  data() {
    return {
      selectedChart: ''
      //savedChartOptions: []
    };
  },
  components: {
    //AddDatasource
  }
};
</script>

<style scoped>
.dashboard-select {
  width: 100px;
}
</style>
