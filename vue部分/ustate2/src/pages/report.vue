<template>
	<u-send title="举报" placeholder="举报描述" :maxCount="120" @send="report"></u-send>
</template>

<script>
	import {mapActions, mapGetters} from 'vuex'
	import USend from '@/components/u-send'
	export default {
		methods: {
			report (content) {
				this.sendReport({
					defId: this.$route.params.id,
					content: content
				})
			},
			...mapActions([
				'sendReport'
			])
		},
		computed: {
			...mapGetters(['profile'])
		},
		components: {
			USend
		}
	}
</script>
<style scoped lang="stylus" rel="stylesheet/stylus">
</style>