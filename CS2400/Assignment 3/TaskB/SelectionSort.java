//Sebastian Vivanco
//CS240
//Assignment 3
//18 May 18

public class SelectionSort
{
    // SELECTION SORT
    /** Sorts the first n objects in an array into ascending order.
     @param a  An array of Comparable objects.
     @param n  An integer > 0. */
    public static <T extends Comparable<? super T>> void selectionSort(T[] a, int n)
    {
        for (int index = 0; index < n - 1; index++)
        {
            int indexOfNextLargest = getIndexOfLargest(a, index, n - 1);
            swap(a, index, indexOfNextLargest);
        } // end for
    } // end selectionSort

    private static <T extends Comparable<? super T>>
    int getIndexOfLargest(T[] a, int first, int last)
    {
        T max = a[first];
        int indexOfMax = first;
        for (int index = first + 1; index <= last; index++)
        {
            if (a[index].compareTo(max) > 0)
            {
                max = a[index];
                indexOfMax = index;
            } // end if
        } // end for

        return indexOfMax;
    } // end getIndexOfLargest

    private static void swap(Object[] a, int i, int j)
    {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    } // end swap
}
