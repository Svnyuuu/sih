<template>
  <div class="details">
    <div class="details-head">
      <el-row :gutter="20">  
        <el-col :span="2">
          <el-button type="plain" >返回</el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="plain">回复</el-button>
          <el-button type="plain">转发</el-button>
          <el-button type="danger">删除</el-button>
          <el-button type="primary">确认</el-button>
        </el-col>
      </el-row>
    </div>
    
    <div class="email-page">
    <div class="header">
      <div class="subject">{{decodedData.subject}}</div>
      <div class="sender">
        <span class="grayfont">发件人：</span>
        <a href="mailto:zahngsisi@163.com" class="bluebox">{{decodedData.sender}}<spans class="grayfont">&ltzahngsisi@163.com></spans></a>
      </div>
      <div class="recipient">
        <span class="grayfont">收件人：</span>
        <a href="mailto:zhangsisi@163.com" class="bluebox">{{decodedData.receiver}}<spans class="grayfont">&ltzahngsisi@163.com></spans></a>
      </div>
      <div class="date">
        <span class="grayfont">时间：{{decodedData.receiveTime}}</span>
      </div>
      <div class="attachment">
        <span class="grayfont">附件：</span>
        1个
        <span class="grayfont">（【icon】开会需要文档.pdf）</span>
        <a href="#" class="bluefont">查看附件</a>
      </div>
    </div>

    <div class="content">
      <h3 style="text-align: center;">{{decodedData.content}}</h3>     
    </div>   
    <div class="attachment-section">
  <div class="box">
    普通附件（1个）
  </div>
  <div class="box">
    <a href="{{decodedData.attachment}}" class="attachment-link">
      <img src="path/to/pdf-icon.png" alt="PDF icon" />
      开会需要文档.pdf (314.23k)
      <div>
        
        <a href="#" class="bluefont">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下载&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
        <a href="#" class="bluefont">预览</a>
      </div>
    </a>

  </div>
</div>
  </div>
  </div>

</template>

<script setup>
import { ref, reactive } from 'vue'
import { Delete, Star, Search, Share } from '@element-plus/icons-vue'
import { useRoute } from 'vue-router' 
const route = useRoute()


//query
let userId=route.query.id;
console.log('id='+userId);
const decodedData = JSON.parse(decodeURIComponent(route.query.data));

console.log('decodedData='+decodedData.subject);


const options = reactive([
  {
    label: '10条',
    value: 10
  },
  {
    label: '20条',
    value: 20
  },
  {
    label: '30条',
    value: 30
  },
])
const dataParams = ref({
  date: [],
  title: '',
  size: 10
})

// const tableData = ref([
// {      title: '这里是邮件标题这里是邮件标题这里是邮件标题',
//       sender: '张宇旁 <zhangsi@163.com>',
//       receiver: '项目开发项目组 <zhangsi@163.com>',
//       time: '2024年04月08日 12:00 (星期一)',
//       attachment: '开发需求文档.pdf',
//       emailBody: `船舶电子电气工程专业指挥活动范围的自动化要求，熟练掌握电气技术、电子技术（包括电力电子、通讯电子）、控制技术、计算机技术及其网络技术等先进知识，满足国际海事组织STCW国际公约中规定的“电气、电子和控制工程”...`,
    
// }
// ])
</script>

<style scoped>
.box {
  border: 1px solid lightgray;
  padding: 10px;
  margin: 0;
}
.details {
  border-radius: 20px;
  background: #fff;
  height: 100%;
  padding: 30px;
}

.content {
  margin-top: 30px;
  height: calc(100% - 90px);
}
.bluebox {
  /* 圆角淡蓝色容器 */
  border-radius: 5px;
  background: #f0f5ff;
  padding: 10px;
  margin-top: 10px;
}
.row-detail {
  display: flex;
  align-items: center;
}

.email-page {
  font-family: Arial, sans-serif;
  padding: 20px;
}

.header {
  border-bottom: 1px solid #ccc;
  padding-bottom: 10px;
  margin-bottom: 10px;
}

.subject {
  font-size: 20px;
  font-weight: bold;
  height: 40px;
}
.grayfont {
  color: #666;
}
.bluefont {
  color: #1890ff;
}

.sender, .recipient, .date, .attachment {
  margin-top: 5px;
  /* 设置容器高度 */
  height: 40px;
}

.content {
  margin-top: 20px;
}

h2 {
  font-size: 18px;
  font-weight: bold;
}

.attachment-section {
  margin-top: 20px;
}

.attachment-link {
  display: flex;
  align-items: center;
}

.attachment-link img {
  margin-right: 10px;
}
</style>