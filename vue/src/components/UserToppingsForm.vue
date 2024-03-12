<template>
  <h1>Toppings</h1>
  <button class="add-topping" @click="isAddToppingVisible = !isAddToppingVisible" :disabled="isToppingBeingUpdated">{{addToppingButtonText}} </button>
  <table>
    <thead>
        <tr>
            <th id="thead-type">Type
                <select id="type-select" v-bind:disabled="isToppingBeingUpdated || isAddToppingVisible" v-model="filterType">
                    <option value = "all">All</option>
                    <option value = "meat">Meat</option>
                    <option value = "veggies">Veggies</option>
                    <option value = "cheese">Cheese</option>
                    <option value = "fruit">Fruit</option>
                    <option value = "crust">Crust</option>
                    <option value = "sauce">Sauce</option>
                </select>
            </th>
            <th id="thead-name">Name</th>
            <th id="thead-cost">Cost</th>
            <th id="thead-available">Available</th>
            <th id="thead-blank"></th>
        </tr>
    </thead>
    <tbody>
        <tr id="add-topping" v-show="isAddToppingVisible">
            <td><select v-model="toppingToAdd.type">
                <option value = "meat">Meat</option>
                <option value = "veggies">Veggies</option>
                <option value = "cheese">Cheese</option>
                <option value = "fruit">Fruit</option>
                <option value = "crust">Crust</option>
                <option value = "sauce">Sauce</option>
            </select></td>
            <td><input type="text" v-model="toppingToAdd.topping_name"></td>
            <td><input type="number" v-model="toppingToAdd.cost"></td>
            <td><label class="Uppercase" for="addAvailable">y/n</label><input name="addAvailable" type="checkbox" v-model="toppingToAdd.isAvailable"></td>
            <td><button class="add-button" @click="createTopping()" :disabled="isToppingBeingUpdated">add</button></td>
        </tr>
        <tr>
 
        </tr>
        <tr class="topping-row" v-for="topping in toppingsToShow" :key="topping.topping_id"> 
            <td>
                <span class="Uppercase" v-show="!topping.isToppingEdit">{{topping.type}}</span>
                <select v-if="topping.isToppingEdit" v-model="toppingToUpdate.type">
                    <option value="meat">Meat</option>
                    <option value="veggies">Veggies</option>
                    <option value="cheese">Cheese</option>
                    <option value="fruit">Fruit</option>
                    <option value = "crust">Crust</option>
                    <option value = "sauce">Sauce</option>
                </select>
            </td>
            <td>
                <span class="Uppercase" v-show="!topping.isToppingEdit">{{topping.topping_name}}</span>
                <input type="text" v-model="toppingToUpdate.topping_name" v-if="topping.isToppingEdit">
            </td>
            <td>
                <span v-show="!topping.isToppingEdit">${{topping.cost}}</span>
                <input type="number" v-model="toppingToUpdate.cost" v-if="topping.isToppingEdit">
            </td>
            <td>
                <svg xmlns="http://www.w3.org/2000/svg" class="x-icon" v-show="!topping.isAvailable && !topping.isToppingEdit" viewBox="0 0 384 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M376.6 84.5c11.3-13.6 9.5-33.8-4.1-45.1s-33.8-9.5-45.1 4.1L192 206 56.6 43.5C45.3 29.9 25.1 28.1 11.5 39.4S-3.9 70.9 7.4 84.5L150.3 256 7.4 427.5c-11.3 13.6-9.5 33.8 4.1 45.1s33.8 9.5 45.1-4.1L192 306 327.4 468.5c11.3 13.6 31.5 15.4 45.1 4.1s15.4-31.5 4.1-45.1L233.7 256 376.6 84.5z"/></svg>
                <svg xmlns="http://www.w3.org/2000/svg" class="check-icon" v-show="topping.isAvailable && !topping.isToppingEdit" viewBox="0 0 448 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M438.6 105.4c12.5 12.5 12.5 32.8 0 45.3l-256 256c-12.5 12.5-32.8 12.5-45.3 0l-128-128c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0L160 338.7 393.4 105.4c12.5-12.5 32.8-12.5 45.3 0z"/></svg>
                <label class="Uppercase" for="updateAvailable" v-if="topping.isToppingEdit">y/n</label><input name="updateAvailable" type="checkbox" v-model="toppingToUpdate.isAvailable" v-if="topping.isToppingEdit">
            </td>
            <td class="button-div">
                <button class="edit-button" :disabled="(computeIsToppingBeingUpdated && !topping.isToppingEdit) || isAddToppingVisible" @click="editTopping(topping.topping_id)">{{topping.isToppingEdit ? 'Update':'Edit'}}</button>
                <button class="cancel-button" @click="cancelUpdate(topping.topping_id)" v-if="topping.isToppingEdit">cancel</button>
            </td>
        </tr>
    </tbody>
  </table>
</template>

<script>
import ToppingService from '../services/ToppingService'
export default {
    created(){
        ToppingService.getAllToppings()
        .then((response) => {
            this.allToppings = response.data;
        });
        ToppingService.getToppingByType("meat")
        .then((response) => {
            this.allMeatToppings = response.data;
        });
        ToppingService.getToppingByType("veggies")
        .then((response) => {
            this.allVeggieToppings = response.data;
        });
        ToppingService.getToppingByType("cheese")
        .then((response) => {
            this.allCheeseToppings = response.data;
        });
        ToppingService.getToppingByType("fruit")
        .then((response) => {
            this.allFruitToppings = response.data;
        });
    },
    data(){
        return{
            allToppings: [],
            toppingsToAdd: {},
            allMeatToppings: [],
            allVeggieToppings: [],
            allCheeseToppings: [],
            allFruitToppings: [],
            allSauceToppings: [],
            allCrustToppings: [],
            filterType: "all",
            toppingToUpdate: {},
            toppingToAdd: {},
            isAddToppingVisible: false,
            isToppingBeingUpdated: false,
        }
    },
    computed:{
        addToppingButtonText(){
            return this.isAddToppingVisible ?
                        'hide add topping':
                        'add topping';
        },
        computeIsToppingBeingUpdated(){
            return this.isToppingBeingUpdated;
        },
        toppingsToShow(){
            if(this.filterType == "all"){
                return this.allToppings;
            }else{
                return this.allToppings.filter(( topping ) => {
                    return topping.type == this.filterType;
                });
            }
        }
    },
    methods:{
        editTopping(toppingId){
            let indexOfTopping = this.allToppings.findIndex((topping) => {
                return topping.topping_id == toppingId;
            });
            if(!this.allToppings[indexOfTopping].isToppingEdit){
                this.isToppingBeingUpdated = true;
                this.allToppings[indexOfTopping].isToppingEdit = true;
                this.toppingToUpdate = {...this.allToppings[indexOfTopping]};
            }else{
                this.isToppingBeingUpdated = false;
                this.allToppings[indexOfTopping].isToppingEdit = false;

                ToppingService.updateTopping(this.toppingToUpdate).then((response) => {
                    ToppingService.getAllToppings()
                    .then((response) => {
                        this.allToppings = response.data;
                    });
                    this.toppingToUpdate = {};
                })
            }
        },
        cancelUpdate(toppingId){
            let indexOfTopping = this.allToppings.findIndex((topping) => {
                return topping.topping_id == toppingId;
            });
            this.isToppingBeingUpdated = false;
            this.allToppings[indexOfTopping].isToppingEdit = false;
            this.toppingToUpdate = {};
        },
        createTopping(){
            ToppingService.createTopping(this.toppingToAdd).then((response) => {
                this.toppingToAdd = {};
                ToppingService.getAllToppings().then((response)=>{
                    this.allToppings = response.data;
                });
                this.isAddToppingVisible = !this.isAddToppingVisible
            });
        },
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
h1{
    color: #A18F63;
}
*{
    font-family: 'Cooper Hewitt Book', sans-serif;
}
table {
  border-collapse: collapse;
  border-bottom: #5FA873 1px solid;
}
.topping-row{
    border-top: #5FA873 1px solid;
    
}
h1, button, th {
    font-family: 'Mandalore Laser Title';

}
.x-icon{
    width: 15px;
    fill: #BB554A;
}
.check-icon{
    width: 20px;
    fill: #5FA873;
}
#type-select{
    border-block-color: #5FA873;
    margin: 5px;
    background-color: #5FA873;
    color: #FFFFFF;
}
#thead-type{
    background-color: #5FA873;
    color: #FFFFFF;
    width: 85px;
}
#thead-name{
    background-color: #A18F63;
    color: #FFFFFF;
    width: 150px;
}
#thead-cost{
    background-color: #AC685B;
    color: #FFFFFF;
    width: 60px;
}
#thead-available{
    background-color: #A18F63;
    color: #FFFFFF;
    width: 60px;
}
#thead-blank{
    background-color: #5FA873;
    width: 20px;
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
.add-topping{
    margin: 10px;
    font-size: 1.1em;
    padding: 10px;
}
input[type="checkbox" i]{
    accent-color: #BB554A;
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
input[type="text" i]{
    width: 95%;
}
input[type="number" i]{
    width: 90%;
}
.Uppercase{
    text-transform: uppercase;
}
</style>