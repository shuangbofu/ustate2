import Vue from 'vue'
import Router from 'vue-router'

import Index from '@/pages/index'
import Hot from '@/pages/hot'
import Profile from '@/pages/profile'
import Notice from '@/pages/notice'

import Publish from '@/pages/publish'
import Detail from '@/pages/detail'
import Reply from '@/pages/reply'
import Report from '@/pages/report'
import Feedback from '@/pages/feedback'

import Posted from '@/pages/posted'
import Liked from '@/pages/liked'

import Login from '@/pages/login'

Vue.use(Router)

export default new Router({
	mode: 'history',
	base: '/ustate2/',
	routes: [
		{
			path: '/',
			redirect: '/index'
		},
		{
			path: '/index',
			name: 'index',
			component: Index
		},
		{
			path: '/hot',
			name: 'hot',
			component: Hot
		},
		{
			path: '/notice',
			name: 'notice',
			component: Notice
		},
		{
			path: '/profile',
			name: 'profile',
			component: Profile
		},
		{
			path: '/publish',
			name: 'publish',
			component: Publish
		},
		{
			path: '/detail/:id',
			name: 'detail',
			component: Detail
		},
		{
			path: '/posted',
			name: 'posted',
			component: Posted
		},
		{
			path: '/liked',
			name: 'liked',
			component: Liked
		},
		{
			path: '/reply',
			name: 'reply',
			component: Reply
		},
		{
			path: '/report/:id',
			name: 'report',
			component: Report
		},
		{
			path: '/feedback',
			name: 'feedback',
			component: Feedback
		},
		{
			path: '/login',
			name: 'login',
			component: Login
		}
	]
})
