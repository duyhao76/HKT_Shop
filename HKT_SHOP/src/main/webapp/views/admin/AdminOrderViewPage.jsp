<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<div class="container rounded bg-white mt-5 mb-5">
        <div class="row">
            <!-- Main Content -->
            <div class="col-md-12">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right">Edit Order</h4>
                    </div>
                    <form action="/HKT_SHOP/admin/order/edit" method="POST">
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <label class="labels">Order ID</label>
                                <input type="text" class="form-control" id="orderId" name="orderId" value = "${order.orderId}" readonly placeholder="Order ID">
                            </div>
                            <div class="col-md-6">
                                <label class="labels">Customer</label>
                                <input type="text" class="form-control" id="customer" name="customer" value = "${order.username}" readonly placeholder="Customer">
                            </div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <label class="labels">Payment Method</label>
                                <select class="form-control" name="paymentMethod" id="paymentMethod">
                                    <option value="" disabled selected>Choose payment method</option>
                                    <option value="1">Cash</option>
                                    <option value="2">ATM</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label class="labels">Total price</label>
                                <input type="text" class="form-control" id="totalAlmount" name="totalAlmount" value = "${order.totalAmount}" readonly placeholder="Total price">
                            </div>
                        </div>
                        <div class="row mt-4">
                            <div class="col-md-12">
                                <h5>Order Details</h5>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Product Name</th>
                                            <th>Quantity</th>
                                            <th>Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${listOrderDetail}" var="orderDetail">
                                        <tr>
                                            <td>${orderDetail.productname}</td>
                                            <td>${orderDetail.quantity}</td>
                                            <td>${orderDetail.unitPrice}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="mt-5 text-center">
                            <button type="submit" class="btn btn-primary profile-button">Save</button>
                            <a href="dashboard.jsp" class="btn btn-secondary profile-button ml-3">Exit</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>