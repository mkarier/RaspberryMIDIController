package AudioDevices;

public class DistortionEffect extends AudioOutput {

	public DistortionEffect(AudioInput inputLine) {
		super(inputLine);
		// TODO Auto-generated constructor stub
	}
	
	public void outputAudio(byte[] buffer)
	{
		if(buffer == null)
			return;
		else
		{
			try 
			{
				for(int i = 0; i < buffer.length; i++)
				{
					//System.out.println(buffer[i]);
					buffer[i] = (byte)(Math.abs(buffer[i]));
				}
				super.lineOut.write(buffer, 0, buffer.length -1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}//end of outputAudio

}
