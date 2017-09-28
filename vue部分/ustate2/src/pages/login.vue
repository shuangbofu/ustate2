<template>
	<div class="login">
		<div class="tip">
			选择以下方式登录Ustate
		</div>
		<div class="methods">
<!-- 			<div class="item">
				<img src="../assets/wechat-icon.png" width="25" height="25">
				使用微信登录
			</div> -->
			<!-- <div class="item" @click="removeAuth">
				<img src="../assets/wechat-icon.png" width="25" height="25">
				取消授权
			</div> -->
			<div class="item" @click="weiboLogin">
				<img src="../assets/weibo-icon.png" width="25" height="25">
				使用微博登录
			</div>
			<!-- <div class="item" @click="openPanel">
				<img src="../assets/talk.png" width="25" height="25">
				使用模拟登录
			</div> -->
			<div class="panel" v-show="show">
				<div class="list-item" v-for="item in profileList">
					<img width="40" height="40" :src="item.avatar" @click="write(item.name)">
					{{item.nickname}}
				</div>
				<div class="name">
					用户名：<input type="text" v-model="name"></input>
				</div>
				<div class="password">
					密码：<input type="password" v-model="password"></input>
				</div>
				<button @click="doLogin">登录</button>

				<h2 style="color: rgba(0, 0, 0, .6);">点击头像自动填写用户名密码</h2>
				<i class="icon-close" @click="closePanel"></i>
			</div>
		</div>
	</div>
</template>
<script>
	import {mapActions, mapGetters} from 'vuex'
	export default {
		data () {
			return {
				name: '',
				password: '',
				show: false,
				profileList: [
					{
						avatar: 'http://tva4.sinaimg.cn/crop.0.0.512.512.180/473df571jw8eku7gao4irj20e80e8dg7.jpg',
						nickname: '杨幂',
						name: 'mi'
					},
					{
						avatar: 'http://tva3.sinaimg.cn/crop.0.1.750.750.180/49dd64e2jw8f0ebj1wrlpj20ku0kw74z.jpg',
						nickname: '薛之谦',
						name: 'joker'
					},
					{
						avatar: 'http://tva2.sinaimg.cn/crop.50.50.126.126.180/7049c17bjw8eujf8ahs4zj206b06bt8n.jpg',
						nickname: 'NBA',
						name: 'nba'
					},
					{
						avatar: 'http://tvax3.sinaimg.cn/crop.0.0.1024.1024.180/c5131475ly8fdl70vs48jj20sg0sgjsi.jpg',
						nickname: '慕课网',
						name: 'mooc'
					},
					{
						avatar: 'http://tva1.sinaimg.cn/crop.0.0.179.179.180/e2a6d962jw1e9flgxbcpdj205005074i.jpg',
						nickname: '洛杉矶湖人',
						name: 'lakers'
					},
					{
						avatar: 'http://tva2.sinaimg.cn/crop.6.0.499.499.180/67e01e50jw8f83za3ayspj20e80dvaab.jpg',
						nickname: '林宥嘉',
						name: 'yoga'
					},
					{
						avatar: 'http://tva1.sinaimg.cn/crop.0.0.1242.1242.180/67a7c045jw8fakipihuz1j20yi0yiabw.jpg',
						nickname: '李荣浩',
						name: 'lee'
					}
				]
			}
		},
		created () {
			// if (this.profile.id === undefined) {
			// 	this.initProfile()
			// }
		},
		activated () {
			this.show = false
			this.password = this.name = ''
		},
		methods: {
			openPanel () {
				this.show = true
			},
			closePanel () {
				this.show = false
			},
			write (str) {
				this.name = str
				this.password = str + '0000'
			},
			doLogin () {
				this.login({
					name: this.name,
					password: this.password
				})
			},
			weiboLogin () {
				window.location.href = 'http://www.ustate.cn:8080/ustate2/weiboLogin'
			},
			removeAuth () {
				window.location.href = 'http://www.ustate.cn:8080/ustate2/removeAuth'
			},
			...mapActions(['login', 'initProfile'])
		},
		computed: {
			...mapGetters(['profile'])
		}
	}
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
@import "../common/stylus/variable.styl"
.login
	position fixed
	top 0
	bottom 42px
	width 100%
	background $color-background
	.tip
		height 42px
		text-align center
		line-height 42px
		font-size 15px
		background #f2f2f2
		color rgba(0, 0, 0, .6)
		font-weight 700
	.methods
		margin-top 20px
		display flex
		flex-direction column
		align-items center
		justify-content center
		.item
			display flex
			justify-content center
			align-items center
			padding 10px 22px
			height 40px
			border 1px solid rgba(0, 0, 0, .3)
			height 20px
			font-size 15px
			border-radius 80px
			margin-bottom 25px
			color rgba(0, 0, 0, .8)
			img
				margin-right 10px
		.panel
			position fixed
			top 0
			display flex
			flex-direction column
			align-items center
			text-align center
			background $color-background
			width 100%
			padding-top 10px
			.list-item
				margin-top 4px
			input
				margin 10px 0
				background #f2f2f2
				padding 5px
				border-radius 10px
				outline none
			button
				margin 10px
				width 60%
				border-style none
				background $color-background-d
				color $color-theme-d
				padding 10px 20px
				border-radius 10px
				outline none
			.icon-close
				margin-top 20px
				font-size 30px
				color $color-theme
				border-radius 50%
				border 2px solid $color-theme
</style>