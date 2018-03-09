package com.ufcg.si1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ufcg.si1.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, String>{

	public Admin getAdminByLogin(String login);
	
}
