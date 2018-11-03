package GUI;
import AudioDevices.*;
import AudioManiplation.VolumeControl;

import java.awt.*;


import javax.swing.JFrame;
import javax.swing.JLabel;


public class Canvas 
{

	protected JFrame box;
	protected MasterGainSlide masterGainSlide;
	private VolumeControl volControl;
	
	public Canvas()
	{
		this.box = new JFrame();
		JLabel label = new JLabel("");
	    label.setPreferredSize(new Dimension(1024, 1024));
		this.box.getContentPane().add(label, BorderLayout.CENTER);
		this.box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.box.pack();
		this.box.setVisible(true);
	}//end of canvas
	
	public void addVolumeSilde(VolumeControl volControl)
	{
		this.masterGainSlide = new MasterGainSlide(this.box, volControl);
	}//end of addVolumeSlide

}//end of canvas
