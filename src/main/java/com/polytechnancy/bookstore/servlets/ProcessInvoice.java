package com.polytechnancy.bookstore.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.polytechnancy.bookstore.business.Cart;
import com.polytechnancy.bookstore.business.PurchaseInfo;
import com.polytechnancy.bookstore.data.dao.InvoiceDAO;
import com.polytechnancy.bookstore.data.entities.LineItem;

/**
 * Servlet implementation class ProcessInvoice
 */
@WebServlet("/ProcessInvoice")
public class ProcessInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String url = "buy";
    
	private final String urlForm = "/WEB-INF/checkout.jsp";

	@EJB
	private InvoiceDAO invoiceDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessInvoice() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = new String();

		final String name = request.getParameter("name");
		final String address = request.getParameter("address");
		
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		if (cart == null) {
			cart = new Cart();
		}
		
		if (name.isEmpty() || address.isEmpty()) {
			error = "Please, full fill the name and the address fiels";
		} else if (cart.isEmpty()) {
			error = "Your cart is empty";
		}
		
		if (error.isEmpty()) {
			List<LineItem>items = cart.getItems();
			invoiceDAO.create(name, address, items);
			
			double totalAmount = cart.getTotal();
			
			PurchaseInfo info = new PurchaseInfo(name, address, totalAmount);
			// Clear the session
			session.setAttribute("cart", new Cart());
			
			session.setAttribute("info", info);
			
			response.sendRedirect(this.url);
		} else {

			request.setAttribute("error", error);
			request.setAttribute("name", name);
			request.setAttribute("address", address);
			
			getServletContext()
			.getRequestDispatcher(this.urlForm).
			forward(request, response);
		}
		
	}

}
