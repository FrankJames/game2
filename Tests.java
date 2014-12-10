import tester.*;

import javalib.worldimages.*;
import javalib.funworld.*;
import javalib.colors.*;

import java.io.*;
import java.util.*;


class Tests {

	LinkedList<Enemies> scaryListEMPTY = new LinkedList( );
	LinkedList<Bomb> bombListEMPTY = new LinkedList( );
	LinkedList<Explosion> explosionListEMPTY = new LinkedList( );
	LinkedList<Rocks> rockListEMPTY = new LinkedList( );
	Hero hero0 = new Hero( new Posn( 100, 100 ) );
	
	// **************************************************
	// *****   testing onKeyEvent( ) -- OVERWORLD   *****
	// **************************************************
	
	public boolean testHeroMove( Tester t ) {

	Hero hero0 = new Hero( new Posn( 100, 100 ) );
	Hero heroUP = new Hero( new Posn( 100, 19 ), "images/uphero.png", true, 0 );
	Hero heroRIGHT = new Hero( new Posn( 140, 100 ), "images/righthero.png", true, 0 );
	Hero heroLEFT = new Hero( new Posn( 60, 100 ), "images/lefthero.png", true, 0 );
	Hero heroDOWN = new Hero( new Posn( 100, 181 ), "images/downhero.png", true, 0 );

		return

			t.checkExpect( hero0.heroMove("up"),
							heroUP, "testing hero move up" + "\n") &&

			t.checkExpect( hero0.heroMove("right"),
							heroRIGHT, "testing hero move right" + "\n") &&

			t.checkExpect( hero0.heroMove("left"),
							heroLEFT, "testing hero move left" + "\n") &&

			t.checkExpect( hero0.heroMove("down"),
							heroDOWN, "testing hero move down" + "\n");
	}

	public boolean testChangeOWtoMenu( Tester t ) {

	OverWorld world1 = new OverWorld( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );
	Menu menu1 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0 );

		return 

			t.checkExpect( world1.onKeyEvent("x"),
							menu1, "testing OverWorld -> Menu" + "\n" );
	}

	public boolean testCreateBomb( Tester t ) {

	LinkedList<Bomb> bombList1 = new LinkedList( );
	OverWorld world1 = new OverWorld( hero0, bombList1, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );
	LinkedList<Bomb> bombList2 = new LinkedList( );
	bombList2.add( new Bomb( new Posn( 100, 100 ) ) );
	OverWorld world2 = new OverWorld( hero0, bombList2, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );

		return

			t.checkExpect( world1.onKeyEvent("z"),
							world2, "testing creating a bomb object" + "\n" );

	}

	// **************************************************
	// ***** 	testing onTick( ) -- OVERWORLD 		*****
	// **************************************************
	
	public boolean testMoveEnemy( Tester t ) {

	Baddy baddy1 = new Baddy( new Posn( 200, 200 ), 0 );
	Baddy baddy2 = new Baddy( new Posn( 200, 220 ), "images/downbaddy.png", 0 );

	LinkedList<Enemies> scaryList1 = new LinkedList( );
	scaryList1.add( baddy1 );

	LinkedList<Enemies> scaryList2 = new LinkedList( );
	scaryList2.add( baddy2 );

	OverWorld world1 = new OverWorld( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryList1 );
	OverWorld world2 = new OverWorld( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryList2 );

		return

			t.checkExpect( world1.onTick( ),
							world2, "testing moving enemies objects" + "\n");
	}


	public boolean testAdvanceBombTimer( Tester t ) {
		
		LinkedList<Bomb> bombList1 = new LinkedList( );
		LinkedList<Bomb> bombList2 = new LinkedList( );
		LinkedList<Bomb> bombList3 = new LinkedList( );
		LinkedList<Explosion> explosionList0 = new LinkedList( );
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


		OverWorld world1 = new OverWorld( hero0, bombList1, explosionList0, rockListEMPTY, scaryListEMPTY );
		OverWorld world2 = new OverWorld( hero0, bombList2, explosionList0, rockListEMPTY, scaryListEMPTY );
		OverWorld world3 = new OverWorld( hero0, bombList3, explosionList0, rockListEMPTY, scaryListEMPTY );
		OverWorld world4 = new OverWorld( hero0, bombListEMPTY, explosionList1, rockListEMPTY, scaryListEMPTY );

		return 

			t.checkExpect( world1.onTick( ),
							world2, "test advance bomb timer" + "\n") &&

			t.checkExpect( world3.onTick( ),
							world4, "test bomb to explosion" + "\n");
	}

	public boolean testAdvanceExplosionTimer( Tester t ) {

		LinkedList<Explosion> explosionList1 = new LinkedList( );
		LinkedList<Explosion> explosionList2 = new LinkedList( );
		LinkedList<Explosion> explosionList3 = new LinkedList( );

		explosionList1.add( new Explosion( 1, new Posn( 250, 250 ) ) );
		explosionList2.add( new Explosion( 2, new Posn( 250, 250 ) ) );
		explosionList3.add( new Explosion( 6, new Posn( 250, 250 ) ) );

		OverWorld world1 = new OverWorld( hero0, bombListEMPTY, explosionList1, rockListEMPTY, scaryListEMPTY );
		OverWorld world2 = new OverWorld( hero0, bombListEMPTY, explosionList2, rockListEMPTY, scaryListEMPTY );
		OverWorld world3 = new OverWorld( hero0, bombListEMPTY, explosionList3, rockListEMPTY, scaryListEMPTY );
		OverWorld world4 = new OverWorld( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );

		return

			t.checkExpect( world1.onTick( ), 
							world2, "test advancing explosion timer" + "\n") &&


			t.checkExpect( world3.onTick( ),
							world4, "test removing explosions" + "\n");

	}

	public boolean testHeroInvincibility( Tester t ) {

		Hero hero1 = new Hero( new Posn( 500, 500 ) );
		Hero hero2 = new Hero( new Posn( 500, 500 ), "images/downhero.png", false, 1 );
		Hero hero3 = new Hero( new Posn( 500, 500 ), "images/downhero.png", false, 2 );
		Hero hero4 = new Hero( new Posn( 500, 500 ), "images/downhero.png", false, 3 );
		Hero hero5 = new Hero( new Posn( 500, 500 ), "images/downhero.png", false, 25 );
		Hero hero6 = new Hero( new Posn( 500, 500 ) );

		LinkedList<Explosion> explosionList1 = new LinkedList( );
		LinkedList<Explosion> explosionList2 = new LinkedList( );
		explosionList1.add( new Explosion( 1, new Posn( 500, 500 ) ) );
		explosionList2.add( new Explosion( 2, new Posn( 500, 500 ) ) );

		OverWorld world1 = new OverWorld( hero1, bombListEMPTY, explosionList1, rockListEMPTY, scaryListEMPTY );
		OverWorld world2 = new OverWorld( hero2, bombListEMPTY, explosionList2, rockListEMPTY, scaryListEMPTY, 2, 3, 2, 0 );
		OverWorld world3 = new OverWorld( hero3, bombListEMPTY, explosionList1, rockListEMPTY, scaryListEMPTY );
		OverWorld world4 = new OverWorld( hero4, bombListEMPTY, explosionList2, rockListEMPTY, scaryListEMPTY );
		OverWorld world5 = new OverWorld( hero5, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );
		OverWorld world6 = new OverWorld( hero6, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );

		return

			t.checkExpect( world1.onTick( ),
							world2, "test reducing health" + "\n") &&

			t.checkExpect( world3.onTick( ),
							world4, "test invincibility" + "\n") &&

			t.checkExpect( world5.onTick( ), 
							world6, "test become vulnerable" + "\n");
	}

	public boolean testCollisions( Tester t ) {

		LinkedList<Explosion> explosionList1 = new LinkedList( );
		LinkedList<Explosion> explosionList2 = new LinkedList( );
		LinkedList<Enemies> scaryList1 = new LinkedList( );
		LinkedList<Enemies> scaryList2 = new LinkedList( );
		LinkedList<Enemies> scaryList3 = new LinkedList( );
		LinkedList<Enemies> scaryList4 = new LinkedList( );
		LinkedList<Enemies> scaryList5 = new LinkedList( );
		LinkedList<Enemies> scaryList6 = new LinkedList( );
		LinkedList<Rocks> rockList1 = new LinkedList( );
		LinkedList<Rocks> rockList2 = new LinkedList( );

		explosionList1.add( new Explosion( 1, new Posn( 200, 200 ) ) );
		explosionList2.add( new Explosion( 2, new Posn( 200, 200 ) ) );

		scaryList1.add( new Baddy( new Posn( 210, 200 ), "images/leftbaddy.png", 3 ) );
		scaryList2.add( new Baddy( new Posn( 210, 200 ), "images/leftbaddy.png", 3 ) );
		scaryList3.add( new Baddy( new Posn( 210, 200 ), "images/rightbaddy.png", 1 ) );
		scaryList4.add( new Baddy( new Posn( 210, 200 ), "images/upbaddy.png", 2 ) );
		scaryList5.add( new Baddy( new Posn( 210, 200 ), "images/downbaddy.png", 0 ) );

		rockList1.add( new DRock( new Posn( 200, 200 ) ) );
		rockList2.add( new NDRock( new Posn( 200, 200 ) ) );


		Hero hero1 = new Hero( new Posn( 150, 150 ) );
		Hero hero2 = new Hero( new Posn( 150, 150 ), "images/downhero.png", false, 1 );

		OverWorld world1 = new OverWorld( hero0, bombListEMPTY, explosionList1, rockListEMPTY, scaryList1 );
		OverWorld world2 = new OverWorld( hero0, bombListEMPTY, explosionList2, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 1 );
		OverWorld world3 = new OverWorld( hero0, bombListEMPTY, explosionList1, rockList1, scaryListEMPTY );
		OverWorld world4 = new OverWorld( hero0, bombListEMPTY, explosionList2, rockListEMPTY, scaryListEMPTY );
		OverWorld world5 = new OverWorld( hero0, bombListEMPTY, explosionList1, rockList2, scaryListEMPTY );
		OverWorld world6 = new OverWorld( hero0, bombListEMPTY, explosionList2, rockList2, scaryListEMPTY );
		OverWorld world7 = new OverWorld( hero1, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryList2 );
		OverWorld world8 = new OverWorld( hero2, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryList2, 2, 3, 2, 0 );
		OverWorld world9 = new OverWorld( hero2, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryList3, 2, 3, 2, 0 );
		OverWorld world10 = new OverWorld( hero2, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryList4, 2, 3, 2, 0 );
		OverWorld world11 = new OverWorld( hero2, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryList5, 2, 3, 2, 0 );

		return 

			// this also tests increasing spending points!
			t.checkExpect( world1.onTick( ),
							world2, "test removing enemies" + "\n") &&

			t.checkExpect( world3.onTick( ),
							world4, "test removing destructable rocks" + "\n") &&

			t.checkExpect( world5.onTick( ),
							world6, "test not removing non-destructable rocks" + "\n") &&

			t.checkOneOf( "test enemy hitting hero" + "\n", world7.onTick( ), world8, world9, world10, world11);
	}

	public boolean testAdvancetoTitle( Tester t ) {

		Hero hero1 = new Hero( new Posn( 1020, 500 ) );
		Hero hero2 = new Hero( new Posn( 800, 690 ) );
		Hero hero3 = new Hero( new Posn( -40, 400 ) );

		OverWorld world1 = new OverWorld( hero1, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );
		OverWorld world2 = new OverWorld( hero2, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );
		OverWorld world3 = new OverWorld( hero3, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );

		TitleScreen title1 = new TitleScreen( 2 );
		TitleScreen title2 = new TitleScreen( 3 );
		TitleScreen title3 = new TitleScreen( 4 );


		return 

			t.checkExpect( world1.onTick( ),
							title1, "test finishing level 1" + "\n") &&

			t.checkExpect( world2.onTick( ),
							title2, "test finishing level 2" + "\n") &&

			t.checkExpect( world3.onTick( ),
							title3, "test finishing level 3" + "\n");
	}

	// **************************************************
	// *****      testing onKeyEvent( ) -- MENU 	*****
	// **************************************************
	
	public boolean testChangeMenutoOW( Tester t ) {

	OverWorld world1 = new OverWorld( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY );
	Menu menu1 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0 );

		return 

			t.checkExpect( menu1.onKeyEvent("x"),
							world1, "testing Menu -> OverWorld" + "\n");
	}

	public boolean testChangeValues( Tester t ) {

	Menu menu1 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0, 1 );
	Menu menu2 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 3, 3, 3, 0, 0 );
	Menu menu3 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0, 1 );
	Menu menu4 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 1, 3, 3, 0, 2 );
	Menu menu5 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0, 0 );
	Menu menu6 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0, 0 );
	Menu menu7 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 1, 2, 1 );
	Menu menu8 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 1, 2, 1 );
	Menu menu9 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 1, 1 );
	Menu menu10 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0, 1 );
	Menu menu11 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0, 1 );
	Menu menu12 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 1, 1 );
	Menu menu13 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0, 1 );
	Menu menu14 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0, 1 );
	Menu menu15 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 2, 1 );
	Menu menu16 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 2, 1 );

		return

			t.checkExpect( menu1.onKeyEvent("right"),
							menu2, "test spending points" + "\n") &&

			t.checkExpect( menu3.onKeyEvent("left"),
							menu4, "test gaining points" + "\n") &&

			t.checkExpect( menu5.onKeyEvent("right"),
							menu6, "test blocking spending" + "\n") &&

			t.checkExpect( menu7.onKeyEvent("left"),
							menu8, "test blocking gaining" + "\n") &&

			t.checkExpect( menu9.onKeyEvent("up"),
							menu10, "test moving arrow up" + "\n") &&

			t.checkExpect( menu11.onKeyEvent("down"),
							menu12, "test moving arrow down" + "\n") &&

			t.checkExpect( menu13.onKeyEvent("up"),
							menu14, "test blocking moving arrow up" + "\n") &&

			t.checkExpect( menu15.onKeyEvent("down"),
							menu16, "test blocking moving arrow down" + "\n");

	}

	// **************************************************
	// ***** 		testing onTick( ) -- MENU       *****
	// **************************************************
	
	public boolean testMenuOnTick( Tester t ) {

	Menu menu1 = new Menu( hero0, bombListEMPTY, explosionListEMPTY, rockListEMPTY, scaryListEMPTY, 2, 3, 3, 0 );

		return

			t.checkExpect( menu1.onTick( ),
							menu1, "testing Menu on tick" + "\n");
	}

	// **************************************************
	// testing OnKeyEvent( ) AND onTick( ) -- TITLESCREEN
	// **************************************************
	
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
		
		Tester.runReport( new Tests( ), true, false );

	}
}