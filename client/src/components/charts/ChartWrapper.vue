<template>
  <q-card>
    <q-card-section>
      <div class="row items-center no-wrap">
        <div class="col">
          <div class="text-h6">{{ chart.name }}</div>
        </div>

        <div class="col-auto">
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
    </q-card-section>

    <q-card-section>
      <component :is="chart.name" :option="chart.option"></component>
    </q-card-section>

    <q-separator />
  </q-card>
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
