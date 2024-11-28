<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/commons/taglib.jsp"%>
	<c:url value="/" var="URL"></c:url>
	
<!DOCTYPE html>
<html lang="vi">

<head>
    <!-- Basic Page Needs
  ================================================== -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Mobile Specific Metas
  ================================================== -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- For Search Engine Meta Data  -->
    <meta name="description" content="Login Form design by DevForum">
    <meta name="keywords" content="Login Form">
    <meta name="author" content="devforum.info">


	<title>Login Page - TomeKeeper</title>

    <!-- Favicon -->
    <link rel="shortcut icon" type="image/icon" href="${URL}login-page-devforum/images/android-icon-36x36.png">
    <link rel="icon" type="image/png" sizes="32x32" href="${URL}login-page-devforum/images/android-icon-36x36.png">
    <link rel="icon" type="image/png" sizes="48x48" href="${URL}login-page-devforum/images/android-icon-48x48.png">
    <link rel="icon" type="image/png" sizes="72x72" href="${URL}login-page-devforum/images/android-icon-72x72.png">

    <!-- Main structure css file -->
    <link rel="stylesheet" href="${URL}login-page-devforum/css/login-style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />
	
	 <!-- Thêm thư viện Google Sign-In -->
    <script src="https://accounts.google.com/gsi/client" async="true" defer></script>
    <script src="${URL}login-page-devforum/js/loginGoogle.js"></script>
</head>

	<!--Nội dung chính-->
	<sitemesh:write property="body" />
    <!--Kết thúc nội dung chính-->
    
</html>