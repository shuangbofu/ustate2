<template>
	<div @scroll="scroll($event)">
		<slot></slot>
		<div class="loading" v-show="length >= 15 && !loadedAll">
			<img src="http://118.89.182.253/img/ring-alt2.gif" height="40" width="40"v-if="!loadedAll">
		</div>
		<div v-if="length < 15 || loadedAll" class="loadedAll" style="text-align: center; padding: 13.5px; color: #8C8C8C;">无更多数据...</div>
	</div>
</template>
<script>
	import {mapMutations, mapGetters} from 'vuex'
	export default {
		props: {
			length: 0
		},
		data() {
			return {
				scrollY: 0,
				pathName: '',
				event: null
			}
		},
		activated () {
			this.pathName = this.$route.name
			if (this.pathName === 'index') {
				if (this.event) {
					this.scrollY = this.event.target.scrollTop = this.scrollRecord.index
				}
			} else if (this.pathName === 'hot') {
				if (this.event) {
					this.scrollY = this.event.target.scrollTop = this.scrollRecord.hot
				}
			} else if (this.pathName === 'posted') {
				if (this.event) {
					this.scrollY = this.event.target.scrollTop = this.scrollRecord.posted
				}
			} else if (this.pathName === 'liked') {
				if (this.event) {
					this.scrollY = this.event.target.scrollTop = this.scrollRecord.liked
				}
			} else if (this.pathName === 'notice') {
				if (this.event) {
					this.scrollY = this.event.target.scrollTop = this.scrollRecord.notice
				}
			}
			this.setLoadedAll(false)
		},
		deactivated () {
			this.saveScrollY({
				name: this.pathName,
				pos: this.scrollY
			})
		},
		methods: {
			scroll (e) {
				this.event = e
				let offsetHeight = e.target.offsetHeight
				let scrollHeight = e.target.scrollHeight
				let scrollTop = e.target.scrollTop
				this.scrollY = scrollTop
				if (scrollTop + offsetHeight === scrollHeight && !this.loadedAll) {
					this.$emit('loadMore')
				}
			},
			...mapMutations({
				setLoadedAll: 'SET_LOADEDALL',
				saveScrollY: 'SAVE_SCROLLY'
			})
		},
		computed: {
			...mapGetters(['loadedAll', 'scrollRecord'])
		}
	}
</script>
<style scoped lang="stylus" rel="stylesheet/stylus">
	.loading
		text-align center
		width 100%
		font-size 20px
</style>