<template>
    <div class="menu-pizza-card">
      <img :src="geturl(pizza.imageUrl)" alt="Pizza image" class="pizza-image"/>
      <div class="pizza-info">
        <h3 class="pizza-name">{{ pizza.pizza_name }}</h3>
        <p class="pizza-desc">{{ pizza.description || pizza.note }}</p>
        <p class="pizza-price">$ {{ pizza.pizza_cost }}</p>
      </div>
    </div>
  </template>
  
  <script>
  import PizzaService from '../services/PizzaService';
  
  export default {
    data() {
      return {
        availablePizzas: []
      };
    },
    methods: {
      geturl(name) {
        return new URL("../images/" + name, import.meta.url).href;
      }
    },
    created() {
      PizzaService.getAvailableSpecialtyPizzas()
        .then(response => {
          this.availablePizzas = response.data;
        });
    },
    props: ['pizza']
  };
  </script>
  
  <style scoped>
  .menu-pizza-card {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 15px; 
    border-radius: 15px;
    background-color: var(--brand-lightred-color);
    color: var(--brand-white-color);
    text-align: center;
    width: 100%; 
    max-width: 200px;
}
  
  .pizza-image {
    width: 100%;
    height: auto;
    border-radius: 10px;
    margin-bottom: 10px;
  }
  
  .pizza-info {
    width: 100%; 
    padding: 0 10px; 
}

.pizza-name {
    font-size: 1.5em;
    margin: 0; 
}

.pizza-desc {
    margin: 10px 0; 
    padding: 5px;
}
  
  .pizza-price {
    font-size: 1.2em;
    font-weight: bold;
    margin: 0;
  }
  </style>
  