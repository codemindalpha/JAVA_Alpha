package testcases.CF012_USESENSITIVECLS__CWE492;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CF012_USESENSITIVECLS__CWE492_SensitiveGood_3 {

    // private member variables of CF012_USESENSITIVECLS__CWE492_SensitiveGood_3 class
    private String accountOwnerName;
    private String accountOwnerSSN;
    private int accountNumber;
    private double balance;

    // constructor for CF012_USESENSITIVECLS__CWE492_SensitiveGood_3 class
    public CF012_USESENSITIVECLS__CWE492_SensitiveGood_3(String accountOwnerName, String accountOwnerSSN,
                       int accountNumber, double initialBalance, int initialRate)
    {
        this.accountOwnerName = accountOwnerName;
        this.accountOwnerSSN = accountOwnerSSN;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.start(initialRate);
    }

// start method will add interest to balance every 30 days

    // creates timer object and interest adding action listener object
    public void start(final double rate)
    {

// InterestAdder is a local inner class

// that implements the ActionListener interface
        class InterestAdder implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {

// update interest
                double interest = CF012_USESENSITIVECLS__CWE492_SensitiveGood_3.this.balance * rate / 100;
                CF012_USESENSITIVECLS__CWE492_SensitiveGood_3.this.balance += interest;
            }
        }
        ActionListener adder = new InterestAdder();
        Timer t = new Timer(1000 * 3600 * 24 * 30, adder);
        t.start();
    }
}