package com.flab.jobgo.user.utils;

import org.mindrot.jbcrypt.BCrypt;

//BCrypt 암호화 방식은 단방향 암호화 방식 중 Salting과 Key Stretching을 적용하여 더욱 안전하게 암호화를 할 수 있다.
public class PasswordEncoder {
	
	public static String encoding(String pw) {
		return BCrypt.hashpw(pw, BCrypt.gensalt());
	}
	
	public static boolean isMatch(String pw, String hashPw) {
		return BCrypt.checkpw(pw, hashPw);
	}
}
