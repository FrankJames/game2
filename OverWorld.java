import javalib.worldimages.*;
import javalib.funworld.*;
import javalib.colors.*;

import java.io.*;
import java.util.*;


/*
	NOTES FOR GAME2:
	working title: Hero Dude who has some bombs hidden up his sleeve or in his back pocket or something


The game itself will have:
	1. the hero
	2. list of bombs currently in play
	3. list of enemies
	4. list of current explosions
	5. rocks that are destroyable and may contain upgrades (more bombs in play, larger explosions, more life points)
	6. non-destroyable background

	On tick will need to:
		1. checkcheckGoBoom for each bomb
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

/*
need to make it so that once a bomb's timer reaches the correct point, it is removed from the bombList and a corresponding explosion
is added to the explosionList

powerups could definitely be fields in the OverWorld, not in the Hero class
 */ 
class OverWorld extends World {
	int width;
	int height;
	Hero hero;
	LinkedList<Bomb> bombList;
	LinkedList<Explosion> explosionList;
	int firePower;

	public OverWorld(int width, int height, Hero hero, 
		LinkedList<Bomb> bombList, LinkedList<Explosion> explosionList ,
		int firePower) {
		this.width = width;
		this.height = height;
		this.hero = hero;
		this.bombList = bombList;
		this.explosionList = explosionList;
		this.firePower = firePower;
	}

	public OverWorld( Hero hero, LinkedList<Bomb> bombList, LinkedList<Explosion> explosionList  ) {
		this.width = 1000;
		this.height = 750;
		this.hero = hero;
		this.bombList = bombList;
		this.explosionList = explosionList;
		this.firePower = 2;
	}

	public World onKeyEvent( String ke ) {
		if ( (ke.equals("up") )
			|| (ke.equals("right") )
			|| (ke.equals("left") )
			|| (ke.equals("down") ) ) {
			return new OverWorld( hero.heroMove( ke ), bombList, explosionList );
		} else if (ke.equals("z") ) {
			bombList.add( new Bomb( hero.pin ) );
			return new OverWorld( hero, bombList, explosionList );
		}
		else {
			return this;
		}
	}

	public World onTick( ) {

		// check if each bomb should be removed from the bombList and added to explosionList
		if( ( bombList.size( ) > 0 ) && ( bombList.element( ).checkGoBoom( ) ) ) {
			Posn bombPin = bombList.removeFirst( ).pin;
			
			// center explosion always happens
			explosionList.add( new Explosion( bombPin ) );

			int i = 0;
			int offset = 30;
			while(i < firePower) {
				explosionList.add( new Explosion( new Posn( bombPin.x + offset, bombPin.y ) ) );
				explosionList.add( new Explosion( new Posn( bombPin.x - offset, bombPin.y ) ) );
				explosionList.add( new Explosion( new Posn( bombPin.x, bombPin.y + offset ) ) );
				explosionList.add( new Explosion( new Posn( bombPin.x, bombPin.y - offset ) ) );
				offset += 30;
				i++;
			}
		}	
		
		// remove explosions that have been sticking around for too long
		while( ( explosionList.size( ) > 0 ) && (explosionList.element( ).timer >= 5 ) ) {
			explosionList.removeFirst( );
			}

		Iterator<Bomb> i  = bombList.listIterator( 0 );
		Iterator<Explosion> j  = explosionList.listIterator( 0 );

		// increase each bomb's timer
		while( i.hasNext( ) ) {
			i.next( ).bombTimeInc( );
		}

		// increase each explosion's timer
		while( j.hasNext( ) ) {
			j.next( ).explosionTimeInc( );
		}

		return new OverWorld( hero, bombList, explosionList );
	}

	WorldImage background = new OverlayImages(
		new RectangleImage(new Posn(0, 0), 1000, 750, new White( )),
		new RectangleImage(new Posn(100, 150), 50, 50, new Blue( ) ));  // completely unnecessary square

	public WorldImage makeImage( ) {

		Iterator<Bomb> i  = bombList.listIterator(0);
		Iterator<Explosion> j  = explosionList.listIterator(0);

		WorldImage world = background;

		while( i.hasNext( ) ) {
			world = new OverlayImages( background, i.next( ).bombView( ) );
		}

		while ( j.hasNext( ) ) {
			world = new OverlayImages( world, j.next( ).explosionView( ) );
		}

		world = new OverlayImages(world, hero.heroView( ) );

		return world;
	}

	public static void main(String[ ] args ) {

		Hero man = new Hero( new Posn( 50, 50 ), 3);
		OverWorld w = new OverWorld( man, new LinkedList( ), new LinkedList( ) );

		w.bigBang( 1000, 750, 0.1);

	}
} 