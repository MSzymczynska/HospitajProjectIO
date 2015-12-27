/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import view.MainPanel;

/**
 *
 * @author Maciej
 */
public class StorageMainPanel {

    private Storage storage;
    private StorageArchive archive;
    private float moneyNeeded;
    private List<ProductOrder> ordersFromOutside;
    public HospitalPharmacy hp;

    public StorageMainPanel(HospitalPharmacy hp) {
    	this.hp=hp;
        this.ordersFromOutside = new ArrayList<ProductOrder>();
        ordersFromOutside.add(new ProductOrder("12", new ProductQuantity(new Product(1,"NAME1","PRODUCER1",new Date()), 10), new Date(), "Kuchnia", "Magazyn"));
        
        this.archive = new StorageArchive();
        
        this.storage = new Storage();
        storage.addToStorage(new ProductQuantity(new Product(1,"NAME1","PRODUCER1",new Date()), 5));
        storage.addToStorage(new ProductQuantity(new Product(2,"NAME2","PRODUCER2",new Date()), 7));
    }

    /*public static void main(String[] args) {
    }*/

    public boolean logIn() {
        return true;
    }

    public boolean logOut() {
        return true;
    }

    public void exit() {

    }

    public boolean showStorage() {
        return true;
    }

    public boolean showArchive() {
        return true;
    }

    public boolean giveOut(ProductQuantity productQuantity, String to) {
        boolean flag = false;

        if (storage.getStorage(productQuantity.product.id).isEmpty()) {
            flag = false;
        } else {
            if (storage.getStorage(productQuantity.product.id).get(0).quantity < productQuantity.quantity) {
                flag = false;
            } else {
                storage.removeFromStorage(productQuantity);
                
                if(to.compareTo("Kuchnia") == 0)
                {
                    
                }
                else if(to.compareTo("Apteka") == 1)
                {
                    Medicine med = new Medicine(3, productQuantity.product.name, "prod3", null);
                    med.quantity=productQuantity.quantity;
                    this.hp.addMedicineToPharmecyList(med);
                    
                    
                }
                
                String id = Integer.toString(Integer.parseInt(archive.getArchive().get(archive.getArchive().size() - 1).id) + 1);
                ProductOut p = new ProductOut(id, productQuantity, new Date(), to);
                archive.addToArchive(p);

                flag = true;
            }
        }

        return flag;
    }

    public boolean takeIn(ProductQuantity productQuantity, String from) {
        boolean flag = false;

        storage.addToStorage(productQuantity);
        
        String id = "1";
        
        if(!archive.getArchive().isEmpty())
        {
            id = Integer.toString(Integer.parseInt(archive.getArchive().get(archive.getArchive().size() - 1).id) + 1);
        }
        
        ProductIn p = new ProductIn(id, productQuantity, new Date(), from);
        
        archive.addToArchive(p);

        return true;
    }

    public float order(ProductMovement productMovement, String from) {
        archive.addToArchive(productMovement);
        //    public ProductIn(String id, ProductQuantity productQuantity, Date date, String from)
        takeIn(productMovement.productQuantity, from);
        return 1;
    }
    
    public boolean orderFromOutside(ProductQuantity pq, String from)
    {
    	//to ponizej z nullami nie dzialalo, ale z danymi zadzia³a³o xD
        //ordersFromOutside.add(new ProductOrder(null,pq,null,from,"Magazyn"));
        ordersFromOutside.add(new ProductOrder("19", pq, new Date(), from, "Magazyn"));
    	return true;
    }

    public List<ProductOrder> getOFO()
    {
        return ordersFromOutside;
    }
    
    public List<ProductQuantity> getStorage()
    {
        return storage.getStorage();
    }
    
    public List<ProductMovement> getArchive()
    {
        return archive.getArchive();
    }
    
    public float getMoneyNeeded() {
        return moneyNeeded;
    }
}
