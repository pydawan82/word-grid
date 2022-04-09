package tuples;

/**
 * A tuple of 2 elements.
 * @param <A> The type of the first item.
 * @param <B> The type of the second item.
 */
public class Pair<A, B> {
	public A first;
	public B second;
	
	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public String toString() {
		return String.format("[%s, %s]", first, second);
	}
	
}
