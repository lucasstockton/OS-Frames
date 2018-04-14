import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Scanner scan = new Scanner(System.in);
		
	int _pageframes = 0; // The number of Frame storage Locations
	int _numberofframes = 0; // The Number of Page Frames to Process
	int _falts = 0; // The Number of times faulted
	int _framekinds = 0; // The Number of Frame Jobs to Request
	int _isFifo = 0; // The Number of Frame Jobs to Request
	boolean isFifo = true;  // This is so we can tell which algo to use.. 
	//int _
    //	int _numberofframes = 0;
	
	while (true)
	{
		System.out.println("Please enter the number of page frames ( 2 to 5 )");	
		
	_pageframes = scan.nextInt();
	
	if ( (1 < _pageframes)  && _pageframes < 6) 
		break;
	
	} 
	
	
	
	
	while (true)
	{
		System.out.println("Use Fifo 1 or LRU 0 ( 0 to 1 )");	
		
		_isFifo = scan.nextInt();
	
	if ( (-1 < _isFifo)  && _isFifo < 2) 
		break;
	
	}
	
	
	
	
	
	
	while(true)
	{
		System.out.println("Please select a frame size to calculate (2 to 20000");	
		
		_numberofframes = scan.nextInt();
	
	if ( (10 < _numberofframes)  && _numberofframes < 20001) 
		break;	
		
	}
	
	
	
	while (true)
	{
		System.out.println("Please enter the number of frame kinds ( 1 to 9 )");	
		
		_framekinds = scan.nextInt();
	
	if ( (0 < _framekinds)  && _framekinds < 10) 
		break;
	
	}
		
		
		int[][] framebuffer = new int[_pageframes+1][_numberofframes]; // Here we are setting up a history of frames plus a extra space to tell if we faulted.
		int[] workingset = new int[_numberofframes];  // 
		
		
		for (int i=0;i < _numberofframes; i++)  // Here we are loading in the Number of Frames with Random Page calls
			workingset[i] = ThreadLocalRandom.current().nextInt(1, _framekinds + 1); // Using the Random to tell. 
		
		//System.out.println("Here is the working sets ");  // This is just Debug information to see we have data
		//for (int i=0;i < _numberofframes; i++)
		//	System.out.print(workingset[i]);
		
		int[][] currentframe = new int[2][_pageframes]; // This is the current frame with the counters 
	
		
		for (int i=0;i < 2; i++) // Setting Defaulting the Current Frame values to 0 
			for (int j=0;j < _pageframes; j++)
				currentframe[i][j] = 0;
		
		
		
		
		for (int _frame=0; _frame < _numberofframes; _frame++ )
		{
			// Check if Frame is inside 
			int _fameLocation = -1; // location of frame
		
			
			for (int j=0; j < _pageframes; j++) // Check if the page is in the frame buffer
			  	{
				if (currentframe[0][j] == workingset[_frame])
					_fameLocation= j; // Setting the location;
									
			  	} // end of searching in page buffer
			
			if (_fameLocation > -1)
			{
				
				for (int j=0; j < _pageframes; j++)
				framebuffer[j][_fameLocation] = currentframe[0][j];
				
				if (!isFifo)
					currentframe[1][_fameLocation]++;
				
				framebuffer[_pageframes][_frame] = 0;
				
				
					
				
				
				continue; // WE HAVE NOTHING TO DO 
			/// COPY THE ARRAY INTO THE BUFFER AND SET THAT WE KNOW IT"S NOT A FAULT. 
			} else {
				// This is a page fault now we have to do the dirty. 
				
				_falts++;  // We have to increment the falts
				
				int _replaceLocation = -1;
				int _smallest = -1;
				
				
				for (int j=0; j < _pageframes; j++) // Checking to find the page load location. 
			  	{
				if (currentframe[0][j] == 0)
				{
					_replaceLocation= j; // Setting the location;
				    continue; // We don't need to search anymore so we skip.
				} // Testing for a black page frame.
				
				if (currentframe[1][j] >  _smallest)
					_replaceLocation = j;
			  	} // Here we found a replacement location 
				
				
				currentframe[0][_replaceLocation] = workingset[_frame];
				
				currentframe[1][_replaceLocation] = 0; // Resetting the count to 0;
				
				if (isFifo)
				for (int j=0; j < _pageframes; j++)
					currentframe[1][j]++;
								
				
				
				
				for (int j=0; j < _pageframes; j++) // Copy the stuf into the buffer
					framebuffer[j][_frame] = currentframe[0][j];
				
				
				framebuffer[_pageframes][_frame] = 1;
				
				
				
			} // end 
		//	current
			
			
			
			
		} // This is the loop to process the frames. 
		
	
	System.out.println("Number of page faults is " + _falts);
	scan.close();

	} // End Main Method
	
	

}
