<template>
	<transition name="slide">
		<div class="USend">
			<u-header :title="title">
					<i slot="back" class="icon-cross"></i>
				<div slot="right" class="right send">
					<i class="icon-checkmark" @click="doSend()"></i>
				</div>
			</u-header>
			<div class="publish-content">
				<div class="text">
					<textarea class="area" :placeholder="placeholder" v-model="content">
					</textarea>
				</div>
				<div style="padding: 10px 20px; ">
					<slot>
					</slot>
					<div class="count" :class="{'over': count < 0}">
						字数：<h2>{{count}}</h2>
					</div>
				</div>
			</div>
		</div>
	</transition>
</template>

<script>
	import UHeader from '@/components/u-header'
	import {mapMutations, mapActions} from 'vuex'
	export default {
		props: {
			title: '',
			placeholder: '',
			maxCount: 0
		},
		data () {
			return {
				content: ''
			}
		},
		methods: {
			doSend () {
				if (this.content.length === 0) {
					this.showTopTip(this.title.substring(0, 2) + '内容不能为空！')
					return
				} else if (this.count < 0) {
					this.showTopTip(this.title.substring(0, 2) + '内容超过字数限制！')
					return
				}
				this.$emit('send', this.content)
				this.$router.back()
			},
			...mapMutations({
				showTopTip: 'SHOW_TOP_TIP'
			}),
			...mapActions(['initProfile'])
		},
		components: {
			UHeader
		},
		activated () {
			this.content = ''
			this.initProfile()
		},
		computed: {
			count () {
				return this.maxCount - this.content.length
			}
		}
	}
</script>
<style scoped lang="stylus" rel="stylesheet/stylus">
	@import "../common/stylus/variable.styl"
	.USend
		position: fixed
		top: 0
		bottom: 0
		z-index: 50
		width: 100%
		height 100%
		background $color-background-d2
		overflow-x hidden
		&.slide-enter-active, &.slide-leave-active
			transition: all 0.3s
		&.slide-enter, &.slide-leave-to
			transform: translate3d(0, 100%, 0)
		.publish-content
			width 100%
			position absolute
			top 42px
			left 0px
			box-sizing border-box
			.text
				height 100%
				.area
					padding 10px 6px
					width 100%
					box-sizing border-box
					overflow hidden
					width 100%
					font-size 16px
					border none
					resize none
					outline none
					height 180px
					background #fff
					overflow-y scroll
			.count
				float right
				flex 1
				padding 3px 0
				color $color-forbid
				&.over
					h2
						color red
				h2
					// margin-right 16px
					// color $color-forbid
					font-size 18px
					float right
</style>