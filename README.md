# eroad
下载后需注意有三个地方的jdbc.properties需要更改。现将其列出，以便修改。分别是：
maven/src/main/webapp/WEB-INF/jdbc.properties
maven/src/test/resources/jdbc.properties
maven/src/test/webapp/WEB-INF/jdbc.properties

还需注意，在你准备同步前需要创建隐藏文件.gitignore，他决定了你在上传代码至github时需要忽略的文件或文件夹。如有需要请自行编辑。

千万注意.gitignore是不处理已经add的文件，所以需要在你同步之前就创建好。因为我让.gitignore忽略了自身，所以没有上传至github。我是这么写的：
#忽略target文件
target
target/*

#忽略gitignore自身
.gitignore

#忽略三个jdbc
/maven/src/main/webapp/WEB-INF/jdbc.properties
/maven/src/test/resources/jdbc.properties
/maven/src/test/webapp/WEB-INF/jdbc.properties



