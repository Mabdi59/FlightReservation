<template>
  <form v-on:submit.prevent="submitToppingsForm">
    <div class="topping-card">


      <h1>{{ isNoPathVariable ? 'Create Your Own Pizza': pizza.pizza_name}}</h1>

      <h2 class="select-header">{{ isNoPathVariable ? 'Select ': 'Customize ' }}Toppings:</h2>

      <br />
      <span class="pizza-size"> Pizza Size: <span v-if="!isNoPathVariable" class="uppercase">{{ pizza.pizza_size }}</span></span>
      <select class="value" v-if="isNoPathVariable" v-model="pizza.pizza_size">
          <option value="small">Small</option>
          <option value="medium">Medium</option>
          <option value="large">Large</option>
      </select>
      <br />
      <br />
      <div class="choose-sauce">
      <span class="sauce"> Choose Your Sauce: </span>
      <div
        class="toppings sauce"
        v-for="topping in sauces"
        :key="topping.topping_id"
      >
        <label :for="topping.topping_id" class="sauce-option">
          {{ topping.topping_name }}
        </label>
        
          <input type="radio"
            :id="topping.topping_name"
            v-bind:checked="topping.isOnPizza"
            v-on:change="changeSauce(topping.topping_id)"
            name="sauceToppings"
          /> 
      </div>
      </div>
      <br />
       <div class="choose-crust">
      <span> Choose Your Crust: </span>
      <div
        class="toppings crust"
        v-for="topping in crust"
        v-bind:key="topping.type"
      >
        <label for="crust-options" id="crust">
          {{ topping.topping_name }}
        </label>
       
          <input type="radio"
            :id="topping.topping_name"
            v-bind:checked="topping.isOnPizza"
            v-on:change="changeCrust(topping.topping_id)"
            name="crustOptions"
          />

      </div>
       </div>

      <br />
      <div class="choose-meats">
      <span> Choose Your Meats: </span>
      <div
        class="toppings meat"
        v-for="topping in meatToppings"
        v-bind:key="topping.type"
      >
        <label for="{{topping.topping_name}}">
          {{ topping.topping_name }}
        </label>

        <input
          type="checkbox"
          id="{{topping.topping_name}}"
          v-model="topping.isOnPizza"
          :disabled="isMaxToppingsMet && !topping.isOnPizza"
         
        />
      </div>
      </div>
      <br />
      <div class="choose-cheese">
      <span> Choose Your Cheese: </span>
      <div
        class="toppings cheese"
        v-for="topping in cheeseToppings"
        v-bind:key="topping.type"
      >
        <label for="{{topping.topping_id}}">
          {{ topping.topping_name }}
        </label>

        <input
          type="checkbox"
          id="{{topping.topping_name}}"
          v-model="topping.isOnPizza"
          :value="topping.topping_id"
          :disabled="isMaxToppingsMet && !topping.isOnPizza"
        />
      </div>
      </div>
      <br />
       <div class="choose-veggies">
      <span> Choose Your Veggies: </span>
      <div
        class="toppings veggies"
        v-for="topping in veggieToppings"
        v-bind:key="topping.type"
      >
        <label for="{{topping.topping_name}}">
          {{ topping.topping_name }}
        </label>

        <input
          type="checkbox"
          id="{{topping.topping_name}}"
          v-model="topping.isOnPizza"
          :value="topping.topping_id"
          :disabled="isMaxToppingsMet && !topping.isOnPizza"
        />
      </div>
       </div>

      <br />
       <div class="choose-fruit">
      <span> Choose Your Fruit: </span>
      <div
        class="toppings fruit"
        v-for="topping in fruitToppings"
        v-bind:key="topping.type"
      >
        <label for="{{topping.topping_name}}">
          {{ topping.topping_name }}
        </label>

        <input
          type="checkbox"
          id="{{topping.topping_id}}"
          v-model="topping.isOnPizza"
          :value="topping.topping_id"
          :disabled="isMaxToppingsMet && !topping.isOnPizza"
        />
      </div>
       </div>

      <div class="price-quantity">
      <label>Qty: </label>
      <input type="number" v-model="pizza.quantity" min="0" max="10">

      <h4>Price: ${{ totalCost }}</h4>
      </div>
     <br /><br />
      <div class="buttons">
        <button id="back" v-on:click="changePizzaId">Go Back</button>
        <input
          id="submit"
          value="Add to Cart"
          type="submit"
           /> 
      </div>
    </div>
    
  </form>
</template>

<script>
import ToppingService from "../services/ToppingService.js";
import PizzaService from "../services/PizzaService.js";

export default {
  data() {
    return {
      allToppings: [],
      includedToppings: [],
      currentToppings: [],
      pizza:{},
      isNoPathVariable: false,
      costBySize:[8,10,12],
      sizeArray:["small","medium","large"]
    
    };
  },
  computed: {
    crust(){
      return this.allToppings.filter((topping) => {
        return topping.type == 'crust';
      });
    },
    sauces(){
      return this.allToppings.filter((topping) => {
        return topping.type == 'sauce';
      });
    },
    fruitToppings(){
      return this.allToppings.filter((topping) => {
        return topping.type == 'fruit';
      });
    },
    veggieToppings(){
      return this.allToppings.filter((topping) => {
        return topping.type == 'veggies';
      });
    },
   cheeseToppings(){
      return this.allToppings.filter((topping) => {
        return topping.type == 'cheese';
      });
    },
    meatToppings(){
      return this.allToppings.filter((topping) => {
        return topping.type == 'meat';
      });
    },
    selectedToppingIds(){
      let selectedToppings = [];
      this.allToppings.forEach((topping) => {
       if(topping.isOnPizza){
         selectedToppings.push(topping.topping_id)
       }
      });
      return selectedToppings;
    },
    isMaxToppingsMet(){
      return this.selectedToppingIds.length == this.pizza.max_toppings;
    },
    totalCost(){
      let sizeCost = 0;
      if(this.pizza.pizza_size == 'small'){
        sizeCost = 8;
      } else if (this.pizza.pizza_size == 'medium'){
        sizeCost = 10;
      } else if (this.pizza.pizza_size == 'large'){
        sizeCost = 12;
      }
      let toppingCost = 0;
      this.allToppings.forEach((topping) => {
        if(topping.isOnPizza){
          toppingCost += topping.cost;
        }
        });

      return (sizeCost + toppingCost) * this.pizza.quantity;
    },
  },

  methods: {

    
//updates the store with the current pizza selections ("in cart")
    submitToppingsForm() {
      this.pizza.is_specialty = false;
      this.pizza.pizza_name = 'custom';
      this.pizza.pizza_cost = this.totalCost;
      this.pizza.toppings = this.selectedToppingIds;
      this.$store.commit("ADD_PIZZA_TO_STORE", this.pizza);
      this.$router.push({ name: "start-order" });
    },
// changes pizzaID currently being stored in ("cart")
    changePizzaId(value) {
      this.$store.commit("UPDATE_CURRENT_ORDER_ID", value);
      this.$router.push({ name: "start-order" });
    },
    changeSauce(sauceId){
      this.sauces.forEach((sauce) => {
        if(sauceId == sauce.topping_id){
          sauce.isOnPizza = true;
        } else {
          sauce.isOnPizza = false;
        }
      })
    },
    changeCrust(crustId){
      this.crust.forEach((crust) => {
        if(crustId == crust.topping_id){
          crust.isOnPizza = true;
        } else {
          crust.isOnPizza = false;
        }
      })
    },
  },
  created() {
    ToppingService.getAvailableToppings().then((response)=>{
      this.allToppings = response.data;
    });
    let pizzaId = this.$route.params.pizzaId;
    if(pizzaId !== ""){
      PizzaService.getAvailablePizza(pizzaId).then((response)=>{
        this.pizza = response.data;
      });
      ToppingService.getToppingsByPizzaId(pizzaId).then((response)=>{
          this.includedToppings = response.data;
          this.allToppings.forEach((topping) => {
            let contains = this.includedToppings.find((pizzaTopping) => {
              return topping.topping_id == pizzaTopping.topping_id;
            })
            if(contains != undefined){
              topping.isOnPizza = true;
            }
            else {
              topping.isOnPizza = false;
            }
          })
        });
    }else{
      this.isNoPathVariable = true;
      this.includedToppings = [];
      this.allToppings.forEach((topping) => {
          topping.isOnPizza = false;
      });
      
      this.pizza.quantity = 1;
      this.pizza.pizza_size = 'small';
    }
    
  },
};
</script>
  
  <style scoped>
@import url("https://fonts.cdnfonts.com/css/cooper-hewitt-book");
@font-face {
    font-family: 'Mandalore Laser Title';
    src: url('../fonts/MandaloreLaserTitle.woff2') format('woff2'),
        url('../fonts/MandaloreLaserTitle.woff') format('woff');
    font-weight: normal;
    font-style: normal;
    font-display: swap;
}
* {
  font-family: "Cooper Hewitt Bold", sans-serif;
}

h1{
  font-family: 'Mandalore Laser Title';
}

input[type="checkbox" i] {
  accent-color: #45A049;
  
}

#submit {
  padding: 10px 15px ;
  margin: 8px;
  border-radius: 5px;
  background-color: #a18f63;
  color: #fff;
  border: none;
  cursor: pointer;
  transition: background-color 0.2s ease-in-out;
}
.buttons {
  padding-left: 5%;
  
}


.topping-card {
  
  color: rgb(121, 72, 17);
  background-color: #b2bb333a;
  border-radius: 8px;
  margin: 20px;
  padding: 130px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease-in-out;
  text-align: center;
  font-size: 18px;

}
.topping-card h1{
  margin-top: 0%;
  color:rgb(165, 122, 42);
}
.select-header{
 color: #BB554A;
 text-align: center;
 padding: 10px;
}
.pizza-size{
  margin-bottom: 8px;
  text-align: center;
  font-size: 20px;
  
}
.sauce{
  margin-top: 10px;
}
.choose-sauce{
   border: 1px solid brown;
   background-color:  #AC685B;
   color: cornsilk;
   border-radius: 5px;
   padding: 15px ;
   font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  
}
.choose-crust{
   border: 1px solid brown;
   background-color:   #AC685B;;
   color: cornsilk;
   border-radius: 5px;
   padding: 15px ;
   margin-top: 20px;
   font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}
.choose-meats{
  border: 1px solid brown;
   background-color:  #AC685B;
   color: cornsilk;
   border-radius: 5px;
   padding: 15px ;
   margin-top: 20px;
   font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}
.choose-cheese{
  border: 1px solid brown;
   background-color: #AC685B;
   color: cornsilk;
   border-radius: 5px;
    padding: 15px ;
   margin-top: 20px;
   font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;

}
.choose-veggies{
  border: 1px solid brown;
   background-color:  #AC685B;
   color: cornsilk;
   border-radius: 5px;
    padding: 15px ;
   margin-top: 20px;
   font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}
.choose-fruit{
  border: 1px solid brown;
   background-color: #AC685B;
   color: cornsilk;
   border-radius: 5px;
   padding: 20px ;
   margin-top: 20px;
   font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
   
}
.price-quantity{
  margin-top: 80px;
  border: 1px solid brown;
   background-color:  #AC685B;
   color: cornsilk;
   border-radius: 5px;
   padding: 5px ;
   font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;

}
.value{
  color: rgb(226, 223, 223);
  background-color: #BB554A;
  padding: 8px;
  font-size: 15px;
  border-radius: 10px;
  
}
input[type='radio'] {
    accent-color: #45A049;
  
}
.topping-selection {
  margin: 20px 0;
}

.toppings {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  text-transform: lowercase;
  text-transform: capitalize;
  margin-left: 150px;
  margin-top: 8px;
  font-size: 18px;
}

.topping {
  margin-right: 15px;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
}

.topping input[type="checkbox"] {
  margin-right: 5px;
}

.topping label {
  cursor: pointer;
}
.value option:hover{
  background-color: seagreen;
}
@media (min-width: 600px) {
  .toppings {
    justify-content: flex-start;
  }
}
</style>
  