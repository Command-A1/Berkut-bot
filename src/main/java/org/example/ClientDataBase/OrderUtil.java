package org.example.ClientDataBase;

public interface OrderUtil {
    public void createNewOrder();
    public int createId();
    public void adDish(String nameDish);
    public void deleteDish(String nameDish);
    public String showAllDish();
    public void orderProcessing();
    public void orderAcceptance();
    public String AddWaiter();
}
