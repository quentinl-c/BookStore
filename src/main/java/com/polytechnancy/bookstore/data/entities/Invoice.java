package com.polytechnancy.bookstore.data.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "invoice_number", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long invoiceNumber;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String address;
	
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<LineItem> items;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "invoice_date", nullable = false)
	private Date invoiceDate;
	
	public Invoice() {
		this.invoiceDate = new Date();
		this.name = new String();
		this.address = new String();
		this.items = new LinkedList<LineItem>();
	}
	
	public Invoice(String name, String address) {
		this.invoiceDate = new Date();
		this.name = name;
		this.address = address;
		this.items = new LinkedList<LineItem>();
	}
	
	public void add(LineItem line) {
		line.setInvoice(this);
		this.items.add(line);
	}

	public long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<LineItem> getItems() {
		return items;
	}

	public void setItems(List<LineItem> items) {
		this.items = items;
	}
	
	public Date getInvoiceDate() {
		return this.invoiceDate;
	}
	
	public void setInvoiceData(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	
}
