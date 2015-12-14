package it.guideland.app.repositories.repositoriesCustom;

import java.util.List;

import it.guideland.app.model.Interest;

public interface InterestRepositoryCustom {

	public List<Interest> findInterestsByCity(String name);
}
