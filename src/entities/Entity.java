/**
 * @author William
 * @date 9/3/15
 * 
 * This is an interface creating an object Entity to be used to make things appear on the screen
 * This class is derived from code made by Oskar Veerhoek
 * source code can be found at http://thecodinguniverse.com/lwjgl-entities/
 */

package entities;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;

public interface Entity {
	public void draw ();
	public void update (int delta);
	public void update ();
	public void setLocation (double x, double y);
	public void setX (double x);
	public void setY (double y);
	public void setWidth(double width);
	public void setHeight(double height);
	public void setTexture(String key);
	public double getX ();
	public double getY ();
	public double getWidth();
	public double getHeight();
	public String getTextureKey();
	public TextureImpl getTexture();
	public boolean intersects (Entity other);
}
