import * as types from "./mutation-types";
import API from "../../api";
import Util from "../../util";

export default {
    init({ commit, dispatch }, debug) {
        return new Promise(resolve => {
            API.init(debug).then(res => {
                console.log("action init", res);
                commit(types.SET_INIT, true);
                commit(types.SET_USERNAME, res.username);
                if (res.username) {
                    commit(types.SET_NOTE_LIST, res.titleList);
                    // commit(types.SET_NOTE_ID, res.noteId);
                    dispatch("setNoteId", res.noteId);
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
    sync({ commit, state }) {
        let changedNoteList = state.noteList.filter(x => {
            if (x.$sync) {
                return x;
            }
        });
        console.log(state, changedNoteList);
        let data = [];
        changedNoteList.forEach(element => {
            let newTitle = element.$sync.oldTitle ? element.title : undefined;
            let diff = element.$sync.oldContent
                ? Util.diff(element.$sync.oldContent, element.content)
                : undefined;
            console.log("----", newTitle, diff);
            data.push({
                _id: element._id,
                newTitle,
                diff
            });
        });
        if (data.length !== 0) {
            API.sync(data).then(res => {
                // console.log(
                //     "haha",
                //     Util.applyDiff(changedNoteList[0].$sync.oldContent, res[0].diff)
                // );
                if (res == "ok") {
                    commit(types.SYNC_SUCCESS);
                }
            });
        }
    },
    login({ commit }, { username, password }) {
        return new Promise(resolve => {
            console.log("action login", username, password);
            API.login(username, password).then(res => {
                console.log("action login res", res);
                commit(types.SET_USERNAME, username);
                resolve();
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
                title: "title",
                content: "content"
            });
            commit(types.SET_NOTE_ID, vid);
            API.addNote(vid).then(res => {
                console.log("action addNote res", res);
                commit(types.ADD_NOTE_SET_ID, {
                    vid: vid,
                    noteId: res
                });
                commit(types.SET_NOTE_ID, res);
                resolve();
            });
        });
    },
    delNote({ commit, state }, index) {
        return new Promise(resolve => {
            let noteId = state.noteList[index]._id;
            console.log("action delNote", index, noteId);
            API.delNote(noteId).then(res => {
                console.log("action delNote res", res);
                commit(types.DEL_NOTE, noteId);
                resolve();
            });
        });
    }
};
