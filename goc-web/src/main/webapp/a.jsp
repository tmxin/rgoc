<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" /> 

<script type="text/javascript" src="./extra/jquery-1.8.1.min.js"></script>

<body>

<div class="input-group">
  <span class="input-group-addon">$</span>
  <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)">
  <span class="input-group-addon">.00</span>
</div>
	<input type="text" class="t" name="tokens" required/><br>
	<input type="text" class="n" name="num" />
	<a class="sub" href="javascript:void(0)">查询</a>
<br>
<a class="r" href="http://baidu.com" target="blank">sssssssss </a>
<br>
还剩<span class="left"></span>次

</body> 
<script type="text/javascript">
$(document).ready(function() {
	
	$(".sub").click(function(){
		var reg = /^\d{7}$/;
		var reg2 = /^[A-Za-z0-9]+$/;
		if (!reg.test($('.n').val())) {
			alert("请输入正确资源号")
		}else if(!reg2.test($('.t').val())){
			alert("请输入正确token")
		}else if($('.t').val() && $('.n').val() && $('.t').val()!="" && $('.n').val()!=""){
			var a = {};
			a.tokens = $('.t').val();
			a.num = $('.n').val();
			
			$.ajax({
		        type: "post",
		        url:"/corn-web/download",
		        data: JSON.stringify(a) ,
		        contentType: "application/json", 
		        dataType: "json",
		        async: false,
		        before:function(data){
		        	alert(data);
		        },
		        error: function(request) {
		            alert("Connection error");
		        },
		        success: function(data) {
		        	if(data.status == 1 || data.status == 2){
		        		$(".r").html(data.url);
		        		$(".left").html(data.left);
		        	}
		        }
		    });
		}else{
			alert("输入有误请重新输入");
		}
		
		/*$.post("/corn-web/download",a,function(result){
		    $(".r").html(result);
		});
		*/
	});
	
});
</script>
</html>