import router from '@/router/router'
import { userModule } from '@/store'
import TokenUtil from '@/utils/TokenUtil'

const whiteList = ['/login'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {
  // set page title
  let user = userModule.user
  if (!user) {
    if (TokenUtil.hasToken()) {
      user = await userModule.getUserAction()
    }
  }
  if (user) {
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      next({ path: '/' })
    } else {
      next()
    }
  } else {
    /* has no token*/
    if (whiteList.indexOf(to.path) !== -1) {
      // in the free login whitelist, go directly
      next()
    } else {
      // other pages that do not have permission to access are redirected to the login page.
      next({ path: '/login' })
    }
  }
})

router.afterEach(() => {
  // finish progress bar
})
