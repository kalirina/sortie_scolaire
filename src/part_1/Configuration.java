package part_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {
	private int n;
	private List<Student> students;
	private List<List<Character>> relations;
	private Map<Character, List<Integer>> preferences;

	public int get_n() {
		return n;
	}

	public List<Character> get_student_ids() {
		List<Character> ids = new ArrayList<>();
		for (Student student : students) {
			ids.add(student.get_id());
		}
		return ids;
	}

	public List<Student> get_students() {
		return List.copyOf(students);
	}

	public List<List<Character>> get_r() {
		return List.copyOf(relations);
	}

	public Map<Character, List<Integer>> get_p() {
		return Map.copyOf(preferences);
	}

	public boolean demarrer(int n) {
		if (n <= 0 || n >= 27)
			return false;

		this.n = n;
		relations = new ArrayList<>();
		preferences = new HashMap<>();
		students = new ArrayList<>();

		for (char id = 'A'; id < 'A' + n; id++) {
			students.add(new Student(id, List.of()));
		}
		return true;
	}

	private boolean containsStudent(char id) {
		for (Student student : students) {
			if (student.get_id() == id)
				return true;
		}
		return false;
	}

	public boolean new_relation(String line) {
		if (line == null || line.trim().isEmpty() || line.trim().equals("0")) {
			return false;
		}

		String[] pers = line.trim().split("\\s+");
		if (pers.length != 2 || pers[0].length() != 1 || pers[1].length() != 1) {
			return false;
		}

		char p1 = pers[0].toUpperCase().charAt(0);
		char p2 = pers[1].toUpperCase().charAt(0);

		if (p1 == p2 || !containsStudent(p1) || !containsStudent(p2)) {
			return false;
		}
		if (relations.contains(List.of(p1, p2)) || relations.contains(List.of(p2, p1))) {
			return false;
		}

		relations.add(List.of(p1, p2));
		return true;
	}

	public boolean new_preference(String line) {
		if (line == null || line.trim().isEmpty() || line.trim().equals("0")) {
			return false;
		}

		String[] all = line.trim().split("\\s+");
		if (all.length != n + 1 || all[0].length() != 1) {
			return false;
		}

		char person = Character.toUpperCase(all[0].charAt(0));
		if (!containsStudent(person)) {
			return false;
		}

		List<Integer> prefs = new ArrayList<>();
		for (int i = 1; i < all.length; i++) {
			int room = Integer.parseInt(all[i]);
			if (room < 1 || room > n || prefs.contains(room)) {
				return false;
			}
			prefs.add(room);
		}

		preferences.put(person, prefs);
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).get_id() == person) {
				students.set(i, new Student(person, prefs));
				break;
			}
		}
		return true;
	}

	public boolean ready_to_construction() {
		return preferences.size() == n;
	}
}