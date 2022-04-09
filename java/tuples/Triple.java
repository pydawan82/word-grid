package tuples;

/**
 * A tuple of 3 elements.
 * @param <A> The type of the first item.
 * @param <B> The type of the second item.
 * @param <C> The type of the third item.
 */
public class Triple<A, B, C> {
	
	public A first;
	public B second;
	public C third;
	
	public Triple(A first, B second, C third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	@Override
	public String toString() {
		return String.format("[%s, %s, %s]", first, second, third);
	}
}
