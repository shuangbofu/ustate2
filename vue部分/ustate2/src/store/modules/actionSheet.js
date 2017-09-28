import * as types from '../mutation-types'
const state = {
	sheetVisible: false,
	actions: []
}

const getters = {
	sheetVisible: state => state.sheetVisible,
	actions: state => state.actions
}

const actions = {
}

const mutations = {
	[types.SET_ACTIONS](state, payload) {
		if (payload.type !== 3) {
			state.actions = []
			if (payload.type === 2) {
				state.actions.push({
					title: '回复评论',
					icon: 'icon-comment42',
					parentId: payload.id,
					rootId: payload.rootId,
					nickname: payload.nickname
				})
			}
			if (payload.type === 1) {
				state.actions.push({
					title: '举报',
					icon: 'icon-upper7',
					id: payload.id
				})
			}
			if (payload.author) {
				let action = {
					title: '删除',
					icon: 'icon-recycling11',
					type: payload.type
				}
				if (payload.type === 1) {
					action.id = payload.id
				}
				if (payload.type === 2) {
					action.id = payload.id
					action.rootId = payload.rootId
					action.index = payload.index
				}
				state.actions.push(action)
			}
		} else {
			state.actions = [
			{
				title: '反馈',
				icon: 'icon-pencil84'
			},
			{
				title: '退出登录'
			}
			]
		}
	},
	[types.SHOW_SHEET](state) {
		state.sheetVisible = true
	},
	[types.CLOSE_SHEET](state) {
		state.sheetVisible = false
	},
	[types.SELECT_REPLY](state, data) {
		state.replySelected = data
	}
}

export default {
	state,
	getters,
	actions,
	mutations
}