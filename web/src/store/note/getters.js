import Util from "../../util";

const _index = state => {
    // debugger
    return state.noteList.findIndex(x => x._id == state.noteId);
};

export default {
    noteIndex: state => {
        return _index(state);
    },
    noteContent: state => {
        let tmp = state.noteList[_index(state)];
        return tmp ? tmp.content : null;
    },
    noteTitle: state => {
        let tmp = state.noteList[_index(state)];
        return tmp ? tmp.title : null;
    },
    syncItems: state => {
        console.log("-------syncItems--------");
        let changedNoteList = state.noteList.filter(x => {
            if (x.$sync) {
                return x;
            }
        });
        console.log(state, changedNoteList);
        let data = [];
        changedNoteList.forEach(element => {
            let newTitle =
                element.$sync.oldTitle === undefined
                    ? undefined
                    : element.title;
            let diff =
                element.$sync.oldContent === undefined
                    ? undefined
                    : Util.diff(element.$sync.oldContent, element.content);
            console.log("----", newTitle, diff);
            data.push({
                _id: element._id,
                newTitle,
                diff
            });
        });
        if (state.oldNoteId != state.noteId) {
            data.push({
                noteId: state.noteId
            });
        }
        return data;
    }
};
