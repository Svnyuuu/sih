<template>
  <div class="ship-foundation">
    <!-- 顶栏操作区域 -->
<!--    <div class="head-con">-->
<!--      <div class="operate">-->
<!--        <ul class="nav">-->
<!--          <li @click="jumpPage('home')">-->
<!--            <el-tooltip effect="dark" content="首页" placement="bottom">-->
<!--              &lt;!&ndash;              <img :src="`/src/assets/foundation/nav_1.png`" alt="" />&ndash;&gt;-->
<!--              <img src="@/assets/foundation/nav_1.png" alt="" />-->
<!--            </el-tooltip>-->
<!--          </li>-->
<!--          <li @click="jumpPage('platform')">-->
<!--            <el-tooltip effect="dark" content="平台管理" placement="bottom">-->
<!--              &lt;!&ndash;              <img :src="`/src/assets/foundation/nav_2.png`" alt="" />&ndash;&gt;-->
<!--              <img src="@/assets/foundation/nav_2.png" alt="" />-->
<!--            </el-tooltip>-->
<!--          </li>-->
<!--          <li @click="jumpPage('application')">-->
<!--            <el-tooltip effect="dark" content="应用中心" placement="bottom">-->
<!--              &lt;!&ndash;              <img :src="`/src/assets/foundation/nav_3.png`" alt="" />&ndash;&gt;-->
<!--              <img src="@/assets/foundation/nav_3.png" alt="" />-->
<!--            </el-tooltip>-->
<!--          </li>-->
<!--          <li @click="jumpPage('communication')">-->
<!--            <el-tooltip effect="dark" content="船岸通信" placement="bottom">-->
<!--              &lt;!&ndash;              <img :src="`/src/assets/foundation/nav_4.png`" alt="" />&ndash;&gt;-->
<!--              <img src="@/assets/foundation/nav_4.png" alt="" />-->
<!--            </el-tooltip>-->
<!--          </li>-->
<!--        </ul>-->
<!--        &lt;!&ndash; 用户名 &ndash;&gt;-->
<!--        <div class="user-con">-->
<!--          <img src="@/assets/foundation/user.png" alt="" />-->
<!--          <span>admin</span>-->
<!--        </div>-->
<!--        <span class="line"></span>-->
<!--        &lt;!&ndash; 退出 &ndash;&gt;-->
<!--        <span class="logout" @click="jumpPage('login')">-->
<!--&lt;!&ndash;          <img :src="`/src/assets/foundation/logout.png`" alt="" />&ndash;&gt;-->
<!--          <img src="@/assets/foundation/logout.png" alt="" />-->
<!--        </span>-->
<!--      </div>-->
<!--    </div>-->
    <!--      <topbar />-->
    <!-- 中间标题 -->
    <p class="page-title1">智能应用</p>
    <!-- 左侧卡片 -->
    <div class="page-l">
      <!-- 船舶态势 -->
      <div class="card-item">
        <p class="head">船舶态势</p>
        <div class="con-ship">
          <ul class="list">
            <li class="item">
              <div id="t_chart_1"></div>
            </li>
            <li>
              <div id="t_chart_2"></div>
            </li>
            <li>
              <div id="t_chart_3"></div>
            </li>
            <li>
              <div id="t_chart_4">
                <span>{{ objInfo.level }}</span>
                <span>CII评级</span>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <!-- 系统资源 -->
      <div class="card-item">
        <p class="head">系统资源</p>
        <div class="con-sys">
          <ul class="sys-t">
            <li>
              <p>物理机（台）</p>
              <p>{{ objSys.physicalMachine }}</p>
            </li>
            <li>
              <p>虚拟机（台）</p>
              <p>{{ objSys.virtualMachine }}</p>
            </li>
          </ul>
          <ul class="sys-c">
            <li>
              <div id="c_chart_1"></div>
              <p>{{ `CPU内核${objSys.cpuT}个，已分配${objSys.cpuU}个` }}</p>
            </li>
            <li>
              <div id="c_chart_2"></div>
              <p>{{ `内存${objSys.memoryT}G，已分配${objSys.memoryU}G` }}</p>
            </li>
            <li>
              <div id="c_chart_3"></div>
              <p>{{ `存储${objSys.storageT}T，已使用${objSys.storageU}T` }}</p>
            </li>
          </ul>
          <ul class="sys-b">
            <li>
              <p>虚拟机CPU占用率过高</p>
              <p>{{ objSys.cpuH }}<span>台</span></p>
            </li>
            <li>
              <p>虚拟机内存占用率过高</p>
              <p>{{ objSys.memoryH }}<span>台</span></p>
            </li>
            <li>
              <p>虚拟机存储占用率过高</p>
              <p>{{ objSys.storageH }}<span>台</span></p>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!-- 中间卡片 -->
    <ul class="page-c">
      <!--      <li v-for="(item, idx) in aPlatform" :key="idx" class="c-item">-->
      <!--        <img :src="`/src/assets/foundation/c_${idx + 1}.png`" alt="" />-->
      <!--&lt;!&ndash;        <img src="@/assets/foundation/c_${idx + 1}.png" alt="" />&ndash;&gt;-->
      <!--        <p>{{ item.label }}</p>-->
      <!--      </li>-->
      <li v-for="(item, idx) in aPlatform[0]" :key="idx" class="c-item">
        <img src="@/assets/foundation/c_1.png" alt="" />
        <p>智能能效系统</p>
      </li>
      <li v-for="(item, idx) in aPlatform[1]" :key="idx" class="c-item">
        <img src="@/assets/foundation/c_2.png" alt="" />
        <p>智能运维系统</p>
      </li>
      <li v-for="(item, idx) in aPlatform[2]" :key="idx" class="c-item">
        <img src="@/assets/foundation/c_3.png" alt="" />
        <p>智能液货系统</p>
      </li>
    </ul>
    <!-- 右侧卡片 -->
    <div class="page-r">
      <!-- 系统预警 -->
      <div class="card-item">
        <p class="head">系统预警</p>
        <div class="con-r">
          <!--          <span>{{tableData1}}</span>-->
          <el-table :data="tableData1" stripe style="width: 100%">
            <el-table-column
                prop="alarmMessage"
                label="预警详情"
                align="center"
                min-width="120"
            />
            <el-table-column
                prop="timestamp"
                label="预警时间"
                align="center"
                min-width="100"
            />
            <el-table-column
                prop="severityValue"
                label="预警等级"
                align="center"
                min-width="100"
            >
              <template #default="{ row }">
                <span v-if="row.severityValue === '严重'" type="success"
                      style="color: #E95A5A; border: none; ">严重</span>
                <span v-else-if="row.shipStatusValue === '提示'" type="danger"
                      style="color: #FFF642; border: none; ">提示</span>
                <span v-else type="warning"
                      style="color: #FC8514; border: none;">一般</span>
              </template>
              <!--              <template #default="{ $index, row }">-->
              <!--                <span :style="{ color: aLevel[row.level].color }">{{-->
              <!--                  aLevel[row.level].label-->
              <!--                }}</span>-->
              <!--              </template>-->
            </el-table-column>
            <el-table-column
                prop="alarmStatusValue"
                label="状态"
                align="center"
                min-width="100"
                sortable
                :default-sort="{prop: 'status', order: 'ascending'}"
                @sort-change="handleSortChange"
            >
              <template v-slot="scope">
                <el-button v-if="scope.row.alarmStatus === '01'" size="small">已处理</el-button>
                <el-button type="primary" v-else-if="scope.row.alarmStatus === '02'"
                           @click="handleClickRow(row)" size="small">待处理</el-button>
              </template>
              <!--              <template #default="{ $index, row }">-->
              <!--                <p-->
              <!--                  @click="handleClickRow(row)"-->
              <!--                  :class="row.status == 0 ? 'act' : 'default'"-->
              <!--                  class="btn-table"-->
              <!--                >-->
              <!--                  {{ row.status == 0 ? "待处理" : "已处理" }}-->
              <!--                </p>-->
              <!--              </template>-->
            </el-table-column>
          </el-table>
        </div>
      </div>
      <!-- 岸端建议 -->
      <div class="card-item">
        <p class="head">岸端建议</p>
        <div class="con-r">
          <!--          <span>{{tableData2}}</span>-->
          <el-table :data="tableData2" stripe style="width: 100%">
            <el-table-column prop="adviceSummarize" label="建议详情" style="width: 170px">
              <template #default="{ $index, row }">
                <div class="suggest-cell" style="width: 170px">
                  <!--                  <img-->
                  <!--                    :src="`/src/assets/foundation/email_${row.operatorId}.png`"-->
                  <!--                    alt=""-->
                  <!--                  />-->
                  <img v-if="row.status === '已读'"
                       src="@/assets/foundation/email_1.png"
                       alt=""
                  />
                  <img v-else-if="row.status === '未读'"
                       src="@/assets/foundation/email_0.png"
                       alt=""
                  />
                  <img v-else
                       src="@/assets/foundation/email_2.png"
                       alt=""
                  />
                  <span>{{ row.adviceSummarize }}</span>
                </div>
              </template>
            </el-table-column>
            <el-table-column
                prop="pushTimestamp"
                label="发送时间"
                align="center"
            />
            <el-table-column
                prop="type"
                label="建议类型"
                align="center"
            >
              <!--              <template #default="{ $index, row }">-->
              <!--                <span>{{ aType[row.type] }}</span>-->
              <!--              </template>-->
            </el-table-column>
          </el-table>
        </div>

        <!-- 对话框 -->
        <div class="dialog" v-if="dialogVisible">
          <div class="title">预警详情</div>
          <el-icon
              @click="
              () => {
                dialogVisible = false;
              }
            "
              class="close"
              color="#fff"
              size="15px"
          ><Close
          /></el-icon>
          <div class="con">
            <el-input
                v-model="curRow.info"
                type="textarea"
                placeholder="Please input"
            />
          </div>
          <div class="foot">
            <p @click="handleClickConfirm">确认</p>
            <p @click="handleClickGo">跳转至应用</p>
          </div>
        </div>
      </div>
    </div>
    <!-- 底部背景图 -->
    <!--    <img class="img-foot" :src="`/src/assets/foundation/img_foot.png`" alt="" />-->
    <img class="img-foot" src="@/assets/foundation/img_foot.png" alt="" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref ,onUnmounted} from "vue";
import { useRouter } from "vue-router";
import * as echarts from "echarts";
import {getAlarm, getSolution, getCurrentEnergyInfo} from '@/api/ship'
import topbar from '@/components/topbar/index'


defineOptions({
  name: "ship-foundation",
  components: {},
});
const router = useRouter();
console.log("router", router);
const updatef =ref(null);
const curRow = ref({
  info: "这里是预警详细信息内容这里是细信息内容这里是预警详细信息内容这里是预警内容这里是预警详细信息内容这里是预警详细信息内容这里是预警详细信息内容这里是预警详细信息内容...",
});
const dialogVisible = ref(false);
// 路由跳转
const jumpPage = (key) => {
  console.log("key", key);
  // router.push({
  //   name: key,
  // })
};

// 打开预警详情对话框
const handleClickRow = (row) => {
  console.log("打开预警详情对话框", row);
  dialogVisible.value = true;
};

// 预警详情对话框-确认
const handleClickConfirm = () => {
  console.log("确认", 1);
  dialogVisible.value = false;
};

// 跳转至应用
const handleClickGo = () => {
  console.log("跳转至应用", 1);
};
function     handleSortChange({ column, prop, order }) {
  tableData1.value.sort((a, b) => {
    if (a.alarmStatus === '01' && b.alarmStatus !== '01') {
      return -1;
    } else if (a.alarmStatus !== '01' && b.alarmStatus === '01') {
      return 1;
    }
    return order === 'ascending' ? a[prop] - b[prop] : b[prop] - a[prop];
  });
  if (order === 'ascending') {
    tableData1.value.reverse();
  }
}

function getfirstpart(){
  getCurrentEnergyInfo().then(res => {
    console.log('energy-info', res)
    objInfo.value.shipSpeed = parseFloat(res.SOG) || 0
    const speed = parseFloat(res.RotateSpeed)
    objInfo.value.engineSpeed = Number(speed.toFixed(2)) || 0
    objInfo.value.enginePower = parseFloat(res.Power) || 0
    objInfo.value.level = res.CIILevel || 0
    console.log('obj-info', objInfo)
    // 航速
    drawGaugeEchart1("t_chart_1", objInfo.value.shipSpeed, "航速/Kn");
    // 主机转速
    drawGaugeEchart2("t_chart_2", objInfo.value.engineSpeed, "主机转速/rpm");
    // 主机功率
    drawGaugeEchart3("t_chart_3", objInfo.value.enginePower, "主机功率/Kw");
  })
}
/** 页面初始化 */
onMounted(() => {
  getfirstpart()
  //updatef.value = setInterval(getfirstpart,5000)
  // 蓝
  drawPieEchart("c_chart_1", "#4D99FD", objSys.value.cpuU, objSys.value.cpuT);
  // 红
  drawPieEchart(
      "c_chart_2",
      "#E95A5A",
      objSys.value.memoryU,
      objSys.value.memoryT
  );
  // 绿
  drawPieEchart(
      "c_chart_3",
      "#2CC049",
      objSys.value.storageU,
      objSys.value.storageT
  );

});
onUnmounted(() => {
  clearInterval(updatef);
});
// 数据-船舶态势
const objInfo = ref({
  shipSpeed: 0,
  engineSpeed: 0,
  enginePower: 0,
  level: 'D',
});

// 数据-系统资源
const objSys = ref({
  physicalMachine: 8,
  virtualMachine: 32,
  // 合计
  cpuT: 1024,
  memoryT: 3072,
  storageT: 192,
  // 已用
  cpuU: 512,
  memoryU: 1536,
  storageU: 48,
  // 超出
  cpuH: 4,
  memoryH: 6,
  storageH: 2,
});

/** 船舶态势-航向图
 * domId 渲染目标标签id
 * v value值
 * name 标题
 */
const drawGaugeEchart1 = function (domId, v, name) {
  const chartDom = document.getElementById(domId);
  const myChart = echarts.init(chartDom);
  var min2 = 0;
  var max2 = 30;
  var splitNumber2 = 4;
  const option = {
    grid: {
      containLabel: true,
      left: 0,
      right: 0,
      top: 0,
      bottom: 0,
    },
    series: [
      {
        name: "刻度线及刻度值",
        type: "gauge",
        center: ["50%", "50%"], //中心位置
        radius: "95%",
        min: min2,
        max: max2,
        splitNumber: splitNumber2,
        axisLine: {
          // 仪表盘轴线
          lineStyle: {
            // 属性lineStyle控制线条样式
            color: [[1, "rgba(255,255,255,0)"]],
            width: 1,
          },
        },
        splitLine: {
          // 仪表盘分隔线
          distance: 20,
          length: 10,
          lineStyle: {
            width: 1,
            color: "rgba(0, 128, 255, .2)",
          },
        },
        axisLabel: {
          // 仪表盘刻度标签
          show: true,
          distance: 0,
          fontSize: 10,
          color: "#fff",
        },
        axisTick: {
          // 仪表盘刻度
          distance: 10,
          length: 10,
          splitNumber: 2,
          lineStyle: {
            color: "rgba(0, 128, 255, .2)",
          },
        },
        pointer: {
          // 仪表盘指针
          show: false,
        },
        anchor: {
          // 表盘中指针的固定点
          show: false,
          showAbove: true,
        },
        detail: {
          // 仪表盘详情，用于显示数据
          fontSize: 20,
          color: "#FFFFFF", // 数据详情颜色
          formatter: "{value}",
          offsetCenter: ["0%", "0%"],
        },
        data: [
          {
            value: v,
          },
        ],
      },
      {
        name: "进度条及指示器",
        type: "gauge",
        center: ["50%", "50%"],
        radius: "84%",
        z: 40,
        min: min2,
        max: max2,
        splitNumber: splitNumber2,
        axisLine: {
          show: false,
        },
        splitLine: {
          show: false,
        },
        axisLabel: {
          show: false,
          distance: 25,
          color: "#fff",
          fontSize: 20,
        },
        axisTick: {
          show: false,
        },
        anchor: {
          show: false,
          showAbove: true,
        },
        detail: {
          show: false,
        },
        data: [
          {
            value: v,
          },
        ],
        pointer: {
          z: 1,
          show: false,
          length: "80%",
          itemStyle: {
            color: "#3D91EA",
          },
        },
        progress: {
          show: true,
          width: 19,
          itemStyle: {
            color: {
              type: "linear",
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [
                {
                  offset: 0,
                  color: "rgba(0, 128, 255,0.3)",
                },
                {
                  offset: 1,
                  color: "rgba(0, 128, 255,0.8)",
                },
              ],
            },
          },
        },
      },
      {
        name: "进度条及指示器背景",
        type: "gauge",
        center: ["50%", "50%"],
        radius: "88%",
        z: 30,
        axisLine: {
          show: false,
        },
        splitLine: {
          show: false,
        },
        axisLabel: {
          show: false,
        },
        axisTick: {
          show: false,
        },
        anchor: {
          show: false,
          showAbove: true,
        },
        detail: {
          show: false,
        },
        data: [
          {
            value: 100,
          },
        ],
        pointer: {
          show: false,
        },
        progress: {
          show: true,
          width: 2,
          itemStyle: {
            color: "rgba(0, 128, 255,0.9)",
          },
        },
      },
      {
        name: "最外层渐变覆盖区",
        type: "gauge",
        center: ["50%", "50%"],
        radius: "100%",
        min: min2,
        max: max2,
        axisLine: {
          show: false,
        },
        axisTick: {
          show: false,
        },
        splitLine: {
          show: false,
        },
        axisLabel: {
          show: false,
        },
        pointer: {
          show: false,
        },
        progress: {
          show: true,
          roundCap: true,
          width: 3,
          itemStyle: {
            borderColor: "transparent",
            color: {
              type: "linear",
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [
                {
                  offset: 0,
                  color: "rgba(0, 128, 255,1)",
                },
                {
                  offset: 1,
                  color: "rgba(0, 128, 255,0)",
                },
              ],
            },
          },
        },
        detail: {
          show: false,
        },
        itemStyle: {
          color: "rgba(0, 128, 255,.3)",
          borderColor: "rgba(0, 128, 255,1)",
        },
        title: {
          offsetCenter: [0, "90%"], // 标题的偏移
          textStyle: {
            color: "#ffff", // 标题的颜色
            fontSize: 15, // 标题的字体大小
          },
        },
        data: [
          {
            value: v,
            name: name,
          },
        ],
      },
      {
        z: 89,
        type: "pie",
        name: "内层细圆环",
        radius: ["38%", "40%"],
        startAngle: 360,
        hoverAnimation: false,
        clockWise: true,
        silent: true,
        itemStyle: {
          normal: {
            color: "rgba(0, 128, 255,.3)",
            shadowBlur: 0,
          },
        },
        tooltip: {
          show: false,
        },
        label: {
          show: false,
        },
        data: [100],
      },
    ],
  };
  option && myChart.setOption(option, true);
  // 浏览器窗口大小变化，图表大小自适应
  window.addEventListener("resize", () => {
    myChart.resize();
  });
};
const drawGaugeEchart2 = function (domId, v, name) {
  const chartDom = document.getElementById(domId);
  const myChart = echarts.init(chartDom);
  var min2 = 0;
  var max2 = 120;
  var splitNumber2 = 4;
  const option = {
    grid: {
      containLabel: true,
      left: 0,
      right: 0,
      top: 0,
      bottom: 0,
    },
    series: [
      {
        name: "刻度线及刻度值",
        type: "gauge",
        center: ["50%", "50%"], //中心位置
        radius: "95%",
        min: min2,
        max: max2,
        splitNumber: splitNumber2,
        axisLine: {
          // 仪表盘轴线
          lineStyle: {
            // 属性lineStyle控制线条样式
            color: [[1, "rgba(255,255,255,0)"]],
            width: 1,
          },
        },
        splitLine: {
          // 仪表盘分隔线
          distance: 20,
          length: 10,
          lineStyle: {
            width: 1,
            color: "rgba(0, 128, 255, .2)",
          },
        },
        axisLabel: {
          // 仪表盘刻度标签
          show: true,
          distance: 0,
          fontSize: 10,
          color: "#fff",
        },
        axisTick: {
          // 仪表盘刻度
          distance: 10,
          length: 10,
          splitNumber: 2,
          lineStyle: {
            color: "rgba(0, 128, 255, .2)",
          },
        },
        pointer: {
          // 仪表盘指针
          show: false,
        },
        anchor: {
          // 表盘中指针的固定点
          show: false,
          showAbove: true,
        },
        detail: {
          // 仪表盘详情，用于显示数据
          fontSize: 20,
          color: "#FFFFFF", // 数据详情颜色
          formatter: "{value}",
          offsetCenter: ["0%", "0%"],
        },
        data: [
          {
            value: v,
          },
        ],
      },
      {
        name: "进度条及指示器",
        type: "gauge",
        center: ["50%", "50%"],
        radius: "84%",
        z: 40,
        min: min2,
        max: max2,
        splitNumber: splitNumber2,
        axisLine: {
          show: false,
        },
        splitLine: {
          show: false,
        },
        axisLabel: {
          show: false,
          distance: 25,
          color: "#fff",
          fontSize: 20,
        },
        axisTick: {
          show: false,
        },
        anchor: {
          show: false,
          showAbove: true,
        },
        detail: {
          show: false,
        },
        data: [
          {
            value: v,
          },
        ],
        pointer: {
          z: 1,
          show: false,
          length: "80%",
          itemStyle: {
            color: "#3D91EA",
          },
        },
        progress: {
          show: true,
          width: 19,
          itemStyle: {
            color: {
              type: "linear",
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [
                {
                  offset: 0,
                  color: "rgba(0, 128, 255,0.3)",
                },
                {
                  offset: 1,
                  color: "rgba(0, 128, 255,0.8)",
                },
              ],
            },
          },
        },
      },
      {
        name: "进度条及指示器背景",
        type: "gauge",
        center: ["50%", "50%"],
        radius: "88%",
        z: 30,
        axisLine: {
          show: false,
        },
        splitLine: {
          show: false,
        },
        axisLabel: {
          show: false,
        },
        axisTick: {
          show: false,
        },
        anchor: {
          show: false,
          showAbove: true,
        },
        detail: {
          show: false,
        },
        data: [
          {
            value: 100,
          },
        ],
        pointer: {
          show: false,
        },
        progress: {
          show: true,
          width: 2,
          itemStyle: {
            color: "rgba(0, 128, 255,0.9)",
          },
        },
      },
      {
        name: "最外层渐变覆盖区",
        type: "gauge",
        center: ["50%", "50%"],
        radius: "100%",
        min: min2,
        max: max2,
        axisLine: {
          show: false,
        },
        axisTick: {
          show: false,
        },
        splitLine: {
          show: false,
        },
        axisLabel: {
          show: false,
        },
        pointer: {
          show: false,
        },
        progress: {
          show: true,
          roundCap: true,
          width: 3,
          itemStyle: {
            borderColor: "transparent",
            color: {
              type: "linear",
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [
                {
                  offset: 0,
                  color: "rgba(0, 128, 255,1)",
                },
                {
                  offset: 1,
                  color: "rgba(0, 128, 255,0)",
                },
              ],
            },
          },
        },
        detail: {
          show: false,
        },
        itemStyle: {
          color: "rgba(0, 128, 255,.3)",
          borderColor: "rgba(0, 128, 255,1)",
        },
        title: {
          offsetCenter: [0, "90%"], // 标题的偏移
          textStyle: {
            color: "#ffff", // 标题的颜色
            fontSize: 15, // 标题的字体大小
          },
        },
        data: [
          {
            value: v,
            name: name,
          },
        ],
      },
      {
        z: 89,
        type: "pie",
        name: "内层细圆环",
        radius: ["38%", "40%"],
        startAngle: 360,
        hoverAnimation: false,
        clockWise: true,
        silent: true,
        itemStyle: {
          normal: {
            color: "rgba(0, 128, 255,.3)",
            shadowBlur: 0,
          },
        },
        tooltip: {
          show: false,
        },
        label: {
          show: false,
        },
        data: [100],
      },
    ],
  };
  option && myChart.setOption(option, true);
  // 浏览器窗口大小变化，图表大小自适应
  window.addEventListener("resize", () => {
    myChart.resize();
  });
};
const drawGaugeEchart3 = function (domId, v, name) {
  const chartDom = document.getElementById(domId);
  const myChart = echarts.init(chartDom);
  var min2 = 0;
  var max2 = 10000;
  var splitNumber2 = 4;
  const option = {
    grid: {
      containLabel: true,
      left: 0,
      right: 0,
      top: 0,
      bottom: 0,
    },
    series: [
      {
        name: "刻度线及刻度值",
        type: "gauge",
        center: ["50%", "50%"], //中心位置
        radius: "95%",
        min: min2,
        max: max2,
        splitNumber: splitNumber2,
        axisLine: {
          // 仪表盘轴线
          lineStyle: {
            // 属性lineStyle控制线条样式
            color: [[1, "rgba(255,255,255,0)"]],
            width: 1,
          },
        },
        splitLine: {
          // 仪表盘分隔线
          distance: 20,
          length: 10,
          lineStyle: {
            width: 1,
            color: "rgba(0, 128, 255, .2)",
          },
        },
        axisLabel: {
          // 仪表盘刻度标签
          show: true,
          distance: 0,
          fontSize: 10,
          color: "#fff",
        },
        axisTick: {
          // 仪表盘刻度
          distance: 10,
          length: 10,
          splitNumber: 2,
          lineStyle: {
            color: "rgba(0, 128, 255, .2)",
          },
        },
        pointer: {
          // 仪表盘指针
          show: false,
        },
        anchor: {
          // 表盘中指针的固定点
          show: false,
          showAbove: true,
        },
        detail: {
          // 仪表盘详情，用于显示数据
          fontSize: 20,
          color: "#FFFFFF", // 数据详情颜色
          formatter: "{value}",
          offsetCenter: ["0%", "0%"],
        },
        data: [
          {
            value: v,
          },
        ],
      },
      {
        name: "进度条及指示器",
        type: "gauge",
        center: ["50%", "50%"],
        radius: "84%",
        z: 40,
        min: min2,
        max: max2,
        splitNumber: splitNumber2,
        axisLine: {
          show: false,
        },
        splitLine: {
          show: false,
        },
        axisLabel: {
          show: false,
          distance: 25,
          color: "#fff",
          fontSize: 20,
        },
        axisTick: {
          show: false,
        },
        anchor: {
          show: false,
          showAbove: true,
        },
        detail: {
          show: false,
        },
        data: [
          {
            value: v,
          },
        ],
        pointer: {
          z: 1,
          show: false,
          length: "80%",
          itemStyle: {
            color: "#3D91EA",
          },
        },
        progress: {
          show: true,
          width: 19,
          itemStyle: {
            color: {
              type: "linear",
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [
                {
                  offset: 0,
                  color: "rgba(0, 128, 255,0.3)",
                },
                {
                  offset: 1,
                  color: "rgba(0, 128, 255,0.8)",
                },
              ],
            },
          },
        },
      },
      {
        name: "进度条及指示器背景",
        type: "gauge",
        center: ["50%", "50%"],
        radius: "88%",
        z: 30,
        axisLine: {
          show: false,
        },
        splitLine: {
          show: false,
        },
        axisLabel: {
          show: false,
        },
        axisTick: {
          show: false,
        },
        anchor: {
          show: false,
          showAbove: true,
        },
        detail: {
          show: false,
        },
        data: [
          {
            value: 100,
          },
        ],
        pointer: {
          show: false,
        },
        progress: {
          show: true,
          width: 2,
          itemStyle: {
            color: "rgba(0, 128, 255,0.9)",
          },
        },
      },
      {
        name: "最外层渐变覆盖区",
        type: "gauge",
        center: ["50%", "50%"],
        radius: "100%",
        min: min2,
        max: max2,
        axisLine: {
          show: false,
        },
        axisTick: {
          show: false,
        },
        splitLine: {
          show: false,
        },
        axisLabel: {
          show: false,
        },
        pointer: {
          show: false,
        },
        progress: {
          show: true,
          roundCap: true,
          width: 3,
          itemStyle: {
            borderColor: "transparent",
            color: {
              type: "linear",
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [
                {
                  offset: 0,
                  color: "rgba(0, 128, 255,1)",
                },
                {
                  offset: 1,
                  color: "rgba(0, 128, 255,0)",
                },
              ],
            },
          },
        },
        detail: {
          show: false,
        },
        itemStyle: {
          color: "rgba(0, 128, 255,.3)",
          borderColor: "rgba(0, 128, 255,1)",
        },
        title: {
          offsetCenter: [0, "90%"], // 标题的偏移
          textStyle: {
            color: "#ffff", // 标题的颜色
            fontSize: 15, // 标题的字体大小
          },
        },
        data: [
          {
            value: v,
            name: name,
          },
        ],
      },
      {
        z: 89,
        type: "pie",
        name: "内层细圆环",
        radius: ["38%", "40%"],
        startAngle: 360,
        hoverAnimation: false,
        clockWise: true,
        silent: true,
        itemStyle: {
          normal: {
            color: "rgba(0, 128, 255,.3)",
            shadowBlur: 0,
          },
        },
        tooltip: {
          show: false,
        },
        label: {
          show: false,
        },
        data: [100],
      },
    ],
  };
  option && myChart.setOption(option, true);
  // 浏览器窗口大小变化，图表大小自适应
  window.addEventListener("resize", () => {
    myChart.resize();
  });
};


/** 系统资源-饼图
 * domId 渲染目标标签id
 * c 颜色
 * u 已用
 * t 全部
 */
const drawPieEchart = function (domId, c, u, t) {
  const chartDom = document.getElementById(domId);
  const myChart = echarts.init(chartDom);
  const option = {
    tooltip: {
      trigger: "none",
    },
    legend: {
      show: false,
    },
    series: [
      {
        silent: true,
        name: "CPU",
        type: "pie",
        radius: ["90%", "100%"],
        avoidLabelOverlap: false,
        label: {
          position: "center",
          formatter: "{d}%",
          color: c,
          fontSize: "20px",
        },
        data: [
          {
            value: u,
            name: "已用",
            itemStyle: {
              color: c,
            },
          },
          {
            value: t - u,
            name: "未用",
            itemStyle: {
              color: "rgba(77, 153, 253, .1)",
            },
          },
        ],
      },
    ],
  };
  option && myChart.setOption(option, true);
  // 浏览器窗口大小变化，图表大小自适应
  window.addEventListener("resize", () => {
    myChart.resize();
  });
};
// 数据-中间卡片
const aPlatform = ref([
  {
    label: "智能能效系统",
  },
  {
    label: "智能运维系统",
  },
  {
    label: "智能液货系统",
  },
]);

// 数据字典-系统预警：等级
const aLevel = ref([
  {
    color: "#FF646C",
    label: "严重",
  },
  {
    color: "#FF7F18",
    label: "一般",
  },
  {
    color: "#FFD318",
    label: "提示",
  },
]);

// 数据字典-岸端建议：类型
const aType = ref(["航线优化", "设备健康", "能效优化"]);

// 数据-系统预警
// const tableData1 = [
//   {
//     name: "航线偏离",
//     date: "05-03 09:12",
//     level: 0,
//     status: 0,
//   },
//   {
//     name: "冷却水泵健康异常",
//     date: "05-03 09:12",
//     level: 1,
//     status: 1,
//   },
//   {
//     name: "污底处理预警",
//     date: "05-03 09:12",
//     level: 2,
//     status: 0,
//   },
//   {
//     name: "航线偏离",
//     date: "05-03 09:12",
//     level: 2,
//     status: 1,
//   },
//   {
//     name: "冷却水泵健康异常",
//     date: "05-03 09:12",
//     level: 0,
//     status: 1,
//   },
//   {
//     name: "污底处理预警",
//     date: "05-03 09:12",
//     level: 0,
//     status: 1,
//   },
//   {
//     name: "航线偏离",
//     date: "05-03 09:12",
//     level: 1,
//     status: 1,
//   },
//   {
//     name: "冷却水泵健康异常",
//     date: "05-03 09:12",
//     level: 0,
//     status: 1,
//   },
// ];

// 数据-岸端建议
// const tableData2 = [
//   {
//     name: "建议调整航线",
//     date: "05-03 09:12",
//     type: 0,
//     status: 1,
//   },
//   {
//     name: "建议更换冷却水泵",
//     date: "05-03 09:12",
//     type: 1,
//     status: 1,
//   },
//   {
//     name: "建议尽快进行污底处理",
//     date: "05-03 09:12",
//     type: 2,
//     status: 2,
//   },
//   {
//     name: "建议调整航线",
//     date: "05-03 09:12",
//     type: 0,
//     status: 2,
//   },
//   {
//     name: "建议调整航线",
//     date: "05-03 09:12",
//     type: 1,
//     status: 1,
//   },
//   {
//     name: "建议尽快进行污底处理",
//     date: "05-03 09:12",
//     type: 2,
//     status: 1,
//   },
//   {
//     name: "建议调整航线",
//     date: "05-03 09:12",
//     type: 0,
//     status: 0,
//   },
// ];
const tableData1 = ref([])
const tableData2 = ref([])

function getAlarmList(){
  getAlarm().then(res => {
    console.log('系统预警alarm', res.rows)
    tableData1.value = res.rows
  })
}

function getSolutionList(){
  getSolution().then(res => {
    console.log('solution', res.rows)
    tableData2.value = res.rows
  })
}

getAlarmList()
getSolutionList()
</script>

<style lang="scss">
.ship-foundation {
  * {
    padding: 0;
    margin: 0;
    font-family: MicrosoftYaHei, MicrosoftYaHei;
  }
  min-width: 1600px;
  width: 100vw;
  height: calc(100vh - 90px);
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
    top: 25px;
    //bottom: -72px;
    margin-left: -95px;
    font-size: 24px;
    color: #ffffff;
  }
  // 底部背景图
  .img-foot {
    width: 420px;
    height: 133px;
    position: absolute;
    bottom: 0;
    left: 50%;
    margin-left: -210px;
  }

  .page-l {
    left: 15px;
  }
  .page-r {
    right: 15px;
  }
  // 两侧卡片
  .page-l,
  .page-r {
    position: absolute;
    //top: -35px;
    top: 0;
    height: calc(100vh - 80px);
    .card-item {
      width: 500px;
      height: calc(100% / 2);
      background: rgba(35, 56, 113, 1);
      margin-bottom: 15px;
      overflow: hidden;

      // 对话框
      .dialog {
        position: absolute;
        top: 0;
        left: -376px;
        width: 356px;
        height: 290px;
        background: #193362;
        box-shadow: 0px 0px 13px 0px rgba(0, 0, 0, 0.5);
        .title {
          width: 356px;
          height: 40px;
          line-height: 40px;
          background: linear-gradient(
                  270deg,
                  rgba(13, 60, 141, 0) 0%,
                  #0d3c8d 100%
          );
          box-shadow: 0px 0px 13px 0px rgba(0, 0, 0, 0.5);
          font-weight: bold;
          font-size: 16px;
          color: #ffffff;
          padding-left: 20px;
        }
        .close {
          position: absolute;
          top: 12px;
          right: 20px;
          cursor: pointer;
        }
        .con {
          textarea {
            resize: none;
            overflow: auto;
            margin: 30px auto;
            width: 303px;
            height: 136px;
            box-shadow: 0px 0px 13px 0px rgba(0, 0, 0, 0.5);
            border: 1px solid #3a5d98;
            background: none;
            padding: 9px 11px;
            font-weight: 400;
            font-size: 12px;
            color: #ffffff;
            line-height: 18px;
          }
        }
        .foot {
          width: 100%;
          display: flex;
          flex-direction: row;
          align-items: center;
          justify-content: center;
          p {
            margin: 0 23px;
            width: 100px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            cursor: pointer;
            background: #0080ff;
            box-shadow: 0px 0px 13px 0px rgba(0, 0, 0, 0.5);
            border-radius: 2px;
            font-weight: 400;
            font-size: 16px;
            color: #ffffff;
          }
        }
      }
      .head {
        margin: 0 !important;
        width: 100%;
        height: 40px;
        line-height: 40px;
        background: linear-gradient(
                270deg,
                rgba(13, 60, 141, 0) 0%,
                #0d3c8d 100%
        );
        padding: 0 13px;
        font-weight: bold;
        font-size: 18px;
        color: #ffffff;
        text-align: left;
        font-style: normal;
        position: relative;
        ::before {
          content: "";
          width: 112px;
          height: 4px;
          background: #527df3;
          position: absolute;
          bottom: 0;
          left: 0;
        }
      }
      &:last-child {
        margin: 0;
      }
      .con-r {
        height: calc(100% - 40px);
        padding: 10px 25px;
        overflow: auto;
      }
      .con-ship {
        height: calc(100% - 40px);
        padding: 10px 25px;
        overflow-y: auto;
        .list {
          list-style: none;
          padding: 0;
          margin: 0;
          display: flex;
          flex-direction: row;
          align-items: center;
          flex-wrap: wrap;
          justify-content: space-between;
          li {
            flex: 1;
            padding: 0;
            margin: 0;
            width: 50%;
            margin-top: 10px;
            div {
              margin: 0 auto;
              width: 155px;
              height: 155px;
            }
            #t_chart_4 {
              background: url("@/assets/foundation/level.png") no-repeat center;
              background-size: 90% 90%;
              width: 170px;
              position: relative;
              margin-top: -10px;
              span:nth-child(1) {
                position: absolute;
                top: 57px;
                left: 75px;
                font-weight: 500;
                font-size: 30px;
                color: #ff922a;
                line-height: 52px;
              }
              span:nth-child(2) {
                position: absolute;
                bottom: -3px;
                left: 60px;
                font-size: 15px;
                color: #ffffff;
                line-height: 16px;
                font-weight: 500;
              }
            }
          }
        }
      }
      .con-sys {
        height: calc(100% - 40px);
        padding: 25px;
        overflow-y: auto;
        .sys-t {
          list-style: none;
          padding: 0;
          display: flex;
          flex-direction: row;
          justify-content: space-between;
          margin-bottom: 15px;

          li {
            width: 200px;
            height: 83px;
            position: relative;
            background: rgba(77, 153, 253, 0.1);
            padding-left: 33px;
            p:nth-child(1) {
              font-weight: 400;
              font-size: 12px;
              line-height: 30px;
              color: #fff;
            }
            p:nth-child(2) {
              font-weight: bold;
              font-size: 26px;
              line-height: 40px;
              color: #4d99fd;
            }
            &::before {
              width: 4px;
              height: 100%;
              content: "";
              background: #4d99fd;
              position: absolute;
              left: 0;
              top: 0;
            }
            &:last-child {
              background: rgba(44, 192, 73, 0.1);
              &::before {
                background: rgba(44, 192, 73, 1);
              }
              p:nth-child(2) {
                font-weight: bold;
                font-size: 26px;
                line-height: 40px;
                color: rgba(44, 192, 73, 1);
              }
            }
          }
        }
        .sys-c {
          list-style: none;
          display: flex;
          flex-direction: row;
          margin-bottom: 10px;
          li {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            div {
              width: 100px;
              height: 100px;
              margin-bottom: 10px;
            }
            p {
              font-size: 11px;
              text-align: center;
              transform: scale(0.8);
              color: #fff;
              font-weight: 400;
            }
          }
        }
        .sys-b {
          list-style: none;
          padding: 0;
          display: flex;
          flex-direction: row;
          justify-content: space-between;
          li {
            width: 140px;
            height: 83px;
            position: relative;
            background: rgba(77, 153, 253, 0.1);
            padding-left: 10px;
            p:nth-child(1) {
              font-weight: 400;
              font-size: 12px;
              line-height: 30px;
              color: #fff;
            }
            p:nth-child(2) {
              font-weight: bold;
              font-size: 26px;
              line-height: 50px;
              color: #4d99fd;
              span {
                font-size: 12px;
                color: #fff;
                padding-left: 5px;
              }
            }
            &::before {
              width: 4px;
              height: 100%;
              content: "";
              background: #4d99fd;
              position: absolute;
              left: 0;
              top: 0;
            }
            &:nth-child(2) {
              background: rgba(233, 90, 90, 0.1);
              &::before {
                background: rgba(233, 90, 90, 1);
              }
              p:nth-child(2) {
                font-weight: bold;
                color: rgba(233, 90, 90, 1);
              }
            }
            &:last-child {
              background: rgba(44, 192, 73, 0.1);
              &::before {
                background: rgba(44, 192, 73, 1);
              }
              p:nth-child(2) {
                font-weight: bold;
                color: rgba(44, 192, 73, 1);
              }
            }
          }
        }
      }
    }
  }
  // 右侧卡片表格样式
  .el-table .el-table__header-wrapper th {
    background: rgba(35, 56, 113, 0.9) !important;
    font-weight: bold;
    font-size: 12px;
    color: #ffffff;
    border: none !important;
  }
  .el-table .el-table__body-wrapper .el-table__row td {
    background: rgba(35, 56, 113, 1) !important;
    border: none !important;
    font-size: 12px;
    color: #ffffff;
    border: none !important;
  }

  .el-table .el-table__body-wrapper .el-table__row--striped td {
    border: none !important;
    background: rgba(35, 56, 113, 0.95) !important;
  }
  .el-table__inner-wrapper {
    &::before {
      background: transparent;
    }
  }

  .el-table .el-table__header-wrapper th,
  .el-table .el-table__body-wrapper .el-table__row td {
    height: 36px !important;
    padding: 0 !important;
  }

  // 表格按钮
  .btn-table {
    width: 60px;
    height: 24px;
    line-height: 24px;
    text-align: center;
    border-radius: 2px;
    display: inline-block;
    cursor: pointer;
    margin: 0 !important;
  }
  // 激活状态
  .act {
    background: #0080ff;
    color: #fff;
  }
  // 默认状态
  .default {
    border: 1px solid #d6d6d6;
    background: #eee;
    color: #aaa;
  }

  // 岸端建议-建议详情
  .suggest-cell {
    display: flex;
    flex-direction: row;
    align-items: center;
    img {
      width: 14px;
      height: 10px;
      margin-right: 3px;
    }
    span {
      flex: 1;
      display: block;
    }
  }

  // 中间卡片
  .page-c {
    width: calc(100vw - 1100px);
    display: flex;
    flex-direction: row;
    justify-content: center;
    position: absolute;
    top: 110px;
    left: 50%;
    transform: translateX(-50%);
    .c-item {
      list-style: none;
      width: 230px;
      height: 270px;
      background: url("@/assets/foundation/border.png") no-repeat center;
      background-size: 100% 100%;
      margin-right: 45px;
      &:hover {
        background-image: url("@/assets/foundation/border_act.png");
      }
      &:last-child {
        margin: 0;
      }
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      cursor: pointer;
      img {
        width: 108px;
        height: 108px;
      }
      p {
        font-weight: bold;
        font-size: 18px;
        color: #fff;
      }
    }
  }
}
</style>
