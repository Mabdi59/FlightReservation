<template>

  <div id="pizza-menu">
    <section id="specialty-pizzas">
       
      <h2 class="menu-section-title">Specialty Pizzas</h2>
      <div class="pizza-card-parent">
        <div class="pizza-card" v-for="pizza in availablePizzas" :key="pizza.pizza_id">
          <h2>{{ pizza.pizza_name }}</h2>
          <p>{{ pizza.note }}</p>
          <label>Qty: </label><input class="input-qty" type="number" v-model="pizza.quantity">
          <div class="pizza-details">
            <span class="pizza-price">${{ pizza.pizza_cost.toFixed(2) }}</span>
            
            <button class="add-to-cart-button show-toppings" v-bind:="currentPizzaId"
           @click="changeCurrentPizzaId(pizza.pizza_id); changeStoredCurrentPizza(pizza.pizza_id); addPizzaToStore(pizza)" >Add to Cart</button>
          </div>
        </div>
        
      </div>
    </section>
  </div>
</template>
<script>
import PizzaService from '../services/PizzaService.js';
import ToppingService from '../services/ToppingService.js';
export default {
  components: {},
  data() {
    return {
      availablePizzas: [],
      selectedPizzas: [],
      currentPizzaId: 0,
    };
  },
  methods: {
     changeCurrentPizzaId(pizza_id){
      this.currentPizzaId = pizza_id;
    },
    changeStoredCurrentPizza(currentPizzaId){
       this.$store.commit('CHANGE_CURRENT_PIZZA_ID', currentPizzaId);
    },
    addPizzaToStore(pizza){
      let toppingIdArray = [];
      ToppingService.getToppingsByPizzaId(pizza.pizza_id).then((response)=>{
        let toppingsOnPizza = response.data;
        toppingsOnPizza.forEach((topping)=>{
          toppingIdArray.unshift(topping.topping_id);
        });
        pizza.toppings = toppingIdArray;

        this.$store.commit('ADD_PIZZA_TO_STORE', {...pizza});
        pizza.quantity = null;

      });
    }

  },
   created() {
     PizzaService.getAvailableSpecialtyPizzas()
        .then((response) => {
          this.availablePizzas = response.data
        });
  },
  computed: {
    getPizza() {
      return this.$store.state.currentPizzaId;
    }
  }
};
</script>
<style scoped>
@import url('https://fonts.cdnfonts.com/css/cooper-hewitt-book');
@font-face {
    font-family: 'Mandalore Laser Title';
    src: url('../fonts/MandaloreLaserTitle.woff2') format('woff2'),
        url('../fonts/MandaloreLaserTitle.woff') format('woff');
    font-weight: normal;
    font-style: normal;
    font-display: swap;
}
*{
    font-family: 'Cooper Hewitt Book', sans-serif;
}
h1, .menu-section-title, h2{
  font-family: 'Mandalore Laser Title';
}
.pizza-menu {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
}

.menu-section-title {
  font-size: 1.8em;
  text-decoration: underline;
  margin-bottom: 15px;
  color: var(--brand-brown-color);
}

.pizza-card-parent {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.pizza-card 
{
  background-color: #e6ee741a;
  border-radius: 8px;
  flex-basis: 300px;
  margin: 10px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease-in-out;
}

.pizza-card:hover {
  transform: scale(1.05);
}

.pizza-card h2 {
  font-size: 1.3em;
  margin-bottom: 10px;
  color: var(--brand-brown-color);
}

.pizza-card p {
  margin-bottom: 15px;
  color: #666;
}

.pizza-details {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.pizza-price {
  font-weight: bold;
  color: #e44c26ec; 
}

.add-to-cart-button {
  background-color: #af844c;
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.2s ease-in-out;
}

.add-to-cart-button:hover {
  background-color: #a08545fb;
}
#specialty-pizzas{
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 10px;
  box-shadow: var(--brand-lightred-color) 0px 0 5px;
  padding: 15px;
}
.input-qty{
  width: 40px;
}

</style>
