<template>
	<div>
		<span class="title-font">流量监控</span>
	<el-row :gutter="40">
        <el-col :span="6" :offset="1">
        	<div class="grid-content bg-purple1">
        		<div class="content-font">正在服务人数</div>
        		<span class="num-font">{{ currNum }}</span>
        	</div>
    	</el-col>
        <el-col :span="6" :offset="2">
        	<div class="grid-content bg-purple2">
        		<div class="content-font">正在排队人数</div>
        		<span class="num-font">{{ rankingNum }}</span>
        	</div>
        </el-col>
        <el-col :span="6" :offset="2">
        	<div class="grid-content bg-purple3">
        		<div class="content-font">历史服务总人数</div>
        		<span class="num-font">{{ historyNum }}</span>
        	</div>
    	</el-col>
	</el-row>
	<div class="charts">
		<div id="charts" style="width: 48%; height: 600px"></div>
		<div id="charts1" style="width: 48%; height: 600px"></div>
	</div>
	</div>
</template>

<script type="text/javascript">
import {getAdminConnect} from '../webScoket/webscoket'
export default {
  data () {
    return {
      currNum: 0,
      rankingNum: 0,
      historyNum: 0,
      ws: null,
      aid: null,
      currNumArray: [],
      curRankNumArray: [],
      myChart: null,
      myChart1: null,
      xData: []
    }
  },
  mounted () {
    this.$store.commit('setAid', localStorage.getItem('aid'))
    this.getNum()
    this.init()
    this.drowLine()
  },
  methods: {
    init () {
      this.aid = this.$store.getters.getAid
      this.ws = getAdminConnect(this.aid)
      this.ws.onopen = (event) => {
        console.log('连接成功!')
      }
      this.ws.onmessage = (event) => {
        console.log(event.data)
        		this.analysisMessage(event.data)
      			}
    },
    analysisMessage (message) {
      message = JSON.parse(message)
      this.currNum = message.currNum
      this.rankingNum = message.curRankNum
      this.historyNum = message.totalNum
      let curHour = new Date()
      this.xData.push(curHour.getHours() + ':' + curHour.getMinutes() + ':' + curHour.getSeconds())
      this.currNumArray.push(this.currNum)
      this.curRankNumArray.push(this.rankingNum)
      this.drowLine()
      this.drowLine1()
    },
    drowLine () {
      this.myChart = this.$echarts.init(document.getElementById('charts'))
      this.myChart.setOption({
        title: {
          text: '当前服务人数',
          left: 'center',
          textStyle: {
            color: '#303133',
            fontStyle: 'normal',
            fontWeight: 'bold',
            fontSize: 18,
            fontFamily: 'Microsoft YaHei'
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: this.xData,
          name: '时间'
        },
        yAxis: {
          name: '人数'
        },
        tooltip: {
          trigger: 'axis'
        },
        series: [
          {
            name: '当前服务人数',
            type: 'line',
            data: this.currNumArray,
            smooth: true,
            itemStyle: {
              normal: {
                color: '#67C23A',
                lineStyle: {
                  shadowColor: 'rgba(0, 0, 0, 4)',
                  color: '#67C23A'
                }
              }
            }
          }
        ]
      })
    },
    drowLine1 () {
      this.myChart1 = this.$echarts.init(document.getElementById('charts1'))
      this.myChart1.setOption({
        title: {
          text: '当前排队人数',
          left: 'center',
          textStyle: {
            color: '#303133',
            fontStyle: 'normal',
            fontWeight: 'bold',
            fontSize: 18,
            fontFamily: 'Microsoft YaHei'
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: this.xData,
          name: '时间'
        },
        yAxis: {
          name: '人数'
        },
        tooltip: {
          trigger: 'axis'
        },
        series: [
          {
            name: '当前排队人数',
            type: 'line',
            data: this.curRankNumArray,
            smooth: true,
            itemStyle: {
              normal: {
                color: '#E6A23C',
                lineStyle: {
                  shadowColor: 'rgba(0, 0, 0, 4)',
                  color: '#E6A23C'
                }
              }
            }
          }
        ]
      })
    },
    getNum () {
      this.$axios.get('api/getNum')
        .then((res) => {
          console.log(res.data)
          this.currNum = res.data.currNum
          this.rankingNum = res.data.curRankNum
          this.historyNum = res.data.totalNum
          let curHour = new Date()
          this.xData.push(curHour.getHours() + ':' + curHour.getMinutes() + ':' + curHour.getSeconds())
          this.currNumArray.push(this.currNum)
          this.curRankNumArray.push(this.rankingNum)
          this.drowLine()
          this.drowLine1()
        }, (err) => {

        })
    }
    /* dataTool (data) {
				var date = this.xData
				var result = new Array()
				for (var i = 0;i < date.length-1;i++) {
					var tol = 0
					var num = 0
					for (var j = 0;j < data.length-1;j++) {
						if(date[i] === data[j].date) {
							tol += data[j].Num
							num++
						}
					}
					result.push(tol/num)

				}
				return result
			} */
  }
}
</script>

<style type="text/css">
	.el-row {
margin-top: 40px;
&:last-child {
  margin-bottom: 0;
}
}
.el-col {
border-radius: 4px;
}
.bg-purple-dark {
background: #99a9bf;
}
.bg-purple1 {
background: #efa97a;
box-shadow: 2px 4px 6px #000;
}
.bg-purple2 {
background: #E7C951;
box-shadow: 2px 4px 6px #000;
}
.bg-purple3 {
background: #E7E7E7;
box-shadow: 2px 4px 6px #000;
}
.bg-purple-light {
background: #e5e9f2;
}
.grid-content {
border-radius: 10px;
min-height: 200px;
text-align: center;
}
.row-bg {
padding: 10px 0;
background-color: #f9fafc;
}
.title-font {
	font-size: 20px;
	font-family: "Hiragino Sans GB";
	font-weight: bold;
}
.content-font {
	font-size: 20px;
}
.num-font {
	font-size: 100px;
}
.charts {
	display: flex;
	margin-top: 80px;
	justify-content: space-between;
}
</style>
