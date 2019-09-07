package GUI;

import java.awt.BorderLayout;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import AudioManiplation.VolumeControl;

public class MasterGainSlide implements ChangeListener 
{
	private VolumeControl volControl;
	private JSlider masterGainSlide;
	
	public MasterGainSlide(ControllerCanvas canvas, VolumeControl volControl)
	{
		this.volControl = volControl;
		Integer min = (int)volControl.getMinMasterGain();
		Integer max = (int)volControl.getMaxMasterGain();
		Integer current = (int)volControl.getCurrentMasterGain();
		this.masterGainSlide = new JSlider(SwingConstants.HORIZONTAL, min, max, current);
		//this.masterGainSlide.setOrientation(SwingConstants.LEFT);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(current, new JLabel("Default " + current));
		labelTable.put(min, new JLabel("Min " + min));
		labelTable.put(max, new JLabel("Max " + max));
		this.masterGainSlide.setLabelTable(labelTable);
		this.masterGainSlide.setPaintLabels(true);
		this.masterGainSlide.setPaintTicks(true);
		this.masterGainSlide.addChangeListener(this);
		canvas.box.add(this.masterGainSlide, BorderLayout.SOUTH);
	}//end of constructor

	
	@Override
	public void stateChanged(ChangeEvent e) 
	{
		// TODO Auto-generated method stub
		this.volControl.setMasterGain((float)this.masterGainSlide.getValue());
	}

}//end of MasterGainSlide
