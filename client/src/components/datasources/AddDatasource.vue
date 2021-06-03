<template>
  <q-dialog v-model="addDS" persistent>
    <q-card style="min-width: 350px">
      <q-card-section align="center">
        <div class="text-h6">Add Datasource</div>
      </q-card-section>
      <q-card-section align="center">
        <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
          <q-input
            filled
            v-model="name"
            label="Datasource Name *"
            hint="Visible Datasource Name"
            lazy-rules
            :rules="[val => (val && val.length > 0) || 'Datasource Name is Mandatory']"
          />

          <q-input
            filled
            v-model="connection"
            label="Connection Details *"
            hint="To be used to connect to Datasource"
            lazy-rules
            :rules="[val => (val && val.length > 0) || 'Connection Detail is Mandatory']"
          />

          <q-input
            filled
            v-model="databaseName"
            label="Database name *"
            hint="Database to be connected"
            lazy-rules
            :rules="[val => (val && val.length > 0) || 'Database Name is Mandatory']"
          />

          <q-input
            filled
            v-model="collection"
            label="Collection/Table name *"
            hint="Collection/Table name"
            lazy-rules
            :rules="[val => (val && val.length > 0) || 'Collection/Table is Mandatory']"
          />

          <q-toggle v-model="active" label="Active" />

          <q-select v-model="type" :options="datasourceTypes" label="Type" />
        </q-form>
      </q-card-section>

      <q-card-actions align="right" class="text-primary">
        <q-btn flat label="Cancel" @click="close()" />
        <q-btn flat label="Add Datasource" @click="onSubmit()" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script>
import { DatasourcesService } from '../../services/datasources.service';
import { notifications } from '../../mixins/notifications';

export default {
  name: 'AddDatasource',
  props: ['addDS', 'fetchDatasources'],
  mixins: [notifications],
  methods: {
    close() {
      this.$emit('update:addDS', false);
      this.fetchDatasources();
    },
    onSubmit() {
      let datasource = {
        name: this.name,
        connectionDetails: this.connection,
        databaseName: this.databaseName,
        collection: this.collection,
        type: this.type,
        active: this.active
      };
      this.save(datasource);
      this.fetchDatasources();
      this.positive(`Successfully Created Datasource: ${this.name}`);
      this.close();
    },
    onReset() {
      console.log('Reset form');
    },
    async save(datasource) {
      await DatasourcesService.save(datasource);
    }
  },
  data() {
    return {
      name: '',
      connection: 'mongodb://localhost:27017/',
      type: 'MONGODB',
      databaseName: 'vizo_sample_db',
      collection: 'click',
      active: true,
      datasourceTypes: ['MONGODB', 'MYSQL', 'POSTGRESQL']
    };
  }
};
</script>
