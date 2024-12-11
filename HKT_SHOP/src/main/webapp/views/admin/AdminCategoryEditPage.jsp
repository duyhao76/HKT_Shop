<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container rounded bg-white mt-5 mb-5">
        <div class="row">
            <!-- Main Content -->
            <div class="col-md-12">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right">Edit Category</h4>
                    </div>
                    <form action="/HKT_SHOP/admin/category/edit" method="POST" enctype="multipart/form-data">
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <label class="labels">Category ID</label>
                                <input type="text" class="form-control" id="categoryId" name="categoryId" value = "${category.categoryId}" readonly placeholder="Category ID">
                            </div>
                            <div class="col-md-6">
                                <label class="labels">Category name</label>
                                <input type="text" class="form-control" id="categoryName" name="categoryName" value = "${category.categoryName}" placeholder="Category name">
                            </div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <label class="labels">Status</label>
							    <select class="form-control" id="isActive" name="isActive">
							        <option value="true" ${category.isActive ? 'selected' : ''}>Đang hoạt động</option>
							        <option value="false" ${!category.isActive ? 'selected' : ''}>Ngưng hoạt động</option>
							    </select>
                            </div>
                            <div class="col-md-6">
                                <c:if test="${category.imgPath != null && category.imgPath.startsWith('https')}">
                        			<img id="imagePreview" src="${category.imgPath}" alt="${category.categoryName}" style="width: 300px;height: 300px;" />
                    			</c:if>
                    
			                    <!-- Kiểm tra nếu đường dẫn ảnh là đường dẫn cục bộ -->
			                    <c:if test="${category.imgPath != null && !category.imgPath.startsWith('https')}">
			                        <c:url value="/image?fname=${category.imgPath}" var="imgUrl" />
			                        <img id="imagePreview" src="${imgUrl}" alt="${category.categoryName}" style="width: 300px;height: 300px;" />
			                    </c:if>
			                    <input type="file" class="form-control" id="imgPathFile" name="imgPathFile" accept="image/*">
			                    <br>
                                  <label for="imageUrl" class="form-label">Hoặc nhập URL ảnh:</label>
                                  <br>
			                    <input type="text" class="form-control" id="imgPathUrl" name="imgPathUrl" placeholder="Link img:">
                            </div>
                        </div>
                        <div class="row mt-4">
                            <div class="col-md-12">
                                <label class="labels">Decription</label>
                                <input type="text" class="form-control" id="decription" name="decription" value = "${category.description}" placeholder="Decription">
                            </div>
                        </div>
                        <div class="row mt-4">
                            <div class="col-md-12">
                                <h5>Order Details</h5>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Product Name</th>
                                            <th>Price</th>
                                            <th>Storage conditions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${listProduct}" var="product">
                                        <tr>
                                            <td>${product.productName}</td>
                                            <td>${product.unitPrice}</td>
                                            <td>${product.storageConditions}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="mt-5 text-center">
                            <button type="submit" class="btn btn-primary profile-button">Save</button>
                            <a href="/HKT_SHOP/admin/categories" class="btn btn-secondary profile-button ml-3">Exit</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
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