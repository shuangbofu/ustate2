<template>
	<u-send title="反馈" placeholder="反馈信息" :maxCount="120" @send="feedback"></u-send>
</template>

<script>
	import {mapActions} from 'vuex'
	import USend from '@/components/u-send'
	export default {
		methods: {
			feedback (content) {
				this.sendFeedback({
					content: content
				})
			},
			...mapActions([
				'sendFeedback'
			])
		},
		components: {
			USend
		}
	}
</script>
<style scoped lang="stylus" rel="stylesheet/stylus">
</style>