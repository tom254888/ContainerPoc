<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1 style="text-align: center;">メニュー画面</h1>
	<hr />
	<form method=post action="Menu">
		<table style="width: 700px;">
			<tbody>
				<tr style="height: 258.933px;">
					<td style="width: 176.333px; height: 258.933px;">
						<p>情報共有同意状況</p>
						<table width="166" height="215">
							<tbody>
								<tr>
									<td style="width: 156px;">
										<p>
											<input type="submit" name="action" value="一覧表示">
										</p>
										<p>
											<input type="submit" name="action" value="発信月別">
										</p>
										<p>
											<input type="submit" name="action" value="発信部別">
										</p>
										<p>
											<input type="submit" name="action" value="作成中">
										</p>
										<p>
											<input type="submit" name="action" value="検索">
										</p>
										<p></p>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
					<td style="width: 408.667px; height: 258.933px;">
						<p>掲示内容一覧</p>
						<p>＜結果＞</p>
						<p></p>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<p></p>
</body>
</html>