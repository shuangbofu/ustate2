<template>
	<div class="stmt-item">
		<prof-line :profile="item.profile" :type="1">
			<div v-if="!isDetail" class="more" slot="more" @click="showMoreSheet">
				<i class="icon-more10"></i>
			</div>
		</prof-line>
		<div class="text" @click.stop.prevent="enterDetail(item.id)">{{close && !isDetail && item.more ? closeContent :item.content}}</div>
		<div class="readMore" @click.stop.prevent="readMore" v-if="item.more && !isDetail">{{close ?'展开全文':'收起'}}</div>
		<div class="foot-line">
			<format-time :time="item.createTime"></format-time>
			<like :id="item.id" :liked="item.liked" :count="item.likeCount" :index="index" :type="1" :isDetail="isDetail"></like>
			<div class="comments" :class="{'active': item.commentCount > 0}" @click.stop.prevent="enterDetail(item.id)">
				<i class="icon" :class="{'icon-comment43':item.commentCount >0, 'icon-comment42': item.commentCount <=0}"></i>
				<span class="number">{{item.commentCount}}</span>
			</div>
		</div>
	</div>
</template>

<script>
	import {mapMutations} from 'vuex'
	import ProfLine from '@/components/prof-line'
	import FormatTime from '@/components/format-time'
	import like from '@/components/like'
	export default {
		data () {
			return {
				close: true
			}
		},
		props: {
			item: {
				type: Object
			},
			index: {
				type: Number,
				default: -1
			},
			isDetail: {
				type: Boolean,
				default: false
			}
		},
		components: {
			ProfLine,
			FormatTime,
			like
		},
		methods: {
			enterDetail (id) {
				if (!this.isDetail) {
					this.setIndex(this.index)
					this.initListType(this.$route.name)
					this.$router.push({name: 'detail', params: {id: id}})
				}
			},
			showMoreSheet () {
				this.setIndex(this.index)
				this.setActions({
					id: this.item.id,
					author: this.item.author,
					type: 1
				})
				this.showSheet()
			},
			readMore () {
				this.close = !this.close
			},
			...mapMutations({
				setIndex: 'SET_INDEX',
				initListType: 'INIT_LIST_TYPE',
				showSheet: 'SHOW_SHEET',
				setActions: 'SET_ACTIONS'
			})
		},
		computed: {
			closeContent () {
			let str = this.item.content
				let count = 0
				let index = 0
				while (count < 200 && index < this.item.content.length) {
					let s = str.substring(0, 1)
					str = str.substring(1)
					s === '\n' ? count += 18 : count++
					index++
				}
				return this.item.content.slice(0, index) + '......'
			}
		}
	}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
	@import "../common/stylus/variable.styl"
	.stmt-item
		word-break break-word
		padding 10px 20px 4px 20px
		border-bottom 10px solid $color-split
		.text
			padding:10px 0 6px 0
			font-size 17px
			line-height 1.5
			white-space pre-wrap
		.readMore
			display inline-block
			padding 2px 18px
			font-size 14px
			background $color-background
			border 1px solid $color-icon
			color $color-icon
			border-radius 20px
			text-align center
			cursor pointer
			line-height 25px
		.foot-line
			padding 6px 0
			.comments
				font-size 18px
				float right
				margin-right 10px
				display inline-block
				color #8F8F8F
				&.active
					color $color-theme
			.number
				vertical-align top
				font-size 18px
</style>