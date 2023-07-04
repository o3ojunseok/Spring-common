package com.junseok.practice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UresRepository extends JpaRepository<User, Long> {
}
