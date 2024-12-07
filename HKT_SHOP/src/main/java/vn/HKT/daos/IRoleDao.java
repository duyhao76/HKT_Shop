package vn.HKT.daos;

import vn.HKT.entities.Roles;

public interface IRoleDao {

	Roles findByRoleName(String roleName);

	Roles findById(Long id);

	void insert(Roles role);

}
