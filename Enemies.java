import javalib.worldimages.*;

interface Enemies {
	public boolean checkHitHero( Hero h );
	public WorldImage enemyView( );
	public Enemies enemyMove( );
	public Enemies enemyChangeDirection( );
	public boolean checkRock( Rocks r );
 	public boolean checkExplosion( Explosion e );
}