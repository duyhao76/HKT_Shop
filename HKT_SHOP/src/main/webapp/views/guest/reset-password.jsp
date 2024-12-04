<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="/commons/taglib.jsp" %>

<!DOCTYPE html>
<html>
  <head>
    <title>
      <c:choose><c:when test="${action == 'create'}">Tạo mật khẩu
      mới</c:when><c:otherwise>Cập nhật mật khẩu</c:otherwise></c:choose>
    </title>
  </head>
  <body>
    <h2 style="text-align: center; color: #333">
      <c:choose>
        <c:when test="${action == 'create'}">Tạo mật khẩu mới</c:when>
        <c:otherwise>Cập nhật mật khẩu</c:otherwise>
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
      <form
        action="${pageContext.request.contextPath}/authentication/reset-password"
        method="post"
        style="display: flex; flex-direction: column; gap: 15px"
      >
        <!-- Hiển thị thông báo thành công -->
        <c:if test="${not empty successMessage}">
          <div class="alert alert-success">${successMessage}</div>
        </c:if>

        <!-- Hiển thị thông báo lỗi -->
        <c:if test="${not empty errorMessage}">
          <div class="alert alert-danger">${errorMessage}</div>
        </c:if>

        <!-- Thêm trường action để xác định trường hợp sử dụng -->
        <input type="hidden" name="action" value="${action}" />
        <input type="hidden" name="token" value="${token}" />
        <input type="hidden" name="email" value="${email}" />

        <label for="password" style="font-weight: bold; color: #333"
          >Mật khẩu mới:</label
        >
        <input
          type="password"
          id="password"
          name="password"
          required
          style="padding: 10px; border-radius: 5px; border: 1px solid #ccc"
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
            <c:when test="${action == 'create'}">Tạo mật khẩu</c:when>
            <c:otherwise>Cập nhật mật khẩu</c:otherwise>
          </c:choose>
        </button>
      </form>
    </div>
  </body>
</html>
