<%-- 
    Document   : Comparar
    Created on : 22-jul-2016, 20:19:44
    Author     : jorge
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comparación</title>
    </head>
    <body style="background-color: lightcyan">
        <%@ include file="/WEB-INF/menu.jspf" %>
        <h1>Comparar</h1>
        <c:if test="${numActual==0 && numAlternativa==0}">
            <h3>No se han realizado simulaciones.</h3>
        </c:if>
        <c:if test="${numActual!=0 && numAlternativa==0}">
            <c:set var="promedioActual" value="${promActual/numActual}"></c:set>
                <h3>No se han realizado simulaciones con la Política Alternativa, no se puede calcular cuál es mejor.</h3>
        </c:if>
        <c:if test="${numActual==0 && numAlternativa!=0}">
            <c:set var="promedioAlternativa" value="${promAlternativa/numAlternativa}"></c:set>
                <h3>No se han realizado simulaciones con la Política Actual, no se puede calcular cuál es mejor.</h3>
        </c:if>
        <c:if test="${numActual!=0 && numAlternativa!=0}">
            <c:set var="promedioActual" value="${promActual/numActual}"></c:set>
            <c:set var="promedioAlternativa" value="${promAlternativa/numAlternativa}"></c:set>

        </c:if>

    <center>
        <table  class="table" border="1" style="text-align: center">
            <%-- INICIO CABECERA TABLA--%>  
            <thead style="background-color: #D8D8D8" >
            <th></th>
            <th>Política Actual</th>
            <th>Política Alternativa</th>
            </thead>
            <tbody>
                <tr>
                    <td style="background-color: #D8D8D8; font-weight: bold">Repeticiones</td>
                    <td>${numActual}</td>
                    <td>${numAlternativa}</td>
                </tr>
                <tr>
                    <td style="background-color: #D8D8D8; font-weight: bold">Promedio</td>
                    <c:if test="${numActual!=0}">
                        <td><fmt:formatNumber value="${promedioActual}" maxFractionDigits="3"></fmt:formatNumber></td>
                    </c:if>
                    <c:if test="${numActual==0}">
                        <td>${numActual}</td>
                    </c:if>
                    <td>${numAlternativa}</td>
                </tr>
            </tbody>
        </table>
    </center>
</body>
</html>
