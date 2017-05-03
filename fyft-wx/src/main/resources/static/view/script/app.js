/**
 * 主页模块,登录功能
 */

define(['vue', 'text!tpl/app.html', 'css!../script/css/demos.css'], function (Vue, AppHtml) {
    return Vue.extend({
        template: AppHtml,
        data: function(){
            return {
                user: '',
                userCode: '',
                passWord: ''
            }
        },
        ready: function(){
        },
        methods: {
        	login : function(){
        		//$.alert("keke！", "警告！");
        		var postData = {'userCode':this.userCode , 'passWord':this.passWord };
        		//this.$router.go('/content')
        		var thisVue = this;
        		$.ajax({
      				url:'/login',
      				type:'post',
      				dataType:'json',
      				data:postData,
      				success:function(data){
      					if(data['success']==true){
      						//thisVue.$router.push({path: '/content'})
      						thisVue.$router.push('/content')
      					}else $.toptip('登录失败', 'error');
      				},
      				error:function(stats){
      					$.toptip('系统异常，请稍后再试', 'error');
      				}
      			});
        		
        	}
        }
    })
})
