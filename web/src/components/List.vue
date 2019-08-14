<template>
  <div class="list">
    <ul>
      <li v-for="(item, index) in titleList" :key="index" @click="onClickItem(index)">
        <UnderlineContainer :dark="true" :active="noteId == item._id">
          <div class="row">
            <span class="nowrap">{{ item.title }}</span>
            <span v-if="showDel(index)" class="del" @click="onClickDel(index)">
              <div class="del-btn">
                <svg
                  t="1565773708228"
                  class="icon"
                  viewBox="0 0 1024 1024"
                  version="1.1"
                  xmlns="http://www.w3.org/2000/svg"
                  p-id="1973"
                  width="20"
                  height="20"
                >
                  <path
                    d="M784 288.96l-56.56-56.576L512.464 447.36 297.504 232.384l-56.56 56.576 214.96 214.96L240.96 718.896l56.56 56.56L512.464 560.48l214.976 214.976L784 718.88 569.04 503.92z"
                    fill="#999"
                    p-id="1974"
                  />
                </svg>
              </div>
            </span>
            <span v-if="showDot(index)" class="dot">
              <span class="dot-item">
                <span class="dot-item-o"></span>
                <span class="dot-tips">未同步</span>
              </span>
            </span>
          </div>
        </UnderlineContainer>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import UnderlineContainer from "./UnderlineContainer";

export default {
  components: {
    UnderlineContainer
  },
  data() {
    return {};
  },
  computed: {
    ...mapState("note", {
      titleList: state => state.noteList,
      noteId: state => state.noteId
    })
  },
  created() {
    console.log(this);
    // this.getTitleList();
  },
  methods: {
    ...mapActions("note", ["setNoteId", "delNote"]),
    onClickItem(index) {
      this.setNoteId(this.titleList[index]._id);
    },
    showDot(index) {
      return this.titleList[index].$sync;
    },
    showDel(index) {
      return this.titleList[index]._id == this.noteId;
    },
    onClickDel(index) {
      console.log("onClickDel", index);
      // this.delNote(index);
      this.$emit("clickDel", index);
    }
  }
};
</script>

<style scoped>
.list {
  letter-spacing: 0.1em;
  font-size: 16px;
  color: #333;
  overflow: auto;
  position: relative;
}
ul {
  padding: 5px 20px;
  margin: 0;
  list-style: none;
  display: flex;
  flex-direction: column;
  align-items: center;
}
li {
  line-height: 2.5em;
  width: 100%;
}
.nowrap {
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
  width: 100%;
}
.row {
  display: flex;
  justify-content: space-between;
}
.dot {
  height: inherit;
  /* width: 26px; */
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
}
.dot-item {
  /* margin-right: 7px; */
  margin-left: 15px;
  /* background: red; */
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 24px;
}
.dot-item-o {
  background: #aaa;
  height: 10px;
  width: 10px;
  border-radius: 20px;
}
.dot-item:hover .dot-tips {
  opacity: 1;
  visibility: visible;
}
.dot-tips {
  font-size: 14px;
  color: #fff;
  transition: all 0.3s;
  opacity: 0;
  visibility: hidden;
  background: #ccc;
  height: 60%;
  width: 5em;
  border-radius: 20px;
  position: absolute;
  top: 20%;
  right: -5px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.del {
  height: inherit;
  width: 24px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.del-btn {
  height: 24px;
  width: 24px;
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: all 0.6s;
}
.del-btn:hover {
  background: #ccc;
  cursor: pointer;
}
.del-btn:active {
  background: #aaa;
}
</style>
