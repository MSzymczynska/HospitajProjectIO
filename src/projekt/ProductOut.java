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
public class ProductOut extends ProductMovement{
    public String to;
    
    
    public ProductOut(String id, ProductQuantity productQuantity, Date date, String to)
    {
        super(id,productQuantity,date);
        this.to = to;
    }
}
