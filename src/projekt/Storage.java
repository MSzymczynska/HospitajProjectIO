/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maciej
 */
public class Storage {
    private List<ProductQuantity> storage;

    public Storage() {
        this.storage = new ArrayList<>();
    }
    
    public boolean addToStorage(ProductQuantity productQuantity)
    {
        boolean flag = false;
        
        if(this.getStorage(productQuantity.product.id).isEmpty())
        {
            storage.add(productQuantity);
            flag = true;
        }
        else
        {
            for (ProductQuantity pq : storage) {
                if (pq.product.id.compareTo(productQuantity.product.id) == 0) {
                    pq.quantity += productQuantity.quantity;
                    flag = true;
                }
            }
        }
        
        return flag;
    }
    
    public List<ProductQuantity> getStorage()
    {
        return storage;
    }
    
    public List<ProductQuantity> getStorage(Integer productID)
    {
        List<ProductQuantity> result = new ArrayList<>();
        
        for (ProductQuantity pq : storage) {
                if (pq.product.id.compareTo(productID) == 0) {
                    result.add(pq);
                }
            }
        
        return result;
    }
    
    public List<ProductQuantity> getStorage(Product product)
    {
        List<ProductQuantity> result = new ArrayList<>();
        
        for (ProductQuantity pq : storage) {
                if (pq.product.compareTo(product) == 0) {
                    result.add(pq);
                }
            }
        
        return result;
    }
    
    public boolean removeFromStorage (ProductQuantity productQuantity)
    {
        boolean flag = false;
        
        for (ProductQuantity pq : storage) {
                if (pq.product.compareTo(productQuantity.product) == 0) {
                    pq.quantity -= productQuantity.quantity;
                    flag = true;
                }
            }
        return flag;
    }
    
    private boolean sendToDB()
    {
        return true;
    }
    
    private boolean getFromDB()
    {
        return true;
    }
    
    private void refresh()
    {
        
    }
}
