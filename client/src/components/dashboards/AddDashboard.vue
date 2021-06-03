<template>
  <q-dialog v-model="addDashboard" persistent>
    <q-card style="min-width: 350px">
      <q-card-section align="center">
        <div class="text-h6">Add Dashboard</div>
      </q-card-section>
      <q-card-section align="center">
        <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
          <q-input
            filled
            v-model="name"
            label="Dashboard Name *"
            hint="Visible Dashboard Name"
            lazy-rules
            :rules="[val => (val && val.length > 0) || 'Dashboard Name is Mandatory']"
          />
        </q-form>
      </q-card-section>

      <q-card-actions align="right" class="text-primary">
        <q-btn flat label="Cancel" @click="close()" />
        <q-btn flat label="Add Dashboard" @click="onSubmit()" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script>
import { DashboardService } from '../../services/dashboard.service';
import { notifications } from '../../mixins/notifications';

export default {
  name: 'AddDatasource',
  props: ['addDashboard', 'fetchAllDashboards'],
  mixins: [notifications],
  methods: {
    close() {
      this.$emit('update:addDashboard', false);
      //this.fetchDatasources();
    },
    onSubmit() {
      let dashboard = {
        name: this.name
      };
      this.save(dashboard);
      //this.fetchDatasources();
      this.close();
    },
    onReset() {
      console.log('Reset form');
    },
    async save(dashboard) {
      await DashboardService.save(dashboard);
      this.positive(`Successfully Created Dashboard: ${this.name}`);
      this.fetchAllDashboards();
    }
  },
  data() {
    return {
      name: ''
    };
  }
};
</script>
