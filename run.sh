# 关闭应用
#应用名称
appName="blog-java"
app=$(pgrep -f $appName)
if test ${app}null != "null"
then
    # 调用应用接口执行保存操作
    status=$(curl http://localhost:8080/writeBlogVisitTimes)
    if test ${status} == 'success'
    then
        # 如果保存成功，则停止应用
        kill -9 $app
    else
        # 如果保存失败，停止脚本
        echo '接口调用失败'
        exit
    fi
fi

# 启动应用
projectPath=/root/code/$appName
cd $projectPath/target
name=$(ls |grep jar$)
nohup java -jar $projectPath/target/$name --spring.profiles.active=prod >$projectPath/log &
tail -f $projectPath/log
