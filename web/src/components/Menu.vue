<template>
  <div class="menu">
    <div id="user">
      <div class="avatar" :style="style.avatar">
        <div class="avatar-item" @click="onClickAvatar">
          <svg
            t="1565612738606"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="12411"
            width="20"
            height="20"
          >
            <path
              d="M901.513318 911.112953c0.004093 0.649799 0.096191 1.27504 0.096191 1.925862l-0.291642 0c-1.509377 21.572318-19.298532 38.657438-41.255613 38.657438-21.956058 0-39.746236-17.08512-41.255613-38.657438l-0.717338 0c0.010233-1.033539 0.153496-2.036379 0.153496-3.074012 0-83.854826-33.293267-159.791309-87.139639-215.417211l0.075725-0.077771c-7.416923-7.259334-12.045337-17.35425-12.045337-28.550243 0-22.084994 17.903765-39.990806 39.98876-39.990806 11.745509 0 22.201651 5.157463 29.518291 13.220093 68.333318 69.115124 111.135146 163.556053 112.677269 268.097014 0.067538 0.976234 0.291642 1.909489 0.291642 2.90312C901.609509 910.479527 901.521504 910.784472 901.513318 911.112953zM512.001023 605.455485c-11.245112 0-22.254863-0.920976-33.135678-2.273786-153.508209 16.661472-273.060049 147.567917-273.100982 306.703287 0 0.090051 0.026606 0.173962 0.026606 0.264013 0 0.047072-0.014326 0.092098-0.014326 0.13917 0.024559 0.923022 0.128937 1.824555 0.137123 2.749624l-0.414439 0c-1.511423 21.572318-19.302625 38.657438-41.257659 38.657438-21.958104 0-39.746236-17.08512-41.255613-38.657438l-0.596588 0c0.002047-160.119791 96.668667-297.541627 234.760768-357.451088-67.552535-48.360428-111.725594-127.296221-111.725594-216.709647 0-147.225109 119.350249-266.573311 266.575358-266.573311S778.576381 191.651948 778.576381 338.877057 659.226133 605.455485 512.001023 605.455485zM512.001023 153.960511c-101.926414 0-184.55225 82.625836-184.55225 184.55225s82.625836 184.55225 184.55225 184.55225 184.554296-82.625836 184.554296-184.55225S613.927437 153.960511 512.001023 153.960511z"
              p-id="12412"
              fill="#fff"
            ></path>
          </svg>
        </div>
      </div>
    </div>
    <div id="tool">
      <div class="tool-item">
        <div class="tool-item-btn add" @click="onClickAdd">
          <svg
            t="1565612533223"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="11425"
            width="20"
            height="20"
          >
            <path
              d="M545 479h255v66H545v255h-66V545H224v-66h255V224h66v255z"
              p-id="11426"
              fill="#666"
            ></path>
          </svg>
        </div>
      </div>
      <div class="tool-item">
        <div class="tool-item-btn sync" @click="onClickSync">
          <svg
            t="1565612498288"
            class="icon"
            viewBox="0 0 1302 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="10441"
            width="16"
            height="16"
          >
            <path
              d="M336.473043 508.638609c-13.423304-13.423304-34.949565-13.423304-48.083478 0l-74.050782 74.150956C191.710609 442.323478 237.534609 299.074783 338.142609 198.366609c83.556174-83.656348 194.782609-129.825391 312.987826-129.825392 118.205217 0 229.431652 46.169043 313.266087 129.825392 13.412174 13.423304 34.938435 13.423304 48.072348 0 13.133913-13.434435 13.412174-34.971826 0-48.128C916.057043 53.715478 787.500522 0.278261 651.130435 0.278261c-136.370087 0-264.926609 53.158957-361.338435 149.960348-116.535652 116.669217-169.349565 283.14713-142.514087 445.696l-87.195826-87.296c-13.412174-13.423304-34.927304-13.423304-48.061217 0-13.423304 13.434435-13.423304 34.971826 0 48.128l138.050782 138.206608c6.700522 6.722783 15.36 10.073043 24.030609 10.073044 8.659478 0 17.318957-3.350261 24.041739-10.073044l138.039652-138.206608c13.690435-13.156174 13.690435-34.693565 0.278261-48.128zM1290.24 508.638609l-138.050783-138.206609c-13.412174-13.434435-34.927304-13.434435-48.061217 0L966.077217 508.638609c-13.412174 13.434435-13.412174 34.971826 0 48.128 13.412174 13.423304 34.927304 13.423304 48.061218 0l77.968695-78.057739c9.794783 129.257739-35.205565 254.319304-127.710608 346.64626-83.556174 83.656348-194.782609 129.814261-313.266087 129.814261S421.698783 909.022609 337.864348 825.35513c-13.412174-13.434435-34.938435-13.434435-48.072348 0-13.412174 13.434435-13.412174 34.971826 0 48.128C386.203826 970.006261 514.760348 1023.443478 651.130435 1023.443478c136.370087 0 264.926609-53.158957 361.338435-149.960348 106.473739-106.607304 158.45287-250.412522 147.545043-398.970434l82.16487 82.253913c6.711652 6.711652 15.36 10.073043 24.041739 10.073043 8.648348 0 17.318957-3.361391 24.019478-10.073043 13.423304-13.156174 13.423304-34.693565 0-48.128z"
              fill="#666"
              p-id="10442"
            ></path>
          </svg>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  data() {
    return {
      style: {
        avatar: {}
      }
    };
  },
  mounted() {
    this.setAvatarWidth();
    window.onresize = () => {
      this.setAvatarWidth();
    };
  },
  methods: {
    ...mapActions("note", ["sync", "addNote"]),
    onClickSync() {
      console.log("menu.vue sync");
      this.sync();
    },
    onClickAdd() {
      console.log("add");
      this.addNote();
    },
    setAvatarWidth() {
      this.style.avatar.width =
        this.$el.querySelector(".avatar").clientHeight + "px";
      this.$forceUpdate();
    },
    onClickAvatar() {
      this.$emit("clickAvatar");
    }
  }
};
</script>

<style scoped>
.menu {
  display: flex;
  justify-content: space-between;
}
#user {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
#tool {
  height: 100%;
  /* background: red; */
  display: flex;
  padding: 0 12px;
}

.avatar {
  height: 100%;
  max-height: 54px;
}
.avatar-item {
  cursor: pointer;
  height: calc(100% - 10px);
  width: calc(100% - 10px);
  /* background: radial-gradient(ellipse farthest-side at 5px 5px, #ddd 20%, #ccc); */
  background: #ddd;
  border-radius: 1000px;
  margin: 5px 15px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
  font-weight: 1000;
  transition: background 0.6s;
}
.avatar-item:hover {
  background: #aaa;
  /* background: radial-gradient(ellipse farthest-side at 5px 5px, #ccc 20%, #999); */
}
.avatar-item:active {
  background: #666;
  /* background: radial-gradient(ellipse farthest-side at 5px 5px, #ccc 20%, #999); */
}
.tool-item {
  width: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.tool-item-btn {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 24px;
  width: 24px;
  border-radius: 100px;
  /* width: 40px; */
  transition: all 0.6s;
}
.tool-item-btn:hover {
  background: #ccc;
  cursor: pointer;
}
.tool-item-btn:active {
  background: #999;
}
</style>
