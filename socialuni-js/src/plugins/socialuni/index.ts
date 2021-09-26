import Vue from 'vue'
import SocialuniConfig from '@/socialuni/model/SocialuniConfig'
import socialuni from 'socialuni'
import store from '@/store'

const socialConfig: SocialuniConfig = {
  store: store,
  secretKey: '5b366df0c95e4061a3a8de91c0d863fd'
}

Vue.use(socialuni, socialConfig)
