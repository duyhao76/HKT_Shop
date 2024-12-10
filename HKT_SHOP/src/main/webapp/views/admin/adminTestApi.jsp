<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>All Users</title>
</head>
<body>
    <h1>Danh sách User</h1>
    <ul id="userList"></ul>

    <script>
        // Gửi yêu cầu đến API để lấy dữ liệu
        fetch('/api-admin-users')
            .then(response => {
                // Kiểm tra mã phản hồi (status) của API
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json(); // Chuyển dữ liệu từ JSON sang đối tượng JavaScript
            })
            .then(data => {
                const userList = document.getElementById('userList');
                
                // Kiểm tra nếu data là một mảng và có phần tử
                if (Array.isArray(data) && data.length > 0) {
                    // Duyệt qua từng user và hiển thị
                    data.forEach(user => {
                        const li = document.createElement('li');
                        li.textContent = `ID: ${user.id}, Username: ${user.username}, Email: ${user.email}`;
                        userList.appendChild(li);
                    });
                } else {
                    const li = document.createElement('li');
                    li.textContent = 'Không có dữ liệu người dùng';
                    userList.appendChild(li);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                const userList = document.getElementById('userList');
                const li = document.createElement('li');
                li.textContent = 'Không thể tải dữ liệu. Vui lòng thử lại sau.';
                userList.appendChild(li);
            });
    </script>
</body>
</html>
