<template>
  <div class="editor">
    <div class="input">
      <!-- <UnderlineContainer :active="isFocus" position="top" :hoverable="false"> -->
      <textarea v-if="content" @focus="onFocus" @blur="onBlur" v-model="content" spellcheck="false"></textarea>
      <div v-if="content === undefined" class="loading-container">
        <div class="box">
          <div class="loading loader-13"></div>
        </div>
      </div>
      <!-- </UnderlineContainer> -->
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapGetters } from "vuex";

export default {
  data() {
    return {
      isFocus: false
    };
  },
  computed: {
    ...mapState("note", {
      noteId: state => state.noteId,
      noteList: state => state.noteList
    }),
    ...mapGetters("note", {
      noteContent: "noteContent"
    }),
    content: {
      get() {
        return this.noteContent;
        // let tmp = this.noteList[this.noteId];
        // return tmp && tmp.content;
      },
      set(newVal) {
        this.setContent({ noteId: this.noteId, content: newVal });
      }
    }
  },
  methods: {
    ...mapMutations("note", { setContent: "SET_CONTENT" }),
    onBlur() {
      this.isFocus = false;
    },
    onFocus() {
      this.isFocus = true;
    }
  }
};
</script>

<style scoped>
.editor {
  padding: 0 20px;
  display: flex;
  justify-content: center;
}
.input {
  width: 100%;
  height: 100%;
  position: relative;
}
textarea {
  font-size: 16px;
  color: #333;
  margin-top: 10px;
  margin-bottom: 10px;
  resize: none;
  padding: 0;
  width: 100%;
  min-height: 2em;
  /* height: fit-content; */
  height: calc(100% - 20px);
  line-height: 1.5em;
  letter-spacing: 0.1em;
  /* background: grey; */
  font-family: Quicksand;
  border: 0;
  outline: none;
}
</style>
