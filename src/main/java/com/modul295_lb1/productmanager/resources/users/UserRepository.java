package com.modul295_lb1.productmanager.resources.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {
    UserData findByUsername(String username);
}
