package vn.HKT.utils;

import java.util.regex.Pattern;

public class Constant {
	public static final String SESSION_USERNAME = "email";
	public static final String COOKIE_REMEMBER = "rememberedEmail";

	// Folder của Ka
	public static final String DIR = "C:\\Sach";
	// Folder của Thọ
	// public static final String DIR = "E:\\IT_Project\\Sach";

	// Matching email hợp lệ
	public static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

	// Matching email phân quyền cho user
	public static final String STUDENT_EMAIL_DOMAIN = "@student.hcmute.edu.vn"; // đăng ký tại khoản bằng loại email này
																				// sẽ gán role là admin

	// Matching các cú pháp tấn công lỗ hỗng bảo mật web
	// Regex phát hiện các chuỗi độc hại
	public static final Pattern SQL_INJECTION_PATTERN = Pattern
			.compile("(['\";]|(?i)(select|insert|update|delete|drop|truncate|union|exec|sp_executesql|xp_cmdshell))");
	public static final Pattern SSTI_PATTERN = Pattern.compile("(?i)(\\{\\{.*?\\}\\}|\\$\\{.*?\\}|#\\{.*?\\})");
	public static final Pattern XSS_PATTERN = Pattern
			.compile("(?i)(<script.*?>.*?</script>|javascript:|onerror=|<.*?on\\w+=)");
	public static final Pattern PATH_TRAVERSAL_PATTERN = Pattern.compile("(\\.\\.\\/|\\.\\.\\\\)");
}