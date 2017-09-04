package View;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundManager {
    private Clip song;

    public SoundManager( String filename ) {
    	try {
			song = AudioSystem.getClip();
			File file = new File( "assets/" + filename );
			AudioInputStream is = AudioSystem.getAudioInputStream( file );
			song.open( is );
		} catch ( Exception e ) {
			e.printStackTrace();
		}	
    }
    
    public void playSound() {
    	song.loop( Clip.LOOP_CONTINUOUSLY );
    }
    
    public void stopSound() {
    	song.stop();
    }
    
    public void playSoundOnce() {
    	song.start();
    }
    
    public void changeVolume( float volume ) {
    	FloatControl fc = ( FloatControl )song.getControl( FloatControl.Type.MASTER_GAIN );
    	fc.setValue( volume );
    }
}