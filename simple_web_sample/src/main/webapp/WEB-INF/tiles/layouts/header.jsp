<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<c:set var="sessionVo" value="${sessionScope.S_USER}" />
<c:set var="path" value="${requestScope['javax.servlet.forward.request_uri']}"></c:set>
<c:set var="mymenu"><s:eval expression="T(com.owra.web.common.util.StringUtil).ObjectToJsonString(sessionVo.menu)" /></c:set>
<c:set var="selectedTopMenu" value="" />
<c:set var="selectedTopName" value="" />
<c:set var="selectedThisName" value="" />
<c:set var="selectedThisMenuId" value="" />


<style>
	a.l-btn{
		height:36px;
	}
	a.l-btn span.l-btn-left{
		padding-top:0;
		padding-bottom:0;
		height:36px;
	}
	a.l-btn span span.l-btn-text{
		height:36px;
		line-height:36px;
		font-size:16px;
		font-weight:bold;
	}
	.s-btn-downarrow,.m-btn-downarrow{
		font-size:16px;
		font-weight:bold;
	}
</style>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west', border:false" style="width:100px">
    	메인이미지
    </div>
    <div data-options="region:'east', border:false" style="width:150px">
    	<div class="easyui-panel" style="height:60px; padding-bottom :10px; margin-left: 40px; padding-right: 10px; padding-top: 20px; overflow: hidden; border:0">
		    <a id="btn-home" href="/logout" class="easyui-linkbutton" data-options="plain:true">로그아웃</a>
		</div>
    </div>
    <div data-options="region:'center', border:false">
    	<div class="easyui-panel" style="height:60px; padding-bottom :10px; padding-left: 10px; padding-right: 10px; padding-top: 14px; overflow: hidden; border:0">
		    <a id="btn-home" href="/main" class="easyui-linkbutton" data-options="plain:true">Home</a>
		    <c:forEach var="result" items="${sessionVo.menu}" varStatus="status">
				<c:if test="${result.top_menu_id != prev_menu_id && result.view_yn1 == 'Y'}">
					<c:set var="menuCnt" value="0"></c:set>
					<c:forEach var="result_cnt" items="${sessionVo.menu}" varStatus="status2">
	            		<c:if test="${result_cnt.top_menu_id ==  result.top_menu_id && result_cnt.left1_menu_id != null && result_cnt.view_yn2 == 'Y'}">
	            			<c:set var="menuCnt" value="${ menuCnt + 1 }"></c:set>
        				</c:if>
        			</c:forEach>
						 <a href="#" class="easyui-menubutton" data-options="menu:'#${result.top_menu_id}'">${result.top_menu_name}</a>
				</c:if>
				<c:set var="prev_menu_id" value="${result.top_menu_id}"></c:set>
		    </c:forEach>
		</div>
		<c:forEach var="result" items="${sessionVo.menu}" varStatus="status">
			<c:if test="${result.top_menu_id != prev_menu_id && result.view_yn1 == 'Y'}">
				<c:set var="menuCnt" value="0"></c:set>
				<c:forEach var="result_cnt" items="${sessionVo.menu}" varStatus="status2">
            		<c:if test="${result_cnt.top_menu_id ==  result.top_menu_id && result_cnt.left1_menu_id != null && result_cnt.view_yn2 == 'Y'}">
            			<c:set var="menuCnt" value="${ menuCnt + 1 }"></c:set>
       				</c:if>
       			</c:forEach>
       					<div id="${result.top_menu_id}" style="width:150px;">
           				<c:forEach var="result_sub" items="${sessionVo.menu}" varStatus="status2">
           					<c:if test="${result_sub.top_menu_id ==  result.top_menu_id && result_sub.left1_menu_id != null && result_sub.view_yn2 == 'Y'}">
           						  <div onclick="javascript:location.href='${result_sub.left1_menu_url}'">${result_sub.left1_menu_name}</div>
           					</c:if>
           				</c:forEach>
           				</div>
			</c:if>
			<c:set var="prev_menu_id" value="${result.top_menu_id}"></c:set>
	    </c:forEach>
		  
    </div>
</div>
 
<script type="text/javascript">
    (function($){
        function getParentMenu(rootMenu, menu){
            return findParent(rootMenu);

            function findParent(pmenu){
                var p = undefined;
                $(pmenu).find('.menu-item').each(function(){
                    if (!p && this.submenu){
                        if ($(this.submenu)[0] == $(menu)[0]){
                            p = pmenu;
                        } else {
                            p = findParent(this.submenu);
                        }
                    }
                });
                return p;
            }
        }
        function getParentItem(pmenu, menu){
            var item = undefined;
            $(pmenu).find('.menu-item').each(function(){
                if ($(this.submenu)[0] == $(menu)[0]){
                    item = $(this);
                    return false;
                }
            });
            return item;
        }

        $.extend($.fn.menubutton.methods, {
            enableNav: function(enabled){
                var curr;
                $(document).unbind('.menubutton');
                if (enabled == undefined){enabled = true;}
                if (enabled){
                    $(document).bind('keydown.menubutton', function(e){
                        var currButton = $(this).find('.m-btn-active,.m-btn-plain-active,.l-btn:focus');
                        if (!currButton.length){
                            return;
                        }

                        if (!curr || curr.button != currButton[0]){
                            curr = {
                                menu: currButton.data('menubutton') ? $(currButton.menubutton('options').menu) : $(),
                                button: currButton[0]
                            };
                        }
                        var item = curr.menu.find('.menu-active');

                        switch(e.keyCode){
                            case 13:  // enter
                                item.trigger('click');
                                break;
                            case 27:  // esc
                                currButton.trigger('mouseleave');
                                break;
                            case 38:  // up
                                var prev = !item.length ? curr.menu.find('.menu-item:last') : item.prevAll('.menu-item:first');
                                prev.trigger('mouseenter');
                                return false;
                            case 40:  // down
                                var next = !item.length ? curr.menu.find('.menu-item:first') : item.nextAll('.menu-item:first');
                                next.trigger('mouseenter');
                                return false;
                            case 37:  // left
                                var pmenu = getParentMenu(currButton.data('menubutton') ? $(currButton.menubutton('options').menu) : $(), curr.menu);
                                if (pmenu){
                                    item.trigger('mouseleave');
                                    var pitem = getParentItem(pmenu, curr.menu);
                                    if (pitem){
                                        pitem.trigger('mouseenter');
                                    }
                                    curr.menu = pmenu;
                                } else {
                                    var prev = currButton.prevAll('.l-btn:first');
                                    if (prev.length){
                                        currButton.trigger('mouseleave');
                                        prev.focus();
                                    }
                                }
                                return false;
                            case 39:  // right
                                if (item.length && item[0].submenu){
                                    curr.menu = $(item[0].submenu);
                                    curr.button = currButton[0];
                                    curr.menu.find('.menu-item:first').trigger('mouseenter');
                                } else {
                                    var next = currButton.nextAll('.l-btn:first');
                                    if (next.length){
                                        currButton.trigger('mouseleave');
                                        next.focus();
                                    }
                                }
                                return false;
                        }
                    });                        
                }
            }
        });
    })(jQuery);

    $(function(){
        $.fn.menubutton.methods.enableNav();
        $(document).keydown(function(e){
            if (e.altKey && e.keyCode == 87){
                $('#btn-home').focus();
            }
        })
    });
</script>