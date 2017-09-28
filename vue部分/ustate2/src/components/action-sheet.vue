<template>
	<div class="actionSheet">
		<transition name="fold">
			<div class="main" v-if="sheetVisible">
				<div class="content">
					<div class="actions">
						<div v-for="item in actions" class="action-item" v-if="item">
							<div @click="doFc(item)">
							<i :class="item.icon" style="margin-right: 4px"></i>
							<h2>{{item.title}}</h2>
							</div>
						</div>
					</div>
					<div class="cancel" @click="closeSheet">
						<h2>取消</h2>
					</div>
				</div>
			</div>
		</transition>
		<transition name="fade">
			<div class="mask" v-if="sheetVisible" @click="closeSheet" ></div>
		</transition>
	</div>
</template>

<script>
	import {mapGetters, mapMutations, mapActions} from 'vuex'
	export default {
		methods: {
			doFc (item) {
				if (item.title === '删除') {
					this.doDelete(item)
				} else if (item.title === '举报') {
					this.doReport(item)
				} else if (item.title === '回复评论') {
					this.doReply()
				} else if (item.title === '反馈') {
					this.doFeedback()
				} else if (item.title === '退出登录') {
					this.logOut()
				}
				this.closeSheet()
			},
			doDelete (item) {
				if (this.$route.name !== 'detail') {
					this.initListType(this.$route.name)
				}
				item.type === 1 ? this.deleteStatement(item.id) : this.deleteComment({
					id: item.id,
					rootId: item.rootId,
					index: item.index
				})
			},
			doReport (item) {
				this.$router.push({name: 'report', params: {id: item.id}})
			},
			doReply () {
				this.$router.push({name: 'reply'})
			},
			doFeedback () {
				this.$router.push({name: 'feedback'})
			},
			logOut () {
				this.logout()
				this.$router.replace({name: 'index'})
			},
			...mapActions([
				'deleteStatement',
				'deleteComment',
				'logout'
			]),
			...mapMutations({
				closeSheet: 'CLOSE_SHEET',
				initListType: 'INIT_LIST_TYPE'
			})
		},
		computed: {
			...mapGetters([
				'sheetVisible',
				'actions'
			])
		},
		watch: {
			$route() {
				this.closeSheet()
			}
		}
	}
</script>
<style scoped lang="stylus" rel="stylesheet/stylus">
.actionSheet
	.main
		position fixed
		left 0px
		bottom 0px
		z-index 50
		width 100%
		transform translate3d(0, 0, 0)
		&.fold-enter-active, &.fold-leave-active
			transition all .4s
		&.fold-enter, &.fold-leave-active
			transform  translate3d(0, 100%, 0)
		.content
			background #E0E0E0
			color rgba(0, 0, 0, .8)
			line-height 45px
			text-align center
			font-size 17px
			.actions
				margin-bottom 5px
				.action-item
					height 45px
					width 100%
					background #fff
					border-bottom 1px solid #e0e0e0
					i, h2
						vertical-align middle
						display inline-block
				:last-child
					border none
			.cancel
				height 45px
				width 100%
				font-size 17px
				background #fff
	.mask
		position fixed
		top 0px	
		left 0px
		width 100%
		height 100%
		z-index 40
		background #000
		opacity 1
		background rgba(2, 17, 27,  .6)
		&.fade-enter-active, &.fade-leave-active
			transition all 0.4s
		&.fade-enter, &.fade-leave-active
			opacity 0
			background rgba(7, 17, 27, 0)
</style>