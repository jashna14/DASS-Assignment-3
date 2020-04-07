import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class new_button {
    private JButton button;

    public new_button (String name) {
        button = new JButton(name);
    }

    public JButton get_button () {
        return button;
    }

    public void add_button(ActionListener x , JPanel panel) {
        JPanel new_panel = new JPanel();
        new_panel.setLayout(new FlowLayout());
        button.addActionListener(x);
        new_panel.add(button);
        panel.add(new_panel);
    }

    public void set_Enabled(boolean x ) {
        button.setEnabled( x );
    }

    public void set_Background_red() {
        button.setBackground( Color.RED );
    }

    public void set_Background_green() {
        button.setBackground( Color.GREEN );
    }
}


