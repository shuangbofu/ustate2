<template>
	<div class="index">
		<div class="list">
			<stmt-list :data="indexList" @loadMore="loadMore">
				<div class="header">
					<router-link tag="div" to="/index" replace>最新</router-link>
					<router-link tag="div" to="/hot" replace>热门</router-link>
				</div>
			</stmt-list>
		</div>
	</div>
</template>

<script>
	import StmtList from '@/components/stmt-list'
	import {mapGetters, mapActions, mapMutations} from 'vuex'
	export default {
		data() {
			return {
			}
		},
		components: {
			StmtList
		},
		created () {
			this._initData()
		},
		activated () {
			if (this.profile.id === undefined) {
				this.initCount()
			}
		},
		methods: {
			_initData () {
				this.initIndexList(0)
				// if (this.count === -1 && this.profile.id !== undefined) {
				// 	this.initCount()
				// }
			},
			loadMore () {
				let id = this.indexList[this.indexList.length - 1].id
				this.initIndexList(id)
			},
			...mapActions([
				'initIndexList',
				'initCount'
			]),
			...mapMutations({
				saveScrollY: 'SAVE_SCROLLY',
				setIndex: 'SET_INDEX'
			})
		},
		watch: {
			$route (val, oldVal) {
				if (oldVal.name === 'index' && (val.name === 'profile' || val.name === 'publish')) {
					this.initIndexList(0)
					this.saveScrollY({
						name: 'index',
						pos: 0
					})
					this.setIndex(-1)
				}
			}
		},
		computed: {
			...mapGetters(['indexList', 'count', 'profile'])
		}
	}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
	@import "../common/stylus/variable.styl"
	.index
		position fixed
		top 0
		bottom 50px
		width 100%
		.header
			height 32px
			margin 0
			padding 10px 6px 4px 6px
			text-align center
			font-size 0
			// box-shadow 1px 1px 4px 1px #f2f2f2
			background $color-background
			div
				display inline-block
				margin 0 5px
				padding 5px 0
				color $color-icon
				letter-spacing 1.6px
				background $color-background
				outline none
				font-size 18px
				&.router-link-active
					color $color-theme
					border-bottom 2px solid $color-theme
		.list
			width 100%
			position absolute
			top 0
			bottom 0
		.add-button
			position fixed
			background $color-background-d
			box-shadow 3px 3px 6px 0px #B8B8B8
			bottom 15%
			right 10%
			font-size 24px
			border-radius 50%
			padding 10px
			padding-bottom 8px
			color $color-theme-d
</style>
