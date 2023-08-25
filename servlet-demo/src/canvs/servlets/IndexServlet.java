package canvs.servlets;

import canvs.dao.impl.CustomerDAOImpl;
import canvs.pojo.Customer;
import canvs.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    private int pageNo = 0;
    private int pageCount = 5;
    private int tailPageNo = 0;
    private CustomerDAOImpl dao = new CustomerDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String number = request.getParameter("pageNo");
        if (Utils.isEmpty(number)) {
            this.pageNo = Integer.parseInt(number);
        }
        List<Customer> customerList = dao.getCustomerList(pageNo, pageCount);
        int count = dao.getCustomerCount();
        if (count > 0) {
            tailPageNo = count / 5;
        }
        request.getSession().setAttribute("tailPageNo", tailPageNo);
        request.getSession().setAttribute("pageNo", pageNo);
        request.getSession().setAttribute("pageCount",pageCount);
        request.getSession().setAttribute("customerList", customerList);
        this.processTemplate("index", request, response);
    }
}
