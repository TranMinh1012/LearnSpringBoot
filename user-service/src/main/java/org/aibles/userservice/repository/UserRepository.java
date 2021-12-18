package org.aibles.userservice.repository;

import org.aibles.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    @Transactional
//    @Modifying
//    @Query("update User u set u.name=?2 where u.id=?1")
//    int updateAddress(int id, String name);
}
