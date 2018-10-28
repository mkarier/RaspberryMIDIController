import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class AudioInputThread extends Thread 
{
	private AudioInput input;
	private List<byte []> bufferStack = Collections.synchronizedList(new ArrayList<byte []>());
	private int stackSize = 10000;
	
	
	AudioInputThread(AudioInput input)
	{
		this.input = input;
	}//end of constructor
	
	public synchronized int getStackSize() {return this.stackSize;}
	public int getSize() {return this.bufferStack.size();}
	
	public byte[] getInput()
	{
		if(this.bufferStack.size() > 0)
			return bufferStack.remove(0);
		else
			return null;
	}//gets return the input
	
	public void run()
	{
		while(true)
		{
			this.bufferStack.add(input.captureAudio());
		}//end of while loop
	}//end of run method
	
	
}//end of AudioInputThread
