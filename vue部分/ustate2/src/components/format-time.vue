<template>
	<div class="time">
		{{createtime}}
	</div>
</template>

<script>
import {getDateDiff, getDate} from '@/common/js/format-date'
export default {
	props: {
		time: {
			type: Number,
			default: 0
		},
		complete: false
	},
	computed: {
		createtime () {
			if (this.complete) {
				return getDate(this.time)
			} else {
				return getDateDiff(this.time)
			}
		}
	}
}
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
.time
	padding 2px 0
	display inline-block
	font-size 13px
	line-height 15px
	color #999999
	letter-spacing .8px
</style>
