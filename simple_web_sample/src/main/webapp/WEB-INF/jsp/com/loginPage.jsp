<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Form Using jQuery - Demo Preview</title>
<meta name="robots" content="noindex, nofollow">
<!-- Include CSS File Here -->
<style >
/* Below line is used for online Google font */
@import url(http://fonts.googleapis.com/css?family=Droid+Serif);
h2{
text-align: center;
font-size: 24px;
}
hr{
margin-bottom: 30px;
}
div.container{
width: 960px;
height: 610px;
margin:50px auto;
font-family: 'Droid Serif', serif;
position:relative;
}
div.main{
width: 320px;
margin-top: 80px;
float:left;
padding: 10px 55px 40px;
background-color: rgba(187, 255, 184, 0.65);
border: 15px solid white;
box-shadow: 0 0 10px;
border-radius: 2px;
font-size: 13px;
}
input[type=text],[type=password] {
width: 97.7%;
height: 34px;
padding-left: 5px;
margin-bottom: 20px;
margin-top: 8px;
box-shadow: 0 0 5px #00F5FF;
border: 2px solid #00F5FF;
color: #4f4f4f;
font-size: 16px;
}
label{
color: #464646;
text-shadow: 0 1px 0 #fff;
font-size: 14px;
font-weight: bold;
}
#login {
width: 100%;
background: linear-gradient(#22abe9 5%, #36caf0 100%);
border: 1px solid #0F799E;
font-size: 20px;
margin-top: 15px;
padding: 8px;
font-weight: bold;
cursor: pointer;
color: white;
text-shadow: 0px 1px 0px #13506D;
}
#login:hover{
background: linear-gradient(#36caf0 5%, #22abe9 100%);
}
</style>
<!-- Include CSS File Here -->
<script src="/js/jquery-easyui/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
    $("#login").click(function(){
        var user_id = $("#user_id").val();
        var password = $("#user_pswd").val();
        // Checking for blank fields.
        if( user_id =='' || password ==''){
            $('input[type="text"],input[type="password"]').css("border","2px solid red");
            $('input[type="text"],input[type="password"]').css("box-shadow","0 0 3px red");
            alert("Please fill all fields...!!!!!!");
        }else {
            $.post("/checkLoginUser",{ user_id: user_id, user_pswd: password},
            function(data) {
                var code = data.resultCode;
                if (code == "S000") {
                    location.href = "/main";
                } else {
                    $("#user_password").focus();
                    $("#user_password").attr("style", "border: solid red 2px;");
                    $("#viewMsg").html("<span class=\"txt_red\">" + data.resultMsg + "</span>");
                    return false;
                }
            });
        }
    });
});
</script>
</head>

<body>
    <div class="container">
        <div class="main">
            <form class="form" method="post" action="#">
                <h2> 사용자 로그인 </h2>
                    <label>아이디 :</label>
                    <input type="text" name="user_id" id="user_id">
                    <label>비밀번호 :</label>
                    <input type="password" name="user_pswd" id="user_pswd">
                    <input type="button" name="login" id="login" value="Login">
            </form>
        </div>
    </div>
</body>

</html>