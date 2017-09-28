<template>
	<div class="header">
		<div class="back" @click="back">
			<slot name="back"><i class="icon-arrow-left2"></i></slot>
		</div>
		<div class="title">
			<h1>{{title}}</h1>
		</div>
		<slot name="right">
			<div class="default"></div>
		</slot>
	</div>
</template>

<script>
	export default {
		props: {
			title: ''
		},
		methods: {
			back() {
				this.$router.back()
			}
		}
	}
</script>
<style scoped lang="stylus" rel="stylesheet/stylus">
	@import "../common/stylus/variable.styl"
	.header
		position fixed
		top 0
		left 0
		z-index 50
		background $color-background
		display flex
		align-items center
		width 100%
		padding 10px
		color #fff
		font-size 0px
		height 42px
		box-shadow 1px 1px 4px 1px #f2f2f2
		box-sizing border-box
		.back, .title, .right, .default
			flex 1
			font-size 18px
			margin-left 4px !important
			color $color-theme
		.back
			margin -2.5px
			font-size 22px
		.icon-cross
			line-height 22px
			font-size 16px
		.title
			flex 1
			text-align center
		.right
			i
				float right
				font-size 20px
				margin-right 3px
			.icon-more10
				font-size 22px
			button
				outline none
				border none
				background $color-background
				color $color-theme
				margin -3.5px 0
				font-size 24px
</style>