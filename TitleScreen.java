import javalib.worldimages.*;
import javalib.funworld.*;
import javalib.colors.*;

import java.io.*;
import java.util.*;

/*

TITLE SCREEN NOTES:

This is where everything will be initiated and where 
it will be tossed back to to start different levels.


because the OverWorld class contains everything for actually playing
the game, we just need to feed that class the appropriate stuff

the title screen class can be the aggregator for every linked list
that we feed into the other classes


The fields of this class will be made from the linkedlists associated with each level
it will keep track of which level the player is on due to a level counter incrementing.


On key event:


on tick:


make image:
if ( check level )
{
	return stuff associated with the level. A loading screen??
}

ON TICK CAN CREATE THE LINKED LISTS THAT WE NEED BASED UPON THE LEVEL NUMBER. WE SIMPLY
GIVE IT THE LEVEL NUMBER AND THEN THE ONTICK CAN CREATE WHAT WE NEED!

 */

class TitleScreen extends World {
	int level;
	LinkedList<Rocks> rockList;
	LinkedList<Enemies> enemyList;

	public TitleScreen( int level, LinkedList<Rocks> rockList, LinkedList<Enemies> enemyList ) {
		this.level = level;
		this.rockList = rockList;
		this.enemyList = enemyList;
	}

	public TitleScreen( int level ) { 
		this.level = level;
		this.rockList = new LinkedList( );
		this.enemyList = new LinkedList( );
	} 

	public World onKeyEvent( String ke ) {

		Hero man = new Hero( new Posn( 100, 150 ) );
		return new OverWorld( man, new LinkedList( ), new LinkedList( ), rockList, enemyList );
	}

	public World onTick( ) {
		if ( ( level == 0 ) && ( rockList.size( ) == 0 ) ) {

			LinkedList<Rocks> rockyList = new LinkedList( );
			LinkedList<Enemies> scaryList = new LinkedList( );

			scaryList.add( new Baddy( new Posn( 350, 250 ), 0 ) );
			scaryList.add( new Baddy( new Posn( 100, 375 ), 1 ) );
			scaryList.add( new Baddy( new Posn( 500, 475 ), 3 ) );
			scaryList.add( new Baddy( new Posn( 350, 150 ), 2 ) );

			int x = 25;
			int y = 75;

		// horizontal borders
		while( x < 1000 ) {
			rockyList.add( new NDRock( new Posn( x, 50 ) ) );
			rockyList.add( new NDRock( new Posn( x, 625 ) ) );
			x+= 50;
		}

		// vertical borders and the exit!
		while( y < 625 ) {
			rockyList.add( new NDRock( new Posn( 25, y ) ) );

			if( ( y < 500 ) && ( y > 250 ) ) 
				rockyList.add( new DRock( new Posn( 975, y ) ) );
			 else 
				rockyList.add( new NDRock( new Posn( 975, y ) ) );
			
			y+= 25;
		}

		rockyList.add( new DRock( new Posn( 500, 100 ) ) );
		rockyList.add( new DRock( new Posn( 250, 400 ) ) );
		rockyList.add( new DRock( new Posn( 600, 300 ) ) );

		// vertical destructable rocks
		x = 825; y = 100;
		while( y < 600 ) {
			rockyList.add( new DRock( new Posn( x, y ) ) );
			y+= 50;
		}

		// matrix of non-destructable rocks
		x = 400; y = 300;
		while( y < 450 ) {
			while( x < 550 ) {
				rockyList.add( new NDRock( new Posn( x, y ) ) );
				x+= 50;
			}
			y+= 50;
			x = 400;
		}
			System.out.println("i made somethin");
			return new TitleScreen( level, rockyList, scaryList );
		}
		else {
			System.out.println("i stopped made somethin");
			return this;
		}
			
	}

	public WorldImage makeImage( ) {
		WorldImage world = new RectangleImage( new Posn( 500, 325 ), 1000, 650, new White( ) );

		world = new OverlayImages( world, new FromFileImage( new Posn( 200, 100 ), "images/bighero.png" ) );

		return world;
	}


	public static void main(String[] args) {

		TitleScreen t = new TitleScreen( 0 );
		t.bigBang( 1000, 650, 0.1);
	}

}