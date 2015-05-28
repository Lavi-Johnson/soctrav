<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin - Bootstrap Admin Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="../resource/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../resource/css/sb-admin.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../resource/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">
		<%@include file="includes/sideBarAdmin.jsp" %>
        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                        	<c:choose>
      							<c:when test="${empty processedMsg}">
      								New User
      							</c:when>
      							<c:otherwise>
      								${processedMsg}
      							</c:otherwise>
							</c:choose>
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> User
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row">
                    <div class="col-lg-6">

                        <form role="form" action="../create/user" method="post" name="loginform">

                            <div class="form-group">
                                <label>User Name</label>
                                <input class="form-control" placeholder="Enter text" name="user_name" id="user_name">
                            </div>
                            
                            <div class="form-group">
                                <label>Password</label>
                                <input class="form-control" placeholder="Enter text" name="user_pass" id="user_pass">
                            </div>
                            
                           <div class="form-group">
                                <label>First Name</label>
                                <input class="form-control" placeholder="Enter text" name="first_name" id="first_name">
                            </div>
                            
                           <div class="form-group">
                                <label>Last Name</label>
                                <input class="form-control" placeholder="Enter text" name="last_name" id="last_name">
                            </div>
                            
                           <div class="form-group">
                                <label>User Email</label>
                                <input class="form-control" placeholder="Enter text" name="user_email" id="user_email">
                            </div>
                            
                           <div class="form-group">
                                <label>Display Name</label>
                                <input class="form-control" placeholder="Enter text" name="alias" id="alias">
                            </div>
                            
                            <div class="form-group">
    							<label>Location</label></br>
                                     <select name="location_id" id="location_id">
                                     	<c:forEach var="location" items="${locations}">
                                     		<option value="${location.id}">${location.location_name}</option>
                                     	</c:forEach>
                                     </select>
							</div>
                            
                            <div class="checkbox">
    							<label>
                                     <input value="2" name="user_type_id" id="user_type_id" type="checkbox">admin
                                </label>
							</div>

                            <button type="submit" class="btn btn-default">Submit Button</button>
                            <button type="reset" class="btn btn-default">Reset Button</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>

                        </form>

                    </div>
                   
                    </div>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../resource/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../resource/js/bootstrap.min.js"></script>

</body>

</html>
