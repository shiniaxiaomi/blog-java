package com.lyj.blog;

import sun.jvm.hotspot.utilities.Bits;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 11:03 上午
 */
public class A {

    public static Runtime rt = Runtime.getRuntime();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(rt.maxMemory()/1024/1024);

        getUseMemory();

        ArrayList<Object> list = new ArrayList<>();
        list.add(new Byte[100*1024*1024/4]);
        list.add(new Byte[100*1024*1024/4]);
        list.add(new Byte[100*1024*1024/4]);
        list.add(new Byte[100*1024*1024/4]);

//        Byte[] bytes = new Byte[100*1024*1024/4];
//        Byte[] bytes1 =new Byte[100*1024*1024/4];
//        Byte[] bytes2 =new Byte[100*1024*1024/4];
//        Byte[] bytes3 =new Byte[100*1024*1024/4];

        getUseMemory();


//        System.out.println(list.get(0));
        list=null;


        System.gc();
        Thread.sleep(1000);

        getUseMemory();

//        System.out.println(bytes);


    }

    public static void getUseMemory(){
        long l = rt.totalMemory() - rt.freeMemory();
        System.out.println("已使用的内存:"+l/1024/1024);
    }


}
