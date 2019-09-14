package GuitarEffects;

import javax.sound.sampled.SourceDataLine;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;


public class PitchShift implements I_AudioOutput
{
	FastFourierTransformer fft;
	I_AudioOutput nextEffect;
	
	public PitchShift(I_AudioOutput nextEffect) 
	{
		this.nextEffect = nextEffect;
		this.fft = new FastFourierTransformer(DftNormalization.STANDARD);
		// TODO Auto-generated constructor stub
	}

	public double[] changeArrayType(byte[] buffer)
	{
		int size = buffer.length;			
		double[] temp = new double[size];
		int i = 0;
		for( ; i < size; i++)
			temp[i] = buffer[i];
		
		return temp;
	}
	
	public byte[] changeArrayType(double[] buffer)
	{
		byte[] temp = new byte[buffer.length];
		for(int i = 0; i < buffer.length; i++)
			temp[i] = (byte) buffer[i];
		return temp;
	}
	
	public byte[] revertToByteArray(Complex[] input)
	{
		byte buffer[] = new byte[input.length];
		for(int i = 0; i < input.length; i++)
			buffer[i] = (byte) input[i].getReal();
		return buffer;
	}
	
	@Override
	public void outputAudio(byte[] buffer)
	{
		if(buffer == null)
			return;
		
		Complex cbuffer[] = this.fft.transform(changeArrayType(buffer), TransformType.FORWARD);
		/*for(Complex temp: cbuffer)
			System.out.println(temp);//*/
		this.nextEffect.outputAudio(revertToByteArray(this.fft.transform(cbuffer, TransformType.INVERSE)));
	}//end of outputAudio


	@Override
	public SourceDataLine getLineOut() {
		// TODO Auto-generated method stub
		return this.nextEffect.getLineOut();
	}
}//end of class
