import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;


public class ExistingGameView implements ActionListener, ListSelectionListener {

    private new_button play;
    private JFrame win;
    private ScrollList partyList;
    private Vector empty = new Vector();

    private String selectedParty;

    private ArrayList<Object> outdata;

    private ControlDesk controlDesk;

    private ExistingGameData data;

    public ExistingGameView(ControlDesk controlDesk) {
        this.controlDesk = controlDesk;

        selectedParty = null;

        win = new JFrame("Existing Game");
        win.getContentPane().setLayout(new BorderLayout());
        ((JPanel) win.getContentPane()).setOpaque(false);


        JPanel colPanel = new JPanel();
        colPanel.setLayout(new GridLayout(1, 2));

        // Existing game list Panel

        JPanel partyPanel = new JPanel();
        partyPanel.setLayout(new FlowLayout());
        partyPanel.setBorder(new TitledBorder("Select your game"));


        data = new ExistingGameData();
        outdata = (ArrayList<Object>) data.get_data();

        for(int i=0;i<outdata.size();i++) {
            ArrayList<Object> partyData = (ArrayList<Object>)outdata.get(i);

            Vector members = (Vector) partyData.get(0);
            String partyName = (i+1) + "." + " ";
            for(int j =0;j<members.size();j++){
                Bowler bowler = (Bowler) members.get(j);
                partyName +=  bowler.getNickName() + " ";
            }
            empty.add(partyName);
        }

        partyList = new ScrollList(empty);
        partyList.add_list(15,300,partyPanel);
        partyList.get_list().addListSelectionListener((ListSelectionListener) this);

        // button panel

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1));

        Insets buttonMargin = new Insets(4, 4, 4, 4);

        play = new new_button("Play");
        play.add_button(this,buttonPanel);

        colPanel.add(partyPanel);
        colPanel.add(buttonPanel);

        win.getContentPane().add("Center", colPanel);

        win.pack();

        // Center Window on Screen
        Dimension screenSize = (Toolkit.getDefaultToolkit()).getScreenSize();
        win.setLocation(
                ((screenSize.width) / 2) - ((win.getSize().width) / 2),
                ((screenSize.height) / 2) - ((win.getSize().height) / 2));
        win.show();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(play.get_button())) {
            if (selectedParty != null) {
                for (int i = 0; i < empty.size(); i++) {
                    if (selectedParty.equals(empty.get(i))) {
                        ArrayList<Object> partyData = (ArrayList<Object>) outdata.get(i);
                        Party party = new Party((Vector) partyData.get(0));
                        party.cumulScores = (int[][]) partyData.get(1);
                        party.Scores = (HashMap) partyData.get(2);
                        party.gameFinished  = (boolean) partyData.get(3);
                        party.frameNumber = (int) partyData.get(4);
                        party.tenthFrameStrike = (boolean) partyData.get(5);
                        party.gameNumber = (int) partyData.get(6);
                        party.finalScores = (int[][]) partyData.get(7);
                        party.curScores = (int[]) partyData.get(8);
                        party.isExisting = true;
                        outdata.remove(i);
                        data.set_data(outdata);
                        controlDesk.addExistingPartyQueue(party);
                        break;
                    }
                }
                win.hide();
            } else {
                win.hide();
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource().equals(partyList.get_list())) {
            selectedParty =
                    ((String) ((JList) e.getSource()).getSelectedValue());
        }
    }
}
