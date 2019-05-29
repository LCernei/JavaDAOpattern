import java.util.ArrayList;
import java.util.Calendar;

public class Horoscop {

    public static void main(String[] args) {
        StudentDao studentDao = new StudentDaoJDBC();

        ArrayList<Student> list = null;

//        Student s1 = new Student(0, "Ion", 2.3f);
//        Student s2 = new Student(1, "Andrei", 7.8f);
//        Student s3 = new Student(2, "Eugen", 9.10f);
//
//        studentDao.createStudent(s1);
//        studentDao.createStudent(s2);
//        studentDao.createStudent(s3);

//        studentDao.deleteStudent(s1);
//        studentDao.deleteStudent(s2);
//        studentDao.deleteStudent(s3);

        Student s0 = studentDao.getStudent(0);
        Student s1 = studentDao.getStudent(1);
        Student s2 = studentDao.getStudent(2);

        s0.setMedie(6.7f);
        studentDao.updateStudent(s0);

        System.out.println("Student with id 0:");
        System.out.println(studentDao.getStudent(0));

        System.out.println("\nAll students:");
        list = studentDao.getStudents();
        for (Student student: list) {
            System.out.println(student);
        }

        System.out.println("\n" + preziceMedie(s0));
        System.out.println("\n" + preziceZi(s0));
        System.out.println("\n" + preziceNrStudentiLenesi(list));

    }

    public static String preziceMedie(Student student) {
        if (student.getNrMatricol() + student.getNume().length() % 2 == 1)
            return "Media va creste!";
        return "Media va scadea!";
    }

    public static String preziceZi(Student student) {
        int day = Calendar.getInstance().get(Calendar.DATE);
        int firstLetter = Character.getNumericValue(student.getNume().charAt(0));
        if (day + firstLetter % 2 == 1)
            return "Vei avea o zi buna!";
        return "Vei avea o zi rea!";
    }

    public static int preziceNrStudentiLenesi(ArrayList<Student> lst) {
        int count = 0;
        for (Student student : lst) {
            if (student.getMedie() > 8 && preziceMedie(student).equals("Media va scadea!"))
                count++;
        }
        return count;
    }
}
