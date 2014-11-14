import javalib.worldimages.*;
import javalib.colors.*;
import java.io.*;


class Bomb {
	int timer;
	Posn pin;
	WorldImage image;


	public Bomb( int timer, Posn pin ) {
		this.timer = timer;
		this.pin = pin;
		this.image = new FromFileImage( pin, "bomb.png" );
	}

	public Bomb( Posn pin ) {
		this.timer = 0;
		this.pin = pin;
		this.image = new FromFileImage( pin, "bomb.png" );
	}

	public WorldImage bombView( ) {
		return this.image;
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