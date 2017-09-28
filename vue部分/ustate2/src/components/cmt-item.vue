<template>
	<div class="cmt-item" @click="showMoreSheet">
		<prof-line :profile="item.profile" :type="2">
			<div slot="extra" class="extra">
				<i>#{{index+1}}</i>  
				<format-time :time="item.createTime"></format-time>
			</div>
			<div class="more" slot="more">
				<div>
					<like :id="item.id" :liked="item.liked" :count="item.likeCount" :index="index" :type="2" @toggle="toggleLiked"></like>
				</div>
			</div>
		</prof-line>
		<div class="text">
			<span v-if="item.parentId>0" class="title">回复</span>
			<span v-if="item.parentId>0" class="addition">{{addition}}</span>
			<span class="reply">{{realContent | commentContentFilter}}</span>
		</div>
	</div>
</template>

<script>
	import {mapMutations} from 'vuex'
	import ProfLine from '@/components/prof-line'
	import FormatTime from '@/components/format-time'
	import like from '@/components/like'
	export default {
		props: {
			item: {
				type: Object
			},
			index: {
				type: Number,
				default: -1
			}
		},
		components: {
			ProfLine,
			FormatTime,
			like
		},
		methods: {
			showMoreSheet () {
				this.setActions({
					id: this.item.id,
					rootId: this.item.rootId,
					author: this.item.author,
					nickname: this.item.profile.nickname,
					index: this.index,
					type: 2
				})
				this.showSheet()
			},
			toggleLiked () {
				this.item.liked = !this.item.liked
				!this.item.liked ? this.item.likeCount -- : this.item.likeCount ++
			},
			...mapMutations({
				showSheet: 'SHOW_SHEET',
				setActions: 'SET_ACTIONS'
			})
		},
		computed: {
			addition () {
				let content = this.item.content
				let index = content.indexOf(':', 0)
				return this.item.content.substring(2, index)
			},
			realContent () {
				if (this.item.parentId > 0) {
					let content = this.item.content
					let index = content.indexOf(':', 0)
					return this.item.content.substring(index, content.length)
				} else {
					return this.item.content
				}
			}
		},
		filters: {
			commentContentFilter (value) {
				return value && value.replace(/\n/g, ' ')
			}
		}
	}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
	@import "../common/stylus/variable.styl"
	.cmt-item
		word-break break-word
		padding-top 10px
		border-bottom 1px solid $color-split2
		font-size 0
		.text
			margin-top 6px
			line-height 1.5
			padding-bottom 5px
			.title
				font-size 16px
			.reply
				font-size 16px
				white-space pre-wrap
			.addition
				font-size 16px
				color $color-theme
		.extra
			i
				color $color-theme
			.time
				font-size 14px !important
</style>