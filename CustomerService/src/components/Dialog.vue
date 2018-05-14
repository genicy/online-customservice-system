<template>
	<el-dialog
	title = "服务评分"
	:visible.sync = "isShow"
	width = "30%"
	:modal="false"
	:show-close="showClose"
	:close-on-click-modal="false">
		<span>请为本次服务评分</span>
			<div class="block">
  			<el-rate
   			 v-model="score"
    		:colors="['#99A9BF', '#F7BA2A', '#FF9900']"
    		show-text
    		size="max">
  			</el-rate>
			</div>
			<span slot="footer" class="dialog-footer">
            	<el-button type="primary"
            	:plain="true"
            	@click="changeScore">确定</el-button>
            </span>
		 <el-dialog
      width="30%"
      :visible.sync="innerVisible"
      append-to-body
      :modal="false"
      @close = "onClose">
      <span>感谢您对本次服务的评价，我们将继续努力以提供更好地服务</span>
    </el-dialog>
	</el-dialog>
</template>

<script type="text/javascript">
export default {
  props: {
    isShow: {
      type: Boolean,
      default: false
    },
    showClose: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      innerVisible: false,
      score: null
    }
  },
  methods: {
    changeScore () {
      if (this.score === null || this.score === 0) {
        this.$message({
          			message: '请为我们的服务评分',
          type: 'warning'
        })
        return
      }
      this.innerVisible = true
    },
    onClose () {
      this.$emit('getScore', this.score)
    }
  }
}
</script>

<style type="text/css">

</style>
