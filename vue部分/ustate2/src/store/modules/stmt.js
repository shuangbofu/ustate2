import api from '@/api/api'
import * as types from '../mutation-types'
import {commonFn, noLoginTipFn, createStatement, createComment, showTip} from '@/common/js/common'
import router from '@/router/index'
const state = {
	indexList: [],
	hotList: [],
	postedList: [],
	likedList: [],
	index: -1,
	listType: '',
	detailStatement: {
		type: Object
	},
	comments: []
}

const getters = {
	indexList: state => state.indexList,
	hotList: state => state.hotList,
	postedList: state => state.postedList,
	likedList: state => state.likedList,
	currStatement (state) {
		if (state.index === -1 || state.listType === '') {
			return state.detailStatement
		} else {
			if (state.listType === 'posts') {
				return state.indexList[state.index]
			} else if (state.listType === 'hot') {
				return state.hotList[state.index]
			} else if (state.listType === 'liked') {
				return state.likedList[state.index]
			} else {
				return state.postedList[state.index]
			}
		}
	},
	comments: state => state.comments,
	index: state => state.index,
	listType: state => state.listType
}

const actions = {
	initIndexList ({commit}, fromId) {
		fromId !== 0 && commit(types.SET_LOADING, true)
		api.getIndexList(fromId, data => {
			commit(types.INIT_INDEX_LIST, {data, fromId})
			commonFn({commit}, fromId, data)
		})
	},
	initHotList ({commit}, fromId) {
		fromId !== 0 && commit(types.SET_LOADING, true)
		api.getHotList(fromId, data => {
			commit(types.INIT_HOT_LIST, {data, fromId})
			commonFn({commit}, fromId, data)
		})
	},
	initPostedList ({commit}, fromId) {
		fromId !== 0 && commit(types.SET_LOADING, true)
		api.getPostedList(fromId, data => {
			commit(types.INIT_POSTED_LIST, {data, fromId})
			commonFn({commit}, fromId, data)
		})
	},
	initLikedList ({commit}, fromId) {
		fromId !== 0 && commit(types.SET_LOADING, true)
		api.getLikedList(fromId, data => {
			commit(types.INIT_LIKED_LIST, {data, fromId})
			commonFn({commit}, fromId, data)
		})
	},
	addStatement ({commit}, payload) {
		api.sendStatement(payload, data => {
			if (data.code === 200) {
				commit(types.ADD_STATEMENT, data)
				showTip('发布成功！')
			} else {
				showTip('发布失败！')
			}
			router.replace({name: 'index'})
		})
	},
	initDetailStatement ({commit}, id) {
		api.getDetail(id, data => {
			commit(types.INIT_DETAIL_STATEMENT, data)
			if (data.code === 500) {
				showTip('该发表已经不存在！')
			}
		})
	},
	toggleLiked ({commit}, payload) {
		if (payload.type === 1) {
			commit(types.TOGGLE_STMT_LIKED, payload)
			commit(types.UPDATE_STMT_LIKE_COUNT, payload)
		} else {
			commit(types.TOGGLE_CMT_LIKED, payload)
		}
		api.toggleLiked(payload, data => {
			if (data.code === 2015) {
				noLoginTipFn(0, 500)
			}
		})
	},
	deleteStatement ({commit}, id) {
		api.deleteStatement(id, data => {
			if (data.code === 200) {
				commit(types.DELETE_STATEMENT, data)
				showTip('删除成功！')
			} else {
				showTip('删除失败！')
			}
		})
	},
	initComments ({commit}, id) {
		api.getCommentList(id, data => {
			commit(types.INIT_COMMENTS, data)
			commit(types.SET_LOADEDALL, true)
			commit(types.INIT_STMT_COMMENTS, data)
		})
	},
	addComment ({commit}, payload) {
		api.sendComment(payload, data => {
			let num = 1
			if (data.code === 200) {
				commit(types.UPDATE_STMT_COMMENTS, {payload, data, num})
				showTip('评论成功！')
			} else {
				showTip('评论失败！')
			}
		})
	},
	replyComment ({commit}, payload) {
		api.replyComment(payload, data => {
			let num = 1
			if (data.code === 200) {
				commit(types.UPDATE_STMT_COMMENTS, {payload, data, num})
				showTip('回复成功！')
			} else {
				showTip('回复失败！')
			}
		})
	},
	deleteComment ({commit}, payload) {
		api.deleteComment(payload, data => {
			let num = -1
			if (data.code === 200) {
				commit(types.UPDATE_STMT_COMMENTS, {payload, data, num})
				showTip('删除评论成功！')
			} else {
				showTip('删除评论失败！')
			}
		})
	}
}

const mutations = {
	[types.INIT_LIST_TYPE] (state, data) {
		if (data === 'index') {
			data = 'posts'
		}
		state.listType = data
	},
	[types.INIT_INDEX_LIST] (state, {data, fromId}) {
		if (data.code === 200) {
			if (fromId !== 0) {
				state.indexList = state.indexList.concat(data.data)
			} else {
				state.indexList = data.data
			}
		}
	},
	[types.INIT_HOT_LIST] (state, {data, fromId}) {
		if (data.code === 200) {
			if (fromId !== 0) {
				state.hotList = state.hotList.concat(data.data)
			} else {
				state.hotList = data.data
			}
		}
	},
	[types.INIT_POSTED_LIST] (state, {data, fromId}) {
		if (data.code === 200) {
			if (fromId !== 0) {
				state.postedList = state.postedList.concat(data.data)
			} else {
				state.postedList = data.data
			}
		}
	},
	[types.INIT_LIKED_LIST] (state, {data, fromId}) {
		if (data.code === 200) {
			if (fromId !== 0) {
				state.likedList = state.likedList.concat(data.data)
			} else {
				state.likedList = data.data
			}
		}
	},
	[types.ADD_STATEMENT] (state, data) {
		let statement = createStatement(data.data)
		state.indexList.unshift(statement)
	},
	[types.SET_INDEX] (state, data) {
		state.index = data
	},
	[types.INIT_DETAIL_STATEMENT] (state, data) {
		if (data.code === 200) {
			state.detailStatement = data.data
		} else if (data.code === 500) {
			state.detailStatement = {}
		}
	},
	[types.TOGGLE_STMT_LIKED] (state, payload) {
		if (payload.index === -1) {
			state.detailStatement.liked = !payload.liked
		} else {
			if (state.listType === 'posts') {
				state.indexList[payload.index].liked = !payload.liked
			} else if (state.listType === 'hot') {
				state.hotList[payload.index].liked = !payload.liked
			} else if (state.listType === 'posted') {
				state.postedList[payload.index].liked = !payload.liked
			} else {
				state.likedList[payload.index].liked = !payload.liked
			}
		}
	},
	[types.UPDATE_STMT_LIKE_COUNT] (state, payload) {
		let num = payload.liked ? -1 : 1
		if (payload.index === -1) {
			state.detailStatement.likeCount += num
		} else {
			if (state.listType === 'posts') {
				state.indexList[payload.index].likeCount += num
			} else if (state.listType === 'hot') {
				state.hotList[payload.index].likeCount += num
			} else if (state.listType === 'posted') {
				state.postedList[payload.index].likeCount += num
			} else {
				state.likedList[payload.index].likeCount += num
			}
		}
	},
	[types.DELETE_STATEMENT] (data) {
		if (state.listType === '') {
		} else if (state.listType === 'posts') {
				state.indexList.splice(state.index, 1)
		} else if (state.listType === 'hot') {
			state.hotList.splice(state.index, 1)
		} else if (state.listType === 'posted') {
			state.postedList.splice(state.index, 1)
		} else {
			state.likedList.splice(state.index, 1)
		}
	},
	[types.INIT_COMMENTS] (state, data) {
		if (data.code === 200) {
			state.comments = data.data
		}
	},
	[types.INIT_STMT_COMMENTS] (state, data) {
		if (data.code === 200) {
			if (state.listType === '') {
				state.detailStatement.comments = data.data
			} else if (state.listType === 'posts') {
					state.indexList[state.index].comments = data.data
			} else if (state.listType === 'hot') {
				state.hotList[state.index].comments = data.data
			} else if (state.listType === 'posted') {
				state.postedList[state.index].comments = data.data
			} else {
				state.likedList[state.index].comments = data.data
			}
		}
	},
	[types.UPDATE_STMT_COMMENTS] (state, {payload, data, num}) {
		let comment = null
		if (num > 0) {
			comment = createComment(data.data)
		}
		if (data.code === 200) {
			if (state.listType === '') {
				state.detailStatement.commentCount += num
				if (num > 0) {
					state.comments.push(comment)
				} else {
					state.comments.splice(payload.index, 1)
				}
			} else if (state.listType === 'posts') {
					state.indexList[state.index].commentCount += num
					if (num > 0) {
						state.indexList[state.index].comments.push(comment)
					} else {
						state.indexList[state.index].comments.splice(payload.index, 1)
					}
			} else if (state.listType === 'hot') {
				state.hotList[state.index].commentCount += num
				if (num > 0) {
					state.hotList[state.index].comments.push(comment)
				} else {
					state.hotList[state.index].comments.splice(payload.index, 1)
				}
			} else if (state.listType === 'posted') {
				state.postedList[state.index].commentCount += num
				if (num > 0) {
					state.postedList[state.index].comments.push(comment)
				} else {
					state.postedList[state.index].comments.splice(payload.index, 1)
				}
			} else {
				state.likedList[state.index].commentCount += num
				if (num > 0) {
					state.likedList[state.index].comments.push(comment)
				} else {
					state.likedList[state.index].comments.splice(payload.index, 1)
				}
			}
		}
	},
	[types.TOGGLE_CMT_LIKED] (state, payload) {
		let num = payload.liked ? -1 : 1
		if (state.listType === '') {
			state.comments[payload.index].liked = !payload.liked
			state.comments[payload.index].likeCount += num
		} else if (state.listType === 'posts') {
				state.indexList[state.index].comments[payload.index].liked = !payload.liked
				state.indexList[state.index].comments[payload.index].likeCount += num
		} else if (state.listType === 'hot') {
			state.hotList[state.index].comments[payload.index].liked = !payload.liked
			state.hotList[state.index].comments[payload.index].likeCount += num
		} else if (state.listType === 'posted') {
			state.postedList[state.index].comments[payload.index].liked = !payload.liked
			state.postedList[state.index].comments[payload.index].likeCount += num
		} else {
			state.likedList[state.index].comments[payload.index].liked = !payload.liked
			state.likedList[state.index].comments[payload.index].likeCount += num
		}
	}
}

export default {
	state,
	getters,
	actions,
	mutations
}