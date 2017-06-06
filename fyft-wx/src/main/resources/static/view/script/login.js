/**
 * 登录模块
 */

define(['vue', 'text!tpl/login.html', 'css!../script/css/demos.css'], function (Vue, AppHtml) {
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
        		var postData = {'userCode':this.userCode , 'passWord':this.passWord };
        		var thisVue = this;
        		$.ajax({
      				url:'/login',
      				type:'post',
      				dataType:'json',
      				data:postData,
      				success:function(data){
      					if(data['success']==true){
      						//thisVue.$router.push({path: '/content'})
      						const token = data.token;
      						window.sessionStorage.setItem('token', token)//保存token
      						thisVue.$router.push('/')//登录跳转首页
      					}else $.toptip(data.msg, 'error');
      				},
      				error:function(stats){
      					$.toptip('系统异常，请稍后再试', 'error');
      				}
      			});
        		
        	}
        }
    })
})
