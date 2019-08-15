package adt.hashtable.closed;

import util.Util;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends
		AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size (or the given size, if it is already prime). 
	 * For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize,
			HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
														// the immediate prime
														// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method,
				realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number.
	 * If the given number is prime, it is returned. 
	 * You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		int primeAbove = number;

		if (primeAbove % 2 == 0)
			primeAbove++;

		while (!Util.isPrime(primeAbove)) {
			primeAbove += 1;
		}
		return primeAbove;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		if (element == null)
			return;

		else {
			int hashKey = getHash(element);

			// Caso a posicao for vazia...
			if (super.table[hashKey] == null) {
				super.table[hashKey] = new LinkedList<T>();
			}
			if (!((LinkedList<T>) super.table[hashKey]).isEmpty()) {
				super.COLLISIONS++;
			}
			((LinkedList<T>) super.table[hashKey]).add(element);
			super.elements++;
		}
	}
	private int getHash(T element) {
		return ((HashFunctionClosedAddress<T>) super.getHashFunction()).hash(element);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(T element) {
		if (element == null)
			return;
		if (this.search(element) == null)
			return;

		int hashKey = getHash(element);

		((LinkedList<T>) super.table[hashKey]).remove(element);
		super.elements--;
		if(((LinkedList<T>) super.table[hashKey]).size() <= 1) {
			super.COLLISIONS--;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		if (element != null) {
			int hashKey = getHash(element);
			
			if (super.table[hashKey] != null &&((LinkedList<T>) (super.table[hashKey])).contains(element))
				return element;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(T element) {
		int index = -1;
		if (element != null && this.search(element) != null) {
			
			int hashKey = getHash(element);
			if (super.table[hashKey] != null && ((LinkedList<T>) super.table[hashKey]).contains(element)) {
				index = hashKey;
			}
		}
		return index;
	}
	
	
//	private static void findMajority(int[] arr)  
//    { 
//        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>(); 
//  
//        for(int i = 0; i < arr.length; i++) { 
//            if (map.containsKey(arr[i])) { 
//                    int count = map.get(arr[i]) +1; 
//                    if (count > arr.length /2) { 
//                        System.out.println("Majority found :- " + arr[i]); 
//                        return; 
//                    } else
//                        map.put(arr[i], count); 
//  
//            } 
//            else
//                map.put(arr[i],1); 
//            } 
//            System.out.println(" No Majority element"); 
//    } 

}
