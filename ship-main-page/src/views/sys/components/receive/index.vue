<template>
  <div class="receive">
    <div class="receive-head">
      <el-row :gutter="20">
        <el-col :span="4">
          <el-button type="danger" :icon="Delete" @click="BatchDelete()">删除</el-button>
          <el-button type="primary" :icon="Star" @click="handleFavorite">收藏</el-button>
        </el-col>
        <el-col :span="dynamicSpan">
          <el-date-picker
              v-model="time"
              type="datetimerange"
              :disabled-hours="disabledHours"
              range-separator="To"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              :disablesDate="disablesDate"
              unlink-panels="true"
          />
        </el-col>
        <el-col :span="6">
          <el-input v-model="title" placeholder="请输入邮件标题"/>
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
        <el-table-column type="selection" width="55"/>
        <el-table-column label="标题" prop="subject" width="700">
          <template #default="{ row }">
            <div class="row-detail">
              <img style="width: 25px; height: 20px; margin-right: 10px;" v-if="row.email === '1'"
                   src="@/assets/images/未读@2x.png"/>
              <img style="width: 25px; height: 20px; margin-right: 10px;" v-else-if="row.email === '2'"
                   src="@/assets/images/形状@2x.png"/>
              <img style="width: 25px; height: 20px; margin-right: 10px;" v-else
                   src="@/assets/images/形状备份 6@2x.png"/>
              <div>
                <el-button type="primary" link @click="detailMessage(row)">{{ row.subject }}</el-button>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="附件">
          <template #default="{ row }">
            <img style="width: 20px;cursor: pointer;" src="@/assets/images/附件@2x.png" alt="" @click="sendUrlToBackend(row)">
          </template>
        </el-table-column>

        <el-table-column label="船名" prop="receiveBoat"/>
        <el-table-column label="发件人" prop="sender"/>
        <el-table-column label="收件时间" prop="receiveTime"/>
        <el-table-column label="发件时间" prop="sendTime"/>
        <el-table-column label="操作" width="180">
          <template #default="{row}">
            <el-button v-if="row.favorite=== 1" type="primary" link @click='favorite(row)'>取消收藏</el-button>
            <el-button v-else type="primary" link @click='favorite(row)'>收藏</el-button>
            <el-button type="primary" link @click="DeleteMessageRow(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
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
  </div>
</template>

<script setup>
import {ref, reactive} from 'vue'
import {Delete, Star, Search, Share} from '@element-plus/icons-vue'
import moment from 'moment'
//导入receive.js文件
import {listReceive, HandleDelete, handleFavorite, getUrl, BaDelete} from '@/api/sys/receive.js'
import {ElMessageBox, ElMessage} from 'element-plus'
import {useRouter} from 'vue-router'

const router = useRouter()

const background = ref(true)

//data 内含 tableData 和 multipleSelection
const data = reactive({
  tableData: [],
  multipleSelection: []
})
// const multipleSelection = ref([]);
// 选择数据
const handleSelectionChange = (val) => {
  data.multipleSelection = val;
}
// //批量删除
// const BatchDelete = () => {
//   const ids = data.multipleSelection.map(item => item.id)
//   if (data.multipleSelection.length === 0) {
//     ElMessage.warning('请选择要删除的数据')
//     return
//   }
//   ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗?', '删除确认', {type: 'warning'})

//     .then(async () => {
//         ids = data.multipleSelection.map(item => item.id),
//         console.log('row ids=' + ids);
//         let res = await BaDelete(ids.join(',')); 
//         ElMessage({
//           type: 'success',
//           message: '删除成功!'
//         })
//     .catch(err => {
//       // 添加错误处理逻辑
//       ElMessage.error('删除时发生错误: ' + err.message)
//     })
//   })
//   .catch(() => {
//     // 处理取消操作的逻辑
//     ElMessage.info('取消操作')
//   })
// }
const BatchDelete = async () => {
  const ids = data.multipleSelection.map(item => item.id);
  if (data.multipleSelection.length === 0) {
    ElMessage.warning('请选择要删除的数据');
    return;
  }
  try {
    await ElMessageBox.confirm('删除数据后移动到回收站', '删除确认', { type: 'warning' });
    console.log('row ids=' + ids);
    let res = await BaDelete(ids.join(',')); // 确保ids是以逗号分隔的字符串
    ElMessage({
      type: 'success',
      message: '删除成功!'
    });
  } catch (err) {
    if (err && err.message) {
      // 用户取消操作会进入这里
      ElMessage.info('取消操作');
    } else {
      // 其他错误，如网络请求错误
      ElMessage.error('删除时发生错误: ' + err.message);
    }
  }
};




const dynamicSpan = () => {
      // 假设页面宽度为1920时，span值为5
      const baseWidth = 1920;
      const baseSpan = 5;
      const currentWidth = window.innerWidth;
      // 根据当前页面宽度动态计算span值
      return Math.round((currentWidth / baseWidth) * baseSpan);
    }
  
//重置
const HandleReset = () => {
  time.value = null;
  title.value = null;
}

//时间和标题
const time = ref('')
const title = ref('')

//分页条数据模型
const pageNum = ref(1)//当前页
const pageSize = ref(10)//每页条数
const total = ref(100)//总条数
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
  pageNum.value = num
  getReceiveList();
}

//当每页条数发生变化，调用此函数
const onSizeChange = (size) => {
  pageSize.value = size
  getReceiveList();
}

//进入收件箱，列出所有消息，后端自动填充
const tableData = ref([]);
const getReceiveList = async () => {
  console.log("getReceiveList******");
  let params = {
    pageNum: pageNum.value,
    pageSize: pageSize.value,
    beginTime: time.value ? moment(time.value[0]).format('YYYY-MM-DD HH:mm:ss') : null,
    endTime: time.value ? moment(time.value[1]).format('YYYY-MM-DD HH:mm:ss') : null,
    title: title.value ? title.value : null,
    status: 1
  }
  const result = await listReceive(params);
  total.value = result.data.total;
  tableData.value = result.data.items;
}
getReceiveList();


//根据条件搜索消息,此外必须检查是否输入查询条件，若没有输入，弹框提示
const MessageSearch = () => {
  getReceiveList();
}


//删除消息
const DeleteMessageRow = async (row) => {
  ElMessageBox.confirm(
      '此操作将消息移入回收站, 是否继续?',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
  )
      .then(async () => {
        console.log('row id=' + row.messageId);
        let res = await HandleDelete(row.messageId);
        ElMessage({
          type: 'success',
          message: '删除成功!'
        });
        //刷新页面
        getReceiveList();
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '已取消删除'
        });
      })
}

//收藏
const favorite = async (row) => {
  await handleFavorite(row.messageId, row.favorite);
  //刷新页面
  getReceiveList();
}
//传url
const sendUrlToBackend = (row) => {
  getUrl(row);
}


 //显示详细页面
 const detailMessage = (row) => {
   router.push({path: '/sys/details', query: {data: encodeURIComponent(JSON.stringify(row))}});
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