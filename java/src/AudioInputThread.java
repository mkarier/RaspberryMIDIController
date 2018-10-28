
public class AudioInputThread extends Thread 
{
	private AudioInput input;
	private byte[][] bufferStack;
	private int index = 0;
	private int stackSize = 10000;
	private int size = 0;
	
	
	AudioInputThread(AudioInput input)
	{
		this.input = input;
		this.bufferStack = new byte[stackSize][];
	}
	
	public synchronized int getStackSize() {return this.stackSize;}
	public synchronized  int getSize() {return this.size;}
	public synchronized void increaseSize() {this.size++;}
	public synchronized byte[] getInput(int index)
	{
		byte[] temp = bufferStack[index];
		bufferStack[index] = null;
		this.size--;
		return temp;
		
	}
	
	public void run()
	{
		while(true)
		{
			if(getSize() < stackSize)
			{
				this.bufferStack[index] = input.captureAudio();
				this.index = (index + 1) % stackSize;
				increaseSize();
			}else
				continue;
		}//end of while loop
	}//end of run method
	
	
}//end of AudioInputThread
