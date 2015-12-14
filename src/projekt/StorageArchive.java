/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Maciej
 */
public class StorageArchive {
    private List<ProductMovement> archive;

    public StorageArchive() {
        this.archive = new ArrayList<>();
    }
    
    public boolean addToArchive(ProductMovement productMovement)
    {
        archive.add(productMovement);
        return true;
    }
    
    public List<ProductMovement> getArchive()
    {
        return archive;
    }
    
    public List<ProductMovement> getArchive(String productName)
    {
        List<ProductMovement> result = new ArrayList<>();
        
        for (ProductMovement pm : archive) {
                if(pm.productQuantity.product.name.compareTo(productName) == 0)
                {
                    result.add(pm);
                }
            }
        
        return result;
    }
    
    public List<ProductMovement> getArchive(Product product)
    {
        List<ProductMovement> result = new ArrayList<>();
        
        for (ProductMovement pm : archive) {
                if(pm.productQuantity.product.compareTo(product) == 0)
                {
                    result.add(pm);
                }
            }
        
        return result;
    }
    
    public List<ProductMovement> getArchive(Date date)
    {
        List<ProductMovement> result = new ArrayList<>();
        
        for (ProductMovement pm : archive) {
                if(pm.date.compareTo(date) == 0)
                {
                    result.add(pm);
                }
            }
        
        return result;
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
