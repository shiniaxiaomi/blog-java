<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">登入</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="/userLogin">
                    <div class="form-group">
                        <label for="userName" class="col-form-label">用户名</label>
                        <input type="text" class="form-control" id="userName">
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-form-label">密码</label>
                        <input type="password" class="form-control" id="password">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary mx-2" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary mx-2" id="userLoginBtn">登入</button>
            </div>
        </div>
    </div>
</div>

<#if isLogin==false>
    <script>
        //打开登入弹窗
        $("#openLoginBtn").click(function () {
            $("#loginModal").modal();
        })
        //点击登入按钮
        $("#userLoginBtn").click(function () {
            $.post("/userLogin",{
                userName:$("#userName").val(),
                password:$("#password").val(),
            },function (data,status) {
                pop.prompt(data.data, 1500);//成功提示
                $('#loginModal').modal('hide');//隐藏登入弹窗
            })
        })
    </script>
</#if>