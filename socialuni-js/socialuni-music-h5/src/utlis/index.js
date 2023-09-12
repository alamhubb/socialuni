import {globalComponents} from './globalComponents.js'
import {formatNumber} from "@/utlis/formatNumber.js";
import {formatTime} from '@/utlis/formatTime.js'

export  function install(app){
	globalComponents(app)
	app.config.globalProperties.$formatNumber = formatNumber
	app.config.globalProperties.$formatTime = formatTime
}