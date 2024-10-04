package com.kun.websocket.chat;

import com.kun.websocket.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage){
        var chatId = chatRoomService
                .getChatRoomId(chatMessage.getSenderId(),chatMessage.getRecipientId(),true)
                .orElseThrow();
        chatMessage.setChatId(chatId);
        repository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId,String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId,recipientId,false);

        //repository::findByChatId 是方法引用，等同于 repository.findByChatId(chatId)
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }
}
