import * as types from '../mutation-types'
import api from '@/api/api'
import {showTip} from '@/common/js/common'
import router from '@/router/index'
import store from '@/store/index'
const state = {
}

const getters = {
}

const actions = {
	login({commit}, payload) {
		api.login(payload, (data) => {
			if (data) {
				store.dispatch('initProfile')
				store.dispatch('initCount')
				store.dispatch('initIndexList', 0)
				setTimeout(() => {
					router.replace({name: 'profile'})
				}, 500)
				setTimeout(() => {
					commit(types.SHOW_TOP_TIP, '登录成功！')
				}, 700)
			} else {
				showTip('登录失败！')
			}
		})
	},
	logout({commit}) {
		commit(types.SET_PROFILE_NULL)
		api.logout((data) => {
		})
	}
}

const mutations = {
}

export default {
	state,
	getters,
	actions,
	mutations
}