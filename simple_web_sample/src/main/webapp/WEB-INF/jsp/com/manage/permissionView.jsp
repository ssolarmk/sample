<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<c:set var="sessionVo" value="${sessionScope.S_USER}" />

<script type="text/javascript">
$(function() {
	$("#menuTree").treegrid({
		url : "",
	    idField:'id',
	    treeField:'text',
	    fitColumns : true,
	    checkbox: true,
	    fit: true,
	    autoRowHeight:false,
	    columns:[[
	    	{field: 'text', title: '메뉴명', width: 150, sortable: false},
            {field: 'id', title: '메뉴ID', width: 80, sortable: false},
            {field: 'menuTp', title: '메뉴유형코드', width: 50, align: "center", sortable: false, hidden:true},
            {field: 'url', title: 'URL', width: 200, align: "left", sortable: false},
	    ]]
	});
	
	$("#sel_auth_grp_cd").combobox({
		onChange : function(newValue,oldValue){
			$('#menuTree').treegrid('getPanel').panel('destroy');
			
			$("#south").html("<table id=\"menuTree\"></table>");
			
			$("#menuTree").treegrid({
				url:'/manage/permission/getMenuTreeRetrieve?auth_grp_cd='+$("#sel_auth_grp_cd").combobox("getValue"),
			    idField:'id',
			    treeField:'text',
			    fitColumns : true,
			    checkbox: true,
			    fit: true,
			    autoRowHeight:false,
			    columns:[[
			    	{field: 'text', title: '메뉴명', width: 150, sortable: false},
		            {field: 'id', title: '메뉴ID', width: 80, sortable: false},
		            {field: 'menuTp', title: '메뉴유형코드', width: 50, align: "center", sortable: false, hidden:true},
		            {field: 'url', title: 'URL', width: 200, align: "left", sortable: false},
			    ]],
			    onLoadSuccess: function (row, data) {
		           if (data.state == "selected") $(this).datagrid('checkRow', row);
		        }
			});
		}
	});
	
	 $("#btnUpdate").click(function(){
    	var auth_grp_cd = $("#sel_auth_grp_cd").combobox("getValue");
    	$("#auth_grp_cd").val(auth_grp_cd);
    	if(auth_grp_cd != ''){
    		var checked_value = ""; 
    		for(var i=0; i< $("#menuTree").treegrid("getCheckedNodes").length; i++ ){
    			checked_value = checked_value +","+ $("#menuTree").treegrid("getCheckedNodes")[i].id 
    		}
    		checked_value = checked_value.replace(",", "");	
   			$("#menu_cd").val(checked_value);
   			ConfirmdialogToAjax("update", "/manage/permission/update", $("#permissionSrcFrm").serialize(), resultFnc);
		}
    	
	 });
	function resultFnc(data){
		var resultCode = data.resultCode;
		var resultMsg = data.resultMsg;
		if(resultCode == "S000"){
			$.messager.alert('성공',"작업을 정상적으로 완료하였습니다.");
		} else {
			$.messager.alert('실패',"작업수행에 실패하였습니다.");
		}
	}

});
</script>

<div data-options="region:'center',title:'권한관리',iconCls:'icon-more',border:false" style="height: 100%">
	<div class="easyui-layout" data-options="fit:true" style="height: 100%">
	    <div data-options="region:'north'" style="height:10%">
	    	<form name="treeFrm" id="treeFrm" style="padding: 20px">
	    		<input class="easyui-combobox" name="sel_auth_grp_cd" id="sel_auth_grp_cd" style="width:30%;" data-options="url:'/common/codelist?CODE_GRP_CD=AUTH_GRP_CD',valueField:'code', textField:'codeNm', label:'권한그룹', editable:false" >
	    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="" id="btnUpdate"> 저장 </a>
	    	</form>
	    </div>
		<div id="south" data-options="region:'south'" style="height:90%">
			<table id="menuTree"></table>
	    </div>
	</div>
</div>
<form class="form-horizontal" id="permissionSrcFrm" name="permissionSrcFrm">
	<input type="hidden" name="site_gb_cd" id="site_gb_cd" value="SG0001"/>
	<input type="hidden" name="auth_grp_cd" id="auth_grp_cd"/>
	<input type="hidden" name="menu_cd" id="menu_cd"/>
</form>