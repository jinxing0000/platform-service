package com.bettem.common.init;

import com.bettem.modules.sys.service.SysDicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitCache implements ApplicationRunner {

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory
            .getLogger(InitCache.class);


    @Autowired
    private SysDicService sysDicService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("++++++++++++++++++++++++++启动开始加载系统字典数据到redis缓存中++++++++++++++++++++++");
        sysDicService.findSysDicDataToRedis();
        logger.debug("++++++++++++++++++++++++++启动结束加载系统字典数据到redis缓存中++++++++++++++++++++++");
    }
}
