import javalib.worldimages.*;

interface Enemies {
	public boolean checkHitHero( );
	public WorldImage enemyView( );
 	public boolean checkExplosion( Explosion e );
}