import * as types from '../mutation-types'
const state = {
	scrollRecord: {}
}

const getters = {
	scrollRecord: state => state.scrollRecord
}

const actions = {
}

const mutations = {
	[types.SAVE_SCROLLY](state, data) {
		if (data.name === 'index') {
			state.scrollRecord.index = data.pos
		} else if (data.name === 'hot') {
			state.scrollRecord.hot = data.pos
		} else if (data.name === 'posted') {
			state.scrollRecord.posted = data.pos
		} else if (data.name === 'liked') {
			state.scrollRecord.liked = data.pos
		} else if (data.name === 'notice') {
			state.scrollRecord.notice = data.pos
		}
	}
}

export default {
	state,
	getters,
	actions,
	mutations
}