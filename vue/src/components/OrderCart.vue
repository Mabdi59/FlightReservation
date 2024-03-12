<template>
<div class="cart-title-and-contents">
    <h2 id="cart-title">Cart</h2>
    <div class="cart-contents">
        <div class="item" v-for="pizza in pizzas" :key="pizza.pizza_id">
            <div class="pizza-title">
                <span>{{ pizza.pizza_name }} - </span>
                <span>Quantity: {{ pizza.quantity }}</span>
                <span id="pizza-cost"> - ${{ pizza.pizza_cost * pizza.quantity }}</span>
                <br />
                 <button @click="increaseQuantity(pizza)">Increase Quantity</button>
                <button @click="removePizza(pizza.pizza_id)">Remove</button>
            </div>
        <div>
            <div class="pizza-toppings" v-for="topping in pizza.toppingsOnPizza" :key="topping.topping_id">
                <span>{{topping.topping_name}}</span>
            </div>
        </div>
    </div>
    <span id="total-pizza-cost">Total: ${{ total }}</span>
  </div>
</div>
</template>

<script>
import ToppingService from '../services/ToppingService';

export default {
    data(){
        return {
            pizzas:[],
            toppings:[],
        }
    },
    created(){
        this.pizzas = this.$store.state.orderData.pizzaSelection;

        ToppingService.getAvailableToppings().then((response)=>{
            this.toppings = response.data;

            this.pizzas.forEach((pizza)=>{
                pizza.toppingsOnPizza = this.toppings.filter((topping)=>{
                    return pizza.toppings.includes(topping.topping_id ); 
                });
            });
        });
    },
    computed: {
        total() {
            let total = 0;
            if(this.pizzas.length > 0){
                this.pizzas.forEach((pizza) => {
                    let pizzaCost = pizza.quantity * pizza.pizza_cost;
                    total += pizzaCost;
                })
            }
            return total;
        }
    },
    methods: {

        removePizza(pizza_id) {
            this.$store.commit("DELETE_PIZZA", pizza_id)
            
    },
        increaseQuantity(pizza) {
        if (pizza.quantity < 10) {
            pizza.quantity += 1;
    }
}
    }
}

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
#cart-title{
    font-family: 'Mandalore Laser Title';
    font-size: 35.2px;
    text-align: center;
    color: #BB554A;
}
.cart-contents{
    max-width: 400px;
    padding: 40px;
    border: 1px solid #ddd;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    background-color: #e6ee741a;
    margin: 25px auto;
}
.item{
    margin-bottom: 10px;
    border-bottom: 1px solid black;
    padding: 5px;
}
.pizza-toppings{
    text-transform: capitalize;
    text-decoration: dashed;
}
.pizza-title{
    text-decoration: underline;
    font-size: 20px;
}
#pizza-cost{
    color: #BB554A;
}
#total-pizza-cost{
    color: #BB554A;
}
</style>