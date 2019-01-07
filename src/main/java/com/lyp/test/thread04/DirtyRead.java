package com.lyp.test.thread04;

import java.util.concurrent.TimeUnit;

/**
 * <p>@filename DirtyRead</p>
 * <p>
 * <p>@description 脏读 </p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2019/1/3 11:45
 **/
public class DirtyRead {
    private String username = "liyupeng";
    private String password = "123456";

    public synchronized void setValue (String username, String password) {
        this.username = username;
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("setValue()..." + this.username + ":" + this.password);
    }

    public /*synchronized*/ void getValue(){
        System.out.println("getValue()..." + username + ":" + password);
    }

    public static void main(String[] args) {
        final DirtyRead dr = new DirtyRead();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                dr.setValue("lvlp", "654321");
            }
        });
        t1.start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dr.getValue();

    }

}
