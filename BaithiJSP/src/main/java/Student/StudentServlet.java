package Student;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/students")
public class StudentServlet extends HttpServlet {
   private List<Student> studentList;
  @Override
  public void init() throws  ServletException{
      super.init();
      studentList = new ArrayList<>();
      studentList.add(new Student(1,"Hoang Minh Duong","HS001","12A1",8.5));
      studentList.add(new Student(2,"Nguyen Van A","HS012","12A1",8.0));
      studentList.add(new Student(3,"Nguyen Van B","HS015","12A2",8.8));
      studentList.add(new Student(4,"Hoang Van A","HS002","12A1",7.5));
      studentList.add(new Student(5,"Hoang Minh Anh","HS005","12A1",9.5));
      studentList.add(new Student(6,"Nguyen Van Dong","HS006","12A3",6.5));
      studentList.add(new Student(7,"Ta Van Chung","HS007","12A1",8.0));
  }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String action = request.getParameter("action");
        if (action==null){
            action = "list";
        }
        switch (action){
            case "new":
                showNewForm(request,response);
                break;
            case "create":
                createStudent(request,response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                deleteStudent(request,response);
                break;
            case "update":
                updateStudent(request,response);
                break;
            default:
                listStudents(request,response);
                break;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        doGet(request,response);
    }
    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.setAttribute("studentList",studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
        dispatcher.forward(request,response);
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        RequestDispatcher dispatcher =request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request,response);
    }
    private void createStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String name = request.getParameter("name");
        String maHS = request.getParameter("maHS");
        String classHS = request.getParameter("classHS");
        double point = Double.parseDouble(request.getParameter("point"));
        int id = studentList.size() +1;

        Student newStudent = new Student(id, name,maHS,classHS,point);
        studentList.add(newStudent);
        response.sendRedirect("students");
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = getStudentByID(id);
        request.setAttribute("student",student);
        RequestDispatcher dispatcher =request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request,response);
    }
    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String maHS = request.getParameter("maHS");
        String classHS = request.getParameter("classHS");
        double point = Double.parseDouble(request.getParameter("point"));

        Student student = getStudentByID(id);
        student.setName(name);
        student.setMaHS(maHS);
        student.setClassHS(classHS);
        student.setPoint(point);
        response.sendRedirect("students");
    }
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        int id= Integer.parseInt(request.getParameter("id"));
        Student student = getStudentByID(id);
        studentList.remove(student);
        response.sendRedirect("students");
    }
    private Student getStudentByID(int id){
        for(Student student : studentList){
            if (student.getId()==id){
                return student;
            }
        }
        return null;
    }
}
