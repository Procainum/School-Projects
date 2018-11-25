import java.util.EmptyStackException;

public final class LinkedStack<T> implements StackInterface<T>
{
    private int  totalEntries = 0;
	private Node topNode;

	public LinkedStack()
	{
		topNode = null;
	}

	public void push(T newEntry)
	{
      topNode = new Node(newEntry, topNode);
      totalEntries++;
	}

	public T peek()
	{
		if (isEmpty())
			throw new EmptyStackException();
		else
         return topNode.item;
	}

	public T pop()
	{
	   T top = peek();  // Might throw EmptyStackException
        topNode = topNode.next;
        totalEntries--;
	   return top;
	}

	public boolean isEmpty()
	{
	    return topNode == null;
	}

	public void clear()
	{
		topNode = null;
		totalEntries = 0;
	}

	private class Node
	{
      private T item;
      private Node next;

      private Node(T item)
      {
         this(item, null);
      }

      private Node(T itemPortion, Node linkPortion)
      {
         item = itemPortion;
         next = linkPortion;
      }
	}
}
