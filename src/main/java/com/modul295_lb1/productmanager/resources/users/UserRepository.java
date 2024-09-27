package com.modul295_lb1.productmanager.resources.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {
    UserData findByEmail(String email);
    UserData findByUsername(String username);

    @Query("SELECT u FROM UserData u WHERE u.email = :usernameOrEmail OR u.username = :usernameOrEmail")
    UserData findByEmailOrUsername(@Param("usernameOrEmail") String usernameOrEmail);

}
