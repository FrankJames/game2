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
		this.pin = pin;
		this.health = health;
		this.image = new FromFileImage( pin, picturename );
		this.width = image.getWidth( );
		this.height = image.getHeight( );
	}


	public Hero heroMove( String ke ) {


		if ( ke.equals("right")) {
			Posn pin2 = new Posn( this.pin.x + this.width, this.pin.y );
			return new Hero( pin2, health, "images/righthero.png" );
		}
		else if ( ke.equals("left")) {
			Posn pin2 = new Posn( this.pin.x - this.width, this.pin.y );
			return new Hero( pin2, health, "images/lefthero.png" );
		}

		else if ( ke.equals("down")) {
			Posn pin2 = new Posn(this.pin.x, this.pin.y + this.height);
			return new Hero( pin2, health, "images/downhero.png" );
		}

		else if ( ke.equals("up")) {
			Posn pin2 = new Posn(this.pin.x, this.pin.y - this.height);
			return new Hero( pin2, health, "images/uphero.png" );
		}
		else {
			return this;
		}
	}

	public Hero heroMove( String ke, int div ) {


		if ( ke.equals("right")) {
			Posn pin2 = new Posn( this.pin.x + (this.width / div), this.pin.y );
			return new Hero( pin2, health, "images/righthero.png" );
		}
		else if ( ke.equals("left")) {
			Posn pin2 = new Posn( this.pin.x - (this.width / div), this.pin.y );
			return new Hero( pin2, health, "images/lefthero.png" );
		}

		else if ( ke.equals("down")) {
			Posn pin2 = new Posn(this.pin.x, this.pin.y + (this.height / div));
			return new Hero( pin2, health, "images/downhero.png" );
		}

		else if ( ke.equals("up")) {
			Posn pin2 = new Posn(this.pin.x, this.pin.y - (this.height / div));
			return new Hero( pin2, health, "images/uphero.png" );
		}
		else {
			return this;
		}
	}

	public Hero heroSet( Posn pin2 ) {
		return new Hero( pin2, this.health );
	}

	// in overworld, iterate through, check if this is flagged on clock tick
 	public boolean checkExplosion( Explosion e ) {
		int a1 = this.pin.x;
		int a2 = e.getPin( ).x;
		int b1 = this.pin.y;
		int b2 = e.getPin( ).y;

		int halfHeroWidth = this.width / 2;
		int halfExpWidth = e.getWidth( ) / 2;
		int halfHeroHeight = this.height / 2;
		int halfExpHeight = e.getHeight( ) / 2;

 		if ( ( Math.abs(a1 - a2) < ( halfExpWidth + halfHeroWidth ) )
			&& ( Math.abs(b1 - b2) < ( halfExpHeight + halfHeroHeight ) ) )
 			return true;
 		else
 			return false;
 	}

 	// returns true if there is a collision
 	public boolean checkRock( Rocks r ) {
 		int a1 = this.pin.x;
		int a2 = r.getPin( ).x;
		int b1 = this.pin.y;
		int b2 = r.getPin( ).y;

		int halfHeroWidth = this.width / 2;
		int halfRockWidth = r.getWidth( ) / 2;
		int halfHeroHeight = this.height / 2;
		int halfRockHeight = r.getHeight( ) / 2;

 		if ( ( Math.abs(a1 - a2) < ( halfRockWidth + halfHeroWidth ) )
			&& ( Math.abs(b1 - b2) < ( halfRockHeight + halfHeroHeight ) ) )
 			return true;
 		else
 			return false;
 	}

 	public boolean checkEnemy( Enemies e ) {
 		int a1 = this.pin.x;
		int a2 = e.getPin( ).x;
		int b1 = this.pin.y;
		int b2 = e.getPin( ).y;

		int halfHeroWidth = this.width / 2;
		int halfEnemyWidth = e.getWidth( ) / 2;
		int halfHeroHeight = this.height / 2;
		int halfEnemyHeight = e.getHeight( ) / 2;

 		if ( ( Math.abs(a1 - a2) < ( halfEnemyWidth + halfHeroWidth ) )
			&& ( Math.abs(b1 - b2) < ( halfEnemyHeight + halfHeroHeight ) ) )
 			return true;
 		else
 			return false;
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

	public WorldImage heroView( ) {
		return image;
	}


}