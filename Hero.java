import javalib.worldimages.*;
import javalib.colors.*;

class Hero {
	int width;
	int height;
	Posn pin;
	int health;
	WorldImage image;

	public Hero( Posn pin, int health) {
		this.pin = pin;
		this.health = health;
		this.image = new FromFileImage( pin, "images/downhero.png" );
		this.width = image.getWidth( );
		this.height = image.getHeight( );
	}

	public Hero( Posn pin, int health, String picturename ) {
		this.width = 30;
		this.height = 50;
		this.pin = pin;
		this.health = health;
		this.image = new FromFileImage( pin, picturename );
	}


	public Hero heroMove( String ke ) {

		if ( ke.equals("right")) {
			this.pin = new Posn( this.pin.x + this.width, this.pin.y );
			return new Hero( this.pin, health, "images/righthero.png" );
		}
		else if ( ke.equals("left")) {
			this.pin = new Posn( this.pin.x - this.width, this.pin.y );
			return new Hero( this.pin, health, "images/lefthero.png" );
		}

		else if ( ke.equals("down")) {
			this.pin = new Posn(this.pin.x, this.pin.y + this.height);
			return new Hero( this.pin, health, "images/downhero.png" );
		}

		else if ( ke.equals("up")) {
			this.pin = new Posn(this.pin.x, this.pin.y - this.height);
			return new Hero( this.pin, health, "images/uphero.png" );
		}
		else {
			return this;
		}
	}

 	public boolean checkExplosion( Explosion e ) {
 		if ( e.pin.equals( this.pin ) )
 			return true;
 		else
 			return false;
 	}

	public WorldImage heroView( ) {
		return image;
	}
}