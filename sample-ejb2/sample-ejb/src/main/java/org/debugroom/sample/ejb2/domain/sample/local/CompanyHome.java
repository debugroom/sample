package org.debugroom.sample.ejb2.domain.sample.local;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 * 
 * @author org.debugroom
 *
 */
public interface CompanyHome extends EJBLocalHome {

	public Company create(String companyId, String companyName) throws CreateException;
	
	public Company findByPrimaryKey(CompanyPK pk) throws FinderException;
	
	public Collection<Company> findByName(String companyName) throws FinderException;
	
	public Collection<Company> findAll() throws FinderException;
	

}
