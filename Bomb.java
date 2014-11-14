import javalib.worldimages.*;
import javalib.colors.*;

class Bomb {
	int timer;
	Posn pin;
	/*
	String filename;
	FromFileImage image;
	*/


	public Bomb( int timer, Posn pin ) {
		this.timer = timer;
		this.pin = pin;
	//	this.filename = "bomb.png";
	//	FromFileImage( pin, filename );
	}

	public Bomb( Posn pin ) {
		this.timer = 0;
		this.pin = pin;
	//	FromFileImage( pin, filename );
	}

	public WorldImage bombView( ) {
		return new RectangleImage( pin, 30, 30, new Blue( ) );
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