package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ChatConversation;
import model.ChatMessage;
import model.User;
import model.UserFactory;

//Singleton DB
public class UserDB {
	
	private static volatile UserDB instance = null;
	private static Map<String, User> users = new HashMap<String, User>();
	//Starter values DB
	private static User user1 = UserFactory.createUser("Test", "test", "test@test.com", "TestName", "TestFname", 21, "Female");
	private static User user2 = UserFactory.createUser("Test2", "test", "test2@test.com", "Test2Name", "Test2Fname", 22, "Male");
	private static User user3 = UserFactory.createUser("Test3", "test", "test3@test.com", "Test3Name", "Test3Fname", 28, "Female");
	private static User user4 = UserFactory.createUser("Test4", "test", "test4@test.com", "Test4Name", "Test4Fname", 21, "Female");
	private static ChatConversation conv = new ChatConversation(user1, user2);
	private static ChatMessage msg1 = new ChatMessage(user1, user2, "testing: hi!");
	private static ChatMessage msg2 = new ChatMessage(user2, user1, "testingBack: hi to you too!");
		
	public static UserDB getDB() {
		if (instance == null) {
			synchronized(UserDB.class) {
				if (instance == null) {
					instance = new UserDB();
					user1.addFriend(user2);
					user2.addFriend(user1);
					user1.addFriend(user3);
					user3.addFriend(user1);
					users.put(user1.getUsername(),user1);
					users.put(user2.getUsername(),user2);
					users.put(user3.getUsername(),user3);
					users.put(user4.getUsername(),user4);
					user1.addConversation(conv);
					user2.addConversation(conv);
					conv.addMessage(msg1);
					conv.addMessage(msg2);
					System.out.println("idUser4:" + user4.getId());
				}
			}
		}
		return instance;
	}
	
	public void add(User user) {
		if(user == null){
			throw new IllegalArgumentException("No user given");
		}
		if (users.containsKey(user.getUsername())) {
			throw new IllegalArgumentException("User already exists");
		}
		users.put(user.getUsername(), user);
	}

	public void delete(String username) {
		if(username == null){
			throw new IllegalArgumentException("No username given");
		}
		users.remove(username);
	}

	public User get(String username) {
		if(username == null){
			throw new IllegalArgumentException("No username given");
		}
		return users.get(username);
	}

	public List<User> getAll() {
		return new ArrayList<User>(users.values());	
	}
	
	public ChatConversation getConversation(String un1, String un2) {
		User user1 = this.get(un1);
		User user2 = this.get(un2);
		boolean alreadyHaveConversation = false;
		ChatConversation conv = null;
		for (ChatConversation conversation: user1.getConversations()) {
			if (conversation.getUsers()[0].equals(user2) || conversation.getUsers()[1].equals(user2) ) {
				alreadyHaveConversation = true;
				conv = conversation;
			}
		}
		if (!alreadyHaveConversation) {
			conv = new ChatConversation(user1, user2);
			user1.addConversation(conv);
			user2.addConversation(conv);
		}
		return conv;
	}

}
