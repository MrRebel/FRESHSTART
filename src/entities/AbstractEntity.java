/**
 * @author William
 * @date 3/9/15
 * 
 * this class creates an AbstactEnitity class to work with the Entity object
 */
package entities;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public abstract class AbstractEntity implements Entity{
	
	protected double x, y, width, height;
	protected String key;
	protected Texture tex;
	protected Rectangle hitbox = new Rectangle();
	//most basic implementation of the Entity
	public AbstractEntity(double x,double y,double width,double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	//textured implementation
	public AbstractEntity(double x,double y,double width,double height,String key) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.key = key;
		tex = textureLoader(key);
	}
	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void setWidth(double width) {
		this.width = width;

	}

	@Override
	public void setHeight(double height) {
		this.height = height;

	}
	public void setTexture(String key){
		this.key = key;
		tex = textureLoader(key);
	}

	@Override
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}
	public String getTextureKey(){
		return key;
	}
	public Texture getTexture(){
		tex = textureLoader(key);
		return tex;
	}

	@Override
	public boolean intersects(Entity other) {
		hitbox.setBounds((int)x, (int)y, (int) width, (int) height);
		return hitbox.intersects(other.getX(), other.getY(), other.getWidth(), other.getHeight());
	}
	//uses slick2d to create a texture to be used in the entity
	private Texture textureLoader(String string){
		try{
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + string + ".png")));
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

}
