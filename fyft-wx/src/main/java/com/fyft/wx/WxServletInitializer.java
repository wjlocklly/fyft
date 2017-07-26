package com.fyft.wx;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import com.fyft.wx.bean.AuthUser;

/**
 *<p>Title: WxServletInitializer.java</p>
 *<p>Description: Web程序启动类</p>
 *<p>CreateDate: 2017年7月26日</p>
 *@author shen
 *@version v1.0
 */
public class WxServletInitializer extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        ConfigurableApplicationContext applicationContext = builder.context();
        AuthUser.setApplicationContext(applicationContext);
        return builder.sources(WxApplication.class);
    }
}
