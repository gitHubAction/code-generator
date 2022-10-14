# 项目名称
spring.application.name=${projectName}

# 加载对应的环境信息
spring.profiles.active=sit

# 让控制器输出的json字符串格式更美观
spring.jackson.serialization.indent_output=true

# 禁用集团的consul
spring.cloud.consul.enabled=false

# Server Port
server.port=8081

# Manage Port
management.server.port=9091

# 禁用所有endpoints的web服务
management.endpoints.web.exposure.exclude="*"

#Apollo相关
app.id=${projectName}

# apollo饥饿配置
apollo.bootstrap.namespaces=application
apollo.bootstrap.enabled=true
apollo.bootstrap.eagerLoad.enabled=true

# swagger相关配置
longfor.web.swagger.enable=true
longfor.web.swagger.contact.name=${author}

# ELK配置
logging.kafka.enabled=false
logging.kafka.broker=
logging.kafka.env=sit

# 日志配置
logging.path=/temp/
logging.level.${basePackage}=DEBUG

# ##### 数据库配置 ########
longfor.data.database.connection.master.url=jdbc:mysql://${url}:${port}/${dataBase}?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false
longfor.data.database.connection.master.username=${user}
longfor.data.database.connection.master.password=${passWord}
longfor.data.database.connection.master.type=com.alibaba.druid.pool.DruidDataSource
longfor.data.database.connection.master.driver-class-name=${driverClass}

# #############druid连接池的补充设置 start##################
# 下面为druid连接池的补充设置，应用到上面所有数据源中
# 初始化大小，最小，最大
#longfor.data.database.connection.master.druid.initialSize=5
#longfor.data.database.connection.master.druid.minIdle=5
#longfor.data.database.connection.master.druid.maxActive=20
# 配置获取连接等待超时的时间
#longfor.data.database.connection.master.druid.maxWait=5000
# 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
#longfor.data.database.connection.master.druid.timeBetweenEvictionRunsMillis=60000
# 配置一个连接在池中最小生存的时间，单位是毫秒
#longfor.data.database.connection.master.druid.minEvictableIdleTimeMillis=300000
#longfor.data.database.connection.master.druid.validationQuery=SELECT 1 FROM DUAL
#longfor.data.database.connection.master.druid.testWhileIdle=true
#longfor.data.database.connection.master.druid.testOnBorrow=false
#longfor.data.database.connection.master.druid.testOnReturn=false
# 打开PSCache，并且指定每个连接上PSCache的大小
#longfor.data.database.connection.master.druid.poolPreparedStatements=true
#longfor.data.database.connection.master.druid.maxPoolPreparedStatementPerConnectionSize=20
# 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
#longfor.data.database.connection.master.druid.filters=stat,wall,log4j
# 通过connectProperties属性来打开mergeSql功能；慢SQL记录
#longfor.data.database.connection.master.druid.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
# 合并多个DruidDataSource的监控数据
#longfor.data.database.connection.master.druid.useGlobalDataSourceStat=true
# #############druid连接池的补充设置 end##################
