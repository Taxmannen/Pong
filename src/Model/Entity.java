package Model;
public abstract class Entity {

	public Entity() {}
	
	public abstract void outside();
	public abstract int getX();
	public abstract void setX( int x );
	public abstract int getY();
	public abstract void setY( int y );
	public abstract int getWidth();
	public abstract int getHeight();
}
