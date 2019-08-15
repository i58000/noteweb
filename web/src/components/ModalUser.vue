<template>
  <div class="modal-user">
    <div class="avatar">
      <div class="avatar-item">
        <svg
          t="1565612738606"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="12411"
          width="40"
          height="40"
        >
          <path
            d="M901.513318 911.112953c0.004093 0.649799 0.096191 1.27504 0.096191 1.925862l-0.291642 0c-1.509377 21.572318-19.298532 38.657438-41.255613 38.657438-21.956058 0-39.746236-17.08512-41.255613-38.657438l-0.717338 0c0.010233-1.033539 0.153496-2.036379 0.153496-3.074012 0-83.854826-33.293267-159.791309-87.139639-215.417211l0.075725-0.077771c-7.416923-7.259334-12.045337-17.35425-12.045337-28.550243 0-22.084994 17.903765-39.990806 39.98876-39.990806 11.745509 0 22.201651 5.157463 29.518291 13.220093 68.333318 69.115124 111.135146 163.556053 112.677269 268.097014 0.067538 0.976234 0.291642 1.909489 0.291642 2.90312C901.609509 910.479527 901.521504 910.784472 901.513318 911.112953zM512.001023 605.455485c-11.245112 0-22.254863-0.920976-33.135678-2.273786-153.508209 16.661472-273.060049 147.567917-273.100982 306.703287 0 0.090051 0.026606 0.173962 0.026606 0.264013 0 0.047072-0.014326 0.092098-0.014326 0.13917 0.024559 0.923022 0.128937 1.824555 0.137123 2.749624l-0.414439 0c-1.511423 21.572318-19.302625 38.657438-41.257659 38.657438-21.958104 0-39.746236-17.08512-41.255613-38.657438l-0.596588 0c0.002047-160.119791 96.668667-297.541627 234.760768-357.451088-67.552535-48.360428-111.725594-127.296221-111.725594-216.709647 0-147.225109 119.350249-266.573311 266.575358-266.573311S778.576381 191.651948 778.576381 338.877057 659.226133 605.455485 512.001023 605.455485zM512.001023 153.960511c-101.926414 0-184.55225 82.625836-184.55225 184.55225s82.625836 184.55225 184.55225 184.55225 184.554296-82.625836 184.554296-184.55225S613.927437 153.960511 512.001023 153.960511z"
            p-id="12412"
            fill="#fff"
          />
        </svg>
      </div>
    </div>
    <div v-if="!username" class="container-login">
      <input v-model="input_username" type="text" placeholder="USERNAME" spellcheck="false" />
      <input v-model="input_password" type="password" placeholder="PASSWORD" />
      <button @click="onClickLogin">
        <svg
          t="1565745539306"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="2979"
          width="35"
          height="35"
        >
          <path
            d="M513.738655 127.757266c-170.455921 0-315.035237 111.702933-365.14011 265.675501h77.020154c46.717883-113.351268 158.082117-193.442293 288.119956-193.442293 172.036516 0 311.986946 139.95043 311.986946 311.986946s-139.95043 312.009526-311.986946 312.009526c-130.037839 0-241.379493-80.091025-288.119956-193.464873H148.598545c50.104873 153.995149 194.68419 265.720662 365.14011 265.720661 211.867519 0 384.242734-172.397795 384.242734-384.265314S725.606174 127.757266 513.738655 127.757266z"
            p-id="2980"
            fill="#8a8a8a"
          />
          <path
            d="M414.454686 623.409394l57.465931 57.465931 171.765557-171.720397-182.129746-182.107166-57.465932 57.465931 86.797266 86.842426H126.018611v81.287762H485.242778z"
            p-id="2981"
            fill="#8a8a8a"
          />
        </svg>
      </button>
    </div>
    <div v-else class="container-logout">
      <div class="text">{{username}}</div>
      <button @click="onClickLogout">
        <svg
          t="1565745500843"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="2435"
          width="35"
          height="35"
        >
          <path
            d="M734.547828 680.875325l171.765557-171.720397-182.129747-182.107166-57.465931 57.465931 86.797265 86.842426H388.645821v81.287762h359.224168l-70.788093 70.765513z"
            p-id="2436"
            fill="#8a8a8a"
          />
          <path
            d="M501.92935 823.986946c-172.036516 0-311.986946-139.97301-311.986946-312.009526S329.870254 199.990474 501.92935 199.990474c84.471533 0 160.927189 33.937641 217.015744 88.69398l51.12097-51.09839c-69.230077-67.830121-163.77226-109.828798-268.136714-109.828798-211.867519 0-384.242734 172.352635-384.242735 384.220154s172.375215 384.265314 384.242735 384.265314c108.451422 0 206.290276-45.340507 276.130011-117.889834l-51.053231-51.030651a310.090232 310.090232 0 0 1-225.07678 96.664697z"
            p-id="2437"
            fill="#8a8a8a"
          />
        </svg>
      </button>
    </div>
  </div>
</template>

<script>
// import Modal from "./Modal";
import { mapState, mapActions } from "vuex";

export default {
  // components: { Modal },
  data() {
    return {
      input_username: null,
      input_password: null
    };
  },
  computed: {
    ...mapState("note", {
      username: state => state.username
    })
  },
  methods: {
    ...mapActions("note", ["init", "login", "logout"]),
    onClickLogin() {
      console.log("onClickLogin");
      this.login({
        username: this.input_username,
        password: this.input_password
      })
        .then(() => {
          this.init();
          setTimeout(() => {
            this.$emit("close");
          }, 2000);
        })
        .catch(res => {
          console.log("login fail", res);
        });
    },
    onClickLogout() {
      console.log("onClickLogout");
      this.logout();
    }
  }
};
</script>

<style scoped>
.modal-user {
  width: 400px;
  /* background: red; */
  /* height: 320px; */
  display: flex;
  flex-direction: column;
  margin: 30px 0;
}
.modal-user .avatar {
  /* background: red; */
  display: flex;
  justify-content: center;
  align-items: center;
  height: 120px;
  margin: 15px 0;
}
.modal-user .avatar .avatar-item {
  height: 100px;
  width: 100px;
  background: radial-gradient(ellipse farthest-side at 5px 5px, #ddd 20%, #ccc);
  border-radius: 1000px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
}
.modal-user .container-login {
  /* background: red; */
  height: 200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.modal-user .container-logout {
  /* background: red; */
  height: 140px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
input::-webkit-input-placeholder {
  color: #6666;
}
input {
  font-family: Quicksand;
  font-size: 1.2em;
  color: #666;
  letter-spacing: 0.1em;
  width: 40%;
  text-align: center;
  height: 2em;
  border: none;
  background: transparent;
  border-bottom: 1px solid #bbb;
  outline: none;
  margin: 5px 0;
}
button {
  margin-top: 20px;
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
  background: #ccc;
  cursor: pointer;
}
button:active {
  background: #999;
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
