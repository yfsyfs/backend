package com.yfs.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 自定义简易ServiceLoader
 * 
 * @author yfs
 *
 */
public class MyServiceLoader {

	private static final String PREFIX = "META-INF/services/";

	@SuppressWarnings("unchecked")
	public static <T> List<T> load(Class<T> clazz) {
		List<String> implClassString = readServiceFile(clazz);
		List<T> implClass = new ArrayList<>();
		for (String qualifiedName : implClassString) {
			Class<T> c = null;
			try {
				c = (Class<T>) Class.forName(qualifiedName);
				implClass.add(c.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return implClass;
	}

	/**
	 * 读取 PREFIX 下面的文件
	 * 
	 * @param clazz
	 * @return
	 */
	private static List<String> readServiceFile(Class<?> clazz) {
		// 实现类的全限定名列表
		List<String> implClass = new ArrayList<>();
		// 配置文件名称 就是接口名
		String propFilename = clazz.getCanonicalName();
		BufferedReader br = null;
		String path = null;
		try {
			// 注意, class.getResourceAsStream 只能获取单个文件, 而通过ClassLoader就可以获取全部文件,
			// 这也是ServiceLoader的做法
			Enumeration<URL> resources = clazz.getClassLoader().getResources(PREFIX + propFilename);
			while (resources.hasMoreElements()) {
				path = resources.nextElement().getPath();
				br = new BufferedReader(new FileReader(path));
				String line = null;
				// 一行一行的读取配置文件
				while ((line = br.readLine()) != null) {
					implClass.add(line);
				}
			}
		} catch (IOException e) {
			System.out.println("读取文件" + path + "失败.");
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return implClass;
	}

}
