<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    								<option value="" disabled>Choose payment method</option>
				    				<option value="Cash" ${order.paymentMethod == 'Cash' ? 'selected' : ''}>Cash</option>
								    <option value="ATM" ${order.paymentMethod == 'ATM' ? 'selected' : ''}>ATM</option>
								</select>
                            </div>
                            <div class="col-md-6">
                                <label class="labels">Total price</label>
                                <input type="text" class="form-control" id="totalAlmount" name="totalAlmount" value = "${order.totalAmount}" readonly placeholder="Total price">
                            </div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <label class="labels">Status</label>
                                <select class="form-control" name="status" id="status">
								    <option value="" disabled>Choose status</option>
								    <option value="Pending" ${order.status == 'Pending' ? 'selected' : ''}>Pending</option>
								    <option value="Complete" ${order.status == 'Complete' ? 'selected' : ''}>Complete</option>
								</select>
                            </div>
                            <div class="col-md-6">
                                <label class="labels">Created date</label>
                                <input type="date" class="form-control" id="orderDate" name="orderDate" value = "${order.orderDate}" readonly placeholder="Total price">
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
                            <a href="/HKT_SHOP/admin/dashboard" class="btn btn-secondary profile-button ml-3">Exit</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>