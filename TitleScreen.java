import javalib.worldimages.*;
import javalib.funworld.*;
import javalib.colors.*;

import java.io.*;
import java.util.*;

class TitleScreen extends World {
	int level;
	LinkedList<Rocks> rockList;
	LinkedList<Enemies> enemyList;
	int firePower;
	int bombNum;
	int health;
	int spending;

	public TitleScreen( int level, LinkedList<Rocks> rockList, LinkedList<Enemies> enemyList,
						int firePower, int bombNum, int health, int spending ) {
		this.level = level;
		this.rockList = rockList;
		this.enemyList = enemyList;
		this.firePower = firePower;
		this.bombNum = bombNum;
		this.health = health;
		this.spending = spending;
	}

	public TitleScreen( int level, int firePower, int bombNum, int health, int spending ) {
		this.level = level;
		this.rockList = new LinkedList( );
		this.enemyList = new LinkedList( );
		this.firePower = firePower;
		this.bombNum = bombNum;
		this.health = health;
		this.spending = spending;
	}

	public TitleScreen( int level ) { 
		this.level = level;
		this.rockList = new LinkedList( );
		this.enemyList = new LinkedList( );
		this.firePower = 2;
		this.bombNum = 3;
		this.health = 3;
		this.spending = 0;
	} 

	public World onKeyEvent( String ke ) {

	// from title screen to level 1 pre-screen
	if( level == 0 ) {
		return new TitleScreen( 1 );
	}

	// from level 1 pre-screen to level 1
	else if( ( level == 1 ) && ( rockList.size( ) > 0 ) ) {
		return new OverWorld( new Hero( new Posn( 100, 150 ) ), 
			new LinkedList( ), new LinkedList( ), rockList, enemyList,
			firePower, bombNum, health, spending );
		}

	// from level 2 pre-screen to level 2
	else if( ( level == 2 ) && ( rockList.size( ) > 0 ) ) {
		return new OverWorld( new Hero( new Posn( 100, 150 ) ), 
			new LinkedList( ), new LinkedList( ), rockList, enemyList,
			firePower, bombNum, health, spending );
		}

	else if( ( level == 3 ) && ( rockList.size( ) > 0 ) ) {
		return new OverWorld( new Hero( new Posn( 100, 150 ) ), 
			new LinkedList( ), new LinkedList( ), rockList, enemyList,
			firePower, bombNum, health, spending );
		}

	// from the end screen to Hard mode
	else if( ( level == 4 ) && ( ke.equals("h") ) ) {
		return new TitleScreen( 1, 0, 1, 1, 0 );
	}

	// from the end screen to Continuing
	else if( ( level == 4 ) && ( ke.equals("p") ) ) {
		return new TitleScreen( 1, firePower, bombNum, health, spending );
	}

	// from the end screen to Starting Over
	else if( ( level == 4 ) && ( ke.equals("t") ) ) {
		return new TitleScreen( 1 );
	}

	// from game over to starting over
	else if(  ( level == 5 ) && ( ke.equals("t") ) ) {
		return new TitleScreen( 1 );
	}

	else
		return this;
	}

	public World onTick( ) {

		// making level one!
		if ( ( level == 1 ) && ( rockList.size( ) == 0 ) ) {

			LinkedList<Rocks> rockyList = new LinkedList( );
			LinkedList<Enemies> scaryList = new LinkedList( );

			scaryList.add( new Baddy( new Posn( 350, 200 ), 1 ) );
			scaryList.add( new Baddy( new Posn( 100, 375 ), 1 ) );
			scaryList.add( new Baddy( new Posn( 500, 475 ), 3 ) );
			scaryList.add( new Baddy( new Posn( 350, 150 ), 2 ) );
			scaryList.add( new Baddy( new Posn( 900, 125 ), 1 ) );
			scaryList.add( new Baddy( new Posn( 900, 500 ), 3 ) );
			scaryList.add( new Baddy( new Posn( 750, 550 ), 2 ) );
			scaryList.add( new Baddy( new Posn( 600, 150 ), 0 ) );

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
		rockyList.add( new DRock( new Posn( 225, 525 ) ) );
		rockyList.add( new DRock( new Posn( 250, 475 ) ) );
		rockyList.add( new DRock( new Posn( 275, 425 ) ) );
		rockyList.add( new DRock( new Posn( 300, 375 ) ) );
		rockyList.add( new DRock( new Posn( 325, 325 ) ) );
		rockyList.add( new DRock( new Posn( 350, 275 ) ) );
		rockyList.add( new DRock( new Posn( 600, 300 ) ) );

		// vertical destructable rocks
		x = 825; y = 250;
		while( y < 600 ) {
			rockyList.add( new DRock( new Posn( x, y ) ) );
			y+= 50;
		}

		// middle-matrix of non-destructable rocks
		x = 400; y = 300;
		while( y < 450 ) {
			while( x < 550 ) {
				rockyList.add( new NDRock( new Posn( x, y ) ) );
				x+= 50;
			}
			y+= 50;
			x = 400;
		}

		// bottom-left matrix of non-destructable rocks
		x = 75; y = 500;
		while( y < 650 ) {
			while( x < 225 ) {
				rockyList.add( new NDRock( new Posn( x, y ) ) );
				x+= 50;
			}
			y+= 50;
			x = 75;
		}

		// top-right matrix of non-destructable rocks
		x = 725; y = 100;
		while( y < 250 ) {
			while( x < 875 ) {
				rockyList.add( new NDRock( new Posn( x, y ) ) );
				x+= 50;
			}
			y+= 50;
			x = 725;
		}
			return new TitleScreen( level, rockyList, scaryList, 
									firePower, bombNum, health, spending );
		}

		else if ( ( level == 2 ) && ( rockList.size( ) == 0 ) ) {

			LinkedList<Rocks> rockyList = new LinkedList( );
			LinkedList<Enemies> scaryList = new LinkedList( );

			scaryList.add( new Baddy( new Posn( 750, 500 ), 1 ) );
			scaryList.add( new Baddy( new Posn( 750, 500 ), 2 ) );
			scaryList.add( new Baddy( new Posn( 750, 500 ), 3 ) );
			scaryList.add( new Baddy( new Posn( 750, 500 ), 0 ) );

			scaryList.add( new Baddy( new Posn( 650, 200 ), 1 ) );
			scaryList.add( new Baddy( new Posn( 650, 200 ), 2 ) );
			scaryList.add( new Baddy( new Posn( 650, 200 ), 3 ) );
			scaryList.add( new Baddy( new Posn( 650, 200 ), 0 ) );

			int x = 25;
			int y = 75;

		// horizontal borders and the exit!
		while( x < 1000 ) {
			rockyList.add( new NDRock( new Posn( x, 50 ) ) );

			if( ( x < 925 ) && ( x > 750 ) ) 
				rockyList.add( new DRock( new Posn( x, 625 ) ) );
			else
				rockyList.add( new NDRock( new Posn( x, 625 ) ) );

			x+= 50;
		}

		// vertical borders!
		while( y < 625 ) {
			rockyList.add( new NDRock( new Posn( 25, y ) ) );
			rockyList.add( new NDRock( new Posn( 975, y ) ) );
			
			y+= 25;
		}

		// checkerboard rows of non-destructable rocks
		x = 300; y = 200;
		while( y < 550 ) {
			while( x < 950 ) {
				rockyList.add( new NDRock( new Posn( x, y ) ) );
				x+= 150;
			}
			y+= 175;
			x = 150;
		}

		// horizontal bridges
		rockyList.add( new DRock( new Posn( 450, 150 ) ) );
		rockyList.add( new DRock( new Posn( 450, 100 ) ) );

		// vertical left side
		rockyList.add( new DRock( new Posn( 300, 425 ) ) );
		rockyList.add( new DRock( new Posn( 300, 475 ) ) );


		// horizontal bridge
		rockyList.add( new DRock( new Posn( 350, 375 ) ) );
		rockyList.add( new DRock( new Posn( 400, 375 ) ) );

		// vertical right side
		rockyList.add( new DRock( new Posn( 600, 425 ) ) );
		rockyList.add( new DRock( new Posn( 600, 475 ) ) );
		rockyList.add( new DRock( new Posn( 600, 525 ) ) );
		rockyList.add( new DRock( new Posn( 600, 575 ) ) );

		//vertical corner
		rockyList.add( new NDRock( new Posn( 900, 100 ) ) );
		rockyList.add( new NDRock( new Posn( 900, 150 ) ) );


			return new TitleScreen( level, rockyList, scaryList, 
									firePower, bombNum, health, spending );
		}

		else if ( ( level == 3 ) && ( rockList.size( ) == 0 ) ) {

			LinkedList<Rocks> rockyList = new LinkedList( );
			LinkedList<Enemies> scaryList = new LinkedList( );

			scaryList.add( new Baddy( new Posn( 750, 500 ), 3 ) );
			scaryList.add( new Baddy( new Posn( 650, 500 ), 2 ) );
			scaryList.add( new Baddy( new Posn( 750, 550 ), 1 ) );
			scaryList.add( new Baddy( new Posn( 850, 500 ), 0 ) );
			scaryList.add( new Baddy( new Posn( 250, 350 ), 3 ) );
			scaryList.add( new Baddy( new Posn( 800, 350 ), 1 ) );
			scaryList.add( new Baddy( new Posn( 650, 400 ), 2 ) );
			scaryList.add( new Baddy( new Posn( 750, 375 ), 0 ) );
			scaryList.add( new Baddy( new Posn( 750, 325 ), 3 ) );
			scaryList.add( new Baddy( new Posn( 875, 400 ), 2 ) );

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

			if( ( y <=  625 ) && ( y > 375 ) ) 
				rockyList.add( new DRock( new Posn( 25, y ) ) );
			 else 
				rockyList.add( new NDRock( new Posn( 25, y ) ) );

			rockyList.add( new NDRock( new Posn( 975, y ) ) );
			
			y+= 25;
		}

		// rows across of non-destructable rocks
		x = 75; y = 250;
		while( x < 950 ) {
			if ( x < 700 )
				rockyList.add( new NDRock( new Posn( x, y ) ) );
			else 
				rockyList.add( new DRock( new Posn( x, y ) ) );

			x+= 50;
		}

		x = 175; y = 450;
		while( x < 1000 ) {
				rockyList.add( new NDRock( new Posn( x, y ) ) );

			x+= 50;
		}

		rockyList.add( new DRock( new Posn( 700, 300 ) ) );
		rockyList.add( new DRock( new Posn( 700, 350 ) ) );
		rockyList.add( new DRock( new Posn( 700, 400 ) ) );



			return new TitleScreen( level, rockyList, scaryList, 
									firePower, bombNum, health, spending );
		}
		else {
			return this;
		}
			
	}

	public WorldImage makeImage( ) {

		WorldImage world = new RectangleImage( new Posn( 500, 325 ), 1000, 650, new White( ) );

		if( level == 0 ) {
			world = new OverlayImages( world, new FromFileImage( new Posn( 500, 325 ), "images/titlescreen.png" ) );
		}

		else if( level == 1 ) {
			world = new OverlayImages( world, new TextImage(
													new Posn( 500, 600 ),
													"LEVEL ONE", 50, new Black( ) ) );

			world = new OverlayImages( world, new FromFileImage( 
													new Posn( 500, 250 ),
													"images/bigbomb.png" ) );
		}

		else if( level == 2 ) {
			world = new OverlayImages( world, new TextImage(
													new Posn( 500, 600 ),
													"LEVEL TWO", 50, new Black( ) ) );

			world = new OverlayImages( world, new FromFileImage( 
													new Posn( 350, 250 ),
													"images/bigbomb.png" ) );

			world = new OverlayImages( world, new FromFileImage( 
													new Posn( 650, 250 ),
													"images/bigbomb.png" ) );
		}

		else if( level == 3 ) {
			world = new OverlayImages( world, new TextImage(
													new Posn( 500, 600 ),
													"LEVEL THREE", 50, new Black( ) ) );

			world = new OverlayImages( world, new FromFileImage( 
													new Posn( 200, 250 ),
													"images/bigbomb.png" ) );

			world = new OverlayImages( world, new FromFileImage( 
													new Posn( 500, 250 ),
													"images/bigbomb.png" ) );

			world = new OverlayImages( world, new FromFileImage( 
													new Posn( 800, 250 ),
													"images/bigbomb.png" ) );
		}

		else if( level == 4 ) {
			world = new OverlayImages( world, new FromFileImage(
													new Posn( 500, 350 ),
													"images/congrats.png" ) );
		}

		else if( level == 5 ) {
			world = new OverlayImages( world, new FromFileImage(
													new Posn( 490, 250 ),
													"images/gameover.png" ) );
		}

		return world;
	}


	public static void main(String[] args) {

		TitleScreen t = new TitleScreen( 0 );
		t.bigBang( 1000, 650, 0.1);
	}

}