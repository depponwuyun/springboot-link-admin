package com.springboot.common.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base_64 {
	private static final String UTF_8 = "UTF-8";

	/**
	 * 对给定的字符串进行base64解码操作
	 */
	public static String decode(byte[] inputData) {
		try {
			if (null == inputData) {
				return null;
			}
			return new String(Base64.decodeBase64(inputData), UTF_8);
		} catch (UnsupportedEncodingException e) {
		}

		return null;
	}

	/**
	 * 对给定的字符串进行base64解码操作
	 */
	public static String decode(String inputData) {
		try {
			if (null == inputData) {
				return null;
			}
			return new String(Base64.decodeBase64(inputData.getBytes(UTF_8)),
					UTF_8);
		} catch (UnsupportedEncodingException e) {
		}

		return null;
	}

	/**
	 * 对给定的字符串进行base64加密操作
	 */
	public static String encode(String inputData) {
		try {
			if (null == inputData) {
				return null;
			}
			return new String(Base64.encodeBase64(inputData.getBytes(UTF_8)),
					UTF_8);
		} catch (UnsupportedEncodingException e) {
		}

		return null;
	}

	/**
	 * 对给定的字符串进行base64加密操作
	 */
	public static String encode(byte[] inputData) {
		try {
			if (null == inputData) {
				return null;
			}
			return new String(Base64.encodeBase64(inputData), UTF_8);
		} catch (UnsupportedEncodingException e) {
		}

		return null;
	}

}
