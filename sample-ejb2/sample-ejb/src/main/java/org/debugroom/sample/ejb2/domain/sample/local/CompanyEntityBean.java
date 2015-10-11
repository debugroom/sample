package org.debugroom.sample.ejb2.domain.sample.local;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.RemoveException;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CompanyEntityBean implements EntityBean {

	private static final Logger logger = LoggerFactory.getLogger(CompanyEntityBean.class);

	private transient EntityContext entityContext;
	
	public abstract void setCompanyId(String companyId);
	public abstract String getCompanyId();
	public abstract void setCompanyPK(CompanyPK copmanyPK);
	public abstract CompanyPK getCompanyPK();
	public abstract void setCompanyName(String companyName);
	public abstract String getCompanyName();
		
	public CompanyPK ejbCreate(String companyId, String companyName){
		CompanyPK companyPK = new CompanyPK(companyId);
		setCompanyPK(companyPK);
		setCompanyId(companyId);
		setCompanyName(companyName);
		return companyPK;
	}

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
        logger.info("#ejbActivate()");
	}

	@Override
	public void ejbLoad() throws EJBException, RemoteException {
        logger.info("#ejbLoad()");
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
        logger.info("#ejbPassivate()");
	}

	@Override
	public void ejbRemove() throws RemoveException, EJBException,
			RemoteException {
        logger.info("#ejbRemove()");
		
	}

	@Override
	public void ejbStore() throws EJBException, RemoteException {
        logger.info("#ejbStore()");
	}

	@Override
	public void setEntityContext(EntityContext entityContext) throws EJBException,
			RemoteException {
        logger.info("#setEntityContext()");
		this.entityContext = entityContext;
	}

	@Override
	public void unsetEntityContext() throws EJBException, RemoteException {
        logger.info("#unsetEntityContext()");
	}

    public void display(){
        logger.info("#display(・∀・)");
    }

    @Override
    public boolean equals(Object other){
    	if((this == other)){
    		return true;
    	}
    	if(!(other instanceof CompanyEntityBean)){
    		return false;
    	}
    	CompanyEntityBean companyEntityBean = (CompanyEntityBean)other;
    	return new EqualsBuilder().append(this.getCompanyPK(), companyEntityBean.getCompanyPK())
    			.isEquals();
    }
    
    @Override
    public int hashCode(){
    	return new HashCodeBuilder()
    	.append(getCompanyPK())
    	.toHashCode();
    }

}
