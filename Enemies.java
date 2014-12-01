import javalib.worldimages.*;

interface Enemies {
	public Posn getPin( );
	public int getWidth( );
	public int getHeight( );
	public WorldImage enemyView( );
	public Enemies enemyMove( );
	public Enemies enemyChangeDirection( );
	public boolean checkHitHero( Hero h );
	public boolean checkRock( Rocks r );
 	public boolean checkExplosion( Explosion e );
}