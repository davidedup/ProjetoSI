package com.ufcg.si1.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.Admin;
import com.ufcg.si1.repositories.AdminRepository;

import io.jsonwebtoken.Jwts;

@Service
public class Util {
	
	@Autowired
	private AdminRepository adminRepository;

	public static <T> List<T> toList(Iterable<T> iterable) {
		ArrayList<T> list = new ArrayList<T>();

		for (T t : iterable) {
			list.add(t);
		}

		return list;
	}
	
	public Admin getAdmin(String autorizacao) {
		System.out.println("cheguei 2");
        String login = getLogin(autorizacao);
        System.out.println("cheguei 5");
        System.out.println(login);
        Admin usuario = adminRepository.getAdminByLogin(login);
        System.out.println("cheguei 6");

        return usuario;
    }
	
	public String getLogin(String autorizacao) {
		System.out.println("cheguei 3");
        String login = (String) Jwts.parser().setSigningKey("pastel").parseClaimsJws(autorizacao).getBody().get("login");
        System.out.println("cheguei 4");
        return login;
    }

}
