<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.mem.model.*" %>

<%
    MemService memSvc = new MemService();
    MemberVO MemberVO = (MemberVO) request.getAttribute("MemberVO");
%>
<!DOCTYPE html>
<html>
<head>
    <title>listOneMember</title>
    <style>
        #content {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        #Commenttable {
            display: flex;
            justify-content: center;
            flex-direction: column;
        }

        table {
            border: 1px solid #ccc;
            border-collapse: collapse;
            margin: 0;
            padding: 0;
            width: 50%;
            table-layout: fixed;
        }

        table caption {
            font-size: 1.5em;
            margin: .5em 0 .75em;
        }

        table tr {
            background-color: #f8f8f8;
            border: 1px solid #ddd;
            padding: .35em;
        }

        table th,
        table td {
            padding: .625em;
            text-align: center;
        }

        table th {
            font-size: .85em;
            letter-spacing: .1em;
            text-transform: uppercase;
        }

        @media screen and (max-width: 600px) {
            table {
                border: 0;
            }

            table caption {
                font-size: 1.3em;
            }

            table thead {
                border: none;
                clip: rect(0 0 0 0);
                height: 1px;
                margin: -1px;
                overflow: hidden;
                padding: 0;
                position: absolute;
                width: 1px;
            }

            table tr {
                border-bottom: 3px solid #ddd;
                display: block;
                margin-bottom: .625em;
            }

            table td {
                border-bottom: 1px solid #ddd;
                display: block;
                font-size: .8em;
                text-align: right;
            }

            table td::before {
                /*
                * aria-label has no advantage, it won't be read inside a table
                content: attr(aria-label);
                */
                content: attr(data-label);
                float: left;
                font-weight: bold;
                text-transform: uppercase;
            }

            table td:last-child {
                border-bottom: 0;
            }
    </style>


</head>
<body>
<%@ include file="../navbar.file" %>
<br>
<div id="CONTENT">
    <div id="Commenttable">

        <c:if test="${not empty errorMsgs}">
            <font style="color: red">請修正以下錯誤:</font>
            <ul>
                <c:forEach var="message" items="${errorMsgs}">
                    <li style="color: red">${message}</li>
                </c:forEach>
            </ul>
        </c:if>
        <table>
            <tr>
                <td>會員編號:</td>
                <td>${MemberVO.memberNo}</td>
            </tr>
            <tr>
                <td>會員姓名:</td>
                <td>${MemberVO.memberName}</td>
            </tr>
            <tr>
                <td>會員電話:</td>
                <td>${MemberVO.memberPhone}</td>
            </tr>
            <tr>
                <td>會員暱稱:</td>
                <td>${MemberVO.memberNickname}</td>
            </tr>
            <tr>
                <td>會員地址:</td>
                <td>${MemberVO.memberAddress}</td
            </tr>
            <tr>
                <td>會員e-mail:</td>
                <td>${MemberVO.memberEmail}</td>
            </tr>
            <tr>
                <td>會員狀態:</td>
                <td>${MemberVO.memberState}</td>
            </tr>
        </table>
    </div>
</div>


</body>
</html>