import javalib.worldimages.*;
import javalib.colors.*;
import java.io.*;


class Bomb {
	int timer;
	int width;
	int height;
	Posn pin;
	WorldImage image;


	public Bomb( int timer, Posn pin ) {
		this.timer = timer;
		this.pin = pin;
		this.image = new FromFileImage( pin, "images/bomb.png" );
		this.width = image.getWidth( );
		this.height = image.getHeight( );
	}

	public Bomb( Posn pin ) {
		this.timer = 0;
		this.pin = pin;
		this.image = new FromFileImage( pin, "images/bomb.png" );
		this.width = image.getWidth( );
		this.height = image.getHeight( );
	}

	public Bomb bombTimeInc( ) {
		return new Bomb( timer + 1, pin );
	}

	public boolean checkGoBoom( ) {
		if (this.timer > 10) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkExplosion( Explosion e ) {
 		int a1 = this.pin.x;
		int a2 = e.pin.x;
		int b1 = this.pin.y;
		int b2 = e.pin.y;

		int halfBombWidth = this.width / 2;
		int halfExpWidth = e.width / 2;
		int halfBombHeight = this.height / 2;
		int halfExpHeight = e.height / 2;

 		if ( ( Math.abs(a1 - a2) < ( halfExpWidth + halfBombWidth ) )
			&& ( Math.abs(b1 - b2) < ( halfExpHeight + halfBombHeight ) ) ) {

 			return true;
 		} else {
 			return false;
 		}
 	}

	public WorldImage bombView( ) {
		return new FromFileImage( pin, "images/bomb.png" );
	}
}