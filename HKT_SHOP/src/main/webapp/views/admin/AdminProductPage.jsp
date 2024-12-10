<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body class="sb-nav-fixed">

		<% String successMessage = (String) session.getAttribute("successMessage");
    	if (successMessage != null) {
		%>
    		<script type="text/javascript">
        	alert('<%= successMessage %>');
    		</script>
		<%
        	// Xóa thông báo khỏi session sau khi hiển thị
        	session.removeAttribute("successMessage");
    	}
		%>
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="/HKT_SHOP/admin/dashboard">Food Store Admin</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Thông tin</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="#!">Đăng xuất</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <a class="nav-link" href="/HKT_SHOP/admin/dashboard">
                                <div class="sb-nav-link-icon"><i class="fas fa-home"></i></i></div>
                                Dashboard
                            </a>
                            <a class="nav-link" href="/HKT_SHOP/admin/users">
                                <div class="sb-nav-link-icon"><i class="fas fa-users"></i></div>
                                Users
                            </a>
                            <a class="nav-link" href="/HKT_SHOP/admin/categories">
                                <div class="sb-nav-link-icon"><i class="fas fa-list"></i></div>
                                Categories
                            </a>
                            <a class="nav-link" href="/HKT_SHOP/admin/products">
                                <div class="sb-nav-link-icon"><i class="fas fa-box"></i></div>
                                Products
                            </a>
                        </div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Products</h1>

                        <div class="d-flex justify-content-end mb-2">
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addProductModal">Add new product</button>
                        </div> 
                        
                        <div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                              <div class="modal-content">
                                <div class="modal-header">
                                  <h5 class="modal-title" id="addProductModalLabel">Add new product</h5>
                                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <form id="addProductForm" action="${pageContext.request.contextPath}/admin/product/add" method="POST" enctype="multipart/form-data">
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label for="productname" class="form-label">Product name</label>
                                            <input type="text" class="form-control" id="productName" name="productName" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="category" class="form-label">Category</label>
                                            <select class="form-control" id="categoryId" name="categoryId" required>
                                                <option value="" disabled selected>Categories</option>
                                                <c:forEach items="${listCategory}" var="category">
                                        			<option value="${category.categoryId}">${category.categoryName}</option>
                                        		</c:forEach>
                                              </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="unitPrice" class="form-label">Price</label>
                                            <input type="number" min="1" class="form-control" id="unitPrice" name="unitPrice" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="quantityInStock" class="form-label">Quantity</label>
                                            <input type="number" min="1" class="form-control" id="quantityInStock" name="quantityInStock" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="createdDate" class="form-label">Created date</label>
                                            <input type="date" class="form-control" id="createdDate" name="createdDate" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="expiryDate" class="form-label">Expiry date</label>
                                            <input type="date" class="form-control" id="expiryDate" name="expiryDate" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="storageConditions" class="form-label">Storage conditions</label>
                                            <input type="text" class="form-control" id="storageConditions" name="storageConditions" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="imgPath" class="form-label">Img</label>
                                            <div class="image-container">
                                                <!-- Hiển thị ảnh mặc định khi chưa có ảnh -->
                                                <img id="imagePreview" src="https://media.istockphoto.com/id/515373062/vector/food-seamless-background.jpg?s=612x612&w=0&k=20&c=hexa_lBms2zsFxEHASUeYhNu17i8JfV3TGOoDark-tk=" alt="Image Preview" class="img-fluid" style="max-height: 200px;"/>
                                            </div>
                                            <br>
                                            <input type="file" class="form-control" id="imgPathFile" name="imgPathFile" accept="image/*">
                                            <br>
                                            <label for="imageUrl" class="form-label">Hoặc nhập URL ảnh:</label>
                                            <br>
                                            <input type="url" class="form-control" id="imgPathUrl" name="imgPathUrl" placeholder="Nhập URL ảnh">
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-primary">Save</button>
                                    </div>
                                </form>
                               </div>
                            </div>
                        </div>

                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Products
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>Product name</th>
                                            <th>Category</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Created date</th>
                                            <th>Expiry date</th>
                                            <th>Storage conditions</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Product name</th>
                                            <th>Category</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Created date</th>
                                            <th>Expiry date</th>
                                            <th>Storage conditions</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <c:forEach items="${listProduct}" var="product">
                                        <tr>
                                            <td>${product.productName}</td>
                                            <td>${product.category.categoryName}</td>
                                            <td>${product.unitPrice}</td>
                                            <td>${product.quantityInStock}</td>
                                            <td>${product.createdDate}</td>
                                            <td>${product.expiryDate}</td>
                                            <td>${product.storageConditions}</td>
                                            <td>
												<c:choose>
											        <c:when test="${product.isActive == true}">
											            Đang hoạt động
											        </c:when>
											        <c:otherwise>
											            Ngưng hoạt động
											        </c:otherwise>
											    </c:choose>
                                            </td>
                                            <td>
                                            	<a href="<c:url value='/admin/productview?id=${product.productId}'/>" 
    											 	class="btn btn-success">
    												Edit
												</a>
                                            </td>
                                        </tr>
                                   		</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Web bán thực phẩm của bạn Hào, bạn Ka, bạn Thọ và bạn Long</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
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