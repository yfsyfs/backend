package com.yfs.go;

import java.io.File;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Hashtable<String, String> env = new Hashtable<>();
		//指明初始化的factory是我们下载的jar包中的RefFSContextFactory
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		//指明Context的初始URL，这里我们的是C盘
		env.put(Context.PROVIDER_URL, "file:///c:/");

		Context ctx = new InitialContext(env);

		//在C盘下创建要给文件夹名为JesminDir
		ctx.createSubcontext("JesminDir");

		//在C盘下定位myfile文件,如果没有的话, 就会抛异常
		File f = (File) ctx.lookup("myfile");
		System.out.println(f);

		//列出当前context下的所有元素的名称和类型(包括文件夹和文件)
		NamingEnumeration list = ctx.list("/");
		while (list.hasMore()) {
			NameClassPair nc = (NameClassPair) list.next();
			System.out.println(nc);
		}
	}

}
/*
c:\myfile
$Recycle.Bin: javax.naming.Context
.rnd: java.io.File
bootTel.dat: java.io.File
Documents and Settings: javax.naming.Context
DRMsoft: javax.naming.Context
hiberfil.sys: java.io.File
Intel: javax.naming.Context
JesminDir: javax.naming.Context
myfile: java.io.File
pagefile.sys: java.io.File
Program Files: javax.naming.Context
Program Files (x86): javax.naming.Context
ProgramData: javax.naming.Context
Recovery: javax.naming.Context
swapfile.sys: java.io.File
System Volume Information: javax.naming.Context
TEMP: javax.naming.Context
Users: javax.naming.Context
Windows: javax.naming.Context
*/
