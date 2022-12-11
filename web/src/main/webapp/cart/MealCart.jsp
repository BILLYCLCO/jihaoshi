<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>菜單商品購物車</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
#content {
	display: flex;
	justify-content: center;
	align-items: center;
}

#cart {
	width: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.all {
	width: 90%;
	display: flex;
	justify-content: Space-around;
	align-items: center;
	border-style: outset;
}
.button {
 	border-radius:1rem; 
 	border: 1px solid #ccc;
}

.pic {
	padding-left: 10px;
	width:30%;
}
.info {
	width:50%;
	padding-bottom: 40px;
	padding-top: 20px;
}
.update {
	width:20%;
}
.img {
	width:80%;
}
</style>
</head>
<body>
	<%@ include file="../navbar.file"%>
	<br>
	<div id="content">
		<div id="cart">
			<c:forEach var="cartProd" items="${cartProds}" varStatus="loop">
				<div class="all">
					<div class="pic">
						<a  href="${ctxPath}/meal/mealController?action=findByprod&mealNo=${cartProd.meal.mealNo}">
							<img class="img" src="${cartProd.meal.showPhoto}">
						</a>
					</div>

					<div class="info">
						<br>
						<h5>
							<a href="${ctxPath}/meal/mealController?action=findByprod&mealNo=${cartProd.meal.mealNo}">${cartProd.meal.mealName}</a>
						</h5>
						<br> <span style="font-size: 18px">分量：${cartProd.quantity}</span>
						<br> <span style="font-size: 18px">熱量：${cartProd.cal}</span>
						<br> <span style="font-size: 18px">簡易食譜：${cartProd.meal.mealRecipe}</span>
						<br> <span style="font-size: 18px">單價NT$<fmt:formatNumber value="${cartProd.meal.mealPrice*cartProd.quantity}" pattern="#,###" /></span>
						<br> <span style="font-size: 18px">總價NT$<fmt:formatNumber value="${cartProd.price}" pattern="#,###" /> </span>
						<br> <span style="font-size: 18px">數量：</span> <span style="font-size: 18px"
							id="amount_value_${loop.index}">${cartProd.amount}</span>
					</div>
					<div class="update">
						<form method="post" action="${ctxPath}/cart/cartController"
							enctype="application/x-www-form-urlencoded"
							id="cart${loop.index}">
							<input name="action" type="text" value="cartModify" hidden>
							<input name="amount" type="range" min="1" max="99"
								value="${cartProd.amount}" id="amount${loop.index}"> <input
								name="cartIndex" type="text" value="${loop.index}" hidden>
						</form>
						<br>
						<form method="post" action="${ctxPath}/cart/cartController"
							enctype="application/x-www-form-urlencoded"
							id="cartDelete${loop.index}">
							<input name="action" type="text" value="cartDelete" hidden>
							<input name="cartIndex" type="text" value="${loop.index}" hidden>
						</form>
						<button type="submit" form="cart${loop.index}" class="button">修改數量</button>
						<button type="submit" form="cartDelete${loop.index}" class="button">從購物車刪除</button>
						<br>
					</div>
				</div>

			</c:forEach>
			<div id="totalPrice">
				<c:choose>
					<c:when test="${empty cartProds}">
						<div>
							<span style="font-size: 16px;">購物車中還沒有東西喔</span>
							<button class="button">
								<a href="${ctxPath}/meal/mealController?action=listAll"
									style="text-decoration: none; color: #333;">去選購</a>
							</button>
							<br>
						</div>
					</c:when>
					<c:otherwise>
						<br>
					<div style="display: flex; justify-content: center; align-items: center;flex-direction: column;">
						<div>
						<span style="font-size: 16px;">商品總價：${totalPrice}元 </span>&nbsp;
						<button type="submit" form="checkout" class="button">去結帳</button>
						<button type="submit" form="clearCart" class="button">清空購物車</button>
						</div>						
						<form method="post"
							action="${ctxPath}/checkout/checkoutController" id="checkout"
							enctype="application/x-www-form-urlencoded">
							<input type="hidden" name="action" value="checkout">
						</form>
						<form method="post" action="${ctxPath}/cart/cartController"
							enctype="application/x-www-form-urlencoded" id="clearCart">
							<input type="hidden" value="clearCart" name="action">
						</form>						
						
					</div>
					</c:otherwise>
				</c:choose>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			<c:forEach var="cartProd" items="${cartProds}" varStatus="loop">
			$('#amount${loop.index}').on('change mousemove', function() {
				$('#amount_value_${loop.index}').html($(this).val());
			});

			</c:forEach>
		});
	</script>
</body>
</html>
