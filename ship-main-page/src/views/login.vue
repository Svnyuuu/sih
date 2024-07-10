<template>
  <div class="page">
    <img class="layer" src="../images/img_0.png" />
<!--    <img class="bg" @click="goToBaiduMap" src="../images/img_1.png" />-->
    <img class="floorBg" src="../images/sys_name.png" />
    <div class="container">
      <div class="wrapper">


<!--        <div class="block-name">-->
<!--          <img class="icon-login-user" src="../images/img_5.png" />-->
<!--          <span class="userName">请输入用户名</span>-->
<!--        </div>-->
<!--        <div class="block-pwd">-->
<!--          <img class="icon-login-pwd" src="../images/img_6.png" />-->
<!--          <span class="userPwd">请输入密码</span>-->
<!--        </div>-->
<!--        <button class="btn-login" type="submit" @click="goToHomePage">-->
<!--          <span class="btn-login-content">登录</span>-->
<!--        </button>-->
        <el-form ref="loginRef" :model="loginForm" :rules="loginRules" class="login-form">
          <div class="groupyi">
            <div class="login-title">
              <img class="strip-left" src="../images/img_3.png" />
              <span class="tag">欢迎登录</span>
              <img class="strip-right" src="../images/img_4.png" />
            </div>
          </div>
          <el-form-item prop="username" style="border-color: #1c84c6">
            <el-input
                v-model="loginForm.username"
                type="text"
                size="large"
                auto-complete="off"
                placeholder="账号"

            >
              <template #prefix><svg-icon icon-class="user" class="el-input__icon input-icon" /></template>
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
                v-model="loginForm.password"
                type="password"
                size="large"
                auto-complete="off"
                placeholder="密码"
                @keyup.enter="handleLogin"
            >
              <template #prefix><svg-icon icon-class="password" class="el-input__icon input-icon" /></template>
            </el-input>
          </el-form-item>
          <el-form-item prop="code" v-if="captchaEnabled">
            <el-input
                v-model="loginForm.code"
                size="large"
                auto-complete="off"
                placeholder="验证码"
                style="width: 63%"
                @keyup.enter="handleLogin"
            >
              <template #prefix><svg-icon icon-class="validCode" class="el-input__icon input-icon" /></template>
            </el-input>
            <div class="login-code">
              <img :src="codeUrl" @click="getCode" class="login-code-img"/>
            </div>
          </el-form-item>
          <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
          <el-form-item style="width:100%;">
            <el-button
                :loading="loading"
                size="large"
                type="primary"
                style="width:100%;"
                @click.prevent="handleLogin"
            >
              <span v-if="!loading">登 录</span>
              <span v-else>登 录 中...</span>
            </el-button>
            <div style="float: right;" v-if="register">
              <router-link class="link-type" :to="'/register'">立即注册</router-link>
            </div>
          </el-form-item>
        </el-form>

        <!-- <form @submit.prevent="login">
          <input v-model="credentials.username" placeholder="请输入用户名">
          <input v-model="credentials.password" placeholder="请输入密码">
        </form> -->
      </div>
    </div>
  </div>
</template>

<script setup>
import { getCodeImg } from "@/api/login";
import Cookies from "js-cookie";
import { encrypt, decrypt } from "@/utils/jsencrypt";
import useUserStore from '@/store/modules/user'

const userStore = useUserStore()
const route = useRoute();
const router = useRouter();
const { proxy } = getCurrentInstance();

const loginForm = ref({
  username: "admin",
  password: "123456",
  rememberMe: false,
  code: "",
  uuid: ""
});

const loginRules = {
  username: [{ required: true, trigger: "blur", message: "请输入您的账号" }],
  password: [{ required: true, trigger: "blur", message: "请输入您的密码" }],
  code: [{ required: true, trigger: "change", message: "请输入验证码" }]
};

const codeUrl = ref("");
const loading = ref(false);
// 验证码开关
const captchaEnabled = ref(false);
// 注册开关
const register = ref(false);
const redirect = ref(undefined);

watch(route, (newRoute) => {
    redirect.value = newRoute.query && newRoute.query.redirect;
}, { immediate: true });

function handleLogin() {
  proxy.$refs.loginRef.validate(valid => {
    if (valid) {
      loading.value = true;
      // 勾选了需要记住密码设置在 cookie 中设置记住用户名和密码
      if (loginForm.value.rememberMe) {
        Cookies.set("username", loginForm.value.username, { expires: 30 });
        Cookies.set("password", encrypt(loginForm.value.password), { expires: 30 });
        Cookies.set("rememberMe", loginForm.value.rememberMe, { expires: 30 });
      } else {
        // 否则移除
        Cookies.remove("username");
        Cookies.remove("password");
        Cookies.remove("rememberMe");
      }
      // 调用action的登录方法
      userStore.login(loginForm.value).then(() => {
        const query = route.query;
        const otherQueryParams = Object.keys(query).reduce((acc, cur) => {
          if (cur !== "redirect") {
            acc[cur] = query[cur];
          }
          return acc;
        }, {});
        router.push({ path: redirect.value || "/", query: otherQueryParams });
      }).catch(() => {
        loading.value = false;
        // 重新获取验证码
        if (captchaEnabled.value) {
          getCode();
        }
      });
    }
  });
}

function getCode() {
  getCodeImg().then(res => {
    captchaEnabled.value = res.captchaEnabled === undefined ? true : res.captchaEnabled;
    if (captchaEnabled.value) {
      codeUrl.value = "data:image/gif;base64," + res.img;
      loginForm.value.uuid = res.uuid;
    }
  });
}

function getCookie() {
  const username = Cookies.get("username");
  const password = Cookies.get("password");
  const rememberMe = Cookies.get("rememberMe");
  loginForm.value = {
    username: username === undefined ? loginForm.value.username : username,
    password: password === undefined ? loginForm.value.password : decrypt(password),
    rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
  };
}

// getCode();
// getCookie();
</script>

<style lang='scss' scoped>

:deep(.el-input__wrapper) {
  background-color: rgba(0,0,0,0);
}

:deep(input:-webkit-autofill) {
  -webkit-text-fill-color: #fff;
  transition: background-color 5000s ease-out 0.5s;
}


:deep(input::-webkit-input-placeholder) {
  color: #fff;
}

:deep(.el-input__inner) {
  background-color: rgba(0, 0, 0, 0) !important;
  color: #fff;
}

.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("@/assets/images/login-background.jpg");
  background-size: cover;
}
.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: transparent;
  width: 450px;
  padding: 5px 20px 5px 35px;
  .el-input {
    height: 55px;
    input {
      height: 40px;

    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 0px;
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.login-code {
  width: 33%;
  height: 40px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
.login-code-img {
  height: 40px;
  padding-left: 12px;
}
.page {
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  width: 100vw;
  height: 100vh;
  font-family: MicrosoftYaHei;
}

.layer {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100vw;
  height: 100%;
}

.bg {
  width: 19.9vw;
  height: 2.6vw;
  position: absolute;
  left: 2.86vw;
  top: 2.14vw;
}

.floorBg {
  width: 45vw;
  height: 7.81vw;
  position: absolute;
  left: 50%;
  top: 7.29vw;
  transform: translateX(-50%);
}

.container {
  position: fixed;
  /* 固定定位，相对于视口 */
  top: 50%;
  /* 垂直居中 */
  left: 50%;
  /* 水平居中 */
  transform: translate(-50%, -50%);
  /* 精确调整到中心位置 */
  width: 30vw;
  /* 宽度为视口宽度的30%，根据需要自行调整 */
  height: auto;
  /* 高度根据内容自动调整，保证登录框内部内容适应 */
  padding: 1.46vw 0.62vw 1.56vw;
  /* 内边距根据需要自行调整 */
  background: url(../images/login_boder.png) no-repeat center center;
  background-size: calc(100% + 2.92vw) calc(100% + 3.12vw);
  /* 让背景图片稍大于容器尺寸 */
  z-index: 100;
  /* 确保它位于其他内容之上 */
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  background-origin: padding-box;
}

.wrapper {
  width: 25.62vw;
  height: 20vw;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-self: center;
  padding: 2.14vw 0.62vw 1.88vw;
  background-image: url(../images/img_10.png);
  background-repeat: no-repeat;
  background-position: 0 0;
  background-size: 100% 100%;
  background-origin: padding-box;
}

.groupyi {
  margin-bottom: 35px;
  display: flex;
  justify-content: space-between;
  align-self: center;
  width: 20.62vw;
}

.login-title {
  display: flex;
  justify-content: space-between;
  align-self: center;
  height: 1.72vw;
  width: 12.66vw;
}

.strip-left {
  width: 6.25vw;
  height: 0.73vw;
  align-self: center;
  margin-top: 0.16vw;
}

.strip-right {
  width: 6.25vw;
  height: 0.73vw;
  align-self: center;
  margin-top: 0.16vw;
}

.tag {
  color: rgba(255, 255, 255, 1);
  font-family: MicrosoftYaHei-Bold;
  font-size: 1.25vw;
  letter-spacing: 0.13vw;
  line-height: 1.72vw;
  align-self: center;
  white-space: nowrap;
  margin: 0 1.3vw;
}

.block-name {
  width: 20.47vw;
  height: 2.71vw;
  display: flex;
  justify-content: flex-start;
  align-self: center;
  margin: 2.6vw 0.05vw 0 0;
  padding: 0.73vw 0.62vw 0.68vw;
  background-image: url(../images/img_11.png);
  background-repeat: no-repeat;
  background-position: 0 0;
  background-size: 100% 100%;
  background-origin: padding-box;
}

.icon-login-user {
  width: 0.94vw;
  height: 1.09vw;
  margin-left: 0.16vw;
  align-self: center;
}

.userName {
  color: rgba(153, 153, 153, 1);
  font-size: 0.94vw;
  line-height: 1.3vw;
  margin-left: 0.99vw;
  align-self: center;
  white-space: nowrap;
}

.block-pwd {
  width: 20.47vw;
  height: 2.71vw;
  display: flex;
  justify-content: flex-start;
  align-self: center;
  margin: 1.2vw 0.05vw 0 0;
  padding: 0.62vw 0.62vw 0.78vw;
  background-image: url(../images/img_12.png);
  background-repeat: no-repeat;
  background-position: 0 0;
  background-size: 100% 100%;
  background-origin: padding-box;
}

.icon-login-pwd {
  width: 0.94vw;
  height: 1.09vw;
  align-self: center;
  margin: 0 0 0.1vw 0.21vw;
}

.userPwd {
  color: rgba(153, 153, 153, 1);
  font-size: 0.94vw;
  line-height: 1.3vw;
  margin-left: 0.89vw;
  align-self: center;
  white-space: nowrap;
}

.btn-login {
  background-color: rgba(0, 128, 255, 1);
  display: flex;
  justify-content: center;
  align-self: center;
  width: 20.47vw;
  margin: 2.34vw 0.05vw 0 0;
  padding: 0.62vw;
  border-radius: 0.21vw;
}

.btn-login-content {
  color: rgba(255, 255, 255, 1);
  font-size: 1.04vw;
  line-height: 1.46vw;
  margin-left: 0.05vw;
  align-self: flex-start;
  max-width: 19.11vw;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
</style>
