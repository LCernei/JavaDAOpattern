import java.util.ArrayList;

public interface StudentDao {
    public void createStudent(Student student);

    public ArrayList<Student> getStudents();
    public Student getStudent(int id);

    public void updateStudent(Student student);
    public void deleteStudent(Student student);
}
