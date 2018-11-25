//Sebastian Vivanco
//CS240
//Assignment 3
//18 May 18

public class IterativeBubbleSort
{
    // logic to sort the elements
    public static void bubbleSort(int sortMe[])
    {
        int next;
        for (int n = sortMe.length; n >= 0; n--)
        {
            for (int i = 0; i < sortMe.length-1; i++)
            {
                next = i + 1;
                if (sortMe[i] > sortMe[next])
                {
                    swap(sortMe, i, next);
                }
            }
        }
    }
    // Swaps the array entries a[i] and a[j].
    private static void swap(int[] a, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    } // end swap

}
