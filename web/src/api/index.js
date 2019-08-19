// import { noteList } from "./mock";
import axios from "axios";
import Vue from "vue";

//请求返回拦截，把数据返回到页面之前做些什么...
axios.interceptors.response.use(
    response => {
        let res = response.data;
        console.log("<i> ", res, Vue.prototype);
        switch (res.status) {
            case 200:
                break;
            case 401:
                Vue.prototype.$message.warn(
                    // `[401]\n${res.message}\n${res.data}`
                    "用户登录信息过期，请重新登录"
                );
                Vue.prototype.$store.commit("note/SET_USERNAME", null);
                break;
            default:
                // Vue.prototype.$message.error(
                //     `[${res.status}]\n${res.message}\n${res.data}`
                // );
                break;
        }
        return response;
    },
    error => {
        return Promise.reject(error);
    }
);

export default {
    /**
     * ajax api
     */
    init() {
        return new Promise((resolve, reject) => {
            axios.get("/services/init").then(resp => {
                let res = resp.data;
                console.log("api init res", res);
                if (200 == res.status) {
                    resolve(res.data);
                } else {
                    reject(res.data);
                }
            });
        });
    },
    getContent(noteId) {
        return new Promise((resolve, reject) => {
            // setTimeout(() => {
            //     console.log(
            //         "api getContent noteId",
            //         noteId,
            //         noteList,
            //         noteList.find(x => x._id == noteId).content
            //     );
            //     // debugger;
            //     resolve(noteList.find(x => x._id == noteId).content);
            // }, 2000);
            axios.post("/services/note/content", { noteId }).then(resp => {
                let res = resp.data;
                console.log("api getContent res", noteId, res);
                if (200 == res.status) {
                    resolve(res.data);
                } else {
                    reject(res.data);
                }
            });
        });
    },
    sync(data) {
        return new Promise((resolve, reject) => {
            // setTimeout(() => {
            //     console.log("api sync", data);
            //     resolve("ok");
            // }, 2000);
            console.log("api sync", data);
            axios.post("/services/note/sync", data).then(resp => {
                let res = resp.data;
                console.log("api sync res", res);
                if (200 == res.status) {
                    resolve(res.data);
                } else {
                    reject(res.data);
                }
            });
        });
    },
    login(username, password) {
        return new Promise((resolve, reject) => {
            axios
                .post("/services/user/login", {
                    username,
                    password
                })
                .then(resp => {
                    let res = resp.data;
                    console.log("api login res", res);
                    if (200 == res.status) {
                        resolve(res.data);
                    } else {
                        reject(res.data);
                    }
                });
        });
    },
    logout() {
        return new Promise((resolve, reject) => {
            axios.get("/services/user/logout").then(resp => {
                let res = resp.data;
                console.log("api logout res", res);
                if (200 == res.status) {
                    resolve(res.data);
                } else {
                    reject(res.data);
                }
            });
        });
    },
    addNote() {
        return new Promise((resolve, reject) => {
            console.log("api addNote");
            axios.post("/services/note/add").then(resp => {
                let res = resp.data;
                console.log("api addNote res", res);
                if (200 == res.status) {
                    resolve(res.data);
                } else {
                    reject(res.data);
                }
            });
        });
    },
    delNote(noteId) {
        return new Promise((resolve, reject) => {
            // setTimeout(() => {
            //     console.log("api delNote", noteId);
            //     resolve("ok");
            // }, 2000);
            axios.post("/services/note/del", { noteId }).then(resp => {
                let res = resp.data;
                console.log("api delNote res", noteId, res);
                if (200 == res.status) {
                    resolve(res.data);
                } else {
                    reject(res.data);
                }
            });
        });
    }
    /**
     * websocket api
     */
};
