import javalib.worldimages.*;
import javalib.colors.*;

 class DRock implements Rocks {
 	Posn pin;
 	int width;
 	int height;
 	WorldImage image;

 	public DRock( Posn pin ) {
 		this.pin = pin;
 		this.width = 50;
 		this.height = 50;
 		this.image = new FromFileImage( pin, "images/drock.png" );
 	}

 	public boolean checkExplosion( Explosion e ) {
 		int a1 = this.pin.x;
		int a2 = e.pin.x;
		int b1 = this.pin.y;
		int b2 = e.pin.y;

		int halfRockWidth = this.width / 2;
		int halfExpWidth = e.width / 2;
		int halfRockHeight = this.height / 2;
		int halfExpHeight = e.height / 2;

 		if ( ( Math.abs(a1 - a2) < ( halfExpWidth + halfRockWidth ) )
			&& ( Math.abs(b1 - b2) < ( halfExpHeight + halfRockHeight ) ) )
 			return true;
 		else
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