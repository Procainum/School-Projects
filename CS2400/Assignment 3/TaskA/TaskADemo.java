//Sebastian Vivanco
//CS240
//Assignment 3
//18 May 18

public class TaskADemo
{
    public static void main(String[] args)
    {
        Integer[] selectionArray = {5, 7, 4, 9, 8, 5, 6, 3};

        System.out.println("A.1 - Selection Sort!");
        //display(selectionArray, selectionArray.length);
        SelectionSort.selectionSort(selectionArray, selectionArray.length);

        Integer[] insertionArray = {5, 7, 4, 9, 8, 5, 6, 3};

        System.out.println("A.2 - Insertion Sort!");
        //display(insertionArray, insertionArray.length);

        InsertionSort.insertionSort(insertionArray, insertionArray.length);

    } // end testSort

    public static void display(Object[] array, int n)
    {
        for (int index = 0; index < n; index++)
            System.out.println(array[index]);
        System.out.println();
    } // end display
}
