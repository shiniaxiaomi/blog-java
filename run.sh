# 关闭应用
app=$(pgrep -f plantip)
if test ${app}null != "null"
then
    kill -9 $app
fi

# 启动应用
projectPath=/root/code/blog-java
cd $projectPath/target
name=$(ls |grep jar$)
java -jar $projectPath/target/$name --spring.profiles.active=prod >$projectPath/log &
tail -f $projectPath/log
