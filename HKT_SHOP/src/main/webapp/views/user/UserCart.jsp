<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/taglib.jsp" %>
<c:url value="" var="URL"></c:url>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Shopping Cart</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <!-- Hero Section Begin -->
    <section class="hero hero-normal">
        <div class="container">
            <div class="row">
                <div class="col-lg-3"></div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->
    <!-- Shoping Cart Section Begin -->
    <section class="shoping-cart spad">
        <div class="container">
            <c:if test="${not empty success}">
                <div class="alert alert-success">${success}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th class="shoping__product">Products</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="subtotal" value="0" />
                                <c:forEach var="cartItem" items="${cartList}">
                                    <tr>
                                        <td class="shoping__cart__item">
                                            <img src="img/cart/${cartItem.product.imgPath}" alt="">
                                            <h5>${cartItem.product.productName}</h5>
                                        </td>
                                        <td class="shoping__cart__price">${cartItem.product.unitPrice}</td>
                                        <td class="shoping__cart__quantity">
                                            <div class="quantity">
                                                <div class="pro-qty">
                                                    <input type="text" value="${cartItem.quantity}">
                                                </div>
                                            </div>
                                        </td>
                                        <td class="shoping__cart__total">
                                            <c:set var="itemTotal" value="${cartItem.product.unitPrice * cartItem.quantity}" />
                                            ${itemTotal}
                                            <c:set var="subtotal" value="${subtotal + itemTotal}" />
                                        </td>
                                        <td class="shoping__cart__item__close">
                                            <span class="icon_close"></span>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
                        <a href="#" class="primary-btn cart-btn cart-btn-right" id="updateCartButton">
                            <span class="icon_loading"></span> Update Cart
                        </a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="shoping__continue">
                        <div class="shoping__discount">
                            <h5>Discount Codes</h5>
                            <form action="#">
                                <input type="text" placeholder="Enter your coupon code">
                                <button type="submit" class="site-btn">APPLY COUPON</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="shoping__checkout">
                        <h5>Cart Total</h5>
                        <ul>
                            <li>Subtotal <span>${subtotal}</span></li>
                            <li>Total <span>${subtotal}</span></li>
                        </ul>
                        <a href="${URL}/HKT_SHOP/user/checkout" class="primary-btn">PROCEED TO CHECKOUT</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shoping Cart Section End -->

    <!-- JS Script -->
    <script>
        $(document).ready(function() {
            $(".icon_close").on('click', function() {
                $(this).closest('tr').remove();
            });

            $("#updateCartButton").on('click', function() {
                alert("Cart updated!");
            });
        });
    </script>
</body>
</html>