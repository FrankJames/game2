import javalib.worldimages.*;
import javalib.colors.*;

/*
 destructable rocks are: 
 	- non-passable terrain
 	- destroyable by explosions
 	- 

*/

 class DRock implements Rocks {
 	Posn pin;
 	int width;
 	int height;

 	public DRock( Posn pin ) {
 		this.pin = pin;
 		this.width = 50;
 		this.height = 50;
 	}

 	public boolean checkExplosion( Explosion e ) {
 		if ( e.pin.equals( this.pin ) )
 			return true;
 		else
 			return false;
 	}
 	
 	public WorldImage rockView( ) {
 		return new RectangleImage( pin, width, height, new Blue( ) );
 	}
 }