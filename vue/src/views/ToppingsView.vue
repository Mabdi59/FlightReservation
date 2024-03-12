<template>
<div id="back-button">
<button id="back-button" v-on:click="$router.push({ name: 'start-order' })">Go Back To Order</button>
</div>
  <div>
    <PizzaEditor />
  </div>
</template>

<script>
import PizzaEditor from "../components/PizzaEditor.vue";

export default {
  components: { PizzaEditor },
  computed: {
    currentPizzaId() {
      let currentPizzaId = this.$store.state.currentPizzaId;
      return currentPizzaId;
    },
  },
};
</script>

<style>
 @import url('https://fonts.cdnfonts.com/css/cooper-hewitt-book');
*{
  font-family: 'Cooper Hewitt Book', sans-serif;
}
button {
  padding: 10px;
  margin: 8px;
  background-color:  #A18F63;
  border-radius: 5px;
  color: #fff;
  border: none;
  cursor: pointer;
  transition: background-color 0.2s ease-in-out;
}

</style>