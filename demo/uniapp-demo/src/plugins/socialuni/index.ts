import Vue from 'vue'
import SocialConfig from '/socialuni/model/SocialConfig'
import socialuni from '../../../socialuni'

const socialConfig: SocialConfig = {
  secretKey: '5b366df0c95e4061a3a8de91c0d863fd',
  weixin: {
    mpId: 'wxc2315506fd81f7b4',
    mpSecret: 'e2fcf20b25d3f8ae24d08656ec99c95e'
  }
}

Vue.use(socialuni, socialConfig)
