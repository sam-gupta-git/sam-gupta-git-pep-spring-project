package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    // @Query("select m from Message m where m.messageId = ?1")
    // Message getMessageById(Integer messageId);

}
