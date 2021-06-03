<template>
  <q-card style="min-width: 350px">
    <q-card-section align="center">
      <div class="text-h6">Add Chart</div>
    </q-card-section>
    <q-card-section align="center">
      <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
        <q-select v-model="selectedChartType" :options="chartTypeOptions" label="Datasource" />
        <q-input
          filled
          v-model="name"
          label="Chart Name *"
          hint="Visible Chart Name"
          lazy-rules
          :rules="[val => (val && val.length > 0) || 'Chart Name is Mandatory']"
        />

        <q-select v-model="selectedDataSource" :options="datasourcesOption" label="Datasource" />

        <q-select v-model="selectedDashboard" :options="availableDashboards" label="Dashboard" />

        <div v-if="selectedChartType == 'pie'">
          <q-select v-model="selectedFieldName" :options="fieldOptions" label="Field Name" />
        </div>

        <div v-if="selectedChartType == 'bar'">
          This is bar chart options
        </div>

        <div v-if="selectedChartType == 'line'">
          This is line chart options
        </div>
      </q-form>
    </q-card-section>

    <q-card-actions align="right" class="text-primary">
      <q-btn class="create-chart" label="Preview" @click="preview()" />
      <q-btn class="create-chart" label="Save" @click="onSubmit()" />
    </q-card-actions>
  </q-card>
</template>

<script>
import { DatasourcesService } from '../../services/datasources.service';
import { mapActions } from 'vuex';
import { GENERATE_CHART_PREVIEW } from '@/store/actions';
import { notifications } from '../../mixins/notifications';

export default {
  name: 'AddChart',
  props: ['availableDashboards'],
  mixins: [notifications],
  mounted() {
    this.fetchDatasources();
  },
  watch: {
    selectedDataSource: async function(val) {
      this.refreshField(val);
    }
  },
  methods: {
    ...mapActions([GENERATE_CHART_PREVIEW]),
    formData() {
      const data = {
        name: this.name,
        field: this.selectedFieldName,
        datasourceId: this.selectedDataSource.value
      };
      return data;
    },
    onSubmit() {
      let datasource = {};
      this.save(datasource);
      this.fetchDatasources();
      this.positive(`Successfully Created Datasource: ${this.name}`);
      this.close();
    },
    onReset() {
      console.log('Reset form');
    },
    async save(datasource) {
      await DatasourcesService.save(datasource);
    },
    async refreshField(datasource) {
      this.fieldOptions = await DatasourcesService.getAllKeys(datasource.value);
    },
    async fetchDatasources() {
      let data = await DatasourcesService.getDatasources();
      let options = [];
      data.forEach(function(obj) {
        options.push({ label: obj.name, value: obj.id });
      });
      console.log(options);
      this.datasourcesOption = options;
      //this.datasourcesOption = data;
    },
    preview() {
      console.log('Preview Chart in right hand panel');
      this.GENERATE_CHART_PREVIEW({ payload: this.formData(), type: 'preview' });
    }
  },

  data() {
    return {
      name: '',
      fieldName: '',
      datasourcesOption: [
        { label: 'Clickstream', value: 1 },
        { label: 'Cart Operation', value: 2 }
      ],
      fieldOptions: [],
      chartTypeOptions: ['pie', 'bar', 'line'],
      selectedDataSource: '',
      selectedFieldName: '',
      selectedDashboard: '',
      selectedChartType: ''
    };
  }
};
</script>
