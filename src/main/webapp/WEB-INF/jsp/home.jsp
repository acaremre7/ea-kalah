<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
    <script>
    window.onload = function() {
    };
    </script>
</head>
<body>
    <div class="header">
	    <center><h1>Kalah Game</h1></center>
	    <center><h2>
	    <c:choose>
	    <c:when test = "${state eq 'NEW'}">
	        Welcome to Kalah. Press button below to start.
	    </c:when>
	    <c:when test = "${state eq 'SOUTH'}">
	        South's turn to play.
	    </c:when>
	    <c:when test = "${state eq 'NORTH'}">
	        North's turn to play.
	    </c:when>
	    <c:when test = "${state eq 'FINISHED'}">
	        Game over !
	        <c:choose>
	        <c:when test = "${fn:length(winner) eq 2}">
	        It's a draw !
	        </c:when>
	        <c:otherwise>
                Winner is: ${winner[0]}
	        </c:otherwise>
	        </c:choose>
	    </c:when>
	    </c:choose>
	    </h2></center>
	</div>
	<br/>
	<br/>

	<c:if test="${not empty board}">
        <table class="kalah-table">
            <tr class="<c:choose><c:when test = "${state eq 'NORTH'}">clickable</c:when><c:otherwise>not-clickable</c:otherwise></c:choose>">
            <td class="kalah-pit-cell"<c:if test="${state eq 'NORTH'}"> onclick="play(12)"</c:if>>${board[12]}</td>
            <td class="kalah-pit-cell"<c:if test="${state eq 'NORTH'}"> onclick="play(11)"</c:if>>${board[11]}</td>
            <td class="kalah-pit-cell"<c:if test="${state eq 'NORTH'}"> onclick="play(10)"</c:if>>${board[10]}</td>
            <td class="kalah-pit-cell"<c:if test="${state eq 'NORTH'}"> onclick="play(9)"</c:if>>${board[9]}</td>
            <td class="kalah-pit-cell"<c:if test="${state eq 'NORTH'}"> onclick="play(8)"</c:if>>${board[8]}</td>
            <td class="kalah-pit-cell"<c:if test="${state eq 'NORTH'}"> onclick="play(7)"</c:if>>${board[7]}</td>
            </tr>
            <tr>
            <td class="kalah-cell">${board[13]}</td>
            <td class="kalah-empty-cell" style="text-align: center;vertical-align: top;font-size:12px">North &#8593;</td>
            <td class="kalah-empty-cell"></td>
            <td class="kalah-empty-cell"></td>
            <td class="kalah-empty-cell" style="text-align: center;vertical-align: bottom;font-size:12px">South &#8595;</td>
            <td class="kalah-cell">${board[6]}</td>
            </tr>
            <tr class="<c:choose><c:when test = "${state eq 'SOUTH'}">clickable</c:when><c:otherwise>not-clickable</c:otherwise></c:choose>">
            <td class="kalah-pit-cell"<c:if test="${state eq 'SOUTH'}"> onclick="play(0)"</c:if>>${board[0]}</td>
            <td class="kalah-pit-cell"<c:if test="${state eq 'SOUTH'}"> onclick="play(1)"</c:if>>${board[1]}</td>
            <td class="kalah-pit-cell"<c:if test="${state eq 'SOUTH'}"> onclick="play(2)"</c:if>>${board[2]}</td>
            <td class="kalah-pit-cell"<c:if test="${state eq 'SOUTH'}"> onclick="play(3)"</c:if>>${board[3]}</td>
            <td class="kalah-pit-cell"<c:if test="${state eq 'SOUTH'}"> onclick="play(4)"</c:if>>${board[4]}</td>
            <td class="kalah-pit-cell"<c:if test="${state eq 'SOUTH'}"> onclick="play(5)"</c:if>>${board[5]}</td>
            </tr>
        </table>
    </c:if>

	<c:if test="${state eq 'NEW' || state eq 'FINISHED'}">
	<br/><br/>
	<center>
	    <button onclick="newGame();" class="btn-newgame">New Game</button>
    </center>
	</c:if>

</body>
</html>