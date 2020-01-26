<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>博客</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- editormd -->
    <link rel="stylesheet" href="/editor.md/css/editormd.min.css" />
    <!--blog-->
    <link rel="stylesheet" href="/css/blog.css" />
    <!--jquery-ui-->
    <link href="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.css" rel="stylesheet">
    <!--tagit-->
    <link href="/css/jquery.tagit.css" rel="stylesheet" type="text/css">

    <style>
        /*tag-it*/
        .ui-front{
            z-index: 100000;
        }
    </style>
</head>
<body style="padding-top: 0px">

<!-- Fixed navbar -->
<nav class="navbar navbar-default custom-nav" style="margin-bottom: 0px">
    <div class="container">
        <div class="navbar-header" style="margin-right: 20px">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand blue" href="#"><strong>是你啊小米</strong></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse custom-nav">
            <ul class="nav navbar-nav">
                <li><a href="/">首页</a></li>
                <li><a href="#about">关于</a></li>
                <li><a href="#contact">联系</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/edit" target="_blank">写博客</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="row container ">

    <div class="whiteBlock">
        <div class="btn-group" role="group" aria-label="...">
            <!--保存类按钮-->
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    保存
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li data-toggle="modal" data-target="#myModal">
                        <a href="javascript:void(0);" data-toggle="tooltip" data-placement="top"
                           title="无需登入,编写的内容会保存">保存博客
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" data-toggle="tooltip" data-placement="top"
                           title="无需登入,编写的内容会保存" id="saveDescBtn">保存博客描述
                        </a>
                    </li>
                    <li><a href="javascript:void(0);">保存到线上草稿</a></li>
                    <li><a href="javascript:void(0);">保存到本地草稿</a></li>
                </ul>
            </div>
            <!--编辑类按钮-->
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    编辑
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0);">编辑博客</a></li>
                    <li><a href="javascript:void(0);">编辑博客描述</a></li>
                    <li><a href="javascript:void(0);">编辑线上草稿</a></li>
                    <li><a href="javascript:void(0);">编辑本地草稿</a></li>
                </ul>
            </div>

            <button type="button" class="btn btn-default" id="get-md-btn"
                    data-toggle="tooltip" data-placement="top" title="无需登入">
                获取markdown
            </button>
            <button type="button" class="btn btn-default" id="get-html-btn"
                    data-toggle="tooltip" data-placement="top" title="无需登入">
                获取html
            </button>
        </div>
    </div>

    <!--编辑器-->
    <div class="whiteBlock">
        <div id="test-editor">
            <textarea style="display:none;"></textarea>
        </div>
    </div>

</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">保存到线上博客</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="blogName" class="col-sm-2 control-label">博客名称</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="blogName" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="blogTags" class="col-sm-2 control-label">标签</label>
                        <div class="col-sm-10">
                            <ul id="singleFieldTags"></ul>
                            <input id="blogTags" value="" disabled="true" style="display: none">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="cancelBtn" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveBlogBtn">保存博客</button>
            </div>
        </div>
    </div>
</div>



<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!--jquery-ui-->
<script src="https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.js"></script>
<!-- tag-it -->
<script src="/js/tag-it.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<!-- editormd -->
<script src="/editor.md/editormd.min.js"></script>
<!-- 弹窗 -->
<script src="/js/pop.js"></script>

<script type="text/javascript">

    var md="";
    var blogId=undefined;
    //设置编辑器的元数据
    <#switch editFlag>
        <#case 'editDesc'>
            md=`${blog.desc!}`
            blogId=${blog.id!-1}
            <#break>
        <#case 'edit'>
            <#break>
    </#switch>

    $(function() {
        //开启提示工具
        $('[data-toggle="tooltip"]').tooltip();
        //开启标签
        var sampleTags = ['c++', 'java', 'php', 'coldfusion', 'javascript', 'asp', 'ruby', 'python', 'c', 'scala', 'groovy', 'haskell', 'perl', 'erlang', 'apl', 'cobol', 'go', 'lua'];
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
            height              : 550,
            path                : "/editor.md/lib/",
            markdown            : md,
            saveHTMLToTextarea  : true,//开启获取html代码
            codeFold            : true,//代码折叠
            //开启图片上传
            imageUpload         : true,
            imageFormats        : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL      : "/uploadImg",//上传图片的请求链接
        });

        //保存到线上博客
        $("#saveBlogBtn").click(function () {
            $.post("/saveBlog",{
                name:$("#blogName").val(),
                tagNames:$('#blogTags').val(),
                md:editor.getMarkdown(),
                html:editor.getHTML()
            },function (data,status) {
                //成功提示
                pop.prompt(data, 1500);
                //关闭弹窗
                $("#cancelBtn").click();
                //清除数据
                $("#blogName").val("");
                $(".tagit-choice").remove();//清除tag
                goBack();
            })
        });

        $("#saveDescBtn").click(function () {
            pop.confirm("确定保存博客描述吗?",function () {
                $.post("/saveDesc",{
                    id:blogId,
                    desc:editor.getMarkdown(),
                    descHtml:editor.getHTML()
                },function (data,status) {
                    //成功提示
                    pop.prompt(data,1000);
                    goBack();
                })
            })
        })


        //返回上一个链接
        function goBack() {
            setTimeout(function () {
                pop.confirm("选择确定将自动返回上一个链接,返回后如有缓存请刷新页面<br>选择取消将什么都不操作",function () {
                    window.history.back(-1);//返回历史链接
                })
            },1000)
        }


        // testEditor.show();//显示编辑器
        // testEditor.hide();//隐藏编辑器
        // editor.appendMarkdown("dsfsdfsdf");//追加markdown的内容
        // editor.getMarkdown();//获取markdown
        // editor.getHTML();//获取html
        // testEditor.watch();//开启预览
        // testEditor.unwatch();//关闭预览
        // testEditor.previewing();//全屏预览
        // testEditor.fullscreen();//全屏编辑
        // testEditor.showToolbar();//显示工具栏
        // testEditor.hideToolbar();//隐藏工具栏
        // testEditormd.insertValue("????");testEditormd.focus();//在光标所在处插入文本
    });
</script>
</body>
</html>