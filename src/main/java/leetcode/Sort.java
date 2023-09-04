package leetcode;

/**
 * Created by ShaoBin on 2016/3/15.
 */
public class Sort {
    public void display(int []arrays) {
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + "    ");
        }
        System.out.println();
    }

    public void bubbleSort(int[] arrays) {                     //ð������
        for (int i = 0; i < arrays.length - 1; i++) {
            for (int j = 0; j < arrays.length - i - 1; j++) {
                if (arrays[j] > arrays[j + 1]) {
                    int t = arrays[j];
                    arrays[j] = arrays[j + 1];
                    arrays[j + 1] = t;
                }
            }
        }
    }

    public void selectSort(int []arrays) {              //ѡ������
        int min = 0;
        for (int i = 0; i < arrays.length; i++) {
            min = i;
            for (int j = i; j < arrays.length; j++) {
                if (arrays[j] <= arrays[min]) {
                    min = j;
                }
            }
            int t = arrays[i];
            arrays[i] = arrays[min];
            arrays[min] = t;
        }
    }

    public void insertionSort(int []arrays) {                //��������
        for (int i = 1; i < arrays.length; i++) {
            int j = i;
            int temp = arrays[i];
            while (j > 0 && arrays[j - 1] >= temp) {
                arrays[j] = arrays[j - 1];
                j--;
            }
            arrays[j] = temp;
        }
    }

    public void shellSort(int []arrays) {                    //ϣ������
        int i,j;
        int temp;
        int h = 1;
        while (h <= arrays.length/3)
            h = h * 3 + 1;
        while (h > 0) {
            for (i = h; i < arrays.length; i++) {
                temp = arrays[i];
                j = i;
                while (j > h - 1 && arrays[j - h] >= temp) {
                    arrays[j] = arrays[ j - h];
                    j -= h;
                }
                arrays[j] = temp;
            }
            h = (h - 1) / 3;
        }
    }

    private void swap(int []arrays, int i, int j) {                            //��������
        int t = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = t;
    }

    private void subsort(int []arrays, int start, int end) {                    //�����Ӻ���
        if (start < end) {
            int base = arrays[start];
            int i = start;
            int j = end + 1;
            while (true) {
                while (i < end && arrays[++i] <= base);
                while (j > start && arrays[--j] >= base);
                if (i < j) {
                    swap(arrays, i, j);
                }
                else {
                    break;
                }
            }
            swap(arrays, start, j);
            subsort(arrays, start, j);
            subsort(arrays, j + 1, end);
        }
    }

    public void quickSort(int []arrays) {                          //��������
        subsort(arrays, 0, arrays.length - 1);
    }

    public void binaryinsertSort(int []arrays) {                 //�۰��������
        for (int i = 1; i < arrays.length; i++) {
            int temp = arrays[i];
            int low = 0;
            int high = i - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (temp > arrays[mid]) {
                    low = mid;
                }
                else {
                    high = mid;
                }
            }
            for (int j = 0; j > low; j--){
                arrays[j] = arrays[j - 1];
            }
            arrays[low] = temp;
        }
    }

    private void merge(int []arrays, int left, int center, int right) {       //�鲢
        int []a = new int[arrays.length];
        int i = left;
        int j = center + 1;
        int k = 0;
        while (i <= center && j <= right) {
            if (arrays[i] < arrays[j]) {
                a[k++] = arrays[i];
            }
            else {
                a[k++] = arrays[j];
            }
        }
        while (i < center) {
            a[k++] = arrays[i++];
        }
        while (j <= right) {
            a[k++] = arrays[j++];
        }
        for (int ii = 0; ii < arrays.length; ii++)
            arrays[i] = a[i];
    }

    private void sort(int []arrays,int start,int end) {             //����
        int mid = (start + end) / 2;
        sort(arrays, start, mid);
        sort(arrays, mid + 1, end);
        merge(arrays, start, mid, end);
    }

    public void mergeSort(int []arrays) {                    //�鲢����
        sort(arrays, 0, arrays.length);
    }

    public static void main(String []args) {
        Sort sort = new Sort();
        int []a0 = {5,9,7,3,0,-1,8,10,2,1,1,0,4,6};
        int []a1 = {5,9,7,3,0,-1,8,10,2,1,1,0,4,6};
        int []a2 = {5,9,7,3,0,-1,8,10,2,1,1,0,4,6};
        int []a3 = {5,9,7,3,0,-1,8,10,2,1,1,0,4,6};
        int []a4 = {5,9,7,3,0,-1,8,10,2,1,1,0,4,6};
        int []a5 = {5,9,7,3,0,-1,8,10,2,1,1,0,4,6};
        int []a6 = {5,9,7,3,0,-1,8,10,2,1,1,0,4,6};
        int []a7 = {5,9,7,3,0,-1,8,10,2,1,1,0,4,6};
        System.out.println("BubbleSort:");
        sort.bubbleSort(a1);
        sort.display(a1);
        System.out.println("SelectSort:");
        sort.selectSort(a2);
        sort.display(a2);
        System.out.println("InsertionSort:");
        sort.insertionSort(a3);
        sort.display(a3);
        System.out.println("ShellSort:");
        sort.shellSort(a4);
        sort.display(a4);
        System.out.println("QuickSort:");
        sort.quickSort(a5);
        sort.display(a5);
        System.out.println("BinaryInsertionSort:");
        sort.binaryinsertSort(a6);
        sort.display(a6);
        System.out.println("MergeSort:");
        sort.mergeSort(a7);
        sort.display(a7);
    }
}
