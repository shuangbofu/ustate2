import * as types from '../mutation-types'
import api from '@/api/api'
import {commonFn} from '@/common/js/common'
const state = {
	notices: []
}

const getters = {
	notices: state => state.notices
}

const actions = {
	initNotices({commit}, fromId) {
		fromId !== 0 && commit(types.SET_LOADING, true)
		api.getNotices(fromId, data => {
			commit(types.INIT_NOTICES, {data, fromId})
			commonFn({commit}, fromId, data)
		})
	}
}

const mutations = {
	[types.INIT_NOTICES] (state, {data, fromId}) {
		if (data.code === 200) {
			if (fromId !== 0) {
				state.notices = state.notices.concat(data.data)
			} else {
				state.notices = data.data
			}
		}
	}
}

export default {
	state,
	getters,
	actions,
	mutations
}