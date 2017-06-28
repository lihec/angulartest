##项目说明##
1. 导入maven项目
2. 打开命令行，切换到项目根目录内..\projectname>
3. 运行 mvn spring-boot:run，如果需要可以采用命令行debug参数：<br />
   mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,address=58381,suspend=n,server=y"
4. 在命令按ctrl+c停止，按照提示"终止批处理操作吗(Y/N)? y "输入y
5. http://localhost:8080/ex为测试url对应controller为com.qianmi.web.ExampleController
6. 现在已经支持像普通spring项目一样在tomcat（必须为tomcat7以上的版本）中启动

####备注####
*  本项目支持maven命令行启动和像普通spring-mvc一样在外部tomcat（必须支持servlet3.0）中运行
*  支持在项目启动时运行liquibase数据库脚本，参考：db/changelog/db.changelog-master.xml
*  通过application.properties中spring.profiles.active=dev切换使用哪个配置文件启动