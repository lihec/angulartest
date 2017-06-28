<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>出错啦</title>
</head>
<c:choose>
<c:when test="${alert eq null or alert eq '1'}">  <!-- alert -->
		<script type="text/javascript">
		<c:if test="${empty errorMsg}">window.alert('系统错误');</c:if>
		<c:if test="${not empty errorMsg}">window.alert('${errorMsg}');</c:if>
		
		<c:if test="${forward ne null}">
			document.location.href='${forward}';
		</c:if>
		</script>
		
</c:when>
<c:otherwise>  <!-- 页面提示 -->
<body>
    <div class="pop-wrapper pop-wrapper-680">
    	<div class="block">
	        <div class="thick-wrapper">
	       		<div class="alert alert-error pusht">
	                   <h3>
	                    ${errorMsg}
	                   </h3>
	               </div>
	        </div>
        </div>
        <div class="form-btn-content">
		    <button class="btn" type="button" onclick="javascript:window.close();">关闭</button>
  		</div>
    </div>
</body>
</c:otherwise>
</c:choose>

</html>