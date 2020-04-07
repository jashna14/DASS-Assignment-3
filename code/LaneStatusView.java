/**
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LaneStatusView implements ActionListener, LaneObserver, PinsetterObserver {

	private JPanel jp;

	private JLabel curBowler;
    private JLabel pinsDown;
	private new_button viewLane;
	private new_button viewPinSetter, maintenance;

	private PinSetterView psv;
	private LaneView lv;
	private Lane lane;
	int laneNum;

	boolean laneShowing;
	boolean psShowing;

	public LaneStatusView(Lane lane, int laneNum ) {

		this.lane = lane;
		this.laneNum = laneNum;

		laneShowing=false;
		psShowing=false;

		psv = new PinSetterView( laneNum );
		Pinsetter ps = lane.getPinsetter();
		ps.subscribe(psv);

		lv = new LaneView( lane, laneNum );
		lane.subscribe(lv);


		jp = new JPanel();
		jp.setLayout(new FlowLayout());
		JLabel cLabel = new JLabel( "Now Bowling: " );
		curBowler = new JLabel( "(no one)" );
		JLabel fLabel = new JLabel( "Foul: " );
        JLabel foul = new JLabel(" ");
		JLabel pdLabel = new JLabel( "Pins Down: " );
		pinsDown = new JLabel( "0" );

		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		Insets buttonMargin = new Insets(4, 4, 4, 4);

		viewLane = new new_button("View Lane");
		viewLane.add_button(this,buttonPanel);

		viewPinSetter = new new_button("Pinsetter");
		viewPinSetter.add_button(this,buttonPanel);

		maintenance = new new_button("     ");
		maintenance.add_button(this,buttonPanel);
		maintenance.set_Background_green();



		viewLane.set_Enabled( false );
		viewPinSetter.set_Enabled( false );



		jp.add( cLabel );
		jp.add( curBowler );
//		jp.add( fLabel );
//		jp.add( foul );
		jp.add( pdLabel );
		jp.add( pinsDown );
		
		jp.add(buttonPanel);

	}

	public JPanel showLane() {
		return jp;
	}

	public void actionPerformed( ActionEvent e ) {
		if ( lane.isPartyAssigned() ) {
			if (e.getSource().equals(viewPinSetter.get_button())) {
				if (!psShowing) {
					psv.show();
					psShowing = true;
				} else if (psShowing) {
					psv.hide();
					psShowing = false;
				}
			}

			if (e.getSource().equals(viewLane.get_button())) {
				if (!laneShowing) {
					lv.show();
					laneShowing = true;
				} else if (laneShowing) {
					lv.hide();
					laneShowing = false;
				}
			}
			if (e.getSource().equals(maintenance.get_button())) {
				lane.unPauseGame();
				maintenance.set_Background_green();
			}
		}
	}

	public void receiveLaneEvent(LaneEvent le) {
		curBowler.setText( le.getBowler().getNickName() );
		if ( le.isMechanicalProblem() ) {
			maintenance.set_Background_red();
		}	
		if (!lane.isPartyAssigned()) {
			viewLane.set_Enabled( false );
			viewPinSetter.set_Enabled( false );
		} else {
			viewLane.set_Enabled( true );
			viewPinSetter.set_Enabled( true );
		}
	}

	public void receivePinsetterEvent(PinsetterEvent pe) {
		pinsDown.setText( (Integer.valueOf(pe.totalPinsDown())).toString() );
//		foul.setText( ( new Boolean(pe.isFoulCommited()) ).toString() );
		
	}

}
