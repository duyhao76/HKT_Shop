<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="/commons/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>

<!DOCTYPE html>
<html>
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
                  <a href="authentication.jsp"
                    ><img
                      src="${URL}login-page-devforum/images/hktshop-logo.png"
                      width="200"
                      alt="brand-logo"
                  /></a>
                </div>
                <!-- ./brand-logo -->
                <p>Login using social media to get quick access</p>
                <!-- social login buttons start -->
                <div class="row social-buttons">
                  <div class="col-xs-4 col-sm-4 col-md-12">
                    <a
                      href="https://www.facebook.com/v7.0/dialog/oauth?
								        scope=email,public_profile&
								        access_type=offline&
								        include_granted_scopes=true&
								        response_type=code&
								        redirect_uri=https://localhost:8443/HKT_SHOP/authentication/auth/facebook&
								        client_id=575064428819129"
                      class="btn btn-block btn-facebook"
                    >
                      <i class="fa fa-facebook"></i>
                      <span class="hidden-xs hidden-sm"
                        >Signin with facebook</span
                      >
                    </a>
                  </div>
                  <!--
                                <div class="col-xs-4 col-sm-4 col-md-12">
                                    <a href="#" class="btn btn-block btn-twitter">
                                        <i class="fa fa-twitter"></i> <span class="hidden-xs hidden-sm">Signin with twitter</span>
                                    </a>
                                </div>
                                -->
                  <div class="col-xs-4 col-sm-4 col-md-12">
                    <a
                      href="https://accounts.google.com/o/oauth2/v2/auth?
											scope=email%20profile&
											access_type=offline&
											include_granted_scopes=true&
											response_type=code&
											redirect_uri=https://localhost:8443/HKT_SHOP/authentication/auth/google&
											client_id=758528731083-1n9qgoodpn8k1u0rjgpu4a7ukmrv06vl.apps.googleusercontent.com"
                      class="btn btn-block btn-google"
                    >
                      <i class="fa fa-google-plus"></i>
                      <span class="hidden-xs hidden-sm"
                        >Signin with google</span
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
                  <h3 class="auth-title">Login to your account</h3>
                  <p>
                    Don’t have an account?
                    <a class="lnk-toggler" data-panel=".panel-signup" href="#"
                      >Sign Up Free!</a
                    >
                  </p>
                </div>
                <div class="row">
                  <div class="col-xs-12 col-sm-12">
                    <!-- Khối chứa thông báo -->
                    <div id="notification-container">
                      <!-- Hiển thị thông báo thành công -->
                      <c:if test="${not empty successMessage}">
                        <div class="alert alert-success notification">
                          ${successMessage}
                        </div>
                      </c:if>

                      <!-- Hiển thị thông báo lỗi -->
                      <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger notification">
                          ${errorMessage}
                        </div>
                      </c:if>
                    </div>

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
                          value="${rememberedEmail != null ? rememberedEmail : ''}"
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
                            <input type="checkbox" name="remember"
                            value="remember-me" ${rememberChecked != null &&
                            rememberChecked ? "checked" : ""} >
                            <span class="label-text">Remember me</span>
                          </label>
                        </div>
                        <div class="col-xs-6 col-sm-6">
                          <p class="forgotPwd">
                            <a
                              class="lnk-toggler"
                              data-panel=".panel-forgot"
                              href="#"
                              >Forgot password?</a
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
                          Login with email
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
                      <h3 class="auth-title">Sign up for free!</h3>
                    </div>

                    <!-- Khối chứa thông báo -->
                    <div id="notification-container">
                      <!-- Hiển thị thông báo thành công -->
                      <c:if test="${not empty successMessage}">
                        <div class="alert alert-success notification">
                          ${successMessage}
                        </div>
                      </c:if>

                      <!-- Hiển thị thông báo lỗi -->
                      <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger notification">
                          ${errorMessage}
                        </div>
                      </c:if>
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
                          required
                        />
                      </div>
                      <div class="form-group">
                        <input
                          type="text"
                          class="form-control"
                          name="fullname"
                          placeholder="Full name"
                          required
                        />
                      </div>
                      <div class="form-group">
                        <div class="pwdMask">
                          <input
                            type="password"
                            class="form-control"
                            name="password"
                            placeholder="Password"
                            required
                          />
                          <span class="fa fa-eye-slash pwd-toggle"></span>
                        </div>
                      </div>
                      <div class="form-group">
                        <p class="term-policy text-muted small">
                          I agree to the
                          <a href="https://policies.google.com/privacy?hl=en"
                            >privacy policy</a
                          >
                          and
                          <a href="https://policies.google.com/terms?hl=en"
                            >terms of service</a
                          >.
                        </p>
                      </div>
                      <div class="form-group">
                        <button
                          class="btn btn-lg btn-primary btn-block"
                          type="submit"
                        >
                          Sign up with email
                        </button>
                      </div>
                    </form>
                    <a class="lnk-toggler" data-panel=".panel-login" href="#"
                      >Already have an account?</a
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
                      <h3 class="auth-title">Recover your password</h3>
                      <p>
                        Fill in your e-mail address below and we will send you
                        an email with further instructions.
                      </p>
                    </div>

                    <!-- Khối chứa thông báo -->
                    <div id="notification-container">
                      <!-- Hiển thị thông báo thành công -->
                      <c:if test="${not empty successMessage}">
                        <div class="alert alert-success notification">
                          ${successMessage}
                        </div>
                      </c:if>

                      <!-- Hiển thị thông báo lỗi -->
                      <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger notification">
                          ${errorMessage}
                        </div>
                      </c:if>
                    </div>

                    <form
                      name="forgetForm"
                      class="forgetForm"
                      action="<c:url value='/authentication/forgotpassword' />"
                      method="POST"
                    >
                      <div class="form-group">
                        <input
                          type="email"
                          class="form-control"
                          name="email"
                          placeholder="Email address"
                          required
                        />
                      </div>
                      <div class="form-group">
                        <button
                          class="btn btn-lg btn-primary btn-block"
                          type="submit"
                        >
                          Recover your password
                        </button>
                      </div>
                      <div class="form-group">
                        <a
                          class="lnk-toggler"
                          data-panel=".panel-login"
                          href="#"
                          >Already have an account?</a
                        >
                      </div>
                      <div class="form-group">
                        <a
                          class="lnk-toggler"
                          data-panel=".panel-signup"
                          href="#"
                          >Don’t have an account?</a
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

    <!-- JavaScript để tự động ẩn thông báo -->
    <script>
      document.addEventListener("DOMContentLoaded", function () {
        const notifications = document.querySelectorAll(".notification");
        notifications.forEach((notification) => {
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
