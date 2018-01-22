package com.ls.util;

import org.springframework.util.StringUtils;

/**
 * 文件的缩略图及其模式字符串定义
 */
public class ThumbModel {

	private static final String THUMB_16="imageView2/2/w/16/h/16";
	private static final String THUMB_32="imageView2/2/w/32/h/32";
	private static final String THUMB_48="imageView2/2/w/48/h/48";
	private static final String THUMB_64="imageView2/2/w/64/h/64";
	private static final String THUMB_128="imageView2/2/w/128/h/128";
	private static final String THUMB_256="imageView2/2/w/256/h/256";
	private static final String THUMB_512="imageView2/2/w/512/h/512";
	private static final String THUMB_BASE="imageView2/2/w/%s/h/%s";


	public static String getThum(int size){
		return String.format(THUMB_BASE,size,size);
	}

	public static void main(String[] args) {
		String thum = getThum(100);
		System.out.println(thum);


	}
	
	

	
}


