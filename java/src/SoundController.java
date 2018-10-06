
import java.io.*;
import java.util.*;
/*
import org.usb4java.*;
import org.usb4java.javax.*;
import javax.usb.*;//*/

import javax.sound.midi.*;
import javax.sound.sampled.*;
import javax.sound.sampled.Line.Info;


public class SoundController 
{
	
	public static void main(String args[])
	{
		try 
		{
			//UsbServices services = UsbHostManager.getUsbServices();
		   // UsbHub hub = services.getRootUsbHub();
		    //listPeripherique(hub);
			AudioInputStream input = AudioSystem.getAudioInputStream(new File("test.wav"));
			//AudioInputStream input = new AudioInputStream(getTargetLine(temp.getFormat()));
			//temp.close();
			//DataLine.Info  info = new DataLine.Info(SourceDataLine.class, input.getFormat());


			SourceDataLine lineOut = (SourceDataLine)AudioSystem.getLine(Port.Info.LINE_OUT);
			AudioListener listener= new AudioListener();
			lineOut.addLineListener(listener);
			lineOut.open(input.getFormat());
			lineOut.start();
			/*while (!listener.playCompleted) 
			{
				// wait for the playback completes
				try 
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}//end of while*/
			
			int BUFFER_SIZE = lineOut.getBufferSize();
			byte[] bytesBuffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while((bytesRead = input.read(bytesBuffer)) != -1)
			{
				lineOut.write(bytesBuffer, 0, bytesRead);
			}
			lineOut.close();
			input.close();
			System.out.println("Closing all the devices");
		}//end of try to read in file info 
		catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}//end of main
	
	private static TargetDataLine getTargetLine(AudioFormat soundFormat) {
        try {
            Mixer.Info infoo[] = AudioSystem.getMixerInfo();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, soundFormat);
            Line.Info infol[] = AudioSystem.getTargetLineInfo(info);
            if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) 
            {
            	System.out.println("Microphne is used");
                return (TargetDataLine) AudioSystem.getLine(Port.Info.MICROPHONE);
            } else if (AudioSystem.isLineSupported(Port.Info.LINE_IN))
            {
            	System.out.println("Line_In is used");
                return (TargetDataLine) AudioSystem.getLine(Port.Info.LINE_IN);
            } else {
            	System.out.println(infol[0].getLineClass().getName() + " is used");
                return (TargetDataLine) AudioSystem.getLine(infol[0]);
            }
            
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
        return null;
    }
	/*
	public static void listPeripherique(UsbHub hub) 
	{
	    //List all the USBs attached
	    List perepheriques = hub.getAttachedUsbDevices();
	    Iterator iterator = perepheriques.iterator();

	    while (iterator.hasNext()) 
	    {

	      UsbDevice perepherique = (UsbDevice) iterator.next();
	      System.out.println(perepherique);

	      if (perepherique.isUsbHub())
	        listPeripherique((UsbHub) perepherique);

	    }//end of while loop
	}//end of listPeripherique
	
	public static UsbDevice findDevice(UsbHub hub, short vendorId, short productId)
	{
	    for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices())
	    {
	        UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
	        if (desc.idVendor() == vendorId && desc.idProduct() == productId) return device;
	        if (device.isUsbHub())
	        {
	            device = findDevice((UsbHub) device, vendorId, productId);
	            if (device != null) return device;
	        }
	    }
	    return null;
	}//end of findDeviclineOut = AudioSystem.getSourceDataLine(input.getFormat());
	//*/
}//end of SOundController
