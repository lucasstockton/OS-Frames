package com.heuman.osstockton.frames;

public class Frame {

	int[][] frame;
	int frameSize;
	boolean isFalt;
	boolean policy;
	int frameCounter;
	
	
	public Frame(int _frameSize, boolean _policy) {
		this.frameSize = _frameSize;
		this.frame = new int[2][this.frameSize];
		this.isFalt = false;
		this.policy = _policy;
		this.frameCounter = 0;
		
	}
	
	
	public int time()
	{
		return frameCounter;
	}
	
	public void Initialize() {
		
		for (int i=0; i < 2; i++)
			for (int j=0; j < this.frameSize; j++)
				if (i==1) frame[i][j] = 10000; else frame[i][j] = 0; 
		
	}
	
	
	public int getFrame(int index) {
		return this.frame[0][index];
	}
	
	public int getCount(int index) {
		return this.frame[1][index];
	}
	
	public void setFrame(int index, int value) {
		this.frame[0][index] = value;
		if (policy)
		resetCount(index); // Reset the Count
		else IncLRU(index); 
		//frameCounter++;
	}
	
	private void resetCount(int index) {
		// TODO Auto-generated method stub
		this.frame[1][index] = 0;
	}

	public int getReplaceIndex()
	{
		
	int index = 0;
	
	for (int i = 1; i < frameSize; i++)
		if (getCount(i) > getCount(index))
			index = i;
		
		return index; 
	
	}
	
	
	public boolean accessItem(int item)
	{
		frameCounter++;
	isFalt = false;
	
	for (int i = 0; i < frameSize; i++)
	
		if (item == this.getFrame(i))
		{
			updatePolicy(i);
			return false;
		}
	
	this.setFrame(getReplaceIndex(), item);
	this.isFalt = true;
	return true;
					
	}
	
	
	
	public void updatePolicy(int index) {
		
		if(policy)
		{
			for (int i = 0; i < frameSize; i++)
			IncCount(i);
		} else {
			IncLRU(index);
			
		}
		
		
	}

	private void IncLRU(int index) {
		// TODO Auto-generated method stub
		
		this.frame[1][index] = frameCounter * -1; 
		
	}

	private void IncCount(int index) {

		this.frame[1][index]++;
		
	}
		
		
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub

		String s = "";
		
		for (int i = 0; i < frameSize; i++)
			if (getFrame(i) == 0) 
				s+="-";
			else
			s+=Constants.programNames[getFrame(i)];		
		


if (isFalt)
	s+=" **";
else s+="   ";


		return s;	
		

	}
	

}
