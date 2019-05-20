<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="ISO-8859-1">
<title>Users</title>
<%@ include file="../common_layout.jsp"%>
<!-- Datatables -->
<link href="../css/dataTables.bootstrap.min.css" rel="stylesheet">
</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<!-- left navigation -->
			<%@ include file="../leftmenu.jsp"%>
			<!-- /left navigation -->

			<!-- top navigation -->
			<%@ include file="../topmenu.jsp"%>
			<!-- /top navigation -->
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2 style="margin-right: 10px;">${role}s</h2>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<table id="datatable"
										class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>Name</th>
												<th>Email</th>
												<th>Contact Number</th>
												<th>City</th>
												<th>State</th>
												<th>Employer</th>
																								
												<!--  <th>Role</th> -->
											</tr>
										</thead>

										

									</table>
								</div>
							</div>
						</div>

					</div>



				</div>
			</div>
			<!-- /page content -->

			<!-- footer content -->
			<%@ include file="../footer.jsp"%>
			<!-- /footer content -->

			<script src="../js/jquery.dataTables.min.js"></script>
			<script src="../js/dataTables.bootstrap.min.js"></script>
		</div>
	</div>
	
</body>
</html>