import java.util.Arrays;

public class ProjectBDriver {


	public static void main(String[] args) {
		FrontBackCappedListInterface<Integer> list = new ArrayFrontBackCappedList<Integer>(10);
		
		// un-comment the lines below to test the extra credit
		 list = new ListFrontBackCappedList<Integer>(10);
		 System.out.println("********************DRIVER BEING RUN WITH EXTRA CREDIT CLASS***********************************");
	
		System.out.println("*****************************TESTING ISEMPTY AND EMPTY DISPLAY*****************************");
		// parameter 1: the list
		// parameter 2: the expected result for if the list is empty
		// parameter 3: the expected result for if the list is full
		testIsEmptyFull(list, true, false);
		
		// parameter 1: the list
		// parameter 2: the expected size of the list
		testSize(list, 0);
		
		// parameter 1: the list
		// parameter 2: the expected String returned from the toString method
		testDisplayMatch(list, "size=0; capacity=10;	[]");

		System.out.println("\n*****************************TESTING ADD TO BACK*****************************");
		// parameter 1: the list
		// parameter 2: indicates if we are adding to the front or back
		// parameter 3: the values to add; values are added from the array beginning (index 0) to end; 
		//			    for example, {1, 2, 3} will first add 1 to the back, then 2 to the back, then 3 to the back
		// parameter 4: expected return value (true if the element was added, false otherwise)
		// parameter 5: a description of the test
		testAdd(list, AddRemovePosition.BACK, new Integer[] {7}, true, "addBack to empty list");
		testDisplayMatch(list, "size=1; capacity=10;	[7]");

		testAdd(list, AddRemovePosition.BACK, new Integer[] {9, 5}, true, "addBack to non-empty list");		
		testIsEmptyFull(list, false, false);	
		testSize(list, 3);
		testDisplayMatch(list, "size=3; capacity=10;	[7, 9, 5]");
		
		testAdd(list, AddRemovePosition.BACK, new Integer[] {5, 3, 2, 1, 9, 8, 7}, true, "addBack to fill the list");
		testIsEmptyFull(list, false, true);
		testSize(list, 10);
		testDisplayMatch(list, "size=10; capacity=10;	[7, 9, 5, 5, 3, 2, 1, 9, 8, 7]");

		testAdd(list, AddRemovePosition.BACK, new Integer[] {4}, false, "addBack to full list");
		testSize(list, 10);
		testDisplayMatch(list, "size=10; capacity=10;	[7, 9, 5, 5, 3, 2, 1, 9, 8, 7]");
		

		System.out.println("\n*****************************TESTING CLEAR*****************************");
		list.clear();
		testIsEmptyFull(list, true, false);
		testDisplayMatch(list, "size=0; capacity=10;	[]");
		
	
		System.out.println("\n*****************************TESTING ADD TO FRONT*****************************");
		list.clear();

		// parameter 1: the list
		// parameter 2: indicates if we are adding to the front or back
		// parameter 3: the values to add; values are added from the array beginning (index 0) to end; 
		//			    for example, {1, 2, 3} will first add 1 to the front, then 2 to the front, then 3 to the front
		// parameter 4: expected return value (true if the element was added, false otherwise)
		// parameter 5: a description of the test
		testAdd(list, AddRemovePosition.FRONT, new Integer[] {2}, true, "addFront to empty list");
		testDisplayMatch(list, "size=1; capacity=10;	[2]");
		
		testAdd(list, AddRemovePosition.FRONT, new Integer[] {4, 3}, true, "addFront to non-empty list");
		testIsEmptyFull(list, false, false);
		testSize(list, 3);
		testDisplayMatch(list, "size=3; capacity=10;	[3, 4, 2]");
		
		testAdd(list, AddRemovePosition.FRONT, new Integer[] {7, 9, 5, 4, 3, 8, 1}, true, "addFront to fill the list");
		testIsEmptyFull(list, false, true);
		testSize(list, 10);
		testDisplayMatch(list, "size=10; capacity=10;	[1, 8, 3, 4, 5, 9, 7, 3, 4, 2]");
	
		testAdd(list, AddRemovePosition.FRONT, new Integer[] {4}, false, "addFront to full list");
		testSize(list, 10);
		testDisplayMatch(list, "size=10; capacity=10;	[1, 8, 3, 4, 5, 9, 7, 3, 4, 2]");
		

		System.out.println("\n*****************************TESTING CONTAINS*****************************");
		clearAndRefillTheList(list, new Integer[] {1, 8, 3, 4, 5, 9, 7, 3, 4, 2});

		// parameter 1: the list
		// parameter 2: the element to look for
		// parameter 3: the expected result (true if the list contains that element, false otherwise)
		// parameter 4: a description of the test
		testContains(list, 1, true, "element is in the first position");
		testContains(list, 2, true, "element is in the last position");
		testContains(list, 5, true, "element is in the middle");
		testContains(list, 3, true, "element is in the list more than once");
		testContains(list, 0, false, "element is not in the list");

		testDisplayMatch(list, "size=10; capacity=10;	[1, 8, 3, 4, 5, 9, 7, 3, 4, 2]");


		System.out.println("\n*****************************TESTING INDEX OF*****************************");
		clearAndRefillTheList(list, new Integer[] {1, 8, 3, 4, 5, 9, 7, 3, 4, 2});
		
		// parameter 1: the list
		// parameter 2: indicates if we are finding the index of the first or last match
		// parameter 3: the value to search for 
		// parameter 4: expected result (the index where the element appears)
		// parameter 5: a description of the test
		testIndexOf(list, IndexPosition.FIRST, 1, 0, "first element in the list");
		testIndexOf(list, IndexPosition.FIRST, 2, 9, "last element in the list");
		testIndexOf(list, IndexPosition.FIRST, 5, 4, "element in the middle of the list");
		testIndexOf(list, IndexPosition.FIRST, 3, 2, "repeated element in the list");
		testIndexOf(list, IndexPosition.FIRST, 0,    "element not in the list");
		
		testDisplayMatch(list, "size=10; capacity=10;	[1, 8, 3, 4, 5, 9, 7, 3, 4, 2]");
		
		
		System.out.println("\n*****************************TESTING LAST INDEX OF*****************************");
		clearAndRefillTheList(list, new Integer[] {1, 8, 3, 4, 5, 9, 7, 3, 4, 2});
		
		testIndexOf(list, IndexPosition.LAST, 1, 0, "first element in the list");
		testIndexOf(list, IndexPosition.LAST, 2, 9, "last element in the list");
		testIndexOf(list, IndexPosition.LAST, 5, 4, "element in the middle of the list");
		testIndexOf(list, IndexPosition.LAST, 3, 7, "repeated element in the list");
		testIndexOf(list, IndexPosition.LAST, 0,    "element not in the list");
			
		testDisplayMatch(list, "size=10; capacity=10;	[1, 8, 3, 4, 5, 9, 7, 3, 4, 2]");
		

		System.out.println("\n*****************************TESTING REMOVES*****************************");
		clearAndRefillTheList(list, new Integer[] {1, 8, 3, 4, 5, 9, 7, 3, 4, 2});
		
		// parameter 1: the list
		// parameter 2: indicates if we are removing from the front or back
		// parameter 3: if it exists, this is the expected value returned from the remove
		// parameter 4: a description of the test
		testRemove(list, AddRemovePosition.FRONT, 1, "remove from non-empty");
		testDisplayMatch(list, "size=9; capacity=10;	[8, 3, 4, 5, 9, 7, 3, 4, 2]");
		
		testRemove(list, AddRemovePosition.FRONT, 8, "remove from non-empty");
		testDisplayMatch(list, "size=8; capacity=10;	[3, 4, 5, 9, 7, 3, 4, 2]");
		
		testRemove(list, AddRemovePosition.BACK, 2, "remove from non-empty");
		testDisplayMatch(list, "size=7; capacity=10;	[3, 4, 5, 9, 7, 3, 4]");
		
		testRemove(list, AddRemovePosition.BACK, 4, "remove from non-empty");
		testSize(list, 6);
		testDisplayMatch(list, "size=6; capacity=10;	[3, 4, 5, 9, 7, 3]");
				
		list.clear();
		// parameter 1: the list
		// parameter 2: indicates if we are removing from the front or back
		// parameter 3: a description of the test
		testRemove(list, AddRemovePosition.FRONT, "remove from empty");
		testIsEmptyFull(list, true, false);
		testSize(list, 0);
		testDisplayMatch(list, "size=0; capacity=10;	[]");

		testRemove(list, AddRemovePosition.BACK, "remove from empty");
		testIsEmptyFull(list, true, false);
		testSize(list, 0);
		testDisplayMatch(list, "size=0; capacity=10;	[]");
		
		list.clear();		list.addFront(1);
		testRemove(list, AddRemovePosition.FRONT, 1, "remove from singleton added to front");
		testIsEmptyFull(list, true, false);
		
		list.clear();		list.addFront(1);
		testRemove(list, AddRemovePosition.BACK, 1, "remove from singleton added to back");
		testIsEmptyFull(list, true, false);
		
		list.clear();		list.addBack(1);
		testRemove(list, AddRemovePosition.FRONT, 1, "remove from singleton added to front");
		testIsEmptyFull(list, true, false);
		
		list.clear();		list.addBack(1);
		testRemove(list, AddRemovePosition.BACK, 1, "remove from singleton added to back");
		testIsEmptyFull(list, true, false);
		
			
		System.out.println("\n*****************************TESTING MIX OF ADDS AND REMOVES*****************************");
		list.clear();
		list.addFront(3); 		list.addBack(2);		list.addFront(4);
		list.addFront(5);		list.addBack(3);		list.addBack(8);
		list.addBack(9);
		testDisplayMatch(list, "size=7; capacity=10;	[5, 4, 3, 2, 3, 8, 9]");
		
		list.removeFront(); list.removeBack();
		testDisplayMatch(list, "size=5; capacity=10;	[4, 3, 2, 3, 8]");
	
		
		System.out.println("\n*****************************TESTING GET ENTRY*****************************");
		clearAndRefillTheList(list, new Integer[] {4, 3, 2, 3, 8});
		
		// parameter 1: the list
		// parameter 2: the index at which you will get the element
		// parameter 3: the expected result (the element at the specified index)
		// parameter 4: a description of the test
		testGetEntry(list, 0, 4, "getting first position");
		testGetEntry(list, 4, 8, "getting last position");
		testGetEntry(list, 2, 2, "getting a middle position");
		
		// parameter 1: the list
		// parameter 2: the index at which you will get the element; these method calls test invalid indices
		// parameter 3: a description of the test
		testGetEntry(list, -1,   "invalid index");
		testGetEntry(list, 11,   "invalid index");
		testGetEntry(list, 5,   "invalid index");
		testGetEntry(list, 7,    "invalid index");
	
			
		System.out.println("\n*****************************TESTING WITH STRINGS*****************************");
		FrontBackCappedListInterface<String> wordList = new ArrayFrontBackCappedList<String>(20);
		testAdd(wordList, AddRemovePosition.FRONT, new String[] {"job!", "Nice", "it!", "did", "You"}, true, "test with Strings");
		testAdd(wordList, AddRemovePosition.BACK, new String[] {"You", "rock!"}, true, "test with Strings");
		testDisplayMatch(wordList, "size=7; capacity=20;	[You, did, it!, Nice, job!, You, rock!]");
		testContains(wordList, new String("it!"), true, "test with Strings");
		testIndexOf(wordList, IndexPosition.FIRST, new String("You"), 0, "test with Strings");
		testIndexOf(wordList, IndexPosition.LAST, new String("You"), 5, "test with Strings");
		
	}

	/*
	 * The methods below are designed to help support the tests cases run from main. You don't
	 * need to use, modify, or understand these methods. You can safely ignore them. :) 
	 */

	public static <T> void testDisplayMatch(FrontBackCappedListInterface<T> list, String expectedOutput) {
		System.out.println("\nExpected output: " + expectedOutput);
		System.out.println("Actual output:   " + list.toString() +"\n");
	}
	public static <T> void testIsEmptyFull(FrontBackCappedListInterface<T> list, boolean expectedEmptyResult, boolean expectedFullResult) {
		System.out.println("\nisEmpty test: expected=" + expectedEmptyResult + " actual=" + list.isEmpty());
		if(expectedEmptyResult !=  list.isEmpty()) {
			System.out.println("*****Test failed for isEmpty on list: " + list);
		}

		System.out.println("isFull test:  expected=" + expectedFullResult + " actual=" + list.isFull());
		if(expectedFullResult !=  list.isFull()) {
			System.out.println("*****Test failed for isFull on list: " + list);
		}
	}
	public static <T> void testSize(FrontBackCappedListInterface<T> list, int expectedSize) {
		System.out.println("\nSize test: expected=" + expectedSize + " actual=" + list.size() );
		if(expectedSize !=  list.size()) {
			System.out.println("*****Test failed for size on list: " + list);
		}
	}
	public static <T> void clearAndRefillTheList(FrontBackCappedListInterface<T> list, T[] valuesToAdd) {
		list.clear();
		for(T value : valuesToAdd) {
			list.addBack(value);
		}
		System.out.println("\nList cleared and refilled: " + list);
	}
	public static <T> void testAdd(FrontBackCappedListInterface<T> list, AddRemovePosition positionToAdd, T[] valuesToAdd, boolean expectedResult, String testDescription) {
		System.out.println("\nTrying to add " + Arrays.toString(valuesToAdd) + " to " + positionToAdd + " of list");
		System.out.println("List before adding: " + list);

		int beforeSize = list.size();
		for(T value : valuesToAdd) {
			boolean actualResult;
			if(positionToAdd==AddRemovePosition.FRONT) { 	
				actualResult = list.addFront(value);
			} else { // positionToAdd==Position.BACK
				actualResult = list.addBack(value);
			}
			if(actualResult!=expectedResult) {
				System.out.println("*****Test failed when adding " + value + "; test:" + testDescription);
				System.out.println("     Expected result=" + expectedResult + "\tActual result=" + actualResult);
			}
		}
		if(expectedResult==false && beforeSize==list.size()) {
			System.out.println("Add was unsuccessful, as expected.");
		}
		System.out.println("List after adding:  " + list);
		if(expectedResult==true) {
			int afterSize = list.size();
			int expectedAfterSize = beforeSize+valuesToAdd.length;
			if(expectedAfterSize != afterSize) {
				System.out.println("*****Test failed when adding " + Arrays.toString(valuesToAdd) + "; test:" + testDescription);
				System.out.println("     Expected after size=" + expectedAfterSize + "\tActual after size=" + afterSize);
			}
		}


	}
	public static <T> void testContains(FrontBackCappedListInterface<T> list, T element, boolean expectedResult, String testDescription) {
		boolean actualResult = list.contains(element);
		System.out.println("\nTarget = " + element + " in List: " + list);
		System.out.println("Expected result=" + expectedResult);
		System.out.println("Actual result=  " + actualResult);
		if(expectedResult!=actualResult) {
			System.out.println("*****Test failed when checking if list contained " + element + "; test:" + testDescription);
		}
	}
	public static <T> void testIndexOf(FrontBackCappedListInterface<T> list, IndexPosition indexPosition, T element, String testDescription) {
		testIndexOf(list, indexPosition, element, -1, testDescription);
	}
	public static <T> void testIndexOf(FrontBackCappedListInterface<T> list, IndexPosition indexPosition, T element, int expectedResult, String testDescription) {
		int actualResult;
		if(indexPosition==IndexPosition.FIRST) {
			actualResult = list.indexOf(element);
		} else { // position==IndexPosition.LAST
			actualResult = list.lastIndexOf(element);
		}
		System.out.println("\nTarget = " + element + " in List: " + list);
		System.out.println("Expected " + indexPosition + " index result = " + expectedResult);
		System.out.println("Actual   " + indexPosition + " index result = " + actualResult);

		if(expectedResult<0 && actualResult>=0) {
			System.out.println("*****Test failed when finding the index of an element not in the list; test:" + testDescription);
			System.out.println("     Result should indicate the element is not on the list.");
		} else if(expectedResult!=actualResult) {
			System.out.println("*****Test failed when finding the index; test:" + testDescription);
		}
	}
	public static <T> void testGetEntry(FrontBackCappedListInterface<T> list, int position, String testDescription) {
		testGetEntry(list, position, null, testDescription);
	}
	public static <T> void testGetEntry(FrontBackCappedListInterface<T> list, int position, T expectedResult, String testDescription) {
		T actualResult = list.getEntry(position);

		System.out.println("\nTarget index = " + position + " in List: " + list);
		System.out.println("Expected element at index " + position + " = " + expectedResult);
		System.out.println("Actual   element at index " + position + " = " + actualResult);

		if(expectedResult==null && actualResult!=null) {
			System.out.println("*****Test failed when using an invalid position; test:" + testDescription);
		} else if(expectedResult!=null && !expectedResult.equals(actualResult)) {
			System.out.println("*****Test failed to get the expected element for test:" + testDescription);
		}
	}
	public static <T> void testRemove(FrontBackCappedListInterface<T> list, AddRemovePosition positionToRemove, String testDescription) {
		testRemove(list, positionToRemove, null, testDescription);
	}
	public static <T> void testRemove(FrontBackCappedListInterface<T> list, AddRemovePosition positionToRemove, T expectedResult, String testDescription) {
		System.out.println("\nTrying to remove from the " + positionToRemove + " of list");
		System.out.println("List before removing: " + list);

		int beforeSize = list.size();

		T actualResult;
		if(positionToRemove==AddRemovePosition.FRONT) { 	
			actualResult = list.removeFront();		
		} else { // positionToRemove==Position.BACK
			actualResult = list.removeBack();
		}
	
		System.out.println("List after  removing: " + list);
		int expectedAfterSize = 0, actualAfterSize = 0;
		if(expectedResult!=null) {
			actualAfterSize = list.size();
			expectedAfterSize = beforeSize-1;
			if(expectedAfterSize != actualAfterSize) {
				System.out.println("*****Test failed when removing from " + positionToRemove + "; test:" + testDescription);
				System.out.println("     Expected after size=" + expectedAfterSize + "\tActual after size=" + actualAfterSize);
			}
		}
		if(expectedResult==null && actualResult!=null) {
			System.out.println("*****Test failed when removing from " + positionToRemove + "; test:" + testDescription);
			System.out.println("     Expected result=" + expectedResult + "\tActual result=" + actualResult);	
		} else if(expectedResult!=null && !expectedResult.equals(actualResult)) {
			System.out.println("*****Test failed when removing from " + positionToRemove + "; test:" + testDescription);
			System.out.println("     Expected after size=" + expectedAfterSize + "\tActual after size=" + actualAfterSize);
		}
	}
	public static enum AddRemovePosition {
		FRONT, BACK;
		
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	public static enum IndexPosition {
		FIRST, LAST;
		
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
}
