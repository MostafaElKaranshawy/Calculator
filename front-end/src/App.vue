<template>
  <div id="app">
    <span class="one"></span>
    <calculator msg:/>
  </div>
</template>

<script>
import calculator from "./components/calculator.vue"
export default {
  name: 'App',
  components: {
    calculator
  }
}
</script>

<style>
  .one { 
    width: 100vw;
    height: 100vh;
    position: absolute;
    z-index: -1;
    background-image: linear-gradient(black 25%, white 67%, green 100%) !important;
  }
</style>