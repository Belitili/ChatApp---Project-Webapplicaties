package model;

public class UserFactory {
	
	private static int idCounter = 1;
	
	public static User createUser(String username, String password, String email, String name, String fname, int age, String gender) {
		return new User(idCounter++, username, password, email, name, fname, age, gender);
	}
	
	public static User createUser(String username, String password, String status, String email, String name, String fname, int age, String gender) {
		return new User(idCounter++, username, password, status, email, name, fname, age, gender);
	}

}
