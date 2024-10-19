package com.example.haibazotest.respository;

import com.example.haibazotest.domain.User;
import com.example.haibazotest.domain.dto.req.user.LoginReqDTO;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username,String password);

    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username", nativeQuery = true)
    User findUserByUserName(String username);
}
