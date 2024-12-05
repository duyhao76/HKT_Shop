package vn.HKT.controllers.AuthServlet;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.HKT.entities.Users;
import vn.HKT.services.IUserService;
import vn.HKT.services.Impl.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Collections;

@WebServlet("/authentication/auth/google")
public class GoogleAuthServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Dotenv dotenv = Dotenv.configure()
    	    .directory("/")
    	    .filename("Client_Secret.env")
    	    .load();
    private static final String CLIENT_ID = dotenv.get("GOOGLE_CLIENT_ID");
    private static final String CLIENT_SECRET = dotenv.get("GOOGLE_CLIENT_SECRET");
    private static final String REDIRECT_URI = "https://localhost:8443/HKT_SHOP/authentication/auth/google";

    private final IUserService userService = new UserServiceImpl();
    
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        
        System.out.println("CLIENT_ID: " + dotenv.get("GOOGLE_CLIENT_ID"));
        System.out.println("CLIENT_SECRET: " + dotenv.get("GOOGLE_CLIENT_SECRET"));


        if (code == null || code.isEmpty()) {
            resp.getWriter().write("Authorization code not found.");
            return;
        }

        String tokenEndpoint = "https://oauth2.googleapis.com/token";
        URI uri = URI.create(tokenEndpoint);
        URL url = uri.toURL();

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            // Gửi yêu cầu trao đổi mã xác thực
            String params = String.format(
                "code=%s&client_id=%s&client_secret=%s&redirect_uri=%s&grant_type=authorization_code",
                code, CLIENT_ID, CLIENT_SECRET, REDIRECT_URI
            );

            try (OutputStream os = conn.getOutputStream()) {
                os.write(params.getBytes());
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    StringBuilder content = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }

                    // Xử lý ID token
                    String idTokenString = parseIdTokenFromResponse(content.toString());
                    handleGoogleUser(idTokenString, req, resp);
                }
            } else {
                resp.getWriter().write("Error exchanging code for token. Response code: " + responseCode);
            }

        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            resp.getWriter().write("Error processing Google authentication: " + e.getMessage());
        }
    }

    private String parseIdTokenFromResponse(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        return jsonObject.has("id_token") ? jsonObject.get("id_token").getAsString() : null;
    }

    private void handleGoogleUser(String idTokenString, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, GeneralSecurityException {
        if (idTokenString == null) {
            resp.getWriter().write("Invalid ID token.");
            return;
        }

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance()
        ).setAudience(Collections.singletonList(CLIENT_ID)).build();

        GoogleIdToken idToken = verifier.verify(idTokenString);

        if (idToken == null) {
            resp.getWriter().write("Invalid ID token.");
            return;
        }

        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();
        String username = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");

        if (email == null || username == null) {
            resp.getWriter().write("Incomplete user information received from Google.");
            return;
        }

        Users user = userService.FindByEmail(email);

        if (user != null) {
            if (user.getPassword() == null) {
                // Chuyển đến trang đặt mật khẩu
                req.getSession().setAttribute("email", email);
                resp.sendRedirect(req.getContextPath() + "/authentication/reset-password?action=create");
            } else {
                // Đăng nhập nếu người dùng đã tồn tại
                HttpSession session = req.getSession(true);
                session.setAttribute("username", username);
                session.setAttribute("email", email);
                session.setAttribute("pictureUrl", pictureUrl);
                session.setAttribute("account", user);

                resp.sendRedirect(req.getContextPath() + "/waiting");
            }
        } else {
            // Tạo tài khoản mới
            userService.register(email, username, null);
            req.getSession().setAttribute("email", email);
            resp.sendRedirect(req.getContextPath() + "/authentication/reset-password?action=create");
        }
        	// Debug: Hiển thị thông tin người dùng
     		System.out.print("Thông tin người dùng từ Google:\n");
     		System.out.print("Tên: " + username + "\n");
     		System.out.print("Email: " + email + "\n");
    }
}
