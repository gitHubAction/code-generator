package com.longfor.csas.config;

import com.longfor.csas.service.impl.IamServiceImpl;
import com.longfor.gaia.gfs.web.feign.EnableLFFeignClients;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhangshihao01
 * @version 1.0
 * @description
 * @date 2021/11/29 18:20
 */
@Configuration
@Import(FeignConfig.class)
@EnableLFFeignClients(basePackages = "com.longfor.csas.feign")
public class IamConfiguration {


   public static final String IAM_URL_FEIGN_NAME = "iam-auth-feign";

   @Bean
   @ConditionalOnMissingBean
   public IamServiceImpl iamService() {
      return new IamServiceImpl();
   }
}
