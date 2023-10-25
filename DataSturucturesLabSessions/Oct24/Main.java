package Oct24;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] arr1 = generateRandomArray(100);
        int[] arr2 = generateRandomArray(1000);
        int[] arr3 = generateRandomArray(10000);
        int[] arr4 = generateRandomArray(100000);

        //Bubble Sort
        System.out.println("Bubble Sort");
        System.out.println("Array Size: 100 - Time: " + timeCalculator(Arrays.copyOf(arr1, arr1.length), 1) + " milliseconds");
        System.out.println("Array Size: 1.000 - Time: " + timeCalculator(Arrays.copyOf(arr2, arr2.length), 1) + " milliseconds");
        System.out.println("Array Size: 10.000 - Time: " + timeCalculator(Arrays.copyOf(arr3, arr3.length), 1) + " milliseconds");
        System.out.println("Array Size: 100.000 - Time: " + timeCalculator(Arrays.copyOf(arr4, arr4.length), 1) + " milliseconds");
        System.out.println();

        //Selection Sort
        System.out.println("Selection Sort");
        System.out.println("Array Size: 100 - Time: " + timeCalculator(Arrays.copyOf(arr1, arr1.length), 2) + " milliseconds");
        System.out.println("Array Size: 1.000 - Time: " + timeCalculator(Arrays.copyOf(arr2, arr2.length), 2) + " milliseconds");
        System.out.println("Array Size: 10.000 - Time: " + timeCalculator(Arrays.copyOf(arr3, arr3.length), 2) + " milliseconds");
        System.out.println("Array Size: 100.000 - Time: " + timeCalculator(Arrays.copyOf(arr4, arr4.length), 2) + " milliseconds");
        System.out.println();

        //Merge Sort
        System.out.println("Merge Sort");
        System.out.println("Array Size: 100 - Time: " + timeCalculator(Arrays.copyOf(arr1, arr1.length), 3) + " milliseconds");
        System.out.println("Array Size: 1.000 - Time: " + timeCalculator(Arrays.copyOf(arr2, arr2.length), 3) + " milliseconds");
        System.out.println("Array Size: 10.000 - Time: " + timeCalculator(Arrays.copyOf(arr3, arr3.length), 3) + " milliseconds");
        System.out.println("Array Size: 100.000 - Time: " + timeCalculator(Arrays.copyOf(arr4, arr4.length), 3) + " milliseconds");
        System.out.println();
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) { // n-1 passes
            for (int j = 0; j < n - i - 1; j++) { // n-i-1 comparisons
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j] and arr[j+1]
                    int temp = arr[j]; // temp = 5
                    arr[j] = arr[j + 1]; // arr[j] = 3
                    arr[j + 1] = temp; // arr[j+1] = 5
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) { // n-1 passes
            int minIndex = i;
            for (int j = i + 1; j < n; j++) { // n-i-1 comparisons
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // swap arr[i] and arr[minIndex]
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            // find the middle point
            int m = (l + r) / 2;

            // sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        // find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // copy data to temp arrays
        System.arraycopy(arr, l, L, 0, n1);
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        // merge the temp arrays
        // initial indexes of first and second subarrays
        int i = 0, j = 0;

        // initial index of merged subarray
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) { // <= for stable sort
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++; // increment index of merged subarray
        }

        // copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            // generate a random number between 0 and 999
            arr[i] = (int) (Math.random() * 1000);
        }
        return arr;
    }

    /**
     * This method prints the calculation time of the sorting algorithms.
     * 1 - Bubble Sort.
     * 2 - Selection Sort.
     * 3 - Merge Sort.
     *
     * @param arr array
     * @param n   number of sorting algorithm
     */
    public static long timeCalculator(int[] arr, int n) {
        long startTime, endTime, duration;

        //switch case
        switch (n) {
            case 1:
                startTime = System.currentTimeMillis();
                bubbleSort(arr);
                endTime = System.currentTimeMillis();
                duration = endTime - startTime;
                return duration;
            case 2:
                startTime = System.currentTimeMillis();
                selectionSort(arr);
                endTime = System.currentTimeMillis();
                duration = endTime - startTime;
                return duration;
            case 3:
                startTime = System.currentTimeMillis();
                mergeSort(arr, 0, arr.length - 1);
                endTime = System.currentTimeMillis();
                duration = endTime - startTime;
                return duration;
            default:
                System.out.println("Invalid Input");
        }
        return 0;
    }

}