package com.lyp.test.thread11;

/**
 * 静态内部类实现单例模式
 *  @author liyupeng
 *  @since  2019-01-04 18:37
 */
public class Singletion {
	
	private static class InnerSingletion {
		private static Singletion single = new Singletion();
	}
	
	public static Singletion getInstance(){
		return InnerSingletion.single;
	}
	
}
