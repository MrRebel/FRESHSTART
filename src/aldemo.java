import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.openal.AL;
import org.lwjgl.util.WaveData;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

public class aldemo {
	public aldemo() throws FileNotFoundException{
		//create a openGL instance in the thread
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Entity Demo");
			Display.create();
			AL.create();
		} catch(LWJGLException e){
			e.printStackTrace();
			Display.destroy();
            AL.destroy();
            System.exit(1);
		}
		WaveData sound = WaveData.create(new BufferedInputStream(new FileInputStream("res/sound.wav")));
		int buffer = alGenBuffers();
		alBufferData(buffer, sound.format, sound.data, sound.samplerate);
		sound.dispose();
		int source = alGenSources();
        alSourcei(source, AL_BUFFER, buffer);

        glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		
		//code loop if the window is closed the loop will end
		while (!Display.isCloseRequested()) {
			while (Keyboard.next()) {
				if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
					alSourcePlay(source);
				}
			}
			Display.update();
			Display.sync(60);
        }
        alDeleteSources(source);
        alDeleteBuffers(buffer);
        AL.destroy();
        Display.destroy();
        System.exit(0);
	}
	public static void main(String[] args) throws FileNotFoundException{
		new aldemo();
	}
}
