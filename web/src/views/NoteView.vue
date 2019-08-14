<template>
  <div class="noteview">
    <Note class="note"/>
  </div>
</template>

<script>
import Note from "../components/Note";
export default {
  components: {
    Note
  }
};
</script>

<style scoped>
.noteview {
  /* background: green; */
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.note {
  height: 80%;
  width: 80%;
  max-width: 1600px;
  /* background: wheat; */
}
</style>
