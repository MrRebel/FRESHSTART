/**
 * @author William Christensen
 * @date 9/22/15
 * 
 * An entity used to hold a word that can be returned in order to be placed on the screen
 */
package entities;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public abstract class AbstractWordEntity extends AbstractEntity implements WordEntity{

	protected int type;
	protected String fontName = "Times New Roman";
	protected String word;
	protected TrueTypeFont font;
	//simple implementation
	public AbstractWordEntity(double x, double y, double width, double height, String word) {
		super(x, y, width, height);
		this.word = word;
		font = fontLoader(fontName);
	}
	public AbstractWordEntity(double x, double y, double width, double height, String key, String word) {
		super(x, y, width, height, key);
		this.word = word;
		font = fontLoader(fontName);
	}
	public AbstractWordEntity(double x, double y, double width, double height, String word, String fontName, int type) {
		super(x, y, width, height);
		this.word = word;
		this.fontName = fontName;
		this.type = type;
		font = fontLoader(fontName);
	}
	//robust textured and font implementation
	public AbstractWordEntity(double x, double y, double width, double height, String key, String word, String fontName, int type) {
		super(x, y, width, height, key);
		this.word = word;
		this.fontName = fontName;
		this.type = type;
		font = fontLoader(fontName);
	}
	public void setWord(String word){ // sets word
		this.word = word;
	}
	public void setFont(String fontName){
		this.fontName = fontName;
		font = fontLoader(this.fontName);
	}
	public void setFontEffect(int type){
		this.type = type;
	}
	public String getWord(){ //returns word
		return word;
	}
	public int getFontEffect(){
		return type;
	}
	
	private TrueTypeFont fontLoader(String key){ // derived from code found at http://ninjacave.com/slickutil3
		Font awtFont = new Font("Times New Roman", type, 24);
		font = new TrueTypeFont(awtFont, false);
	 
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("Library/Fonts/" + key + ".ttf");
	 
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(24f);
			font = new TrueTypeFont(awtFont2, false);
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return font;
	}
	
}
