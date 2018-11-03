package AudioDevices;

public class AudioOutputThread extends Thread 
{
	private AudioOutput output;
	private AudioInputThread inputThread;
	private int stackSize;
	
	public AudioOutputThread(AudioOutput output, AudioInputThread inputThread)
	{
		this.output = output;
		this.inputThread = inputThread;
		this.stackSize = inputThread.getStackSize();
	}//end of constructor
	
	public void run()
	{
		while(true)
		{
			output.outputAudio(inputThread.getInput());
		}//end of while loop
	}//end of run method
	
}//End of AudioOutputThread
