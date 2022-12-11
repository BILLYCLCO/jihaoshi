<%@ page import="com.meal.model.MealVO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<title>${meal.mealName}</title>
<style>
#content {
	display: flex; 
	justify-content: center; 
	align-items: center;
}
.prod {
	display:flex;
	flex-wrap:wrap;
	justify-content: space-around;  
}
.button {
 	border-radius:1rem; 
 	border: 1px solid #ccc;
}
.img {
	margin-right: 50px;
	width:45%;
}
.productPage {
	text-align: left;
	line-height: 35px; 
	margin:auto 0;
	width:45%
}
</style>
</head>
<body>
	<%@ include file="../navbar.file"%>
	<br>
	<div id="CONTENT">
		<!--商品欄開始-->
		<div class="prod">
			<div class="img">
				<img src="${meal.showPhoto}" class="productPhoto"
					style="width: 100%">
			</div>
			<div class="productPage">
				<span class="mealDescription"> <label>菜單名稱：</label><span>${meal.mealName}</span>
					<br> <label>內容物：</label><span>${meal.mealContent}</span> <br>
					<label>熱量：</label><span id="cal">${meal.mealCal}</span> <br> <label>營養特色：</label><span
					style="font-size: 18px"> <c:forEach
							var="nutrientFeatureDetail"
							items="${meal.nutrientFeatureDetails}">
							<a
								href="${ctxPath}/meal/mealController?action=hashtag&featureName=${nutrientFeatureDetail.featureName }"
								style="font-style: italic">#${nutrientFeatureDetail.featureName}&ensp;</a>
						</c:forEach>
				</span> <br> <label>過敏源：</label><span>${meal.mealAllergen}</span> <br>
					<label>價格：</label><span id="price">${meal.mealPrice}</span> <br>
					<label>食譜：</label><span>${meal.mealRecipe}</span>
					<form method="post" action="${ctxPath}/meal/mealController"
						id="form1">
						<input type="text" name="action" value="toUpdate" hidden>
						<input type="text" value="${meal.mealNo}" name="mealNo" hidden>
					</form>
					<label>是否上架：</label><span>${meal.launch==1?"上架中" : "未上架"}</span> <br>
					<label>更新時間：</label>
				<fmt:formatDate value="${meal.updateTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /> <br>
					<button type="submit" form="form1" class="button">修改資料</button>
			</div>
		</div>
	</div>
	<script>

	</script>
</body>
</html>
