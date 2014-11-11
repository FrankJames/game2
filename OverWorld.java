import javalib.worldimages.*;
import javalib.funworld.*;
import javalib.colors.*;

/*
	NOTES FOR GAME2:
	working title: Hero Dude who has some bombs hidden up his sleeve


The game itself will have:
	1. the hero
	2. list of bombs currently in play
	3. list of enemies
	4. list of current explosions
	5. rocks that are destroyable and may contain upgrades (more bombs in play, larger explosions, more life points)
	6. non-destroyable background

	On tick will need to:
		1. checkGoBoom for each bomb
		2. checkHitHero for each enemy
		3. checkExplosion for hero/enemies/obstacles

Other notes:
	Potentially a boss dragon with randomized movement
	Potentially sprites? file name from string hardcoded in 
		*if sprites, will need 4 different sprites for each character-- face down, left, right, and up
		*sprites done in gimp possibly


The world needs to have different states:

obviously there will be different rooms, but what about an inventory? or a title screen?

 */


class OverWorld extends World {
	int width;
	int height;
	Hero hero;

	public OverWorld(int width, int height, Hero hero) {
		this.width = width;
		this.height = height;
		this.hero = hero;
	}

	public OverWorld(Hero hero) {
		this.width = 800;
		this.height = 500;
		this.hero = hero;
	}

	public World onKeyEvent( String ke ) {
		return new OverWorld( hero.heroMove( ke ) );
	}

	public World onTick( ) {
		return new OverWorld( hero );
	}

	WorldImage background = new OverlayImages(
		new RectangleImage(new Posn(0, 0), 800, 500, new White( )),
		new RectangleImage(new Posn(100, 150), 50, 50, new Blue( ) ));  // completely unnecessary square

	public WorldImage makeImage( ) {
		WorldImage world = new OverlayImages( background, hero.heroView( ) );
		return world;
	}

	public static void main(String[ ] args ) {

		Hero man = new Hero( new Posn( 50, 50 ), 2, 0, 3);
		OverWorld w = new OverWorld( man );

		w.bigBang( 800, 500, 0.1);

	}
} 