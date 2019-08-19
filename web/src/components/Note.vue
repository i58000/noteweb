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

import { mapActions, mapState, mapGetters } from "vuex";

import Util from "../util";

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
      indexDel: -1,
      syncTimeInterval: null,
      syncInputInterval: null
    };
  },
  watch: {
    syncItems() {
      if (!this.syncConfig.onInput) return;
      console.log("time:", new Date().getTime() - this.syncTime);
      let timeout =
        this.syncConfig.inputInterval - (new Date().getTime() - this.syncTime);
      console.log("timeout:", timeout);

      if (
        !this.syncInputInterval &&
        Util.checkSyncReady(this.syncItems, this.syncConfig.inputCount)
      ) {
        this.syncInputInterval = setTimeout(
          () => {
            this.syncInputInterval = null;
            this.sync()
              .then(() => {
                this.$notification.success({
                  message: "自动同步成功",
                  description:
                    new Date(this.syncTime).toLocaleString() +
                    " [ 输入检测同步 ]"
                });
              })
              .catch(e => {
                this.$notification.error({
                  message: "同步失败",
                  description: e
                });
              });
          },
          timeout < 0 ? 0 : timeout
        );
      }
    },
    username() {
      console.log("=======================================", this.username);

      if (!this.username) {
        clearInterval(this.syncTimeInterval);
        this.syncTimeInterval = null;
        this.modalActive = "USER";
      } else {
        if (this.syncConfig.onTimeInterval) {
          this.syncTimeInterval = setInterval(() => {
            this.sync()
              .then(() => {
                this.$notification.success({
                  message: "自动同步成功",
                  description:
                    new Date(this.syncTime).toLocaleString() + " [ 定时同步 ]"
                });
              })
              .catch(e => {
                this.$notification.error({
                  message: "同步失败",
                  description: e
                });
              });
          }, this.syncConfig.timeInterval); // 10min = 600000
        }
      }
    }
  },
  computed: {
    ...mapState("note", {
      username: state => state.username,
      init_flag: state => state.init,
      syncTime: state => state.syncTime,
      syncConfig: state => state.syncConfig
    }),
    ...mapGetters("note", {
      syncItems: "syncItems"
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
    window.onbeforeunload = () => {
      console.log(this.syncItems.length);
      if (this.syncItems.length == 0) {
        // do unload
      } else {
        this.sync();
      }
    };
  },
  methods: {
    ...mapActions("note", ["init", "sync"]),
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
