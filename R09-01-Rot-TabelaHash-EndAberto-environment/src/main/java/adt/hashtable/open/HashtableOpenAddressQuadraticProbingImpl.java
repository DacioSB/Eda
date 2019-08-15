package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element == null)
			return;

		int hashKey;
		for (int j = 0; j < super.capacity(); j++) {
			hashKey = this.getHash(element, j);

			if (super.table[hashKey] == null) {
				super.table[hashKey] = element;
				super.elements++;
				return;
			} else {
				if (super.table[hashKey] != null || super.table[hashKey].equals(element)) {
					super.COLLISIONS++;
				}
			}
		}
	}
	private int getHash(T element, int probe) {
		return ((HashFunctionQuadraticProbing<T>) super.getHashFunction()).hash(element, probe);
	}

	@Override
	public void remove(T element) {
		if (element == null)
			return;

		if (!super.isEmpty()) {
			int hashKey;
			for (int i = 0; i < super.capacity(); i++) {
				hashKey = this.getHash(element, i);

				if (super.table[hashKey] != null && super.table[hashKey].equals(element)) {
					super.table[hashKey] = null;
					super.elements--;
					return;
				}
			}
		}
	}

	@Override
	public T search(T element) {
		if (element == null)
			return null;

		if (!super.isEmpty()) {
			int hashKey;
			for (int i = 0; i < super.capacity(); i++) {
				hashKey = this.getHash(element, i);

				if (super.table[hashKey] != null && super.table[hashKey].equals(element))
					return element;
			}
		}
		return null;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;

		if (!super.isEmpty()) {
			int hashKey;
			for (int i = 0; i < super.capacity(); i++) {
				hashKey = this.getHash(element, i);

				if (super.table[hashKey] != null && super.table[hashKey].equals(element)) {
					index = hashKey;
					break;
				}
			}
		}
		return index;
	}
}
