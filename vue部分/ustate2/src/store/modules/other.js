import api from '@/api/api'
// import * as types from '../mutation-types'
import {showTip} from '@/common/js/common'
const state = {
}

const getters = {
}

const actions = {
	sendReport ({commit}, payload) {
		api.addReport(payload, data => {
			showTip('举报成功！')
		})
	},
	sendFeedback ({commit}, payload) {
		api.addFeedback(payload, data => {
			showTip('反馈成功！')
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