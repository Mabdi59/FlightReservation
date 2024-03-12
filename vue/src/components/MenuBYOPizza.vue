<template>
    <div class="topping-card">
        <div class="header">
  <h2 id="BYOP-title">Build Your Own Pizza!</h2>
        </div>
  <div id="container">
    <div class="topping-container-style">
        <h3 
            class="Crust topping-type-title"> Crust Style
        </h3>
        <div class="toppings"
            v-for="topping in crustToppings" :key="topping.topping_id">{{ topping.topping_name }} {{ topping.cost == 0 ? '':'- $' + topping.cost + ' extra' }}
        </div>
    </div>
    <div class="topping-container-style">
        <h3 
            class="Sauce topping-type-title"> Pizza Sauces
        </h3>
        <div class="toppings"
            v-for="topping in sauceToppings" :key="topping.topping_id">{{ topping.topping_name }}{{ topping.cost == 0 ? '':'-$' + topping.cost + ' extra' }}
        </div>
    </div>
    <div class="topping-container-style">
        <h3 
            class="Cheese topping-type-title"> Cheeses
        </h3>
        <div class="toppings"
            v-for="topping in cheeseToppings" :key="topping.topping_id">{{ topping.topping_name }} {{ topping.cost == 0 ? '':'- $' + topping.cost}}
        </div>
    </div>
    <div class="topping-container-style">
        <h3 
            class="Meat topping-type-title"> Meat Toppings
        </h3>
        <div class="toppings"
            v-for="topping in meatToppings" :key="topping.topping_id">{{ topping.topping_name }} {{ topping.cost == 0 ? '':'- $' + topping.cost}}
        </div>
    </div>
    <div class="topping-container-style">
        <h3 
            class="Fruit topping-type-title"> Fruit Toppings
        </h3>
        
        <div class="toppings"
            v-for="topping in fruitToppings" :key="topping.topping_id">{{ topping.topping_name }} {{ topping.cost == 0 ? '':'- $' + topping.cost}}
        </div>
    </div>
    <div class="topping-container-style">
        <h3 
            class="Veggie topping-type-title"> Veggie Toppings
        </h3>
        
        <div class="toppings"
            v-for="topping in veggieToppings" :key="topping.topping_id">{{ topping.topping_name }} {{ topping.cost == 0 ? '':'- $' + topping.cost}} 
        </div>
    </div>
  </div>
</div>

</template>

<script>
import ToppingService from '../services/ToppingService'
export default {
    created(){
        ToppingService.getAvailableToppings()
        .then((response) => {
             this.availableToppings = response.data });
    },
    data(){
        return{
            availableToppings: [],
        }
    },
    computed: {
        crustToppings(){
            return this.availableToppings.filter((topping) => {
                return topping.type =="crust"
            })
        },
        sauceToppings(){
            return this.availableToppings.filter((topping) => {
                return topping.type =="sauce"
            })
        },
        cheeseToppings(){
            return this.availableToppings.filter((topping) => {
                return topping.type == "cheese"
            })
        },
        meatToppings(){
            return this.availableToppings.filter((topping) => {
                return topping.type == "meat"
            })
        },
        fruitToppings(){
            return this.availableToppings.filter((topping) => {
                return topping.type == "fruit"
            })
        },
        veggieToppings(){
            return this.availableToppings.filter((topping) => {
                return topping.type == "veggies"
            })
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
.topping-card {
    box-sizing: border-box;
    background-color: #a18f6380;
    border-radius: 12px;
}
#container {
    width: 100%;
    /* height: 65vh; */
    display: flex;
    box-sizing: border-box;
    flex-wrap: wrap;
    justify-content: space-evenly;
    align-items: stretch;
    align-content: center;
    row-gap: 1rem;
    column-gap: 1rem;
    flex-grow: 1;
    flex-shrink: 0;
    padding: 15px;
}
    
.topping-container-style{
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0,0,0, .01);
    width: 250px;
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    justify-content: space-between;
    box-sizing: border-box;
    border: 2px inset #BB554A;
    background-color: var(--brand-lightred-color);
}
.toppings{
    margin: auto;
    padding: 5px 1px;
    text-transform: uppercase;
    color: white;
}
#BYOP-title {
    font-family: 'Mandalore Laser Title';
    color: #BB554A;
    background: linear-gradient(45deg, #a18f6380 50%, #5FA873 100%);
    height: 100%;
    border-radius: 12px;
    border-bottom-left-radius: 0px;
    border-bottom-right-radius: 0px;
    padding: 10px;
    text-shadow: white 0px 0 5px;
    font-size: 2em;
}

.topping-type-title {
    font-family: 'Mandalore Laser Title';
    text-decoration: underline;
    font-size: 25px;
    padding: 10px;
    margin: 0px;
    background-color: white;
    border-radius: 8px;
    color: #BB554A;
    border-bottom-left-radius: 0px;
    border-bottom-right-radius: 0px;
}
.toppings:not(:last-child){
    border-bottom: 1px solid black;
}
</style>