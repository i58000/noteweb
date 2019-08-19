<template>
  <div class="modal-del">
    <div class="text">是否删除：{{ noteList[index]?noteList[index].title:"deleted" }}</div>
    <div class="button-container">
      <!-- <button>a</button> -->
      <button
        :disabled="deleting"
        @click="onclickBtn"
        @mouseenter="mouseenter"
        @mouseleave="mouseleave"
        :style="style"
      >
        <svg
          t="1565791863225"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="5551"
          width="30"
          height="30"
        >
          <path
            d="M178.016 946.016q-0.992 0-3.008-0.992l0.992 0.512 2.016 0.512z m-8-4q-3.008-2.016-4.992-3.008 2.016 0.992 4.992 3.008z m780.48-738.528q-10.496-10.496-26.496-10.496h-184V134.976q0-28.992-20.992-49.504t-50.016-20.512h-314.016q-30.016 0-50.496 20.512t-20.512 49.504v58.016H98.976q-15.008 0-25.504 10.496t-10.496 24.992 10.496 24.992 25.504 10.496h31.008v623.008q0 28.992 20.992 49.504t50.016 20.512h632q28.992 0 50.016-20.512t20.992-49.504V263.968h20q15.008 0 26.016-10.496t11.008-24.992-10.496-24.992zM354.016 136h315.008v56.992h-315.008V136z m478.976 751.008H200.992V264h632v623.008zM511.488 384q-14.496 0-24.992 10.496t-10.496 25.504v334.016q0 15.008 10.496 25.504t24.992 10.496 24.992-10.496 10.496-25.504V420q0-15.008-10.496-25.504T511.488 384z m-194.496 0q-15.008 0-25.504 10.496t-10.496 25.504v334.016q0 15.008 10.496 25.504t25.504 10.496 25.504-10.496 10.496-25.504V420q0-15.008-10.496-25.504T316.992 384zM704 384q-15.008 0-25.504 10.496t-10.496 25.504v334.016q0 15.008 10.496 25.504t25.504 10.496 25.504-10.496 10.496-25.504V420q0-15.008-10.496-25.504T704 384z"
            p-id="5552"
            :fill="iconColor"
          ></path>
        </svg>
      </button>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  props: {
    index: Number
  },
  data() {
    return {
      iconColor: "#888",
      deleting: false,
      style: {
        // background: "linear-gradient(to bottom, #0001, #fff0)" //"rgb(194, 100, 100)"
      }
    };
  },
  watch: {
    deleting() {
      if (this.deleting) {
        this.style.background = "rgb(194, 100, 100)";
      } else {
        this.style.background = "linear-gradient(to bottom, #0001, #fff0)";
      }
    }
  },
  computed: {
    ...mapState("note", {
      noteList: state => state.noteList
    })
  },
  methods: {
    ...mapActions("note", ["delNote"]),
    mouseenter() {
      this.iconColor = "#fff";
    },
    mouseleave() {
      this.iconColor = "#888";
    },
    onclickBtn() {
      this.deleting = true;
      this.delNote(this.index)
        .then(() => {
          console.log("ModelDel.vue onclickBtn")
          this.deleting = false;
          this.$emit("close");
        })
        .catch(() => {
          console.log("catch");
        });
    }
  }
};
</script>

<style scoped>
.modal-del {
  width: 400px;
  display: flex;
  flex-direction: column;
  margin: 30px 0;
}
.button-container {
  display: flex;
  justify-content: center;
}
button {
  /* margin-top: 20px; */
  margin: 0 10px;
  border: none;
  outline: none;
  height: 50px;
  width: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 100px;
  transition: all 0.6s;
  padding: 0;
  background: linear-gradient(to bottom, #0001, #fff0);
}
button:hover {
  background: rgb(194, 100, 100);
  cursor: pointer;
}
button:active {
  background: rgb(165, 52, 52);
}
.text {
  font-family: Quicksand;
  font-size: 1.2em;
  color: #666;
  letter-spacing: 0.1em;
  text-align: center;
  height: 2em;
  margin: 5px 0;
}
</style>
