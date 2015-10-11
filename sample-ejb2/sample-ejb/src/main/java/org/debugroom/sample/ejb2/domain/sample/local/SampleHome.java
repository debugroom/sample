package org.debugroom.sample.ejb2.domain.sample.local;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 * 
 * @author org.debugroom
 *
 */
public interface SampleHome extends EJBLocalHome{

    public Sample create() throws CreateException;

}
