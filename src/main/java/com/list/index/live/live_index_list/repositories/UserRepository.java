package com.list.index.live.live_index_list.repositories;

import com.list.index.live.live_index_list.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserId(Integer userId);

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
