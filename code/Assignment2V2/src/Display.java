import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Display extends JPanel{
    JPanel dp;
    final DefaultTableModel model;
    public Display() {
        dp = new JPanel();
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Price");
        JTable items = new JTable(model);

        dp.add(new JScrollPane(items));
    }

    public void addItems(String pid, String name, String price) {
        model.addRow(new Object[]{pid, name, price});
    }
    public void deleteRow() {
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No Products to Delete");
        }
        else {
            model.removeRow(model.getRowCount() - 1);
            JOptionPane.showMessageDialog(null, "Product Deleted");
        }

    }
}
