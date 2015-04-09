package entities;

public abstract class AbstractButtonEntity extends AbstractEntity implements ButtonEntity {

	public AbstractButtonEntity(double x, double y, double width, double height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public boolean inbounds (int mousex, int mousey){
		if (mousex > x && mousex < x + width && mousey > y && mousey < y + height){
			return true;
		}else{
			return false;
		}
	}

}
