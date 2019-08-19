import * as types from "./mutation-types";
import Vue from "vue";
// import getters from "./getters";

export default {
    [types.SET_INIT](state, data) {
        state.init = data;
        state.syncTime = new Date().getTime();
    },
    [types.SET_USERNAME](state, data) {
        state.username = data;
    },
    [types.SET_NOTE_LIST](state, data) {
        state.noteList = data;
    },
    [types.SET_NOTE_ID](state, data) {
        state.noteId = data;
    },
    [types.SET_OLD_NOTE_ID](state, data) {
        state.oldNoteId = data;
    },
    [types.SET_TITLE](state, { noteId, title }) {
        // debugger
        let index = state.noteList.findIndex(x => x._id == noteId);
        // let index = getters.noteIndex(state);
        // sync flag
        if (
            state.noteList[index].$sync == undefined ||
            state.noteList[index].$sync.oldTitle == undefined
        ) {
            Vue.set(state.noteList[index], "$sync", {
                ...state.noteList[index].$sync,
                oldTitle: state.noteList[index].title
            });
        }

        Vue.set(state.noteList[index], "title", title);
    },
    [types.SET_CONTENT](state, { noteId, content }) {
        let index = state.noteList.findIndex(x => x._id == noteId);
        // let index = getters.noteIndex(state);
        console.log("===", state.noteList[index].content);
        // debugger;
        // sync flag
        if (state.noteList[index].content !== undefined) {
            // debugger;
            if (
                state.noteList[index].content != content &&
                (state.noteList[index].$sync == undefined ||
                    state.noteList[index].$sync.oldContent == undefined)
            ) {
                Vue.set(state.noteList[index], "$sync", {
                    ...state.noteList[index].$sync,
                    oldContent: state.noteList[index].content
                });
            }
        }

        Vue.set(state.noteList[index], "content", content);
    },
    [types.SYNC_START](state) {
        state.syncing = true;
        state.syncTime = new Date().getTime();
    },
    [types.SYNC_SUCCESS](state, noteId) {
        state.noteList.map(x => {
            Vue.delete(x, "$sync");
        });
        if (noteId) {
            state.oldNoteId = noteId;
        }
        state.syncing = false;
    },

    [types.ADD_NOTE](state, note) {
        state.noteList.push(note);
    },
    [types.ADD_NOTE_SET_ID](state, { vid, noteId }) {
        console.log("zzzz", state.noteList.find(x => x.noteId == vid));
        state.noteList.find(x => x._id == vid)._id = noteId;
    },

    [types.DEL_NOTE](state, noteId) {
        // console.log("zzzz", state.noteList.find(x => x.noteId == vid));
        let index = state.noteList.findIndex(x => x._id == noteId);
        Vue.delete(state.noteList, index);
        // delete state.noteList.find(x => x._id == noteId);
    }
};
