`With reference to AY20/21 Sem 2 CS2040S Lecture 5 - 7: Sorting`

From the usage of Binary Search, one thing that is important to take note of is that without a sorted set of data, we can say bye-bye to that **sexy** O(log n) when it comes to a common array... (─  ‿  ─) 

Therefore, we must find ways to get our data **sorted** and in the **quickest** way possible... leading into today's topic — SORTING!

---

## Lecture Flow Summary

In CS2040S AY20/21, the topic of Sorting is split into 3 sections. The first focuses on the basic sorting techniques that carry the time complexity of O(n^2), with some introduction to merge sort. The second will continue on merge sort and then step into the field of quicksort, leaving the third with some applied augmentations of quicksort. No matter how are the lectures structured, they all are built with the general idea focusing on — how to become faster!

---

### BubbleSort

`If two adjacent elements are in the wrong order, then we swap their position. We repeat this step until all are in order.`
 
Visualisation? Go to [visualgo](https://visualgo.net/en/sorting "Visualgo - Bubble Sort")!

    //Java
    void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

As the above algorithm will always run in O(n^2) time complexity due to nested for-loop (even if the array is already sorted), the algorithm can be further optimized by terminating the algorithm if the inner for-loop does not result in any swap.

    // Java
    void bubbleSort(int arr[], int n)
    {
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++)
        {
            swapped = false;
            for (j = 0; j < n - i - 1; j++)
            {
                if (arr[j] > arr[j + 1])
                {
                    // swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
 
            // IF no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }
> 
> Property:
> - Worst (Reversely Sorted Array) & Average Run-Time: O(n^2).
> - Best Run-Time (Already Sorted): O(n).
> - Auxiliary Space: O(1).
> - Loop Invariant: At the end of iteration **i**, the biggest **i** items are correctly sorted in the final **i** positions of the array.
> - Correctness: Array is sorted after **n** iterations of outer for-loop.
> - Stability: **Stable**, able to preserve the order of equal elements (Only different elements are swapped).

---

### Selection Sort

`Find the next smallest element. Put it at the back of sorted array. Repeat.`

Visualisation? Go to [visualgo](https://visualgo.net/en/sorting "Visualgo - Selection Sort")!

In selection sort, we maintain the given array into 2 parts:
> 1. The subarray which is already sorted. 
> 2. Remaining subarray which is unsorted.

To sort in an ascending order, we repeatedly find the minimum element from the unsorted subarray and we put at the back of the sorted array.

    // Java
    void SelectionSort(int arr[])
    {
        int n = arr.length;
 
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;
 
            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
> 
> Property:
> - Time Complexity: Always O(n^2)
> - Loop invariant: At the end of iteration **i**: the smallest **i** items are correctly sorted in the first **i** positions of the array.
> - Auxiliary Space: O(1)
> - Stability: **Not Stable**, as order maybe altered.
![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/uwi74vwb4uf449otsgur.png)
![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/41k2zt66ooq6ys97rtpn.png)

---

### Insertion Sort

`One element at a time, look for a place to insert it.`

Visualisation? Go to [visualgo](https://visualgo.net/en/sorting "Visualgo - Insertion Sort")!

Similarly, we maintain the given array in 2 subarray:
> 1. The subarray which is already sorted. 
> 2. Remaining subarray which is unsorted.

To sort in ascending order: 
> 1. Iterate from arr[1] to arr[n] over the array (Unsorted Portion). 
> 2. Compare the current element (key) from the Unsorted Portion to its predecessor (in Sorted Portion). 
> 3. If the key element is smaller than its predecessor, compare it to the elements before. Move the greater elements one position up to make space for the swapped element.

    // Java
    void InsectionSort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
 
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
> 
> Property:
> - Worst (Reversely Sorted) & Average Time Complexity: O(n^2).
> - Best Case (Already Sorted) Time Complexity: O(n).
> - Auxiliary Space: O(1).
> - Loop invariant: At the end of iteration **i**: the first **i** items in the array are in sorted order.
> - Stability: Stable. For equal elements, we only insert to the back of that equal element.
> - Uses: Insertion sort is used when number of elements is small. It can also be useful when input array is almost sorted, only few elements are misplaced in complete big array.

---

### Merge Sort

`Divide & Conquer`

Visualisation? Go to [visualgo](https://visualgo.net/en/sorting "Visualgo - Insertion Sort")!

> 1. Divide: split array into two halves.
> 2. Recurse: sort the two halves.
> 3. Combine: merge the two sorted halves.

    // Algo
    MergeSort(arr[], l,  r)
    If r > l
      1. Find the middle point to divide the array into two halves:  
             middle m = l+ (r-l)/2
      2. Call mergeSort for first half:    -> T(n/2)
             Call mergeSort(arr, l, m)
      3. Call mergeSort for second half:    -> T(n/2)
             Call mergeSort(arr, m+1, r)
      4. Merge the two halves sorted in step 2 and 3:    -> θ(n)
             Call merge(arr, l, m, r)
             
---

    // Java Implementation
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    void MergeSort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m =l+ (r-l)/2;
 
            // Sort first and second halves
            MergeSort(arr, l, m);
            MergeSort(arr, m + 1, r);
 
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
> 
> Analysis:
> - Merge() RunTime: cn = θ(n), where c is an arbitary constant
>
>> 1. In each iteration, move one element to final list.
>> 2. After n iterations, all the items are in the final list.
>> 3. Each iteration takes θ(1) time to compare two elements and copy one.
>
> - recurrence RunTime: 
> T(n) = 2T(n/2) + θ(n) 
> = θ(RunTime of Merge() * no of level) 
> = θ(n Log n)
> ![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/igmgo5vmmmj9ah1ta6wd.png)

---

> Property:
> - Time Complexity of Merge Sort: Always **θ(n Log n)** as MergeSort always divides the array into two halves and takes linear time to merge two halves.
> - Space Complexity:
> S(n) 
> = 2S(n/2) + n
> = θ(n Log n)
> ![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/fls7ekjurnj0uzn54fp2.png)
> - Stability: Stable, as long as merge() is stable.

---

### Summary:
![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/q7we7dl2qamzumbape31.png)
