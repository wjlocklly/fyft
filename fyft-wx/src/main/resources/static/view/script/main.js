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
    	"jqweui" : ["zepto"]
    },
    map: {
    	'*': {
    	      'css': '../assets/require/css.min' // or whatever the path to require-css is
    	    }
    }
}
// 
require.config(requireConfig)
require(['vue', 'vue-router', 'zepto', 'jqweui', 'app'], function (Vue, VueRouter, $, Jqweui, App) {
    Vue.config.debug = true
    Vue.config.devtools = true

    Vue.use(VueRouter)
    
    //路由配置
    const routes = [
    	{path:'', component:App},
    	{path:'/content', 
		 component:function(resolve) {
	            require(['content'], function(Content){
	                resolve(Content)
	            })
	        }
    	}
    ]
    
    const router = new VueRouter({
      routes // （缩写）相当于 routes: routes
    })
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