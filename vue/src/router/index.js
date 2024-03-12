import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import MenuView from '../views/MenuView.vue';
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import OrderView from '../views/OrderView.vue';
import UserView from '../views/UserView.vue';
import UserPizzaView from '../views/UserPizzasView.vue';
import UserToppingsView from '../views/UserToppingsView.vue';
import OrderConfirmationView from '../views/OrderConfirmationView.vue';
import UserOrdersView from '../views/UserOrdersView.vue';
import UserOrderDetailView from '../views/UserOrderDetailView.vue';
import ToppingsView from '../views/ToppingsView.vue';


/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/menu',
    name: 'menu',
    component: MenuView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/user',
    name: 'user-home',
    component: UserView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/user/pizzas',
    name: 'user-pizzas',
    component: UserPizzaView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/user/toppings',
    name: 'user-toppings',
    component: UserToppingsView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/user/orders',
    name: 'user-orders',
    component: UserOrdersView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/user/orders/:orderId/details',
    name: 'user-orders-details',
    component: UserOrderDetailView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/start-order',
    name: 'start-order',
    component: OrderView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/orders/create',
    name: 'confirm-order',
    component: OrderConfirmationView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/logout',
    name: 'logout',
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
   path: "/toppings/:pizzaId?",
   name: "topping",
   component: ToppingsView,
   meta: {
     requiresAuth: false
   }
  }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};

  }
});

export default router;
