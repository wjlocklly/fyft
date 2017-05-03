
var learnPath = "/gzyb-learn/admin/";
var newsPath = "/eac-news/pages/";
var orgPath = "/eac-base/pages/";
var testPath = "/bhmstrain/jsp/exam/";

var navs = [
	{
		"title": "组织管理",
		"icon": "fa-cubes",
		"spread": false,
		"href" : "baidu.com",
		"children": [{
			"title": "组织架构管理",
			"icon": "",
			"href": orgPath+"organ.jsp",
			"spread": false
		},{
			"title": "党员管理",
			"icon": "",
			"href": orgPath+"member.jsp",
			"spread": false
		},{
			"title": "用户管理",
			"icon": "",
			"href": orgPath+"structure.jsp",
			"spread": false
		},{
			"title": "权限管理",
			"icon": "",
			"href": orgPath+"roleConfig.jsp",
			"spread": false
		}]
	},
	 {
		"title": "信息发布",
		"icon": "fa-desktop",
		"spread": false,
		"children": [{
			"title": "信息发布",
			"icon": "",
			"href": newsPath+"news/newsMgr.jsp",
			"spread": true
		},{
			"title": "热点文章",
			"icon": "",
			"href": newsPath+"news/Hotnews.jsp",
			"spread": false
		},{
			"title": "信息审核",
			"icon": "",
			"href": newsPath+"news/news.jsp?STATUS_FLAG=a",
			"spread": false
		},{
			"title": "信息检索",
			"icon": "",
			"href": newsPath+"search/newsList.jsp",
			"spread": false
		},{
			"title": "废弃文章",
			"icon": "",
			"href": newsPath+"news/wasteNews.jsp",
			"spread": false
		},{
			"title": "频道管理",
			"icon": "",
			"href": newsPath+"channel/channelMgr.jsp",
			"spread": false
		},{
			"title": "专题管理",
			"icon": "",
			"href": newsPath+"topic/topic.jsp?TOPIC_ID=1",
			"spread": false
		},{
			"title": "关键字管理",
			"icon": "",
			"href": newsPath+"keyword/keyword.jsp",
			"spread": false
		}]
	},
	{
		"title": "在线学习",
		"icon": "fa-book",
		"spread": false,
		"children": [{
			"title": "课程管理",
			"icon": "",
			"href": learnPath+"category/categoryList.jsp?type=1",
			"spread": true
		},{
			"title": "分类管理",
			"icon": "",
			"href": learnPath+"category/categoryList.jsp?type=2",
			"spread": false
		},{
			"title": "学习记录",
			"icon": "",
			"href": learnPath+"record/recordList.jsp",
			"spread": false
		},{
			"title": "学习笔记",
			"icon": "",
			"href": learnPath+"review/reviewList.jsp",
			"spread": false
		},{
			"title": "学习主题",
			"icon": "",
			"href": learnPath+"topic/topicList.jsp",
			"spread": false
		}]
	}, {
		"title": "在线考试",
		"icon": "fa-address-book",
		"href": "",
		"spread": false,
		"children": [{
			"title": "我的考试",
			"icon": "",
			"href": testPath+"mainPage.jsp"
		}, {
			"title": "知识管理",
			"icon": "",
			"href": testPath+"knowledge/knowledgeTree.jsp"
		}, {
			"title": "题库管理",
			"icon": "",
			"href": testPath+"question/questionList.jsp"
		}, {
			"title": "试卷管理",
			"icon": "",
			"href": testPath+"paper/paperList.jsp"
		}, {
			"title": "试卷计划",
			"icon": "",
			"href": testPath+"info/infoList.jsp"
		}, {
			"title": "考试审批",
			"icon": "",
			"href": testPath+"info/approvePlanMain.jsp"
		}, {
			"title": "考试评卷",
			"icon": "",
			"href": testPath+"eva/evaList.jsp"
		}, {
			"title": "考试分析",
			"icon": "",
			"href": testPath+"analysis/examAnalysisList.jsp"
		}]
	}
            
];