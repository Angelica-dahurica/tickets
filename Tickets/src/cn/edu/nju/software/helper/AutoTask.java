package cn.edu.nju.software.helper;

import cn.edu.nju.software.service.AutoService;
import org.springframework.context.ApplicationContext;

import java.util.TimerTask;

public class AutoTask extends TimerTask {
    private ApplicationContext ctx;
    private AutoService autoService;

    public AutoTask(ApplicationContext context){
        ctx = context;
        autoService = (AutoService)ctx.getBean("autoService");
    }

    @Override
    public void run() {
        try {
            autoService.handleOrders();
            autoService.handleLocks();
            autoService.handleScores();
            autoService.handleTickets();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
