<template>
    <h1>{{ order.orderName }}</h1>
    <h3>Phone Number: {{ order.phoneNumber }}</h3>
    <div v-if="order.delivery">
        <h2>Delivery Info:</h2>
        <p>Address: {{ order.address }}</p>
        <p>Delivery Time: {{ order.deliveryDateTime }}</p>
    </div>
    <select v-model="order.orderStatus" @change="changeStatus()">
        <option value="pending">Pending</option>
        <option v-if="order.delivery" value="out for delivery">Out For Delivery</option>
        <option v-if="order.delivery" value="delivered">Delivered</option>
        <option value="in kitchen">In Kitchen</option>
        <option v-if="!order.delivery" value="awaiting pickup">Awaiting Pickup</option>
        <option value="complete">Complete</option>
        <option value="canceled">Canceled</option>
    </select>
    <div class="order" v-for="pizza in pizzasInOrder" :key="pizza.pizza_id">
        <div class="pizza">
            <span>Pizza Name: <span class="uppercase">{{ pizza.pizza_name }}</span></span>
            <span>Size: <span class="uppercase">{{  pizza.pizza_size }}</span></span>
            <span>Qty: {{ pizza.quantity }}</span>
            <input type="checkbox">
        </div>
        <div class="toppings">
            <div class="toppings-list" v-for="topping in pizza.toppings" :key="topping.topping_id">
                <span class="uppercase">{{ topping.topping_name }}</span>
                <span class="uppercase">{{ topping.type }}</span>

            </div>
        </div>


    </div>
  
</template>

<script>
import UserOrderService from '../services/UserOrderService';
import PizzaService from '../services/PizzaService';
import ToppingService from '../services/ToppingService';

export default {
    data(){
        return{
            order: {},
            pizzasInOrder:[],
        }
    },
    created(){
        const id = this.$route.params.orderId;
        
        UserOrderService.getOrderById(id).then((response)=>{
            this.order = response.data;
            PizzaService.getPizzasByOrderId(id).then((response)=>{
                this.pizzasInOrder = response.data;
                this.pizzasInOrder.forEach((pizza)=>{
                    PizzaService.getPizzaQuantityByPizzaIdAndOrderId(pizza.pizza_id, id).then((response)=>{
                        pizza.quantity = response.data;
                    });
                    ToppingService.getToppingsByPizzaId(pizza.pizza_id).then((response)=>{
                        pizza.toppings = response.data;
                    })
                });
            });
        });

    },
    methods:{
        changeStatus(){
            UserOrderService.updateOrder(this.order).then((response)=>{

            });
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
*{
    font-family: 'Cooper Hewitt Book', sans-serif;
}
h1, button{
    font-family: 'Mandalore Laser Title';
}
h1{
    
    color: #A18F63;
}
input[type="checkbox" i]{
    accent-color: #BB554A;
}
.uppercase{
    text-transform: uppercase;
}
.order{
    background-color: #a18f6380;
    padding: 15px;
    margin: 10px;
    border-radius: 20px;
    border: #AC685B 3px solid;
    width: 80vw;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.pizza{
    background-color: #5FA873;
    color: white;
    padding: 10px;
    border-radius: 10px;
    width: 90%;
    display: flex;
    justify-content: space-evenly;
}
.toppings{
    width: 75%;
}
.toppings-list{
    padding: 5px;
    border-radius: 5px;
    display: flex;
    justify-content: space-between;
    margin: 5px;
}
.toppings>div:nth-child(even){
    background-color: #BB554A;
    color: white;;
}
.toppings>div:nth-child(odd){
    background-color: white;
    color: #BB554A;
}
</style>