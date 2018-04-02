package cn.edu.nju.software.listeners;

import cn.edu.nju.software.helper.AutoTask;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Application Lifecycle Listener implementation class NFDFlightDataTaskListener
 *
 */
@WebListener
public class AutoTaskListener implements ServletContextListener {

    private static final long PERIOD_DAY = 60 * 1000;

    /**
     * Default constructor.
     */
    public AutoTaskListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent cse)  {
        // TODO Auto-generated method stub
        ServletContext application = cse.getServletContext();
        ApplicationContext ctx =WebApplicationContextUtils.getRequiredWebApplicationContext(application);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Timer timer = new Timer();
        AutoTask task = new AutoTask(ctx);
        timer.schedule(task, date, PERIOD_DAY);
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  {
        // TODO Auto-generated method stub
    }

}
