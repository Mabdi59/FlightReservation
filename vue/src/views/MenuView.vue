<template>
  <div class="menu-view">
    <h1>Welcome to Our Menu</h1>

    <h2>Specialty Pizzas</h2>
    <div class="pizza-list">
      <MenuPizzaCard v-for="pizza in pizzas" :key="pizza.pizza_id" :pizza="pizza"/>
    </div>
    <MenuBYOPizza></MenuBYOPizza>

    <button v-on:click="goToOrderPage()" class="order-button">Place Your Order</button>
  </div>
</template>

<script>
import MenuPizzaCard from '../components/MenuPizzaCard.vue';
import PizzaService from '../services/PizzaService';
import ToppingService from '../services/ToppingService.js';
import MenuBYOPizza from '../components/MenuBYOPizza.vue';

export default {
  components: {
    MenuPizzaCard,
    MenuBYOPizza
  },
  data() {
    return {
      pizzas: [],
      toppings: []
    };
  },
  created() {
    this.loadPizzas();
    this.loadToppings();
  },
  methods: {
    async loadPizzas() {
      try {
        const response = await PizzaService.getAllSpecialtyPizzas();
        this.pizzas = response.data;
      } catch (error) {
        console.error('Error fetching pizzas:', error);
      }
    },
    async loadToppings() {
      try {
        const response = await ToppingService.getAllToppings();
        this.toppings = response.data;
      } catch (error) {
        console.error('Error fetching toppings:', error);
      }
    },
    goToOrderPage() {
      this.$router.push({ name: 'start-order' });
      window.scrollTo(0,0);
    }
  }
};
</script>

<style scoped>
@font-face {
    font-family: 'Mandalore Laser Title';
    src: url('../fonts/MandaloreLaserTitle.woff2') format('woff2'),
         url('../fonts/MandaloreLaserTitle.woff') format('woff');
    font-weight: normal;
    font-style: normal;
    font-display: swap;
}
.menu-view {
    text-align: center;
    max-width: 1200px;
    margin: auto;
    padding: 20px;
}

.menu-view h1, .menu-view h2 {
    color: var(--brand-darkred-color);
    font-family: 'Mandalore Laser Title', sans-serif; 
}

.pizza-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 30px;
    margin-top: 30px;
}

.pizza-card {
    background-color: var(--brand-lightred-color);
    color: var(--brand-white-color);
    padding: 10px;
    border-radius: 5px;
    text-align: center;
    flex-basis: calc(33.33% - 20px);
}

.toppings-section {
    margin-top: 40px;
}

.toppings-list {
    list-style-type: none;
    padding: 0;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 10px;
}

.toppings-list li {
    background-color: var(--brand-green-color);
    padding: 5px 15px;
    border-radius: 5px;
    font-size: 1em;
}

.order-button {
    margin-top: 30px;
    padding: 10px 20px;
    font-size: 1.2em;
    color: var(--brand-white-color);
    background-color: var(--brand-green-color);
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease-in-out;
}

.order-button:hover {
    background-color: var(--brand-brown-color);
}

@media (max-width: 600px) {
    .pizza-card {
        flex-basis: 100%; 
    }
}

@media (min-width: 601px) and (max-width: 900px) {
    .pizza-card {
        flex-basis: calc(50% - 30px); 
    }
}

@media (min-width: 901px) {
    .pizza-card {
        flex-basis: calc(33.33% - 30px); 
    }
}
</style>
