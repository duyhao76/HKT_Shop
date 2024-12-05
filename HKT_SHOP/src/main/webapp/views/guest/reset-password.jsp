<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        <c:choose>
            <c:when test="${action == 'create'}">Create a password</c:when>
            <c:otherwise>Update password</c:otherwise>
        </c:choose>
    </title>
</head>
<body>
    <h2 style="text-align: center; color: #333;">
        <c:choose>
            <c:when test="${action == 'create'}">Create a password</c:when>
            <c:otherwise>Update password</c:otherwise>
        </c:choose>
    </h2>

    <div
        style="
            max-width: 400px;
            margin: auto;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
        "
    >
        <!-- Khối chứa thông báo -->
        <div id="notification-container">
            <!-- Hiển thị thông báo thành công -->
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success notification">${successMessage}</div>
            </c:if>

            <!-- Hiển thị thông báo lỗi -->
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger notification">${errorMessage}</div>
            </c:if>
        </div>

        <form
            action="${pageContext.request.contextPath}/authentication/reset-password"
            method="post"
            style="display: flex; flex-direction: column; gap: 15px;"
        >
            <!-- Thêm trường action để xác định trường hợp sử dụng -->
            <input type="hidden" name="action" value="${action}">
            <input type="hidden" name="token" value="${token}">
            <input type="hidden" name="email" value="${email}">

            <label for="password" style="font-weight: bold; color: #333;">Password:</label>
            <input
                type="password"
                id="password"
                name="password"
                required
                style="padding: 10px; border-radius: 5px; border: 1px solid #ccc;"
                minlength="6"
            />

            <button
                type="submit"
                style="
                    padding: 10px;
                    border-radius: 5px;
                    border: none;
                    background-color: #5cb85c;
                    color: white;
                    font-weight: bold;
                    cursor: pointer;
                "
            >
                <c:choose>
                    <c:when test="${action == 'create'}">Create a password</c:when>
                    <c:otherwise>Update password</c:otherwise>
                </c:choose>
            </button>
        </form>
    </div>

    <!-- JavaScript để tự động ẩn thông báo -->
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const notifications = document.querySelectorAll(".notification");
            notifications.forEach(notification => {
                // Ẩn thông báo sau 5 giây
                setTimeout(() => {
                    notification.style.transition = "opacity 1s";
                    notification.style.opacity = "0";
                    setTimeout(() => notification.remove(), 1000); // Xóa hẳn khỏi DOM sau 1 giây
                }, 5000);
            });
        });
    </script>
</body>
</html>
