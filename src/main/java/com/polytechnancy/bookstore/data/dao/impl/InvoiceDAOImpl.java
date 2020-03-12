package com.polytechnancy.bookstore.data.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.polytechnancy.bookstore.data.dao.InvoiceDAO;
import com.polytechnancy.bookstore.data.entities.Book;
import com.polytechnancy.bookstore.data.entities.Invoice;
import com.polytechnancy.bookstore.data.entities.LineItem;

@Stateless
public class InvoiceDAOImpl implements InvoiceDAO {

	@PersistenceContext(unitName="BookstorePU")
	private EntityManager em;
	
	public void create(String name, String address, List<LineItem> items) {
		Invoice invoice = new Invoice(name, address);

		for(LineItem item : items) {
			LineItem itemCopy = new LineItem();
			itemCopy.setBook(em.find(Book.class, item.getBook().getIsbn()));
			itemCopy.setQuantity(item.getQuantity());
			invoice.add(itemCopy);
		}
		
		em.persist(invoice);
	}
}
