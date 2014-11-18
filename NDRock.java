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

 	// non-destructable rocks will never explode
  	public boolean checkExplosion( Explosion e ) {
  		return false;
 	}
 	
 	public WorldImage rockView( ) {
 		return new RectangleImage( pin, width, height, new Black( ) );
 	}
}