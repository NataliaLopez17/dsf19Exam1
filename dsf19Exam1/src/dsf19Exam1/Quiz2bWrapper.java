package dsf19Exam1;
public class Quiz2bWrapper {
	
	/*Consider a member method equalSet, which determines if  
	 * another set S is equal to this set. Recall that two sets are equal, 
	 * if and only if they have the same elements. Implement method equalSet. 
	 * If the two sets are emtpy, then they are equal. 
	 * For example, is S1 = {Joe, Ned, Tom}, S2 = {Joe, Ned}, 
	 * and S3 = {Joe, Tom, Ned}, them S1.equalSet(S2) is false, 
	 * but S1.equalSet(S3) is true. 
	 * Remember: Sets are unordered.
	 */
	
	public static interface Set<E> {
	    public int size();
	    
	    public boolean isEmpty();
	    
	    public void add(E e);
	    
	    public boolean isMember(E e);
	    
	    public boolean remove(E e);
	            
	    public void clear();
	    
	    public boolean isSubset(Set<E> S);
	    
	    public Set<E> union(Set<E> S);
	    
	    public Set<E> difference(Set<E> S);

	    public Set<E> intersection(Set<E> S);

	    public E[] toArray();
	    
	    public boolean equalSet(Set<E> S);
	    
	}

	@SuppressWarnings("unchecked")
	public static class ArraySet<E> implements Set<E> {
		private E[] elements;
		private int currentSize;
		private static final int DEFAULT_SIZE = 10;


		public ArraySet(int initialSize) {
			if (initialSize < 1) {
				throw new IllegalArgumentException("Size must be at least 1.");
			}
			this.elements = (E[]) new Object[initialSize];
			this.currentSize = 0;
		}

		public ArraySet() {
			this(DEFAULT_SIZE);
		}

		@Override
		public int size() {
			return this.currentSize;
		}

		@Override
		public boolean isEmpty() {
			return this.size() == 0;
		}

		@Override
		public void add(E e) {
			if (!this.isMember(e)) {
				if (this.size() == this.elements.length) {
					reAllocate();
				}
				this.elements[this.currentSize++] = e;
			}

		}

		private void reAllocate() {
	        E temp[] = (E[]) new Object[2*this.size()];
	        for (int i=0; i < this.size(); ++i) {
	                temp[i] = this.elements[i];
	        }
	        this.elements = temp;
			
		}

		@Override
		public boolean isMember(E e) {
			for (int i=0; i < this.size(); ++i) {
				if (this.elements[i].equals(e)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean remove(E e) {
			for (int i=0; i < this.size(); ++i) {
				if (this.elements[i].equals(e)) {
					this.elements[i] = this.elements[this.currentSize-1];
					this.elements[this.currentSize-1] = null;
					this.currentSize--;
					return true;
				}
			}
			return false;

		}

		@Override
		public void clear() {
	        for (int i=0; i < this.size(); ++i) {
	            this.elements[i] = null;
	        }
	        this.currentSize = 0; 
		}

		@Override
		public boolean isSubset(Set<E> S) {
			for (int i=0; i < this.size(); ++i) {
				if (!S.isMember(this.elements[i])) {
					return false;
				}
			}
			return true;
		}

		@Override
		public Set<E> union(Set<E> S) {

			Set<E> result = new ArraySet<E>(this.size() + S.size());
			for (int i=0; i < this.size(); ++i) {
				result.add(this.elements[i]);
			}
			E[] temp = S.toArray();
			for (int i=0; i < S.size(); ++i) {
				if (!result.isMember(temp[i])) {
					result.add(temp[i]);
				}
			}
			return result;
		}

		@Override
		public Set<E> difference(Set<E> S) {

			Set<E> result = new ArraySet<E>();
			for (int i=0; i < this.size(); ++i) {
				if (!S.isMember(this.elements[i])) {
					result.add(this.elements[i]);
				}
			}
			return result;
			

		}

		@Override
		public Set<E> intersection(Set<E> S) {
			return this.difference(this.difference(S));
		}

		@Override
		public E[] toArray() {
			E result[] = (E[]) new Object[this.size()];
			
			for (int i=0; i < this.size(); ++i) {
				result[i]  = this.elements[i];
			}
			
			return result;

		}

		@Override
		public boolean equalSet(Set<E> S) {
			return false;
			// ADD YOUR CODE HERE
		}
	}
}