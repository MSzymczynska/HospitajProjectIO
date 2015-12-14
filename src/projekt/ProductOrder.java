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
public class ProductOrder extends ProductMovement{
    public String from;
    public String to;
    
    public ProductOrder(String id, ProductQuantity productQuantity, Date date, String from, String to)
    {
        super(id,productQuantity,date);
        this.from = from;
        this.to = to;
    }
}
