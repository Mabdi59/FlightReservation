import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      currentOrderId: 0,
      currentPizzaId: 0,
      token: currentToken || '',
      user: currentUser || {},
     
      orderData: {
        customerDetails: {
          orderName: "",
          phoneNumber: "",
          orderDateTime: "",
          isDelivery: false,
          address: "",
          deliveryDateTime: '',
          paymentInfo: "",
          totalCost: 0,
          orderStatus: 'pending',
          emailAddress: ''
        },
        pizzaSelection: [
      
      ]

      }
      
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_ORDER(state, payload){
        state.orderData = payload;
      },

       SET_PIZZA_ORDER(state, payload){
        state.orderData.pizzaSelection = payload;
      },
    
      UPDATE_ORDER_DATA(state, newData){
        state.orderData = {...state.orderData, ...newData};
       },
       CHANGE_CURRENT_PIZZA_ID(state, selectedPizzaId){
        state.currentPizzaId = selectedPizzaId;
      },
       SAVE_PIZZA_SELECTION(state, currentToppings){
         let newPizzaSelection = {
        pizzaId : state.currentPizzaId,
        toppings : currentToppings,
        quantity : 1
         }

         state.orderData.pizzaSelection.unshift(newPizzaSelection);
         
         
       },
       ADD_PIZZA_TO_STORE(state, pizza){
        if(pizza.quantity > 0){
          state.orderData.pizzaSelection.unshift(pizza);
        }
       },
       DELETE_PIZZA(state, pizza_id){
        let pizzaIndex = state.orderData.pizzaSelection.findIndex((pizza) => {
          return pizza.pizza_id == pizza_id;
        });
        if(pizzaIndex > -1) {
          state.orderData.pizzaSelection.splice(pizzaIndex, 1)
        }
        
       },
       CLEAR_ORDER(state, payload){
          state.orderData = {};
          state.orderData = {
            customerDetails: {
              orderName: "",
              phoneNumber: "",
              orderDateTime: "",
              isDelivery: false,
              address: "",
              deliveryDateTime: '',
              paymentInfo: "",
              totalCost: 0,
              orderStatus: 'pending',
              emailAddress: ''
            },
            pizzaSelection: []
          }
       }

    
    },
     

  });
  return store;
}
