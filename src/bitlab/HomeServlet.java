package bitlab;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value= "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Zhanyla", "Z", "IT", 500000));
        employees.add(new Employee("Ikram", "I", "IT", 500000));
        employees.add(new Employee("Kobe", "Bryant", "Player", 500000));
        employees.add(new Employee("Tobi", "M", "Actor", 500000));
        employees.add(new Employee("Tanya", "M", "Manager", 500000));
        employees.add(new Employee("Sasha", "S", "HR", 500000));
        employees.add(new Employee("Diana", "D", "Recrutor", 500000));

        out.print("<table>");
        out.print("<thead>");
        out.print("<th>NAME</th>");
        out.print("<th>SURNAME</th>");
        out.print("<th>DEPARTMENT</th>");
        out.print("<th>SALARY</th>");
        out.print("</thead>");
        out.print("<tbody>");
        for(Employee employee : employees){
            out.print("<tr>");

        }
        out.print("</tbody>");
        out.print("</table>");

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String department = req.getParameter("department");
        String salaryStr = req.getParameter("salary");
        Integer salary = Optional.ofNullable(salaryStr)
                .filter(value -> !value.isEmpty())
                .map(Integer::valueOf)
                .orElse(null);

        // Если имя или фамилия null, то нет смысла продолжать текущий метод
        if (name != null && !name.isEmpty() && surname != null && !surname.isEmpty()) {
            Employee employee = new Employee(name, surname, department, salary);
            DbManager.addEmployee(employee);
        }

        resp.sendRedirect("/home");
    }
}
