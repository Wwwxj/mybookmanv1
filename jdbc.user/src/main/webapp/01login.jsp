<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录</title>
<!-- 告诉浏览器不要缩放，可以适用于多种设备 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bs/css/paper/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
	.container-fluid{
	   position: absolute;
	   width: 40%;
	   left: 50%;
	   top: 50%;
	   margin-left: -258px;
	   margin-top: -101px;
	}
	</style>
</head>
<body>
	<div class="container-fluid well">
		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal" role="form" method="post" action="login">
					<div class="form-group">

						<label for="inputName" class="col-sm-2 control-label">
							用户名： </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputName" name="name"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPwd" class="col-sm-2 control-label">
							密码：</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="inputPwd" name="pwd"/>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label> <input type="checkbox" />记住密码
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
						    <button type="submit" class="btn btn-default">登录</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="bs/js/jquery.min.js"></script>
	<script type="text/javascript" src="bs/js/bootstrap.min.js"></script>
</body>
</html>