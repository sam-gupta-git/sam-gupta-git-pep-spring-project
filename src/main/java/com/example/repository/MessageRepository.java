package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * JPA Repository interface for the Message entity
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("select m from Message m where m.postedBy = ?1")
    List<Message> findMessageByAccount(int accountId);

}
