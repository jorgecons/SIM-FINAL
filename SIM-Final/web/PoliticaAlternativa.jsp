<%-- 
    Document   : PoliticaAlternativa
    Created on : 22-jul-2016, 20:19:21
    Author     : jorge
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Política Alternativa</title>
    </head>
    <body style="background-color: lightcyan">
        <%@ include file="/WEB-INF/menu.jspf" %>

        <h1>Politica Alternativa</h1>
        <h3>Para un tiempo de simulación de ${tiempoSimulacion*60} minutos, se obtiene que:</h3>
        <h3>El tiempo promedio de los clientes en el banco es de 
            <span style="color: royalblue;font-weight: bolder; font-size: 20px"><fmt:formatNumber value="${promedio}" maxFractionDigits="3"></fmt:formatNumber></span>
            minutos para <span style="color: royalblue;font-weight: bolder; font-size: 20px">${clientesAtendidos}</span> clientes atendidos.</h3>

        <%-- INICIO TABLA--%>      
        <table  class="table" border="1" style="text-align: center">
            <%-- INICIO CABECERA TABLA--%>  
            <thead style="background-color: #D8D8D8" >
            <th>Vuelta</th>
            <th>Evento Actual</th>
            <th>Tiempo (minutos)</th>
            <th>rnd Llegada Cliente</th> 
            <th>Llegada entre Cliente</th> 
            <th style="background-color: chartreuse">Proxima Llegada Cliente</th> 
            <th>rnd Tipo Atención</th> 
            <th>Tipo Atención</th>  
            <th>rnd Atencion</th> 
            <th>Tiempo Atencion</th>
                <c:forEach  var="c" begin ="1" end="3">
                <th style="background-color: chartreuse">Tiempo Fin Atencion</th>
                </c:forEach>
                <c:forEach  var="c" begin ="1" end="3">
                <th style=>Empleado</th>
                </c:forEach>
            <th>Cola</th>
            <th>Clientes Atendidos</th>
            <th>Tiempo Acumulado</th>
            <th colspan="${clientesMax*3}">Clientes</th>


        </thead>
        <%-- INICIO CUERPO/FILA TABLA--%>  

        <tbody>
            <c:forEach items="${vectorMostrar}" var="p"  varStatus="loop">
                <tr>
                    <td>${loop.index}</td>
                    <c:if test="${p.eventoActual == 0}">
                        <td>Inicializo</td>
                        <td><fmt:formatNumber value="${p.tiempoActual}" maxFractionDigits="3"></fmt:formatNumber></td>
                        <td><fmt:formatNumber value="${p.rndLlegadaCliente}" maxFractionDigits="3"></fmt:formatNumber></td>
                        <td><fmt:formatNumber value="${p.tiempoEntreLlegadaCliente}" maxFractionDigits="3"></fmt:formatNumber></td>
                    </c:if>
                    <c:if test="${p.eventoActual == 1}">
                        <td>Llegada Cliente</td>
                        <td><fmt:formatNumber value="${p.tiempoActual}" maxFractionDigits="3"></fmt:formatNumber></td>
                        <td><fmt:formatNumber value="${p.rndLlegadaCliente}" maxFractionDigits="3"></fmt:formatNumber></td>
                        <td><fmt:formatNumber value="${p.tiempoEntreLlegadaCliente}" maxFractionDigits="3"></fmt:formatNumber></td>
                    </c:if>

                    <c:if test="${p.eventoActual == 3}">
                        <td>Fin Atencion</td>
                        <td><fmt:formatNumber value="${p.tiempoActual}" maxFractionDigits="3"></fmt:formatNumber></td>
                            <td></td>
                            <td></td>
                    </c:if>
                    <c:if test="${p.siguienteEvento <= 1}">
                        <td style="background-color: chartreuse "><fmt:formatNumber value="${p.tiempoLlegadaProximoCliente}" maxFractionDigits="3"></fmt:formatNumber></td>
                    </c:if>
                    <c:if test="${p.siguienteEvento > 1}">
                        <td><fmt:formatNumber value="${p.tiempoLlegadaProximoCliente}" maxFractionDigits="3"></fmt:formatNumber></td>
                    </c:if>
                    <c:if test="${p.rndFinLlenado!=0}">
                        <td><fmt:formatNumber value="${p.rndFinLlenado}" maxFractionDigits="3"></fmt:formatNumber></td>
                        <c:if test="${p.rndFinLlenado<0.7}">
                            <td>Entre 9' y 15'</td>
                        </c:if>
                        <c:if test="${p.rndFinLlenado>=0.7}">
                            <td>Exponencial Negativa</td>
                        </c:if>
                    </c:if>
                    <c:if test="${p.rndFinLlenado==0}">
                        <td></td>
                        <td></td>
                    </c:if>       
                    <c:if test="${p.rndFinAtencion==0}">
                        <td></td>
                        <td></td>
                    </c:if>
                    <c:if test="${p.rndFinAtencion!=0}">
                        <td><fmt:formatNumber value=" ${p.rndFinAtencion}" maxFractionDigits="3"></fmt:formatNumber></td>
                        <td><fmt:formatNumber value=" ${p.tiempoAtencion}" maxFractionDigits="3"></fmt:formatNumber></td>
                    </c:if>
                    <c:forEach items="${p.tiemposFinAtencion}" var="t" varStatus="loop">
                        <c:if test="${t == 0}">
                            <td style="background-color: darkgray"></td>
                        </c:if>
                        <c:if test="${t != 0}"> 
                            <c:if test="${p.siguienteEvento==3}">
                                <c:if test="${p.numEmp==loop.index}">
                                    <td style="background-color: chartreuse"><fmt:formatNumber value=" ${t}" maxFractionDigits="3"></fmt:formatNumber></td> 
                                </c:if>
                                <c:if test="${p.numEmp!=loop.index}">
                                    <td><fmt:formatNumber value=" ${t}" maxFractionDigits="3"></fmt:formatNumber></td> 
                                </c:if>
                            </c:if>
                            <c:if test="${p.siguienteEvento!=3}">
                                <td><fmt:formatNumber value=" ${t}" maxFractionDigits="3"></fmt:formatNumber></td> 
                            </c:if>
                        </c:if>
                    </c:forEach>
                    <c:forEach items="${p.empleados}" var="t" varStatus="loop">
                        <c:if test="${t.estado!='LIBRE' }">
                            <td style="background-color: firebrick">${t.estado}</td>

                        </c:if>
                        <c:if test="${t.estado=='LIBRE' }">
                            <td style="background-color: lightgreen">${t.estado}</td>

                        </c:if>
                    </c:forEach>
                    <td>${p.cola.size()}</td>
                    <td>${p.clientesAtendidos}</td>
                    <td><fmt:formatNumber value="${p.tiempoTotalEnBanco}" maxFractionDigits="3"></fmt:formatNumber></td>
                    <c:forEach items="${p.clientes}" var="t" varStatus="loop">
                        <c:if test="${t.siendoAtendido!=67}">
                            <td>${t.estado}</td>
                            <c:if test="${t.siendoAtendido!=-1}">
                                <td>${t.siendoAtendido}</td>
                            </c:if>
                            <c:if test="${t.siendoAtendido==-1}">
                                <td></td>
                            </c:if>
                            <td><fmt:formatNumber value="${t.tiempoLlegada}" maxFractionDigits="3"></fmt:formatNumber></td>
                        </c:if>

                    </c:forEach>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</body>
</html>
