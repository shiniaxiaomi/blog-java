<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">

    <#--blog-->
    <link rel="stylesheet" href="/css/blog.css">
    <!--markdown-->
    <link href="editor.md/css/editormd.preview.min.css" rel="stylesheet">

    <style>
        <#--设置toc目录样式-->
        #navbar-example li{
            display: block;
        }
        #navbar-example ul{
            padding-left: 20px;
        }
        a.nav-link.active {
            background-color: #d0e3f5;
        }
        /*小屏幕*/
        @media (max-width: 768px) {
            .whiteBlock{
                padding: 0px;
            }
        }

    </style>

    <title>博客</title>
</head>
<body style="padding-top:0">

<#--引入顶部导航栏,并将固定在顶部设置为false-->
<#include "ftlTemplate/headerTemplate.ftl">
<@header fixedTop=false />

<#--主体-->
<div class="container-xl">
    <div class="row">

        <#--左侧-->
        <div class="col-lg-9">
            <div class="whiteBlock">
                <!--面包屑导航-->
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/">首页</a></li>
                        <li class="breadcrumb-item active" aria-current="page">${blog.name!}</li>
                    </ol>
                </nav>

                <#--博客相关信息-->
                <div class="center">
                    <h1>${blog.name!}</h1>

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
                </div>

                <!--内容主体-->
                <div data-spy="scroll" data-target="#navbar-example3" data-offset="0">
                    ${blog.mdHtml!}
                </div>

            </div>
        </div>

        <#--右侧-->
        <div class="col-lg-3 d-none d-lg-block" style="max-width: 300px;">

            <#--介绍信息-->
            <#include "ftlTemplate/introduceTemplate.ftl" >
            <@introduce/>

            <#--toc目录-->
            <div class="whiteBlock sticky-top scrollspy-example vh-100 overflow-auto">
                <a class="nav-link" href="#">回到顶部</a>
                ${blog.tocHtml!}
            </div>
        </div>
    </div>
</div>



<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/js/jquery.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<!-- 弹窗 -->
<script src="/js/pop.js"></script>

<#--引入登入模板(该模板需要刚在jquery加载之后的body标签内)-->
<#include  "ftlTemplate/loginTemplate.ftl">

<script>
    $(function () {

        //开启提示工具
        $('[data-toggle="tooltip"]').tooltip();

        //开启滚动监听
        $("body").scrollspy({ target: '#navbar-example' })

    })
</script>

</body>
</html>