package com.lyj.blog.filter;

import com.lyj.blog.util.MDUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AbstractFileFilter;

import java.io.File;
import java.io.IOException;

public class FileFilter extends AbstractFileFilter {
    @Override
    public boolean accept(File file) {
        //排除目录
        if(file.isDirectory()){
            return false;
        }
        //去除隐藏文件
        if(file.getName().startsWith(".")){
            return false;
        }

        //去除不以.md结尾的文件
        if(!file.getName().endsWith(".md")){
            return false;
        }
        System.out.println(file.getName());

        try {
            String path = file.getPath();
            String s = FileUtils.readFileToString(file);
            String render = MDUtil.render("<!DOCTYPE html>" +
                    "<html>\n" +
                    "<meta charset=\"UTF-8\">\n" +
                    "<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                    "<title></title>"+
                    "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,user-scalable=0\">"+
                    "<head>\n" +
                    "<link href=\"https://cdn.bootcss.com/github-markdown-css/3.0.1/github-markdown.min.css\" rel=\"stylesheet\">"+//github markdown css
                    "<link href=\"https://cdn.bootcss.com/prism/1.17.1/themes/prism-okaidia.min.css\" rel=\"stylesheet\">" +
                    "<link href=\"https://cdn.bootcss.com/prism/1.17.1/plugins/line-numbers/prism-line-numbers.min.css\" rel=\"stylesheet\">" +
                    "<link href=\"https://cdn.bootcss.com/prism/1.17.1/plugins/toolbar/prism-toolbar.min.css\" rel=\"stylesheet\">"+
                    "<style>\n" +
                    "    .markdown-body {\n" +
                    "        box-sizing: border-box;\n" +
                    "        min-width: 200px;\n" +
                    "        max-width: 980px;\n" +
                    "        margin: 0 auto;\n" +
                    "        padding: 45px;\n" +
                    "    }\n" +
                    "code[class*=language-], pre[class*=language-]{" +
                    "font-size: 13px;" +
                    "}"+
                    " \n" +
                    "    @media (max-width: 767px) {\n" +
                    "        .markdown-body {\n" +
                    "            padding: 15px;\n" +
                    "        }\n" +
                    "    }\n" +
                    "</style> "+
                    "</head>" +
                    "<body>\n" +
                    "<div class=\"markdown-body\">"+
                    s +
                    "</div>"+
                    "<script src=\"https://cdn.bootcss.com/prism/1.17.1/components/prism-core.min.js\"></script>\n" +
                    "<script src=\"https://cdn.bootcss.com/prism/1.17.1/plugins/line-numbers/prism-line-numbers.min.js\"></script>" +
                    "<script src=\"https://cdn.bootcss.com/prism/1.17.1/plugins/autoloader/prism-autoloader.min.js\"></script>"+//自定引入对应的代码解析
                    "<script src=\"https://cdn.bootcss.com/prism/1.17.1/plugins/toolbar/prism-toolbar.min.js\"></script>"+
                    "<script src=\"https://cdn.bootcss.com/prism/1.17.1/plugins/copy-to-clipboard/prism-copy-to-clipboard.min.js\"></script>"+
                    "</body>\n" +
                    "</html>");
            FileUtils.writeStringToFile(new File("/Users/yingjie.lu/Code/blog/src/main/resources/static/html/"+path.substring(33,path.length()-3)+".html"),render );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
