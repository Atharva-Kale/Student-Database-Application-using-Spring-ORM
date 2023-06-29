package org.example;

import org.dao.StudentDao;
import org.entities.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
        StudentDao studentDao=context.getBean("studentDao", StudentDao.class);
//        Student student=new Student(1,"Atharva Kale","thane");
//        int r=studentDao.insert(student);
//        System.out.println(r);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        boolean go=true;
        while(go) {
            System.out.println("PRESS 1 to add new student");
            System.out.println("PRESS 2 to display all students");
            System.out.println("PRESS 3 to get detail of single student");
            System.out.println("PRESS 4 to delete students");
            System.out.println("PRESS 5 to update student");
            System.out.println("PRESS 6 to exit");

            try {
                int input = Integer.parseInt(br.readLine());
                switch (input) {
                    case 1:
                        System.out.println("Enter user id: ");
                        int uid = Integer.parseInt((br.readLine()));

                        System.out.println("Enter user name: ");
                        String uName = br.readLine();

                        System.out.println("Enter user city: ");
                        String uCity = br.readLine();

                        Student s = new Student();
                        s.setStudentId(uid);
                        s.setStudentName(uName);
                        s.setStudentCity(uCity);
                        int r = studentDao.insert(s);
                        System.out.println(r + "student added");
                        System.out.println("------------------");
                        System.out.println();
                        break;

                    case 2:
                        System.out.println("-----------------------");
                        List<Student> allStudents = studentDao.getAllStudents();
                        for (Student st : allStudents) {
                            System.out.println("Id: " + st.getStudentId());
                            System.out.println("Name: " + st.getStudentName());
                            System.out.println("City: " + st.getStudentCity());
                        }
                        System.out.println("--------------------");
                        break;

                    case 3:
                        System.out.println("Enter user id: ");
                        int userId = Integer.parseInt((br.readLine()));
                        Student student = studentDao.getStudent(userId);
                        System.out.println("Id: " + student.getStudentId());
                        System.out.println("Name: " + student.getStudentName());
                        System.out.println("City: " + student.getStudentCity());
                        break;

                    case 4:
                        System.out.println("Enter user id: ");
                        int id = Integer.parseInt((br.readLine()));
                        studentDao.deleteStudent(id);
                        System.out.println("Student deleted...");
                        break;

                    case 5:
                        System.out.println("Enter the Student id to update: ");
                        int updId = Integer.parseInt(br.readLine());
                        System.out.println("PRESS 1 to update Name");
                        System.out.println("PRESS 2 to update City");
                        int nameOrCity = Integer.parseInt(br.readLine());
                        Student student1 = studentDao.getStudent(updId);
                        String updatedName = student1.getStudentName();
                        String updatedCity = student1.getStudentCity();

                        switch (nameOrCity) {
                            case 1:
                                System.out.println("Enter the Name to be updated: ");
                                updatedName = br.readLine();
                                student1 = new Student(updId, updatedName, updatedCity);
                                break;
                            case 2:
                                System.out.println("Enter the City to be updated: ");
                                updatedCity = br.readLine();
                                student1 = new Student(updId, updatedName, updatedCity);
                                break;
                        }
                        studentDao.updateStudent(student1);
                        System.out.println("Updated student Details Successfully!");
                        break;
                    case 6:

                        go = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input try again");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            System.out.println("Thank you for using my application");
        }
    }

}
