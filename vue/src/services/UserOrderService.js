import axios from "axios";

export default{
    getOrders(){
        return axios.get('/orders');
    },
    getOrderById(orderId){
        return axios.get(`/orders/${orderId}`);
    },
    updateOrder(order){
        return axios.put(`/orders/update/${order.orderId}`, order);
    },
    createOrder(order){
        return axios.post(`/orders/create`, order);
    },
    addPizzaToOrder(orderId, pizzaId, quantity){
        return axios.post(`/orders/addPizzaToOrder/${orderId}/${pizzaId}/${quantity}`);
    }
}