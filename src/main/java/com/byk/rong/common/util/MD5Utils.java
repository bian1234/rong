package com.byk.rong.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {



	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	/**
	 *   用户输入的密码进行加密
	 */
	public static String encryption(String username,String pswd,String salt) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + salt),
				HASH_ITERATIONS).toHex();
		return newPassword;
	}



}
