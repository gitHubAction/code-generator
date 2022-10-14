package ${basePackage};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
* @author ${author}
* @description 启动类
* @date ${date}
*/
@EnableSwagger2
@EnableApolloConfig
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}