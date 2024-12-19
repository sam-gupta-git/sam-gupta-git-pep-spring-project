package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {
    /**
     * 
     */
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
        return messageRepository.getAllMessages();
    }

    // /**
    //  * Method to retrieve list of messages from a specific account
    //  * @param posted_by The account_id to get messages from, should match an account_id in the account table
    //  */ 
    // public List<Message> getMessagesByAccount(int posted_by) {
    //     return messageRepository.getMessagesByAccount(posted_by);
    // }

    // /**
    //  * Method to retrieve a message by its ID
    //  * @param message_id The ID of a message to be retrieved
    //  */ 
    // public Message getMessageById(int message_id) {
    //     return messageRepository.getMessageById(message_id);
    // }

    // /**
    //  * Method to update the message_text of a message given its ID
    //  * @param message_id The ID of the message to be updated
    //  * @param message The message object that contains the updated message
    //  */ 
    // public Message updateMessage(int message_id, Message message) {
    //     if (messageRepository.getMessageById(message_id) != null){
    //         messageRepository.updateMessage(message_id, message);
    //         Message updatedMessage = messageRepository.getMessageById(message_id);
    //         return updatedMessage;
    //     }
    //     return null;
    // }

    // /**
    //  * Method to delete a message given its ID
    //  * @param message_id The ID of the message to be deleted
    //  */ 
    // public Message deleteMessage(int message_id) {
    //     Message deletedMessage = messageRepository.getMessageById(message_id);
    //     if (deletedMessage != null){
    //         messageRepository.deleteMessage(message_id);
    //         return deletedMessage;
    //     }
    //     return null;
    // }
}
