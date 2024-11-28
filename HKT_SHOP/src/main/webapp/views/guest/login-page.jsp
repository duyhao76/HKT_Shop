<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>
<!DOCTYPE html>
<html lang="vi">
  <body>
    <!-- Start Preloader -->
    <div id="preload-block">
      <div class="square-block"></div>
    </div>
    <!-- Preloader End -->

    <div class="container-fluid">
      <div class="row">
        <div
          class="authfy-container col-xs-12 col-sm-10 col-md-8 col-lg-6 col-sm-offset-1 col-md-offset-2 col-lg-offset-3"
        >
          <div class="col-sm-5 authfy-panel-left">
            <div class="brand-col">
              <div class="headline">
                <!-- brand-logo start -->
                <div class="brand-logo">
                  <a href="login-page.jsp"
                    ><img
                      src="${URL}login-page-devforum/images/No-bg logo.png"
                      width="150"
                      alt="brand-logo"
                  /></a>
                </div>
                <!-- ./brand-logo -->
                <p>
                  Đăng nhập bằng phương tiện truyền thông xã hội để truy cập
                  nhanh chóng
                </p>

                <!-- social login buttons start -->
                <div class="row social-buttons">
                  <div class="col-xs-4 col-sm-4 col-md-12">
                    <a
                      href="https://www.facebook.com/v7.0/dialog/oauth?
								        scope=email,public_profile&
								        access_type=offline&
								        include_granted_scopes=true&
								        response_type=code&
								        redirect_uri=https://localhost:8443/Project_Sach/authentication/auth/facebook&
								        client_id=859828519474363"
                      class="btn btn-block btn-facebook"
                    >
                      <i class="fa fa-facebook"></i>
                      <span class="hidden-xs hidden-sm"
                        >Đăng nhập với Facebook</span
                      >
                    </a>
                  </div>
                  <div class="col-xs-4 col-sm-4 col-md-12">
                    <a
                      href="https://github.com/login/oauth/authorize?scope=user:email,public_profile&access_type=offline&include_granted_scopes=true&response_type=code&redirect_uri=https://localhost:8443/Project_Sach/authentication/auth/github&client_id=Ov23liRlJeDjCQWzTSHR"
                      class="btn btn-block btn-github"
                    >
                      <i class="fa fa-github"></i>
                      <span class="hidden-xs hidden-sm"
                        >Đăng nhập với Github</span
                      >
                    </a>
                  </div>
                  <!-- Nút Đăng nhập bằng Google -->
                  <div class="col-xs-4 col-sm-4 col-md-12">
                    <a
                      href="https://accounts.google.com/o/oauth2/v2/auth?
											scope=email%20profile&
											access_type=offline&
											include_granted_scopes=true&
											response_type=code&
											redirect_uri=https://localhost:8443/Project_Sach/authentication/auth/google&
											client_id=1013728767709-o6q514dv1kk6h8eh3asqsalkdi3tbfud.apps.googleusercontent.com"
                      class="btn btn-block btn-google"
                    >
                      <i class="fa fa-google"></i>
                      <span class="hidden-xs hidden-sm"
                        >Đăng nhập với Google</span
                      >
                    </a>
                  </div>
                </div>
                <!-- ./social-buttons -->
              </div>
            </div>
          </div>

          <div class="col-sm-7 authfy-panel-right">
            <!-- authfy-login start -->
            <div class="authfy-login">
              <!-- panel-login start -->
              <div class="authfy-panel panel-login text-center active">
                <div class="authfy-heading">
                  <h3 class="auth-title">Đăng nhập vào tài khoản của bạn</h3>
                  <p>
                    Không có tài khoản?
                    <a class="lnk-toggler" data-panel=".panel-signup" href="#"
                      >Đăng ký miễn phí!</a
                    >
                  </p>
                </div>

				<!-- Hiển thị thông báo thành công -->
					<c:if test="${not empty successMessage}">
					    <div class="alert alert-success" class="alert alert-danger"
		                      role="alert"
		                      style="font-size: 14px; padding: 6px"
		                      >
                      		${successMessage}
                     	</div>
					</c:if>

                <!-- Hiển thị thông báo lỗi nếu có -->
                <div style="height: auto; margin-bottom: 10px">
                  <c:if test="${not empty errorMessage}">
                    <div
                      class="alert alert-danger"
                      role="alert"
                      style="font-size: 14px; padding: 6px"
                    >
                      ${errorMessage}
                    </div>
                  </c:if>
                </div>
                <div class="row">
                  <div class="col-xs-12 col-sm-12">
                    <form
                      name="loginForm"
                      class="loginForm"
                      action="<c:url value='/authentication/login' />"
                      method="POST"
                    >
                      <div class="form-group">
                        <input
                          type="email"
                          class="form-control email"
                          name="email"
                          placeholder="Email address"
                          required
                        />
                      </div>
                      <div class="form-group">
                        <div class="pwdMask">
                          <input
                            type="password"
                            class="form-control password"
                            name="password"
                            placeholder="Password"
                            required
                          />
                          <span class="fa fa-eye-slash pwd-toggle"></span>
                        </div>
                      </div>
                      <!-- start remember-row -->
                      <div class="row remember-row">
                        <div class="col-xs-6 col-sm-6">
                          <label class="checkbox text-left">
                            <input
                              type="checkbox"
                              value="remember-me"
                              name="remember"
                            />
                            <span class="label-text">nhớ tôi</span>
                          </label>
                        </div>
                        <div class="col-xs-6 col-sm-6">
                          <p class="forgotPwd">
                            <a
                              class="lnk-toggler"
                              data-panel=".panel-forgot"
                              href="#"
                              >Quên mật khẩu?</a
                            >
                          </p>
                        </div>
                      </div>
                      <!-- ./remember-row -->
                      <div class="form-group">
                        <button
                          class="btn btn-lg btn-primary btn-block"
                          type="submit"
                        >
                          Đăng nhập bằng email
                        </button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
              <!-- ./panel-login -->

              <!-- panel-signup start -->
              <div class="authfy-panel panel-signup text-center">
                <div class="row">
                  <div class="col-xs-12 col-sm-12">
                    <div class="authfy-heading">
                      <h3 class="auth-title">Đăng ký miễn phí!</h3>
                    </div>
                    <form
                      name="signupForm"
                      class="signupForm"
                      action="<c:url value='/authentication/signup' />"
                      method="POST"
                    >
                      <div class="form-group">
                        <input
                          type="email"
                          class="form-control"
                          name="email"
                          placeholder="Email address"
                        />
                      </div>
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control"
                          name="fullname"
                          placeholder="Full name"
                        />
                      </div>
                      <div class="form-group">
                        <div class="pwdMask">
                          <input
                            type="password"
                            class="form-control"
                            name="password"
                            placeholder="Password"
                          />
                          <span class="fa fa-eye-slash pwd-toggle"></span>
                        </div>
                      </div>
                      <div class="form-group">
                        <p class="term-policy text-muted small">
                          Tôi đồng ý với
                          <a href="https://policies.google.com/privacy?hl=vi"
                            >chính sách bảo mật</a
                          >
                          và
                          <a href="https://policies.google.com/terms?hl=vi"
                            >điều khoản dịch vụ</a
                          >.
                        </p>
                      </div>
                      <div class="form-group">
                        <button
                          class="btn btn-lg btn-primary btn-block"
                          type="submit"
                        >
                          Đăng ký bằng email
                        </button>
                      </div>
                    </form>
                    <a class="lnk-toggler" data-panel=".panel-login" href="#"
                      >Đã có tài khoản?</a
                    >
                  </div>
                </div>
              </div>
              <!-- ./panel-signup -->

              <!-- panel-forget start -->
              <div class="authfy-panel panel-forgot">
                <div class="row">
                  <div class="col-xs-12 col-sm-12">
                    <div class="authfy-heading">
                      <h3 class="auth-title">Khôi phục mật khẩu của bạn</h3>
                      <p>
                        Điền địa chỉ email của bạn bên dưới và chúng tôi sẽ gửi
                        cho bạn một email kèm theo hướng dẫn thêm.
                      </p>
                    </div>
                    <form
                      name="forgetForm"
                      class="forgetForm"
                      action="<c:url value='/authentication/forgotpassword' />"
                      method="POST"
                    >
                    
	                    <!-- Hiển thị thông báo thành công -->
						<c:if test="${not empty successMessage}">
						    <div class="alert alert-success">${successMessage}</div>
						</c:if>
						
						<!-- Hiển thị thông báo lỗi -->
						<c:if test="${not empty errorMessage}">
						    <div class="alert alert-danger">${errorMessage}</div>
						</c:if>
                                       
                      <div class="form-group">
                        <input
                          type="email"
                          class="form-control"
                          name="email"
                          placeholder="Email address"
                        />
                      </div>
                      <div class="form-group">
                        <button
                          class="btn btn-lg btn-primary btn-block"
                          type="submit"
                        >
                          Khôi phục mật khẩu của bạn
                        </button>
                      </div>
                      <div class="form-group">
                        <a
                          class="lnk-toggler"
                          data-panel=".panel-login"
                          href="#"
                          >Đã có tài khoản?</a
                        >
                      </div>
                      <div class="form-group">
                        <a
                          class="lnk-toggler"
                          data-panel=".panel-signup"
                          href="#"
                          >Không có tài khoản?</a
                        >
                      </div>
                    </form>
                  </div>
                </div>
              </div>
              <!-- ./panel-forgot -->
            </div>
            <!-- ./authfy-login -->
          </div>
        </div>
      </div>
      <!-- ./row -->
    </div>
    <!-- ./container -->
  </body>
</html>
