<template>
  <div class="ship-foundation">
    <div class="head-con">
      <div class="operate">
        <ul class="nav">
          <li @click="jumpPage('home')">
            <el-tooltip effect="dark" content="首页" placement="bottom">
              <!--              <img :src="`/src/assets/foundation/nav_1.png`" alt="" />-->
              <img src="@/assets/foundation/nav_1.png" alt="" />
            </el-tooltip>
          </li>
          <li @click="jumpPage('platform')">
            <el-tooltip effect="dark" content="平台管理" placement="bottom">
              <!--              <img :src="`/src/assets/foundation/nav_2.png`" alt="" />-->
              <img src="@/assets/foundation/nav_2.png" alt="" />
            </el-tooltip>
          </li>
          <li @click="jumpPage('application')">
            <el-tooltip effect="dark" content="应用中心" placement="bottom">
              <!--              <img :src="`/src/assets/foundation/nav_3.png`" alt="" />-->
              <img src="@/assets/foundation/nav_3.png" alt="" />
            </el-tooltip>
          </li>
          <li @click="jumpPage('communication')">
            <el-tooltip effect="dark" content="船岸通信" placement="bottom">
              <!--              <img :src="`/src/assets/foundation/nav_4.png`" alt="" />-->
              <img src="@/assets/foundation/nav_4.png" alt="" />
            </el-tooltip>
          </li>
        </ul>
        <!-- 用户名 -->
        <div class="user-con">
          <img src="@/assets/foundation/user.png" alt="" />
          <span>admin</span>
        </div>
        <span class="line"></span>
        <!-- 退出 -->
        <span class="logout" @click="jumpPage('login')">
  <!--          <img :src="`/src/assets/foundation/logout.png`" alt="" />-->
            <img src="@/assets/foundation/logout.png" alt="" />
          </span>
      </div>
    </div>
  </div>

</template>

<script setup>
import { ElMessageBox } from 'element-plus'
import useAppStore from '@/store/modules/app'
import useUserStore from '@/store/modules/user'
import useSettingsStore from '@/store/modules/settings'
import { useRouter } from 'vue-router'
const appStore = useAppStore()
const userStore = useUserStore()
const settingsStore = useSettingsStore()
const router = useRouter()
const jumpPage = (key) => {
  console.log("key", key);
  if (key === 'communication') {
    router.push({ path: '/sys/index' })
    return
  }

  ElMessageBox.confirm('确定注销并退出系统吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    userStore.logOut().then(() => {
      location.href = '/login';
    })
  }).catch(() => { });
  // router.push({
  //   name: key,
  // })
};
</script>

<style scoped lang="scss">
.ship-foundation {
  * {
    padding: 0;
    margin: 0;
    font-family: MicrosoftYaHei, MicrosoftYaHei;
  }
  //min-width: 1600px;
  width: 100vw;
  height: 90px;
  background: radial-gradient(0% 100% at 49% 50%, #1b3064 0%, #0a213a 100%);
  position: relative;
  overflow: hidden;
  .img-head {
    width: 100%;
    height: 155px;
    position: absolute;
    top: -19px;
    left: 0;
  }

  // 顶栏操作区域
  .head-con {
    width: 100%;
    height: 90px;
    background: url("@/assets/foundation/img_head.png") no-repeat center -30px;
    background-size:100%;
    position: relative;
    .operate {
      float: right;
      display: flex;
      flex-direction: row;
      align-items: center;
      height: 50px;
      .nav {
        display: flex;
        flex-direction: row;
        align-items: center;
        list-style: none;
        li {
          margin-right: 35px;
          cursor: pointer;
          img {
            width: 17px;
          }
        }
      }
      .line {
        width: 1px;
        height: 20px;
        background: #d4d4d4;
        margin: 0 20px;
      }
      .user-con {
        display: flex;
        flex-direction: row;
        align-items: center;
        img {
          width: 100%;
        }
        span {
          font-family: PingFangSC, PingFang SC;
          font-weight: 400;
          font-size: 14px;
          color: #ffffff;
          line-height: 20px;
          padding: 0 5px;
        }
      }
      .logout {
        margin-right: 20px;
        cursor: pointer;
      }
    }

    // 中间标题
    .page-title {
      width: 190px;
      text-align: center;
      line-height: 50px;
      height: 50px;
      background: url("@/assets/foundation/title.png") no-repeat center;
      background-size: 100% 100%;
      position: absolute;
      left: 50%;
      bottom: -72px;
      margin-left: -95px;
      font-size: 24px;
      color: #ffffff;
    }
  }
  .page-title1 {
    width: 190px;
    text-align: center;
    line-height: 50px;
    height: 50px;
    background: url("@/assets/foundation/title.png") no-repeat center;
    background-size: 100% 100%;
    position: absolute;
    left: 50%;
    top: 115px;
    //bottom: -72px;
    margin-left: -95px;
    font-size: 24px;
    color: #ffffff;
  }


}

</style>