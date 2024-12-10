<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="jakarta.tags.core"  %>
<!-- Header Section Begin -->
    <header class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__left">
                            <ul>
                                <li><i class="fa fa-envelope"></i> nguyenhiepka910@gmail.com</li>
                                <li>Free Shipping for all Order of $99</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__right">
                            <div class="header__top__right__social">
                                <a href="https://www.facebook.com/v7.0/dialog/oauth?
								        scope=email,public_profile&
								        access_type=offline&
								        include_granted_scopes=true&
								        response_type=code&
								        redirect_uri=https://localhost:8443/HKT_SHOP/authentication/auth/facebook&
								        client_id=575064428819129">
								        <i class="fa fa-facebook"></i></a>
								        
                                <a href="https://accounts.google.com/o/oauth2/v2/auth?
											scope=email%20profile&
											access_type=offline&
											include_granted_scopes=true&
											response_type=code&
											redirect_uri=https://localhost:8443/HKT_SHOP/authentication/auth/google&
											client_id=758528731083-1n9qgoodpn8k1u0rjgpu4a7ukmrv06vl.apps.googleusercontent.com">
											<i class="fa fa-google"></i></a>
                                
                            </div>
                            <div class="header__top__right__language">
                                <img src="${URL}ogani-master/img/language.png" alt="">
                                <div>English</div>
                                <span class="arrow_carrot-down"></span>
                                <ul>
                                    <li><a href="#">Spanis</a></li>
                                    <li><a href="#">English</a></li>
                                </ul>
                            </div>
                            <div class="header__top__right__auth">
                                <a href="${pageContext.request.contextPath}/authentication/login"><i class="fa fa-user"></i> Login</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="header__logo">
                        <a href="${pageContext.request.contextPath }/guest/home"><img src="${URL}login-page-devforum\images\hktshop-logo.png" alt="" style="
    height: 80px;"></a>
                    </div>
                </div>
                <div class="col-lg-6">
                    
                </div>
                <div class="col-lg-3">
                    <div class="header__cart">
                        <ul>
<!--                             <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
 -->                            <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
                        </ul>
                        <div class="header__cart__price">item: <span>$150.00</span></div>
                    </div>
                </div>
            </div>
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <!-- Header Section End -->