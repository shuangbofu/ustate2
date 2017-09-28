<template>
	<u-send title="发布" placeholder="写下你想说的话" :maxCount="1000" @send="send">
		<sercret-button :sercret="sercret" @toggle="toggle"></sercret-button>
	</u-send>
</template>

<script>
	import {mapActions, mapGetters} from 'vuex'
	import USend from '@/components/u-send'
	import SercretButton from '@/components/sercret-button'
	export default {
		data () {
			return {
				sercret: true
			}
		},
		methods: {
			toggle () {
				this.sercret = !this.sercret
			},
			send (content) {
				this.addStatement({
					content: content,
					sercret: this.sercret
				})
			},
			...mapActions(['addStatement', 'initCount'])
		},
		components: {
			USend,
			SercretButton
		},
		activated () {
			this.sercret = true
		},
		computed: {
			...mapGetters(['isGetCount', 'profile'])
		},
		watch: {
			$route (val, oldVal) {
				if ((val.name === 'index' || val.name === 'hot') && oldVal.name === 'publish') {
					if (this.isGetCount && this.profile.id !== undefined) {
						this.initCount()
					}
				}
			}
		}
	}
</script>
<style scoped lang="stylus" rel="stylesheet/stylus">
	
</style>