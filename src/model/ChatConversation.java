package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class ChatConversation {
	
	private User[] users = new User[2];
	private ArrayList<ChatMessage> conversation;
	
	public ChatConversation(User user1, User user2) {
		users[0] = user1;
		users[1] = user2;
		conversation = new ArrayList<ChatMessage>();
	}
	public User[] getUsers() {
		return users;
	}
	public ArrayList<ChatMessage> getConversation() {
		return conversation;
	}

	public void addMessage(User sender, User receiver, String message) {
		conversation.add(new ChatMessage(sender, receiver, message));
	}
	public void addMessage(ChatMessage message) {
		conversation.add(message);
	}
	
	public JSONArray toJSON() throws JSONException {
		JSONArray array = new JSONArray();
		for (ChatMessage message: conversation) {
			array.put(message.toJSON());
		}
		return array;
	}

}
