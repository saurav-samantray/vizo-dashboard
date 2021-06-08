import Vue from 'vue';
import Router from 'vue-router';
import MainLayout from './layouts/mainlayout.vue';
import Home from './views/Home.vue';
import About from './views/About.vue';
import QueryBuilder from './views/QueryBuilder.vue';
import DashblocksShowcase from './views/DashblocksShowcase.vue';
import Dashboards from './views/Dashboards.vue';
import Charts from './views/Charts.vue';
import Datasources from './views/Datasources.vue';
import Typography from './views/typography.vue';
import MaterialIcons from './views/materialicons.vue';
import ChartJsShowcase from './views/ChartJsShowcase.vue';
import Dygraphs from './views/Dygraphs.vue';
import Mdi from './views/mdi.vue';
import Tables from './views/tables.vue';
import Forms from './views/forms.vue';
import Observability from './views/observability.vue';
import Summary from './views/summary.vue';
import Requests from './views/requests.vue';
import VGT from './views/vuegoodtable.vue';
import QuasarTable from './views/quasartable.vue';
import APIOp from './views/apiop.vue';
import Errors from './views/errors.vue';
import Timeline from './views/timeline.vue';

import Login from './pages/login.vue';
import Register from './pages/register.vue';

import store from './store/store.js';
import { TokenService } from './services/storage.service';

Vue.use(Router);

const ifNotAuthenticated = (to, from, next) => {
  if (!store.getters.loggedIn) {
    next();
    return;
  }
  next(false);
};

const router = new Router({
  routes: [
    {
      path: '/',
      component: MainLayout,
      meta: {
        public: false
      },
      children: [
        {
          path: '',
          name: 'showcase',
          component: DashblocksShowcase
        },
        {
          path: '/dashboards',
          name: 'dashboards',
          component: Dashboards
        },
        {
          path: '/datasources',
          name: 'datasources',
          component: Datasources
        },
        {
          path: '/charts',
          name: 'charts',
          component: Charts
        },
        {
          path: '/chartjs',
          name: 'chartjs',
          component: ChartJsShowcase
        },
        {
          path: '/dygraphs',
          name: 'dygraphs',
          component: Dygraphs
        },
        {
          path: '/typography',
          name: 'typography',
          component: Typography
        },
        {
          path: '/mdi',
          name: 'mdi',
          component: Mdi
        },
        {
          path: '/icons',
          name: 'icons',
          component: MaterialIcons
        },
        {
          path: '/tables',
          name: 'tables',
          component: Tables
        },
        {
          path: '/forms',
          name: 'forms',
          component: Forms
        },
        {
          path: '/observability',
          name: 'observability',
          component: Observability
        },
        {
          path: '/summary',
          name: 'summary',
          component: Summary
        },
        {
          path: '/requests',
          name: 'requests',
          component: Requests
        },
        {
          path: '/vgt',
          name: 'vgt',
          component: VGT
        },
        {
          path: '/apiop',
          name: 'apiop',
          component: APIOp
        },
        {
          path: '/errors',
          name: 'errors',
          component: Errors
        },
        {
          path: '/quasartable',
          name: 'quasartable',
          component: QuasarTable
        },
        {
          path: '/timeline',
          name: 'timeline',
          component: Timeline
        }
      ]
    },
    {
      path: '/login',
      component: Login,
      meta: {
        public: true
      },
      beforeEnter: ifNotAuthenticated
    },
    {
      path: '/register',
      component: Register,
      meta: {
        public: true
      },
      beforeEnter: ifNotAuthenticated
    },
    {
      path: '/qb',
      component: QueryBuilder,
      meta: {
        public: true
      }
    },
  ]
});

router.beforeEach((to, from, next) => {
  const isPublic = to.matched.some(record => record.meta.public);
  const loggedIn = store.getters.loggedIn;

  if (!isPublic && !loggedIn) {
    console.log('Redirect to login');
    return next({
      path: '/login',
      query: { redirect: to.fullPath }
    });
  }

  next();
});

export default router;
