package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import AudioManiplation.VolumeControl;

public class KillswitchButton implements MouseListener
{
	private VolumeControl muteButton;
	public KillswitchButton(ControllerCanvas canvas, VolumeControl volControler)
	{
		JTextArea area = new JTextArea(canvas.box.getHeight()/2, canvas.box.getWidth()/2);
		canvas.box.add(area);
		canvas.box.addMouseListener(this);
		this.muteButton = volControler;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		this.muteButton.mute();
		
	}
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		this.muteButton.unMute();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}//end of KillswitchButton
