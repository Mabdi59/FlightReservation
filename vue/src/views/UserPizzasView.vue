<template>
    <UserNav />
    <UserPizzaForm />
</template>

<script>
import UserNav from '../components/UserNav.vue';
import UserPizzaForm from '../components/UserPizzaForm.vue';
export default {
    components:{UserNav, UserPizzaForm}

}
</script>

<style scoped>
@font-face {
    font-family: 'Mandalore Laser Title';
    src: url('../fonts/MandaloreLaserTitle.woff2') format('woff2'),
        url('../fonts/MandaloreLaserTitle.woff') format('woff');
    font-weight: normal;
    font-style: normal;
    font-display: swap;
}

*{
    font-family: 'Mandalore Laser Title';

}
</style>