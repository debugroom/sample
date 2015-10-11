package org.debugroom.sample.ejb2.domain.sample.local;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleSessionBean implements SessionBean{

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(SampleSessionBean.class);
    
    private SessionContext sessionContext;
    
    @Override
    public void ejbActivate() throws EJBException, RemoteException {
        logger.info("#ejbActivate()");
    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {
        logger.info("#ejbPassivate()");
        
    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {
        logger.info("#ejbRemove()");
        
    }

    @Override
    public void setSessionContext(SessionContext sessionContext) throws EJBException,
            RemoteException {
        logger.info("#setSessionContext()");
        this.sessionContext = sessionContext;
    }

    public void display(){
        logger.info("#display(・∀・)");
    }
    
}

