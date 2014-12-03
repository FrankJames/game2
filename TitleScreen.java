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

	else if( ( level == 4 ) && ( ke.equals("h") ) ) {
		return new TitleScreen( 1, 0, 1, 1, 0 );
	}

	else if( ( level == 4 ) && ( ke.equals("p") ) ) {
		return new TitleScreen( 1, firePower, bombNum, health, spending );
	}

	else if( ( level == 4 ) && ( ke.equals("t") ) ) {
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

			int x = 25;
			int y = 75;

		// horizontal borders and the exit!
		while( x < 1000 ) {
			rockyList.add( new NDRock( new Posn( x, 50 ) ) );

			if( ( x < 975 ) && ( x > 800 ) ) 
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

		// checkerboard of non-destructable rocks
		x = 300; y = 200;
		while( y < 550 ) {
			while( x < 950 ) {
				rockyList.add( new NDRock( new Posn( x, y ) ) );
				x+= 150;
			}
			y+= 175;
			x = 150;
		}
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
			scaryList.add( new Baddy( new Posn( 800, 250 ), 1 ) );

			int x = 25;
			int y = 75;

		// horizontal borders and the exit!
		while( x < 1000 ) {
			rockyList.add( new NDRock( new Posn( x, 50 ) ) );
			rockyList.add( new NDRock( new Posn( x, 625 ) ) );

			x+= 50;
		}

		// vertical borders!
		while( y < 625 ) {

			if( ( y <=  625 ) && ( y > 375 ) ) 
				rockyList.add( new DRock( new Posn( 25, y ) ) );
			 else 
				rockyList.add( new NDRock( new Posn( 25, y ) ) );

			rockyList.add( new NDRock( new Posn( 975, y ) ) );
			
			y+= 25;
		}

		// rows across of non-destructable rocks
		x = 50; y = 250;
		while( x < 700 ) {
			rockyList.add( new NDRock( new Posn( x, y ) ) );
			x+= 50;
		}

		x = 150; y = 450;
		while( x < 1000 ) {
			rockyList.add( new NDRock( new Posn( x, y ) ) );
			x+= 50;
		}
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
			world = new OverlayImages( world, new TextImage(
													new Posn( 500, 325 ),
													"CONGRATULATIONS!", 50, new Black( ) ) );

			world = new OverlayImages( world, new TextImage( 
													new Posn( 500, 450 ),
													"try again? press T!", 24, new Black( ) ) );

			world = new OverlayImages( world, new TextImage(
													new Posn( 200, 450 ),
													"hard mode? press H!", 24, new Black( ) ) );

			world = new OverlayImages( world, new TextImage( 
													new Posn( 800, 450 ),
													"keep your powerups? press P!", 24, new Black( ) ) );
		}

		return world;
	}


	public static void main(String[] args) {

		TitleScreen t = new TitleScreen( 0 );
		t.bigBang( 1000, 650, 0.1);
	}

}