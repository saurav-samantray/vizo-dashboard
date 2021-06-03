<template>
  <div>
    <q-toolbar>
      <q-toolbar-title>
        <div class="text-h5">Preview</div>
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
import { mapActions } from 'vuex';
import { GENERATE_CHART_PREVIEW } from '@/store/actions';

export default {
  name: 'ChartPreviewHeader',
  methods: {
    ...mapActions([GENERATE_CHART_PREVIEW]),
    formData() {
      const data = {
        name: this.selectedChart.name,
        field: this.selectedChart.field,
        datasourceId: this.selectedChart.datasourceId
      };
      return data;
    },
    preview(id) {
      console.log('Preview Chart in right hand panel');
      this.GENERATE_CHART_PREVIEW({ payload: id, type: 'saved' });
    }
  },
  props: ['availableCharts'],
  data() {
    return {
      selectedChart: ''
      //savedChartOptions: []
    };
  },
  components: {
    //AddDatasource
  },
  watch: {
    selectedChart: async function(val) {
      this.preview(val.value);
    }
  }
};
</script>

<style scoped>
.dashboard-select {
  width: 100px;
}
</style>
