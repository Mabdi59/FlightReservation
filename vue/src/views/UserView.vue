<template>
    <UserNav />
    <div id="welcome">
        <h2>Welcome, Employee!</h2>
        <h4>Please select an option</h4>
    </div>
</template>

<script>
import UserNav from '../components/UserNav.vue';
export default {
    components:{UserNav}

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

*{
    font-family: 'Mandalore Laser Title';

}
h2{
    color: #BB554A;
}
h4{
    color: #5FA873;
}
#welcome{
    display: flex;
    flex-direction: column;
    align-items: center;
}
</style>