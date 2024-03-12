<template>
    <div class="pizza-card-container">
      <div class="pizza-card" v-for="pizza in availablePizzas" :key="pizza.pizza_id"
        :style="{ backgroundImage: 'url(' + geturl(pizza.imageUrl) + ')' }">
        <h3 class="pizza-name">{{ pizza.pizza_name }}</h3>
        <p class="pizza-note">{{ pizza.note }}</p>
      </div>
    </div>
  </template>

<script>
import PizzaService from '../services/PizzaService';

export default {
  data() {
    return {
      availablePizzas: []
    }
  },
  methods:{
    geturl(name){
        return new URL("../images/"+name, import.meta.url).href
    }
  },
  created() {
    PizzaService.getAvailableSpecialtyPizzas()
      .then(response => {
        this.availablePizzas = response.data;
      });
  },
}
</script>

<style scoped>
@font-face {
    font-family: 'Mandalore Laser Title';
    src: url('./fonts/MandaloreLaserTitle.woff2') format('woff2'),
        url('./fonts/MandaloreLaserTitle.woff') format('woff');
    font-weight: normal;
    font-style: normal;
    font-display: swap;
}

:root{
    --brand-green-color: #5FA873;
    --brand-brown-color: #A18F63;
    --brand-lightred-color: #AC685B;
    --brand-darkred-color: #BB554A;
    --brand-white-color: #971414;
}


*{
    font-family: 'Mandalore Laser Title';

}
.pizza-card>p{
    align-self: flex-end;
    font-size: 0em;
    transition: 500ms;
}
.pizza-card {
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin: 10px;
    border-radius: 15px;
    width: 300px;
    height: 150px;
    background-image: url('../images/Hero.png');
    transition: 500ms;
    padding: 5px;
    position: relative; 
}
.pizza-name {
    position: absolute;
    top: 10px; 
    left: 10px; 
    color: white;
    text-shadow: black 2px 0 10px;
    z-index: 1;
    margin: 0;
}
.pizza-note {
    position: absolute;
    bottom: 10px; 
    right: 10px; 
    color: white;
    text-shadow: black 2px 0 10px;
    z-index: 1;
    margin: 0;
}

.pizza-card:hover,
.pizza-card:active{
    width: 350px;
    height: 350px;
    transition: 500ms;

}
.pizza-card:hover>p,
.pizza-card:active>p{
    transition: 500ms;
    font-size: 1em;

}
.pizza-card:hover>h3,
.pizza-card:active>h3{
    transition: 500ms;
    font-size: 1.5em;
}
.pizza-card>h3,
.pizza-card>h3{
    transition: 500ms;
    font-size: 1.2em;
}
.pizza-name,
.pizza-desc{
    color: white;
    text-shadow: black 2px 0 10px;
}
.pizza-card {
    background-size: cover; 
    background-position: center; 
}
</style>
