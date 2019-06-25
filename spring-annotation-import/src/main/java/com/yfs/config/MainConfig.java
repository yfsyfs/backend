package com.yfs.config;

import org.springframework.context.annotation.Import;

import com.yfs.bean.Color;
import com.yfs.bean.Human;

@Import({ Color.class, Human.class }) // bean的默认的id是类的全限定名
public class MainConfig {

}