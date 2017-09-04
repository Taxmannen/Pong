package Model;
import View.SoundManager;

public class Ball extends Entity {
	private int width = 15;
	private int height = 15;
	private int velX;
	private int velY;
	private double dir;
	private int x = Game.screenWidth/2 - width/2;
	private int y = Game.screenHeight/2 - height/2;

	public void serve() {
		SoundManager serve = new SoundManager( "serve.wav" );
		dir = Math.random();
		if( dir < 0.25 ) {
			velX = -2;
			velY = -2;
		}
		if( ( dir > 0.25 ) && dir < 0.5 ) { 
			velX = 2;
			velY = 2;
		}
		if( ( dir > 0.5 ) && dir < 0.75 ) {
			velX = -2;
			velY = 2;
		}
		if( dir > 0.75 ) {
			velX = 2;
			velY = -2; 
		}
		serve.playSoundOnce();
	}
	
	public void colliding() {
		SoundManager hit = new SoundManager( "hit.wav" );
		if( x > Game.paddle.getX() && x < Game.paddle.getX() + Game.paddle.getWidth() && y > Game.paddle.getY() && y < Game.paddle.getY() + Game.paddle.getHeight() ) {
			hit.playSoundOnce();
			velX =- velX + 1;
		    velY =+ velY + 1;
		} 
		if( x > Game.paddle_2.getXP2() - Game.paddle_2.getWidth() && x < Game.paddle_2.getXP2() + Game.paddle_2.getWidth() && y > Game.paddle_2.getY() && y < Game.paddle_2.getY() + Game.paddle_2.getHeight()) {
			hit.playSoundOnce();
			velX =- velX - 1;
			velY =+ velY - 1;
		}
	}
	
	@Override
	public void outside() {
		SoundManager goal = new SoundManager( "goal.wav" );
		SoundManager hit = new SoundManager( "hit.wav" );
		if( x < 0 ) {
			Game.paddle.setPlayer2Points( Game.paddle.getPlayer2Points() + 10 );
			goal.playSoundOnce();
			reset();
		}
		else if( x + width > Game.screenWidth ) {
			Game.paddle.setPlayer1Points( Game.paddle.getPlayer1Points() + 10 );
			goal.playSoundOnce();
			reset();
		}
		if( y < 0 + 40 ) {
			hit.playSoundOnce();
			velX =+ velX;
			velY =- velY;
		}
		else if( y + height > Game.screenHeight  - 40 ) {
			hit.playSoundOnce();
			velX =+ velX;
			velY =- velY;
		}
	}
	
	public void reset() {
		x = Game.screenWidth/2 - width/2;
		y = Game.screenHeight/2 - height/2;
		velX = 0;
		velY = 0;
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
	
	public int getVelX() { return velX; }
	public void setVelX( int velX ) { this.velX = velX; }
	public int getVelY() { return velY; }
	public void setVelY( int velY ) { this.velY = velY; }

}
