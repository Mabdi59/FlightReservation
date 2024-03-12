import axios from "axios";

export default{
    getToppingsByPizzaId(pizzaId){
        return axios.get(`/toppings/pizza/${pizzaId}`);
    },
    getAllToppings(){
        return axios.get('/toppings');
    },
    getToppings(){
        return axios.get('/toppings')
    },
    getToppingByType(type){
        return axios.get(`/toppings/type/${type}`)
    },
    createTopping(topping){
        return axios.post('/toppings', topping)
    },
    updateTopping(topping){
        return axios.put('/toppings', topping)
    },
    getAvailableToppings(){
        return axios.get('/toppings/available')
    }
}