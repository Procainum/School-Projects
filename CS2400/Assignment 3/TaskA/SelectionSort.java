//Sebastian Vivanco
//CS240
//Assignment 3
//18 May 18

public class SelectionSort
{

    public static <T extends Comparable<? super T>> void selectionSort(T[] a, int n)
    {
        for (int index = 0; index < n - 1; index++)
        {
            int indexOfNextSmallest = getIndexOfSmallest(a, index, n - 1);
            swap(a, index, indexOfNextSmallest);
            display(a);
        } // end for
    } // end selectionSort

    private static <T extends Comparable<? super T>>
    int getIndexOfSmallest(T[] a, int first, int last)
    {
        T min = a[first];
        int indexOfMin = first;
        for (int index = first + 1; index <= last; index++)
        {
            if (a[index].compareTo(min) < 0)
            {
                min = a[index];
                indexOfMin = index;
            } // end if
        } // end for

        return indexOfMin;
    } // end getIndexOfSmallest

    private static void swap(Object[] a, int i, int j)
    {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    } // end swap

    private static void display(Object[] input) {

        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ", ");
        }
        System.out.println("\n");
    }

}
