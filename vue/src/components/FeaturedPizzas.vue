<template>
  <div class="featured-pizzas">
    <h1 class="menu-section-title">Featured Pizzas</h1>
    <div class="pizza-card-parent">
      <div class="pizza-card" v-for="(pizza, index) in featuredPizzas" :key="index">
        <img :src="pizza.imageUrl" alt="Image of pizza" class="pizza-image" />
        <h2>{{ pizza.pizza_name }}</h2>
        <p>{{ pizza.description }}</p>
        <div class="pizza-details">
          <span class="pizza-price">${{ pizza.pizza_cost.toFixed(2) }}</span>
          <button class="add-to-cart-button" @click="addToCart(pizza)">Add to Cart</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PizzaService from '../services/PizzaService.js'; 

export default {
  data() {
    return {
      featuredPizzas: []
    };
  },
  created() {
    this.fetchFeaturedPizzas();
  },
  methods: {
    fetchFeaturedPizzas() {
      PizzaService.getAllSpecialtyPizzas() 
        .then(response => {
          this.featuredPizzas = response.data;
        })
        .catch(error => {
          console.error('Error fetching featured pizzas:', error);
        });
    },
    addToCart(pizza) {
      this.$emit('addToCart', pizza);
    }
  }
};
</script>

<style scoped>
.featured-pizzas {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.menu-section-title {
  font-size: 2em;
  color: #4CAF50;
  margin-bottom: 20px;
}

.pizza-card-parent {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.pizza-card {
  background-color: white;
  border-radius: 10px;
  margin: 15px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease-in-out;
  text-align: center;
  width: 300px; 
}

.pizza-card:hover {
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
  transform: translateY(-5px);
}

.pizza-image {
  width: 100%;
  height: 200px; 
  object-fit: cover;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

.pizza-card h2 {
  font-size: 1.4em;
  margin-top: 10px;
}

.pizza-card p {
  font-size: 1em;
  margin-bottom: 10px;
}

.pizza-details {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.pizza-price {
  font-weight: bold;
  color: #333;
}

.add-to-cart-button {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.2s ease-in-out;
}

.add-to-cart-button:hover {
  background-color: #45A049;
}
</style>
