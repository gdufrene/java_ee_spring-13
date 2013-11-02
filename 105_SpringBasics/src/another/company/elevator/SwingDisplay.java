package another.company.elevator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ext.company.elevator.ControlPanel;
import ext.company.elevator.ControlPanel.Button;
import fr.eservice.common.DisplayPanel;
import fr.eservice.common.ElevatorListener;

@org.springframework.stereotype.Component
@Qualifier("graphic")
public class SwingDisplay extends DisplayPanel implements ElevatorListener {
	
	@Autowired
	private ControlPanel controlPanel;
	
	private JPanel mainPanel;
	
	public SwingDisplay() {
		
		mainPanel = new JPanel( new BorderLayout() );
		mainPanel.add( buildElevatorDisplayPanel(), BorderLayout.NORTH );
		mainPanel.add( buildElevatorButtonsPanel(), BorderLayout.WEST );
		mainPanel.add( buildDoorPanel(), BorderLayout.CENTER );
		
		JPanel eastPanel = new JPanel( new GridLayout(0,2) );
		eastPanel.add( buildElevatorPositionPanel() );
		eastPanel.add( builCallButtonsPanel() );
		mainPanel.add( eastPanel, BorderLayout.EAST );
		
		JFrame frame = new JFrame("Elevator application");
		
		frame.getContentPane().add( mainPanel );
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	JPanel elevatorButtonsPanel;
	private JPanel buildElevatorButtonsPanel() {
		if ( elevatorButtonsPanel != null ) return elevatorButtonsPanel;
		elevatorButtonsPanel = new JPanel( new GridLayout(0,2) );
		Button[] buttons = Button.values();
		for(int i = 0; i < buttons.length / 2; i++) {
			Button b = buttons[i];
			buttons[i] = buttons[ buttons.length - i - 1 ];
			buttons[ buttons.length - i - 1 ] = b;
		}
		for ( Button b : buttons ) {
			JButton jb = createButton(b);
			elevatorButtonsPanel.add( jb );
		}
		return elevatorButtonsPanel;
	}
	
	private Dimension buttonDimension = new Dimension(40, 40);
	private JButton createButton(Button btn) {
		JButton button = new JButton(btn.label);
		button.setPreferredSize(buttonDimension);
		// button.setBorderPainted(false);
		// button.setOpaque(true);
		offStyle(button);
		button.addActionListener( new ButtonListener(btn) );
		return button;
	}
	private class ButtonListener implements ActionListener {
		private  Button btn;
		public ButtonListener(Button btn) {
			this.btn = btn;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println( "you pushed " + btn.name() );
			if ( controlPanel == null ) return;
			controlPanel.push( btn );
		}
	};
	
	private JPanel elevatorDisplayPanel;
	private JPanel buildElevatorDisplayPanel() {
		if ( elevatorDisplayPanel != null ) return elevatorDisplayPanel;
		elevatorDisplayPanel = new JPanel( new FlowLayout() );
		labels.clear();
		
		elevatorDisplayPanel.add( createLight( Going.DOWN.name() ) );
		for( Level l : Level.values() ) {
			elevatorDisplayPanel.add( createLight( l.name ) );
		}
		elevatorDisplayPanel.add( createLight( Going.UP.name() ) );
		return elevatorDisplayPanel;
	}
	
	
	private Dimension lightDimension = new Dimension( 40, 40 );
	private HashMap<String, JLabel> labels = new HashMap<String, JLabel>();
	private Component createLight( String name ) {
		JLabel label = new JLabel( name, JLabel.CENTER );
		label.setPreferredSize(lightDimension);
		label.setOpaque(true);
		offStyle( label );
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		labels.put(name, label);
		return label;
	}
	
	private JPanel doorPanel;
	private double doorPos = 0d;
	private JPanel buildDoorPanel() {
		if ( doorPanel != null ) return doorPanel;
		doorPanel = new JPanel(true) {
			private static final long serialVersionUID = 1L;
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				int width = getWidth();
				int widthDoors = (int) (width / 2 * (1.0 - doorPos));
				if ( widthDoors < 10 ) widthDoors = 10;
				int height = getHeight();
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, widthDoors, height);
				g.fillRect(width - widthDoors, 0, widthDoors, height);
				
				g.setColor(Color.BLACK);
				g.drawRect(0, 0, widthDoors, height-1);
				g.drawRect(width - widthDoors, 0, widthDoors-1, height-1);
			}
		};
		return doorPanel;
	}
	
	private JPanel elevatorPositionPanel;
	private double cabinPos = 1d;
	private Dimension cabinDimension = new Dimension( 60, 0 );
	private JPanel buildElevatorPositionPanel() {
		if ( elevatorPositionPanel != null ) return elevatorPositionPanel;
		elevatorPositionPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				int width = getWidth();
				int height = getHeight();
				int cabinHeight = height / 5;
				int y = (int) (height - cabinPos * cabinHeight);
				g.setColor(Color.white);
				g.fillRect(2, y, width - 3, cabinHeight);
				g.setColor(Color.black);
				g.drawRect(2, y, width - 3, cabinHeight);
				int x = width / 2;
				g.drawLine(x, 0, x, y);
			}
			@Override
			public Dimension getPreferredSize() {
				return cabinDimension;
			}
		};
		return elevatorPositionPanel;
	}
	
	private JPanel callButtonsPanel;
	private JPanel builCallButtonsPanel() {
		if ( callButtonsPanel != null ) return callButtonsPanel;
		callButtonsPanel = new JPanel( new GridLayout(0, 1) );
		Dimension callButtonDimension = new Dimension(20, 20);
		for (int i = 0; i < 5; i++) {
			final int levelButton = 5 - i;
			JButton b = new JButton( "(*)" );
			b.setPreferredSize(callButtonDimension);
			b.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Call elevator at " + levelButton);
					if ( controlPanel == null ) return;
					controlPanel.pushCallButton(levelButton);
				}
			});
			callButtonsPanel.add( b );
		}
		return callButtonsPanel;
	}


	private void offStyle(JComponent comp) {
		comp.setBackground(Color.WHITE);
		comp.setForeground(Color.black);
		comp.repaint();
	}
	
	private void onStyle(JComponent comp) {
		comp.setBackground( Color.GREEN );
		comp.setForeground( Color.RED );
		comp.repaint();
	}


	@Override
	public void setCurrentLevel(Level level) {
		for( Level l : Level.values() ) {
			JLabel label = labels.get( l.name );
			if ( label == null ) continue;
			if ( l == level ) onStyle( label );
			else offStyle( label );
		}
	}
	
	public void setGoing(Going going) {
		for( Going g : Going.values() ) {
			JLabel label = labels.get( g.name() );
			if ( label == null ) continue;
			if ( g == going ) onStyle( label );
			else offStyle( label );
		}
		
	};

	
	@Override
	public void atFloor(int floor) {
		if ( floor < 1 || floor > Level.values().length) return;
		Level level = Level.values()[floor - 1];
		setCurrentLevel(level);
	}
	
	@Override
	public void doorClosed() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doorOpened() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doorUpdatePosition(double x) {
		doorPos = x;
		doorPanel.repaint();
	}
	
	@Override
	public void elevatorUpdatePosition(double x) {
		cabinPos = x;
		elevatorPositionPanel.repaint();
	}

	public static void main(String[] args) {
		SwingDisplay display = new SwingDisplay();
		display.setCurrentLevel( Level.LEVEL_3 );
		display.setGoing( Going.UP );
		
		display.doorUpdatePosition(0.8);
		display.elevatorUpdatePosition(2.5);
	}

}
