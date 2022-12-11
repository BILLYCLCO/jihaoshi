<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.mem.model.*" %>

<%
    MemService memSvc = new MemService();
    List<MemberVO> list = memSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<html>
<head>

    <title>listAllMember</title>
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
            width: 95%;
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
                <h1>會員資料</h1>
                <FORM METHOD="post" ACTION="../mem/MemberServlet">
                    <b>查詢會員資料 :</b> <input type="text" name="memberNo"> <input
                        type="hidden" name="action" value="getOne_For_Display">
                    <input type="submit" value="送出">
                </FORM>
                <table>
                    <tr>
                        <th>會員編號</th>
                        <th>會員姓名</th>
                        <th>會員電話</th>
                        <th>會員暱稱</th>
                        <th>會員地址</th>
                        <th>會員e-mail</th>
                        <th>會員狀態</th>

                    </tr><div style="margin: 0px auto;">
                    <%@ include file="page1Mem.file" %>
                </div>
                    <br>
                    <c:forEach var="MemberVO" items="${list}" begin="<%= pageIndex %>" end="<%= pageIndex+rowsPerPage-1 %>">
                        <tr>
                            <td>${MemberVO.memberNo}</td>
                            <td>${MemberVO.memberName}</td>
                            <td>${MemberVO.memberPhone}</td>
                            <td>${MemberVO.memberNickname}</td>
                            <td>${MemberVO.memberAddress}</td>
                            <td>${MemberVO.memberEmail}</td>
                            <td>${MemberVO.memberState}</td>
                            <td>
                                <FORM METHOD="post"
                                      ACTION="<%=request.getContextPath()%>/mem/MemberServlet"
                                      style="margin-bottom: 0px;">
                                    <input type="submit" value="修改"> <input
                                        type="hidden" name="memberNo" value="${MemberVO.memberNo}">
                                    <input type="hidden" name="action"
                                           value="getOne_For_Update">

                                </FORM>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
        <br>
        <%@ include file="page2Mem.file" %>
        </div>
    </div>


</body>
</html>