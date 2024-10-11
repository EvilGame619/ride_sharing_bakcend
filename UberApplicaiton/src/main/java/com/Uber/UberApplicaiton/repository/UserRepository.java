package com.Uber.UberApplicaiton.repository;

import com.Uber.UberApplicaiton.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
