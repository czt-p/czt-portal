# 1、 工程描述
本工程为策知通云平台的服务端程序，云平台采用前后端分离设计，本工程为前端web提供数据接口服务。
# 2、工程依赖环境
运行环境：jdk1.8+  
构建工具：maven3.3.9+  
本地依赖包：本工程依赖于一些私有包，工程要正常运行需将私有包安装到maven本地仓库。安装好maven后，在windows命令行环境下可通过双击脚本(env/install-dep.cmd)自动安装。  
数据库：mysql5.6+  
搜索服务器：elasticsearch-2.4.6

# 3、运行方式 #
工程基于spring boot进行构建，通过jar包方式运行。直接运行BootStrapApplication类即可。

# 4、工程部署结构图 #
![](https://i.imgur.com/x4m3CPO.png)