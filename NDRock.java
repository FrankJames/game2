import javalib.worldimages.*;
import javalib.colors.*;



class NDRock implements Rocks {
	Posn pin;
 	int width;
 	int height;

  	public NDRock( Posn pin ) {
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
 		return new RectangleImage( pin, width, height, new Brown( ) );
 	}
}