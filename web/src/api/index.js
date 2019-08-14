import { noteList } from "./mock";

export default {
    /**
     * ajax api
     */
    // getTitleList() {
    //     return new Promise(resolve => {
    //         setTimeout(() => {
    //             let titleList = JSON.parse(JSON.stringify(noteList));
    //             resolve(
    //                 titleList.map(x => {
    //                     delete x.content;
    //                     return x;
    //                 })
    //             );
    //         }, 1000);
    //     });
    // },
    init(debug) {
        return new Promise(resolve => {
            setTimeout(() => {
                console.log("api init");
                let titleList = JSON.parse(JSON.stringify(noteList));
                // debugger;
                debug = 1;
                if (debug) {
                    resolve({
                        username: "anjinshuo",
                        titleList: titleList.map(x => {
                            delete x.content;
                            return x;
                        }),
                        noteId: "0001"
                    });
                } else {
                    resolve({
                        username: null //"anjinshuo",
                        // titleList: titleList.map(x => {
                        //     delete x.content;
                        //     return x;
                        // }),
                        // noteId: "0001"
                    });
                }
            }, 2000);
        });
    },
    getContent(noteId) {
        return new Promise(resolve => {
            setTimeout(() => {
                console.log(
                    "api getContent noteId",
                    noteId,
                    noteList,
                    noteList.find(x => x._id == noteId).content
                );
                // debugger;
                resolve(noteList.find(x => x._id == noteId).content);
            }, 2000);
        });
    },
    sync(data) {
        return new Promise(resolve => {
            setTimeout(() => {
                console.log("api sync", data);
                resolve("ok");
            }, 2000);
        });
    },
    login(username, password) {
        return new Promise(resolve => {
            setTimeout(() => {
                console.log("api login", username, password);
                resolve("ok");
            }, 2000);
        });
    },
    logout() {
        return new Promise(resolve => {
            setTimeout(() => {
                console.log("api logout");
                resolve("ok");
            }, 2000);
        });
    },
    addNote(vid) {
        return new Promise(resolve => {
            setTimeout(() => {
                console.log("api addNote", vid);
                resolve("noteId_" + vid);
            }, 5000);
        });
    },
    delNote(noteId) {
        return new Promise(resolve => {
            setTimeout(() => {
                console.log("api delNote", noteId);
                resolve("ok");
            }, 2000);
        });
    }
    /**
     * websocket api
     */
};
