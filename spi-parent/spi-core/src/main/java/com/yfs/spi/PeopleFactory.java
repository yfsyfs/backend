package com.yfs.spi;

import java.util.List;

import com.yfs.People;
import com.yfs.util.MyServiceLoader;

public class PeopleFactory {

	/**
	 * 通过自定义简易SPI组件实现了People接口的本地服务并调用
	 */
	public static void invoke() {
		List<People> peoples = MyServiceLoader.load(People.class);
		for (People people : peoples) {
			people.introduce();
		}
	}

}
