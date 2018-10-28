
public class AudioOutputThread extends Thread 
{
	private AudioOutput output;
	private AudioInputThread inputThread;
	
	AudioOutputThread(AudioOutput output, AudioInputThread inputThread)
	{
		this.output = output;
		this.inputThread = inputThread;
	}//end of constructor
	
	public void run()
	{
		while(true)
		{
			output.outputAudio(inputThread.getInput());
		}//end of while loop
	}//end of run method
	
}//End of AudioOutputThread
