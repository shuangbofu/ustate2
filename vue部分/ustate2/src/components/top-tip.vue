<template>
	<transition name="slide">
		<div class="topTip" v-if="topTipVisible">
			<h2>{{message}}</h2>
		</div>
	</transition>
</template>
<script>
	import {mapGetters} from 'vuex'
	export default {
		computed: {
			...mapGetters([
				'message',
				'topTipVisible'
			])
		}
	}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
	@import "../common/stylus/variable.styl"
	.topTip
		position fixed
		top 0
		z-index 52
		height 42px
		width 100%
		background rgba(38, 162, 255, .6)
		&.slide-enter-active, &.slide-leave-active
			transform  translate3d(0,  0, 0)
			transition all .2s linear
		&.slide-enter, &.slide-leave-active
			transform translate3d(0, -100%,  0)
		h2
			color $color-theme-d
			text-align center
			line-height 42px
			font-size 15px
			font-weight 700
</style>