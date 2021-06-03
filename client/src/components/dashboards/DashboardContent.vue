<template>
  <div class="row items-center" style="margin-bottom: 10px;">
    <div class="col-md-5 col-sm-12 q-ma-xs" v-for="chart in charts" v-bind:key="chart.id">
      <ChartWrapper :chart="chart"></ChartWrapper>
    </div>
  </div>
</template>

<script>
import ChartWrapper from '../charts/ChartWrapper';

export default {
  name: 'DashboardContent',
  data() {
    return {
      menuOptions: [{ title: 'Resize' }, { title: 'Delete' }],
      charts: [
        {
          id: 'Pie',
          name: 'Pie',
          option: {
            tooltip: {
              trigger: 'item',
              formatter: '{a} <br/>{b} : {c} ({d}%)'
            },
            series: [
              {
                name: 'Vizo Pie Chart',
                type: 'pie',
                radius: '70%',
                center: ['50%', '60%'],
                data: [
                  {
                    name: 'AddToCart',
                    value: 4
                  },
                  {
                    name: 'View',
                    value: 3
                  },
                  {
                    name: 'PlaceOrder',
                    value: 2
                  }
                ]
              }
            ]
          }
        },
        {
          id: 'pie2',
          name: 'pie',
          option: {
            tooltip: {
              trigger: 'item',
              formatter: '{a} <br/>{b} : {c} ({d}%)'
            },
            series: [
              {
                name: 'Traffic Sources',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                  { value: 335, name: 'Direct2' },
                  { value: 310, name: 'Email2' },
                  { value: 2344, name: 'Ad Networks2' },
                  { value: 1354, name: 'Video Ads2' },
                  { value: 154, name: 'Search Engines2' }
                ],
                emphasis: {
                  itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
                }
              }
            ]
          }
        }
      ]
    };
  },
  components: {
    ChartWrapper
  }
};
</script>
