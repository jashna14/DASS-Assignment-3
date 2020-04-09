import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class Stats extends JFrame {
//    private JTextField jtf;
//    private JLabel searchLbl;
//    private TableModel model;
//    private JTable table;
//    private TableRowSorter sorter;
//    private JScrollPane jsp;
//    private Vector bowlerdb;
    private String[][] AllScores;
    public Stats() {
        ReadData();
        setFrameAttributes();
        SortAllScores();
        setStats();
        JTextField jtf = new JTextField(30);
        JLabel searchLbl = new JLabel("<html><body><h4>Search</h4></body></html>");
        String columns[]={"Name","Time & Date","Score"};
        TableModel model = new DefaultTableModel(AllScores,columns);
        TableRowSorter sorter = new TableRowSorter<>(model);
        JTable table = new JTable(model);
        table.setRowSorter(sorter);
        JScrollPane jsp = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(searchLbl);
        panel.add(jtf);
        add(panel);
        add(jsp);
        jtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(jtf.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(jtf.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(jtf.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });
    }
    private void setFrameAttributes(){
        setTitle("Stats");
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    private void setStats(){
        JPanel panel = new JPanel(new GridLayout(0,1));
        JLabel TopScore = new JLabel("Top Score : " + AllScores[0][2] + "\n");
        JLabel TopPlayer = new JLabel("Top Player : " + AllScores[0][0] + "\n");
        JLabel LeastScore = new JLabel("Least Score : " + AllScores[ScoreHistoryFile.getNumberOfScores() - 1][2] + "\n");
        panel.add(TopScore);
        panel.add(TopPlayer);
        panel.add(LeastScore);
        add(panel);
    }
    private void ReadData(){
        try {
            AllScores = ScoreHistoryFile.AllScores();
        } catch (Exception e) {
            System.err.println("File Error");
        }
    }
    private void SortAllScores(){
        Arrays.sort(AllScores,new StringArrayComparator());
    }
}