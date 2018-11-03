package AudioDevices;
//Copied from Franky Chanyau who posted it on stackoverflow
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.Mixer.Info;


public class AudioInput {
	private int line;
	protected AudioFormat format;
	private Info[] lines;
	protected TargetDataLine inputLine;
	private DataLine.Info inInfo;
	private int bufferSize = 1920; //This is how the old buffer calculated the buffer size: (int) format.getSampleRate() * format.getFrameSize();;
	private byte[] buffer;
	private int rate = 44100;
	private int bit = 16;
	private int channel = 1;
	private boolean signed = true;
	private boolean big_endian = true;
	
  public AudioInput(int line){
    this.line = line;
    this.setup();
  }

  public AudioInput(){
    this.line = 0;
    this.setup();
  }
  
  //protected void start() { this.inputLine.start();}
  public TargetDataLine getInputLine(){return this.inputLine;}
  public AudioFormat getAudioFormat() {return this.format;}
  public int getBufferSize() {return this.bufferSize;}
  public synchronized byte[] getBuffer() {return this.buffer;}

  public byte[] captureAudio()
  {
	  inputLine.read(buffer,0,buffer.length);
      //listen(buffer);
	  return buffer;
  }

  private void setup(){
    format = new AudioFormat(this.rate, this.bit, this.channel, this.signed, this.big_endian); 
    lines = AudioSystem.getMixerInfo();   
    //this.bufferSize = (int) format.getSampleRate() * format.getFrameSize();
    inInfo = new DataLine.Info(TargetDataLine.class, format);
    buffer = new byte[bufferSize];
    try {
    	inputLine = (TargetDataLine)AudioSystem.getMixer(lines[line]).getLine(inInfo);
		inputLine.open(format, bufferSize);
		inputLine.start(); 
	} catch (LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//end of catch
  }//end of setup

  public void printLineInfo(){
    for (int i = 0; i < lines.length; i++){
      System.out.println(i+": "+lines[i].getName()+"\n"+lines[i].getDescription());
    }
  }

}
