<template>
  <div id="app">
    <NoteView id="noteview"/>
  </div>
</template>

<script>
import NoteView from "./views/NoteView.vue";

export default {
  name: "app",
  components: {
    NoteView
  }
};
</script>
<style scoped>
#app {
  height: 100%;
}
#noteview {
  height: 100%;
}
</style>
<style>
html,
body {
  height: 100%;
  margin: 0;
  font-family: Quicksand;
  font-size: 16px;
}
</style>

