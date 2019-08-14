<template>
  <div class="underline-container" :class="classC">
    <slot>contain</slot>
    <div class="underline" :class="classU + classActive"></div>
  </div>
</template>

<script>
export default {
  props: {
    active: Boolean,
    position: String,
    hoverable: { type: Boolean, default: true },
    dark: { type: Boolean, default: true }
  },
  data() {
    return {
      classU: null,
      classC: null
    };
  },
  computed: {
    classActive() {
      return this.active ? "underline-active " : " ";
    }
  },
  created() {
    this.classC = this.hoverable
      ? "underline-container-hoverable "
      : "underline-container ";
    this.classU =
      (this.position == "top" ? "underline-top " : " ") +
      (this.dark ? "underline-dark " : " ");
  }
};
</script>

<style scoped>
.underline-container {
  height: 100%;
  width: 100%;
  position: relative;
}
.underline {
  position: absolute;
  bottom: 0;
  background: #bbb;
  height: 1px;
  width: 0%;
  opacity: 1;
  transition: width 0.3s;
}
.underline-container-hoverable:hover > .underline {
  width: 100%;
  opacity: 1;
}
.underline-container-hoverable:hover {
  cursor: pointer;
}
.underline-active {
  width: 100%;
  opacity: 1;
}
.underline-top {
  top: 0;
}
/* .underline-dark {
  background: #fff;
} */
</style>
