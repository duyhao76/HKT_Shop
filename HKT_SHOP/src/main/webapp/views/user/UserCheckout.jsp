<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<body>
    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="checkout__form">
                <h4>Billing Details</h4>
                <form action="#">
                    <div class="row">
                        <div class="col-lg-8 col-md-6">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>Fist Name<span>*</span></p>
                                        <input type="text">
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>Last Name<span>*</span></p>
                                        <input type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="checkout__input">
                                <p>Address<span>*</span></p>
                                <input type="text" placeholder="Street Address" class="checkout__input__add">
                            </div>

                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>Phone<span>*</span></p>
                                        <input type="text">
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>Email<span>*</span></p>
                                        <input type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="checkout__input__checkbox">
                            </div>
                            <div class="checkout__input">
                                <p>Order notes<span>*</span></p>
                                <input type="text"
                                    placeholder="Notes about your order, e.g. special notes for delivery.">
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6">
                            <div class="checkout__order">
						 <h4>Your Order</h4>
						<div class="checkout__order__products">Products <span>Total</span></div>
						<ul>
						    <li>Rau  <span>10.000</span></li>
						    <li>Thịt <span>50.000</span></li>
						    <li>Trứng <span>30.000</span></li>
						</ul>
						<div class="checkout__order__subtotal">Subtotal <span>90.000</span></div>
						<div class="checkout__order__total">Total <span>90.000</span></div>
						
						<!-- Form xử lý đặt hàng -->
						<form id="orderForm" onsubmit="return handleOrder()">
						    <div class="checkout__input__checkbox">
						        <button type="submit" class="site-btn">PLACE ORDER</button>
						    </div>
						</form>
						
						<!-- Thông báo sẽ hiển thị ở đây -->
						<div id="message"></div>
						
						<script>
						    // Hàm xử lý khi người dùng nhấn đặt hàng
						    function handleOrder() {
						        // Hiển thị thông báo "Đặt hàng thành công"
						        document.getElementById('message').innerText = 'Đặt hàng thành công!';
						
						        // Hiển thị thông báo trong console để kiểm tra
						        console.log("Đặt hàng thành công!");
						
						        // Chuyển hướng về trang chủ sau 3 giây
						        setTimeout(function() {
						            window.location.href = "/HKT_SHOP/user/home"; // Trang chủ (có thể thay đổi URL này)
						        }, 1000); // 3000ms = 3 giây					
						    }
						</script>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <!-- Checkout Section End -->
</body> <!-- Thêm thẻ đóng </body> -->
    