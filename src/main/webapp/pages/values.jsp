<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Quadratic</title>
</head>
<body>
<h1>${message}</h1>

<form:form method="POST" action="calculate" modelAttribute="params">
    <table>
        <tr>
            <td><form:label path="a">a</form:label></td>
            <td><form:input  path="a"/></td>
        </tr>
        <tr>
            <td><form:label path="b">b</form:label></td>
            <td><form:input path="b"/></td>
        </tr>
        <tr>
            <td><form:label path="c">c</form:label></td>
            <td><form:input path="c"/></td>
        </tr>
        <br/>
        <tr>
            <td colspan="2">
                <input type="submit" value="Рассчитать"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>