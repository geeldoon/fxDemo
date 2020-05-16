package home.dao;

import home.models.Student;
import home.util.DBUtil;
import javafx.scene.control.Alert;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {
    private static Connection connection;
    public StudentDAO(){
        connection = DBUtil.connect();
    }
    private Statement stm;


    public void createStudent(Student student){
        //Sql Query
        try{


            String sql = "Insert into students (id, name, level, secion, gender, languages) values " +
                    "("+student.getId() +",'" + student.getName() +"', '" + student.getLevel() + "', '" + student.getClassName() +"', '"
                    +student.getGender() + "', '" + (student.getLanguages()[0] != null ? student.getLanguages()[0] : null)+","+(student.getLanguages()[1] != null ? student.getLanguages()[1] : null)+"')";
            System.out.println(sql);

            //create statement
            stm = connection.createStatement();
            //execute the statement
            int rows = stm.executeUpdate(sql);
            if(rows > 0){
                Alert a = new Alert(Alert.AlertType.INFORMATION, rows + " Saved");
                a.show();
            }

            stm.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> readStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        stm = connection.createStatement();

        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()){
            Student student = convertToStudent(rs);
            students.add(student);
        }

        return students;
    }

    public int updateStudent(Student student) throws SQLException {
        String sql ="UPDATE students set name=?, id=?, level=?, secion=? where id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(2, student.getId());
        ps.setString(1, student.getName());
        ps.setString(3, student.getLevel());
        ps.setString(4, student.getClassName());
        ps.setInt(5, student.getId());

        int r = ps.executeUpdate();

        System.out.println(r);
        return r;
    }

    public int deleteStudent(Student student) throws SQLException {
        String sql = "Delete from students where id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, student.getId());
        int r = ps.executeUpdate();
        return  r;


    }


    private Student convertToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setClassName(rs.getString("secion"));
        student.setLevel(rs.getString("level"));

        return student;

    }



}
