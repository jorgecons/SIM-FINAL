<%-- 
    Document   : Inicio
    Created on : 16-jul-2016, 21:40:03
    Author     : jorge
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Trabajo Final Simulación</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body style="background-color: lightcyan">
        <%@ include file="/WEB-INF/menu.jspf" %>
    <center> <h1>Trabajo Final Simulación</h1></center>
    <h2>Ejercicio 304</h2>
    <p>
        Un banco está analizando la implementación de un cambio en la forma de atención de solicitantes de crédito.
    </p>
    <p>
        <b>Actualmente el procedimiento es el siguiente:</b> los solicitantes llegan, obtienen un formulario predefinido,
        que tienen que llenar para lo cual tardan 39 minutos de media con distribución exponencial.
        Luego se dirigen a alguno de los tres empleados que leen y revisan el formulario completándolo en
        caso de ser necesario, para lo cual demoran entre 5 y 11 minutos distribución uniforme.
    </p>
    <p>
        <b> Se propone el siguiente procedimiento alternativo:</b> los solicitantes llegan un pre-formulario por
        Internet, y una vez validado por personal del banco, deben acertarse al empleado del sector, el cuál
        le hace completar los datos faltantes.
        El empleado demora en atender a un cliente entre 9 y 15 minutos el 70% de las veces y el resto con
        una distribución exponencial media 12.
    </p>
    <center>    <table border='1' cellspacing=0 cellpadding=2 style="text-align: center">
            <tr style="font-weight: bold ;background-color: khaki">
                <td>Tiempo de Atención</td>
                <td>Probabilidad</td>
                <td>Min - Max</td>
            </tr>
            <tr>
                <td>Entre 9' y 15'</td>
                <td>70%</td>
                <td>0.00 - 0.69</td>
            </tr>
            <tr>
                <td>Exponencial con media 12'</td>
                <td>30%</td>
                <td>0.70 - 0.99</td>
            </tr>
        </table>
    </center>
    <p>
        El tiempo medio entre llegadas de los solicitantes es de 5 solicitantes por hora (distribución
        exponencial).
    </p>
    <p>
        Determinar si con la mejora, el tiempo que demora un cliente en el banco es menor.
    </p>
    <form method="post" action="${pageContext.request.contextPath}/Servidor" name="datos">
        <center>
            <p>

                Ingrese las horas a simular:

                <c:if test="${primeraVez==true}">

                    <input required type="number" name="tiempo">

                </c:if>
                <c:if test="${primeraVez==false}">
                    <input readonly required type="number" value="${tiempoSimulacion}" name="tiempo">
                </c:if>
            </p>
            <input style="font-family: Arial; font-size: 16px"  type="submit" name="politicaActual" value="Simular Política Actual">
            <input style="font-family: Arial; font-size: 16px" type="submit" name="politicaAlternativa" value="Simular Política Alternativa">
            <input style="font-family: Arial; font-size: 16px" type="submit" name="comparar" value="Comparar">

        </center>
    </form>
</body>
</html>
