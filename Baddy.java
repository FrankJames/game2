import java.util.*;

import javalib.worldimages.*;
import javalib.colors.*;


// has to store the last move taken so that it can have a simple AI



class Baddy implements Enemies {
	Posn pin;
	int width;
	int height;
	int lastMove;

	public Baddy( Posn pin, int width, int height, int lastMove ) {
		this.pin = pin;
		this.width = width;
		this.height = height;
		this.lastMove = lastMove;
	}

	public Baddy( Posn pin, int lastMove ) {
		this.pin = pin;
		this.width = 50;
		this.height = 50;
		this.lastMove = lastMove;
	}

	public boolean checkHitHero( Hero h ) {
		return false;
	}

	// continues moving in the same direction
	public Baddy enemyMove( ) {
		if ( lastMove == 0 ) {
			return new Baddy( new Posn( pin.x, pin.y + height), width, height, 0 );
		} else 	if ( lastMove == 1 ) {
			return new Baddy( new Posn( pin.x + width, pin.y), width, height, 1 );
		} else if ( lastMove == 2 ) {
			return new Baddy( new Posn( pin.x, pin.y - height), width, height, 2 );
		} else
			return new Baddy( new Posn( pin.x - width, pin.y), width, height, 3 );
	}

	// change direction!
	public Baddy enemyChangeDirection( ) {
		int randDir = (int)(Math.random( ) * 4 );
		return new Baddy( pin, width, height, randDir );
	}


 	// returns true if there is a collision
 	public boolean checkRock( Rocks r ) {
 		int a1 = this.pin.x;
		int a2 = r.getPin( ).x;
		int b1 = this.pin.y;
		int b2 = r.getPin( ).y;

		int halfBaddyWidth = this.width / 2;
		int halfRockWidth = r.getWidth( ) / 2;
		int halfBaddyHeight = this.height / 2;
		int halfRockHeight = r.getHeight( ) / 2;

 		if ( ( Math.abs(a1 - a2) < ( halfRockWidth + halfBaddyWidth ) )
			&& ( Math.abs(b1 - b2) < ( halfRockHeight + halfBaddyHeight ) ) )
 			return true;
 		else
 			return false;
 	}

	public WorldImage enemyView( ) {
		return new RectangleImage( pin, 50, 50, new Blue( ) );
	}
 	public boolean checkExplosion( Explosion e ) {
 		return false;
 	}

}