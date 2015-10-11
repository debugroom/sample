package org.debugroom.sample.app.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import org.debugroom.sample.domain.service.common.ServiceExecutor;

/**
 * 
 * @author org.debugroom
 *
 */
public class XmlBasedMappingSampleController implements Controller{

    private ServiceExecutor serviceExecutor;
	
    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        serviceExecutor.executeService(request.getParameter("target"));
        return null;
    }

    public ServiceExecutor getServiceExecutor() {
        return serviceExecutor;
    }

    public void setServiceExecutor(ServiceExecutor serviceExecutor) {
        this.serviceExecutor = serviceExecutor;
    }
    
}
