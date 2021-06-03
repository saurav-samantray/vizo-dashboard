<template>
  <div>
    <div class="row items-center">
      <div class="col-6">
        <div class="text-h6">{{ chart.name }}</div>
      </div>
      <div class="col-auto justify-right">
        <q-btn color="grey-7" round flat icon="more_vert">
          <q-menu cover auto-close>
            <q-list>
              <q-item clickable>
                <q-item-section>Remove Chart</q-item-section>
              </q-item>
              <q-item clickable>
                <q-item-section>Send Feedback</q-item-section>
              </q-item>
              <q-item clickable>
                <q-item-section>Share</q-item-section>
              </q-item>
            </q-list>
          </q-menu>
        </q-btn>
      </div>
    </div>

    <div class="row items-center">
      <div class="col-12">
        <component :is="chart.type" :option="chart.option"></component>
      </div>
    </div>
  </div>
</template>

<script>
import Pie from '../charts/Pie';

export default {
  name: 'ChartWrapper',
  data() {
    return {
      menuOptions: [{ title: 'Resize' }, { title: 'Delete' }]
    };
  },
  props: ['chart'],
  components: {
    Pie
  }
};
</script>
