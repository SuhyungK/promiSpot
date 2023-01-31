package com.ssafy.promispotback.websocket.model;



public class ChatMessage {
	
	public enum MessageType {
		ENTER, TALK
	}
	
	private MessageType type; // 메시지 타입
	private String roomId; // 방번호
	private String sender; // 메시지 보낸 사람
	private String message; // 메시지 
	
	
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
