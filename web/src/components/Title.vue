<template>
  <div class="title">
    <div class="input" v-if="title !== undefined">
      <UnderlineContainer :active="isFocus" v-show="noteId">
        <input type="text" @blur="onBlur" @focus="onFocus" v-model="title" spellcheck="false" />
      </UnderlineContainer>
    </div>
  </div>
</template>

<script>
import UnderlineContainer from "./UnderlineContainer";
import { mapState, mapMutations, mapGetters } from "vuex";

export default {
  components: {
    UnderlineContainer
  },
  data() {
    return {
      isFocus: false
    };
  },
  mounted() {
    console.log("+++", this.noteId);
  },
  computed: {
    ...mapState("note", {
      noteList: state => state.noteList,
      noteId: state => state.noteId
    }),
    ...mapGetters("note", {
      noteIndex: "noteIndex",
      noteTitle: "noteTitle"
    }),
    title: {
      get() {
        return this.noteTitle;
        // let tmp = this.noteList[this.noteIndex];
        // return tmp && tmp.title;
      },
      set(newVal) {
        this.setTitle({ noteId: this.noteId, title: newVal });
      }
    }
  },
  methods: {
    ...mapMutations("note", { setTitle: "SET_TITLE" }),
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
.title {
  padding: 0 20px;
  /* background: #ddd; */
  border-radius: 0 10px 0 0;
  display: flex;
  justify-content: center;
  height: 100%;
}
.input {
  height: 100%;
  width: 100%;
  /* background: green; */
}
input {
  letter-spacing: 0.1em;
  color: #666;
  width: 100%;
  height: 100%;
  line-height: 2em;
  background: none;
  font: 20px Quicksand-Medium;
  border: 0;
  outline: none;
  padding: 0;
}
</style>
