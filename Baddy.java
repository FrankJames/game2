import java.util.*;

import javalib.worldimages.*;
import javalib.colors.*;


// has to store the last move taken so that it can have a simple AI



class Baddy implements Enemies {
	Posn pin;
	int width;
	int height;
	int lastMove;
	WorldImage image;

	public Baddy( Posn pin, int lastMove ) {
		this.pin = pin;
		this.image = new FromFileImage( pin, "images/downbaddy.png" );
		this.width = image.getWidth( );
		this.height = image.getHeight( );
		this.lastMove = lastMove;
	}

	public Baddy( Posn pin, String picturename, int lastMove ) {
		this.pin = pin;
		this.image = new FromFileImage( pin, picturename );
		this.width = image.getWidth( );
		this.height = image.getHeight( );
		this.lastMove = lastMove;
	}

	public boolean checkHitHero( Hero h ) {
		return false;
	}

	// continues moving in the same direction
	public Baddy enemyMove( ) {
		if ( lastMove == 0 ) {
			return new Baddy( new Posn( pin.x, pin.y + 20), "images/downbaddy.png", 0 );
		} else 	if ( lastMove == 1 ) {
			return new Baddy( new Posn( pin.x + 20, pin.y), "images/rightbaddy.png", 1 );
		} else if ( lastMove == 2 ) {
			return new Baddy( new Posn( pin.x, pin.y - 20), "images/upbaddy.png", 2 );
		} else
			return new Baddy( new Posn( pin.x - 20, pin.y), "images/leftbaddy.png", 3 );
	}

	// change direction!
	public Baddy enemyChangeDirection( ) {
		int randDir = (int)(Math.random( ) * 4 );
		if ( randDir == 0 ) {
			return new Baddy( pin, "images/downbaddy.png", 0 );
		} else 	if ( randDir == 1 ) {
			return new Baddy( pin, "images/rightbaddy.png", 1 ); 
		} else if ( randDir == 2 ) {
			return new Baddy( pin, "images/upbaddy.png", 2 ); 
		} else
			return new Baddy( pin, "images/leftbaddy.png", 3 ); 
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
		return image;
	}
 	public boolean checkExplosion( Explosion e ) {
 		 int a1 = this.pin.x;
		int a2 = e.getPin( ).x;
		int b1 = this.pin.y;
		int b2 = e.getPin( ).y;

		int halfBaddyWidth = this.width / 2;
		int halfExpWidth = e.getWidth( ) / 2;
		int halfBaddyHeight = this.height / 2;
		int halfExpHeight = e.getHeight( ) / 2;

 		if ( ( Math.abs(a1 - a2) < ( halfExpWidth + halfBaddyWidth ) )
			&& ( Math.abs(b1 - b2) < ( halfExpHeight + halfBaddyHeight ) ) )
 			return true;
 		else
 			return false;
 	}

}