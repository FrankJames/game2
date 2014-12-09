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
	-Collisions
	-titlescreen

MENU ONKEYEVENT
	-manipulate variables with spending points
	-not manipulating variables with no spending points
	-returning the overworld

 */

class Tests {

	LinkedList<Enemies> scaryListEMPTY = new LinkedList( );
	LinkedList<Bomb> bombListEMPTY = new LinkedList( );
	LinkedList<Explosion> explosionListEMPTY = new LinkedList( );
	LinkedList<Rocks> rockListEMPTY = new LinkedList( );
	Hero hero1 = new Hero( new Posn( 100, 100 ) );
	

	

	// testing onKeyEvent( ) -- OVERWORLD
	
	public boolean testHeroMove( Tester t ) {

	Hero hero1 = new Hero( new Posn( 100, 100 ) );
	Hero heroUP = new Hero( new Posn( 100, 19 ), "images/uphero.png", true, 0 );
	Hero heroRIGHT = new Hero( new Posn( 140, 100 ), "images/righthero.png", true, 0 );
	Hero heroLEFT = new Hero( new Posn( 60, 100 ), "images/lefthero.png", true, 0 );
	Hero heroDOWN = new Hero( new Posn( 100, 181 ), "images/downhero.png", true, 0 );

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

	OverWorld world1 = new OverWorld( hero1, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );
	Menu menu1 = new Menu( hero1, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0 );

		return 

			t.checkExpect( world1.onKeyEvent("x"),
							menu1, "testing OverWorld -> Menu" + "\n" );
	}

	public boolean testCreateBomb( Tester t ) {

	LinkedList<Bomb> bombList1 = new LinkedList( );
	OverWorld world1 = new OverWorld( hero1, bombList1, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );
	LinkedList<Bomb> bombList2 = new LinkedList( );
	bombList2.add( new Bomb( new Posn( 100, 100 ) ) );
	OverWorld world2 = new OverWorld( hero1, bombList2, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );

		return

			t.checkExpect( world1.onKeyEvent("z"),
							world2, "testing creating a bomb object" + "\n" );

	}


	// testing onTick( ) -- OVERWORLD
	
	public boolean testMoveEnemy( Tester t ) {

	Baddy baddy1 = new Baddy( new Posn( 200, 200 ), 0 );
	Baddy baddy2 = new Baddy( new Posn( 200, 220 ), "images/downbaddy.png", 0 );

	LinkedList<Enemies> scaryList1 = new LinkedList( );
	scaryList1.add( baddy1 );

	LinkedList<Enemies> scaryList2 = new LinkedList( );
	scaryList2.add( baddy2 );

	OverWorld world1 = new OverWorld( hero1, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryList1 );
	OverWorld world2 = new OverWorld( hero1, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryList2 );

		return

			t.checkExpect( world1.onTick( ),
							world2, "testing moving enemies objects" + "\n");
	}


	public boolean testAdvanceBombTimer( Tester t ) {
		
		LinkedList<Bomb> bombList1 = new LinkedList( );
		LinkedList<Bomb> bombList2 = new LinkedList( );
		LinkedList<Bomb> bombList3 = new LinkedList( );
		LinkedList<Explosion> explosionList1 = new LinkedList( );

		bombList1.add( new Bomb( 1, new Posn( 300, 300 ) ) );
		bombList2.add( new Bomb( 2, new Posn( 300, 300 ) ) );
		bombList3.add( new Bomb( 19, new Posn( 400, 400 ) ) );

		explosionList1.add( new Explosion( 1, new Posn( 400, 400 ) ) );
		explosionList1.add( new Explosion( 1, new Posn( 430, 400 ) ) );
		explosionList1.add( new Explosion( 1, new Posn( 370, 400 ) ) );
		explosionList1.add( new Explosion( 1, new Posn( 400, 430 ) ) );
		explosionList1.add( new Explosion( 1, new Posn( 400, 370 ) ) );
		explosionList1.add( new Explosion( 1, new Posn( 460, 400 ) ) );
		explosionList1.add( new Explosion( 1, new Posn( 340, 400 ) ) );
		explosionList1.add( new Explosion( 1, new Posn( 400, 460 ) ) );
		explosionList1.add( new Explosion( 1, new Posn( 400, 340 ) ) );


		OverWorld world1 = new OverWorld( hero1, bombList1, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );
		OverWorld world2 = new OverWorld( hero1, bombList2, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );
		OverWorld world3 = new OverWorld( hero1, bombList3, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );
		OverWorld world4 = new OverWorld( hero1, bombListEMPTY, explosionList1, rockListEMPTY, scaryListEMPTY );

		return 

			t.checkExpect( world1.onTick( ),
							world2, "test advance bomb timer" + "\n") &&

			t.checkExpect( world3.onTick( ),
							world4, "test bomb to explosion" + "\n");


	}

	// testing onKeyEvent( ) -- MENU
	
	public boolean testChangeMenutoOW( Tester t ) {

	OverWorld world1 = new OverWorld( hero1, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );
	Menu menu1 = new Menu( hero1, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0 );

		return 

			t.checkExpect( menu1.onKeyEvent("x"),
							world1, "testing Menu -> OverWorld" + "\n");
	}


	// testing onTick( ) -- MENU
	
	public boolean testMenuOnTick( Tester t ) {

	Menu menu1 = new Menu( hero1, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0 );

		return

			t.checkExpect( menu1.onTick( ),
							menu1, "testing Menu on tick" + "\n");
	}

	// testing OnKeyEvent( ) AND onTick( ) -- TITLESCREEN
	
	public boolean testAdvanceTitle( Tester t ) {

	TitleScreen title0 = new TitleScreen( 0 );
	TitleScreen title1 = new TitleScreen( 1 );

		return

			t.checkExpect( title0.onKeyEvent(" "),
							title1, "testing advancing titlescreen" + "\n");
	}

	public boolean testAdvancetoOW( Tester t ) {

	TitleScreen title1 = new TitleScreen( 1 );
	World title2 = title1.onTick( );

	/* the following is how the title screen constructs the first level.
		 this only happens on the first call to onTick( ), and then after
		 that it simply stores this information until it is needed in
		 constructing the first Overworld
	*/

	LinkedList<Rocks> rockyList1 = new LinkedList( );
	LinkedList<Enemies> scaryList1 = new LinkedList( );

			scaryList1.add( new Baddy( new Posn( 350, 200 ), 1 ) );
			scaryList1.add( new Baddy( new Posn( 100, 375 ), 1 ) );
			scaryList1.add( new Baddy( new Posn( 500, 475 ), 3 ) );
			scaryList1.add( new Baddy( new Posn( 350, 150 ), 2 ) );
			scaryList1.add( new Baddy( new Posn( 900, 125 ), 1 ) );
			scaryList1.add( new Baddy( new Posn( 900, 500 ), 3 ) );
			scaryList1.add( new Baddy( new Posn( 750, 550 ), 2 ) );
			scaryList1.add( new Baddy( new Posn( 600, 150 ), 0 ) );

			int x = 25;
			int y = 75;

		// horizontal borders
		while( x < 1000 ) {
			rockyList1.add( new NDRock( new Posn( x, 50 ) ) );
			rockyList1.add( new NDRock( new Posn( x, 625 ) ) );
			x+= 50;
		}

		// vertical borders and the exit!
		while( y < 625 ) {
			rockyList1.add( new NDRock( new Posn( 25, y ) ) );

			if( ( y < 500 ) && ( y > 250 ) ) 
				rockyList1.add( new DRock( new Posn( 975, y ) ) );
			 else 
				rockyList1.add( new NDRock( new Posn( 975, y ) ) );
			
			y+= 25;
		}

		rockyList1.add( new DRock( new Posn( 500, 100 ) ) );
		rockyList1.add( new DRock( new Posn( 225, 525 ) ) );
		rockyList1.add( new DRock( new Posn( 250, 475 ) ) );
		rockyList1.add( new DRock( new Posn( 275, 425 ) ) );
		rockyList1.add( new DRock( new Posn( 300, 375 ) ) );
		rockyList1.add( new DRock( new Posn( 325, 325 ) ) );
		rockyList1.add( new DRock( new Posn( 350, 275 ) ) );
		rockyList1.add( new DRock( new Posn( 600, 300 ) ) );

		// vertical destructable rocks
		x = 825; y = 250;
		while( y < 600 ) {
			rockyList1.add( new DRock( new Posn( x, y ) ) );
			y+= 50;
		}

		// middle-matrix of non-destructable rocks
		x = 400; y = 300;
		while( y < 450 ) {
			while( x < 550 ) {
				rockyList1.add( new NDRock( new Posn( x, y ) ) );
				x+= 50;
			}
			y+= 50;
			x = 400;
		}

		// bottom-left matrix of non-destructable rocks
		x = 75; y = 500;
		while( y < 650 ) {
			while( x < 225 ) {
				rockyList1.add( new NDRock( new Posn( x, y ) ) );
				x+= 50;
			}
			y+= 50;
			x = 75;
		}

		// top-right matrix of non-destructable rocks
		x = 725; y = 100;
		while( y < 250 ) {
			while( x < 875 ) {
				rockyList1.add( new NDRock( new Posn( x, y ) ) );
				x+= 50;
			}
			y+= 50;
			x = 725;
		}

	TitleScreen title3 = new TitleScreen( 1, rockyList1, scaryList1, 2, 3, 3, 0 );
	OverWorld world1 = new OverWorld( new Hero( new Posn( 100, 150 ) ), 
						new LinkedList( ), new LinkedList( ), rockyList1, scaryList1,
						2, 3, 3, 0 );


		return

			t.checkExpect( title1.onKeyEvent(" "),
							title1, "test block advance to OverWorld" + "\n") &&

			t.checkExpect( title2.onTick( ),
							title3, "test creation of the first level" + "\n") &&

			t.checkExpect( title3.onTick( ),
							title3, "test storing the values instead of creating again" + "\n") &&

			t.checkExpect( title3.onKeyEvent(" "),
							world1, "test advance to first level" + "\n");


	}
	
	public static void main(String[] args) {
		
		Tester.runReport( new Tests( ), false, false );

		System.out.println("--------END OF CODE---------");
	}
}