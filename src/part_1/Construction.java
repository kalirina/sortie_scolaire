package part_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Construction {
	private int n;
	private int score;
	private List<Student> students;
	private List<List<Character>> relations;
	private Map<Character, List<Integer>> preferences;
	private RoomAssignment roomAssignment;

	public int get_n() {
		return n;
	}
	public int get_sc() {
		return score;
	}
	public List<Character> get_s() {
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
		return relations;
	}
	public Map<Character, List<Integer>> get_p() {
		return preferences;
	}
	public RoomAssignment get_room_assignment() {
		return roomAssignment;
	}

	public Construction(int n, List<Student> students, List<List<Character>> relations,
			Map<Character, List<Integer>> preferences) {
		this.n = n;
		this.students = students;
		this.relations = relations;
		this.preferences = preferences;
		this.roomAssignment = new RoomAssignment();
	}

	public void solution_naive() {
		List<Integer> rooms = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			rooms.add(i);
		}

		for (Student student : students) {
			List<Integer> preference = student.get_preferences();
			for (int room : preference) {
				if (rooms.contains(room)) {
					roomAssignment.assign(student, room);
					rooms.remove(Integer.valueOf(room));
					break;
				}
			}
		}
	}

	public boolean changer_chambres(String line) {
		if (line == null || line.trim().isEmpty() || line.trim().equals("0")) {
			return false;
		}

		String[] pers = line.trim().split("\\s+");
		if (pers.length != 2 || pers[0].length() != 1 || pers[1].length() != 1) {
			return false;
		}

		char p1 = Character.toUpperCase(pers[0].charAt(0));
		char p2 = Character.toUpperCase(pers[1].charAt(0));
		if (p1 == p2) {
			return false;
		}

		Student s1 = findStudent(p1);
		Student s2 = findStudent(p2);
		if (s1 == null || s2 == null) {
			return false;
		}

		roomAssignment.swap(s1, s2);
		calculer_score();
		return true;
	}

	private Student findStudent(char id) {
		for (Student student : students) {
			if (student.get_id() == id) {
				return student;
			}
		}
		return null;
	}

	public void calculer_score() {
		score = 0;
		for (Student student : students) {
			char p = student.get_id();
			int c = roomAssignment.roomOf(student);
			List<Integer> pr = student.get_preferences();

			if (c == pr.get(0)) {
				continue;
			}

			List<Character> jalouseDe = new ArrayList<>();
			for (List<Character> pair : relations) {
				if (pair.contains(p)) {
					int index = pair.indexOf(p);
					jalouseDe.add(pair.get(index == 0 ? 1 : 0));
				}
			}

			int indexOfRoom = pr.indexOf(c);
			boolean jealous = false;
			for (int k = 0; k < indexOfRoom; k++) {
				int preferredRoom = pr.get(k);
				for (char fou : jalouseDe) {
					Student jealousStudent = findStudent(fou);
					if (jealousStudent != null
							&& roomAssignment.roomOf(jealousStudent) == preferredRoom) {
						score++;
						jealous = true;
						break;
					}
				}
				if (jealous)
					break;
			}

		}
	}
}