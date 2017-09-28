<template>
	<div class="profile">
		<div class="profile-header">
			<div class="header">
				<div class="info">
					<img :src="profile.avatar" height="60" width="60" class="avatar">
					<h2 class="name">{{profile.nickname}}</h2>
					<div class="more" @click="showMoreSheet">
						<i class="icon-more10"></i>
					</div>
				</div>
				<div class="profile-record">
					<router-link tag="div" class="record line" to="/posted">
						<h2 class="title">发表</h2><h2 class="count">{{profile.postsCount}}</h2>
					</router-link>
					<router-link tag="div" class="record line" to="/liked">
						<h2 class="title">喜欢</h2><h2 class="count">{{profile.likePostsCount}}</h2>
					</router-link>
					<router-link tag="div" class="record" to="/notice">
						<h2 class="title">消息</h2><h2 class="count">{{profile.noticeTotalCount}}</h2>
					</router-link>
				</div>
			</div>
			<div class="notice" v-if="count > 0">
				<div class="main">
					<router-link tag="div" to="/notice">
						<i class="icon-bell"></i>
						<h2>{{count}}条新消息</h2>
						<i class="icon-keyboard_arrow_right"></i>
					</router-link>
				</div>
			</div>
		</div>
		<div class="moreServices">
			<div class="title-bar">
				发现更多
			</div>
		</div>
		<div v-if="!profile.id" class="loading-container">
			<loading></loading>
		</div>
	</div>
</template>

<script>
	import {mapGetters, mapActions, mapMutations} from 'vuex'
	import Loading from '@/components/loading'
	import {noLoginTipFn} from '@/common/js/common'
	export default {
		created() {
			this._initData()
			this.flag = false
		},
		activated () {
			if (this.flag) {
				noLoginTipFn(0, 0, () => {
				})
			}
			this.flag = true
		},
		methods: {
			showMoreSheet () {
				this.setActions({
					type: 3
				})
				this.showSheet()
			},
			_initData() {
				this.setFalse()
				this.initProfile()
				this.initCount()
			},
			...mapActions([
				'initProfile',
				'initCount'
			]),
			...mapMutations({
				showSheet: 'SHOW_SHEET',
				setActions: 'SET_ACTIONS',
				setFalse: 'SET_GET_COUNT_FALSE'
			})
		},
		computed: {
			...mapGetters([
				'profile',
				'count'
			])
		},
		components: {
			Loading
		},
		watch: {
			$route(val, oldVal) {
				if (val.name === 'profile' && oldVal.name === 'login') {
					this.initProfile()
				}
			}
		}
	}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
	@import "../common/stylus/variable.styl"
.profile
	position absolute
	top 0
	bottom 50px
	width 100%
	display flex
	flex-direction column
	align-items center
	overflow-x hidden
	.profile-header
		width 100%
		display flex
		flex-direction column
		align-items center
		padding-bottom 6px
		.header
			width 100%
			display flex
			flex-direction column
			align-items center
			padding 16px 0 12px
			position relative
			.info
				display flex
				flex-direction column
				align-items center
				.avatar
					text-align center
					border-radius 50%
					margin-bottom 12px
				.more
					position absolute
					top 7px
					right 20px
					background $color-background
					vertical-align middle
					color rgba(0, 0, 0, .6)
					font-size 23px
			.profile-record
				width 100%
				padding-top 15px
				display flex
				background $color-background
				.record
					flex 1
					font-size 16px
					color #999
					padding 4px 0
					text-align center
					h2
						display inline-block
						color rgba(0, 0, 0, 0.8)
					.count
						margin-left 10px
						font-size 18px
					.title
						color rgba(0, 0, 0, 0.5)
				.line
					border-right 1px solid rgba(0, 0, 0, 0.2)
		.notice
			padding-bottom 12px
			display flex
			align-items center
			font-size 15px
			color #fff
			background $color-background
			.main
				background rgba(0, 0, 0, 0.4)
				margin 0 auto
				// width 120px
				display flex
				align-items center
				padding 9px 2px 8px 11px
				border-radius 6px
				font-size 0
				.icon-bell
					flex 1
					display inline-block
					font-size 14px
					line-height 15px
					margin-right 6px
					vertical-align top
				h2
					flex 1
					font-size 15px
					line-height 13px
					display inline-block
					letter-spacing 1.2px
					vertical-align top
				.icon-keyboard_arrow_right
					flex 1
					font-size 21px
					line-height 14px
					vertical-align top
	.moreServices
		width 100%
		.title-bar
			width 100%
			padding-top 16px
			background #fafafa
			color #949494
			padding 10px
			font-size 16px
	.loading-container
		position fixed
		top 0
		left 0
		background #f2f2f2
		height 100%
		width 100%
</style>
