package Model;

public class Paddle extends Entity {
	private int width = 15;
	private int height = 100;
	private int x = width;
	private int xP2 = Game.screenWidth - width * 2;
	private int y = Game.screenHeight / 2 - height / 2;
	private int player1Points;
	private int player2Points;
	
	@Override
	public void outside() {
		if( y < 0 + 40 ) {
			y = 40;
		}
		else if( y + height > Game.screenHeight - 40 ) {
			y = Game.screenHeight - height - 40;
		}
	}
	
	@Override
	public int getX() { return x; }
	@Override
	public void setX( int x ) { this.x = x; }
	@Override 
	public int getY() { return y; }
	@Override
	public void setY( int y ) { this.y = y; }
	@Override
	public int getWidth() { return width; }
	@Override
	public int getHeight() { return height; }
	public int getXP2() { return xP2; }
	
	public int getPlayer1Points() { return player1Points; }
	public void setPlayer1Points( int player1Points ) { this.player1Points = player1Points; }
	public int getPlayer2Points() { return player2Points; }
	public void setPlayer2Points( int player2Points ) { this.player2Points = player2Points; }
}
