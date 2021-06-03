<template>
  <div class="row items-center" style="margin-bottom: 10px">
    <div class="col-md-12 col-sm-12 q-ma-xs">
      <q-table
        title="Datasources"
        :data="datasources"
        class="ub-items-table"
        :columns="columns"
        row-key="name"
        :filter="filter"
        :visible-columns="visibleColumns"
        :loading="loading"
      >
        <template v-slot:top>
          <q-input borderless dense debounce="300" v-model="filter" placeholder="Search">
            <template v-slot:append>
              <q-icon name="search" />
            </template>
          </q-input>

          <q-space />

          <q-select
            v-model="visibleColumns"
            multiple
            outlined
            dense
            options-dense
            :display-value="$q.lang.table.columns"
            emit-value
            map-options
            :options="columns"
            option-value="name"
            options-cover
            style="min-width: 150px"
          />
        </template>
        <template v-slot:body="props">
          <q-tr :props="props">
            <q-td key="name" class="datasource-name" :props="props">
              {{ props.row.name }}
              <q-popup-edit v-model="props.row.name">
                <q-input v-model="props.row.name" dense autofocus counter></q-input>
              </q-popup-edit>
            </q-td>
            <q-td key="connectionDetails" class="connection-details" :props="props">
              {{ props.row.connectionDetails }}
              <q-popup-edit v-model="props.row.connectionDetails">
                <q-input v-model="props.row.connectionDetails" dense autofocus counter></q-input>
              </q-popup-edit>
            </q-td>
            <q-td key="databaseName" class="database-name" :props="props">
              {{ props.row.databaseName }}
              <q-popup-edit v-model="props.row.databaseName">
                <q-input v-model="props.row.databaseName" dense autofocus counter></q-input>
              </q-popup-edit>
            </q-td>
            <q-td key="collection" class="collection" :props="props">
              {{ props.row.collection }}
              <q-popup-edit v-model="props.row.collection">
                <q-input v-model="props.row.collection" dense autofocus counter></q-input>
              </q-popup-edit>
            </q-td>
            <q-td key="type" :props="props">
              {{ props.row.type }}
            </q-td>
            <q-td key="connected" :props="props">
              <span class="dot" v-bind:class="{ connected: props.row.connected }"></span>
              <q-popup-edit v-model="props.row.connected">
                <q-toggle v-model="props.row.connected" label="Connected" />
              </q-popup-edit>
            </q-td>
            <q-td key="actions" :props="props">
              <q-btn class="q-mr-md" icon="mdi-content-save-edit" @click="editItem(props.row)" size="md" flat round>
                <q-tooltip>
                  Save updates
                </q-tooltip>
              </q-btn>
              <q-btn icon="mdi-delete" @click="deleteItem(props.row)" size="md" flat round>
                <q-tooltip>
                  Delete Datasource
                </q-tooltip>
              </q-btn>
            </q-td>
          </q-tr>
        </template>
      </q-table>
    </div>
  </div>
</template>

<script>
import { DatasourcesService } from '../../services/datasources.service';
import { notifications } from '../../mixins/notifications';

export default {
  name: 'DatasourcesContent',
  mounted() {
    //this.initialize()
  },
  props: ['datasources', 'fetchDatasources'],
  mixins: [notifications],
  methods: {
    async deleteItem(row) {
      let name = row.name;
      console.log('deleting: ', row.id);
      await DatasourcesService.delete(row.id);
      this.fetchDatasources();
      this.positive(`Deleted Datasource ${name}`);
    },
    async editItem(row) {
      console.log('updating: ', row);
      await DatasourcesService.update(row);
      this.fetchDatasources();
      //let data = await DatasourcesService.getDatasources();
    }
  },
  data() {
    return {
      loading: false,
      filter: '',
      visibleColumns: ['name', 'connectionDetails', 'databaseName', 'collection', 'type', 'connected', 'actions'],
      columns: [
        {
          name: 'name',
          required: true,
          label: 'Datasource Name',
          align: 'left',
          field: row => row.name,
          format: val => `${val}`,
          sortable: true
        },
        { name: 'connectionDetails', align: 'left', label: 'Connection', field: 'connectionDetails', sortable: true },
        { name: 'databaseName', align: 'left', label: 'Database', field: 'databaseName', sortable: true },
        { name: 'collection', align: 'left', label: 'Collection/Table', field: 'collection', sortable: true },
        { name: 'type', align: 'left', label: 'Type', field: 'type', sortable: true },
        { name: 'connected', align: 'center', label: 'Connected', field: 'connected', sortable: true },
        { name: 'actions', align: 'center', label: 'Actions', field: 'actions' }
      ]
    };
  }
};
</script>
<style lang="scss">
.datasource-name {
  width: 200px;
}
.dot {
  height: 20px;
  width: 20px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  &.connected {
    background-color: green;
  }
}
.ub-items-table {
  & table {
    table-layout: auto;
  }

  /* [sv2] This disables highlighting background of selected rows in q-table. */
  .q-table > tbody > tr.selected > td:after {
    content: none;
  }

  .q-table > thead > tr > th {
    font-size: 1.2em;
  }

  & tbody > tr.selected > td.ub-items-val {
    font-weight: 500;
  }

  /* TODO Consider monospace
  font-family: "Monaco", "Menlo", "Ubuntu Mono", "Consolas", "source-code-pro", "Lucida Console", monospace;
  */
  .ub-items-val {
    &.active {
      color: #1a1aa6;
    }
  }

  .ub-items-data {
    font-weight: 500;
  }

  .ub-items-row:hover > .ub-items-row-ctrl > .ub-items-ctrls {
    display: block;
  }

  /* This enables passing arbitrary color to q-toggle */
  .q-toggle__inner--truthy {
    color: currentColor;
  }
  .q-toggle--dark .q-toggle__inner {
    color: currentColor;
  }
  /* This makes track color more prominent */
  .q-toggle__track {
    opacity: 0.8;
  }
  /* This makes thumb less prominent in dark mode */
  .q-toggle--dark .q-toggle__thumb:after {
    opacity: 0.7;
  }
}
</style>
