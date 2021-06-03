<template>
  <q-toolbar>
    <span class="text-h5 q-mr-md">Dashboard</span>
    <q-separator dark vertical inset />
    
    <q-select v-model="selectedDashboard" class="dashboard-select text-h5" :options="availableDashboards" />

    <q-space />

    <q-btn flat round dense icon="add" class="q-mr-xs text-h6"/>
    
  </q-toolbar> 
</template>

<script>

export default {
  name: "DashboardsHeader",
  data() {
    return {
      selectedDashboard: 'Cart',
      availableDashboards: ['Cart','API','Search']
    }
  }
};
</script>

<style scoped>
.dashboard-select {
  width: 100px;
}
</style>
