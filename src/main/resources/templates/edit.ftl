<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>写博客</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">

    <#-- editormd -->
    <link rel="stylesheet" href="/editor.md/css/editormd.min.css" />
    <#--blog-->
    <link rel="stylesheet" href="/css/blog.css">
    <#--jquery-ui-->
    <link href="/css/jquery-ui.min.css" rel="stylesheet">
    <#--tagit-->
    <link href="/css/jquery.tagit.css" rel="stylesheet" type="text/css">

    <style>
        /*tag-it*/
        .ui-front{
            z-index: 100000;
        }
        /*编辑页面下的toc样式*/
        #custom-toc-container .markdown-toc-list{
            padding-left: 20px;
        }
        #custom-toc-container .markdown-toc-list ul{
            display: flex;
            flex-wrap: wrap;
            padding-left: 20px;
            margin-bottom: 0;
            list-style: none;
            flex-direction: column!important;
        }
        #custom-toc-container .markdown-toc-list li{
            display: block;
        }
        /*小屏幕*/
        @media (max-width: 768px) {
            .whiteBlock{
                padding: 0px;
            }
            .vh-65{
                height: 100px;
            }
        }
    </style>

</head>
<body>

<#--引入顶部导航栏-->
<#include "ftlTemplate/headerTemplate.ftl">
<@header/>

<div class="row mx-2">
    <#--左侧(目录)-->
    <div class="col-md-3" style="max-width: 350px;">
        <#--toc目录-->
        <div class="whiteBlock px-0 ">

            <#--如果是编辑页面,则出现按钮组-->
            <#if editFlag ??>
                <div class="center mb-2">
                    <#--保存按钮组-->
                    <div class="btn-group btn-group-sm mx-1" role="group">
                        <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            保存
                        </button>
                        <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                            <#switch editFlag>
                                <#case 'editDesc'>
                                    <a class="dropdown-item" href="javascript:void(0);" data-toggle="tooltip" data-placement="top"
                                       <#if isLogin==true>
                                           id="saveDescBtn" title="将博客描述保存到线上服务器"
                                        <#else >
                                            title="请先登入"
                                        </#if>
                                       >保存博客描述</a>
                                    <#break>
                                <#case 'editBlog'>
                                    <div data-toggle="tooltip" data-placement="top"
                                         <#if isLogin==true>title="将博客保存到线上服务器"<#else>title="请先登入"</#if> title="">
                                        <a class="dropdown-item" href="javascript:void(0);"
                                           data-toggle="modal" <#if isLogin==true>data-target="#myModal"</#if>>保存博客</a>
                                    </div>
                                    <#break>
                                <#case 'edit'>
                                    <div data-toggle="tooltip" data-placement="top"
                                         <#if isLogin==true>title="将博客保存到线上服务器"<#else>title="请先登入"</#if> title="">
                                        <a class="dropdown-item" href="javascript:void(0);"
                                           data-toggle="modal" <#if isLogin==true>data-target="#myModal"</#if>>保存博客</a>
                                    </div>
                                    <a class="dropdown-item" href="javascript:void(0);">保存到线上草稿</a>
                                    <a class="dropdown-item" href="javascript:void(0);">保存到本地草稿</a>
                                    <#break>
                            </#switch>
                        </div>
                    </div>
                    <#--编辑按钮组-->
                    <div class="btn-group btn-group-sm mx-1" role="group">
                        <button id="btnGroupDrop2" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            编辑
                        </button>
                        <div class="dropdown-menu" aria-labelledby="btnGroupDrop2">
                            <a class="dropdown-item" href="javascript:void(0);">编辑博客</a>
                            <a class="dropdown-item" href="javascript:void(0);">编辑博客描述</a>
                            <a class="dropdown-item" href="javascript:void(0);">编辑线上草稿</a>
                            <a class="dropdown-item" href="javascript:void(0);">编辑本地草稿</a>
                        </div>
                    </div>
                    <#--其他按钮组-->
                    <div class="btn-group btn-group-sm mx-1" role="group">
                        <button id="btnGroupDrop2" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            其他
                        </button>
                        <div class="dropdown-menu" aria-labelledby="btnGroupDrop2">
                            <a class="dropdown-item" href="javascript:void(0);" id="refreshTocBtn"
                               data-toggle="tooltip" data-placement="top" title="打开右侧的预览视图后目录会自动刷新">刷新目录</a>
                        </div>
                    </div>
                    <hr>
                </div>
            </#if>

            <p class="ml-3">目录</p>
            <div class="overflow-auto vh-65">
                <div id="custom-toc-container"></div>
            </div>
        </div>
    </div>

    <#--右侧(编辑器)-->
    <div class="col-md-9">
        <div class="whiteBlock vh-85">
            <#--编辑器-->
            <div id="test-editor">
            <textarea style="display:none;"
            ><#switch editFlag><#case 'editDesc'>${blog.desc!}<#break>
                    <#case 'editBlog'>${blog.md!}<#break>
                    <#case 'edit'><#break></#switch></textarea>
            </div>
        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">保存到线上博客</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="blogName" class="col col-form-label">名称</label>
                        <div class="col">
                            <input class="form-control" id="blogName" placeholder="" value="
                                <#switch editFlag>
                                    <#case 'editDesc'><#break>
                                    <#case 'editBlog'>${blog.name!}<#break>
                                    <#case 'edit'><#break>
                                </#switch>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="blogTags" class="col col-form-label">标签</label>
                        <div class="col">
                            <ul id="singleFieldTags"></ul>
                            <input id="blogTags" disabled="true" style="display: none" value="
                                <#switch editFlag>
                                    <#case 'editDesc'><#break>
                                    <#case 'editBlog'>
                                        <#list blog.tags as tag>
                                            ${tag.name},
                                        </#list>
                                        <#break>
                                    <#case 'edit'><#break>
                                </#switch>">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="cancelBtn" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveBlogBtn">保存博客</button>
            </div>
        </div>
    </div>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/js/jquery.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

<!--jquery-ui-->
<script src="/js/jquery-ui.min.js"></script>
<!-- tag-it -->
<script src="/js/tag-it.min.js"></script>
<!-- editormd -->
<script src="/editor.md/editormd.min.js"></script>
<!-- 弹窗 -->
<script src="/js/pop.js"></script>

<#--引入登入模板(该模板需要刚在jquery加载之后的body标签内)-->
<#include  "ftlTemplate/loginTemplate.ftl">

<script type="text/javascript">

    let isMobile = /Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent);
    var blogId=undefined;
    //设置编辑器的元数据
    <#switch editFlag>
    <#case 'editDesc'>
    blogId=${blog.id!-1} <#break>
        <#case 'editBlog'>
        blogId=${blog.id!-1} <#break>
            <#case 'edit'> <#break>
            </#switch>

            $(function() {

                //开启提示工具
                $('[data-toggle="tooltip"]').tooltip();
                //开启标签
                var sampleTags = [${tipTags!"'java'"}];
                $('#singleFieldTags').tagit({
                    //输入提示
                    availableTags: sampleTags,
                    // 与赋值操作有关
                    singleField: true,
                    allowSpaces: true, //标签中是否允许空格
                    singleFieldNode: $('#blogTags') //将值保存到mySingleField元素
                });

                //编辑器
                var editor = editormd("test-editor", {
                    width               : "100%",
                    height              : 600,
                    path                : "/editor.md/lib/",
                    markdown            : "",
                    saveHTMLToTextarea  : true,//开启获取html代码
                    codeFold            : true,//代码折叠
                    atLink              : false,//关闭@link的解析
                    emailLink           : false,//关闭@email的解析
                    todoList            : true,//开启todolist
                    //开启图片上传
                    imageUpload         : true,
                    imageFormats        : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                    imageUploadURL      : "/uploadImg",//上传图片的请求链接,
                    tocStartLevel       : 1 ,//toc目录开始节点
                    //加载成功后的回调
                    onload : function() {
                        //开启toc目录
                        editor.config({
                            tocContainer : "#custom-toc-container",
                            tocDropdown   : false
                        });
                        //判断如果是移动端,则关闭右侧预览
                        if(isMobile){
                            editor.unwatch();
                        }

                        <#--
                        this.fullscreen();
                        this.unwatch();
                        this.watch().fullscreen();
                        this.setMarkdown("");
                        this.width("100%");
                        this.height(480);
                        this.resize("100%", 640);
                        buildTocHtml();
                        -->
                    }
                });


                //保存到线上博客
                $("#saveBlogBtn").click(function () {
                    var tocHtml=undefined;
                    //如果是移动端,先生成toc目录结构,在构建tocHtml
                    if(isMobile){
                        editor.watch();
                        tocHtml=buildTocHtml();
                    }else{
                        tocHtml=buildTocHtml();
                    }
                    $.post("/saveBlog",{
                        id:blogId,
                        name:$("#blogName").val(),
                        tagNames:$('#blogTags').val(),
                        md:editor.getMarkdown(),
                        tocHtml:tocHtml,//生成toc内容
                        mdHtml: getHtml(),
                    },function (data,status) {
                        //成功提示
                        pop.prompt(data.data, 1500);
                        //关闭弹窗
                        $("#cancelBtn").click();
                        //清除数据
                        $("#blogName").val("");
                        $(".tagit-choice").remove();//清除tag
                        goBack();
                    })
                });

                //保存博客描述
                $("#saveDescBtn").click(function () {
                    pop.confirm("确定保存博客描述吗?",function () {
                        $.post("/saveDesc",{
                            id:blogId,
                            desc:editor.getMarkdown(),
                            descHtml:getHtml()
                        },function (data,status) {
                            //成功提示
                            pop.prompt(data.data,1000);
                            goBack();
                        })
                    })
                })

                //刷新目录
                $("#refreshTocBtn").click(function () {
                    editor.watch();
                })


                //返回上一个链接
                function goBack() {
                    setTimeout(function () {
                        pop.confirm("是否前往首页?",function () {
                            window.location.href="/";
                        })
                    },1000)
                }

                //获取预览的html
                function getHtml() {
                    //替换header的id
                    let query = $(".editormd-preview-container").find(":header");
                    for(var i=0;i<linkId.length;i++){
                        var header=query[i];
                        $(header).attr("id",linkId[i]);
                    }
                    return "<div class=\"markdown-body editormd-preview-container\" previewcontainer=\"true\">"
                        +$(".editormd-preview-container").eq(0).html()
                        +"</div>"
                }

                <#--
                testEditor.show();//显示编辑器
                testEditor.hide();//隐藏编辑器
                editor.appendMarkdown("dsfsdfsdf");//追加markdown的内容
                editor.getMarkdown();//获取markdown
                editor.getHTML();//获取html
                testEditor.watch();//开启预览
                testEditor.unwatch();//关闭预览
                testEditor.previewing();//全屏预览
                testEditor.fullscreen();//全屏编辑
                testEditor.showToolbar();//显示工具栏
                testEditor.hideToolbar();//隐藏工具栏
                testEditormd.insertValue("????");testEditormd.focus();//在光标所在处插入文本
                -->
            });


            //递归生成tocHtml
            var linkId=[];//保存生成的linkId
            function buildTocHtml() {
                linkId=[];//清空
                var html={str:""};
                html.str+="<ul class='nav flex-column' id='navbar-example'>";
                var toc=$("#custom-toc-container").find(".markdown-toc-list").eq(0);//拿到ul对象
                if(toc.children().length!=0){
                    for(var i=0;i<toc.children().length;i++){
                        _buildTocHtml(html,toc.children()[i]);
                    }
                }
                html.str+="</ul>";
                return html.str;
            }
            //去遍历子组件
            function _buildTocHtml(html,child) {
                if(child.tagName=="A"){
                    linkId.push("h"+child.attributes.level.value+"-"+child.text);
                    html.str+="<a class='nav-link' href='#h"+child.attributes.level.value+'-'+child.text+"'>"+child.text+"</a>";
                    return;//没有子节点,返回
                }else if(child.tagName=="LI"){
                    if(child.length==0){
                        return;//没有子节点,返回
                    }
                    html.str+="<li class='nav-item'>";
                    //有子节点,则遍历子节点
                    var childs=$(child).children();
                    for(var i=0;i<childs.length;i++){
                        _buildTocHtml(html,childs[i]);
                    }
                    html.str+="</li>";
                }else if(child.tagName=="UL"){
                    if(child.length==0){
                        return;//没有子节点,返回
                    }
                    html.str+="<ul>";
                    //有子节点,则遍历子节点
                    var childs=$(child).children();
                    for(var i=0;i<childs.length;i++){
                        _buildTocHtml(html,childs[i]);
                    }
                    html.str+="</ul>";
                }
            }


</script>

</body>
</html>