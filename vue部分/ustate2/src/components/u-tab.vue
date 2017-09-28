<template>
	<div class="tab">
		<router-link tag="div" class="tab-item" to="/index" replace>
			<i class="item-icon icon-house128"></i>
		</router-link>
		<router-link tag="div" class="tab-item" to="/publish">
			<i class="item-icon icon-add153"></i>
		</router-link>
		<router-link tag="div" class="tab-item" to="/profile" replace>
			<i class="item-icon icon-user"></i>
			<div class="badge" v-if="count > 0">{{count}}</div>
		</router-link>
	</div>
</template>

<script>
	import {mapGetters} from 'vuex'
	export default {
		computed: {
			...mapGetters(['count'])
		}
	}
</script>

<style scoped lang="stylus" rel="stylsheet/stylus">
	@import "../common/stylus/variable.styl"
	.tab
		position fixed
		left 0
		bottom 0
		height 50px
		width 100%
		display flex
		align-items center
		background $color-background
		border-top 1px solid $color-border-bottom1
		z-index 20
		.tab-item
			position relative
			flex 1
			text-align center
			color $color-icon
			width 100%
			&.router-link-active
				color $color-theme
			.item-icon
				font-size $icon-size
				display inline-block
			.icon-user
				font-size 32px
			.badge
				position absolute
				margin-left -12px
				display inline-block
				top -4px
				height 20px
				width 20px
				font-size 10px
				line-height 20px
				text-align center
				border-radius 50%
				color $color-theme-d
				background rgb(239, 64, 86)
</style>
