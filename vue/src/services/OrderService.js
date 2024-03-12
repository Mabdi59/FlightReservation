import axios from 'axios';

const API_URL = 'http://localhost:9000';

export default {
  // Create a new order
  createOrder(orderData) {
    return axios.post(`${API_URL}/orders`, orderData)
      .then(response => response.data)
      .catch(error => {
        console.error('Error creating order:', error);
        throw error;
      });
  },

  // Get all orders
  getOrders() {
    return axios.get(`${API_URL}/orders`)
      .then(response => response.data)
      .catch(error => {
        console.error('Error fetching orders:', error);
        throw error;
      });
  },

  // Get details of a specific order
  getOrderDetails(orderId) {
    return axios.get(`${API_URL}/orders/${orderId}`)
      .then(response => response.data)
      .catch(error => {
        console.error('Error fetching order details:', error);
        throw error;
      });
  },

  // Update an existing order
  updateOrder(order) {
    return axios.put(`${API_URL}/orders/${order.order_id}`, order)
      .then(response => response.data)
      .catch(error => {
        console.error('Error updating order:', error);
        throw error;
      });
  }
};
