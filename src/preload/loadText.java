package preload;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class loadText {
	TrueTypeFont a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,space,ac,bc,cc,dc,ec,fc,gc,hc,ic,jc,kc,lc,mc,nc,oc,pc,qc,rc,sc,tc,uc,vc,wc,xc,yc,zc;
	
	TrueTypeFont[] letters ={
			a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,space,ac,bc,cc,dc,ec,fc,gc,hc,ic,jc,kc,lc,mc,nc,oc,pc,qc,rc,sc,tc,uc,vc,wc,xc,yc,zc
	};
	
	public TrueTypeFont[] loadText(String fontName, int type){
		for (int i = 0; i < letters.length; i++) {
			letters[i]= fontLoader(fontName,letters[i],type);
		}
		return letters;
	}
	
	private static TrueTypeFont fontLoader(String fontName, TrueTypeFont font, int type){ // derived from code found at http://ninjacave.com/slickutil3
		Font awtFont = new Font(fontName, type, 24);
		font = new TrueTypeFont(awtFont, false);
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("/Library/Fonts/" + fontName + ".ttf");
	 
			Font awtFont2 = Font.createFont(Font.PLAIN, inputStream);
			awtFont2 = awtFont2.deriveFont(24f);
			font = new TrueTypeFont(awtFont, true);
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return font;
	}
}
