<template>
	<u-send title="回复评论" :placeholder="placeholder" :maxCount="500" @send="reply"></u-send>
</template>

<script>
	import {mapActions, mapGetters} from 'vuex'
	import USend from '@/components/u-send'
	export default {
		created () {
			if (!this.data) {
				this.$router.go(-1)
			}
		},
		methods: {
			reply (content) {
				this.replyComment({
					rootId: this.data.rootId,
					parentId: this.data.parentId,
					content: this.placeholder + content
				})
			},
			...mapActions([
				'replyComment'
			])
		},
		components: {
			USend
		},
		activated () {
			this.content = ''
		},
		computed: {
			placeholder () {
				if (this.data) {
					return '回复@' + this.data.nickname + ':'
				}
			},
			data () {
				return this.actions[0]
			},
			...mapGetters([
				'actions'
			])
		}
	}
</script>
<style scoped lang="stylus" rel="stylesheet/stylus">
</style>