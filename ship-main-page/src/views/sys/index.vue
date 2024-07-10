<template>
  <el-container class="container">
    <el-header style="padding: 0; height: 89px !important;">
      <topbar style="background: #142d72;" />
    </el-header>
    <el-container>
      <el-aside width="300px" class="aside">
        <div class="menu">
          <div
              class="menu-item"
              :class="{ active: tabsValue === item.type }"
              @click="menuChange(item)"
              v-for="(item, i) in menuList"
              :key="i"
            >
            <el-image class="img" :src="item.icon" />
            <div class="label">{{ item.label }}</div>
          </div>
        </div>
      </el-aside>
      <el-main class="main-container">
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router' 
import topbar from '@/components/topbar/index'
import 收件箱 from '@/assets/images/收件箱@2x.png'
import 新建邮件 from '@/assets/images/新建邮件@2x.png'
import 已发送 from '@/assets/images/已发送@2x.png'
import 已回收 from '@/assets/images/回收站@2x.png'
import 收藏夹 from '@/assets/images/收藏夹@2x.png'
import 草稿箱 from '@/assets/images/file.png'

const router = useRouter()
const tabsValue = ref(1)
const menuList = reactive([
  {
    type: 1,
    icon: 收件箱,
    label: '收件箱',
    url: '/sys/index'
  },
  {
    type: 2,
    icon: 新建邮件,
    label: '新建邮件',
    url: '/sys/mail'
  },
  {
    type: 3,
    icon: 已发送,
    label: '已发送',
    url: '/sys/send'
  },
  {
    type: 4,
    icon: 已回收,
    label: '回收站',
    url: '/sys/reback'
  },
  {
    type: 5,
    icon: 收藏夹,
    label: '收藏夹',
    url: '/sys/collection'
  },
  {
    type: 6,
    icon: 草稿箱,
    label: '草稿箱',
    url: '/sys/draft'
  },
  {
    type: 7,
    icon: 草稿箱,
    label: 'details',
    url: '/sys/details'
  },
  {
    type: 8,
    icon: 草稿箱,
    label: '气象数据查询',
    url: '/sys/netcdf'
  },
  {
    type: 9,
    icon: 草稿箱,
    label: '气象数据解析',
    url: '/sys/parsenc'
  }
])

const menuChange = (options) => {
  tabsValue.value = options.type;
  router.push({ path: options.url });
}
</script>

<style scoped>
.container {
  width: 100%;
  height: 100%;
}

.main-container {
  width: calc(100% - 300px);
  padding: 20px;
  background: #e6e4e5;
  margin: 0 !important;
}

.aside {
  margin: 0;
  background-image: url('@/assets/images/侧边栏@2x.png');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  padding: 0;
}

.img {
  width: 35px;
  height: 32px;
  margin-right: 20px;
}

.label {
  color: #fff;
  font-size: 16px;
  width: 100px;
}

.menu {
  width: 100%;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
}

.menu-item {
  cursor: pointer;
  height: 70px;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.menu-item.active {
  background: #3b7cff;
}
</style>