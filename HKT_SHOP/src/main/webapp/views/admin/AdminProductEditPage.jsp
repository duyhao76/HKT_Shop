<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>
	<div class="container rounded bg-white mt-5 mb-5">
		<div class="row">
			<!-- Main Content -->
			<div class="col-md-12">
				<div class="p-3 py-5">
					<div class="d-flex justify-content-between align-items-center mb-3">
						<h4 class="text-right">Edit Product</h4>
					</div>
					<form action="/HKT_SHOP/admin/product/edit" method="POST"
						enctype="multipart/form-data">
						<input type="text" id="productId" name="productId"
							value="${product.productId}"
							style="visibility: hidden; position: absolute;">
						<div class="row mt-2">
							<div class="col-md-6">
								<label class="labels">Product name</label> 
								<input type="text" class="form-control" id="productName" name="productName" value="${product.productName}" placeholder="Product name">
							</div>
							<div class="col-md-6">
								<label for="category" class="form-label">Category</label> 
								<select class="form-control" id="categoryId" name="categoryId" required>
									<option value="" disabled selected>Categories</option>
									<c:forEach items="${listCategory}" var="category">
										<option value="${category.categoryId}"
											${product.category.categoryId == category.categoryId ? 'selected' : ''}>
											${category.categoryName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row mt-2">
							<div class="col-md-6">
								<label class="labels">Created date</label> <input type="date"
									class="form-control" id="createdDate" name="createdDate"
									value="${product.createdDate}" placeholder="Created date">
							</div>
							<div class="col-md-6">
								<label class="labels">Expiry date</label> <input type="date"
									class="form-control" id="expiryDate" name="expiryDate"
									value="${product.expiryDate}" placeholder="Expiry date">
							</div>
						</div>
						<div class="row mt-2">
							<div class="col-md-6">
								<label class="labels">Status</label> 
								<select class="form-control" id="isActive" name="isActive">
									<option value="true" ${product.isActive ? 'selected' : ''}>Đang
										hoạt động</option>
									<option value="false" ${!product.isActive ? 'selected' : ''}>Ngưng
										hoạt động</option>
								</select>
							</div>
							<div class="col-md-6">
								<label class="labels">Quantity</label> 
								<input type="number" min="1" class="form-control" id="quantityInStock" name="quantityInStock" value="${product.quantityInStock}" placeholder="Quantity">
							</div>
						</div>
						<div class="row mt-2">
							<div class="col-md-6">
								<c:if test="${product.imgPath != null && product.imgPath.startsWith('https')}">
									<img id="imagePreview" src="${product.imgPath}" alt="${product.productName}" style="width: 300px; height: 300px;" />
								</c:if>

								<!-- Kiểm tra nếu đường dẫn ảnh là đường dẫn cục bộ -->
								<c:if test="${product.imgPath != null && !product.imgPath.startsWith('https')}">
									<c:url value="/image?fname=${product.imgPath}" var="imgUrl" />
									<img id="imagePreview" src="${imgUrl}" alt="${product.productName}" style="width: 300px; height: 300px;" />
								</c:if>
								<input type="file" class="form-control" id="imgPathFile" name="imgPathFile" accept="image/*"> 
								<br> 
								<label for="imageUrl" class="form-label">Hoặc nhập URL ảnh:</label> 
								<br>
								<input type="text" class="form-control" id="imgPathUrl" name="imgPathUrl" placeholder="Link img:">
							</div>
							<div class="col-md-6">
								<label class="labels">Price</label> 
								<input type="number" min="1" class="form-control" id="unitPrice" name="unitPrice" value="${product.unitPrice}" placeholder="Price">
							</div>
						</div>
						<div class="row mt-4">
							<div class="col-md-12">
								<div class="col-md-6">
								<label class="labels">Storage conditions</label> 
								<input type="text" class="form-control" id="storageConditions" name="storageConditions" value="${product.storageConditions}" placeholder="Storage conditions">
							</div>
							</div>
						</div>
						<div class="mt-5 text-center">
							<button type="submit" class="btn btn-primary profile-button">Save</button>
							<a href="/HKT_SHOP/admin/products" class="btn btn-secondary profile-button ml-3">Exit</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/AdminPage/js/scripts.js?v=1.0.1"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/AdminPage/js/database-simple-demo.js?v=1.0.1"></script>
        <script>
	    // Hiển thị xem trước hình ảnh mới nếu người dùng chọn file
	    // Hiển thị xem trước hình ảnh mới nếu người dùng chọn file
	    document.getElementById('imgPathFile').addEventListener('change', function(event) {
	        const file = event.target.files[0];
	        const preview = document.getElementById('imagePreview');
	        if (file) {
	            const reader = new FileReader();
	            reader.onload = function(e) {
	                preview.src = e.target.result;
	            };
	            reader.readAsDataURL(file);
	        } else {
	            // Nếu không có ảnh, có thể xóa preview
	            preview.src = '';
	        }
	    });
	    
	    // Hiển thị xem trước hình ảnh từ URL nếu người dùng nhập
	    document.getElementById('imgPathUrl').addEventListener('input', function(event) {
	        const url = event.target.value;
	        const preview = document.getElementById('imagePreview');
	        if (url) {
	            preview.src = url;
	        }
	    });
	</script>
</body>
</html>