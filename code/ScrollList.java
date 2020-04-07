import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;

public class ScrollList {

    private JList jlist;

    public ScrollList(Object list) {
        jlist = new JList((Vector) list);
    }

    public void add_list(int rowcount , int visible_width , JPanel panel){
        jlist.setVisibleRowCount(rowcount);
        jlist.setFixedCellWidth(visible_width);
        JScrollPane pane = new JScrollPane(jlist);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(pane);
    }

    public JList get_list(){
        return jlist;
    }

    public void set_ListData(Object list) {
        jlist.setListData((Vector) list);
    }
}
