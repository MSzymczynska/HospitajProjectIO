/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.Date;

/**
 *
 * @author Maciej
 */
public class Product {
    public Integer id;
    public String name;
    public String producer;
    public Date expirationDate;
    
    public Product(String name) {
        this.name = name;
    }
    public Product(int id, String name, String producer, Date expDate)
    {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.expirationDate = expDate;
    }
    public Product() {
		// TODO Auto-generated constructor stub
	}
	public String getProducer() {
        return producer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    int compareTo(Product product) {
        return (Integer)(this.id.compareTo(product.id));
    }
    
    @Override 
    public String toString() {
    	return this.name;
    }
}
