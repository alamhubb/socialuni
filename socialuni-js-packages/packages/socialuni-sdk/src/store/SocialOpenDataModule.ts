import {VuexModule, Module, Action} from 'vuex-class-modules'
import SocialAppModule from './SocialAppModule'


import {Pinia, Store} from "pinia-class-component"

@Store
export default class SocialOpenDataModule extends Pinia {
    threeSecretKey = ''
    threeUserId = ''
    threeProviderAppId = ''
    threeProviderType = ''
}
