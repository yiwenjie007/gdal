# dll下载资源
http://download.gisinternals.com/release.php

# 配置环境变量
在环境变量，系统变量中，增加一个GDAL_JAVA和GDAL32_DLL，变量值根据自己的存放路径来填写

变量名：GDAL_JAVA

变量值：E:\myenv\gdal64\release-1800-x64-dev\release-1800-x64\bin\gdal\java

变量名：GDAL32_DLL

变量值：E:\myenv\gdal64\release-1800-x64-dev\release-1800-x64\bin

在系统变量中增加path变量，如果之前已经存在了，就在这个变量的值后面追加即可。

变量名：path

变量值：;%GDAL32_DLL%;%GDAL32_JAVA%;

比如变量值是有Java的

%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;

添加后，就是

%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;%GDAL32_DLL%;%GDAL32_JAVA%;

# 引入jar
E:\myenv\gdal64\release-1800-x64-dev\release-1800-x64\bin\gdal\java下面的gdal.jar文件引入到你的libraries中