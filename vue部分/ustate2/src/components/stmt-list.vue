<template>
	<u-scroll @loadMore="loadMore" class="stmt-list" :length="data.length">
		<slot></slot>
		<li v-for="(item, index) in data">
			<stmt-item :item="item" :index="index"></stmt-item>
		</li>
		<div v-if="!data.length" class="loading-container">
			<loading></loading>
		</div>
	</u-scroll>
</template>

<script>
	import {mapActions, mapGetters, mapMutations} from 'vuex'
	import UScroll from '@/components/u-scroll'
	import StmtItem from '@/components/stmt-item'
	import Loading from '@/components/loading'
	export default {
		created () {
		},
		props: {
			data: {
				type: Array,
				default: []
			}
		},
		components: {
			UScroll,
			StmtItem,
			Loading
		},
		methods: {
			loadMore () {
				if (!this.loading && !this.loadedAll) {
					this.$emit('loadMore')
				}
			},
			...mapActions(['initList']),
			...mapMutations({
				setLoadedall: 'SET_LOADEDALL'
			})
		},
		computed: {
			...mapGetters([
				'loading',
				'loadedAll'
			])
		}
	}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
	@import "../common/stylus/variable.styl"
	.stmt-list
		height 100%
		width 100%
		overflow scroll
		.loading-container
			position fixed
			top 0
			left 0
			background #f2f2f2
			height 100%
			width 100%
</style>
