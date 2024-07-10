<template>
    <div class="receive">
      <div class="receive-head">
        <el-row :gutter="20">
          <el-col :span="4">
            <el-button type="danger" :icon="Delete">彻底删除</el-button>
          </el-col>
          <el-col :span="5">
            <el-date-picker
              v-model="time"
              type="datetimerange"
              range-separator="-"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
            />
          </el-col>
          <el-col :span="6">
            <el-input v-model="title" placeholder="请输入邮件标题" />
          </el-col>
          <el-col :span="4">
            <el-button type="primary" :icon="Search" @click="MessageSearch">查询</el-button>
            <el-button type="info" :icon="Share" @click="HandleReset">重置</el-button>
          </el-col>
        </el-row>
      </div>
      <div class="content">
        <el-table
          ref="multipleTableRef"
          :data="tableData"
          style="width: 100%"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="55" />
          <el-table-column label="标题" prop="subject" width="700">
          </el-table-column>
          <el-table-column label="附件">
            <template #default="{ row }">
              <img style="width: 20px;" src="@/assets/images/附件@2x.png" alt="">
            </template>
          </el-table-column>
          <el-table-column label="发件人" prop="sender" />
          <el-table-column label="发件时间" prop="sendTime" />
          <el-table-column label="操作" width="180">
            <template #default="{ row }">
              <el-button v-if="row.favorite === 1" type="primary" link @click='favorite(row)'>取消收藏</el-button>
              <el-button v-else type="primary" link @click='favorite(row)'>收藏</el-button>
              <el-button type="primary" link @click="DeleteMessageRow(row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="footer">
	<el-pagination
	      v-model:current-page="pageNum"
	      v-model:page-size="pageSize"
	      :page-sizes="[10, 20, 30, 40]"
	      :background="background"
	      layout="total, sizes, prev, pager, next, jumper"
	      :total="total"
	      :size='large'
	      @size-change="onSizeChange"
	      @current-change="onCurrentChange"
	    />
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, reactive } from 'vue'
  import { Delete, Star, Search, Share } from '@element-plus/icons-vue'
import moment from 'moment'
//导入receive.js文件
import { listReceive, HandleForeverDelete, handleFavorite } from '@/api/sys/receive.js'
import {ElMessageBox,ElMessage} from 'element-plus'


const background = ref(true)


//重置
const HandleReset=()=>{
    time.value=null;
    title.value=null;
}

//时间和标题
const time = ref('')
const title=ref('')

//分页条数据模型
const pageNum=ref(1)//当前页
const pageSize=ref(10)//每页条数
const total=ref(100)//总条数
//当前页码发生变化，调用此函数
const onCurrentChange=(num)=>{
   pageNum.value=num
}
//当每页条数发生变化，调用此函数
const onSizeChange=(size)=>{
   pageSize.value=size
}

//进入回收站，列出所有消息，后端自动填充
const tableData = ref([]);
const getRecycleList = async () => {
  console.log("getRecycleList*****");
  let params={
     pageNum:pageNum.value,
     pageSize:pageSize.value,
     beginTime:time.value? moment(time.value[0]).format('YYYY-MM-DD HH:mm:ss'):null,
     endTime:time.value?moment(time.value[1]).format('YYYY-MM-DD HH:mm:ss'):null,
     title:title.value?title.value:null,
     status:4
  }
  const result = await listReceive(params);
  total.value=result.data.total;
  tableData.value = result.data.items;
}
getRecycleList();


//根据条件搜索消息,此外必须检查是否输入查询条件，若没有输入，弹框提示
const MessageSearch = () => {
  getRecycleList();
}


//删除消息
const DeleteMessageRow = async (row) => {
  ElMessageBox.confirm(
  '此操作将永久删除该消息, 是否继续?',
  '提示',
  {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }
)
  .then(async() => {
  console.log('row id='+row.messageId);
  let res = await HandleForeverDelete(row.messageId);
  ElMessage({
    type: 'success',
    message: '删除成功!'
  });
  //刷新页面
  getRecycleList();
})
  .catch(() => {
  ElMessage({
  type: 'info',
  message: '已取消删除'
});
})
}

//收藏
const favorite=async(row)=>{
  await handleFavorite(row.messageId,row.favorite);
  //刷新页面
  getRecycleList();
}
  </script>
  
  <style scoped>
  .receive {
    border-radius: 5px;
    background: #fff;
    height: 100%;
    padding: 30px;
  }
  
  .content {
    margin-top: 30px;
    height: calc(100% - 90px);
  }
  
  .row-detail {
    display: flex;
    align-items: center;
  }
  
  .footer {
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 670px;
    margin: 0 auto;
  }
  </style>