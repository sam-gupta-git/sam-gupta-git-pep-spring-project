package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{


    // @Query("insert into Message (posted_by, message_text, time_posted_epoch) values (?1, ?2, ?3)")
    // Message insertMessage(Message message);

    @Query("select m from Message m")
    List<Message> getAllMessages();

}
