import javalib.worldimages.*;
import javalib.colors.*;



class NDRock implements Rocks {
	Posn pin;
 	int width;
 	int height;
 	WorldImage image;

  	public NDRock( Posn pin ) {
 		this.pin = pin;
 		this.width = 50;
 		this.height = 50;
 		this.image = new FromFileImage( pin, "images/ndrock.png" );
 	}

 	// non-destructable rocks will never explode
  	public boolean checkExplosion( Explosion e ) {
  		return false;
 	}
 	
 	public WorldImage rockView( ) {
 		return image;
 	}

 	public Posn getPin( ) {
 		return pin;
 	}

  	public int getWidth( ) {
 		return width;
 	}

 	public int getHeight( ) {
 		return height;
 	}
}