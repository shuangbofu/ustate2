<template>
	<div class="prof-line">
		<div class="avatar">
			<img :src="profile.avatar" width="40" height="40">
		</div>
		<div class="info" :class="{'comment': type === 2, 'statement': type === 1}">
			<h2 class="name">{{profile.nickname}}</h2>
			<h2 class="gender" :class="{'female':!profile.gender>0}">{{profile.gender?'♂':'♀'}}</h2>
			<slot name="extra">
			</slot>
		</div>
		<slot name="more"></slot>
	</div>
</template>

<script>
	export default {
		props: {
			profile: {
				type: Object,
				default () {
					return {
						avatar: '',
						nickname: '',
						gender: 0
					}
				}
			},
			type: 0
		}
	}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
	@import "../common/stylus/variable.styl"
	.prof-line
		display flex
		align-items center
		.avatar
			margin-right 8px
			width 40px
			height 40px
			flex 0 0 40px
			display inline-block
			img
				border-radius 50%
		.info
			flex 1
			height 40px
			display block
			box-sizing border-box
			&.statement
				h2
					font-size 16px
					padding 15px 0
			&.comment
				font-size 14px !important
				padding 2.5px 0
			h2
				display inline-block
				line-height 16px
				&.female
					color $color-female
			.name
				color black
				margin-right 1px
			.gender
				color $color-male
		.more
			float right
			display inline-block
			color $color-theme
			.icon-more10
				font-size 20px
				color #999999
				background none
</style>