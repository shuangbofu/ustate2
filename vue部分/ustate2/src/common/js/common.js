import * as types from '@/store/mutation-types'
import prof from '@/store/modules/prof'
import router from '@/router/index'
import store from '@/store/index'

export const commonFn = function ({commit}, fromId, data) {
	if (fromId !== 0) {
		commit(types.SET_LOADING, false)
	}
	if (data.data.length < 15) {
		commit(types.SET_LOADEDALL, true)
	}
}

export const noLoginTipFn = function (time1, time2, cb) {
	let id = prof.state.profile.id
	if (id === undefined) {
		setTimeout(() => {
			store.commit(types.SHOW_TOP_TIP, '请先登录！')
		}, time1)
		setTimeout(() => {
			router.replace({name: 'login'})
		}, time2)
	} else {
		if (router.history.current.name === 'login') {
			router.replace({name: 'profile'})
		}
		if (cb !== undefined) {
			cb()
		}
	}
}

export const showTip = function (message) {
	setTimeout(() => {
		store.commit(types.SHOW_TOP_TIP, message)
	}, 500)
}

export const createStatement = function (data) {
	let profile = data.profile
	profile.gender = prof.state.profile.gender
	if (!data.sercret) {
		profile.nickname = prof.state.profile.nickname
		profile.avatar = prof.state.profile.avatar
	} else {
		if (profile.gender === 1) {
			profile.avatar = 'http://118.89.182.253/img/avatar_male.jpg'
		} else {
			profile.avatar = 'http://118.89.182.253/img/avatar_female.jpg'
		}
	}
	return Object.assign({}, data, {
		profile: profile
	})
}

export const createComment = function (data) {
	let profile = prof.state.profile
	return Object.assign({}, data, {
		profile: {
			id: profile.id,
			nickname: profile.nickname,
			avatar: profile.avatar,
			gender: profile.gender
		}
	})
}