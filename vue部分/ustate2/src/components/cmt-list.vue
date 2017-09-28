<template>
	<u-scroll class="cmt-list">
		<ul>
			<slot></slot>
			<li v-for="(item, index) in data" class="cmts">
				<cmt-item :item="item" :index="index"></cmt-item>
			</li>
		</ul>
	</u-scroll>
</template>

<script>
	import UScroll from '@/components/u-scroll'
	import CmtItem from '@/components/cmt-item'
	export default {
		props: {
			data: {
				type: Array,
				default: []
			}
		},
		components: {
			UScroll,
			CmtItem
		}
	}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
	@import "../common/stylus/variable.styl"
	.cmt-list
		width 100%
		height 100%
		overflow-y scroll
		.cmts
			padding 0 20px
</style>
