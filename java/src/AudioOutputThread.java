
public class AudioOutputThread extends Thread 
{
	private AudioListener output;
	private AudioInputThread inputThread;
	private int stackSize;
	
	AudioOutputThread(AudioListener output, AudioInputThread inputThread)
	{
		this.output = output;
		this.inputThread = inputThread;
		this.stackSize = inputThread.getStackSize();
	}
	
	public void run()
	{
		boolean outputing = false;
		byte[] buffer = null;
		int index = 0;
		while(true)
		{
			if(inputThread.getSize() < 1)
			{
				outputing = true;
				while(outputing)
				{
					buffer = inputThread.getInput(index);
					index = (index + 1) % stackSize;
					if(buffer != null)
						output.outputAudio(buffer);
					if(inputThread.getSize() > 0)
						continue;
					else
						outputing = false;
				}//end of inner while loop
			}//end of if
			else
				continue;
		}//end of while loop
	}//end of run method
	
}//End of AudioOutputThread
