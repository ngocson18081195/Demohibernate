<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="input" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ngocson
  Date: 30/09/2017
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script>
        $( function() {
            $( "#da" ).datepicker({
                dateFormat: "dd/mm/yy"
            });
        } );
    </script>
</head>
<body>
<div class="col-md-offset-4">
<form:form method="post" modelAttribute="emp" action="${pageContext.request.contextPath}/employee"
           enctype="multipart/form-data">
    <c:if test="${not empty emp.ID}">
        <form:hidden path="ID"/>
    </c:if>
    <table>
        <tr>
            <td>Name :</td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>BirthDay</td>
            <td><form:input id="da" path="date" readonly=""/></td>
            <td><form:errors path="date"/></td>
        </tr>
        <tr>
            <td>Image</td>
            <td><form:input path="multipartFile" type="file"/></td>

        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Create"/> <input type="reset" value="Reset"/></td>
        </tr>

    </table>
</form:form>
</div>

<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">List Sale Product</span></div>
    <div class="tablecontainer">
        <table class="table table-hover" border="2" style="width: 100%">
            <thead>
            <tr>
                <th>ID.</th>
                <th>Name</th>
                <th>BirthDay</th>
                <th>Image</th>
                <th width="20%"></th>
            </tr>
            </thead>
            <tbody>
            <!--/*@thymesVar id="listproduct" type="java.lang.Iterable<demoweb.App.Entity.ProductSale>"*/-->
            <c:forEach items="${list}" var="emp">
            <tr>
                    <input:formatDate value="${emp.date}" var="birthday" pattern="dd/MM/yyyy"/>
                    <td>${emp.ID}</td>
                    <td>${emp.name}</td>
                    <td>${birthday}</td>
                    <td><img src="${pageContext.request.contextPath}/image/${emp.ID}" style="height:70px;width: 70px;">
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/employee?ID=${emp.ID}">
                            <button type="button" class="btn btn-success custom-width">
                                Edit
                                    <%--<span class="glyphicon glyphicon-star"></span>--%>
                            </button>
                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/delete/${emp.ID}">
                            <button type="button" class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash"></span> Star
                            </button>
                        </a>
                    </td>
            </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>
</div>


</body>
</html>
