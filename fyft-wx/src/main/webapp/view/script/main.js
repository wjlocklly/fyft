/**
 * 入口模块 
 */

var requireConfig = {
    baseUrl:'view/script',
    paths: {
        'text': '../assets/require/text',
        'vue': '../assets/vue/vue.min',
        'vue-router': '../assets/vue/vue-router'
    },
    shim: {
    },
    map: {
    }
}

require.config(requireConfig)
require(['vue', 'vue-router', 'app'], function (Vue, VueRouter, App) {
    Vue.config.debug = true
    Vue.config.devtools = true

    Vue.use(VueRouter)
    
    //主页模板
    const routes = [
    	{path:'', component:App}
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