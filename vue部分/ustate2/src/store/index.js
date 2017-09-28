import Vue from 'vue'
import Vuex from 'vuex'
import stmt from './modules/stmt'
import prof from './modules/prof'
import notice from './modules/notice'
import other from './modules/other'
import loadMore from './modules/loadMore'
import actionSheet from './modules/actionSheet'
import scroll from './modules/scroll'
import topTip from './modules/topTip'
import user from './modules/user'

Vue.use(Vuex)

export default new Vuex.Store({
	modules: {
		stmt,
		prof,
		notice,
		other,
		loadMore,
		actionSheet,
		scroll,
		topTip,
		user
	}
})