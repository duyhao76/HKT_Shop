package vn.HKT.services;

import vn.HKT.entities.Users;

public interface IUserService {

	Users login(String email, String password);

	boolean register(String email, String fullName, String password);

	Users FindByEmail(String email);

}
