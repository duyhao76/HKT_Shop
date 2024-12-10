<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>
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
            <a class="navbar-brand ps-3" href="AdminDashBoardPage.html">Food Store Admin</a>
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
                            <a class="nav-link" href="AdminProductPage.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-box"></i></div>
                                Products
                            </a>
                            <a class="nav-link" href="AdminInventoryPage.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-warehouse"></i></div>
                                Inventory
                            </a>
                        </div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Dashboard</h1>
                        
                        <div class="cards-grid">
                            <div class="card">
                                <div class="card-header">
                                    <div class="card-title">Total Users</div>
                                    <div class="card-icon">
                                        <i class="fas fa-users"></i>
                                    </div>
                                </div>
                                <h3>1,234</h3>
                            </div>
                            <div class="card">
                                <div class="card-header">
                                    <div class="card-title">Total Products</div>
                                    <div class="card-icon">
                                        <i class="fas fa-box"></i>
                                    </div>
                                </div>
                                <h3>567</h3>
                            </div>
                            <div class="card">
                                <div class="card-header">
                                    <div class="card-title">Total Orders</div>
                                    <div class="card-icon">
                                        <i class="fas fa-shopping-cart"></i>
                                    </div>
                                </div>
                                <h3>89</h3>
                            </div>
                            <div class="card">
                                <div class="card-header">
                                    <div class="card-title">Revenue</div>
                                    <div class="card-icon">
                                        <i class="fas fa-dollar-sign"></i>
                                    </div>
                                </div>
                                <h3>$12,345</h3>
                            </div>
                        </div>
                    
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Recent Orders
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Customer</th>
                                            <th>Note</th>
                                            <th>Total</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Order ID</th>
                                            <th>Customer</th>
                                            <th>Note</th>
                                            <th>Total</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    <c:forEach items="${listOrder}" var="order">
                                        <tr>
                                            <td>${order.orderId}</td>
                                            <td>${order.username}</td>
                                            <td>${order.note}</td>
                                            <td>${order.totalAmount}</td>
                                            <td>${order.status}</td>
                                            <td>
                                            	<a href="<c:url value='/admin/orders?id=${order.orderId}'/>" 
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
    </body>
</html>
