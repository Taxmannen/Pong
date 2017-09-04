package Controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Model.Game;

public class Keyboard extends KeyAdapter {	
	
	public void keyPressed( KeyEvent e ) {			
		int code = e.getKeyCode();
		if( !Game.pause ) {
			if( code == KeyEvent.VK_W ) {
				Game.paddle.setY( Game.paddle.getY() - 10 );
			} 
			if( code == KeyEvent.VK_S ) {
				Game.paddle.setY( Game.paddle.getY() + 10 );
			}	
			
			if( code == KeyEvent.VK_UP ) {
				Game.paddle_2.setY( Game.paddle_2.getY() - 10 );
			} 
			if( code == KeyEvent.VK_DOWN ) {
				Game.paddle_2.setY( Game.paddle_2.getY() + 10 );
			}	
			if( code == KeyEvent.VK_SPACE ) {
				if( Game.ball.getX() == Game.screenWidth/2 - Game.ball.getWidth()/2 && Game.ball.getY() == Game.screenHeight/2 - Game.ball.getHeight()/2) {
					Game.ball.serve();
				}
			}
			if( code == KeyEvent.VK_R ) {
				if( Game.timeLeft == 0 ) {
					Game.reset = true;
				}
			}
		}
		if( code == KeyEvent.VK_ESCAPE ) {
			Game.pause = !Game.pause;
		}	
	}
}	