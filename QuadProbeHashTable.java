package assignment10;

import java.util.Collection;

public class QuadProbeHashTable implements Set<String> {

	private int size;
	private int collision;
	private String[] hashtable;
	private int capacity;
	private HashFunctor functor;

	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		this.capacity = isPrime(capacity) ? capacity : nextPrime(capacity - 1);
		hashtable = new String[this.capacity];
		this.functor = functor;
		size = 0;
		collision = 0;
	}

	private int nextPrime(int number) {
		int prime = number % 2 == 0 ? number : number - 1;
		boolean isPrime = false;
		while (!isPrime) {
			prime += 2;
			isPrime = true;
			for (int i = 3; i < Math.sqrt(number); i += 2) {
				if (number % i == 0) {
					isPrime = false;
					break;
				}
			}
		}
		return prime;
	}

	private boolean isPrime(int number) {
		for (int i = 2; i < Math.sqrt(number); i = nextPrime(i)) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

	@Override
	public boolean add(String item) {
		if (item == null)
			return false;
		
		
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(String item) {
		int hashposition = functor.hash(item) % capacity;
		int startPosition = hashposition;
		
		int i = 1;
		while (hashtable[hashposition] != null) {
			if (hashtable[hashposition].equals(item))
				return true;
			hashposition = startPosition + (int) Math.pow(i, 2) % capacity;
			i++;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean containsAll = true;
		for (String item : items)
			if (!contains(item))
				containsAll = false;
		return containsAll;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

}
