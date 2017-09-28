<template>
	<transition name="slide">
		<div class="liked">
			<u-header title="喜欢"></u-header>
			<div class="list">
				<stmt-list :data="likedList" @loadMore="loadMore"></stmt-list>
			</div>
		</div>
	</transition>
</template>

<script>
	import StmtList from '@/components/stmt-list'
	import UHeader from '@/components/u-header'
	import {mapGetters, mapActions} from 'vuex'
	export default {
		data() {
			return {
			}
		},
		components: {
			StmtList,
			UHeader
		},
		created () {
			this._initData()
		},
		methods: {
			_initData () {
				this.initLikedList(0)
			},
			loadMore () {
				let id = this.likedList[this.likedList.length - 1].id
				this.initLikedList(id)
			},
			...mapActions([
				'initLikedList'
			])
		},
		computed: {
			...mapGetters(['likedList'])
		}
	}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
	@import "../common/stylus/variable.styl"
	.liked
		position fixed
		top 0
		bottom 0
		width 100%
		height 100%
		z-index 40
		background $color-background
		&.slide-enter-active, &.slide-leave-active
			transition: all .3s
		&.slide-enter, &.slide-leave-to
			transform: translate3d(100%, 0, 0)
		.list
			width 100%
			position fixed
			top 42px
			bottom 0
</style>
