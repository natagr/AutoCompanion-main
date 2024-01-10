package com.auto.companion.domain.repository;

import java.util.List;
import java.util.Optional;

import com.auto.companion.domain.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

  @Query(value = """
    SELECT t FROM Token t
    INNER JOIN User u ON t.user.id = u.id
    WHERE u.id = :userId AND (t.expired = false OR t.revoked = false)
  """)
  List<Token> findAllValidTokensByUser(@Param("userId") Long id);


  Optional<Token> findByToken(String token);
}
