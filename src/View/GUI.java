package View;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Controller.Keyboard;
import Model.Game;

@SuppressWarnings("serial")
public class GUI extends Canvas implements Runnable {
	private Thread thread;
	private boolean running;
	private JFrame frame;
	private Game game;
	
	public GUI( Game game ) {
		this.game = game;
		frame = new JFrame();
		setPreferredSize( new Dimension( Game.screenWidth, Game.screenHeight ));
		setBackground( Color.black ); 
		frame.addKeyListener( new Keyboard() );
		frame.setResizable( false );
		frame.setTitle( "Pong" );
		frame.add( this );
		frame.pack();
		frame.setLocationRelativeTo( null );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread( this );
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		while( running ) {
			long now = System.nanoTime();
			delta += ( now - lastTime ) / ns;
			lastTime = now;
			while(delta >= 1) {
				game.update();
				delta--;
			}
			render();
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if( bs == null ) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor( new Color(0, 0, 0, 105 )); 
		g.fillRect( 0, 0, getWidth(), getHeight() );
		g.setColor( Color.white );
		if( game.gameOver == true ) {
			String text = "GAME OVER! Press R to replay";
			int w = g.getFontMetrics().stringWidth( text );
			g.drawString( text , Game.screenWidth/2 - w/2, Game.screenHeight/2 + 20 );
			int w2 = g.getFontMetrics().stringWidth( game.winnerText );
			g.drawString( game.winnerText, Game.screenWidth/2 - w2/2, Game.screenHeight/2 - 20 );
		} else {
			if( !Game.pause ) {
				String time = "" + Game.timeLeft;
				int w = g.getFontMetrics().stringWidth( time );
				g.drawLine( Game.screenWidth/2, 40, Game.screenWidth/2, Game.screenHeight - 40 );
				g.drawLine( 0, 40, Game.screenWidth, 40 );
				g.drawLine( 0, Game.screenHeight - 40, Game.screenWidth, Game.screenHeight - 40 );
				g.drawString( time, Game.screenWidth/2 - w/2, 25 );
				g.drawString( "" + Game.paddle.getPlayer1Points(), 30, 25 );
				g.drawString( "" + Game.paddle.getPlayer2Points(), Game.screenWidth - 35, 25 );
				String text = "Press Escape to pause!";
				int w2 = g.getFontMetrics().stringWidth( text );
				g.drawString( "Press Escape to pause!" , Game.screenWidth/2 - w2/2, Game.screenHeight - 15 );
				g.fillRect( Game.paddle.getX(), Game.paddle.getY(), Game.paddle.getWidth(), Game.paddle.getHeight() );
				g.fillRect( Game.paddle_2.getXP2(), Game.paddle_2.getY(), Game.paddle_2.getWidth(), Game.paddle_2.getHeight() );
				g.fillOval( Game.ball.getX(), Game.ball.getY(), Game.ball.getWidth(), Game.ball.getHeight() );
			}
		}
		if( Game.pause && game.gameOver == false ) {
			String text = "The Game is Paused!";
			int w = g.getFontMetrics().stringWidth( text );
			g.drawString( text, Game.screenWidth/2 - w/2, Game.screenHeight/2 );
		}
		g.dispose();
		bs.show();
	}
}