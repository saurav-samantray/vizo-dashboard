<template>
  <q-page class="ub-page">
    <div class="row justify-between">
      <div class="col-md-3">
        <AddChart :availableDashboards="availableDashboards" />
      </div>
      <div class="col-md-8 q-pl-md">
        <div class="row justify-between">
          <div class="col-md-12">
            <ChartPreviewHeader :availableCharts="availableCharts" />
          </div>
        </div>
        <div class="row justify-between">
          <div class="col-md-12">
            <ChartPreview />
          </div>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
import AddChart from '../components/charts/AddChart';
import ChartPreview from '../components/charts/ChartPreview';
import ChartPreviewHeader from '../components/charts/ChartPreviewHeader';
import ChartService from '../services/chart.service';
import DashboardService from '../services/dashboard.service';
export default {
  name: 'Charts',
  data() {
    return {
      content: 'Charts',
      availableCharts: [],
      availableDashboards: []
    };
  },
  mounted() {
    this.initialize();
  },
  methods: {
    async initialize() {
      let options = [];
      let data = await ChartService.getCharts();
      data.forEach(function(obj) {
        options.push({ label: obj.name, value: obj.id });
      });
      this.availableCharts = options;

      options = [];
      data = await DashboardService.getAll();
      data.forEach(function(obj) {
        options.push({ label: obj.name, value: obj.id });
      });
      this.availableDashboards = options;
    }
  },
  components: {
    AddChart,
    ChartPreview,
    ChartPreviewHeader
  }
};
</script>
