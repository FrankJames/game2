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
	LinkedList<Enemies> enemyList;
	int firePower;
	int bombNum;

	public OverWorld(int width, int height, Hero hero, 
		LinkedList<Bomb> bombList, LinkedList<Explosion> explosionList ,
		LinkedList<Rocks> rockList, LinkedList<Enemies> enemyList, int firePower, int bombNum ) {
		this.width = width;
		this.height = height;
		this.hero = hero;
		this.bombList = bombList;
		this.explosionList = explosionList;
		this.rockList = rockList;
		this.enemyList = enemyList;
		this.firePower = firePower;
		this.bombNum = bombNum;
	}

	public OverWorld( Hero hero, 
		LinkedList<Bomb> bombList, LinkedList<Explosion> explosionList ,
		LinkedList<Rocks> rockList, LinkedList<Enemies> enemyList, int firePower, int bombNum ) {
		this.width = width;
		this.height = height;
		this.hero = hero;
		this.bombList = bombList;
		this.explosionList = explosionList;
		this.rockList = rockList;
		this.enemyList = enemyList;
		this.firePower = firePower;
		this.bombNum = bombNum;
	}

	public OverWorld( Hero hero, LinkedList<Bomb> bombList, 
		LinkedList<Explosion> explosionList, LinkedList<Rocks> rockList, LinkedList<Enemies> enemyList ) {
		this.width = 1000;
		this.height = 650;
		this.hero = hero;
		this.bombList = bombList;
		this.explosionList = explosionList;
		this.rockList = rockList;
		this.enemyList = enemyList;
		this.firePower = 2;
		this.bombNum = 3;
	}

	public World onKeyEvent( String ke ) {

		Iterator<Rocks> k = rockList.listIterator( 0 );

		if ( (ke.equals("up") )
			|| (ke.equals("right") )
			|| (ke.equals("left") )
			|| (ke.equals("down") ) ) {

			boolean canMove = true;
			boolean canMoveHalf = true;
			boolean canMoveQuarter = true;
			boolean canMoveEigth = true;
			boolean canMoveSixteenth = true;
			boolean canMoveThirtySecond = true;

			while( k.hasNext( ) ) {
				Rocks r = k.next( );
				if ( hero.heroMove( ke ).checkRock( r ) ) {
					canMove = false;
				}

				if ( hero.heroMove( ke, 2 ).checkRock( r ) ) {
					canMoveHalf = false;
				}

				if ( hero.heroMove( ke, 4 ).checkRock( r ) ) {
					canMoveQuarter = false;
				}

				if ( hero.heroMove( ke, 8 ).checkRock( r ) ) {
					canMoveEigth = false;
				}

				if ( hero.heroMove( ke, 16 ).checkRock( r ) ) {
					canMoveSixteenth = false;
				}

				if ( hero.heroMove( ke, 32 ).checkRock( r ) ) {
					canMoveThirtySecond = false;
				}
			}

			if ( canMove ) {
				return new OverWorld( hero.heroMove( ke ), bombList, explosionList, 
										rockList, enemyList, firePower, bombNum );

			} else if ( canMoveHalf )  {
				return new OverWorld( hero.heroMove( ke, 2 ), bombList, explosionList, 
										rockList, enemyList, firePower, bombNum );

			} else if ( canMoveQuarter ) {
				return new OverWorld( hero.heroMove( ke, 4 ), bombList, explosionList, 
										rockList, enemyList, firePower, bombNum );

			} else if ( canMoveEigth ) {
				return new OverWorld( hero.heroMove( ke, 8 ), bombList, explosionList, 
										rockList, enemyList, firePower, bombNum );

			} else if ( canMoveSixteenth ) {
				return new OverWorld( hero.heroMove( ke, 16 ), bombList, explosionList, 
										rockList, enemyList, firePower, bombNum );

			} else if ( canMoveThirtySecond ) {
				return new OverWorld( hero.heroMove( ke, 32 ), bombList, explosionList, 
										rockList, enemyList, firePower, bombNum );

			} else {
				return this;
			}

		} else if ( ke.equals("z")  && ( bombList.size( ) < bombNum ) ) {
			bombList.add( new Bomb( hero.pin ) );
			return new OverWorld( hero, bombList, explosionList, rockList, enemyList, firePower, bombNum );
		}

		else if ( ke.equals("b") ) {
			return new Menu( hero, bombList, explosionList, rockList, enemyList, firePower, bombNum );
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
		LinkedList<Bomb> nextBombList = new LinkedList( );
		j  = explosionList.listIterator( 0 );


		// increase each bomb's timer and check if each bomb overlaps with any explosion
		// in which case it creates a bomb that will immediately explodes
		while( i.hasNext( ) ) {
			Bomb bomby = i.next( );
			nextBombList.add( bomby.bombTimeInc( ) );

			while( j.hasNext( ) ) {
				Explosion expl = j.next( );

				if ( bomby.checkExplosion( expl ) ) {
					nextBombList.removeLast( );
					nextBombList.add( new Bomb( bomby.timer + 10, bomby.pin ) );
				}
			}
			j = explosionList.listIterator( 0 );
		}

		LinkedList<Enemies> nextEnemyList = new LinkedList( );
		Iterator<Enemies> l = enemyList.listIterator( 0 );
		k = rockList.listIterator( 0 );


		// iterate through, checkRock for each enemy, if true then have to switch directions, 
		// else continue forward
		while( l.hasNext( ) ) {
			Enemies e = l.next( );
			Enemies eNext = e.enemyMove( );
			boolean enemyCanMove = true;

			while( k.hasNext( ) ) {
				Rocks checkAgainstRock = k.next( );

				
				// if the next enemy movement would collide, we need to change direction
				if ( eNext.checkRock( checkAgainstRock ) ) {
					enemyCanMove = false;
					}
			}
			k = rockList.listIterator( 0 );

			if( enemyCanMove ) {
				nextEnemyList.add( eNext ); 
			} else {
				nextEnemyList.add( e.enemyChangeDirection( ) );
			}

		}

		// increase each explosion's timer
		while( j.hasNext( ) ) {
			j.next( ).explosionTimeInc( );
		}

		return new OverWorld( hero, nextBombList, explosionList, nextRockList, nextEnemyList, firePower, bombNum );
	}



	public WorldImage makeImage( ) {

		Iterator<Bomb> i  = bombList.listIterator( 0 );
		Iterator<Explosion> j  = explosionList.listIterator( 0 );
		Iterator<Rocks> k = rockList.listIterator( 0 );
		Iterator<Enemies> l = enemyList.listIterator( 0 );

		WorldImage world = new RectangleImage(new Posn(0, 0), 1000, 650, new White( ) );

		while( i.hasNext( ) ) {
			world = new OverlayImages( world, i.next( ).bombView( ) );
		}

		while ( j.hasNext( ) ) {
			world = new OverlayImages( world, j.next( ).explosionView( ) );
		}

		while (k.hasNext( ) ) {
			world = new OverlayImages( world, k.next( ).rockView( ) );
		}

		while (l.hasNext( ) ) {
			world = new OverlayImages( world, l.next( ).enemyView( ) );
		}

		world = new OverlayImages(world, hero.heroView( ) );	 

		return world;
	}



	public static void main(String[ ] args ) {

		Hero man = new Hero( new Posn( 100, 150 ), 3);
		LinkedList<Rocks> levelOne = new LinkedList( );

		int x = 25;
		int y = 75;

		// horizontal borders
		while( x < 1000 ) {
			levelOne.add( new NDRock( new Posn( x, 50 ) ) );
			levelOne.add( new NDRock( new Posn( x, 625 ) ) );
			x+= 50;
		}

		// vertical borders and the exit!
		while( y < 625 ) {
			levelOne.add( new NDRock( new Posn( 25, y ) ) );

			if( ( y < 500 ) && ( y > 300 ) ) 
				levelOne.add( new DRock( new Posn( 975, y ) ) );
			 else 
				levelOne.add( new NDRock( new Posn( 975, y ) ) );
			
			y+= 25;
		}

		levelOne.add( new DRock( new Posn( 500, 100 ) ) );
		levelOne.add( new DRock( new Posn( 250, 400 ) ) );
		levelOne.add( new DRock( new Posn( 600, 300) ) ) ;

		LinkedList scaryList = new LinkedList( );
		scaryList.add( new Baddy( new Posn( 350, 250 ), 0 ) );



		OverWorld w = new OverWorld( man, new LinkedList( ), new LinkedList( ), levelOne, scaryList );

		w.bigBang( 1000, 650, 0.1);
	}
} 