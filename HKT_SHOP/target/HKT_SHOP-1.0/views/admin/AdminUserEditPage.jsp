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
                        <h4 class="text-right">Edit User</h4>
                    </div>
                    <form action="/HKT_SHOP/admin/user/edit" method="POST">
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <label class="labels">User ID</label>
                                <input type="text" class="form-control" id="userId" name="userId" value = "${user.userId}" readonly placeholder="User ID">
                            </div>
                            <div class="col-md-6">
                                <label class="labels">User name</label>
                                <input type="text" class="form-control" id="username" name="username" value = "${user.username}" readonly placeholder="User name">
                            </div>
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <label class="labels">Email</label>
                                <input type="text" class="form-control" id="email" name="email" value = "${user.email}" readonly placeholder="Email">
                            </div>
                            <div class="col-md-6">
                                <label class="labels">Created date</label>
                                <input type="date" class="form-control" id="createdDate" name="createdDate" value = "${user.createdDate}" readonly placeholder="Created date">
                            </div>
                        </div>
                        <div class="row mt-4">
                            <div class="col-md-12">
                                <label class="labels">Role</label>
                                <select class="form-control" name="role" id="role">
    								<option value="" disabled>Choose role</option>
				    				<option value="1" ${user.role.roleName == 'user' ? 'selected' : ''}>User</option>
								    <option value="2" ${user.role.roleName == 'admin' ? 'selected' : ''}>Admin</option>
								</select>
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