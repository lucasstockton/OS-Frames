package com.heuman.osstockton.frames;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class TestApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//System.out.println();
		
		Scanner scan = new Scanner(System.in);	
		boolean _repeat = true;
		
		while (true) {
			

		
		int _myinput = -1; // input
		int fifoFault = 0;
		int lruFault = 0;
		
		int _inputJobCount = 0;
		int _inputPageFrames = 0;
		

		boolean _displayOutput = false;
	
		
		
		System.out.print("Would you like to display output 1 for yes 0 for no) ?  : ");	
		_inputPageFrames = scan.nextInt();
		
		if ( _inputPageFrames == 1)
		{
			_displayOutput = true;
		} else 
			_displayOutput = false;
		
		
		
		
		System.out.print("How many page Frames for processing (Greater then 1) ?  : ");	
		_inputPageFrames = scan.nextInt();
		
		if ( _inputPageFrames < 1)
		{
			System.out.println("Invalid value exiting program!");
			break;
		}
		
		
		
		System.out.print("How many jobs to randomly access (Greater then 1 and less then 50) ?  : ");	
		_inputJobCount = scan.nextInt();
		
		if (!(1 < _inputJobCount  && _inputJobCount < 51))
		{
			System.out.println("Invalid value exiting program!");
			break;
		}
		
		
		Frame frameFIFO = new Frame(_inputPageFrames,true); 
		frameFIFO.Initialize();
		
		Frame frameLRU = new Frame(_inputPageFrames,false);
		frameLRU.Initialize();
		
				 
			System.out.print("How many page accesses to run (0 to quit)?  : ");	
			_myinput = scan.nextInt();
			
			if (_myinput == 0)
				break;
			
			for (int access : Constants.returnRandomNumArray(_inputJobCount, _myinput))
			//for (int i=0;i < _myinput; i++) 
			{
				if (_displayOutput)
				System.out.print(" Frame " + (frameFIFO.frameCounter +1) + " Access ("+ Constants.programNames[access] +")  ");
				//int access = ThreadLocalRandom.current().nextInt(1, 5 + 1);
				
			if (frameFIFO.accessItem(access)) fifoFault++;
		
			if (_displayOutput)
			System.out.print(frameFIFO + "  ");
		
		if (frameLRU.accessItem(access)) lruFault++;
		if (_displayOutput)
		System.out.println(frameLRU);
		
		
		
			}
			
			System.out.println("Total Faults FIFO " + fifoFault  + " Total Faults LRU " + lruFault + " with " + frameFIFO.frameCounter + " access attempts.");

		
		
			
			
			
		}
			
			
			scan.close();
		}

	// for (int i=0;i < _myinput; i++) // Here we are loading in the Number of
	// Frames with Random Page calls
	// workingset[i] = ; // Using the Random to tell.


	
}
