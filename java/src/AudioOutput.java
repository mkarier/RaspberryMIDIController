import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.Line.Info;

public class AudioOutput implements LineListener 
{
	public boolean playCompleted = false;
	protected SourceDataLine lineOut;
	private int bufferSize;
	
	public AudioOutput(AudioInput inputLine)
	{
		try {
			
			this.lineOut = AudioSystem.getSourceDataLine(inputLine.getAudioFormat());
			this.lineOut.addLineListener(this);
			this.lineOut.open(inputLine.getAudioFormat());
			this.bufferSize = inputLine.getBufferSize();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lineOut.start();
	}
	
	public SourceDataLine getLineOut() {return this.lineOut;}
	public int getBufferSize() { return this.bufferSize;}
	
	public void outputAudio(byte[] buffer)
	{
		if(buffer == null)
			return;
		else
			this.lineOut.write(buffer, 0, buffer.length);
	}
	
	@Override
	public void update(LineEvent event) 
	{
		// TODO Auto-generated method stub
		LineEvent.Type type = event.getType();
        
        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");
             
        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }
	}//end of update

}//end of AudioListener
