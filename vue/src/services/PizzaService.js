import axios from "axios";

export default {
    getAllPizzas() {
        return axios.get('/pizzas');
    },
      
    getAllSpecialtyPizzas() {
        return axios.get('/pizzas/specialty');
    },

    addToppingToPizza(pizzaId, toppingId) {
        return axios.post(`/pizzas/${pizzaId}/add/${toppingId}`);
    },

    removeToppingFromPizza(pizzaId, toppingId) {
        return axios.delete(`/pizzas/${pizzaId}/remove/${toppingId}`);
    },

    updatePizza(pizza) {
        return axios.put('/pizzas/update', pizza);
    },

    createSpecialtyPizza(pizza) {
        return axios.post('/pizzas/specialty', pizza);
    },

    getAvailableSpecialtyPizzas() {
        return axios.get('/pizzas/specialty/available');
    },

    getAllToppings() {
        return axios.get('/toppings');
    },

    createCustomPizza(pizza) {
        return axios.post('/pizzas/create-custom', pizza);
    },

    placeOrder(order) {
        return axios.post('/orders', order);
    },

    getOrderConfirmation(orderId) {
        return axios.get(`/orders/confirmation/${orderId}`);
    },
    getPizzasByOrderId(orderId){
        return axios.get(`/pizzas/order/${orderId}`);
    },
    getPizzaQuantityByPizzaIdAndOrderId(pizzaId,orderId){
        return axios.get(`/pizzas/${pizzaId}/order/${orderId}/quantity`);
    },
    createCustomSpecialtyPizza(pizza){
        return axios.post(`/pizzas/create-specialty`, pizza);
    },
    getAvailablePizza(pizzaId){
        return axios.get(`/pizzas/${pizzaId}/customer`);
    }
}
