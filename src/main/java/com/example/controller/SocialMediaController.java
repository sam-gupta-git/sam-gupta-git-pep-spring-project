package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    @Autowired
    SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    /**
     * Handler for adding a new message, return error 400: bad request if invalid
     * @param message Message object to be added
     */
    @PostMapping("/messages")
    @ResponseBody
    public ResponseEntity<Message> postMessageHandler(@RequestBody Message message) {
        // Verify that the new message text is valid and the message refers to an existing account
        if (accountService.getAccountById(message.getPostedBy()) != null && message.getMessageText().length() > 0 && message.getMessageText().length() < 255){
            Message addedMessage = messageService.addMessage(message);
            return ResponseEntity.status(HttpStatus.OK).body(addedMessage);
        } else {    
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Handler for updating a message, return error 400: bad request if invalid
     * @param messageId Path variable, ID of message to be updated
     * @param message Message object containing updated text
     */
    @PatchMapping("/messages/{messageId}")
    @ResponseBody
    public ResponseEntity<Integer> updateMessageHandler(@PathVariable Integer messageId, @RequestBody Message message){
        // Verify that the updated message text is valid and the specified message exists
        String messageText = message.getMessageText();
        if (messageText.length() > 0 && messageText.length() < 255 && messageService.getMessageById(messageId) != null){
            int updateResult = messageService.updateMessage(messageId, message);
            return ResponseEntity.status(HttpStatus.OK).body(updateResult);
        } else {    
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Handler for retrieving all messages
     */
    @GetMapping("/messages")
    @ResponseBody
    public ResponseEntity<List<Message>> getAllMessagesHandler(){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllMessages());
    }

    /**
     * Handler for retrieving a message given an ID
     * @param messageId Path variable, ID of message to be retrieved
     */
    @GetMapping("/messages/{messageId}")
    @ResponseBody
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessageById(messageId));
    }

    /**
     * Handler for deleting a message given an ID
     * @param messageId Path variable, ID of message to be deleted
     */
    @DeleteMapping("/messages/{messageId}")
    @ResponseBody
    public ResponseEntity<Integer> deleteMessageHandler(@PathVariable Integer messageId) {
        // Delete and return message if it exists
        if (messageService.getMessageById(messageId) != null){
            int deleteResult = messageService.deleteMessage(messageId);
            return ResponseEntity.status(HttpStatus.OK).body(deleteResult);
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    /**
     * Handler for registering a new account, return error 409: conflict if username already exists
     * or error 400 if username is invalid
     * @param account The account object to be added
     */
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Account> postAccountHandler (@RequestBody Account account) {
        // Check if user exists
        if (accountService.getAccountByUsername(account.getUsername()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        // Check if username and password are valid
        if (account.getUsername().length() != 0 && account.getPassword().length() >= 4){
            Account addedAccount = accountService.addAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body(addedAccount);
        } else {    
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Handler for verifying a login given username and password, return error 401: forbidden if invalid login
     * @param account The account object to be verified and returned if successful
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Account> postAccountLoginHandler (@RequestBody Account account){
        // Check if username and password are valid
        Account loginAccount = accountService.login(account.getUsername(), account.getPassword());
        if (loginAccount != null){
            return ResponseEntity.status(HttpStatus.OK).body(loginAccount);
        } else {    
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Handler for retrieving messages given an account ID
     * @param accountId Path variable, ID of account to retrieve messages from
     */
    @GetMapping("/accounts/{accountId}/messages")
    @ResponseBody
    public ResponseEntity<List<Message>> getMessagesByAccount (@PathVariable Integer accountId) {
        List<Message> fetchedMessages = messageService.getMessagesByAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(fetchedMessages);
    }
}