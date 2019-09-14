package GuitarEffects;

import javax.sound.sampled.SourceDataLine;

public class DistortionEffect implements I_AudioOutput {

	I_AudioOutput nextEffect;
	public DistortionEffect(I_AudioOutput nextEffect) {
		this.nextEffect = nextEffect;
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
				this.nextEffect.outputAudio(buffer);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}//end of outputAudio

	@Override
	public SourceDataLine getLineOut() {
		// TODO Auto-generated method stub
		return this.nextEffect.getLineOut();
	}

}
