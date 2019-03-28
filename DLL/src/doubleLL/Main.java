package doubleLL;

public class Main {
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		DoubleLL dll = new DoubleLL();
		dll.addFirst(14);
		dll.addFirst(23);
		dll.addLast(9);
		
		DoubleLL dll2 =  new DoubleLL();
		dll2.addFirst(14);
		dll2.addFirst(23);
		dll2.addFirst(9);
		
		
		System.out.println(dll.equals(dll2));
		long endTime = System.nanoTime();
		System.out.println(endTime - startTime);
	}

}
