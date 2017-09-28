import axios from 'axios'
export default {
	getIndexList (fromId, cb) {
		axios.defaults.withCredentials = true
		axios.get(`http://www.ustate.cn:8080/ustate2/posts?fromId=${fromId}`).then((response) => cb(response.data))
	},
	getHotList (fromId, cb) {
		axios.defaults.withCredentials = true
		axios.get(`http://www.ustate.cn:8080/ustate2/hot?fromId=${fromId}`).then((response) => cb(response.data))
	},
	getPostedList (fromId, cb) {
		axios.defaults.withCredentials = true
		axios.get(`http://www.ustate.cn:8080/ustate2/posts/self?fromId=${fromId}`).then((response) => cb(response.data))
	},
	getLikedList (fromId, cb) {
		axios.defaults.withCredentials = true
		axios.get(`http://www.ustate.cn:8080/ustate2/like/self?fromId=${fromId}`).then((response) => cb(response.data))
	},
	sendStatement(paylaod, cb) {
		axios.defaults.withCredentials = true
		let url = `http://www.ustate.cn:8080/ustate2/post`
		axios({
			method: 'POST',
			url: url,
			data: {
				content: paylaod.content,
				sercret: paylaod.sercret ? 1 : 0
			},
			headers: {
				'Content-Type': 'application/json; charset=utf-8'
			}
		}).then((response) => cb(response.data))
	},
	getProfile(cb) {
		axios.defaults.withCredentials = true
		axios.get('http://www.ustate.cn:8080/ustate2/profile').then((response) => cb(response.data))
	},
	getNoticeCount(cb) {
		axios.defaults.withCredentials = true
		axios.get(`http://www.ustate.cn:8080/ustate2/notice/count`).then((response) => cb(response.data))
	},
	toggleLiked(paylaod, cb) {
		axios.defaults.withCredentials = true
		let part = paylaod.type > 1 ? 'comment' : 'post'
		let method = paylaod.liked ? 'DELETE' : 'POST'
		axios({
			method: method,
			url: `http://www.ustate.cn:8080/ustate2/like/${part}`,
			data: {
				parentId: paylaod.id
			},
			headers: {
				'Content-Type': 'application/json; charset=utf-8'
			}
		}).then((response) => cb(response.data))
	},
	getDetail(id, cb) {
		axios.defaults.withCredentials = true
		axios.get(`http://www.ustate.cn:8080/ustate2/post?id=${id}`).then((response) => cb(response.data))
	},
	getCommentList (id, cb) {
		axios.defaults.withCredentials = true
		axios.get(`http://www.ustate.cn:8080/ustate2/comments/?postId=${id}`).then((response) => cb(response.data))
	},
	sendComment(paylaod, cb) {
		axios.defaults.withCredentials = true
		let url = `http://www.ustate.cn:8080/ustate2/comment`
		axios({
			method: 'POST',
			url: url,
			data: paylaod,
			headers: {
				'Content-Type': 'application/json; charset=utf-8'
			}
		}).then((response) => cb(response.data))
	},
	replyComment(paylaod, cb) {
		axios.defaults.withCredentials = true
		let url = `http://www.ustate.cn:8080/ustate2/comment`
		axios({
			method: 'POST',
			url: url,
			data: paylaod,
			headers: {
				'Content-Type': 'application/json; charset=utf-8'
			}
		}).then((response) => cb(response.data))
	},
	deleteStatement(id, cb) {
		axios.defaults.withCredentials = true
		axios({
			method: 'DELETE',
			url: `http://www.ustate.cn:8080/ustate2/post`,
			data: {
				id: id
			}
		}).then((response) => cb(response.data))
	},
	deleteComment(paylaod, cb) {
		axios.defaults.withCredentials = true
		axios({
			method: 'DELETE',
			url: `http://www.ustate.cn:8080/ustate2/comment`,
			data: {
				rootId: paylaod.rootId,
				id: paylaod.id
			}
		}).then((response) => cb(response.data))
	},
	getNotices(fromId, cb) {
		axios.defaults.withCredentials = true
		axios.get(`http://www.ustate.cn:8080/ustate2/notices?fromId=${fromId}`).then((response) => cb(response.data))
	},
	addReport(paylaod, cb) {
		axios.defaults.withCredentials = true
		let url = `http://www.ustate.cn:8080/ustate2/report`
		axios({
			method: 'POST',
			url: url,
			data: paylaod,
			headers: {
				'Content-Type': 'application/json; charset=utf-8'
			}
		}).then((response) => cb(response.data))
	},
	addFeedback(paylaod, cb) {
		axios.defaults.withCredentials = true
		let url = `http://www.ustate.cn:8080/ustate2/feedback`
		axios({
			method: 'POST',
			url: url,
			data: paylaod,
			headers: {
				'Content-Type': 'application/json; charset=utf-8'
			}
		}).then((response) => cb(response.data))
	},
	login(paylaod, cb) {
		axios.defaults.withCredentials = true
		let url = `http://www.ustate.cn:8080/ustate2/login`
		axios({
			method: 'POST',
			url: url,
			data: paylaod,
			headers: {
				'Content-Type': 'application/json; charset=utf-8'
			}
		}).then((response) => cb(response.data))
	},
	logout(cb) {
		axios.defaults.withCredentials = true
		axios.post('http://www.ustate.cn:8080/ustate2/logout').then((response) => cb(response.data))
	}
}
