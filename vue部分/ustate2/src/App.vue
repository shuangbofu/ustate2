<template>
	<div id="app">
		<keep-alive>
			<router-view></router-view>
		</keep-alive>
		<u-tab></u-tab>
		<action-sheet></action-sheet>
		<top-tip></top-tip>
	</div>
</template>

<script>
	import UTab from '@/components/u-tab'
	import ActionSheet from '@/components/action-sheet'
	import topTip from '@/components/top-tip'
	export default {
		components: {
			UTab,
			ActionSheet,
			topTip
		}
	}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
</style>
