/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *
 * @author Maciej
 */
public class StorageMainPanel {


    public Storage storage;
    public StorageArchive archive;
    private float moneyNeeded;
    public List<ProductOrder> ordersFromOutside;
    public HospitalPharmacy hp;
    public KitchenPanel k;

    public StorageMainPanel(HospitalPharmacy hp) {
    	this.hp=hp;
        this.ordersFromOutside = new ArrayList<ProductOrder>();
        this.archive = new StorageArchive(this);
        this.storage = new Storage();
    }

    /*public static void main(String[] args) {
    }*/

    public boolean giveOut(ProductQuantity productQuantity, String to) {
        boolean flag = false;

        if (storage.getStorage(productQuantity.product.id).isEmpty() || storage.getStorage(productQuantity.product.id).get(0).quantity < productQuantity.quantity) {
            flag = false;
            }
        else {
                storage.removeFromStorage(productQuantity);
                
                if(to.equals("Kuchnia"))
                {
                    KitchenPanel.getInstance().addProductQuantity(productQuantity);
                }
                else if(to.equals("Apteka"))
                {
                  Medicine med = new Medicine(3, productQuantity.product.name, "prod3", null);
                  med.quantity=productQuantity.quantity;
          		  System.out.println(med.name);
          		  hp.addMedicineToPharmecyList(med);
                                                           
                }
                
                String id = "1";
                
                if(!archive.getArchive().isEmpty())
                {
                    id = Integer.toString(Integer.parseInt(archive.getArchive().get(archive.getArchive().size() - 1).id) + 1);
                }ProductOrder p = new ProductOrder(id, productQuantity, new Date(), "Magazyn", to);
                archive.addToArchive(p);

                flag = true;
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
        
        ProductOrder p = new ProductOrder(id, productQuantity, new Date(), from, "Magazyn");
        
        archive.addToArchive(p);

        return true;
    }

    public float order(ProductMovement productMovement, String from) {
        moneyNeeded += 10 * productMovement.productQuantity.quantity;
        String tmpID = "0";
		for (int i = 1; i < ordersFromOutside.size(); i++) {
			if (Integer.parseInt(ordersFromOutside.get(i).id) > Integer.parseInt(tmpID))
				tmpID = ordersFromOutside.get(i).id;
		}
		tmpID = String.valueOf(Integer.parseInt(tmpID) + 1);
        ordersFromOutside.add(new ProductOrder(tmpID, productMovement.productQuantity, new Date(), "Sklep", "Magazyn"));
        archive.addToArchive(productMovement);
        return 1;
    }
    
    public boolean orderFromOutside(ProductQuantity pq, String from)
    {
    	//to ponizej z nullami nie dzialalo, ale z danymi zadzia³a³o xD
        //ordersFromOutside.add(new ProductOrder(null,pq,null,from,"Magazyn"));
		String tmpID = "0";
		for (int i = 1; i < ordersFromOutside.size(); i++) {
			if (Integer.parseInt(ordersFromOutside.get(i).id) > Integer.parseInt(tmpID))
				tmpID = ordersFromOutside.get(i).id;
		}
		tmpID = String.valueOf(Integer.parseInt(tmpID) + 1);
		ordersFromOutside.add(new ProductOrder(tmpID, pq, new Date(), from, "Magazyn"));
		archive.sendToDB();
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
