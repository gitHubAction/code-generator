<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true">
    <!-- 	<include resource="org/springframework/boot/logging/logback/base.xml" /> -->
    <!-- 不直接引用base.xm, 改为引入依赖的文件, 防止多个app部署同一套环境，导致/tmp/spring.log 权限问题 -->
    <include resource="org/springframework/boot/logging/logback/defaults.xml" />
    <include resource="org/springframework/boot/logging/logback/console-appender.xml" />
    <include resource="org/springframework/boot/logging/logback/file-appender.xml" />

    <!-- 日志格式化模板 ,输出样例如下： -->
    <!-- 2017-09-23 18:05:23.147 INFO 4736 [http-nio-8080-exec-2] com.uhope.loggertest.model.UserInfo
        : UserInfo has been created... -->
    <property name="FILE_LOG_PATTERN"
              value="%d{yyyy-MM-dd HH:mm:ss.SSS} ${LOG_LEVEL_PATTERN} ${PID} --- [%t] %-40.40logger{39} : %m%n${LOG_EXCEPTION_CONVERSION_WORD}" />

    <!-- APP 名称没配置, 每个App都需要配置 -->
    <property name="APP_NAME" value="uiptemplate"></property>

    <!-- APP安装Home目录配置, 每个App都需要配置 -->
    <property name="APP_HOME" value="/home/uiptemplate"></property>

    <property name="LOG_HOME_PATH" value="${APP_HOME}/logs"></property>

    <property name="DEBUG_LOG_FILE" value="${LOG_HOME_PATH}/debug/${APP_NAME}_debug" />
    <property name="INFO_LOG_FILE" value="${LOG_HOME_PATH}/info/${APP_NAME}_info" />
    <property name="WARN_LOG_FILE" value="${LOG_HOME_PATH}/warn/${APP_NAME}_warn" />
    <property name="ERROR_LOG_FILE" value="${LOG_HOME_PATH}/error/${APP_NAME}_error" />
    <property name="RUN_LOG_FILE" value="${LOG_HOME_PATH}/run/${APP_NAME}_run" />
    <property name="SEC_LOG_FILE" value="${LOG_HOME_PATH}/security/${APP_NAME}_sec" />
    <property name="START_LOG_FILE" value="${LOG_HOME_PATH}/start/${APP_NAME}_start" />

    <!-- Error 级别 日志配置 -->
    <appender name="ERROR_FILE"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>

        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${ERROR_LOG_FILE}.%d{yyyy-MM-dd}.log</FileNamePattern>
            <MaxHistory>60</MaxHistory>
        </rollingPolicy>

        <filter class="ch.qos.logback.classic.filter.LevelFilter"><!-- 只打印ERROR日志 -->
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <!-- INFO 级别 日志配置 -->
    <appender name="INFO_FILE"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>

        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${INFO_LOG_FILE}.%d{yyyy-MM-dd}.log</FileNamePattern>
            <MaxHistory>60</MaxHistory>
        </rollingPolicy>

        <filter class="ch.qos.logback.classic.filter.LevelFilter"><!-- 只打印INFO日志 -->
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>
    <!-- WARN 级别 日志配置 -->
    <appender name="WARN_FILE"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>

        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${WARN_LOG_FILE}.%d{yyyy-MM-dd}.log</FileNamePattern>
            <MaxHistory>60</MaxHistory>
        </rollingPolicy>

        <filter class="ch.qos.logback.classic.filter.LevelFilter"><!-- 只打印WARN日志 -->
            <level>WARN</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <!-- DEBUG 级别 日志配置 -->
    <appender name="DEBUG_FILE"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>

        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${DEBUG_LOG_FILE}.%d{yyyy-MM-dd}.log</FileNamePattern>
            <MaxHistory>60</MaxHistory>
        </rollingPolicy>

        <filter class="ch.qos.logback.classic.filter.LevelFilter"><!-- 只打印DEBUG日志 -->
            <level>DEBUG</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <!-- 运行日志配置,包含DEBUG、INFO、WARN、ERROR 等日志 -->
    <appender name="RUN_FILE"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>

        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${RUN_LOG_FILE}.%d{yyyy-MM-dd}.log</FileNamePattern>
            <MaxHistory>60</MaxHistory>
        </rollingPolicy>
    </appender>

    <!-- 安全日志配置,包含DEBUG、INFO、WARN、ERROR 等日志 -->
    <appender name="SECURITY_FILE"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>

        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${SEC_LOG_FILE}.%d{yyyy-MM-dd}.log</FileNamePattern>
            <MaxHistory>60</MaxHistory>
        </rollingPolicy>

    </appender>

    <!-- 启动日志配置, 包含DEBUG、INFO、WARN、ERROR 等日志 -->
    <appender name="START_FILE"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>

        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${START_LOG_FILE}.%d{yyyy-MM-dd}.log</FileNamePattern>
            <MaxHistory>60</MaxHistory>
        </rollingPolicy>

    </appender>

    <!-- 配置安全日志: 包路径, 这里主要针对安全相关模块都在该包路径下 -->
    <logger name="com.uhope.loggertest.security" level="DEBUG"
            additivity="false">
        <appender-ref ref="SECURITY_FILE" />
    </logger>

    <!-- 配置安全日志: 完整类名称路径, 这里主要特殊场景,涉及安全 但是不在安全包路径下 -->
    <logger name="com.uhope.loggertest.manager.UserManager" level="DEBUG"
            additivity="false">
        <appender-ref ref="SECURITY_FILE" />
    </logger>

    <!-- 配置启动日志：类完整路径 -->
    <!-- <logger name="com.uhope.loggertest.LoggerTestApplication" level="DEBUG"
        additivity="false"> <appender-ref ref="START_FILE" /> </logger> -->

    <!-- 配置启动日志：类完整路径 -->
    <logger name="com.uhope.loggertest.listener.AppContextListener"
            level="DEBUG" additivity="false">
        <appender-ref ref="START_FILE" />
    </logger>

    <!-- 按包名称拆分日志级别后输出文件(指定不同的appender)， <loger>用来设置某一个包或者具体的某一个类的日志打印级别、以及指定<appender>。<loger>仅有一个name属性，一个可选的level和一个可选的addtivity属性。
        name:用来指定受此loger约束的某一个包或者具体的某一个类。 level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO,
        WARN, ERROR, ALL 和 OFF，还有一个特俗值INHERITED或者同义词NULL，代表强制执行上级的级别。如果未设置此属性，那么当前loger将会继承上级的级别。
        addtivity:是否向上级loger传递打印信息。默认是true。 -->

    <!-- root节点是必选节点，用来指定最基础的日志输出级别，只有一个level属性 level:用来设置打印级别， 大小写无关：TRACE,
        DEBUG, INFO, WARN, ERROR, ALL 和 OFF，不能设置为INHERITED或者同义词NULL，默认是DEBUG. 另外Spring
        Boot 最好开启 INFO 级别或以上 日志，否则基础Debug日志会相当多，影响性能 -->

    <root level="INFO">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="DEBUG_FILE" />
        <appender-ref ref="INFO_FILE" />
        <appender-ref ref="WARN_FILE" />
        <appender-ref ref="ERROR_FILE" />
        <appender-ref ref="RUN_FILE" />
    </root>
    <!--配置sql语句打印，name对应mapper包路径,如果有多个，则配置多个<logger>标签-->
    <logger name="${basePackage}.mapper">
        <level value="DEBUG"/>
        <appender-ref ref="DEBUG_FILE"/>
    </logger>
    <!-- 配置启动日志：类完整路径 -->
    <logger name="${basePackage}.Application"
            level="DEBUG" additivity="false">
        <appender-ref ref="START_FILE" />
    </logger>

</configuration>
