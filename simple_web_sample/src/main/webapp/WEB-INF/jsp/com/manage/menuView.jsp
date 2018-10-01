<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<c:set var="sessionVo" value="${sessionScope.S_USER}" />

<script type="text/javascript">
$(function() {
	$("#menuTree").treegrid({
		url : "/manage/menu/getMenuTreeRetrieve",
	    idField:'MENU_CD',
	    treeField:'MENU_NM',
	    fitColumns : true,
	    fit: true,
	    autoRowHeight:false,
	    singleSelect:true,
	    columns:[[
	    	{field: 'MENU_NM', title: '메뉴명', width: 150, sortable: false},
            {field: 'MENU_CD', title: '메뉴ID', width: 80, sortable: false},
            {field: 'MENU_TP', title: '메뉴유형코드', width: 50, align: "center", sortable: false, hidden:true},
            {field: 'MENU_TP_NM', title: '메뉴유형', width: 100, align: "center", sortable: false},
            {field: 'MENU_URL', title: 'URL', width: 200, align: "left", sortable: false},
            {field: 'MENU_ORD', title: '정렬순서', width: 50, align: "center", sortable: false},
            {field: 'USE_YN', title: 'USE_YN', width: 40, align: "center", sortable: false, hidden:true},
            {field: 'USE_YN_NM', title: '사용여부', width: 60, sortable: false, align:"center"},
            {field: 'UPT_USER_ID', title: '등록자', align: "center", width: 80, sortable: false},
            {field: 'UPT_DTTM', title: '등록일시', width: 120, sortable: false},
            {field: 'MENU_PRNT_CD', title: 'menu_pid', width: 150, sortable: false, hidden:true},
            {field: 'iconCls', title: 'iconCls', width: 150, sortable: false, hidden:true},
            {field: 'level', title: 'level', width: 150, sortable: false, hidden:true},
            {field: 'SITE_GB_CD', title: 'SITE_GB_CD', width: 150, sortable: false, hidden:true},
            {field: 'expanded', title: 'expanded', width: 150, sortable: false, hidden:true}
	    ]],
	    onClickRow : function(data){
			$("#menuSrcFrm").form("reset");
    	    
    	    if(data.MENU_TP == "R"){
   	    		$("#menu").combobox("select",data.MENU_CD);
    	    }else{
   	    		$("#menu").combobox("select",data.MENU_PRNT_CD);
    	    }  
    	    	$("#menu_nm").textbox("setValue", data.MENU_NM);
      			$("#menu_cd").textbox("setValue", data.MENU_CD);
      			$("#old_menu_cd").textbox("setValue", data.MENU_CD);
      			$("#menu_tp_nm").textbox("setValue", data.MENU_TP_NM);
        		$("#menu_tp").combobox("select",data.MENU_TP);
        		$("#menu_url").textbox("setValue", data.MENU_URL);
        		$("#menu_ord").textbox("setValue", data.MENU_ORD);
        		$("#site_gb_cd").val(data.SITE_GB_CD);
        		$("#menu_use_yn").switchbutton("check", data.USE_YN);
				$("#user_id").textbox("setValue", data.UPT_USER_ID);
        		$("#upt_dttm").textbox("setValue", data.UPT_DTTM);
        		$("#menu_prnt_cd").val(data.MENU_PRNT_CD);

        		$("#btn_menu_create").hide();
				$("#btn_menu_update").show();
	    }
	});
	
	/*
	설명	: 저장버튼 click event
	수정일		수정자		수정내용
	*/
	$("#btn_menu_create").click(function() {
		if($("#menuSrcFrm").form("validate")){
			$("#menu_state").val("create");
			ConfirmdialogToAjax("create", "/manage/menu/menuCreate", $("#menuSrcFrm").serialize(), fn_return_menuCreate);
		} else {
			return $("#menuSrcFrm").form("validate");
		}
	});
	
	/*
	설명	: 수정버튼 click event
	수정일		수정자		수정내용
	*/
	$("#btn_menu_update").click(function() {
		if($("#menuSrcFrm").form("validate")){
			$("#menu_state").val("update");
			ConfirmdialogToAjax("update", "/manage/menu/menuUpdate", $("#menuSrcFrm").serialize(), fn_return_menuUpdate);
		} else {
			return $("#menuSrcFrm").form("validate");
		}
	});
	
	/*
	설명	: 삭제버튼 click event
	수정일		수정자		수정내용
	*/
	$("#btn_menu_delete").click(function(){
		if ($("#menu").val().length == 0) {
			swal("경고", "삭제할 메뉴명을 선택해주십시오.", "error");
			return;
		}
		ConfirmdialogToAjax("delete", "/manage/menu/menuDelete", $("#menuSrcFrm").serialize(), fn_return_menuDelete);
	});	
	
	
	$("#btn_menu_reset").click(function() {
		$("#menuSrcFrm").form("reset");
		var upMenu;
	    var menuTp;
	    
		menuTp = $("#menuTree").treegrid("getSelected").MENU_TP;//A, D, P, ROOT
		upMenu = $("#menuTree").treegrid("getSelected").MENU_CD;
		
		$("#site_gb_cd").val($("#menuTree").treegrid("getSelected").SITE_GB_CD);
	    
		if(menuTp == "A"){
	    	if(upMenu.length > 3) {
	    		$("#menu").combobox("select",upMenu.substr(0, upMenu.length-2));
	    		$("#menu_prnt_cd").val(upMenu.substr(0, upMenu.length-2));
	    	}else{
	    		$("#menu").combobox("select",upMenu);
	    		$("#menu_prnt_cd").val(upMenu);
	    	}
	    }else{
	    	$("#menu").combobox("select",upMenu);
	    	$("#menu_prnt_cd").val(upMenu);
	    }
		
		$("#menu_use_yn").switchbutton("check", 'Y');
	    $("#btn_menu_create").show();
		$("#btn_menu_update").hide();
	});
	
	function fn_return_menuDelete(data) {
		var resultCode = data.resultCode;
		var resultMsg = data.resultMsg;
		$("#menuTree").treegrid('reload');
		$('#menu').combobox('reload');
		$("#menuSrcFrm").form("reset");
		if (resultCode == "S000") {
			$.messager.alert('성공',"작업을 정상적으로 완료하였습니다.");
		}
	}
	function fn_return_menuCreate(data) {
		var resultCode = data.resultCode;
		var resultMsg = data.resultMsg;
		$("#menuTree").treegrid('reload');
		$('#menu').combobox('reload');
		$("#menuSrcFrm").form("reset");
		if (resultCode == "S000") {
			$.messager.alert('성공',"작업을 정상적으로 완료하였습니다.");
		}
	}
	function fn_return_menuUpdate(data) {
		var resultCode = data.resultCode;
		var resultMsg = data.resultMsg;
		$("#menuTree").treegrid('reload');
		$('#menu').combobox('reload');
		$("#menuSrcFrm").form("reset");
		if (resultCode == "S000") {
			$.messager.alert('성공',"작업을 정상적으로 완료하였습니다.");
		}
	}
	
});
</script>

<div data-options="region:'center',title:'메뉴관리',iconCls:'icon-more',border:false">
	<div class="easyui-layout" data-options="fit:true">
	    <div data-options="region:'west'" style="width:50%">
	    	<table id="menuTree"></table>
	    </div>
		<div data-options="region:'east'" style="width:50%;">
			<div id="p" class="easyui-panel" title="상세 화면" style="padding:10px;height: 90%;">
		        <form id="menuSrcFrm" name="menuSrcFrm" method="post">
		        	<input type="hidden"  name="menu_state"  id="menu_state"/>
	        		<input type="hidden" name="view_yn" id="view_yn"/>
	        		<input type="hidden" name="menu_prnt_cd" id="menu_prnt_cd"/>
	        		<input type="hidden" name="site_gb_cd" id="site_gb_cd"/>
	        		<input type="hidden" name="upt_user_id" id="upt_user_id" value="${sessionVo.user_id}"/>
		            <div style="margin-bottom:20px">
		                <input class="easyui-combobox" name="menu" id="menu" style="width:30%;" data-options="url:'/manage/menu/upMenuRetrieve?SITE_GB_CD=SG0001',valueField:'menu_cd', textField:'menu', label:'메뉴', disabled:true" >
		            </div>
		            <div style="margin-bottom:20px">
		                <input class="easyui-textbox" name="menu_cd" id="menu_cd" style="width:100%" data-options="label:'메뉴ID:',required:true">
		            </div>
		            <div style="margin-bottom:20px">
		                <input class="easyui-textbox" name="menu_nm" id="menu_nm" style="width:100%" data-options="label:'메뉴명',required:true">
		            </div>
		            <div style="margin-bottom:20px">
		                <input class="easyui-combobox" name="menu_tp" id="menu_tp" style="width:20%;" data-options="url:'/common/codelist?CODE_GRP_CD=MENU_TP',valueField:'code', textField:'codeNm', label:'메뉴유형'" >
		            </div>
		            <div style="margin-bottom:20px">
		                <input class="easyui-textbox" name="menu_url" id="menu_url" style="width:100%" data-options="label:'URL',required:true">
		            </div>
		            <div style="margin-bottom:20px">
		                <input class="easyui-textbox" name="menu_ord" id="menu_ord" style="width:100%" data-options="label:'정렬순서',required:true">
		            </div>
		            <div style="margin-bottom:20px">
		            	<label>사용여부 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		            		<input class="easyui-switchbutton" name="menu_use_yn" id="menu_use_yn" data-options="onText:'예',offText:'아니요',required:true" value="Y" >
		            	</label>
		            </div>
		            <div style="margin-bottom:20px">
		                <input class="easyui-textbox" name="user_id" id="user_id" style="width:100%" data-options="label:'최종 수정자'" readonly="readonly">
		            </div>
		            <div style="margin-bottom:20px">
		                <input class="easyui-textbox" name="upt_dttm" id="upt_dttm" style="width:100%" data-options="label:'최종 수정일시'" readonly="readonly">
		            </div>
		        </form>
		    </div>
		    <div id="footer" style="padding:5px;">
		         <div style="text-align:right;padding:5px 0">
		            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn_menu_reset" data-options="iconCls:'icon-reload'" style="width:80px">신규</a>
		            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn_menu_update" data-options="iconCls:'icon-add'" style="width:80px;display: none;">저장</a>
		            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn_menu_create" data-options="iconCls:'icon-add'" style="width:80px">저장</a>
		            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn_menu_delete" data-options="iconCls:'icon-remove'" style="width:80px">삭제</a>
		        </div>
		    </div>
	    </div>
			
	</div>
</div>