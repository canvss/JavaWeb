package canvs.servlets;

import canvs.dao.CustomerDAO;
import canvs.dao.impl.CustomerDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/delete")
public class DeleteServlet extends ViewBaseServlet{
    private CustomerDAO dao = new CustomerDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.delCustomerById(id);
        response.sendRedirect("index");
    }
}
