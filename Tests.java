import tester.*;

import javalib.worldimages.*;
import javalib.funworld.*;
import javalib.colors.*;

import java.io.*;
import java.util.*;

/*

TESTING FILE

NEED TO TEST:

OVERWORLD ONTICK
	-Explosions
	-Bombs
	-invincibility
	-Enemy movement
	-Collisions
	-titlescreen

OVERWORLD ONKEYEVENT
	-hero movement
	-laying bombs
	-return menu

MENU ONTICK
	-return the same thing

MENU ONKEYEVENT
	-manipulate variables with spending points
	-not manipulating variables with no spending points
	-returning the overworld

TITLESCREEN ONTICK
	-creating the levels

TITLESCREEN ONKEYEVENT
	-advancing the titlescreen
	-advancing to the overworld


 */

class Tests {

	Hero hero1 = new Hero( new Posn( 100, 100 ) );
	Hero heroUP = new Hero( new Posn( 100, 19 ), "images/uphero.png", true, 0 );
	Hero heroRIGHT = new Hero( new Posn( 140, 100 ), "images/righthero.png", true, 0 );
	Hero heroLEFT = new Hero( new Posn( 60, 100 ), "images/lefthero.png", true, 0 );
	Hero heroDOWN = new Hero( new Posn( 100, 181 ), "images/downhero.png", true, 0 );

	LinkedList<Enemies> scaryList = new LinkedList( );
	LinkedList<Bomb> bombList = new LinkedList( );
	LinkedList<Explosion> explosionList = new LinkedList( );
	LinkedList<Rocks> rockList = new LinkedList( );

	LinkedList<Bomb> bombList2 = new LinkedList( );
	//bombList2.add( new Bomb( new Posn( 100, 100 ) ) );

	Baddy baddy = new Baddy( new Posn( 200, 200 ), 0 );

	OverWorld world1 = new OverWorld( hero1, bombList, explosionList, rockList, scaryList );
	OverWorld world2 = new OverWorld( hero1, bombList2, explosionList, rockList, scaryList );
	Menu menu1 = new Menu( hero1, bombList, explosionList, rockList, scaryList, 2, 3, 3, 0 );

	// testing onKeyEvent( ) -- OVERWORLD
	
	public boolean testHeroMove( Tester t ) {
		return

			t.checkExpect( hero1.heroMove("up"),
							heroUP, "testing hero move up" + "\n") &&

			t.checkExpect( hero1.heroMove("right"),
							heroRIGHT, "testing hero move right" + "\n") &&

			t.checkExpect( hero1.heroMove("left"),
							heroLEFT, "testing hero move left" + "\n") &&

			t.checkExpect( hero1.heroMove("down"),
							heroDOWN, "testing hero move down" + "\n");
	}

	public boolean testChangeOWtoMenu( Tester t ) {
		return 

			t.checkExpect( world1.onKeyEvent("x"),
							menu1, "testing OverWorld -> Menu" + "\n" );
	}

	public boolean testCreateBomb( Tester t ) {

		bombList2.add( new Bomb( new Posn( 100, 100 ) ) );

		return

			t.checkExpect( world1.onKeyEvent("z"),
							world2, "testing creating a bomb object" + "\n" );

	}


	// testing onTick( ) -- OVERWORLD
	

	// testing onKeyEvent( ) -- MENU
	
	public boolean testChangeMenutoOW( Tester t ) {
		return 

			t.checkExpect( menu1.onKeyEvent("x"),
							world1, "testing Menu -> OverWorld" + "\n" );
	}


	// testing onTick( ) -- MENU
	

	// testing OnKeyEvent( ) -- TITLESCREEN
	

	// testing onTick( ) -- TITLESCREEN
	
	public static void main(String[] args) {
		
		Tester.runReport( new Tests( ), false, false );

		System.out.println("--------END OF CODE---------");
	}
}