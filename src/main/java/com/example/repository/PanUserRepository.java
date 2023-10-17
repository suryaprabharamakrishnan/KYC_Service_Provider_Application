package com.example.repository;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class PanUserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public boolean existsByEmail(String email) {
		Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM pan_user WHERE email = :email");
		query.setParameter("email", email);
		int count = ((Number) query.getSingleResult()).intValue();
		return count > 0;
	}

}
