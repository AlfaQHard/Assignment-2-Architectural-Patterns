import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CashRegister extends JFrame{
    private int pid;
    private double totalPrice;
    private String barcode;
    Keyboard k;
    Display d;

    public CashRegister() {
        k = new Keyboard();
        d = new Display();

        // Layout Setup
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Horizontal Layout
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(d.dp)
                .addComponent(k.price)
                .addComponent(k.code)
                .addComponent(k.kb));
        // Vertical Layout
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(d.dp)
                .addComponent(k.price)
                .addComponent(k.code)
                .addComponent(k.kb));

        // Frame Settings
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        pack();

        // ActionListener for enter button on keyboard
        k.enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                barcode = k.code.getText();
                k.code.setText("");
                searchID(barcode);
            }
        });

        // ActionListener for delete button on keyboard
        k.delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (totalPrice != 0) {
                    totalPrice -= Double.parseDouble(d.model.getValueAt(d.model.getRowCount() - 1, d.model.findColumn("Price")).toString());
                    k.price.setText(String.valueOf(totalPrice));
                }
                d.deleteRow();
            }
        });

        // ActionListener for purchase button on keyboard
        k.purchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Items purchased for $" + totalPrice);
                d.model.setRowCount(0);
                totalPrice = 0;
                k.price.setText(String.valueOf(totalPrice));
            }
        });

    }

    // Search given id in database (text file)
    public void searchID(String pid) {
        try {
            File prod = new File("list.txt");
            Scanner read = new Scanner(prod);
            String line = "";
            String prodID = "";

            while (read.hasNextLine()) {
                line = read.nextLine();
                prodID = line.substring(0,4);
                line = line.substring(5);

                if (prodID.equals(pid)) {
                    break;
                }
            }
            // Get product info from line
            String name = line.substring(0, line.indexOf(','));
            line = line.substring(line.indexOf(',') + 1);
            String price = line;

            // Adds product to table
            if (prodID.equals(pid)) {
                d.addItems(pid, name, price);
                totalPrice += Double.parseDouble(price);
                k.price.setText(String.valueOf(totalPrice));
            }
            else {
                JOptionPane.showMessageDialog(null, "Product Not Found");
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "List not found");
        }
    }
}
