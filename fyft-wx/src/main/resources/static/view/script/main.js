/**
 * 入口模块 
 */

var requireConfig = {
	urlArgs: "r=" + (new Date()).getTime(),
	baseUrl:'view/script',
    paths: {
        'text': '../assets/require/text',
        'vue': '../assets/vue/vue.min',
        'vue-router': '../assets/vue/vue-router',
        'zepto':'../assets/jqweui/zepto',
        'jqweui':'../assets/jqweui/jquery-weui.min'
    },
    shim: {
    	"jqweui" : ["zepto"] //依赖zepto
    },
    map: {
    	'*': {
    	      'css': '../assets/require/css.min' // or whatever the path to require-css is
    	    }
    }
}
// 
require.config(requireConfig)
require(['vue', 'vue-router', 'zepto', 'jqweui', 'app'], function (Vue, VueRouter, Zepto, Jqweui, App) {
    Vue.config.debug = true
    Vue.config.devtools = true

    Vue.use(VueRouter)
    
    //路由配置
    const routes = [
    	{
    		path:'/',
    		component:App,
	        meta: {
    			requiresAuth: true
		    }
    	},
    	{
    		path:'/login', 
    		component:function(resolve) {
    			//require获取login.js,生成组件
	            require(['login'], function(Content){
	                resolve(Content)
	            })
	        },
	        meta: {
    			//requiresAuth: true//登录模块不需要身份验证
		    }
    	},
    	{
    		path:'/blog', 
    		component:function(resolve) {
	            require(['blog'], function(Content){
	                resolve(Content)
	            })
	        },
	        meta: {
    			requiresAuth: true
		    }
    	}
    ]
    
    const router = new VueRouter({
      routes // （缩写）相当于 routes: routes
    })
    
    //路由拦截，判断token是否存在
    router.beforeEach((to, from, next) => {
		let token = window.sessionStorage.getItem('token')
		if (to.matched.some(record => record.meta.requiresAuth) && (!token || token === null)) {
			next({
				path: '/login',
				query: { redirect: to.fullPath }
			})
		} else {
			next()
		}
	})
	
	//将token放到ajax请求头中(heads),用于登录用户后台验证
	$.ajaxSettings.beforeSend=function(request){
    	request.setRequestHeader("token", window.sessionStorage.getItem('token'));
    }
    	
    const app = new Vue({
      router
    }).$mount('#app')
    
    // 配置自定义过滤器
    /*Vue.filter('localDateString', function(value) { 
        return new Date(value).toLocaleString()
    })*/

    // 配置resouce访问
    //Vue.use(VueResource)
})