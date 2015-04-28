/**
 * @author William Christensen
 * @date 4/28/15
 * 
 * An extension of the basic Entity Interface it creates an interface that is used to make entities that are easily checked for being inbounds.
 */
package entities;

public interface ButtonEntity extends Entity{
	public boolean inbounds (int mousex, int mousey);
}
