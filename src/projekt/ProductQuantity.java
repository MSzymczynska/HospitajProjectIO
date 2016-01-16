/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

/**
 *
 * @author Maciej
 */
public class ProductQuantity {
    public Product product;
    public int quantity;
    
    public ProductQuantity(Product product, int quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }
    public ProductQuantity() {
		// TODO Auto-generated constructor stub
	}
	public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    @Override 
    public String toString() {
    	return this.product.toString() + " [" + this.quantity + "]\n";
    }
    
}
