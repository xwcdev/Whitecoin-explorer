<template>
  <div id="app">
    <v-nav/>
    <router-view class="mian_alls" />
    <iFooter />
  </div>
</template>

<script>
import VNav from "./components/nav/VNav";
import iFooter from "./components/nav/iFooter";
import bus from "./utils/bus";

export default {
  components: { VNav,iFooter },
  name: "App",
  created() {
    let href = window.location.href;
    if (href.indexOf("/blocks") > 0) {
      bus.navChoice = 1;
    } else if (href.indexOf("/transaction") > 0) {
      bus.navChoice = 2;
    } else if (href.indexOf("/contracts") > 0) {
      bus.navChoice = 3;
    } else if(href.indexOf('/richlist') > 0) {
      bus.navChoice = 4;
    }
  }
};
</script>

<style lang="less">
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /*height: 100%;*/
  .app-top-lin {
    display: block;
    height: 0;
  }

  input::-webkit-input-placeholder {
    font-size: 12px;
  }
  .mian_alls{
    height: 100%;
  }
}

</style>
