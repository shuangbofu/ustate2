<template>
	<transition name="slide">
		<div class="notice">
			<u-header title="消息"></u-header>
			<div class="notice-content">
				<u-scroll class="notice-list" @loadMore="loadMore" :length="notices.length">
					<ul>
						<li v-for="note in notices" class="note-item">
							<div class="content-header">
								<i :class="{'icon-heart280' :note.action === 1, 'icon-comment43':note.action%2===0, 'icon-smile2': note.action === 3}"></i>
								<h2 v-if="note.action === 2 || note.action === 4">有人回复了你</h2>
								<h2 v-if="note.action === 3">有人赞了你的评论</h2>
								<h2 v-if="note.action === 1">有人赞了你的发表</h2>
								<format-time :time="note.createTime" complete="true"></format-time>
							</div>
							<div v-if="note.action === 2 || note.action === 4" class="reply">
								<h2>{{note.content | commentContentFilter}}</h2>
							</div>
							<div class="content" @click="enterDetail(note.rootId)">
								<h2>{{note.originContent !== '' ? note.originContent : '该内容已被删除' | commentContentFilter}}</h2>
							</div>
						</li>
					</ul>
					<div v-if="!notices.length" class="loading-container">
						<loading></loading>
					</div>
				</u-scroll>
			</div>
		</div>
	</transition>
</template>

<script>
	import {mapMutations, mapGetters, mapActions} from 'vuex'
	import UScroll from '@/components/u-scroll'
	import UHeader from '@/components/u-header'
	import FormatTime from '@/components/format-time'
	import Loading from '@/components/loading'
	export default {
		data () {
			return {
				notice: []
			}
		},
		components: {
			UScroll,
			FormatTime,
			Loading,
			UHeader
		},
		created () {
			this._initData()
			this.resetStatus()
		},
		activated () {
			this.initListType('')
		},
		methods: {
			_initData() {
				this.initNotices(0)
			},
			loadMore() {
				let id = this.notices[this.notices.length - 1].id
				this.initNotices(id)
			},
			enterDetail (id) {
				this.setIndex(-1)
				this.$router.push({name: 'detail', params: {id: id}})
			},
			...mapMutations({
				setIndex: 'SET_INDEX',
				resetStatus: 'RESET_STATUS',
				saveScrollY: 'SAVE_SCROLLY',
				initListType: 'INIT_LIST_TYPE'
			}),
			...mapActions(['initNotices'])
		},
		computed: {
			...mapGetters(['notices'])
		},
		filters: {
			commentContentFilter (value) {
				return value && value.replace(/\n/g, ' ')
			}
		},
		watch: {
			$route (val, oldVal) {
				if (val.name === 'notice' && oldVal.name === 'profile') {
					this.initNotices(0)
					this.resetStatus()
					this.saveScrollY({
						name: 'notice',
						pos: 0
					})
				}
			}
		}
	}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
	@import '../common/stylus/variable'
	.notice
		position fixed
		top 0
		bottom 0
		width 100%
		background $color-background
		z-index 40
		&.slide-enter-active, &.slide-leave-active
			transition: all .3s
		&.slide-enter, &.slide-leave-to
			transform: translate3d(100%, 0, 0)
		.notice-content
			position fixed
			top 42px
			bottom 0
			width 100%
			.notice-list
				position relative
				height 100%
				width 100%
				overflow-y scroll
				.note-item
					padding 20px 20px
					border-bottom 1px solid $color-split2
					font-size 15px
					box-sizing border-box
					.content-header
						padding 2px 0 10px
						.time
							float right
							vertical-align top
							font-size 14px
							line-height 10px
						h2, i
							color 	rgba(0, 0, 0, .6)
							display inline-block
							letter-spacing 1.6px
							vertical-align top
						.icon-heart280
							color $color-like
						.icon-comment43, .icon-smile2
							color $color-theme
					.reply
						margin 4px 0 4px 6px
						padding-bottom 6px
						letter-spacing .3px
						line-height 1.5
						color rgba(0, 0, 0, .8)
						white-space pre-wrap
					.content
						padding 8px
						border-radius 10px
						background $color-background-d2
						white-space pre-wrap
						h2
							letter-spacing 1.8px
							color #8C8C8C				
							max-height: 50px;
							line-height: 22px;
							display: -webkit-box;
							-webkit-box-orient: vertical;
							-webkit-line-clamp: 2;
							overflow: hidden;
							text-overflow: ellipsis;
				.loading-container
					position fixed
					top 0
					left 0
					background #f2f2f2
					height 100%
					width 100%
</style>
