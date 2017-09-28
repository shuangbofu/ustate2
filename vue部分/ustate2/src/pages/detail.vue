<template>
	<transition name="slide">
		<div class="detail">
			<u-header title="Ustate正文">
				<div slot="right" class="right" @click="showMoreSheet">
					<i class="icon-more10"></i>
				</div>
			</u-header>
			<div class="detail-content" ref="detailWrapper">
				<cmt-list :data="currComments">
					<stmt-item :item="currStatement" :isDetail="true"></stmt-item>	
				</cmt-list>
			</div>
			<div class="reply">
				<textarea class="area" @focus="textareaFocus" placeholder="发表评论" v-model="content" ref="areaScroll"></textarea>
				<button class="send" @click="sendComment" :class="{'activated': content.length > 0}">发送</button>
			</div>
			<div v-if="!currStatement.id" class="loading-container">
				<loading></loading>
			</div>
		</div>
	</transition>
</template>

<script>
	import {mapGetters, mapActions, mapMutations} from 'vuex'
	import StmtItem from '@/components/stmt-item'
	import UHeader from '@/components/u-header'
	import CmtList from '@/components/cmt-list'
	import Loading from '@/components/loading'
	export default {
		data () {
			return {
				content: '',
				data: {
					type: Object,
					default: null
				}
			}
		},
		created () {
			this._initData()
			this.flag = true
		},
		activated () {
			this.flag && this.setLoadedAll(true)
			!this.flag && this._initData()
			this.flag = false
			this.$refs.areaScroll.style.height = '22px'
			this.flag2 = true
		},
		deactivated () {
			this.content = ''
		},
		components: {
			StmtItem,
			UHeader,
			CmtList,
			Loading
		},
		methods: {
			_initData() {
				let id = this.$route.params.id
				if (this.listType === '') {
					this.initDetailStatement(id)
					this.initComments(id)
				} else {
					if (this.$store.getters.index === -1) {
						this.initDetailStatement(id)
					}
					if (this.currStatement.comments === undefined) {
						this.initComments(id)
					}
				}
			},
			showMoreSheet () {
				this.setActions({
					id: this.currStatement.id,
					author: this.currStatement.author,
					type: 1
				})
				this.showSheet()
			},
			textareaFocus () {
				this.initProfile()
			},
			sendComment () {
				if (this.content === '') {
					this.showTopTip('评论内容不能为空！')
					return
				}
				this.addComment({
					content: this.content,
					rootId: this.currStatement.id,
					parentId: 0
				})
				this.content = ''
			},
			...mapActions([
				'initDetailStatement',
				'initComments',
				'addComment',
				'initProfile',
				'replyComment',
				'initCount'
			]),
			...mapMutations({
				showSheet: 'SHOW_SHEET',
				setActions: 'SET_ACTIONS',
				setLoadedAll: 'SET_LOADEDALL',
				showTopTip: 'SHOW_TOP_TIP'
			})
		},
		computed: {
			currComments () {
				if (this.currStatement.comments !== undefined) {
					return this.currStatement.comments
				} else {
					return this.comments
				}
			},
			...mapGetters([
				'currStatement',
				'comments',
				'actions',
				'isGetCount',
				'profile',
				'listType'
			])
		},
		watch: {
			$route (val, oldVal) {
				if ((val.name === 'index' || val.name === 'hot') && oldVal.name === 'detail') {
					if (this.isGetCount && this.profile.id !== undefined) {
						this.initCount()
					}
				}
				if (val.name === 'detail' && oldVal.name === 'reply') {
					this.flag = true
				}
			}
		}
	}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
	@import "../common/stylus/variable.styl"
	.detail
		position fixed
		top 0
		bottom 0
		width 100%
		height 100%
		z-index 21
		background $color-theme-d
		&.slide-enter-active, &.slide-leave-active
			transition: all .3s
		&.slide-enter, &.slide-leave-to
			transform: translate3d(100%, 0, 0)
		.detail-content
			width 100%
			position absolute
			top 42px
			bottom 55px
		.reply
			position fixed
			bottom 0
			width 100%
			height 55px
			border-top 1px solid $color-border-bottom1
			background $color-background
			padding 10px 12px
			box-sizing border-box
			display flex
			align-items center
			.area
				overflow auto
				border-bottom 1px solid $color-forbid
				flex 1 1 auto
				background-color rgb(242, 242, 242)
				border-radius 5px
				border none
				outline none
				resize none
				padding 6px
				margin-right 8px
				font-size 17px
			.send, .area
				display inline-block		
			.send
				padding 0
				flex 0 1 38px
				height 100%
				outline none
				border none
				color $color-forbid
				font-size 18px
				border-radius 10%
				background $color-background
				&.activated
					color $color-theme
		.loading-container
			position: absolute
			width: 100%
			top: 50%
			transform: translateY(-50%)
</style>