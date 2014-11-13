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
		this.timer = 0;
		this.pin = pin;
	}

	public WorldImage bombView( ) {
		return new RectangleImage( pin, 10, 10, new Blue( ) );
	}

	public Bomb bombTimeInc( ) {
		this.timer++;
		return this;
	}

	public boolean checkGoBoom( ) {
		if (this.timer > 10) {
			return true;
		} else {
			return false;
		}
	}
}