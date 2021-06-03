<template>
  <div>
    <q-toolbar>
      <!-- <span class="text-h5 q-mr-md">Dashboard</span> -->
      <q-separator dark vertical inset />

      <q-select v-model="selectedDashboard" class="dashboard-select text-h5" :options="availableDashboards" label="Select A Dashboard" />

      <q-space />

      <q-btn flat round dense icon="add" class="q-mr-xs text-h6" @click="openAdd" />
    </q-toolbar>
    <AddDashboard :addDashboard.sync="addDashboard" :fetchAllDashboards="fetchAllDashboards"></AddDashboard>
  </div>
</template>

<script>
import AddDashboard from './AddDashboard';
import DashboardService from '../../services/dashboard.service';
export default {
  name: 'DashboardsHeader',
  data() {
    return {
      selectedDashboard: '',
      availableDashboards: ['Cart', 'API', 'Search'],
      addDashboard: false
    };
  },
  components: {
    AddDashboard
  },
  mounted() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.fetchAllDashboards();
    },
    async fetchAllDashboards() {
      console.log('fetchAllDashboards');
      let options = [];
      let data = await DashboardService.getAll();
      data.forEach(function(obj) {
        options.push({ label: obj.name, value: obj.id });
      });
      this.availableDashboards = options;
    },
    openAdd() {
      this.addDashboard = true;
    }
  }
};
</script>

<style scoped>
.dashboard-select {
  width: 250px;
}
</style>
