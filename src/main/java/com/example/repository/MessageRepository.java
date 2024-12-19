package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    @Query("select m from Message m")
    List<Message> getAllMessages();

}
