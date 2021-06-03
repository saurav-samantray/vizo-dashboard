<template>
  <q-page class="ub-page">
  <DashboardsHeader></DashboardsHeader> 

  <DashboardContent></DashboardContent> 
  </q-page>
</template>

<script>
import DashboardsHeader from '../components/dashboards/DashboardsHeader'
import DashboardContent from '../components/dashboards/DashboardContent'

export default {
  name: 'Dashboards',
  data() {
    return {
      content: 'Dashboards'
    }
  },
  components: {
    DashboardsHeader,
    DashboardContent
  }
}
</script>
