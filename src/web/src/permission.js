import router from './router'
import store from './store'
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'// Progress 进度条样式
import { Message } from 'element-ui'
import { getToken } from '@/utils/auth' // 验权
import axios from './utils/request.js'


const whiteList = ['/login', '/register', '/forget_password', '/loginMember', '/checkInRegist', '/registForm'] // 不重定向白名单
//to 即将要进入的目标 路由对象
router.beforeEach((to, from, next) => {
  NProgress.start()

  if (getToken()) {
		// console.log(to.path)
		if (whiteList.indexOf(to.path) == -1) {
			axios.get("/api/apartment/isCompanyCert").then((res) => {
				// 没认证，强制跳转到认证页面
				if(res.data.status == 0 && res.data.userType == 1 ){
					next({
			        path: '/checkInRegist',
			        query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
		    	})
					NProgress.done()
				}else{
					next()
				}
			})
 
		}else{
			// store.dispatch('GetMenu').then(res => {

			//   }).catch((err) => {

			// })
			if (to.path === '/login') {
			  next()
			  NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
			} else {
			  if (store.getters.roles.length === 0) {
			    store.dispatch('GetInfo').then(res => { // 拉取用户信息
			      next()     //resolve 钩子
			    }).catch((err) => {
			      store.dispatch('FedLogOut').then(() => {
			        Message.error(err || 'Verification failed, please login again')
			        next({ path: '/' })
			      })
			    })
			  } else {
			    next()
			  }
			}
			
		}
  } else {
    // 在免登录白名单，直接进入
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next({
	        path: '/login',
	        query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
    	})
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done() // 结束Progress
})
