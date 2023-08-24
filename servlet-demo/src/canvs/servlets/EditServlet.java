package canvs.servlets;

import canvs.dao.CustomerDAO;
import canvs.dao.impl.CustomerDAOImpl;
import canvs.exception.DateException;
import canvs.pojo.Customer;
import canvs.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

@WebServlet("/edit")
public class EditServlet extends ViewBaseServlet {
    private CustomerDAO dao = new CustomerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Customer cust = dao.getCustomerById(Integer.parseInt(id));
        req.setAttribute("cust", cust);
        this.processTemplate("edit", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String birth = req.getParameter("birth");
        String salary = req.getParameter("salary");
        if (Utils.isEmpty(id) && Utils.isEmpty(name) && Utils.isEmpty(email) && Utils.isEmpty(birth) && Utils.isEmpty(salary)) {
            try {
                Date date = Utils.dateFormat(birth);
                Customer cust = new Customer(Integer.parseInt(id), name, email, date, Double.parseDouble(salary));
                dao.updateCustomerById(cust);
                resp.sendRedirect("index");
            } catch (ParseException e) {
                throw new RuntimeException("时间格式有误....");
            }
        } else {
            try {
                throw new DateException("时间格式错误！");
            } catch (DateException e) {
                e.printStackTrace();
            }
        }
    }
}
