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
		this.image = new FromFileImage( pin, "images/bomb.png" );
	}

	public Bomb( Posn pin ) {
		this.timer = 0;
		this.pin = pin;
		this.image = new FromFileImage( pin, "images/bomb.png" );
	}

 	public boolean checkExplosion( Explosion e ) {
 		if ( e.pin.equals( this.pin ) )
 			return true;
 		else
 			return false;
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

	public WorldImage bombView( ) {
		return new FromFileImage( pin, "images/bomb.png" );
	}
}