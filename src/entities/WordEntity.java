/**
 * @author William Christensen
 * @date 4/28/15
 * 
 * An extension of the basic Entity that can be used to create text on the screen.
 */
package entities;

import java.util.Hashtable;

import org.newdawn.slick.TrueTypeFont;

public interface WordEntity extends Entity {
	public void setWord(String word);
	public void setFont(String font);
	public void setFontEffect(int type);
	public void setFontPreloaded(String[] fontNames, int[] types);
	public String getWord();
	public String getFont();
	public int getFontEffect();
}
