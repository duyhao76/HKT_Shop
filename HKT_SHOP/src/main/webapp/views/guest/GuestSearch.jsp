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
                        <h2>HTK SHOP</h2>
                        <div class="breadcrumb__option">
                            <a href="${pageContext.request.contextPath }/guest/home">Home</a>
                            <span>Shop</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Product Section Begin -->
    <section class="product spad">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="row">
    <c:forEach var="product" items="${productList}">
        <div class="col-lg-3 col-md-6 col-sm-6">
            <div class="product__item">
                <!-- Hình ảnh sản phẩm -->
                <div class="product__item__pic set-bg">
                    <!-- Kiểm tra nếu đường dẫn ảnh là URL (https) -->
                    <c:if test="${product.imgPath != null && product.imgPath.startsWith('https')}">
                        <a href="${pageContext.request.contextPath }/guest/detail?productId=${product.productId}">
                        	<img src="${product.imgPath}" alt="${product.productName}" style="width: 100%; height: auto;" />
                        </a>
                    </c:if>
                    
                    <!-- Kiểm tra nếu đường dẫn ảnh là đường dẫn cục bộ -->
                    <c:if test="${product.imgPath != null && !product.imgPath.startsWith('https')}">
                        <c:url value="/image?fname=${product.imgPath}" var="imgUrl" />
                        <a href="${pageContext.request.contextPath }/guest/detail?productId=${product.productId}">
                        <img src="${imgUrl}" alt="${product.productName}" style="width: 100%; height: auto;" />
                        </a>
                    </c:if>
                    <ul class="product__item__pic__hover">
                                        <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                        <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                        <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                    </ul>
                </div>
                
                <!-- Thông tin sản phẩm -->
                <div class="product__item__text">
                    <h6><a href="#">${product.productName}</a></h6>
                    <h5>${product.unitPrice} VNĐ</h5>
                </div>
            </div>
        </div>  
    </c:forEach>
</div>
                    <div class="product__pagination">
                        <a href="#">1</a>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#"><i class="fa fa-long-arrow-right"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Section End -->