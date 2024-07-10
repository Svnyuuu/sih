<template>
  <div class="page">
    <img class="layer" src="../images/img_0.png" />
    <img class="bg" @click="goToStarMap" src="../images/img_1.png" />
    <img class="bg" @click="goToBaiduMap" src="../images/img_1.png" />
    <img class="floorBg" src="../images/sys_name.png" />
    <div class="container">
      <div class="wrapper">
        <div class="groupyi">
          <div class="login-title">
            <img class="strip-left" src="../images/img_3.png" />
            <span class="tag">欢迎登录</span>
            <img class="strip-right" src="../images/img_4.png" />
          </div>
        </div>

        <div class="block-name">
          <img class="icon-login-user" src="../images/img_5.png" />
          <span class="userName">请输入用户名</span>
        </div>
        <div class="block-pwd">
          <img class="icon-login-pwd" src="../images/img_6.png" />
          <span class="userPwd">请输入密码</span>
        </div>
        <button class="btn-login" type="submit" @click="goToHomePage">
          <span class="btn-login-content">登录</span>
        </button>

        <!-- <form @submit.prevent="login">
          <input v-model="credentials.username" placeholder="请输入用户名">
          <input v-model="credentials.password" placeholder="请输入密码">
        </form> -->
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
import { useRouter } from "vue-router";
import { getCodeImg } from "@/api/login";
import Cookies from "js-cookie";
import { encrypt, decrypt } from "@/utils/jsencrypt";
import useUserStore from '@/store/modules/user'

export default {

  components: {
  },
  data() {
    return {
      constants: {},
      credentials: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    async login() {
      try {
        const response = await axios.post('http://localhost:8081/api/personnel/name/admin', this.credentials);
        if (response.status == 200) {
          this.$router.push({ name: 'mainpage' });
        }

      } catch (error) {
        console.error('Login error', error);
      }
    }
  },
  setup() {
    const router = useRouter();
    const goToHomePage = () => {
      console.log('router', router)
      // setUserId(1)
      // const roleList = []
      // const role = 'admin'
      // const role1 = 'user'
      // roleList.push(role)
      // roleList.push(role1)
      document.cookie="Role-List=[admin]"
      document.cookie="User-Id=15675655"
      // setRoleList(roleList)
      router.push({
        name: "homepage",
      });
    };
    const goToStarMap = () => {
      router.push({
        name: "starmap",
      });
      // return new Promise((resolve, reject) => {
      //   login(1, 1, 1, 1).then(res => {
      //     let data = res.data
      //     setToken(data.access_token)
      //     this.token = data.access_token
      //     resolve()
      //   }).catch(error => {
      //     reject(error)
      //   })
      // })

    };
    const goToBaiduMap = () => {
      router.push({
        name: "baidumap",
      });
    };
    return {
      goToHomePage,
      goToStarMap,
      goToBaiduMap,
    };
  }
}
</script>

<style scoped src="../css/login.css" />
