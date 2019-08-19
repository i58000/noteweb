import * as types from "./mutation-types";
import API from "../../api";
import Util from "../../util";

export default {
    init({ commit, dispatch, state }) {
        return new Promise(resolve => {
            API.init().then(res => {
                console.log("action init", res);
                commit(types.SET_INIT, true);
                commit(types.SET_USERNAME, res.username);
                if (res.username) {
                    commit(types.SET_NOTE_LIST, res.titleList);
                    // commit(types.SET_NOTE_ID, res.noteId);
                    let noteId = res.noteId;
                    if (!noteId) {
                        noteId = state.noteList[0] && state.noteList[0]._id;
                    }
                    dispatch("setNoteId", noteId);
                    commit(types.SET_OLD_NOTE_ID, noteId);
                }
                resolve(res.username);
            });
        });
    },
    setNoteId({ commit, getters }, noteId) {
        commit(types.SET_NOTE_ID, noteId);
        console.log(
            "action setNoteId getters.noteContent",
            getters.noteContent
        );
        if (getters.noteContent === undefined) {
            API.getContent(noteId).then(content => {
                console.log("action API.getContent", noteId, content);
                commit(types.SET_CONTENT, { noteId, content });
            });
        }
    },
    sync({ commit, state, getters }) {
        // debugger
        return new Promise((resolve, reject) => {
            let data = getters.syncItems;
            if (data.length !== 0) {
                commit(types.SYNC_START);
                API.sync(data)
                    .then(res => {
                        // console.log(
                        //     "haha",
                        //     Util.applyDiff(changedNoteList[0].$sync.oldContent, res[0].diff)
                        // );
                        console.log("action api sync res", res);
                        commit(types.SYNC_SUCCESS, res);
                        resolve();
                    })
                    .catch(e => {
                        console.log("action api sync catch", e);
                        reject(e);
                    });
            }
        });
    },
    login({ commit }, { username, password }) {
        return new Promise((resolve, reject) => {
            console.log("action login", username, password);
            API.login(username, password)
                .then(res => {
                    console.log("action login res", res);
                    commit(types.SET_USERNAME, username);
                    resolve();
                })
                .catch(errcode => {
                    reject(errcode);
                });
        });
    },
    logout({ commit }) {
        return new Promise(resolve => {
            console.log("action logout");
            API.logout().then(res => {
                console.log("action logout res", res);
                commit(types.SET_USERNAME, null);
                resolve();
            });
        });
    },
    addNote({ commit }) {
        return new Promise(resolve => {
            console.log("action addNote");
            // gen vid
            let vid = "vid_" + new Date().getTime();
            commit(types.ADD_NOTE, {
                _id: vid,
                title: "",
                content: ""
            });
            commit(types.SET_NOTE_ID, vid);
            API.addNote()
                .then(res => {
                    console.log("action addNote res", res);
                    commit(types.ADD_NOTE_SET_ID, {
                        vid: vid,
                        noteId: res
                    });
                    commit(types.SET_NOTE_ID, res);
                    resolve();
                })
                .catch(err => {
                    reject(RangeError);
                });
        });
    },
    delNote({ commit, state }, index) {
        return new Promise(resolve => {
            let noteId = state.noteList[index]._id;
            console.log("action delNote", index, noteId);
            API.delNote(noteId).then(res => {
                console.log("action delNote res", res);
                if (0 == res) {
                    // debugger
                    commit(types.DEL_NOTE, noteId);
                    commit(types.SET_NOTE_ID, null);
                }
                resolve();
            });
        });
    }
};
