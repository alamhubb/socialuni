import { Module, VuexModule } from 'vuex-class-modules'

@Module({ generateMutationSetters: true })
export default class AppModule extends VuexModule {

}
