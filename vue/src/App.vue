<template>
  <div id="capstone-app">

    <LogoComponent />
    
    <router-view />

    <div id="foot">
      <FooterComponent />
    </div>
  </div>
</template>

<script>
import LogoComponent from './components/LogoComponent.vue';
import FooterComponent from './components/FooterComponent.vue';
export default{
  components:{LogoComponent, FooterComponent}

}
</script>

<style scoped>

#capstone-app{
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100vh;
}
#foot{
  margin-top: auto;
}


</style>
