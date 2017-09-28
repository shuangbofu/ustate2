import * as types from '../mutation-types'
const state = {
	topTipVisible: false,
	message: ''
}

const getters = {
	topTipVisible: state => state.topTipVisible,
	message: state => state.message
}

const actions = {
}

const mutations = {
	[types.SHOW_TOP_TIP] (state, data) {
		state.message = data
		state.topTipVisible = true
		setTimeout(() => {
			state.topTipVisible = false
		}, 1000)
	}
}

export default {
	state,
	getters,
	actions,
	mutations
}