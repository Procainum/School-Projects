import java.util.EmptyStackException;

public final class ArrayStack<T> implements StackInterface<T>
{
	private T[] stack;
	private int topIndex;
	private static final int DEFAULT_CAPACITY = 50;

	public ArrayStack()
	{
	    this(DEFAULT_CAPACITY);
 	}

	public ArrayStack(int initialCapacity)
	{
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
	}

	public void push(T newEntry)
	{
		stack[topIndex + 1] = newEntry;
		topIndex++;
	}

	public T peek()
	{
		if (isEmpty()) {
            throw new EmptyStackException();
        }
		else {
            return stack[topIndex];
         }
	}

	public T pop()
	{
		if (isEmpty())
			throw new EmptyStackException();
		else
		{
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
         return top;
		}
   }

   public boolean isEmpty()
	{
		return topIndex < 0;
	}

	public void clear()
	{
		while (topIndex > -1)
      	{
      		stack[topIndex] = null;
			topIndex--;
      	}
	}
}
