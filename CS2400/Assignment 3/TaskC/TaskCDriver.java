//Sebastian Vivanco
//CS240
//Assignment 3
//18 May 18

public class TaskCDriver
{
    public static void main(String[] args)
    {
        int[] array = { 5, 7, 4, 9, 8, 5, 6, 3};
        System.out.println("Unsorted Array:\n");
        display(array);
        System.out.println("Bubble Sorted Array:\n");
        IterativeBubbleSort.bubbleSort(array);
        display(array);
    }

    private static void display(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println("\n");
    }
}
