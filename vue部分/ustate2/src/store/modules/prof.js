import api from '@/api/api'
import * as types from '../mutation-types.js'
import {noLoginTipFn} from '@/common/js/common'
const state = {
	profile: {
		type: Object
	},
	count: -1,
	isGetCount: true
}

const getters = {
	profile: state => state.profile,
	count: state => state.count,
	isGetCount: state => state.isGetCount
}

const actions = {
	initProfile ({commit}) {
		api.getProfile(data => {
			commit(types.INIT_PROFILE, data)
			noLoginTipFn(500, 1000)
		})
	},
	initCount ({commit}) {
		api.getNoticeCount(data => {
			commit(types.INIT_COUNT, data)
		})
	}
}

const mutations = {
	[types.INIT_PROFILE] (state, data) {
		if (data.code === 200) {
			state.profile = data.data
		}
	},
	[types.INIT_COUNT] (state, data) {
		if (data.code === 200) {
			state.count = data.data
		}
	},
	[types.RESET_STATUS] (state) {
		state.count = 0
	},
	[types.SET_GET_COUNT_FALSE] (state) {
		state.isGetCount = false
	},
	[types.SET_PROFILE_NULL] (state) {
		state.profile = {}
	}
}

export default {
	state,
	getters,
	actions,
	mutations
}