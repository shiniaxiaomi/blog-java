<#--定义首页的blog列表-->

<#macro blogList tagColor="" blog="">
    <#if blog??>
        <div>
        <#--分界线-->
        <hr>
        <#--标题-->
        <h3 class="blue"><a href="/blog?id=${blog.id!}">${blog.name!}</a></h3>

        <#--参数-->
        <p class="text-muted" style="margin-bottom: 0px">
            <#--博客参数-->
            <span style="margin-right: 10px">
                <img class="myIcon" src="/icons/calendar.svg" title="创建日期">
                ${blog.createTime?string("yyyy-MM-dd")}
            </span>
            <span style="margin-right: 10px">
                <img class="myIcon" src="/icons/arrow-repeat.svg" title="更新日期">
                ${blog.updateTime?string("yyyy-MM-dd")}
            </span>
            <span style="margin-right: 10px">
                <img class="myIcon" src="/icons/Eye.svg" title="观看人数">
                ${blog.hot!}
            </span>
            <#--编辑按钮-->
            <#if isLogin==true>
                <a type="button" style="margin-top: -2px;" class="btn btn-link btn-xs px-0"
                   href="/editDesc?blogId=${blog.id!}">编辑描述</a>
                <a type="button" style="margin-top: -2px;" class="btn btn-link btn-xs px-0"
                   href="/editBlog?blogId=${blog.id!}">编辑博客</a>
            </#if>
        </p>
        <#--标签-->
        <div style="margin-bottom: 10px">
            <#list blog.tags as tag>
                <a href="/" class="badge ${tagColor!}">${tag.name!}</a>
            </#list>
        </div>

        <#--概述,通过markdown的形式进行描述,那么就可以随意的添加图片链接了-->
        <div class="text-muted">
            ${blog.descHtml!}
        </div>
    </div>
    </#if>
</#macro>