import java.sql.*;
import java.util.ArrayList;

public class StudentDaoJDBC implements StudentDao{
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/hr?useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "lc";
    private static final String PASSWORD = "qwe";

//CREATE TABLE students(id int primary key,name varchar(30),averageMark decimal(4,2));

    public void createStudent(Student student){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME,PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO students VALUES(?,?,?)");
            preparedStatement.setInt(1, student.getNrMatricol());
            preparedStatement.setString(2, student.getNume());
            preparedStatement.setFloat(3, student.getMedie());
            boolean b=preparedStatement.execute();
            if(b==true)
                System.out.println("1 record inserted...");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException clsNotFoundEx) {
            clsNotFoundEx.printStackTrace();
            System.exit(1);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                System.exit(1);
            }
        }
    }

    public void updateStudent(Student student){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME,PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE students SET averageMark=? WHERE id=?");
            preparedStatement.setFloat(1, student.getMedie());
            preparedStatement.setInt(2, student.getNrMatricol());
            boolean b=preparedStatement.execute();
            if(b==true)
                System.out.println(student.getMedie()+" is the new mark for id="+student.getNrMatricol());
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException clsNotFoundEx) {
            clsNotFoundEx.printStackTrace();
            System.exit(1);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                System.exit(1);
            }
        }
    }

    public void deleteStudent(Student student){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME,PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM students WHERE id=?");
            preparedStatement.setInt(1, student.getNrMatricol());
            boolean b=preparedStatement.execute();
            if(b==true)
                System.out.println("1 record deleted...");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException clsNotFoundEx) {
            clsNotFoundEx.printStackTrace();
            System.exit(1);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                System.exit(1);
            }
        }
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> studentList = null;
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME,
                    PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            ResultSetMetaData metaData = resultSet.getMetaData();
            studentList = new ArrayList<Student>();

            while (resultSet.next()) {
                int nrMatricol = resultSet.getInt(1);
                String nume = resultSet.getString(2);
                float medie = resultSet.getFloat(3);
                studentList.add(new Student(nrMatricol, nume, medie));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException clsNotFoundEx) {
            clsNotFoundEx.printStackTrace();
            System.exit(1);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                System.exit(1);
            }
        }
        return studentList;
    }

    public Student getStudent(int id) {
        Student student = null;
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students WHERE id="+id);
            ResultSetMetaData metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                int nrMatricol = resultSet.getInt(1);
                String nume = resultSet.getString(2);
                float medie = resultSet.getFloat(3);
                student = new Student(nrMatricol, nume, medie);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException clsNotFoundEx) {
            clsNotFoundEx.printStackTrace();
            System.exit(1);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                System.exit(1);
            }
        }
        return student;
    }
}
