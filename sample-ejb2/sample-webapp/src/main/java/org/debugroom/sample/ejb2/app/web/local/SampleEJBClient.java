package org.debugroom.sample.ejb2.app.web.local;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.debugroom.sample.ejb2.domain.sample.local.Sample;
import org.debugroom.sample.ejb2.domain.sample.local.SampleHome;

public class SampleEJBClient extends HttpServlet{

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(SampleEJBClient.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {

            Context context = new InitialContext();
            SampleHome sampleHome = (SampleHome)context.lookup("java:comp/env/ejb/SampleSessionBean");
            
            Sample sample = sampleHome.create();
            
            sample.display();
            
            PrintWriter out = resp.getWriter();
            out.println("(・ω・`)");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        super.doGet(req, resp);
    }

    
}
