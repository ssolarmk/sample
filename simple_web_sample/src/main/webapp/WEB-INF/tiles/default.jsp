<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<script src="/js/jquery-easyui/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/jquery-easyui/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/jquery-easyui/locale/easyui-lang-ko.js" type="text/javascript" charset="utf-8"></script>

<link rel="stylesheet" type="text/css" href="/js/jquery-easyui/themes/icons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui/themes/black/easyui.css" />
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui/themes/icon.css" />


<script src="/js/common/ajaxCommon.js" type="text/javascript"></script>
<style type="text/css">
html, body {
	height: 100%;
	overflow: hidden;
	margin: 0px;
	padding: 0;
}
</style>


</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;">
	    <div data-options="region:'north',border:false" style="height:60px">
	    	 <tiles:insertAttribute name="header" />
	    </div>
	    <div data-options="region:'south',split:true,border:false" style="height:50px;"></div>
	    <tiles:insertAttribute name="body" />
	</div>
</body>
</html>