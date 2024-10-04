package com.kun.websocket.chatroom;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

//Optional 是 Java 8 中引入的一个包装类，避免了返回 null 的问题。如果找到了符合条件的 ChatRoom，
// 它将返回一个包含该 ChatRoom 的 Optional 对象；如果没有找到，则返回一个空的 Optional 对象。
public interface ChatRoomRepository extends MongoRepository<ChatRoom,String> {

    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId,String recipientId);
}
