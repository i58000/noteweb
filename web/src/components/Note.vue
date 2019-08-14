<template>
  <div class="note">
    <div class="note-container">
      <div class="aside">
        <div v-if="username" class="aside-container">
          <Menu id="menu" @clickAvatar="modalActive = 'USER'" />
          <List id="list" @clickDel="showModalDel" />
        </div>
        <div v-else-if="!init_flag" class="loading-container">
          <div class="box">
            <div class="loading loader-13"></div>
          </div>
        </div>
      </div>
      <div class="main">
        <div v-if="username" class="main-container">
          <Title id="title" />
          <Editor id="editor" />
        </div>
        <div v-else-if="!init_flag" class="loading-container">
          <div class="box">
            <div class="loading loader-13"></div>
          </div>
        </div>
      </div>
    </div>
    <Modal :active="modalActive" @close="onClickCloseModal">
      <ModalUser v-if="modalActive == 'USER'" @close="modalActive = 'N'"></ModalUser>
      <ModalDel v-else-if="modalActive == 'DEL'" @close="modalActive = 'N'" :index="indexDel"></ModalDel>
    </Modal>
  </div>
</template>

<script>
import Menu from "./Menu";
import List from "./List";
import Title from "./Title";
import Editor from "./Editor";
import Modal from "./Modal";
import ModalUser from "./ModalUser";
import ModalDel from "./ModalDel";

import { mapActions, mapState } from "vuex";

export default {
  components: {
    Menu,
    List,
    Title,
    Editor,
    Modal,
    ModalUser,
    ModalDel
  },
  data() {
    return {
      modalActive: "N",
      indexDel: -1
    };
  },
  watch: {
    // init_flag() {
    //   console.log("++++++++++++++++++++++++++++++");
    // }
  },
  computed: {
    ...mapState("note", {
      username: state => state.username,
      init_flag: state => state.init
    })
  },
  created() {
    this.init().then(username => {
      console.log("++++++++++++++++++++++++++++++", username);
      // 未登录
      if (!username) {
        this.modalActive = "USER";
      }
    });
  },
  methods: {
    ...mapActions("note", ["init"]),
    onClickCloseModal() {
      if (this.username) {
        this.modalActive = "N";
      }
    },
    showModalDel(index) {
      console.log("showModalDel", index);
      this.indexDel = index;
      this.modalActive = "DEL";
    }
  }
};
</script>

<style scoped>
.note-container {
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  box-shadow: 0 0 20px #0003;
  border-radius: 10px;
}
.aside {
  display: flex;
  flex-wrap: wrap;
  border-radius: 10px 0 0 10px;
  background: radial-gradient(ellipse farthest-side at 0 0, #fff 20%, #eee);
  height: 100%;
  width: 20%;
  position: relative;
}
.aside-container {
  height: 100%;
  width: 100%;
}
.main {
  width: 80%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  border-radius: 0 10px 10px 0;
  position: relative;
}
.main-container {
  height: 100%;
  width: 100%;
}
#menu {
  height: 10%;
  width: 100%;
  /* width: calc(20% - 40px); */
}
#list {
  height: 90%;
  width: 100%;
  /* width: calc(20% - 40px); */
}
#title {
  height: 10%;
  /* width: 100%; */
  width: calc(100%-40px);
  min-height: 40px;
  max-height: 60px;
}
#editor {
  margin-top: -1px;
  height: calc(100% - 10%);
  /* width: 100%; */
  width: calc(100%-40px);
  max-height: calc(100% - 40px);
  min-height: calc(100% - 60px);
}
</style>
