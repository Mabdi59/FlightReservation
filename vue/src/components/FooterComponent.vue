<template>
    <footer>
        <a href="#">about Us</a>
        <br />
        <a href="#">Reviews</a>
        <br />
        <router-link :to="{name:'login'}">Employee Login</router-link>
    </footer>
</template>

<script>
export default {

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

:root{
    --brand-green-color: #5FA873;
    --brand-brown-color: #A18F63;
    --brand-lightred-color: #AC685B;
    --brand-darkred-color: #BB554A;
    --brand-white-color: #FFFFFF;
}

footer{
    border-top-left-radius: 25px;
    border-top-right-radius: 25px;
    background-color: #a18f6366;
    color: #5FA873;
    justify-self: flex-end;
    width: 90vw;
    height: 90px;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    padding: 15px;
    margin-top: 10px;
}
a{
    color: #BB554A;
}
*{
    font-family: 'Mandalore Laser Title';

}
</style>