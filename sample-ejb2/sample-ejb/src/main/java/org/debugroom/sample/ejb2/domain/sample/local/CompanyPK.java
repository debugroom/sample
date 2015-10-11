package org.debugroom.sample.ejb2.domain.sample.local;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 
 * @author org.debugroom
 *
 */
public class CompanyPK implements Serializable{

	private static final long serialVersionUID = -1288016616447132659L;

	private String companyId;

	public CompanyPK(){}
	
	public CompanyPK(String companyId){
		this.companyId = companyId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	@Override
	public boolean equals(Object other){
		if((this == other)){
			return true;
		}
		if(!(other instanceof CompanyPK)){
			return false;
		}
		CompanyPK companyPK = (CompanyPK)other;
		return new EqualsBuilder()
					.append(this.getCompanyId(), companyPK.getCompanyId())
					.isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder()
					.append(getCompanyId())
					.toHashCode();
	}

}
