import Vue from "vue";
import App from "./App.vue";
import store from "./store";

import api from "./api";
// import ws from "./network/ws";
import "./assets/note.css";
import "./assets/font.css";
import "./assets/loading.css";

Vue.config.productionTip = false;

Vue.prototype.$api = api;
// Vue.prototype.$ws = ws;

new Vue({
    store,
    render: h => h(App)
}).$mount("#app");
