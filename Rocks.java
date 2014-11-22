import javalib.worldimages.*;

interface Rocks {
	public WorldImage rockView( );
	public boolean checkExplosion( Explosion e );
	public Posn getPin( );
	public int getWidth( );
	public int getHeight( );
}