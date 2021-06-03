<template>
  <div v-if="previewChartData.name || generatingChartPreview">
    <ChartWrapper v-bind:chart="previewChartData" />
  </div>
  <div v-else>
    <q-card>
      <q-card-section>
        <div class="row items-center no-wrap">
          <div class="col">
            <div class="text-h6">Nothing to show here</div>
          </div>
        </div>
      </q-card-section>
      <q-inner-loading :showing="generatingChartPreview">
        <q-spinner-gears size="50px" color="primary" />
      </q-inner-loading>
    </q-card>
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
