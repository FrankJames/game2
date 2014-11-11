import javalib.worldimages.*;
import javalib.colors.*;

class Bomb {
	int timer;
	Posn pin;

	public Bomb( int timer, Posn pin ) {
		this.timer = timer;
		this.pin = pin;
	}

	public Bomb( Posn pin ) {
		this.timer = 30;
		this.pin = pin;
	}

	public WorldImage bombView( ) {
		return new RectangleImage( pin, 10, 10, new Blue( ) );
	}

	public Bomb  bombTimeInc( ) {
		return new Bomb( timer + 1, pin );
	}

	public boolean goBoom( ) {
		if (this.timer > 20) {
			return true;
		} else {
			return false;
		}
	}
}