package Model;
import java.awt.Dimension;
import java.awt.Toolkit;

import View.GUI;

public class Game {
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int screenWidth = ( int ) screenSize.getWidth() / 2;
	public static int screenHeight = ( int ) screenSize.getHeight() / 2;
	public static Ball ball = new Ball();
	public static Paddle paddle = new Paddle();
	public static Paddle paddle_2 = new Paddle();
	public static boolean pause;
	public static boolean reset;
	public static int timeLeft = 60;
	public boolean gameOver = false;
	public long updateTimer;
	public long gameTimer;
	public String winnerText;

	public Game() {
		new GUI( this ).start();
		timer();
		updateTimer = System.currentTimeMillis();
		gameTimer = System.currentTimeMillis();
	}
	
	public void update() {
		if( !Game.pause ) {
			timer();
			ball.setX( Game.ball.getX() + Game.ball.getVelX() );
			ball.setY( Game.ball.getY() + Game.ball.getVelY() );
			ball.outside();
			ball.colliding();
			paddle.outside();
			paddle_2.outside();
			if(reset == true) {
				reset = false;
				reset();
			}
		}
	}
	
	public void timer() {
		if( System.currentTimeMillis() - gameTimer >= 1000 ) {
			timeLeft--;
			if( timeLeft == 0 ) {
				ball.reset();
				if( paddle.getPlayer1Points() > paddle.getPlayer2Points() ) {
					winnerText = "Player 1 Won!";
				} 
				else if( paddle.getPlayer2Points() > paddle.getPlayer1Points() ) {
					winnerText = "Player 2 Won!";
				} else {
					winnerText = "Draw";
				}
				gameOver = true;
			}
			gameTimer = System.currentTimeMillis();
		}
	}
	
	public void reset() {
		gameOver = false;
		paddle.setPlayer1Points(0);
		paddle.setPlayer2Points(0);
		timeLeft = 60;
	}
}
