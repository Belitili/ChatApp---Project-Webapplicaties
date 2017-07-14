package model;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class ChatMessage {
	
	User sender;
	User receiver;
	String message;
	Date datetime;
	
	
	public ChatMessage(User sender, User receiver, String message) {
		super();
		setSender(sender);
		setReceiver(receiver);
		setMessage(message);
		setDatetime(new Date());
	}
	public User getSender() {
		return sender;
	}
	private void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	private void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	private void setMessage(String message) {
		this.message = message;
	}
	public Date getDatetime() {
		return datetime;
	}
	private void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
        json.put("sender", this.getSender().getUsername());
        json.put("receiver", this.getReceiver().getUsername());
        json.put("message", this.getMessage());
        json.put("datetime", this.getDatetime());
        return json;
	}
}
