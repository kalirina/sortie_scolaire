package part_1;

import java.util.Map;
import java.util.HashMap;

public class RoomAssignment {
	private final Map<Student, Integer> assignments = new HashMap<>();

    public void assign(Student student, int room) {
        assignments.put(student, room);
    }
    
    public void swap(Student first, Student second) {
        int firstRoom = assignments.get(first);
        int secondRoom = assignments.get(second);

        assignments.put(first, secondRoom);
        assignments.put(second, firstRoom);
    }

    public int roomOf(Student student) {
        return assignments.get(student);
    }
}
