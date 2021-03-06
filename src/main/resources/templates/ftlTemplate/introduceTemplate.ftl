<#--定义博客右侧的介绍信息-->

<#macro introduce>
    <#--介绍-->
    <div class="whiteBlock center">
        <#--图片-->
        <div>
            <img src="/img/myself111.jpg" class="rounded-circle" style="margin: 10px">
            <p class="text-center">是你啊小米</p>
            <p class="text-center text-muted">专注于Java后端开发</p>
        </div>
        <#--其他标签-->
        <div>
            <span class="badge badge-blue">奋斗</span>
            <span class="badge badge-blue">坚持</span>
            <span class="badge badge-blue">被访问${homePageVisitCount!}次</span>
        </div>
        <#--社交账号-->
        <hr/>
        <p class="center" style="font-size: 18px">社交账号</p>
        <div class="center" style="margin: 15px">
            <a href="https://github.com/shiniaxiaomi" target="_blank" class="icon-content" data-toggle="tooltip" data-placement="top" title="https://github.com/shiniaxiaomi">
                <div class="icon-body"><svg viewBox="64 64 896 896" focusable="false" data-icon="github" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M511.6 76.3C264.3 76.2 64 276.4 64 523.5 64 718.9 189.3 885 363.8 946c23.5 5.9 19.9-10.8 19.9-22.2v-77.5c-135.7 15.9-141.2-73.9-150.3-88.9C215 726 171.5 718 184.5 703c30.9-15.9 62.4 4 98.9 57.9 26.4 39.1 77.9 32.5 104 26 5.7-23.5 17.9-44.5 34.7-60.8-140.6-25.2-199.2-111-199.2-213 0-49.5 16.3-95 48.3-131.7-20.4-60.5 1.9-112.3 4.9-120 58.1-5.2 118.5 41.6 123.2 45.3 33-8.9 70.7-13.6 112.9-13.6 42.4 0 80.2 4.9 113.5 13.9 11.3-8.6 67.3-48.8 121.3-43.9 2.9 7.7 24.7 58.3 5.5 118 32.4 36.8 48.9 82.7 48.9 132.3 0 102.2-59 188.1-200 212.9a127.5 127.5 0 0 1 38.1 91v112.5c.8 9 0 17.9 15 17.9 177.1-59.7 304.6-227 304.6-424.1 0-247.2-200.4-447.3-447.5-447.3z"></path></svg></div>
            </a>
            <span class="icon-content" data-toggle="tooltip" data-placement="top" title="QQ : 806648324">
            <div class="icon-body"><svg viewBox="64 64 896 896" focusable="false" data-icon="qq" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M824.8 613.2c-16-51.4-34.4-94.6-62.7-165.3C766.5 262.2 689.3 112 511.5 112 331.7 112 256.2 265.2 261 447.9c-28.4 70.8-46.7 113.7-62.7 165.3-34 109.5-23 154.8-14.6 155.8 18 2.2 70.1-82.4 70.1-82.4 0 49 25.2 112.9 79.8 159-26.4 8.1-85.7 29.9-71.6 53.8 11.4 19.3 196.2 12.3 249.5 6.3 53.3 6 238.1 13 249.5-6.3 14.1-23.8-45.3-45.7-71.6-53.8 54.6-46.2 79.8-110.1 79.8-159 0 0 52.1 84.6 70.1 82.4 8.5-1.1 19.5-46.4-14.5-155.8z"></path></svg></div>
            </span>
                <span class="icon-content" data-toggle="tooltip" data-placement="top" title="WeChat : lyjlyj806648324">
                <div class="icon-body"><svg viewBox="64 64 896 896" focusable="false" data-icon="wechat" width="1em" height="1em" fill="currentColor" aria-hidden="true"><path d="M690.1 377.4c5.9 0 11.8.2 17.6.5-24.4-128.7-158.3-227.1-319.9-227.1C209 150.8 64 271.4 64 420.2c0 81.1 43.6 154.2 111.9 203.6a21.5 21.5 0 0 1 9.1 17.6c0 2.4-.5 4.6-1.1 6.9-5.5 20.3-14.2 52.8-14.6 54.3-.7 2.6-1.7 5.2-1.7 7.9 0 5.9 4.8 10.8 10.8 10.8 2.3 0 4.2-.9 6.2-2l70.9-40.9c5.3-3.1 11-5 17.2-5 3.2 0 6.4.5 9.5 1.4 33.1 9.5 68.8 14.8 105.7 14.8 6 0 11.9-.1 17.8-.4-7.1-21-10.9-43.1-10.9-66 0-135.8 132.2-245.8 295.3-245.8zm-194.3-86.5c23.8 0 43.2 19.3 43.2 43.1s-19.3 43.1-43.2 43.1c-23.8 0-43.2-19.3-43.2-43.1s19.4-43.1 43.2-43.1zm-215.9 86.2c-23.8 0-43.2-19.3-43.2-43.1s19.3-43.1 43.2-43.1 43.2 19.3 43.2 43.1-19.4 43.1-43.2 43.1zm586.8 415.6c56.9-41.2 93.2-102 93.2-169.7 0-124-120.8-224.5-269.9-224.5-149 0-269.9 100.5-269.9 224.5S540.9 847.5 690 847.5c30.8 0 60.6-4.4 88.1-12.3 2.6-.8 5.2-1.2 7.9-1.2 5.2 0 9.9 1.6 14.3 4.1l59.1 34c1.7 1 3.3 1.7 5.2 1.7a9 9 0 0 0 6.4-2.6 9 9 0 0 0 2.6-6.4c0-2.2-.9-4.4-1.4-6.6-.3-1.2-7.6-28.3-12.2-45.3-.5-1.9-.9-3.8-.9-5.7.1-5.9 3.1-11.2 7.6-14.5zM600.2 587.2c-19.9 0-36-16.1-36-35.9 0-19.8 16.1-35.9 36-35.9s36 16.1 36 35.9c0 19.8-16.2 35.9-36 35.9zm179.9 0c-19.9 0-36-16.1-36-35.9 0-19.8 16.1-35.9 36-35.9s36 16.1 36 35.9a36.08 36.08 0 0 1-36 35.9z"></path></svg></div>
            </span>
        </div>
    </div>

    <#--标签-->
    <div class="whiteBlock">
        <p class="text-left">标签</p>
        <div id="tagsDiv">
            <#if tags??>
                <#list tags as tag>
                    <a href="/moreBlogByTag?id=${tag.id!}" class="badge badge-success">${tag.name!}</a>
                </#list>
                <a class="badge badge-blue" href="javascript:void(0);" onclick="getMoreTags()">更多</a>
            </#if>
        </div>
        <script>
            function getMoreTags() {
                $.get("/moreTags",function(data,status){
                    if(data.code==200){
                        var tagsHtml="";
                        for(var i=0;i<data.data.length;i++){
                            var tag=data.data[i];
                            tagsHtml+="<a style=\"margin-right: 4px;\" href=\"/moreBlogByTag?id="+tag.id+"\" class=\"badge badge-success\">"+tag.name+"</a>";
                        }
                        $("#tagsDiv").html(tagsHtml);
                    }
                })
            }
        </script>
    </div>

    <!--归档-->
    <div class="whiteBlock" >
        <p class="text-left">归档</p>
        <ul class="list-group">
            <#if blogCounts??>
                <#list blogCounts as blogCount>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <a class="text-body" style="font-family: Menlo"
                           <#if blogCount.year!="总计">href="/moreBlogByYear?year=${blogCount.year!}"</#if>>${blogCount.year!}<#if blogCount.year!="总计">年</#if></a>
                        <span class="badge badge-blue badge-pill">${blogCount.count!}</span>
                    </li>
                </#list>
            </#if>
        </ul>
    </div>

</#macro>