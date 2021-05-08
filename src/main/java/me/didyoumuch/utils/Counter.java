package me.didyoumuch.utils;

public class Counter {

	private long ms = 0L;
	
    public long getCurrentMS(){
		return System.nanoTime() / 1000000L;
	}

    public boolean hasReached(float f){
		return (float) (getCurrentMS() - this.ms) >= f; 
	}
    
    public void reset(){
		this.ms = getCurrentMS();
	}
}
