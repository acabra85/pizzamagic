<%--
  Created by IntelliJ IDEA.
  User: Agustin
  Date: 12/10/2016
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Magic Pizza</title>
        <link href="../../css/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <table><tr align="center"><td colspan="3">
            <div><table><tr>
                <td><h1>Magic Pizza!</h1></td>
                <td>
                    <s:url var="url" action="order">
                        <s:param name="request_locale">en</s:param>
                    </s:url>
                    <s:a href="%{url}">En</s:a>
                </td>
                <td>
                    <s:url var="url" action="order">
                        <s:param name="request_locale">fr</s:param>
                    </s:url>
                    <s:a href="%{url}">Fr</s:a>
                </td>
            </tr></table></div>
        </td>
        </tr>
            <tr>
            <td><img src="../../img/pizza1.png"></td>
            <td><img src="../../img/pizza2.png"></td>
            <td><img src="../../img/pizza3.png"></td>
        </tr></table>
        <s:form action="order" namespace="/mp">
            <s:iterator value="pizzas">
                <s:radio theme="simple" name="pizzaId" list="#{id:name}" value="defaultPizzaValue"/><br>
            </s:iterator>
            <s:textfield key="quantity" />
            <s:textfield key="name" />
            <s:textfield key="address" />
            <s:textfield key="telephone" />
            <s:submit key="PizzaOrder.submit" style="float:left"/>
        </s:form>

        <!-- Action Results-->
        <s:set name="showProcessed" value="orderProcessed"/>
        <s:set name="showQuantityError" value="quantityError"/>
        <s:if test="%{#showProcessed==true}">
            <s:div class="success">
                <s:text name="PizzaOrder.orderplacedStart" />
                <s:property value="orderTotal" />
                <s:text name="PizzaOrder.orderplacedEnd" />
            </s:div>
        </s:if>
        <s:if test="%{#showQuantityError==true}">
            <s:div  class="error" id="error_box">
                <s:text name="PizzaOrder.wrongquantity" />
            </s:div>
        </s:if>
    </body>
</html>