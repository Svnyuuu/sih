<template>
  <div class="netcdf">
    <div class="receive-head">
      <el-row :gutter="20">
        <el-col :span="3">
          <el-button type="danger" :icon="Delete" @click="handleDelete">删除</el-button>
          <el-button type="primary" :icon="Star" @click="handleStar">收藏</el-button>
        </el-col>
        <el-col :span="6">
          <el-date-picker
              v-model="time"
              type="datetimerange"
              range-separator="To"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
          />
        </el-col>
        <el-col :span="3">
          <el-input v-model="longitude" placeholder="请输入经度"/>
        </el-col>
        <el-col :span="3">
          <el-input v-model="latitude" placeholder="请输入纬度"/>
        </el-col>
        <el-col :span="3">
          <el-input v-model="radius" placeholder="请输入半径"/>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" :icon="Search" @click="NcSearch">查询</el-button>
          <el-button type="info" :icon="Share" @click="HandleReset">重置</el-button>
        </el-col>
      </el-row>
    </div>
    <div class="content">
      <el-table
          ref="multipleTableRef"
          :data="tableData"
          style="width: 100%"
          @selection-change="handleSelectionChange">
        <el-table-column label="时间" prop="datetime"/>
        <el-table-column label="经度" prop="longitude"/>
        <el-table-column label="纬度" prop="latitude"/>
        <el-table-column label="u10" prop="u10"/>
        <el-table-column label="v10" prop="v10"/>
        <el-table-column label="hws" prop="hws"/>
        <el-table-column label="mws" prop="mws"/>
        <el-table-column label="操作" width="180">
          <template #default="{row}">
            <el-button v-if="row.hws=== 1" type="primary" link @click='favorite(row)'>取消收藏</el-button>
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
            @size-change="onSizeChange"
            @current-change="onCurrentChange"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import {Delete, Star, Search, Share} from '@element-plus/icons-vue'
//导入receive.js文件

const background = ref(true)

//查询条件
const time = ref('')
const longitude = ref('')
const latitude=ref('')
const radius = ref('')

//分页条数据模型
const pageNum = ref(1)//当前页
const pageSize = ref(10)//每页条数
const total = ref(100)//总条数
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
  pageNum.value = num
}
//当每页条数发生变化，调用此函数
const onSizeChange = (size) => {
  pageSize.value = size
}

//进入收件箱，列出所有消息，后端自动填充
const tableData = ref([]);
const getNCList = async () => {
  let params = {
    pageNum: pageNum.value,
    pageSize: pageSize.value,
    beginTime: time.value ? moment(time.value[0]).format('YYYY-MM-DD HH:mm:ss') : null,
    endTime: time.value ? moment(time.value[1]).format('YYYY-MM-DD HH:mm:ss') : null,
    longitude: longitude.value ? longitude.value : null,
    latitude: latitude.value ? latitude.value : null,
    radius: radius.value ? radius.value : null,
  }
  const result = await listNC(params);
  total.value = result.data.total;
  tableData.value = result.data.items;
}
getNCList();


//根据条件搜索消息,此外必须检查是否输入查询条件，若没有输入，弹框提示
const NcSearch = () => {
  getNCList();
}


//删除消息
// const DeleteMessageRow = async (row) => {
//   ElMessageBox.confirm(
//       '此操作将消息移入回收站, 是否继续?',
//       '提示',
//       {
//         confirmButtonText: '确定',
//         cancelButtonText: '取消',
//         type: 'warning'
//       }
//   )
//       .then(async () => {
//         console.log('row id=' + row.messageId);
//         let res = await HandleDelete(row.messageId);
//         ElMessage({
//           type: 'success',
//           message: '删除成功!'
//         });
//         //刷新页面
//         getReceiveList();
//       })
//       .catch(() => {
//         ElMessage({
//           type: 'info',
//           message: '已取消删除'
//         });
//       })
// }
//
// //收藏
// const favorite = async (row) => {
//   await handleFavorite(row.messageId, row.favorite);
//   //刷新页面
//   getReceiveList();
// }
//
//
// //显示详细页面
// const detailMessage = (row) => {
//   router.push({path: '/sys/details', query: {data: encodeURIComponent(JSON.stringify(row))}});
// }


</script>

<style scoped>
.netcdf {
  border-radius: 5px;
  background: #fff;
  height: 100%;
  padding: 30px;
}

.content {
  margin-top: 30px;
  height: calc(100% - 90px);
}

.footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 670px;
  margin: 0 auto;
}

</style>