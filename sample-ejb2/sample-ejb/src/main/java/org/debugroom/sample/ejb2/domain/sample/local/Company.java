package org.debugroom.sample.ejb2.domain.sample.local;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalObject;

/**
 * 
 * @author org.debugroom
 *
 */
public interface Company extends EJBLocalObject{
	
	public void display();
	
	public CompanyPK getCompanyId() throws CreateException;
	public void setCompanyId(String companyId) throws CreateException;
	public String getCompanyName() throws CreateException;
	public void setCompanyName(String companyName) throws CreateException;
	
}
