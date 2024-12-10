<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>
<!DOCTYPE html>
<!-- Hero Section Begin -->
    <section class="hero">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <i class="fa fa-bars"></i>
                            <span>All Category</span>
                        </div>
                        <ul>
                            <!-- Lặp qua danh sách các danh mục từ request -->
					        <c:forEach var="category" items="${categoryList}">
					            <li><a href="${pageContext.request.contextPath }/user/category?categoryId=${category.categoryId}">${category.categoryName}</a></li>
					        </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="${pageContext.request.contextPath }/user/search" method="GET">
                                <input type="text" name="keyword" placeholder="What do you need?">
                                <button type="submit" class="site-btn">SEARCH</button>
                            </form>
                        </div>
                        <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <div class="hero__search__phone__text">
                                <h5>+0702525896</h5>
                                <span>support 24/7 time</span>
                            </div>
                        </div>
                    </div>
                    <div class="hero__item set-bg" data-setbg="${URL}ogani-master/img/hero/banner.png">
                        
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Categories Section Begin -->
    <section class="categories">
        <div class="container">
            <div class="row">
                <div class="categories__slider owl-carousel">
                <c:forEach var="category" items="${categoryList}">
                    <div class="col-lg-3">
                        <div class="categories__item set-bg">
                        	<!-- Kiểm tra nếu đường dẫn ảnh là URL (https) -->
		                    <c:if test="${category.imgPath != null && category.imgPath.startsWith('https')}">
		                        	<img src="${category.imgPath}" alt="${category.categoryName}" style="width: 100%; height: auto;" />
		                    </c:if>
		                    <!-- Kiểm tra nếu đường dẫn ảnh là đường dẫn cục bộ -->
		                    <c:if test="${category.imgPath != null && !category.imgPath.startsWith('https')}">
		                        <c:url value="/image?fname=${category.imgPath}" var="imgUrl" />
		                        <img src="${imgUrl}" alt="${category.categoryName}" style="width: 100%; height: auto;" />
		                    </c:if>
                            <h5><a href="${pageContext.request.contextPath}/user/category?categoryId=${category.categoryId}">${category.categoryName}</a></h5>
                        </div>
                    </div> 
                </c:forEach> 
                </div>
            </div>
        </div>
    </section>
    <!-- Categories Section End -->

    <!-- Featured Section Begin -->
<section class="featured spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <h2>Product</h2>
                </div>
                <div class="featured__controls">
                    <ul>
                        <li class="active" data-filter="*" onclick="${pageContext.request.contextPath }/user/home?categoryId=">All</li>
                        <c:forEach var="category" items="${categoryList}">
                            <li data-filter=".${category.categoryName}" 
                                onclick="${pageContext.request.contextPath }/user/home?categoryId=${category.categoryId}">
                                ${category.categoryName}
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row featured__filter">
            <c:forEach var="product" items="${productList}">
                <div class="col-lg-3 col-md-4 col-sm-6 mix ${product.category.categoryName}">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg">
                    <!-- Kiểm tra nếu đường dẫn ảnh là URL (https) -->
                    <c:if test="${product.imgPath != null && product.imgPath.startsWith('https')}">
                    	<a href="${pageContext.request.contextPath }/user/detail?productId=${product.productId}">
                        	<img src="${product.imgPath}" alt="${product.productName}" style="width: 100%; height: auto;" />
                        </a>
                    </c:if>
                    
                    <!-- Kiểm tra nếu đường dẫn ảnh là đường dẫn cục bộ -->
                    <c:if test="${product.imgPath != null && !product.imgPath.startsWith('https')}">
                        <c:url value="/image?fname=${product.imgPath}" var="imgUrl" />
                        <a href="${pageContext.request.contextPath }/user/detail?productId=${product.productId}">
                        <img src="${imgUrl}" alt="${product.productName}" style="width: 100%; height: auto;" />
                        </a>
                    </c:if>

                    <ul class="featured__item__pic__hover">
                        <li><a href="#"><i class="fa fa-heart"></i></a></li>
                        <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                        <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                </div>
                        <div class="featured__item__text">
                            <h6><a href="#">${product.productName}</a></h6>
                            <h5>${product.unitPrice} VNĐ</h5>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
<!-- Featured Section End -->


    <!-- Banner Begin -->
    <div class="banner">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="banner__pic">
                        <img src="${URL}ogani-master/img/banner/banner-1.jpg" alt="">
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="banner__pic">
                        <img src="${URL}ogani-master/img/banner/banner-2.jpg" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br>
    <!-- Banner End -->


   