import coverPicture from '@/components/common/coverPicture.vue'
import cover from '@/components/common/cover.vue'
import coverBox from '@/components/common/coverBox.vue'
import titleTop from '@/components/common/titleTop.vue'
import radio from '@/components/common/radio.vue'
import searchCover from '@/components/common/searchCover.vue'
import skeleton1 from '@/components/skeleton1.vue'
import skeleton2 from '@/components/skeleton2.vue'


export function globalComponents(app){
	app.component('coverPicture',coverPicture)
	app.component('cover',cover)
	app.component('coverBox',coverBox)
	app.component('titleTop',titleTop)
	app.component('radio',radio)
	app.component('searchCover',searchCover)
	app.component('skeleton1',skeleton1)
	app.component('skeleton2',skeleton2)
}
