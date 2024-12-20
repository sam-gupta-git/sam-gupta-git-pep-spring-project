package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired    
    MessageRepository messageRepository;

    /**
     * Method to add a new message, returns the new message object if successful
     * @param message Message object that contains message information
     */ 
    public Message addMessage(Message message){
        return messageRepository.save(message);
    }

    /**
     * Method to retrieve all messages as a list, call messageRepository to select all messages in table
     */ 
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * Method to retrieve list of messages from a specific account
     * @param posted_by The account_id to get messages from, should match an account_id in the account table
     */ 
    public List<Message> getMessagesByAccount(int accountId) {
        return messageRepository.findMessageByAccount(accountId);
    }

    /**
     * Method to retrieve a message by its ID
     * @param messageId The ID of a message to be retrieved
     */ 
    public Message getMessageById(Integer messageId) {
        Optional<Message> messageOptional = messageRepository.findById(messageId);
        if(messageOptional.isPresent()){
            Message message = messageOptional.get();
            return message;
        }
        return null;
    }

    /**
     * Method to update the message_text of a message given its ID
     * @param messageId The ID of the message to be updated
     * @param message The message object that contains the updated message
     */ 
    public int updateMessage(int messageId, Message message) {
        Optional<Message> messageOptional = messageRepository.findById(messageId);
        if(messageOptional.isPresent()){
            Message updatedMessage = messageOptional.get();
            updatedMessage.setMessageText(message.getMessageText());
            messageRepository.save(updatedMessage);
            return 1;
        }
        return 0;
    }

    /**
     * Method to delete a message given its ID
     * @param messageId The ID of the message to be deleted
     */ 
    public int deleteMessage(int messageId) {
        Optional<Message> messageOptional = messageRepository.findById(messageId);
        if(messageOptional.isPresent()){
            Message deletedMessage = messageOptional.get();
            messageRepository.delete(deletedMessage);
            return 1;
        }
        return 0;
    }
}
