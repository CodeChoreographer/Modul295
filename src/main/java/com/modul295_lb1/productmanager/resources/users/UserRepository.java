package com.modul295_lb1.productmanager.resources.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, Integer> {
    UserData findByUsername(String username);
}
