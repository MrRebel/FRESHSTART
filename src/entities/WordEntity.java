/**
 * @author William Christensen
 * @date 4/28/15
 * 
 * An extension of the basic Entity that can be used to create text on the screen.
 */
package entities;

public interface WordEntity extends Entity {
	public void setWord(String score);
	public void setFont(String font);
	public void setFontEffect(int type);
	public String getWord();
	public String getFont();
	public int getFontEffect();
}
