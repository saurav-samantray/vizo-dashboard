<template>
  <div class="full-height" v-if="previewChartData.name || generatingChartPreview">
    <ChartWrapper v-bind:chart="previewChartData" />
    <q-inner-loading :showing="generatingChartPreview">
      <q-spinner-facebook size="50px" color="primary" />
    </q-inner-loading>
  </div>
  <div v-else class="row justify-center full-height full-width">
    <div class="col">
      <div class="text-h6">Nothing to show here</div>
    </div>
  </div>
</template>

<script>
import ChartWrapper from './ChartWrapper';
import { mapGetters } from 'vuex';

export default {
  name: 'ChartPreview',
  data() {
    return {
      menuOptions: [{ title: 'Resize' }, { title: 'Delete' }]
    };
  },
  components: {
    ChartWrapper
  },
  computed: {
    ...mapGetters(['previewChartData', 'generatingChartPreview'])
  }
};
</script>
