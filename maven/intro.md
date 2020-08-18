---
author: lunar
date: Tue 18 Aug 2020 08:56:12 AM CST
---

### Maven

maven是一种项目构建与管理工具。

使用maven管理的项目都具有项目的项目结构。

1. 有一个pom.xml用于维护当前项目用的jar包
2. 所有的Java代码都放在src/main/java下面
3. 所有的测试代码都放在src/test/java下面

maven风格的项目使用仓库统一管理jar包。没有使用maven的项目都各自维护一套jar包，而maven的项目统一使用一个仓库。

#### 下载与配置

官方的下载地址:http://maven.apache.org/download.cgi

在Windows上需要将maven解压后的bin目录写入环境变量。

在Linux上则需要打开/etc/profile文件，写入
```
#set maven environment
export MAVEN_HOME=maven_dir
export PATH=$MAVEN_HOME/bin:$PATH
```
maven_dir自然就是maven解压后放置的目录，一般建议放在/usr/local目录下。

保存后更新配置文件: source /etc/profile

然后需要更新镜像，打开配置文件conf/settings.xml

找到mirror项

![](https://images2017.cnblogs.com/blog/1017611/201709/1017611-20170920104716696-1951742177.png)

网址部分记得在http:后面添加两个斜杠/

#### 命令行创建maven风格的Java项目

为了方便理解maven的运作机制，这里用命令行创建一个maven风格的Java项目。

`mvn archetype:generate -DgroupId=com.maven -DartifactId=practice -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode:false`

- archetype:generate: 表示创建一个项目
- DgroupId: 项目包名: com.maven
- DartifactId 项目名称: practice
- DarchetypeArtifactId 项目类型: maven-archetype-quickstart
- DinteractiveMode: false 表示前面参数都给了，就不用一个一个输入了

运行后会在路径下生成一个项目名相同的文件夹。里面包括src,test,target等文件夹。

#### package命令

在项目文件夹下，运行`mvn package`命令，maven会自动进行编译，测试，打包，最后在target文件夹下生成一个jar包。

运行程序`java -cp target/demo-1.0-SNAPSHOT.jar com.demo.App`
