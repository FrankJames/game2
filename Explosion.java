import javalib.worldimages.*;
import javalib.colors.*;


// Explosions are created from Bombs and have firepower according to the hero's bomb power from upgrades

class Explosion {
	int timer;
	Posn pin;

	public Explosion( Posn pin ) {
		this.timer = 0;
		this.pin = pin;
	}

	public Explosion( int timer, Posn pin ) {
		this.timer = timer;
		this.pin = pin;
	}

	public WorldImage explosionView( ) {
		return new RectangleImage( pin, 30, 30, new Red( ) );
	}

	public Explosion  explosionTimeInc( ) {
		if ( timer < 5 ) {
			this.timer++;
			return this;
		} else {  return null; }
	}
}