import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Keyboard extends JPanel {

    JPanel kb;
    final JTextField code;
    final JTextField price;
    final JButton enter;
    final JButton delete;
    final JButton purchase;

    public Keyboard() {
        // Text box for code
        code = new JTextField();
        code.setEditable(false);

        // Text box for total
        price = new JTextField();
        price.setEditable(false);

        // Number buttons 0-9
        JButton[] numbers = new JButton[10];

        // Enter, Delete, purchase buttons
        enter = new JButton("Enter");
        delete = new JButton("Delete");
        purchase = new JButton("Purchase");

        // Panel to hold buttons
        kb = new JPanel();
        kb.setLayout(new GridLayout(5, 3));

        // Loop through 0-9 integers creating a button and ActionListener for each
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = new JButton(String.valueOf(i));
            numbers[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    code.setText(code.getText() + e.getActionCommand());
                }
            });
        }

        // Add all components to the panel
        kb.add(numbers[1]);
        kb.add(numbers[2]);
        kb.add(numbers[3]);
        kb.add(numbers[4]);
        kb.add(numbers[5]);
        kb.add(numbers[6]);
        kb.add(numbers[7]);
        kb.add(numbers[8]);
        kb.add(numbers[9]);
        kb.add(numbers[0]);
        kb.add(enter);
        kb.add(delete);
        kb.add(purchase);
    }
}
