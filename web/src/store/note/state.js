export default {
    init: false,
    username: null,
    /**
     *  which note
     */
    noteId: null,
    oldNoteId: null,
    /**
     * {
     *    _id: "",
     *    title: "",
     *    content: ""
     * }
     */
    noteList: [],
    syncing: false,
    syncTime: 0,
    syncConfig: {
        onInput: true,
        inputCount: 20,
        inputInterval: 10000, // 10s

        onTimeInterval: true,
        timeInterval: 600000 // 10min
    }
};
