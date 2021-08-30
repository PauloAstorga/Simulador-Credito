<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejercicio 1</title>
    </head>
    <body>
        <fieldset><legend>Simulador Credito Hipotecario</legend>
            <form action="ServletEjercicio1" method="GET">
                <h4>Nombre</h4>
                <input type="text" id="nombre" name="nombre" value="${name}"><br>
                <h4>Monto</h4>
                <input type="number" id="monto" name="monto" value="${uf}">UF<br>
                <h4>Cuota (años)</h4>
                <select id="cuota" name="cuota" value="${cuotas}">
                    <option value="">Seleccione
                    <%for (int i=0;i<30;i++){%>
                    <option><%=i+1%>
                    <%}%>
                </select>
                <h4>Seguro desgravamen</h4>
                <label for="si">Si</label>
                <input type="radio" name="seguro" id="seguro" value="Si">
                <label for="no">No</label>
                <input type="radio" name="seguro" id="seguro" value="No"><br>
                
                <h4>Seguro Contra incendio</h4>
                <label for="si">Si</label>
                <input type="radio" name="seguro2" id="seguro2" value="Si">
                <label for="no">No</label>
                <input type="radio" name="seguro2" id="seguro2" value="No"><br>
                
                <br><input type="submit" value="Aceptar">
            </form>
                
            <c:if test="${not empty error}">
            <h4 style="color:red">Hubo un error en el ingreso de parametros.</h4>            
            </c:if>
            
            
        </fieldset>
        <c:if test="${not empty finalizado}">
            <h1 style="color:green">No hubo errores :D</h1>
                <fieldset><legend>Datos: </legend>
                <h3>Nombre: <%=request.getAttribute("name")%></h3>
                <h3>Cuotas: <%=request.getAttribute("cuotas")%></h3>
                <h3>Seguro desgravamen: <%=request.getAttribute("seguro1")%> (10% del total)</h3>
                <h3>Seguro incendio: <%=request.getAttribute("seguro2")%> (5% del total)</h3>
                <h3>Interes aplicados: <%=request.getAttribute("Interes")%>% del total<br>
                    <%=request.getAttribute("InteresValor")%> CLP</h3>
                <h3>Cuota mensual: <%=request.getAttribute("cuotaMensual")%> CLP</h3>
                <h3>Total: <%=request.getAttribute("Total")%> CLP</h3>
                </fieldset>
            </c:if>        
    </body>
</html>