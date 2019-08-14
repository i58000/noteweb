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
    }
};
