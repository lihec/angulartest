<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>出错啦！</title>
</head>
<div class="pop-wrapper pop-wrapper-680">
    <div class="block">
        <div class="thick-wrapper">
            <div class="alert alert-error pusht">
                <h3>
                    系统发生一个未知错误
                </h3>
                错误详情：
                请求：${path}，时间：<fmt:formatDate value="${timestamp}" pattern="yyyy-MM-dd HH:mm:ss" />，错误码：${status}，异常：${error}
            </div>
        </div>
    </div>
    <%--<div class="form-btn-content">--%>
        <%--<button class="btn" type="button" onclick="javascript:parent.closeDialog();">关闭</button>--%>
    <%--</div>--%>
</div>
</html>