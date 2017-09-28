<template>
		<div class="like" @click.stop.prevent="toggle()" :class="{'active': liked}">
			<i class="icon" :class="{'icon-heart280':liked, 'icon-heart281':!liked, 'icon-smile2':liked && type ===2,  'icon-smile': !liked && type === 2, 'comment': type === 2}"></i>
			<span class="number" :class="{'comment': type === 2}">{{count}}</span>
		</div>
</template>

<script>
import {mapActions, mapMutations} from 'vuex'
export default {
	props: {
		count: {
			type: Number,
			default: 0
		},
		id: {
			type: Number,
			default: -1
		},
		liked: {
			default: 0
		},
		index: {
			type: Number,
			default: -1
		},
		type: {
			type: Number,
			default: -1
		},
		isDetail: {
			type: Boolean,
			default: false
		}
	},
	data () {
		return {
		}
	},
	methods: {
		toggle () {
			let realIndex = this.index
			if (this.type === 1) {
				if (this.isDetail) {
					realIndex = this.$store.getters.index
				} else {
					this.initListType(this.$route.name)
				}
			}
			this.toggleLiked({
				liked: this.liked,
				type: this.type,
				id: this.id,
				index: realIndex
			})
		},
		...mapActions(['toggleLiked']),
		...mapMutations({
			initListType: 'INIT_LIST_TYPE',
			setIndex: 'SET_INDEX'
		})
	}
}
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
	@import "../common/stylus/variable.styl"
	.like
		color #999999
		font-size 17px
		line-height 18px
		float right
		display inline-block
		.comment
			font-size 18px
		&.active
			color $color-like
			.comment
				color $color-theme
		.number
			vertical-align top
			font-size 18px
			text-align center
</style>
