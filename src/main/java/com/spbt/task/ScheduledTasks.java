package com.spbt.task;

import com.spbt.service.UserService;
import com.spbt.util.DateUtil;
import com.spbt.util.HttpRequester;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 定时任务
 * @author 李贺[of253]
 * @date 2015/8/24 11:08
 */
@Component
public class ScheduledTasks {
    @Autowired
    private UserService userService;

    @Value("${app.domain}")
    private String appDomain;

    /**
     * 采用线程池发生消息
     */
    ExecutorService threadPool = Executors.newFixedThreadPool(3);

    //    @Scheduled(fixedRate = 20000)
    //    @Scheduled(cron = "${scheduled.cron.remind}")
    //    public void remind() {
    //        System.out.println(DateUtil.getCurrentDate());
    //    }

    @Scheduled(cron = "${scheduled.cron.refreshSite}")
    public void refreshSite() {
        System.out.println(DateUtil.getCurrentDate());
        String result = HttpRequester.get(appDomain + "/jn", HTTP.UTF_8);
        System.out.println("http request result : " + result);
    }
}
