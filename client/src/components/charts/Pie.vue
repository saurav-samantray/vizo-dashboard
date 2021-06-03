<template>
  <v-chart class="chart" :option="option" />
</template>

<script>
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { PieChart } from 'echarts/charts';
import { TitleComponent, TooltipComponent, LegendComponent } from 'echarts/components';
import VChart, { THEME_KEY } from 'vue-echarts';

use([CanvasRenderer, PieChart, TitleComponent, TooltipComponent, LegendComponent]);

export default {
  name: 'Pie',
  components: {
    VChart
  },
  props: ['option'],
  provide: {
    [THEME_KEY]: 'light'
  }
};
</script>

<style scoped>
.chart {
  height: 200px;
}
</style>
