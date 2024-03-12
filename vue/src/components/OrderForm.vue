<template>

  <div class="form-container">
    <form v-on:submit.prevent="saveCustomerInfo" class="customer-form">
   <h1> Please Enter Your Information: </h1>  
      <div class="form-group">
        
        <label for="fullName" id="customer-info" class="grid-child">Full Name:</label>
        <input id="fullName" class="input-box" v-model="customerDetails.orderName" type="text" required placeholder="ex: John Doe" />
      </div>
      <div class="form-group" >
        <label for="phoneNumber" class="grid-child" id="customer-info">Phone Number:</label>
        <input id="phoneNumber" v-model="customerDetails.phoneNumber" type="tel"  pattern="[0-9]{3}[0-9]{3}[0-9]{4}" required  title="Phone number should be 10 digits" />
      </div>
      <div class="form-group">
        <label for="email" class="grid-child" id="customer-info">Email Address:</label>
        <input id="email" class="input-box" v-model="customerDetails.emailAddress" type="email" required placeholder="example@example.com" />
      </div>
      
    
    <div class="delivery-option" v-show="requiredInfoSaved">
   <h3> Please make a selection: </h3>
      <div class="form-group delivery">
        <label for="isDelivery" id="customer-info" class="delivery-option">Delivery</label>
        <input type="radio" 
        :id="customerDetails.isDelivery"
        @click="deliverySelection(true)"
        name="deliveryOption" 
        value="delivery" />
      </div>
       <div class="form-group delivery">
        <label for="isDelivery" id="customer-info" class="delivery-option">Pick-Up</label>
        <input type="radio" 
        :id="customerDetails.isDelivery"
       @click="deliverySelection(false)"
        name="deliveryOption" 
        value="pickup" />
      </div>
      </div>

      <div v-show="customerDetails.isDelivery" class="form-group">
        <label for="address" id="customer-info">Full Address:</label>
        <input id="address" class="input-box" v-model="customerDetails.address" type="text" placeholder="123 Example St, City, State 12345" />
      </div>

      <div v-show="customerDetails.isDelivery" class="form-group">
        <label for="paymentInfo" id="customer-info">Payment Card #:</label>
        <input id="paymentInfo" class="input-box" v-model="customerDetails.paymentInfo" type="text" placeholder="1234 5678 9012 3456" />
      </div>
      <div class="submit-button">
      <button type="submit" id="submit" :disabled="!formEmpty" v-bind:class="readyToSubmit" value="submit">Save Billing Information</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
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
      }
   }
  },
  computed: {
      requiredInfoSaved() {
        if(this.customerDetails.orderName !== '' && this.customerDetails.phoneNumber !== '' && this.customerDetails.emailAddress !== ''){
          return true;
        }
        return false;
      },
      formEmpty(){
        if(this.customerDetails.orderName !== '' && this.customerDetails.phoneNumber !== '' && this.customerDetails.emailAddress
          ){
          return true;
        }
        return false;
      }
  
  },
  methods: {
      deliverySelection(selection) {
        if(selection === true){
          this.customerDetails.isDelivery = true;
        } else{
          this.customerDetails.isDelivery = false;
        }
         return this.customerDetails.isDelivery;
    },
    saveCustomerInfo() {
     this.$store.commit('UPDATE_ORDER_DATA', this.customerDetails);
     window.scrollTo(0,0);
    }
  }
};
</script>


<style scoped>
@import url("https://fonts.cdnfonts.com/css/cooper-hewitt-book");

.order-view {
  width: 100%;
}

#customer-info {
  font-family: 'Cooper Hewitt Book', sans-serif;
  font-size: 1em;
  
}
h1, h3, #submit {
   font-family: "Cooper Hewitt Bold", sans-serif;
  margin-bottom: 38px;
  border-bottom: 1px solid lightgray;
  text-transform: uppercase;
 
}
h1 {
  margin-left: 10px;
  font-size: 1.7em;
  color: #AC685B;
  
}
h3 {
  width: 100%;
  font-size: 1.4em;
  color: #AC685B;
}

.form-container {
  margin: auto;
  padding: 40px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #e6ee741a; 

}
.form-container {
  display: flex;
}

.customer-form {
  max-width: 75%;
  margin: auto;
}


.form-group {
  margin-bottom: 20px; 
  height: auto;
  padding-top: 10px;
  padding-bottom: 1px;
}


::placeholder {
  font-size: 20px;
  color: lightgray;
  opacity: .75;

}

form {
  display: block;
  margin-bottom: 10px;
  font-weight: bold;
  color: #333; 
}

input[type="text"],
input[type="email"],
input[type="tel"] {
  width: 100%;
  padding: 12px;
  border: 1px solid #AC685B;
  border-radius: 8px;
  font-size: 20px;
  background-color: #FFFFFF;
}

input[type="radio"] {
    accent-color: #BB554A;
    display: inline;
     margin: 5px;
     border: 0px;
     width: 100%;
     height: 25px;
}
.delivery-option {
  display: flex;
  flex-direction: row;
  align-items: baseline;
  justify-content: baseline;
  flex-wrap: wrap;
  margin: 10px;
}
.delivery {
  margin-right: auto;
  margin-left: auto;
}


button {
  background-color: #494747;
  color: white;
  padding: 4px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

#submit {
  background-color: #AC685B;
  border: none;
  cursor: pointer;
  margin: 10px 10px 8px 10px;
  font-size: 22px;
}
#submit:hover {
  background-color: #5FA873;
}
.submit-button {
  margin: auto 15%
}
</style>
