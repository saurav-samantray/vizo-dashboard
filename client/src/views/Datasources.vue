<template>
  <q-page class="ub-page">
    <DatasourcesHeader :fetchDatasources="fetchDatasources" />
    <DatasourcesContent :datasources="datasources" :fetchDatasources="fetchDatasources" />
  </q-page>
</template>

<script>
import DatasourcesHeader from '../components/datasources/DatasourcesHeader';
import DatasourcesContent from '../components/datasources/DatasourcesContent';
import { DatasourcesService } from '../services/datasources.service';

export default {
  name: 'Datasources',
  data() {
    return {
      content: 'Datasources',
      refresh: false,
      datasources: []
    };
  },
  watch: {
    refresh: function(newValue, oldValue) {
      console.log('watching refresh');
      this.fetchDatasources();
    }
  },
  mounted() {
    this.fetchDatasources();
  },
  methods: {
    async fetchDatasources() {
      let data = await DatasourcesService.getDatasources();
      this.datasources = data;
    }
  },
  components: {
    DatasourcesHeader,
    DatasourcesContent
  }
};
</script>
