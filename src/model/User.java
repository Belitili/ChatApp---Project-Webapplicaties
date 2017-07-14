package model;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

	private int id;
	private String username;
	private String status;
	private String password;
	private String email;
	private String name;
	private String fname;
	private int age;
	private String gender;
	private ArrayList<User> friends = new ArrayList<User>();
	private ArrayList<ChatConversation> conversations = new ArrayList<ChatConversation>();
	
	User(int id, String username, String password, String email, String name, String fname, int age, String gender) {
		setData(id, username, password, "offline", email, name, fname, age, gender);
	}
	User(int id, String username, String password, String status, String email, String name, String fname, int age, String gender) {
		setData(id, username, password, status, email, name, fname, age, gender);
	}
	
	private void setData(int id, String username, String password, String status, String email, String name, String fname, int age, String gender) {
		this.setId(id);
		this.setUsername(username);
		this.setPassword(password);
		this.setStatus(status);
		this.setEmail(email);
		this.setName(name);
		this.setFname(fname);
		this.setAge(age);
		this.setGender(gender);
	}
	public void addFriend(User friend) {
		friends.add(friend);
	}
	public ArrayList<User> getFriends() {
		return new ArrayList<User>(friends);
	}
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getEmail() {
		return email;
	}
	private void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public String getFname() {
		return fname;
	}
	private void setFname(String fname) {
		this.fname = fname;
	}
	public int getAge() {
		return age;
	}
	private void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	private void setGender(String gender) {
		this.gender = gender;
	}
	public void addConversation(ChatConversation conversation) {
		conversations.add(conversation);
	}
	public ArrayList<ChatConversation> getConversations() {
		return conversations;
	}
	
	public JSONObject getJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("id", this.getId());
        json.put("username", this.getUsername());
        json.put("status", this.getStatus());
        json.put("name", this.getName());
        json.put("fname", this.getFname());
        json.put("email", this.getEmail());
        json.put("password", this.getPassword());
        json.put("age", this.getAge());
        json.put("gender", this.getGender());
        return json;
    }
}
