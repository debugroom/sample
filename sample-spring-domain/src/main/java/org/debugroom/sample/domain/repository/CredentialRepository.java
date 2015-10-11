package org.debugroom.sample.domain.repository;

import java.util.List;

import org.debugroom.sample.domain.model.Credential;

public interface CredentialRepository {

	public List<Credential> getCredentials(String userId);
	
}
