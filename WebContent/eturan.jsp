<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.text.*,jp.co.jri.databean.*, java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="jp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>一覧画面</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>

<!-- paginathing.js for table paging -->
<script type="text/javascript" src="resources/js/paginathing.js"></script>

<!-- init -->
<script type="text/javascript">
	jQuery(document)
			.ready(
					function($) {
						for (var i = 1; i <= 150; i++) {
							$('.list-group').append(
									'_$tag_______________________ Item ' + i
											+ '_$tag');
						}

						$('.list-group').paginathing({
							perPage : 5,
							limitPagination : 9,
							containerClass : 'panel-footer',
							pageNumbers : true
						})

						$('.table tbody').paginathing({
							perPage : 5, // show item per page
							insertAfter : '.table', //class or id (eg: .element or #element). append the paginator after certain element
							pageNumbers : false, // showing current page number of total pages number, to work properly limitPagination must be true 
							limitPagination : false, // false or number. Limiting your pagination number.
							prevNext : true, // enable previous and next button
							firstLast : true, // enable first and last button
							prevText : '＜＜前５件　', // Previous button text prevText : '&laquo;',
							nextText : '　後５件＞＞', // Next button text nextText : '&raquo;', 
							firstText : '', // "First button" text
							lastText : '', // "Last button" text
							containerClass : 'pagination-container', // extend default container class
							ulClass : 'pagination', // extend default ul class
							liClass : 'page', // extend li class
							activeClass : 'active', // active link class
							disabledClass : 'disable' // disabled link class
						});
					});
</script>

<%
	//beanの処理
	T_NOTICE_INFO bean = (T_NOTICE_INFO) request.getAttribute("bean");
%>

</head>
<body>

	<!-- ---------------- -->
	<!-- ナビゲーションバー　 -->
	<!-- ---------------- -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a
		class="navbar-brand" href="#"> <b>FG情報同意状況</b> <!-- 		<img src="resources/img/jri.png"　width="200" height="90" alt="JRI Logo">
 -->
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="/ContainerPoc/Ichiran">一覧表示 <span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="hasshin_tukibetu"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> 発信月別 </a>
				<div class="dropdown-menu" aria-labelledby="hasshin_tukibetu">
					<a class="dropdown-item" href="#">2017/08</a> <a
						class="dropdown-item" href="#">2017/09</a> <a
						class="dropdown-item" href="#">2017/10</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">今月</a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="hasshin_bubetu"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> 発信部別 </a>
				<div class="dropdown-menu" aria-labelledby="hasshin_bubetu">
					<a class="dropdown-item" href="#">職域ソリューション部確定拠出年金推進室</a> <a
						class="dropdown-item" href="#">事務統括部</a> <a class="dropdown-item"
						href="#">リテール統括部</a> <a class="dropdown-item" href="#">ホールセール統括部</a>
					<a class="dropdown-item" href="#">市場営業統括部</a> <a
						class="dropdown-item" href="#">金融商品営業部</a> <a
						class="dropdown-item" href="#">ファイナンシャル・ソリューション統括部</a>
				</div></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="sakuseichu"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> 作成中 </a>
				<div class="dropdown-menu" aria-labelledby="sakuseichu">
					<a class="dropdown-item" href="#">新規登録</a> <a class="dropdown-item"
						href="#">一覧</a>
				</div></li>
			<li class="nav-item"><a class="nav-link disabled" href="#">管理</a>
			</li>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="検索">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">検索</button>
		</form>
	</div>
	</nav>

	<!-- --------------------- -->
	<!-- コンテンツのタイトルバー　 -->
	<!-- --------------------- -->
	<div class="container-fluid" style="background-color: SeaGreen;">
		<center>
			<%
				out.print(request.getAttribute("formTitle")); //表のタイトル
			%>
		</center>
	</div>
	<br>
	<!-- ---------------- -->
	<!-- コンテンツ　　　　　 -->
	<!-- ---------------- -->
	<div class="container">
		<form class="form-inline my-2 my-lg-0" method="post" action="Edit">
			<div style="margin-left: auto;">
				<button style="width: 100px;"
					class="btn btn-outline-success my-2 my-sm-0" type="submit">編集</button>
				<button style="width: 100px;"
					class="btn btn-outline-success my-2 my-sm-0" type="submit" disabled>一時保存</button>
				<button style="width: 100px;"
					class="btn btn-outline-success my-2 my-sm-0" type="submit" disabled>発信</button>
				<button style="width: 100px;"
					class="btn btn-outline-success my-2 my-sm-0" type="submit" disabled>削除</button>
			</div>
			<input type="text" name="searchid" hidden value=
			<%
				out.print(bean.getNOTICE_ID());
			%>
			>
		</form>

		<hr />

		<table class="table-bordered" style="height: 103px; width: 900px;"
			cellspacing="1" cellpadding="1">
			<tbody>

				<tr>
					<td style="width: 149px; background-color: LightGreen;">発信部</td>
					<td style="width: 900px;">
						<%
							out.print(bean.getBUTEN_NAME());
						%>
					</td>
				</tr>
				<tr>
					<td style="width: 149px; background-color: LightGreen;">掲示担当者</td>
					<td style="width: 1000px;">
						<%
							out.print(bean.getTANTO_NAME());
						%>
					</td>
				</tr>
				<tr>
					<td style="width: 149px; background-color: LightGreen;">件名</td>
					<td style="width: 900px;">
						<%
							out.print(bean.getNOTICE_TITLE());
						%>
					</td>
				</tr>
				<tr>
					<td style="width: 149px; background-color: LightGreen;">備考</td>
					<td style="width: 900px;">
						<%
							out.print(bean.getNOTICE_NOTE());
						%>
					</td>
				</tr>
			</tbody>
		</table>
		<p>&nbsp;</p>
		<br> <b>＜添付ファイル＞</b>
		<table class="table-bordered" style="height: 103px; width: 900px;"
			cellspacing="1" cellpadding="1">
			<tbody>
				<tr>
					<td style="width: 149px; background-color: LightGreen;">添付ファイル①</td>
					<td style="width: 900px;"><a href="#"> <%
 	out.print(bean.getFILE_TITLE1());
 %>
					</a></td>
				</tr>
				<tr>
					<td style="width: 149px; background-color: LightGreen;">添付ファイル②</td>
					<td style="width: 900px;"><a href="#"> <%
 	out.print(bean.getFILE_TITLE2());
 %>
					</a></td>
				</tr>
				<tr>
					<td style="width: 149px; background-color: LightGreen;">添付ファイル③</td>
					<td style="width: 900px;"><a href="#"> <%
 	out.print(bean.getFILE_TITLE3());
 %>
					</a></td>
				</tr>
				<tr>
					<td style="width: 149px; background-color: LightGreen;">添付ファイル④</td>
					<td style="width: 900px;"><a href="#"> <%
 	out.print(bean.getFILE_TITLE4());
 %>
					</a></td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>