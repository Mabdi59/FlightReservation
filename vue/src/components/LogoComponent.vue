<template>
  <div id="brand">
        <img v-on:click="goHome()" id="logo" src="../images/byosmall.png">
        <div>
            <div id="container">
                <h1 v-on:click="goHome()" id="with-line">Pathway Pizza</h1>
                <span id="large-screen-only"></span>
            </div>
            <div id="way-container">
                <p>This is the way</p>
                <span id="small-screen-only"></span>
            </div>
            
        </div>
    </div>
</template>
    
<script>

const brandGreenColor = '#5FA873';

export default {
    methods:{
        goHome(){
            this.$router.push({name: 'home'});
        }
    }

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
*{
    font-family: 'Mandalore Laser Title';
}
#with-line{
    cursor: pointer;
    border-bottom: #A18F63 5px solid;
}
h1{
    color: #5FA873;
}
p{
    color: #BB554A;
    letter-spacing: -.2em;
}
#logo{
    cursor: pointer;
    width: 100px;
    flex-grow: 0;
    
}
#brand{
    display: flex;
    border-bottom: #5FA873 5px solid;
    width: 90vw;
    align-items: center;
}
#brand>div{
    flex-grow: 1;
}
#container{
    display: flex;
    flex-direction: column;
}
#container>h1{
    
    align-self: flex-start;
}
#large-screen-only{
    display: none;
}
#small-screen-only{
    display: block;
    background: linear-gradient(0.25turn,#BB554A, white);
    border-top-left-radius: 3px;
    border-bottom-left-radius: 3px;
    flex-grow: 1;
    height: .8em;
    margin-bottom: 17px;
}
#way-container{
    display: flex;
    align-items: flex-end;
    gap: 5px;
}
@media only screen and (min-width: 675px) {
    #small-screen-only{
        display: none;
    }
    #brand>div{
        display: flex;
        justify-content: space-between;
        flex-grow: 1;

    }

    #brand>div>div>h1{
        font-size: 3em;
        align-self: flex-start;
        margin-top: 15px;
    }
    #brand>div>div>p{
        align-self: flex-end;
        font-size: 1.3em;
        margin-bottom: 10px;
    }
    #large-screen-only{
        display: block;
        background: linear-gradient(0.25turn, white, #BB554A);
        flex-grow: 1;
        height: 1.1em;
        margin-bottom: 10px;
        border-top-right-radius: 3px;
        border-bottom-right-radius: 3px;
    }
    #container{
        flex-grow: 1;
        display: flex;
        flex-direction: column;
    }
    #logo{
    cursor: pointer;
    width: 140px;
    flex-grow: 0;
        
    }
}
</style>