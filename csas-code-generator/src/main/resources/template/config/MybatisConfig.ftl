package ${basePackage}.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;

/**
* @author ${author}
* @description myBatis配置
* @date ${date}
*/
@Slf4j
@Configuration
public class MybatisConfig {


    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage("${basePackage}.domain");
        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver
                .getResources("classpath*:mapper/*.xml"));
        factory.setConfiguration(mybatisConfig());
        return factory.getObject();
    }

    /**
     * 方法前面加上static。让使用configuration的类在没有实例化的时候不会去过早的要求@Autowired和@Value,否则会导致@value注入失败
     *
     * @return
     */
    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage("${basePackage}.mapper");
        return mapperScannerConfigurer;
    }

    /**
     * 与mybatis-config.xml对应的 java类，通过java对象({@link org.apache.ibatis.session.Configuration})来设置，删掉xml配置文件
     * typeAliases的配置可以参考{@link  org.apache.ibatis.session.Configuration#getTypeAliasRegistry()} ,一般常用的对象都已经默认配置
     *
     * @return
     */
    private org.apache.ibatis.session.Configuration mybatisConfig() {
        org.apache.ibatis.session.Configuration mybatisConfig = new org.apache.ibatis.session.Configuration();
        //默认情况下,Mybatis对Map的解析生成, 如果值(value)为null的话,那么key也不会被加入到map中,因此需要设置callSettersOnNulls为true
        mybatisConfig.setCallSettersOnNulls(true);
        return mybatisConfig;
    }

}