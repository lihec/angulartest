<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<html>
<head>
    <script src="/static/js/jquery-1.10.2.min.js"></script>

    <script type="text/javascript">
        function testAjax(id) {
            $.ajax({
                type: "GET",
                url: "/jn",
                data: {id : id},
                dataType: "json",
                success: function(json){
                    console.info(json);
                    if(json.result=='ok'){
                        alert('调用ajax成功');
                    }else{
                        alert(json.msg);
                    }
                }
            });
        }
    </script>
</head>
<body>
<h2>这是一个页面</h2>

<button type="button" onclick="testAjax()">正常ajax</button>
<button type="button" onclick="testAjax('1')">异常ajax</button>

</body>
</html>
