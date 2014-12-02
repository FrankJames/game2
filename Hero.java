import javalib.worldimages.*;
import javalib.colors.*;

class Hero {
	int width;
	int height;
	Posn pin;
	WorldImage image;
	boolean canBeHit;
	int timer;

	public Hero( Posn pin, String picturename, boolean canBeHit, int timer ) {
		this.pin = pin;
		this.image = new FromFileImage( pin, picturename );
		this.width = image.getWidth( );
		this.height = image.getHeight( );
		this.canBeHit = canBeHit;
		this.timer = timer;
	}


	public Hero( Posn pin ) {
		this.pin = pin;
		this.image = new FromFileImage( pin, "images/downhero.png" );
		this.width = image.getWidth( );
		this.height = image.getHeight( );
		this.canBeHit = true;
		this.timer = 0;
	}

	public Hero( Posn pin, WorldImage image, boolean canBeHit, int timer ) {
		this.pin = pin;
		this.image = image;
		this.width = image.getWidth( );
		this.height = image.getHeight( );
		this.canBeHit = canBeHit;
		this.timer = timer;
	}


	public Hero heroMove( String ke ) {
		if ( ke.equals("right")) {
			Posn pin2 = new Posn( this.pin.x + this.width, this.pin.y );
			return new Hero( pin2, "images/righthero.png", canBeHit, timer );
		}
		else if ( ke.equals("left")) {
			Posn pin2 = new Posn( this.pin.x - this.width, this.pin.y );
			return new Hero( pin2, "images/lefthero.png", canBeHit, timer );
		}

		else if ( ke.equals("down")) {
			Posn pin2 = new Posn(this.pin.x, this.pin.y + this.height);
			return new Hero( pin2, "images/downhero.png", canBeHit, timer );
		}

		else if ( ke.equals("up")) {
			Posn pin2 = new Posn(this.pin.x, this.pin.y - this.height);
			return new Hero( pin2, "images/uphero.png", canBeHit, timer );
		}
		else {
			return this;
		}
	}

	public Hero heroMove( String ke, int div ) {
		if ( ke.equals("right")) {
			Posn pin2 = new Posn( this.pin.x + (this.width / div), this.pin.y );
			return new Hero( pin2, "images/righthero.png", canBeHit, timer );
		}
		else if ( ke.equals("left")) {
			Posn pin2 = new Posn( this.pin.x - (this.width / div), this.pin.y );
			return new Hero( pin2, "images/lefthero.png", canBeHit, timer );
		}

		else if ( ke.equals("down")) {
			Posn pin2 = new Posn(this.pin.x, this.pin.y + (this.height / div));
			return new Hero( pin2, "images/downhero.png", canBeHit, timer );
		}

		else if ( ke.equals("up")) {
			Posn pin2 = new Posn(this.pin.x, this.pin.y - (this.height / div));
			return new Hero( pin2, "images/uphero.png", canBeHit, timer );
		}
		else {
			return this;
		}
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

	public Hero heroTimerInc( ) {
		if ( timer < 25)
			return new Hero( pin, image, canBeHit, timer + 1 );
		else
			return new Hero( pin, image, true, 0 );
	}

	public WorldImage heroView( ) {
		return image;
	}
}