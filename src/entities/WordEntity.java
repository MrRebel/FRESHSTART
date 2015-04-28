package entities;

public interface WordEntity extends Entity {
	public void setScore(int score);
	public void setFont(String font);
	public void setFontEffect(int type);
	public int getScore();
	public String getFont();
	public int getFontEffect();
}
