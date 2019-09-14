package GuitarEffects;

import java.io.*;
import java.util.Arrays;

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
import javax.sound.sampled.SourceDataLine;

import AudioDevices.AudioInput;

public class TestEffect implements I_AudioOutput
{

	private Synthesizer synth;
	private Receiver receive;
	public SysexMessage myMsg;
	private Sequencer seq;
	private Transmitter trans;
	private int time = 0;
	BufferedWriter out;
	private I_AudioOutput nextEffect;
	
	public TestEffect(I_AudioOutput nextEffect) throws MidiUnavailableException 
	{
		this.nextEffect = nextEffect;
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
		try {
			out = new BufferedWriter(new FileWriter("Buffered.gnu"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end of constructor
	
	@Override
	public void outputAudio(byte[] buffer)
	{
		if(buffer == null)
			return;
		else
		{
			try {
				//this.myMsg.setMessage(SysexMessage.SYSTEM_EXCLUSIVE, buffer, buffer.length -1);
				//this.receive.send(this.myMsg, -1);
				//byte[] array = new byte[buffer.length];
				/*for(int i = 0; i < buffer.length; i++)
				{
					//System.out.println(buffer[i]);
					array[i] = (byte)(buffer[i] * (3/2));
				}*/
				this.nextEffect.outputAudio(buffer);
				for(byte output: buffer)
					this.out.write((time++) + " " + output + "\n");
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

}//end of PianoEffect
