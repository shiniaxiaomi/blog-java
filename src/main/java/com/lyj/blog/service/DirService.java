package com.lyj.blog.service;

import com.lyj.blog.file.DirOrFile;
import com.sun.jmx.remote.internal.ArrayQueue;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 根据path路径返回对应的路径下的目录结构
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/11 10:01 上午
 */

@Service
public class DirService {


    //获取对应path的目录
    public List<DirOrFile> getDir(String path){
        DirOrFile dirOrFile = DirOrFile.Instance.getChild().get(0);
        if(path==null || "/".equals(path)){
            return dirOrFile.getChild();
        }else{
            Queue<DirOrFile> queue=new ArrayDeque();
            queue.add(dirOrFile);
            while(!queue.isEmpty()){
                DirOrFile poll = queue.poll();
                if(poll.getChild()==null) continue;
                for(int i=0;i<poll.getChild().size();i++){
                    DirOrFile file = poll.getChild().get(i);//拿出孩子节点
                    //如果孩子节点的名字匹配，则返回他的孩子节点
                    if(file.getName().equals(path)){
                        return file.getChild();
                    }else {//如果不匹配，则入队，等待遍历其孩子节点
                        queue.add(file);
                    }
                }
            }

            return new ArrayList<>();//如果没找到，则返回[]
        }
    }

    //获取SEO数据
    public String getSEOData(){
        StringBuilder sb = new StringBuilder();
        DirOrFile dirOrFile = DirOrFile.Instance.getChild().get(0);
        _getSEOData(dirOrFile,sb);
        return sb.toString();
    }

    private void _getSEOData(DirOrFile dirOrFile,StringBuilder sb){
        //如果没有孩子，直接保存
        if(dirOrFile.getChild()==null){
            buildBlogLink(dirOrFile,sb);
            return;
        }

        //遍历并保存孩子节点
        List<DirOrFile> childs = dirOrFile.getChild();
        for(int i=0;i<childs.size();i++){
            _getSEOData(childs.get(i),sb);
        }

    }

    private void buildBlogLink(DirOrFile dirOrFile,StringBuilder sb){
        sb.append("<a href='/blog");
        sb.append(dirOrFile.getUrl());
        sb.append("'>");
        sb.append(dirOrFile.getName());
        sb.append("</a><br>");
    }


}
