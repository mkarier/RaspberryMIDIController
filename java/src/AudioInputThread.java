import java.util.Collections;
import java.util.LinkedList;

public class AudioInputThread extends Thread 
{
	private AudioInput input;
	private LinkedList<byte []> bufferStack = new LinkedList<byte []>();
	private int stackSize = 10000;
	
	
	AudioInputThread(AudioInput input)
	{
		this.input = input;
	}//end of constructor
	
	public synchronized int getStackSize() {return this.stackSize;}
	public int getSize() {return this.bufferStack.size();}
	
	public synchronized byte[] getInput()
	{
		if(this.bufferStack.size() > 0)
			return bufferStack.removeFirst();
		else
			return null;
	}//gets return the input
	
	public void run()
	{
		while(true)
		{
			this.bufferStack.addLast(input.captureAudio());
		}//end of while loop
	}//end of run method
	
	
}//end of AudioInputThread
