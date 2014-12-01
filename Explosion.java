import javalib.worldimages.*;
import javalib.colors.*;

class Explosion {
	int timer;
	Posn pin;
	WorldImage image;
	int width;
	int height;

	public Explosion( Posn pin ) {
		this.timer = 0;
		this.pin = pin;
		this.image = new FromFileImage( pin, "images/explosion.png" );
		this.width = image.getWidth( );
		this.height = image.getHeight( );
	}

	public Explosion( int timer, Posn pin ) {
		this.timer = timer;
		this.pin = pin;
		this.image = new FromFileImage( pin, "images/explosion.png" );
		this.width = image.getWidth( );
		this.height = image.getHeight( );
	}

	public Posn getPin( ) {
		return pin;
	}

	public int getWidth( ) {
		return width;
	}

	public int getHeight( ) {
		return height;
	}

	public WorldImage explosionView( ) {
		return new FromFileImage( pin, "images/explosion.png" );
	}

	public Explosion explosionTimeInc( ) {
		return new Explosion( timer + 1, pin );
	}


}