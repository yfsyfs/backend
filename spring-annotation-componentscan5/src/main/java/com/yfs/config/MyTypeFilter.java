package com.yfs.config;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class MyTypeFilter implements TypeFilter {

	/**
	 * 返回true表示匹配成功, false表示匹配失败
	 * metadataReader 表示读取到的当前正在扫描的类的信息
	 * metadataReaderFactory 可以获取到其他任何类的信息
	 */
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata(); // 获取当前类注解的信息
		ClassMetadata classMetadata = metadataReader.getClassMetadata(); // 获取当前正在扫描的类的信息——例如类型是啥，实现了什么接口
		Resource resource = metadataReader.getResource(); // 获取当前类资源的路径
		String className = classMetadata.getClassName(); // 获取类名
		System.out.println("---->" + className); // 输出类名 (则当前处理到了哪个类都可以获取的到)
		return className.contains("er"); // 只有id包含 er 的bean 才会加入IOC容器中去
	}

}
