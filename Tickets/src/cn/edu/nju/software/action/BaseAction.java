package cn.edu.nju.software.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class BaseAction extends ActionSupport implements SessionAware,  
        ServletRequestAware, ServletResponseAware,ServletContextAware{  
  
    private static final long serialVersionUID = 1L;  

    HttpServletRequest request;
    HttpServletResponse response;
    ServletContext application;
    private Map session;
  
    public void setSession(Map session) {
        this.session = session;  
    }  
  
    public void setServletRequest(HttpServletRequest request) {  
       this.request = request;  
    }  
  
    public void setServletResponse(HttpServletResponse response) {  
       this.response = response;  
    }

	@Override
	public void setServletContext(ServletContext application) {
		this.application = application;
	}

}

