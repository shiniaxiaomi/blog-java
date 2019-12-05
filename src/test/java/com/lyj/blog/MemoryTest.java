package com.lyj.blog;

import java.util.ArrayList;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 11:03 上午
 */

//测试List中的对象还存在其他对象引用时，当list回收后，list中的对象如何被回收的问题
public class MemoryTest {

    public static Runtime rt = Runtime.getRuntime();

    public static void main(String[] args) throws InterruptedException {
        //打印刚开始时的内存占用
        getUseMemory();

        //创建对象，使其占用内存
        ArrayList<Object> list = new ArrayList<>();
        Node node = new Node();//有强引用的node对象
        Node node1 = new Node();//有强引用的node1对象
        list.add(node);//将node将入到list中，即list中有执行node对象的地址
        list.add(new Node().add(node1));//重点：创建第一个new Node()的匿名节点，并将具有强引用的node1对象添加为匿名对象的后继节点
        System.out.println("node:"+node);

        //打印创建对象后的内存占用
        getUseMemory();

        list=null;//将list设置为null，等待垃圾回收
        System.gc();//请求垃圾回收
        Thread.sleep(1000);//等待垃圾回收

        //打印垃圾收集后的内存占用
        getUseMemory();

        /**
         * 结果：
         *      ArrayList对象会被垃圾回收，因为list被置为null，即ArrayList对象没有引用指向它，所以被回收。
         *      第一个new Node()的匿名节点将会被垃圾回收，尽管是list中有指向这个匿名节点的引用，但是该匿名节点还是被回收了
         *          - 可能是gc认为，list为null，即ArrayList即将被回收，回收后，那么这个匿名节点也就没有引用指向它了，
         *            所以等到下次在收集它还不如一次性收集掉，但是node和node1不同，因为他们有引用指向，说明还有其他对象在
         *            使用他们，所以不会当ArrayList被回收，也不会回收node和node1对象，即还存在其他引用的对象
         *      node1不会被垃圾回收，因为有引用指向node1对象；
         *      node不会被垃圾回收，因为有引用指向node对象
         */
    }

    //打印当前内存的使用情况
    public static void getUseMemory(){
        long l = rt.totalMemory() - rt.freeMemory();
        System.out.println("已使用的内存:"+l/1024/1024);
    }


    //保证一个节点的内存占用为100MB
    static class Node{
        Node next;
        Byte[] bytes = new Byte[100*1024*1024/4];
        public Node add(Node node){
            this.next=node;
            return node;
        }
    }
}
