package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.entity.Account;
import com.example.entity.Message;

import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.web.bind.annotation.*;

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
    // public Javalin startAPI() {
    //     // New endpoints with handlers
    //     Javalin app = Javalin.create();
    //     app.get("example-endpoint", this::exampleHandler);
    //     app.get("/messages", this::getAllMessagesHandler);
    //     app.get("/messages/{message_id}", this::getMessageById);
    //     app.post("/messages", this::postMessageHandler);
    //     app.patch("/messages/{message_id}", this::updateMessageHandler);
    //     app.delete("/messages/{message_id}", this::deleteMessageHandler);
    //     app.post("/register", this::postAccountHandler);
    //     app.post("/login", this::postAccountLoginHandler);
    //     app.get("/accounts/{account_id}/messages", this::getMessagesByAccount);
    //     return app;
    // }

    @PostMapping("/messages")
    public ResponseEntity<Message> postMessageHandler(@RequestBody Message message) {
        // Verify that the new message text is valid and the message refers to an existing account
        //  && accountService.getAccountById(message.getPostedBy()) != null
        if (message.getMessageText().length() > 0 && message.getMessageText().length() < 255){
            Message addedMessage = messageService.addMessage(message);
            return ResponseEntity.status(HttpStatus.OK).body(addedMessage);
        
        } else {    
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
    }

    // /**
    //  * Handler for updating message endpoint
    //  * @param context The Javalin Context object manages information about both the HTTP request and response.
    //  */
    // private void updateMessageHandler(Context ctx) throws JsonProcessingException {
    //     ObjectMapper mapper = new ObjectMapper();
    //     Message message = mapper.readValue(ctx.body(), Message.class);
    //     int message_id = Integer.parseInt(ctx.pathParam("message_id"));
    //     // Verify that the updated message text is valid and the specified message exists
    //     if (message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255 && messageService.getMessageById(message_id) != null){
    //         Message updatedMessage = messageService.updateMessage(message_id, message);
    //         ctx.json(mapper.writeValueAsString(updatedMessage));
    //     } else {    
    //         ctx.status(400);
    //     }
    // }

    /**
     * Handler for retrieving all messages
     */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessagesHandler(){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllMessages());
    }

    /**
     * Handler for retrieving a message given an ID
     */
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessageById(messageId));
    }

    // /**
    //  * Handler for deleting a message given an ID
    //  * @param context The Javalin Context object manages information about both the HTTP request and response.
    //  */
    // private void deleteMessageHandler(Context ctx) throws JsonProcessingException {
    //     ObjectMapper mapper = new ObjectMapper();
    //     int message_id = Integer.parseInt(ctx.pathParam("message_id"));
    //     // Delete and return message if it exists
    //     if (messageService.getMessageById(message_id) != null){
    //         Message deletedMessage = messageService.deleteMessage(message_id);
    //         ctx.json(mapper.writeValueAsString(deletedMessage));
    //     } 
    // }

    // /**
    //  * Handler for new account endpoint
    //  * @param context The Javalin Context object manages information about both the HTTP request and response.
    //  */
    // private void postAccountHandler(Context ctx) throws JsonProcessingException {
    //     ObjectMapper mapper = new ObjectMapper();
    //     Account account = mapper.readValue(ctx.body(), Account.class);
    //     // Check if username and password are valid
    //     if (accountService.getAccountByUsername(account.getUsername()) == null && account.getUsername().length() != 0 && account.getPassword().length() >= 4){
    //         Account addedAccount = accountService.registerAccount(account);
    //         ctx.json(mapper.writeValueAsString(addedAccount));
    //     } else {    
    //         ctx.status(400);
    //     }
    // }

    // /**
    //  * Handler for verifying a login given username and password, return 401: forbidden if invalid login
    //  * @param context The Javalin Context object manages information about both the HTTP request and response.
    //  */
    // private void postAccountLoginHandler(Context ctx) throws JsonProcessingException {
    //     ObjectMapper mapper = new ObjectMapper();
    //     Account account = mapper.readValue(ctx.body(), Account.class);
    //     Account accountLogin = accountService.verifyLogin(account.getUsername(), account.getPassword());
    //     // Return account if login is successful
    //     if (accountLogin != null){
    //         ctx.json(mapper.writeValueAsString(accountLogin));
    //     } else {    
    //         ctx.status(401);
    //     }
    // }

    // /**
    //  * Handler for retrieving messages given an account ID
    //  * @param context The Javalin Context object manages information about both the HTTP request and response.
    //  */
    // private void getMessagesByAccount(Context ctx) throws JsonProcessingException {
    //     ObjectMapper mapper = new ObjectMapper();
    //     int posted_by = Integer.parseInt(ctx.pathParam("account_id"));
    //     List<Message> fetchedMessages = messageService.getMessagesByAccount(posted_by);
    //     ctx.json(mapper.writeValueAsString(fetchedMessages));
    // }
}