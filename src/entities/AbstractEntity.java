/**
 * @author William Christensen
 * @date 3/9/15
 * 
 * A Class implementing the Entity Interface allowing the user to 
 * change the appearance and location of the object.
 * This class is derived from code made by Oskar Veerhoek
 * source code can be found at http://thecodinguniverse.com/lwjgl-entities/
 */
package entities;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;

public abstract class AbstractEntity implements Entity{
	
	protected double x, y, width, height;
	protected String key = "";
	protected TextureImpl tex;
	protected Rectangle hitbox = new Rectangle();
	//most basic implementation of the Entity
	public AbstractEntity(double x,double y,double width,double height) {
		this.x = x; //sets internal and external x to the same value
		this.y = y;  //sets internal and external y to the same value
		this.width = width;  //sets internal and external width to the same value
		this.height = height;  //sets internal and external height to the same value
	}
	//textured implementation most robust initial implementation of the Entity
	public AbstractEntity(double x,double y,double width,double height,String key) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.key = key;
		tex = textureLoader(key);  //sets internal and external texture key to the same value
	}
	@Override
	public void setLocation(double x, double y) { // a method for setting the x and y values
		this.x = x;
		this.y = y;
	}

	@Override
	public void setX(double x) { // a method for setting the x value
		this.x = x;
	}

	@Override
	public void setY(double y) { // a method for setting the y value
		this.y = y;
	}

	@Override
	public void setWidth(double width) { // a method for setting the width value
		this.width = width;

	}

	@Override
	public void setHeight(double height) { // a method for setting the height value
		this.height = height;

	}
	public void setTexture(String key){ // a method for setting the Texture key value
		this.key = key;
		tex = textureLoader(key);
	}

	@Override
	public double getX() { // a method for returning x
		return x;
	}

	public double getY() { // a method for returning y
		return y;
	}

	@Override
	public double getWidth() { // a method for returning width
		return width;
	}

	@Override
	public double getHeight() { // a method for returning height
		return height;
	}
	public String getTextureKey(){ // a method for returning Texture Key
		return key;
	}
	public TextureImpl getTexture(){ // a method for returning Texture
		tex = textureLoader(key);
		return tex;
	}

	@Override
	public boolean intersects(Entity other) { //collision detection
		hitbox.setBounds((int)x, (int)y, (int) width, (int) height);
		return hitbox.intersects(other.getX(), other.getY(), other.getWidth(), other.getHeight());
	}
	//uses slick2d to create a texture to be used in the entity this method is derived from tutorial by Oskar Veerhoek source can by found at http://thecodinguniverse.com/lwjgl-textures/
	private TextureImpl textureLoader(String string){
		try{
			return (TextureImpl) TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + string + ".png"))); // returns texture
		}catch (FileNotFoundException e){ // checks file found errors
			e.printStackTrace(); 
		}catch (IOException e){ //checks std in and out errors
			e.printStackTrace();
		}
		return null;
	}

}
