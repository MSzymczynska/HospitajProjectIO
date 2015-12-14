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
public class ProductMovement {
    public String id;
    public ProductQuantity productQuantity;
    public Date date;
    
    public ProductMovement(String id, ProductQuantity productQuantity, Date date)
    {
        this.id = id;
        this.productQuantity = productQuantity;
        this.date = date;
    }
}
