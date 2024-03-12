<template>
<div id="back-button">
<button id="back-button" v-on:click="this.$router.push({name: 'home'})">Go Back</button>
<button @click="showCart = !showCart" v-show="areItemsInCart && !orderPlaced"><font-awesome-icon :icon="['fas', 'cart-plus']" /></button>
</div>
    
  
  <div class="order-view">
    <OrderCart v-if="showCart && !orderPlaced && areItemsInCart" />


    <OrderForm v-show="!isCustomerDataEntered && !orderPlaced" @updateCustomerInfo="updateCustomerInfo" />

    <div class="order" v-show="isCustomerDataEntered && !orderPlaced">
      <h1>Place Your Pizza Order</h1>
      <PizzaSelection @selectPizza="selectPizza" />
      <div class="custom-pizza-section">
        <h2>Custom Pizza</h2>
        <button @click="byoPizza()" id="byo-button">Build Your Own</button>
      </div>
      
      <button id="confirm" @click="confirmOrder" v-show="isCustomerDataEntered && areItemsInCart && !orderPlaced">Confirm Order</button>
    </div>
    
    <div v-if="orderPlaced" id="thanks">
      <h1>Thank you for choosing Pathway Pizza!</h1>
      <div class="reviews">
      <p>“I tried The Jackson pizza - I don’t know who came up with that, but they’re a genius!”
    -Christopher Guarnera </p>
    <p> “There’s truly no better way to celebrate a job offer than with a Pathway Pizza.”
    -Dr. Isaac Chow
      </p>
    <p>“You made it through the Tech Elevator bootcamp - that calls for a Margherita (pizza, of course), from Pathway Pizza!"
     -Rita Stall
      </p>
    <p>“There’s a pizza for everyone! Even with her New Year’s Resolution, my wife always spares a cheat meal for The Safest Option!” -Doug Amos</p>
    <p></p>
    </div>
    </div>
 
  </div>
</template>

<script>
import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
library.add(fas);


import ToppingService from '../services/ToppingService';
import UserOrderService from '../services/UserOrderService';
import OrderForm from '../components/OrderForm.vue';
import PizzaSelection from '../components/PizzaSelection.vue';
import OrderCart from '../components/OrderCart.vue';
import PizzaService from '../services/PizzaService';

export default {
  components: {
    OrderForm,
    PizzaSelection,
    OrderCart,
  
    FontAwesomeIcon,
  },
  data() {
    return {
      showCartButton: false,
      showCart: false,
      orderPlaced: false,

      currentOrderId: 0,
      selectedPizzaSize: 'medium',
      orderDetails: {
        customerInfo: null, 
        deliveryInfo: null,
        pizzaSelections: []
      },
      showConfirmation: false
    };
  },
  computed: {
    isOrderReady() {
      return this.orderDetails.customerInfo &&
             this.orderDetails.deliveryInfo &&
             this.orderDetails.pizzaSelections.length > 0;
    },
    areItemsInCart(){
      return this.$store.state.orderData.pizzaSelection.length > 0;
    },
    isCustomerDataEntered(){
      return this.$store.state.orderData.orderName &&
            this.$store.state.orderData.phoneNumber &&
            this.$store.state.orderData.emailAddress;
    }
  },
  methods: {
    byoPizza(){
      this.$router.push({name: 'topping', params:{pizzaId: ''}})
      window.scrollTo(0,0);

    },
    updateCustomerInfo(customerInfo) {
      this.orderDetails.customerInfo = customerInfo;
    },
    updateDeliveryDetails(deliveryInfo) {
      this.orderDetails.deliveryInfo = deliveryInfo;
    },
    selectPizza(pizza) {
      this.orderDetails.pizzaSelections.push({...pizza, size: this.selectedPizzaSize});
    },
    confirmOrder() {
      let order = this.$store.state.orderData;
      let totalCost = 0;
      let orderToSend = {};
      orderToSend.orderName = order.orderName;
      orderToSend.phoneNumber = order.phoneNumber;
      orderToSend.delivery = order.isDelivery;
      orderToSend.isDelivery = order.isDelivery;
      orderToSend.orderStatus = order.orderStatus;
      orderToSend.emailAddress = order.emailAddress;
      orderToSend.totalCost = totalCost;
      let orderReceived = {};
      UserOrderService.createOrder(order).then((response)=>{
        orderReceived = response.data;
        order.pizzaSelection.forEach((pizza)=>{
            let pizzaCost = pizza.pizza_cost * pizza.quantity;
            totalCost += pizzaCost;
            if(!pizza.is_specialty){
              PizzaService.createCustomPizza(pizza).then((response)=>{
                let customPizza = response.data;
                pizza.toppings.forEach((toppingId)=>{
                  PizzaService.addToppingToPizza(customPizza.pizza_id, toppingId);
                });
                UserOrderService.addPizzaToOrder(orderReceived.orderId, customPizza.pizza_id, pizza.quantity);

              });
            } else{

              UserOrderService.addPizzaToOrder(orderReceived.orderId, pizza.pizza_id, pizza.quantity);
              
            }
          });
          orderReceived.totalCost = totalCost;
          UserOrderService.updateOrder(orderReceived);

      });
      this.orderPlaced = true;
      this.$store.commit('CLEAR_ORDER', 0);
      window.scrollTo(0,0);
      

    },
    closeConfirmation() {
      this.showConfirmation = false;
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

h1, h2, #confirm{
  font-family: 'Mandalore Laser Title';
}
 .order-view {
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}
 .order-view h1{
   text-align: center;
 }
.back-button{
  margin-left: auto;
}

h1 {
  color: var(--brand-darkred-color);
  margin-bottom: 20px;
  font-size: 2.2em;
}
h2{
  color: var(--brand-brown-color);
  font-size: 1.8em;
  text-decoration: underline;
  text-shadow: grey 1px 0 1px;
}

button {
  background-color: var(--brand-darkred-color);
  color: white;
  border: none;
  border-radius: 4px;
  padding: 12px 24px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
  font-weight: bold;
  
}
.custom-pizza-section{
  margin: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 10px;
  padding: 15px;
  box-shadow: var(--brand-lightred-color) 0px 0 5px;
}
#byo-button{
  font-family: 'Mandalore Laser Title';
  font-size: 2em;
  background-color: var(--brand-green-color);
}
#byo-button:hover{
  background-color: var(--brand-brown-color);
}

button:hover {
  background-color: var(--brand-green-color);
  
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin-top: 10px;
  background: #f9f9f9;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.OrderForm, .DeliveryForm, .OrderSummary {
  width: 100%;
  max-width: 600px;
  margin-bottom: 20px;
} 
.order{
  display: flex;
  flex-direction: column;
  align-items: center;
}
#confirm{
  margin-top: 30px;
  font-size: 3vw;
}

.reviews {
  display: flex;
  flex-direction: column;
  margin: auto;
  font-style: italic;
  width: 70%;
  font-size: 1.3em;
  color: #BB554A;
  padding: 40px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #e6ee741a; 
}

p {
  margin-left: 15px;
  margin-right: 15px;
}

</style>
