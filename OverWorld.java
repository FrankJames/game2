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
		1. checkGoBoom for each bomb
		2. checkHitHero for each enemy
		3. checkExplosion for hero/enemies/obstacles

Other notes:
	Potentially a boss dragon with randomized movement

The world needs to have different states:
obviously there will be different rooms, but what about an inventory? or a title screen?
powerups could definitely be fields in the OverWorld, not in the Hero class
 */ 
class OverWorld extends World {
	int width;
	int height;
	Hero hero;
	LinkedList<Bomb> bombList;
	LinkedList<Explosion> explosionList;
	LinkedList<Rocks> rockList;
	int firePower;

	public OverWorld(int width, int height, Hero hero, 
		LinkedList<Bomb> bombList, LinkedList<Explosion> explosionList ,
		LinkedList<Rocks> rockList, int firePower) {
		this.width = width;
		this.height = height;
		this.hero = hero;
		this.bombList = bombList;
		this.explosionList = explosionList;
		this.rockList = rockList;
		this.firePower = firePower;
	}

	public OverWorld( Hero hero, LinkedList<Bomb> bombList, 
		LinkedList<Explosion> explosionList, LinkedList<Rocks> rockList ) {
		this.width = 1000;
		this.height = 650;
		this.hero = hero;
		this.bombList = bombList;
		this.explosionList = explosionList;
		this.rockList = rockList;
		this.firePower = 2;
	}

	public World onKeyEvent( String ke ) {
		if ( (ke.equals("up") )
			|| (ke.equals("right") )
			|| (ke.equals("left") )
			|| (ke.equals("down") ) ) {
			return new OverWorld( hero.heroMove( ke ), bombList, explosionList, rockList );
		} else if (ke.equals("z") ) {
			bombList.add( new Bomb( hero.pin ) );
			return new OverWorld( hero, bombList, explosionList, rockList );
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

		Iterator<Explosion> j  = explosionList.listIterator( 0 );
		LinkedList<Rocks> nextRockList = new LinkedList( );
		Iterator<Rocks> k = rockList.listIterator( 0 );
		


		// check through each explosion to see if it is colliding with any (destructable) rock
		// this will build an updated RockList with all of the rocks that are not currently being destroyed,
		// which we will later pass into our game constructor
		while ( k.hasNext( ) ) {
			Rocks rocky = k.next( );
			nextRockList.add( rocky );

			while( j.hasNext( ) ) {
				Explosion exp = j.next( );

				if (  rocky.checkExplosion( exp )  )
					nextRockList.remove( rocky );
			}
			j = explosionList.listIterator( 0 );

		}
		

		Iterator<Bomb> i  = bombList.listIterator( 0 );
		j  = explosionList.listIterator( 0 );

		// increase each bomb's timer
		while( i.hasNext( ) ) {
			i.next( ).bombTimeInc( );
		}

		// increase each explosion's timer
		while( j.hasNext( ) ) {
			j.next( ).explosionTimeInc( );
		}

		return new OverWorld( hero, bombList, explosionList, nextRockList );
	}

	WorldImage background = new OverlayImages(
		new RectangleImage(new Posn(0, 0), 1000, 650, new White( )),
		new RectangleImage(new Posn(100, 150), 50, 50, new White( ) ));  // completely unnecessary square

	public WorldImage makeImage( ) {

		Iterator<Bomb> i  = bombList.listIterator(0);
		Iterator<Explosion> j  = explosionList.listIterator(0);
		Iterator<Rocks> h = rockList.listIterator(0);

		WorldImage world = background;

		while( i.hasNext( ) ) {
			world = new OverlayImages( world, i.next( ).bombView( ) );
		}

		while ( j.hasNext( ) ) {
			world = new OverlayImages( world, j.next( ).explosionView( ) );
		}

		while (h.hasNext( ) ) {
			world = new OverlayImages( world, h.next( ).rockView( ) );
		}

		world = new OverlayImages(world, hero.heroView( ) );	 

		return world;
	}





	public static void main(String[ ] args ) {

		Hero man = new Hero( new Posn( 50, 50 ), 3);
		LinkedList<Rocks> levelOne = new LinkedList( );
		levelOne.add( new DRock( new Posn( 100, 100 ) ) );
		levelOne.add( new DRock( new Posn( 250, 400 ) ) );
		levelOne.add( new DRock( new Posn( 600, 300) ) );
		levelOne.add( new NDRock( new Posn( 700, 500) ) );



		OverWorld w = new OverWorld( man, new LinkedList( ), new LinkedList( ), levelOne );

		w.bigBang( 1000, 650, 0.1);
	}
} 