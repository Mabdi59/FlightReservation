<template>
    <div>
        <button id="pizzas" @click="pizzaClick()">Pizzas</button>
        <button id="toppings" @click="toppingClick()">Toppings</button>
        <button id="orders" @click="ordersClick()">Orders</button>
    </div>
  
</template>

<script>
export default {
    methods:{
        pizzaClick(){
            this.$router.push({name: 'user-pizzas'})
        },
        toppingClick(){
            this.$router.push({name: 'user-toppings'})

        },
        ordersClick(){
            this.$router.push({name: 'user-orders'})

        },
    }

}
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
*{
    font-family: 'Mandalore Laser Title';

}
button{
    box-sizing: border-box;
    margin: 10px;
    background-color: #a18f6380;
    color: #BB554A;
    border: #5FA873 2px solid;
    border-radius: 10px;
    height: 50px;
    width: 150px;
    font-size: 1.1em;
    transition: 250ms;
    cursor: pointer;
}
button:hover,
button:active{
    box-sizing: border-box;
    margin: 10px;
    background-color: #ffffff;
    color: #BB554A;
    border: #5FA873 3px solid;
    border-radius: 10px;
    height: 50px;
    width: 150px;
    font-size: 1.1em;
    transition: 250ms;
}
div{
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
}
</style>