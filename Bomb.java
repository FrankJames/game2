import javalib.worldimages.*;

class Bomb {
	int timer;
	Posn pin;

	public Bomb( Posn pin ) {
		this.timer = 30;
		this.pin = pin;
	}
}