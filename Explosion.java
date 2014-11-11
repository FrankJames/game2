import javalib.worldimages.*;
import javalib.colors.*;


// Explosions are created from Bombs and have firepower according to the hero's bomb power from upgrades

class Explosion {
	int timer;
	Posn pin;

	public Explosion( int timer, Posn pin ) {
		this.timer = timer;
		this.pin = pin;
	}

	public WorldImage explosionView( ) {
		return new RectangleImage( pin, 10, 10, new Red( ) );
	}

	public Explosion  explosionTimeInc( ) {
		if ( timer < 20 ) {
			return new Explosion( timer + 1, pin );
		} else {  return null; }
	}
	
}