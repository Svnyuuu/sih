<template>
    <div class="receive">
      <div class="receive-head">
        <el-row :gutter="20">
          <el-col :span="4">
            <el-button type="danger" :icon="Delete">删除</el-button>
            <el-button type="primary" :icon="Star">收藏</el-button>
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
          <el-table-column label="船名" prop="receiveBoat" />
          <el-table-column label="发件人" prop="sender" />
          <el-table-column label="发件时间" prop="sendTime" />
          <el-table-column label="操作" width="180">
            <template #default="{ row }">
              <el-button v-if="row.favorite === 1" type="primary" link @click='favorite(row)'>取消收藏</el-button>
              <el-button v-else type="primary" link @click='favorite(row)'>收藏</el-button>
              <el-button type="primary" link @click='DeleteMessageRow(row)'>删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="footer">
        <el-pagination background 
              v-model:current-page="pageNum"
	      v-model:page-size="pageSize"
	      :page-sizes="[10, 20, 30, 40]"
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
  import {listReceive,HandleDelete,handleFavorite} from '@/api/sys/receive.js'
  import {ElMessageBox,ElMessage} from 'element-plus'


  const time=ref('')
  const title=ref('')

  const pageNum=ref(1)
  const pageSize=ref(10)
  const total=ref(100)
  const tableData = ref([])


	const onSizeChange=(size)=>{

		pageSize.value=size;
	}

	const onCurrentChange=(num)=>{
		pageNum.value=num;
	}

  //列出已发送消息
  const getSendMessages=async()=>{
    let params={
     pageNum:pageNum.value,
     pageSize:pageSize.value,
     beginTime:time.value? moment(time.value[0]).format('YYYY-MM-DD HH:mm:ss'):null,
     endTime:time.value?moment(time.value[1]).format('YYYY-MM-DD HH:mm:ss'):null,
     title:title.value?title.value:null,
     status:2
  }
  const result = await listReceive(params);
  total.value=result.data.total;
  tableData.value = result.data.items;   
  }
getSendMessages();


//根据条件搜索消息,此外必须检查是否输入查询条件，若没有输入，弹框提示
const MessageSearch = () => {
  getSendMessages();
}

//重置
const HandleReset=()=>{
    time.value=null;
    title.value=null;
}

//删除消息
const DeleteMessageRow=(row)=>{
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
  let res = await HandleDelete(row.messageId);
  ElMessage({
    type: 'success',
    message: '删除成功!'
  });
  //刷新页面
  getSendMessages();
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
  getSendMessages();
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