package canvs.servlets;

import canvs.dao.CustomerDAO;
import canvs.dao.impl.CustomerDAOImpl;
import canvs.exception.DateException;
import canvs.pojo.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@WebServlet("/add")
public class AddServlet extends ViewBaseServlet{
    private CustomerDAO dao = new CustomerDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processTemplate("add",req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String birth = req.getParameter("birth");
        double salary = Double.parseDouble(req.getParameter("salary"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(birth);
            Customer cust = new Customer(name,email,new java.sql.Date(date.getTime()),salary);
            dao.insertCustomer(cust);
            resp.sendRedirect("index");
        } catch (ParseException e) {
            try {
                throw new DateException("时间格式错误！");
            } catch (DateException ex) {
                ex.printStackTrace();
            }
        }
    }
}
