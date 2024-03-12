<template>
    <h1>Specialty Pizzas</h1>
    <button class="add-pizza" @click="isAddPizzaVisible = !isAddPizzaVisible" :disabled="isPizzaBeingUpdated">{{addPizzaButtonText}}</button>
    <table>
        
        <thead>
            <tr>
                <th id="thead-name">Name</th>
                <th id="thead-size">Size</th>
                <th id="thead-cost">Cost</th>
                <th id="thead-max-toppings">Max Toppings</th>
                <th id="thead-note">Note<span class="th-note-extra" v-show="isPizzaBeingUpdated || isAddPizzaVisible"> / Description / URL</span></th>
                <th id="thead-available">available</th>
                <th id="thead-blank"></th>
            </tr>
        </thead>
        <tbody>

            <tr id="add-pizza" v-show="isAddPizzaVisible">
                <td><input type="text" v-model="pizzaToAdd.pizza_name" placeholder="Name"></td>
                <td><select v-model="pizzaToAdd.pizza_size">
                    <option value="small">Small</option>
                    <option value="medium">Medium</option>
                    <option value="large">Large</option>
                </select></td>
                <td><input type="number" v-model="pizzaToAdd.pizza_cost" placeholder="1.00"></td>
                <td><input type="number" v-model="pizzaToAdd.max_toppings" placeholder="3"></td>
                <td>
                    <input type="text" v-model="pizzaToAdd.note" placeholder="Note">
                    <input type="text" v-model="pizzaToAdd.description" placeholder="Description">
                    <input type="text" v-model="pizzaToAdd.imageUrl" placeholder="/url/default">
                </td>
                <td><label class="Uppercase" for="addAvailable">y/n</label><input name="addAvailable" type="checkbox" v-model="pizzaToAdd.is_available"></td>
                <td><button class="cancel-button" @click="createSpecialtyPizza()" :disabled="isPizzaBeingUpdated">add</button></td>
            </tr>
            <template v-for="pizza in pizzas" :key="pizza.pizza_id">
                <tr class="pizza-row">
                    
                    <td>
                        <span v-show="!pizza.isPizzaEdit">{{ pizza.pizza_name }}</span> 
                        <input type="text" v-model="pizzaToUpdate.pizza_name" v-if="pizza.isPizzaEdit">
                    </td>
                    <td>
                        <span class="Uppercase" v-show="!pizza.isPizzaEdit">{{ pizza.pizza_size }}</span>
                        <select v-if="pizza.isPizzaEdit" v-model="pizzaToUpdate.pizza_size">
                            <option value="small">Small</option>
                            <option value="medium">Medium</option>
                            <option value="large">Large</option>
                        </select>
                    </td>
                    <td>
                        <span v-show="!pizza.isPizzaEdit">${{ pizza.pizza_cost }}</span>
                        <input type="number" v-model="pizzaToUpdate.pizza_cost" v-if="pizza.isPizzaEdit">
                    </td>
                    <td>
                        <span v-show="!pizza.isPizzaEdit">{{ pizza.max_toppings }}</span>
                        <input type="number" v-model="pizzaToUpdate.max_toppings" v-if="pizza.isPizzaEdit">
                    </td>
                    <td>
                        <span v-show="!pizza.isPizzaEdit">{{ pizza.note }}</span>
                        <input type="text" v-model="pizzaToUpdate.note" v-if="pizza.isPizzaEdit">
                        <input type="text" v-model="pizzaToUpdate.description" v-if="pizza.isPizzaEdit">
                        <input type="text" v-model="pizzaToUpdate.imageUrl" v-if="pizza.isPizzaEdit">
                    </td>
                    <td class="available">
                        <svg xmlns="http://www.w3.org/2000/svg" class="x-icon" v-show="!pizza.is_available && !pizza.isPizzaEdit" viewBox="0 0 384 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M376.6 84.5c11.3-13.6 9.5-33.8-4.1-45.1s-33.8-9.5-45.1 4.1L192 206 56.6 43.5C45.3 29.9 25.1 28.1 11.5 39.4S-3.9 70.9 7.4 84.5L150.3 256 7.4 427.5c-11.3 13.6-9.5 33.8 4.1 45.1s33.8 9.5 45.1-4.1L192 306 327.4 468.5c11.3 13.6 31.5 15.4 45.1 4.1s15.4-31.5 4.1-45.1L233.7 256 376.6 84.5z"/></svg>
                        <svg xmlns="http://www.w3.org/2000/svg" class="check-icon" v-show="pizza.is_available && !pizza.isPizzaEdit" viewBox="0 0 448 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M438.6 105.4c12.5 12.5 12.5 32.8 0 45.3l-256 256c-12.5 12.5-32.8 12.5-45.3 0l-128-128c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0L160 338.7 393.4 105.4c12.5-12.5 32.8-12.5 45.3 0z"/></svg>
                        <label for="updateAvailable" class="Uppercase" v-if="pizza.isPizzaEdit">y/n</label><input name="updateAvailable" type="checkbox" v-model="pizzaToUpdate.is_available" v-if="pizza.isPizzaEdit">


                    </td>
                    <td class="button-div">
                        <button class="edit-button" :disabled="(computeIsPizzaBeingUpdated && !pizza.isPizzaEdit) || isAddPizzaVisible" @click="editPizza(pizza.pizza_id, $event)">{{ pizza.isPizzaEdit ? 'Update':'Edit'  }}</button>
                        <button class="cancel-button" @click="cancelUpdate(pizza.pizza_id)" v-if="pizza.isPizzaEdit">Cancel</button>
                    </td>
                    
                    

                </tr>
                <tr v-if="pizza.isPizzaEdit" >
                    <td colspan="8">
                        <div class="pizza-toppings">
                            <div class="topping-label">
                                <span>Name</span>
                                <span>Cost</span>
                                <span>Type</span>
                            </div>
                            <div v-for="topping in allToppings" :key="topping.topping_id" class="topping-data">
                                <input type="checkbox" v-model="topping.isOnPizza">
                                <span>{{ topping.topping_name }}</span>
                                <span>${{ topping.cost }}</span>
                                <span>{{ topping.type }}</span>
                                
                            </div>
                        </div>
                    </td>

                </tr>
            </template>
        </tbody>
    </table>

  
</template>

<script>
import PizzaService from '../services/PizzaService';
import ToppingService from '../services/ToppingService';

export default {
    created(){
        PizzaService.getAllSpecialtyPizzas()
        .then((response)=>{
            this.pizzas = response.data;
        });
        ToppingService.getAllToppings()
        .then((response)=>{
            this.allToppings = response.data;
        });
    },
    data(){
        return{
            pizzas:[],
            pizzaToAdd:{},
            isAddPizzaVisible: false,
            pizzaToUpdate: {},
            isPizzaBeingUpdated: false,
            pizzaToppings: [],
            allToppings:[]
        }
    },
    computed:{
        addPizzaButtonText(){
            return this.isAddPizzaVisible ?
                        'hide add pizza':
                        'add pizza';
        },
        computeIsPizzaBeingUpdated(){
            return this.isPizzaBeingUpdated;
        },
        computePizzaToppings(){
            return this.allToppings.filter((topping)=>{
                return topping.isOnPizza;
            });
        }
    },
    methods:{
        editPizza(pizzaId){
            let indexOfPizza = this.pizzas.findIndex((pizza)=>{
                return pizza.pizza_id == pizzaId;
            });
            if(!this.pizzas[indexOfPizza].isPizzaEdit){
                this.isPizzaBeingUpdated = true;
                this.pizzas[indexOfPizza].isPizzaEdit = true;
                this.pizzaToUpdate = {...this.pizzas[indexOfPizza]};
                ToppingService.getToppingsByPizzaId(pizzaId)
                .then((response)=>{
                    this.pizzaToppings = response.data;
                    this.allToppings.forEach((topping)=>{

                        let contains = this.pizzaToppings.find((pizzaTopping)=>{
                            return topping.topping_id == pizzaTopping.topping_id;
                        });
                        if(contains != undefined){
                            topping.isOnPizza = true;
                        }else{
                            topping.isOnPizza = false;
                        }
                    });
                });
                

            }else{
                this.isPizzaBeingUpdated = false;
                this.pizzas[indexOfPizza].isPizzaEdit = false;
                //get new toppings
                let toppingsToAdd = this.allToppings.filter((topping)=>{
                    return topping.isOnPizza && !this.isAlreadyInPizzaToppings(topping);
                });
                // get toppings to be removed
                let toppingsToRemove = this.allToppings.filter((topping)=>{
                    return !topping.isOnPizza && this.isAlreadyInPizzaToppings(topping);
                });

                // add toppings
                if(toppingsToAdd.length > 0){
                    toppingsToAdd.forEach((topping)=>{
                        PizzaService.addToppingToPizza(pizzaId, topping.topping_id);
                    });
                }


                //remove toppings
                if(toppingsToRemove.length > 0){
                    toppingsToRemove.forEach((topping)=>{
                        PizzaService.removeToppingFromPizza(pizzaId, topping.topping_id);
                    })
                }

                //update pizza
                this.pizzaToUpdate.pizza_id = pizzaId;
                PizzaService.updatePizza(this.pizzaToUpdate).then((response)=>{
                    PizzaService.getAllSpecialtyPizzas()
                    .then((response)=>{
                        this.pizzas = response.data;
                    });
                    this.pizzaToUpdate = {};
                    this.pizzaToppings = [];
                })

                
                
            }

        },
        cancelUpdate(pizzaId){
            let indexOfPizza = this.pizzas.findIndex((pizza)=>{
                return pizza.pizza_id == pizzaId;
            });
            if(!this.pizzas[indexOfPizza].isPizzaEdit){
                this.isPizzaBeingUpdated = true;
                this.pizzas[indexOfPizza].isPizzaEdit = true;
                this.pizzaToUpdate = this.pizzas[indexOfPizza];
            }else{
                this.isPizzaBeingUpdated = false;
                this.pizzas[indexOfPizza].isPizzaEdit = false;
                this.pizzaToUpdate = {};
                this.pizzaToppings = [];
            }
        },
        isAlreadyInPizzaToppings(topping){

            let isAlreadyInPizzaToppings = false;
            if(this.pizzaToppings.length > 0){
                this.pizzaToppings.forEach((pizzaTopping)=>{
                    if(pizzaTopping.topping_id == topping.topping_id){
                        isAlreadyInPizzaToppings = true;
                    }
                });
            }
            return isAlreadyInPizzaToppings;
        },
        createSpecialtyPizza(){
            PizzaService.createSpecialtyPizza(this.pizzaToAdd).then((response)=>{
                this.pizzaToAdd = {};
                PizzaService.getAllSpecialtyPizzas()
                .then((response)=>{
                    this.pizzas = response.data;
                });
                this.isAddPizzaVisible = !this.isAddPizzaVisible;
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


h1, button,th, .topping-label>span, .th-note-extra{
    font-family: 'Mandalore Laser Title';

}
*{
    font-family: 'Cooper Hewitt Book', sans-serif;
}
h1{
    color: #A18F63;
}
table {
  border-collapse: collapse;
  border-bottom: #5FA873 1px solid;
}
.pizza-row{
    border-top: #5FA873 1px solid;
    
}

.x-icon{
    width: 15px;
    fill: #BB554A;
}
.check-icon{
    width: 20px;
    fill: #5FA873;
}
.pizza-toppings{
    display: flex;
    flex-direction: column;
    align-items: center;

}
.topping-label{
    background-color: #5FA873;
}
.topping-label,
.topping-data{
    width: 350px;
    border-top: #5FA873 1px solid;
}
.topping-label,
.topping-data{
    display: flex;
    justify-content: flex-end;
}
.topping-label>span,
.topping-data>span{
    width: 100px;
}
.topping-label>span{
    font-weight: bold;
    font-size: .9em;
    color: #FFFFFF;
}

input[type="text" i]{
    width: 95%;
}
input[type="number" i]{
    width: 90%;
}
input[type="checkbox" i]{
    accent-color: #BB554A;
}
#thead-name{
    background-color: #5FA873;
    color: #FFFFFF;
    width: 150px;
}
#thead-size{
    background-color: #A18F63;
    color: #FFFFFF;
    width: 90px;
}
#thead-cost{
    background-color: #AC685B;
    color: #FFFFFF;
    width: 60px;
}
#thead-max-toppings{
    background-color: #5FA873;
    color: #FFFFFF;
    width: 60px;
}
#thead-note{
    background-color: #A18F63;
    color: #FFFFFF;
    width: 300px;
}
#thead-available{
    background-color: #BB554A;
    color: #FFFFFF;
    width: 60px;
}
#thead-blank{
    background-color: #5FA873;
    width: 80px;
}
.button-div{
    display: flex;
    flex-direction: column;
}
.edit-button,
.cancel-button{
    width: 90%;
    margin: 5px;
}
.add-pizza{
    margin: 10px;
    font-size: 1.1em;
    padding: 10px;
}
button{
    box-sizing: border-box;
    background-color: #a18f6380;
    color: #BB554A;
    border: #5FA873 2px solid;
    border-radius: 5px;
    transition: 250ms;
    cursor: pointer;
}
button:hover,
button:active{
    background-color: #ffffff;
    color: #BB554A;
    border-radius: 5px;
    transition: 250ms;
}
button:disabled{
    background-color: #a18f6380;
    color: #A18F63;
    border: #AC685B 2px solid;
    transition: 250ms;
    cursor: auto;

}
.Uppercase{
    text-transform: uppercase;
}
</style>