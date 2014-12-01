import javalib.worldimages.*;
import javalib.funworld.*;
import javalib.colors.*;

import java.io.*;
import java.util.*;

/* menu class:

Needs to keep track of:
	1. the list of enemies
	2. the list of rocks
	3. the list of bombs
	4. the list of explosions
	5. the hero / his position

needs to be able to manipulate:
	1. unused points
	2. how many bombs allowed on screen
	3. firepower

extends world:


onKeyEvent
	up/down: 		change arrow cursor
	left/right:		change upgrade / unused points counters
	b: 				exit menu, back to OverWorld

onTick
	do nothing?

makeImage
	black rectangle of the same size as OverWorld
	text images


The cursor arrow will be represented as a number. Arrow can either be 0 or 1.

*/

class Menu extends World {
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
	int arrow;
	int spending;

	public Menu( int width, int height, Hero hero,
				 LinkedList<Bomb> bombList, LinkedList<Explosion> explosionList,
				 LinkedList<Rocks> rockList, LinkedList<Enemies> enemyList,
				 int firePower, int bombNum, int arrow, int spending ) {
		this.width = width;
		this.height = height;
		this.hero = hero;
		this.bombList = bombList;
		this.explosionList = explosionList;
		this.rockList = rockList;
		this.enemyList = enemyList;
		this.firePower = firePower;
		this.bombNum = bombNum;
		this.arrow = arrow;
		this.spending = spending;
	}

	public Menu( Hero hero,
				 LinkedList<Bomb> bombList, LinkedList<Explosion> explosionList,
				 LinkedList<Rocks> rockList, LinkedList<Enemies> enemyList,
				 int firePower, int bombNum, int health, int arrow, int spending ) {
		this.width = 1000;
		this.height = 650;
		this.hero = hero;
		this.bombList = bombList;
		this.explosionList = explosionList;
		this.rockList = rockList;
		this.enemyList = enemyList;
		this.firePower = firePower;
		this.bombNum = bombNum;
		this.health = health;
		this.arrow = arrow;
		this.spending = spending;
	}

	public Menu( Hero hero,
				 LinkedList<Bomb> bombList, LinkedList<Explosion> explosionList,
				 LinkedList<Rocks> rockList, LinkedList<Enemies> enemyList,
				 int firePower, int bombNum, int health ) {
		this.width = 1000;
		this.height = 650;
		this.hero = hero;
		this.bombList = bombList;
		this.explosionList = explosionList;
		this.rockList = rockList;
		this.enemyList = enemyList;
		this.firePower = firePower;
		this.bombNum = bombNum;
		this.health = health;
		this.arrow = 0;
		this.spending = 0;
	}

	public World onKeyEvent( String ke ) {

		// move mouse cursor up
		if( ke.equals("up") && ( arrow == 1 ) ) {
			return new Menu ( hero, bombList, explosionList, rockList, 
				enemyList, firePower, bombNum, health, 0, spending );
		} 

		// move arrow down
		else if ( ke.equals("down") && ( arrow == 0)) {
			return new Menu ( hero, bombList, explosionList, rockList, 
				enemyList, firePower, bombNum, health, 1, spending );
		}

		// increase fire power
		else if ( ke.equals("right") && ( arrow == 0 ) && ( spending > 0 ) ) {
			return new Menu ( hero, bombList, explosionList, rockList, 
				enemyList, firePower + 1, bombNum, health, arrow, spending - 1 );
		}

		// decrease fire power
		else if ( ke.equals("left") && ( arrow == 0 ) && ( firePower > 1 ) ) {
			return new Menu ( hero, bombList, explosionList, rockList, 
				enemyList, firePower - 1, bombNum, health, arrow, spending + 1 );
		}

		// increase bomb number
		else if ( ke.equals("right") && ( arrow == 1 ) && ( spending > 0 ) ) {
			return new Menu ( hero, bombList, explosionList, rockList, 
				enemyList, firePower, bombNum + 1, health, arrow, spending - 1 );
		}

		// increase bomb number
		else if ( ke.equals("left") && ( arrow == 1 ) && ( bombNum > 0 ) ) {
			return new Menu ( hero, bombList, explosionList, rockList, 
				enemyList, firePower, bombNum - 1, health, arrow, spending + 1 );
		}

		else if ( ke.equals("x") ) {
			return new OverWorld( hero, bombList, explosionList, rockList, 
				enemyList, firePower, bombNum, health );
		}

		else {
			return this;
		}
	}

	public World onTick( ){ 
		return this; // nothing to manipulate
	}

	public WorldImage makeImage( ){

		WorldImage world = new RectangleImage( new Posn( 500, 325 ), 1000, 650, new Black( ) );

		world = new OverlayImages( world, new RectangleImage( 
											new Posn( 400, 400 + ( 100 * arrow ) ), 
											100, 50, new White( ) ) );

		world = new OverlayImages ( world, new TextImage( 
											new Posn( 500, 400 ), 
											"Fire Power: " + firePower, 12,
											new White( ) ) );

		world = new OverlayImages ( world, new TextImage( 
											new Posn( 500, 500 ), 
											"Bomb Number: " + bombNum, 12,
											new White( ) ) );

		return world;

	}

}