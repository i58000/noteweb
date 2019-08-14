<template>
  <div class="modal" :class="clazz" @click="onClickClose">
    <div class="modal-container" @click="onClickContainer">
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    active: String
  },
  data() {
    return {
      // clazz: ""
    };
  },
  computed: {
    clazz() {
      return this.active == "N" ? "" : "modal-active";
    }
  },
  created() {
    // window.addEventListener("keydown", e => {
    //   console.log(e);
    //   this.clazz = "modal-active";
    // });
  },
  methods: {
    onClickClose($event) {
      console.log("onClickClose", $event);
      // $event.stopPropagation();
      this.$emit("close");
    },
    onClickContainer($event) {
      $event.stopPropagation();
    }
  }
};
</script>

<style scoped>
.modal-active {
  /* background: red; */
  opacity: 1 !important;
  visibility: visible !important;
}
.modal {
  transition: all 0.6s;
  opacity: 0;
  visibility: hidden;
  /* position: absolute; */
  top: 0;
  left: 0;
  position: absolute;
  background: #0006;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.modal-container {
  background: radial-gradient(ellipse farthest-side at 0 0, #fff 20%, #ddd);
  /* min-width: 20px;
  min-height: 20px; */
  border-radius: 10px;
  box-shadow: 0 0 20px #0003;
}
</style>
