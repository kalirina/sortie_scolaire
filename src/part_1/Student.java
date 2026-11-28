package part_1;

import java.util.List;

public class Student {
	private final char id;
	private final List<Integer> preferences;
	
	public Student(char id, List<Integer> preferences) {
		this.id = id;
		this.preferences = preferences;
	}
	
	public char get_id() {
		return id;
	}
	public List<Integer> get_preferences() {
		return List.copyOf(preferences);
	}
}
