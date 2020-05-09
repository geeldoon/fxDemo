package home.dao;

import home.models.Student;
import home.util.DBUtil;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO {
    private static Connection connection;
    public StudentDAO(){
        connection = DBUtil.connect();
    }


    public void createStudent(Student student){
        //Sql Query
        try{


            String sql = "Insert into students (id, name, level, secion, gender, languages) values " +
                    "("+student.getId() +",'" + student.getName() +"', '" + student.getLevel() + "', '" + student.getClassName() +"', '"
                    +student.getGender() + "', '" + student.getLanguages()[0] +"')";
            System.out.println(sql);

            //create statement
            Statement stm = connection.createStatement();
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
}
