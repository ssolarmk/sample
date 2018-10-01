<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<script type="text/javascript">
$(function() {
	$('#userList').datagrid({
		title : "유저 리스트",
	    url:'/basic/user/userListRetrieve',
	    rownumbers : true,
	    pagination : true,
	    pagePosition : "bottom",
	    fitColumns : true,
	    fit: true,
	    autoRowHeight:false,
	    singleSelect:true,
	    columns:[[
	    	{field: 'usid', title: '아이디', width:30},
            {field: 'user_nm', title: '사용자명', align: "left", width:30},
            {field: 'auth_grp_nm', title: '권한그룹', width:30},
            {field: 'fi_org_nm', title: '소속', width:30},
            {field: 'ent_dttm', title: '등록일자', align: "center", width:30},
            {field: 'use_yn_nm', title: '사용여부', align: "center", width:30},
            {field: 'use_yn', title: 'use_yn', hidden:true},
            {field: 'dept_cd', title: 'dept_cd', hidden:true},
            {field: 'inco_dt', title: 'inco_dt', hidden:true},
            {field: 'pos_cd', title: 'pos_cd', hidden:true},
            {field: 'auth_grp_cd', title: 'auth_grp_cd', hidden:true},
            {field: 'str_dttm', title: 'str_dttm', hidden:true},
            {field: 'end_dttm', title: 'end_dttm', hidden:true},
            {field: 'fi_org_cd', title: 'fi_org_cd', hidden:true},
            {field: 'email', title: 'email', hidden:true},
            {field: 'tel_no', title: 'tel_no', hidden:true}
	    ]],
	    onClickRow : function(index, data){
	    	 $('#frm_user_info').form('load',data);
	    }
	
	});
});
</script>

<div data-options="region:'center',title:'사용자 관리',iconCls:'icon-more',border:false">
	<div class="easyui-layout" data-options="fit:true">
	    <div data-options="region:'west'" style="width:50%">
	    	<table id="userList"></table>
	    </div>
		<div data-options="region:'east'" style="width:50%">
			<div class="easyui-panel" title="사용자 정보" style="width:100%;height:100%;padding:30px 60px;">
				<form id="frm_user_info" method="post">
					<div style="margin-bottom:10px">
		                <input class="easyui-textbox" name="usid" style="width:100%" data-options="label:'아이디:',required:true">
		            </div>
		            <div style="margin-bottom:10px">
		                <input class="easyui-textbox" name="user_nm" style="width:100%" data-options="label:'사용자명:',required:true">
		            </div>
		            <div style="margin-bottom:10px">
		                <input class="easyui-maskedbox" name="tel_no" mask="999-9999-9999" style="width:100%" data-options="label:'연락처:',required:true">
		            </div>
		            <div style="margin-bottom:10px">
		                <input class="easyui-textbox" name="email" style="width:100%" data-options="label:'이메일:'">
		            </div>
		            <div style="margin-bottom:10px">
		                <input class="easyui-textbox" name="fi_org_nm" style="width:100%" data-options="label:'소속:'">
		            </div>
		            <div style="margin-bottom:10px">
		                <input class="easyui-combobox" name="auth_grp_cd" id="auth_grp_cd0" style="width:30%;" data-options="url:'/common/codelist?CODE_GRP_CD=AUTH_GRP_CD',valueField:'code', textField:'codeNm', label:'권한그룹', editable:false" >
		            </div>
		            <div style="margin-bottom:10px">
		                <input class="easyui-textbox" name="inco_dt" style="width:100%" data-options="label:'입사일자:'">
		            </div>
		            <div style="margin-bottom:10px">
		            	<label>사용여부 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		            		<input class="easyui-switchbutton" name="use_yn" data-options="onText:'Yes',offText:'No'" value="Y" >
		            	</label>
		            </div>
				</form>
			</div>
	    </div>
			
	</div>
</div>