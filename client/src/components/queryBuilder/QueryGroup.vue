<template>
  <div class="row rounded-borders q-pa-sm q-ma-md qb-query-group">
    <div class="col">
      <div class="row justify-between">
        <div class="col-3">
          <!-- <svg height="210" width="1" style="z-index:2;position:absolute">
            <line x1="0" y1="15" x2="50" y2="2000" style="stroke:rgb(255,0,0);stroke-width:2" />
          </svg> -->
          <canvas :id="group.id + '_canvas'" width="30" height="350" style="border:1px solid #d3d3d3;z-index=2;position:absolute">
            Your browser does not support the HTML5 canvas tag.
          </canvas>
          <div class="q-pl-sm">
            <span v-if="group.rules.length > 1 || group.children.length > 1 || group.rules.length + group.children.length > 1">
              <q-radio keep-color v-model="condition" val="AND" label="AND" color="teal" />
              <q-radio keep-color v-model="condition" val="OR" label="OR" color="orange" />
            </span>
            <q-checkbox keep-color v-model="not" val="NOT" label="NOT" color="red" />
          </div>
        </div>
        <div class="col-3 query-group-btn">
          <q-btn :dense="true" icon="mdi-plus" class="create-chart" label="Rule" @click="addRule()" />
          <q-btn :dense="true" icon="mdi-plus" class="create-chart q-ml-sm" label="Group" @click="addGroup('child')" />
        </div>
      </div>
      <div
        @mouseover="rule.showDelete = true"
        @mouseleave="rule.showDelete = false"
        class="row qb-query-rule q-pa-sm q-ma-md rounded-borders"
        v-for="rule in group.rules"
        v-bind:key="rule.id"
        :ref="'rule_'+rule.id"
      >
        <div class="col-3">
          <q-select square outlined :options="rule.fieldOptions" :dense="dense" v-model="rule.selectedField" label="Field" />
        </div>
        <div class="col-3 q-ml-md">
          <q-select square outlined :options="rule.operatorOptions" :dense="dense" v-model="rule.selectedOperator" label="Operator" />
        </div>
        <div class="col-3 q-ml-md">
          <q-input v-model="rule.value" :dense="dense" />
        </div>
        <div class="col-1 q-ml-xl" style="min-height: 42px;">
          <!-- <q-btn icon="mdi-trash-can" @click="deleteRule(rule.id)" /> -->
          <q-btn v-show="rule.showDelete" round color="red" icon="mdi-trash-can" @click="deleteRule(rule.id)" />
        </div>
      </div>
      <transition-group name="fade" tag="div">
        <div class="row" v-for="group in group.children" v-bind:key="group.id">
          <div class="col-11">
            <query-group :group="group"></query-group>
          </div>
          <div class="col-auto">
            <q-btn style="margin-top: 150%" round color="red" icon="mdi-trash-can" @click="deleteGroup(group.id)" />
          </div>
        </div>
      </transition-group>
    </div>
  </div>
</template>

<script>
//import QueryRule from './QueryRule'
import SQL_OPERATORS from '../../constants.js';

export default {
  name: 'QueryGroup',
  props: ['group', 'name'],
  mixins: [],
  mounted() {
    const ruleId = this.addRule();
    //this.canvasPaint(0,1);

  },
  watch: {},
  methods: {
    addRule() {
      console.log('Add rule');
      const id = this.randomString();
      this.group.rules.push({
        id: id,
        name: 'some rule',
        fieldOptions: ['1', '2', '3'],
        selectedField: '',
        operatorOptions: ['==', '!=', 'LIKE', 'NOT LIKE', 'STARTS WITH', 'ENDS WITH', 'IS EMPTY', 'IS NOT EMPTY'],
        selectedOperator: '',
        value: '',
        showDelete: false
      });
      this.canvasPaint(74,this.group.rules.length);
      this.$nextTick(() => {
        console.log(id);
        var rule = this.$refs['rule_'+id][0].getBoundingClientRect();
        console.log(rule);
      });
      return id;

    },
    activeDeleteRule() {
      this.deleteRuleActive = !this.deleteRuleActive;
    },
    deleteRule(id) {
      this.group.rules = this.group.rules.filter(function(obj) {
        return obj.id !== id;
      });
      this.canvasPaint(74,this.group.rules.length);
    },
    deleteGroup(id) {
      this.group.children = this.group.children.filter(function(obj) {
        return obj.id !== id;
      });
    },
    addGroup(name) {
      console.log('Add rule');
      this.group.children.push({ id: this.randomString(), rules: [], children: [] });
    },
    randomString() {
      return Math.round(Math.pow(36, 10 + 1) - Math.random() * Math.pow(36, 10))
        .toString(36)
        .slice(1);
    },
    canvasPaint(offset,count) {
      console.log('Painting Canvas');
      var c = document.getElementById(this.group.id + '_canvas');
      var ctx = c.getContext('2d');
      ctx.clearRect(0, 0, c.width, c.height);
      ctx.beginPath();
      ctx.moveTo(18, 20);
      ctx.lineTo(12, 20);
      ctx.lineTo(12, 80);
      ctx.lineTo(18, 80);
      for(var i = 1 ; i < count ; i++){
        ctx.lineTo(12, 80 + (offset * (i - 1) ));
        ctx.lineTo(12, 80 + (offset * i) );
        ctx.lineTo(18, 80 + (offset * i) );  
      }
      ctx.stroke();
    }
  },
  data() {
    return {
      condition: 'AND',
      not: false,
      dense: true,
      deleteRuleActive: false
    };
  }
};
</script>

<style lang="scss">
.qb-query-group {
  border: 1px solid orangered;
  background-color: #fff1e8;
}
.qb-query-rule {
  // border: 1px solid green;
  background-color: #e8ffee;
}
.query-group-btn {
  justify-content: end;
}
</style>
