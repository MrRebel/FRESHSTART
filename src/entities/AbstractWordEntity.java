/**
 * @author William Christensen
 * @date 9/22/15
 * 
 * An entity used to hold a word that can be returned in order to be placed on the screen
 */
package entities;

import java.awt.Font;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import preload.loadText;

public abstract class AbstractWordEntity extends AbstractEntity implements WordEntity{

	protected static int type = Font.PLAIN;
	protected static String fontName = "Times New Roman";
	protected String word = "";
	protected int[] types = {Font.PLAIN};
	protected String[] fontNames = {"Times New Roman"};
	protected static TrueTypeFont font;
	protected HashMap<String,TrueTypeFont> preloader = new HashMap<String,TrueTypeFont>(26);
	TrueTypeFont a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,space,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,one,two,three,four,five,six,seven,eight,nine,zero,qm,pm,cm;	
	TrueTypeFont[] letters ={
			a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,space,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,one,two,three,four,five,six,seven,eight,nine,zero,qm,pm,cm
			};
	String[] lvalues ={
			"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"," ","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","1","2","3","4","5","6","7","8","9","0","?",".",","	
			};
	//simple implementation
	public AbstractWordEntity(double x, double y, String word) {
		super(x, y, 10, 10);
		this.word = word;
		font = fontLoader(fontName, font, type);
		setHeight(font.getHeight(word));
		setWidth(font.getWidth(word));
	}
	public AbstractWordEntity(double x, double y, String key, String word) {
		super(x, y, font.getWidth(word), font.getHeight(word), key);
		this.word = word;
	}
	public AbstractWordEntity(double x, double y, String word, String fontName, int type) {
		super(x, y, font.getWidth(word), font.getHeight(word));
		this.word = word;
		this.fontName = fontName;
		this.type = type;
	}
	//robust textured and font implementation
	public AbstractWordEntity(double x, double y, String key, String word, String fontName, int type) {
		super(x, y, font.getWidth(word), font.getHeight(word), key);
		this.word = word;
		this.fontName = fontName;
		this.type = type;
	}
	public void setWord(String word){
		this.word = word;
	}
	public void setFont(String font){
		if(Arrays.asList(fontNames).contains(font)){
			fontName = font;
		}else{
			throw new IllegalArgumentException("Font was not preloaded");
		}
	}
	public void setFontEffect(int type){
		if(Arrays.asList(types).contains(type)){
			this.type=type;
		}else{
			throw new IllegalArgumentException("Type was not preloaded");
		}
	}
	public void setFontPreloaded(String[] fontNames, int[] types){
		this.fontNames = fontNames;
		this.types = types;
		for(String f: fontNames){
			for(int g: types){
				String temp = "";
				if(g == Font.BOLD){
					temp = "bold";
				}
				else if (g == Font.ITALIC){
					temp = "italic";
				}
				for (int i = 0; i < letters.length; i++) {
					preloader.put(lvalues[i] + f + temp,fontLoader(f,letters[i],g));
				}
			}
		}
	}
	public String getWord(){
		return word;
	}
	public String getFont(){
		return fontName;
	}
	public int getFontEffect(){
		return type;
	}
	private void preload(String[] fontNames, int[] types){
		for(String f: fontNames){
			for(int g: types){
				String temp = "";
				if(g == Font.BOLD){
					temp = "bold";
				}
				else if (g == Font.ITALIC){
					temp = "italic";
				}
				for (int i = 0; i < letters.length; i++) {
					preloader.put(lvalues[i] + f + temp,fontLoader(f,letters[i],g));
				}
			}
		}
	}
	private TrueTypeFont[] loadText(String fontName, int type){
		for (int i = 0; i < letters.length; i++) {
			letters[i]= fontLoader(fontName,letters[i],type);
		}
		return letters;
	}
	private static TrueTypeFont fontLoader(String key, TrueTypeFont font, int type){ // derived from code found at http://ninjacave.com/slickutil3
		
		Font awtFont = new Font(key, type, 24);
		font = new TrueTypeFont(awtFont, false);
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("/Library/Fonts/" + key + ".ttf");
	 
			Font awtFont2 = Font.createFont(Font.PLAIN, inputStream);
			awtFont2 = awtFont2.deriveFont(24f);
			font = new TrueTypeFont(awtFont, true);
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return font;
	}
	public void draw(){
		int tempx = 0;
		for (int i = 0; i<word.toCharArray().length; i++){
			String temp = ""; 
			if(type == Font.BOLD){
				temp = "bold";
			}
			else if (type == Font.ITALIC){
				temp = "italic";
			}
			preloader.get(word.toCharArray()[i] + fontName + temp).drawString((int)getX() + tempx, (int)getY(),  "" + word.toCharArray()[i],Color.yellow);
			tempx += preloader.get(word.toCharArray()[i] + fontName + temp).getWidth("" + word.toCharArray()[i]);
		}
		
	}
}
