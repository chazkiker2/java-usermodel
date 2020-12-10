package com.lambdaschool.usermodel.repository;


import com.lambdaschool.usermodel.models.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;



/**
 * The CRUD Repository connecting Role to the rest of the application
 */
public interface RoleRepository
		extends CrudRepository<Role, Long> {
	/**
	 * JPA Query to find a role by name case insensitive search
	 *
	 * @param name the name of the role which you seek
	 *
	 * @return the first role matching the given name using a case insensitive search
	 */
	Role findByNameIgnoreCase(String name);

	@Transactional
	@Modifying
	@Query(value = "UPDATE roles " + "SET name=:rolename, " + "last_modified_by=:username, " +
	               "last_modified_date=CURRENT_TIMESTAMP " + "WHERE roleid = :roleid ",
	       nativeQuery = true)
	void updateRoleName(
			@Param("roleid")
					long roleid,
			@Param("rolename")
					String rolename,
			@Param("username")
					String username
	);

}
