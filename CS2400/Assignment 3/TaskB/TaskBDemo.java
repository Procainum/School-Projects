//Sebastian Vivanco
//CS240
//Assignment 3
//18 May 18

public class TaskBDemo
{
    public static void main(String[] args)
    {
        Integer[] selectionArray = {5, 7, 4, 9, 8, 5, 6, 3};

        System.out.println("B: Before Reverse Selection sort:");
        display(selectionArray, selectionArray.length);

        SelectionSort.selectionSort(selectionArray, selectionArray.length);

        System.out.println("After Reverse Selection sort:");
        display(selectionArray, selectionArray.length);

    } // end testSort

    public static void display(Object[] array, int n)
    {
        for (int index = 0; index < n; index++)
            System.out.println(array[index]);
        System.out.println();
    } // end display
}
