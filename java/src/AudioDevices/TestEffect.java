package AudioDevices;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.Transmitter;

public class TestEffect extends AudioOutput 
{

	private Synthesizer synth;
	private Receiver receive;
	public SysexMessage myMsg;
	private Sequencer seq;
	private Transmitter trans;
	
	public TestEffect(AudioInput inputLine) throws MidiUnavailableException 
	{
		super(inputLine);
		// TODO Auto-generated constructor stub
		this.synth = MidiSystem.getSynthesizer();
		Instrument[] instruments = this.synth.getAvailableInstruments();
		//for(Instrument in : instruments)
			System.out.println(this.synth.getDefaultSoundbank().getName());
		this.synth.loadInstrument(instruments[1]);
		this.synth.open();
		this.receive = synth.getReceiver();
		this.myMsg = new SysexMessage();
		this.seq = MidiSystem.getSequencer();
		this.trans = seq.getTransmitter();
		this.trans.setReceiver(this.receive);
	}//end of constructor
	
	@Override
	public void outputAudio(byte[] buffer)
	{
		if(buffer == null)
			return;
		else
		{
			try {
				this.myMsg.setMessage(SysexMessage.SYSTEM_EXCLUSIVE, buffer, buffer.length -1);
				this.receive.send(this.myMsg, -1);
				super.lineOut.write(buffer, 0, buffer.length -2);
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}//end of outputAudio

}//end of PianoEffect
