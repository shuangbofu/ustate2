import * as types from '../mutation-types'
const state = {
	loading: false,
	loadedAll: false
}

const getters = {
	loading: state => state.loading,
	loadedAll: state => state.loadedAll
}

const actions = {
}

const mutations = {
	[types.SET_LOADING] (state, data) {
		state.loading = data
	},
	[types.SET_LOADEDALL] (state, data) {
		state.loadedAll = data
	}
}

export default {
	state,
	getters,
	actions,
	mutations
}