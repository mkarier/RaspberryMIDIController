import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class AudioListener implements LineListener 
{
	public boolean playCompleted = false;
	
	
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
