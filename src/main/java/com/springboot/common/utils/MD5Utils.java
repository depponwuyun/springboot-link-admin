package com.springboot.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @功能描述：该类的功能是对密码进行MD5算法摘要
 * @作者：
 * @创建日间：2012-1-11
 */
public class MD5Utils {

	private static final String ALGORITHM = "MD5";
	public static final int LENGTH = 16;
	public static final int CHAR_LENGTH = 32;
	public static final int FOUR_BIT = 4;

	/**
	 * 该方法将指定的字符串用MD5算法加密后返回。
	 * 
	 * @param str
	 * @return
	 */
	/**
	 * public static String getMD5Encoding(String str){ byte[] input =
	 * str.getBytes(); String output = null; //声明16进制字母 char[]
	 * hexChar={'0','1','2'
	 * ,'3','4','5','6','7','8','9','a','b','c','d','e','f'}; try { //MD5摘要算法
	 * MessageDigest md = MessageDigest.getInstance("MD5"); md.update(input);
	 * //MD5算法的结果是128位一个整数,在这里javaAPI已经把结果转换成字节数组了 byte[] temp = md.digest();
	 * char[] ch = new char[32]; byte b = 0; for (int i = 0; i < LENGTH; i++) {
	 * b = temp[i]; //取每一个字节的低四位换成16进制字母 ch[2*i] = hexChar[b>>>4&0xf];
	 * //取每一个字节的高四位换成16进制字母 ch[2*i+1] = hexChar[b&0xf]; } output = new
	 * String(ch); } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
	 * return output; }
	 **/
	public static String convertToChar(String str) {
		byte[] input = str.getBytes();
		String output = null;
		// 声明16进制字母
		char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			// MD5摘要算法
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input);
			// MD5算法的结果是128位一个整数,在这里javaAPI已经把结果转换成字节数组了
			byte[] temp = md.digest();
			char[] ch = new char[CHAR_LENGTH];
			byte b = 0;
			for (int i = 0; i < LENGTH; i++) {
				b = temp[i];
				// 取每一个字节的低四位换成16进制字母
				ch[2 * i] = hexChar[b >>> FOUR_BIT & 0xf];
				// 取每一个字节的高四位换成16进制字母
				ch[2 * i + 1] = hexChar[b & 0xf];
			}
			output = new String(ch);
			// output = output.substring(0, output.length()/2);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * 
	 * @创建人： 欧阳明睿
	 * @创建时间： 2011-10-19
	 * @功能描述：该方法将指定的字符串用MD5算法加密后返回
	 * @修改记录：
	 * @是否废弃：否
	 * @param s
	 *            待加密字符串
	 * @return 加密后字符串
	 */
	public static String getMD5Encoding(String s) {
		if (StringUtils.isBlank(s)) {
			return "";
		}
		byte[] input = s.getBytes();
		String output = null;
		// 声明16进制字母
		char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			// 获得一个MD5摘要算法的对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input);
			/*
			 * MD5算法的结果是128位一个整数,在这里javaAPI已经把结果转换成字节数组了
			 */
			byte[] tmp = md.digest();// 获得MD5的摘要结果
			char[] str = new char[CHAR_LENGTH];
			byte b = 0;
			for (int i = 0; i < LENGTH; i++) {
				b = tmp[i];
				str[2 * i] = hexChar[b >>> FOUR_BIT & 0xf];// 取每一个字节的低四位换成16进制字母
				str[2 * i + 1] = hexChar[b & 0xf];// 取每一个字节的高四位换成16进制字母
			}
			output = new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return output;
	}

	/**
	 * 
	 * encrypt md5加密+base64编码<br/>
	 * <br/>
	 * <b>创建人：</b>wanghui<br/>
	 * <b>修改人：</b>wanghui<br/>
	 * 
	 * @param param
	 * @return String
	 * @throws UnsupportedEncodingException
	 * @date 2011-9-8
	 * @exception
	 * @since 1.0.0
	 */
	public static String getMD5AndBase64(String param) {
		MessageDigest digest = null;
		String result = null;
		if (param == null) {
			return null;
		}
		try {
			digest = MessageDigest.getInstance(ALGORITHM);
			result = Base_64.encode(digest.digest(param.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;

	}

}
