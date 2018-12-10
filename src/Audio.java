
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Audio {
	private int count = 1;
	private Clip clip;
	private AudioInputStream audioInputStream;
	long clipTime;

	public void music() {
		try {
//			count++;
			if (this.clip != null) {
				if (clip.isActive() == true) {
					clip.stop();
				}
			}
			audioInputStream = AudioSystem
					.getAudioInputStream(new File("Music\\BGM"+ this.count + ".wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
			clip.start();
		} catch (FileNotFoundException e) {
		} catch (IOException error) {
		} catch (UnsupportedAudioFileException exception) {
		} catch (LineUnavailableException exception) {
		}
		this.clip.loop(3);
	}
	
	public void playSound(String s) {
		AudioPlayer loopMachine = AudioPlayer.player;
		try {
			String image = String.format("Music\\%s.wav", s);
			InputStream song = new FileInputStream(image);
			AudioStream BGM = new AudioStream(song);
			loopMachine.start(BGM);
		} catch (FileNotFoundException e) {
		} catch (IOException error) {
		}
	}
	
	public void pauseMusic(){
		this.clipTime = clip.getMicrosecondPosition();
		this.clip.stop();
	}
	
	public void continueMusic(){
		this.clip.setMicrosecondPosition(this.clipTime);
		this.clip.start();
	}
	
	public Clip getClip(){
		return this.clip;
	}
	
	public void updateCount(int i){
		this.count = i;
	}
	
	public int getCount(){
		return this.count;
	}
}