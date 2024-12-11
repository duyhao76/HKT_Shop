package vn.HKT.controllers.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HKT.entities.CartItem;
import vn.HKT.services.ICartService;
import vn.HKT.services.impl.CartServiceImpl;

@WebServlet(urlPatterns = { "/user/cart" })
public class CartController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ICartService cartService = new CartServiceImpl(); // Khởi tạo CartService

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI(); 
        req.setCharacterEncoding("UTF-8"); 
        resp.setCharacterEncoding("UTF-8"); 

        if (url.contains("cart")) {
            long userId = 1L; 

            // Gọi dịch vụ để lấy danh sách giỏ hàng của người dùng
            List<CartItem> cartList = cartService.findByUserId(userId);

            System.out.println("Cart list size: " + cartList.size());

            // Lưu danh sách giỏ hàng vào request để chuyển tiếp đến JSP
            req.setAttribute("cartList", cartList);

            // Chuyển tiếp đến trang JSP hiển thị giỏ hàng
            req.getRequestDispatcher("/views/user/UserCart.jsp").forward(req, resp);
        }
    }
}

