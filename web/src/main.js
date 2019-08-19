import Vue from "vue";
import App from "./App.vue";
import store from "./store";

import { message, notification } from "ant-design-vue";
// import 'ant-design-vue/dist/antd.css'
import "ant-design-vue/lib/message/style/css";
import "ant-design-vue/lib/notification/style/css";

import api from "./api";
// import ws from "./network/ws";
import "./assets/note.css";
import "./assets/font.css";
import "./assets/loading.css";

Vue.config.productionTip = false;

Vue.prototype.$message = message;
Vue.prototype.$notification = notification;

Vue.prototype.$api = api;
// Vue.prototype.$ws = ws;

Vue.prototype.$store = store;

new Vue({
    // store,
    render: h => h(App)
}).$mount("#app");
