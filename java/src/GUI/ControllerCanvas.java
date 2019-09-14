package GUI;
import AudioDevices.*;
import AudioManipulation.VolumeControl;

import java.awt.*;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;


public class ControllerCanvas
{

	protected JFrame box;
	protected MasterGainSlide masterGainSlide;
	protected KillswitchButton killswitch;
	
	public ControllerCanvas()
	{
		this.box = new JFrame("LabelController");
		/**JTextArea label = new JTextArea("VolumeController");
		label.setSize(20000,20000);
		this.box.add(label); //**/
		this.box.setPreferredSize(new Dimension(300,300));
		this.box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.box.pack();
		this.box.setVisible(true);
	}//end of canvas
	
	public void addVolumeSilde(VolumeControl volControl)
	{
		this.masterGainSlide = new MasterGainSlide(this, volControl);
		this.box.pack();
	}//end of addVolumeSlide
	
	public void addKillswitch(VolumeControl volControl)
	{
		this.killswitch = new KillswitchButton(this, volControl);
		this.box.pack();
	}

}//end of canvas
