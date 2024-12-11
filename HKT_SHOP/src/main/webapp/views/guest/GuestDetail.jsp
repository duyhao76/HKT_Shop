<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>
<!DOCTYPE html>
<!-- Hero Section Begin -->
    <section class="hero hero-normal">
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
					            <li><a href="${pageContext.request.contextPath }/guest/category?categoryId=${category.categoryId}">${category.categoryName}</a></li>
					        </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="${pageContext.request.contextPath }/guest/search" method="GET">
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
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="${URL}ogani-master/img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>${product.productName}</h2>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->
    <!-- Product Details Section Begin -->
	<section class="product-details spad">
	    <div class="container">
	        <div class="row">
	            <div class="col-lg-6 col-md-6">
	                <div class="product__details__pic">
	                    <div class="product__details__pic__item">
	                        <!-- Kiểm tra nếu đường dẫn ảnh là URL (https) -->
                    <c:if test="${product.imgPath != null && product.imgPath.startsWith('https')}">
                        	<img src="${product.imgPath}" alt="${product.productName}" style="width: 100%; height: auto;" />
                    </c:if>
                    
                    <!-- Kiểm tra nếu đường dẫn ảnh là đường dẫn cục bộ -->
                    <c:if test="${product.imgPath != null && !product.imgPath.startsWith('https')}">
                        <c:url value="/image?fname=${product.imgPath}" var="imgUrl" />
                        <img src="${imgUrl}" alt="${product.productName}" style="width: 100%; height: auto;" />
                    </c:if>
	                    </div>
	                </div>
	            </div>
	            <div class="col-lg-6 col-md-6">
	                <div class="product__details__text">
	                    <h3>${product.productName}</h3>
	                    <div class="product__details__rating">
	                        <i class="fa fa-star"></i>
	                        <i class="fa fa-star"></i>
	                        <i class="fa fa-star"></i>
	                        <i class="fa fa-star"></i>
	                        <i class="fa fa-star-half-o"></i>
	                        <span>(18 reviews)</span>
	                    </div>
	                    <div class="product__details__price">${product.unitPrice} VNĐ</div>
	                    <p>${product.storageConditions}</p>
	                    <div class="product__details__quantity">
	                        <div class="quantity">
	                            <div class="pro-qty">
	                                <input type="text" value="1">
	                            </div>
	                        </div>
	                    </div>
	                    <a href="${pageContext.request.contextPath}/authentication/login" class="primary-btn">ADD TO CARD</a>
	                    <a href="#" class="heart-icon"><span class="icon_heart_alt"></span></a>
	                    <ul>
	                        <li><b>Availability In Stock</b> <samp>${product.quantityInStock}</samp></li>
	                        <li><b>Production Date</b>  <samp>${product.createdDate}</samp></li>
	                        <li><b>Expiration Date</b> <samp>${product.expiryDate}</samp></li>
	                    </ul>
	                </div>
	            </div>
                
            </div>
        </div>
    </section>