import Vue from 'vue'
import SocialuniConfig from '@/socialuni/model/SocialuniConfig'
import socialuni from 'socialuni'
import store from '@/store'

const socialConfig: SocialuniConfig = {
  store: store
}

Vue.use(socialuni, socialConfig)
