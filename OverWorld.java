import javalib.worldimages.*;
import javalib.funworld.*;
import javalib.colors.*;

import java.io.*;
import java.util.*;


/*
	NOTES FOR GAME2:
	working title: Hero Dude who has some bombs hidden up his sleeve or in his back pocket or something

Other notes:
	Potentially a boss dragon with randomized movement
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
	int health;
	int spending;

	public OverWorld(int width, int height, Hero hero, 
		LinkedList<Bomb> bombList, LinkedList<Explosion> explosionList ,
		LinkedList<Rocks> rockList, LinkedList<Enemies> enemyList, 
		int firePower, int bombNum, int health, int spending ) {
		this.width = width;
		this.height = height;
		this.hero = hero;
		this.bombList = bombList;
		this.explosionList = explosionList;
		this.rockList = rockList;
		this.enemyList = enemyList;
		this.firePower = firePower;
		this.bombNum = bombNum;
		this.health = health;
		this.spending = spending;
	}

	public OverWorld( Hero hero, 
		LinkedList<Bomb> bombList, LinkedList<Explosion> explosionList ,
		LinkedList<Rocks> rockList, LinkedList<Enemies> enemyList, 
		int firePower, int bombNum, int health, int spending ) {
		this.width = width;
		this.height = height;
		this.hero = hero;
		this.bombList = bombList;
		this.explosionList = explosionList;
		this.rockList = rockList;
		this.enemyList = enemyList;
		this.firePower = firePower;
		this.bombNum = bombNum;
		this.health = health;
		this.spending = spending;
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
		this.health = 3;
		this.spending = 0;
	}

	public World onKeyEvent( String ke ) {

		Iterator<Rocks> k = rockList.listIterator( 0 );
		Iterator<Enemies> m = enemyList.listIterator( 0 );

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

			while( m.hasNext( ) ) {
				Enemies e = m.next( );
				if ( hero.heroMove( ke ).checkEnemy( e ) ) {
					canMove = false;
				}

				if ( hero.heroMove( ke, 2 ).checkEnemy( e ) ) {
					canMoveHalf = false;
				}

				if ( hero.heroMove( ke, 4 ).checkEnemy( e ) ) {
					canMoveQuarter = false;
				}

				if ( hero.heroMove( ke, 8 ).checkEnemy( e ) ) {
					canMoveEigth = false;
				}

				if ( hero.heroMove( ke, 16 ).checkEnemy( e ) ) {
					canMoveSixteenth = false;
				}

				if ( hero.heroMove( ke, 32 ).checkEnemy( e ) ) {
					canMoveThirtySecond = false;
				}
			}

			if ( canMove ) {
				return new OverWorld( hero.heroMove( ke ), bombList, explosionList, 
										rockList, enemyList, firePower, bombNum, health, spending );

			} else if ( canMoveHalf )  {
				return new OverWorld( hero.heroMove( ke, 2 ), bombList, explosionList, 
										rockList, enemyList, firePower, bombNum, health, spending );

			} else if ( canMoveQuarter ) {
				return new OverWorld( hero.heroMove( ke, 4 ), bombList, explosionList, 
										rockList, enemyList, firePower, bombNum, health, spending );

			} else if ( canMoveEigth ) {
				return new OverWorld( hero.heroMove( ke, 8 ), bombList, explosionList, 
										rockList, enemyList, firePower, bombNum, health, spending );

			} else if ( canMoveSixteenth ) {
				return new OverWorld( hero.heroMove( ke, 16 ), bombList, explosionList, 
										rockList, enemyList, firePower, bombNum, health, spending );

			} else if ( canMoveThirtySecond ) {
				return new OverWorld( hero.heroMove( ke, 32 ), bombList, explosionList, 
										rockList, enemyList, firePower, bombNum, health, spending );

			} else {
				return this;
			}

		} else if ( ke.equals("z")  && ( bombList.size( ) < bombNum ) ) {
			bombList.add( new Bomb( hero.pin ) );
			return new OverWorld( hero, bombList, explosionList, rockList, enemyList, 
									firePower, bombNum, health, spending );
		}

		else if ( ke.equals("x") ) {
			return new Menu( hero, bombList, explosionList, rockList, enemyList, 
									firePower, bombNum, health, spending );
		}
		
		else {
			return this;
		}
	}

	public World onTick( ) {

		Hero nextHero = hero;
		LinkedList<Enemies> partEnemyList = new LinkedList( );
		LinkedList<Enemies> nextEnemyList = new LinkedList( );
		Iterator<Enemies> l = enemyList.listIterator( 0 );
		LinkedList<Explosion> nextExplosionList = new LinkedList( );
		boolean removedEnemy = false;

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

		// iterate through the list and check if we need to remove them
		// because of a collision with an explosion
		while( l.hasNext( ) ) {
			Enemies nextEnemy = l.next( );

			partEnemyList.add( nextEnemy );

			while( j.hasNext( ) ) {
				Explosion explo = j.next( );

				if( nextEnemy.checkExplosion( explo ) ) {
					partEnemyList.remove( nextEnemy );
					removedEnemy = true; 
				}
			}

			j = explosionList.listIterator( 0 );
		}

		Iterator<Enemies> m = partEnemyList.listIterator( 0 );
		k = rockList.listIterator( 0 );

		// iterate through, checkRock for each enemy, if true then have to switch directions, 
		// else continue forward
		while( m.hasNext( ) ) {
			Enemies e = m.next( );
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

			// check if the enemy will hit the hero, 
			// if the hero can be hit, then lose health
			// enemies always change direction in this case
			if ( eNext.checkHitHero( hero ) ) {
				if ( hero.canBeHit ) {
					health -= 1;
					hero.canBeHit = false;
				}

				enemyCanMove = false;
			}

			if( enemyCanMove ) {
					nextEnemyList.add( eNext ); 
				} else {
					nextEnemyList.add( e.enemyChangeDirection( ) );
				}
		}


		// increase each explosion's timer
		while( j.hasNext( ) ) {
			Explosion expNext = j.next( ).explosionTimeInc( );
			nextExplosionList.add( expNext );

			if( hero.checkExplosion( expNext ) && hero.canBeHit ) {
				health -= 1;
				hero.canBeHit = false;
			}
		}

		// if the hero is invulnerable, then we go through the 
		// invulnerability timer and then reset at the end. 
		if( hero.canBeHit == false ) {
			nextHero = hero.heroTimerInc( );
		}

		// if we removed an enemy, increase our spending amount!
		if( removedEnemy ) {
			spending++;
		}

		if( hero.getPin( ).x > 1000 ) {
			return new TitleScreen( 2, firePower, bombNum, health, spending );
		}
		else if( hero.getPin( ).y > 650 ) {
			return new TitleScreen( 3, firePower, bombNum, health, spending );
		}
		else if( hero.getPin( ).x < 0 ) {
			return new TitleScreen( 4, firePower, bombNum, health, spending );
		}

		if( health < 1 ) {
			return new TitleScreen( 5 );
		}

		return new OverWorld( nextHero, nextBombList, nextExplosionList, nextRockList, nextEnemyList, 
							firePower, bombNum, health, spending );
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

		while ( k.hasNext( ) ) {
			world = new OverlayImages( world, k.next( ).rockView( ) );
		}

		while ( l.hasNext( ) ) {
			world = new OverlayImages( world, l.next( ).enemyView( ) );
		}

		world = new OverlayImages( world, new TextImage( 
												new Posn( 950, 20 ),
												"Health: " + health, 20,
												new Black( ) ) );

		world = new OverlayImages( world, new TextImage( 
												new Posn( 800, 20 ),
												"Spending: " + spending, 20,
												new Black( ) ) );

		world = new OverlayImages( world, hero.heroView( ) );	 

		return world;
	}
} 