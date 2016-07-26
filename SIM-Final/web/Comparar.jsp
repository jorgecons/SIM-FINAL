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
            <h3>Para un tiempo de simulación de ${tiempoSimulacion*60} minutos, se obtiene que:</h3>
            <c:if test="${promedioActual<promedioAlternativa}">
                <h3>Es mejor la <span style="color: royalblue;font-weight: bolder; font-size: 20px">Política Actual</span> con un promedio de 
                    <span style="color: royalblue;font-weight: bolder; font-size: 20px"><fmt:formatNumber value="${promedioActual}" maxFractionDigits="3"></fmt:formatNumber></span>
                    minutos por cliente. No se desea realizar cambios.</h3>
            </c:if>
            <c:if test="${promedioActual>promedioAlternativa}">
                <h3>Es mejor la <span style="color: royalblue;font-weight: bolder; font-size: 20px">Política Alternativa</span> con un promedio de 
                    <span style="color: royalblue;font-weight: bolder; font-size: 20px"><fmt:formatNumber value="${promedioAlternativa}" maxFractionDigits="3"></fmt:formatNumber></span>
                    minutos por cliente. Se recomienda cambiar la política.</h3>
            </c:if>
        </c:if>

    <center>
        <table border='1' cellspacing=0 cellpadding=2 style="text-align: center">
            <%-- INICIO CABECERA TABLA--%>  
            <thead style="background-color: khaki" >
            <th></th>
            <th>Política Actual</th>
            <th>Política Alternativa</th>
            </thead>
            <tbody>
                <tr>
                    <td style="background-color: khaki; font-weight: bold">Repeticiones</td>
                    <td>${numActual}</td>
                    <td>${numAlternativa}</td>
                </tr>
                <tr>
                    <td style="background-color: khaki; font-weight: bold">Promedio</td>
                    <c:if test="${numActual!=0}">
                        <td><fmt:formatNumber value="${promedioActual}" maxFractionDigits="3"></fmt:formatNumber></td>
                    </c:if>
                    <c:if test="${numActual==0}">
                        <td>${numActual}</td>
                    </c:if>
                    <c:if test="${numAlternativa!=0}">
                        <td><fmt:formatNumber value="${promedioAlternativa}" maxFractionDigits="3"></fmt:formatNumber></td>
                    </c:if>
                    <c:if test="${numAlternativa==0}">
                        <td>${numAlternativa}</td>
                    </c:if>
                </tr>
            </tbody>
        </table>
    </center>
</body>
</html>
