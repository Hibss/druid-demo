package com.czkj.multi.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class DruidMultiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DruidMultiDemoApplication.class, args);
	}
	@Bean
	public ServletRegistrationBean druidServlet() {
		log.info("初始化druid可视化");
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
				new StatViewServlet(), "/druid/*");
		// 白名单(为空表示,所有的都可以访问,多个IP的时候用逗号隔开)
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		// IP黑名单 (存在共同时，deny优先于allow) （黑白名单就是如果是黑名单，那么该ip无法登陆该可视化界面）
		servletRegistrationBean.addInitParameter("deny", "127.0.0.2");
		// 设置登录的用户名和密码
		servletRegistrationBean.addInitParameter("loginUsername", "root");
		servletRegistrationBean.addInitParameter("loginPassword", "123456");
		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "true");
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}
