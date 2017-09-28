<template>
	<div class="sercret">
		<div class="button" @click="toggle" :class="{activated: sercret}">
			<transition name="slide1">
				<div class="left"  v-if="!sercret">
					<div class="circle"></div>
				</div>
			</transition>
			<transition name="slide2">
				<div class="right" v-if="sercret">
					<div class="circle"></div>
				</div>
			</transition>
		</div>
		<div class="sign" :class="{activated: sercret}">
			<h2 >匿名</h2>
		</div>
	</div>
</template>
<script>
	export default {
		props: {
			sercret: true
		},
		methods: {
			toggle () {
				this.$emit('toggle')
			}
		}
	}
</script>
<style scoped lang="stylus" rel="stylesheet/stylus">
@import "../common/stylus/variable.styl"
.sercret
	// margin-left 16px
	height 24px
	box-sizing border-box
	width 100px
	float left
	.button
		display inline-block
		width 44px
		height 23px
		border-radius 20px
		overflow hidden
		background $color-split2
		&.activated
			background $color-theme
		.left
			margin 1px
			border-radius 20px
			height 21px
			width 42px
			background $color-split2
			.circle
				float left
				border-radius 20px
				height 100%
				width 21px
				background $color-theme-d
				box-shadow 2px 0px 4px 0px $color-border-bottom1
			&.slide1-enter-active
				transition: all .3s
			&.slide1-enter, &.slide1-leave-to
				transform: translateX(21px)
		.right
			margin 1px
			border-radius 20px
			height 21px
			width 42px
			background $color-theme
			&.slide2-enter-active
				transition: all .3s
			&.slide2-enter, &.slide2-leave-to
				transform: translateX(-21px)
			.circle
				float right
				border-radius 20px
				height 100%
				width 21px
				background $color-theme-d
				box-shadow -2px 0px 4px 0px $color-border-bottom1
	.sign
		display inline-block
		margin-left 4px
		padding 2px 0
		color $color-forbid
		&.activated
			color $color-theme !important
		h2
			font-size 19px
			float right
</style>