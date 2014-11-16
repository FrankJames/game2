import javalib.worldimages.*;
import javalib.colors.*;

class Explosion {
	int timer;
	Posn pin;
	WorldImage image;

	public Explosion( Posn pin ) {
		this.timer = 0;
		this.pin = pin;
		this.image = new FromFileImage( pin, "images/explosion.png" );
	}

	public Explosion( int timer, Posn pin ) {
		this.timer = timer;
		this.pin = pin;
		this.image = new FromFileImage( pin, "images/explosion.png" );
	}

	public WorldImage explosionView( ) {
		return new FromFileImage( pin, "images/explosion.png" );
	}

	public Explosion  explosionTimeInc( ) {
		if ( timer < 5 ) {
			this.timer++;
			return this;
		} else {  return null; }
	}

	
}