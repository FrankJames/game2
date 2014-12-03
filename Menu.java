import javalib.worldimages.*;
import javalib.funworld.*;
import javalib.colors.*;

import java.io.*;
import java.util.*;


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
				 int firePower, int bombNum, int health, int spending ) {
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
		this.spending = spending;
	}

	public World onKeyEvent( String ke ) {

		// move mouse cursor up
		if( ke.equals("up") &&  ( ( arrow == 2 ) || ( arrow == 1 ) ) ) {
			return new Menu ( hero, bombList, explosionList, rockList, 
				enemyList, firePower, bombNum, health, arrow - 1, spending );
		} 

		// move arrow down
		else if ( ke.equals("down") && ( ( arrow == 0) || ( arrow == 1 ) ) ) {
			return new Menu ( hero, bombList, explosionList, rockList, 
				enemyList, firePower, bombNum, health, arrow + 1, spending );
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

		// decrease bomb number
		else if ( ke.equals("left") && ( arrow == 1 ) && ( bombNum > 0 ) ) {
			return new Menu ( hero, bombList, explosionList, rockList, 
				enemyList, firePower, bombNum - 1, health, arrow, spending + 1 );
		}

		// increase health
		else if ( ke.equals("right") && ( arrow == 2 ) && ( spending > 0 ) ) {
			return new Menu ( hero, bombList, explosionList, rockList, 
				enemyList, firePower, bombNum, health + 1, arrow, spending - 1 );
		}

		// decrease health
		else if ( ke.equals("left") && ( arrow == 2 ) && ( health > 0 ) ) {
			return new Menu ( hero, bombList, explosionList, rockList, 
				enemyList, firePower, bombNum, health - 1, arrow, spending + 1 );
		}

		else if ( ke.equals("x") ) {
			return new OverWorld( hero, bombList, explosionList, rockList, 
				enemyList, firePower, bombNum, health, spending );
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

		world = new OverlayImages( world, new FromFileImage( 
											new Posn( 300, 197 + ( 100 * arrow ) ), 
											"images/arrow.png" ) );

		world = new OverlayImages( world, new TextImage( 
											new Posn( 500, 200 ), 
											"Fire Power: " + firePower, 30,
											new White( ) ) );

		world = new OverlayImages( world, new TextImage( 
											new Posn( 500, 300 ), 
											"Bomb Number: " + bombNum, 30,
											new White( ) ) );

		world = new OverlayImages ( world, new TextImage( 
											new Posn( 500, 400 ), 
											"Health: " + health, 30,
											new White( ) ) );

		world = new OverlayImages( world, new TextImage(
											new Posn( 500, 500 ),
											"Press X to exit!", 12,
											new White( ) ) );

		return world;

	}

}