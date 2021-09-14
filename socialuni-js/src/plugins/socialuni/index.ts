import Vue from 'vue'
import SocialConfig from 'socialuni/model/SocialConfig'
import socialuni from 'socialuni'
import store from '@/store'

const socialConfig: SocialConfig = {
  store: store,
  secretKey: '5b366df0c95e4061a3a8de91c0d863fd'
}

Vue.use(socialuni, socialConfig)
