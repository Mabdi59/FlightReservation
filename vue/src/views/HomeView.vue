<template>
    <div id="home">
      <div class="buttons">
    <button v-on:click="$router.push({ name: 'menu' })">View Menu</button>
    <button v-on:click="$router.push({ name: 'start-order' })">Place Order</button>
  </div>
  <PizzaCard/>
    </div>
  </template>
  
  <script>
  import PizzaCard from '../components/PizzaCard.vue';
  export default {
    components:{PizzaCard},
    name: 'HomeView',
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

:root {
    --brand-green-color: #5FA873;
    --brand-brown-color: #A18F63;
    --brand-lightred-color: #AC685B;
    --brand-darkred-color: #BB554A;
    --brand-white-color: #FFFFFF;
}

#home {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
}
.pizza-card-container {
    display: grid;
    grid-template-columns: repeat(2, 1fr); 
    gap: 20px;
    width: 100%;
    max-width: 1200px;
}

.buttons {
    display: flex;
    gap: 20px;
    margin-top: 20px;
}

button {
    background-color: var(--brand-brown-color);
    color: var(--brand-white-color);
    border: 2px solid var(--brand-green-color);
    border-radius: 10px;
    padding: 10px 20px;
    font-size: 1.1em;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: var(--brand-green-color);
}

.pizza-card-container {
    display: grid;
    grid-template-columns: repeat(2, 1fr); 
    gap: 20px;
    width: 100%;
    max-width: 1200px;
}

.pizza-card img {
    width: 100%;
    height: auto;
    border-radius: 10px;
}

.pizza-card h3,
.pizza-card p {
    margin: 5px 0;
}
@media (max-width: 600px) {
    #home .pizza-card-container {
        grid-template-columns: 1fr; 
    }
}
</style>

  