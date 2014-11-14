import javalib.worldimages.*;
import javalib.colors.*;

class Hero {
	int width;
	int height;
	Posn pin;
	int health;

	public Hero(int width, int height, Posn pin, int health) {
		this.width = width;
		this.height = height;
		this.pin = pin;
		this.health = health;
	}

	public Hero( Posn pin, int health) {
		this.width = 30;
		this.height = 50;
		this.pin = pin;
		this.health = health;
	}


	public Hero heroMove( String ke ) {

		if ( ke.equals("right")) {
			this.pin = new Posn( this.pin.x + this.width, this.pin.y );
			return new Hero( this.pin, health );
		}
		else if ( ke.equals("left")) {
			this.pin = new Posn( this.pin.x - this.width, this.pin.y );
			return new Hero( this.pin, health );
		}

		else if ( ke.equals("down")) {
			this.pin = new Posn(this.pin.x, this.pin.y + this.height);
			return new Hero( this.pin, health );
		}

		else if ( ke.equals("up")) {
			this.pin = new Posn(this.pin.x, this.pin.y - this.height);
			return new Hero( this.pin, health );
		}
		else {
			return this;
		}
	}

	public WorldImage heroView( ) {
		return new RectangleImage( pin, width, height, new Red() ); // red is a placeholder for the sprite 
	}
}