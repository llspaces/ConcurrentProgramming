package com.lyp.test.thread11;

/**
 *  double check和锁定类信息 实现单例模式
 *
 *  @author liyupeng
 *  @since  2019-01-04 18:37
 */
public class DoubleCheckSingleton {

	private static DoubleCheckSingleton ds;
	
	public  static DoubleCheckSingleton getDs(){
		if(ds == null){
			try {
				//模拟初始化对象的准备时间...
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (DoubleCheckSingleton.class) {
				if(ds == null){
					ds = new DoubleCheckSingleton();
				}
			}
		}
		return ds;
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DoubleCheckSingleton.getDs().hashCode());
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DoubleCheckSingleton.getDs().hashCode());
			}
		},"t2");
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DoubleCheckSingleton.getDs().hashCode());
			}
		},"t3");
		
		t1.start();
		t2.start();
		t3.start();
	}
	
}
