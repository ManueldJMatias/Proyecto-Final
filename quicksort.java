
/**
 * Write a description of class quicksort here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class quicksort
{
        private void quickSort(int[] nums, int inicio, int fin) {
        if (inicio >= fin) {
            return;
        }

        int posI = inicio;
        int posD = fin;
        int pivote = nums[(posI + posD) / 2];

        do {
            while (nums[posI] < pivote) {
                posI++;
            }
            while (nums[posD] > pivote) {
                posD--;
            }
            if (posI <= posD) {
                intercambiar(nums, posI, posD);
                posI++;
                posD--;
            }
        } while (posI <= posD);

        if (inicio < posD) {
            quickSort(nums, inicio, posD);
        }
        if (posI < fin) {
            quickSort(nums, posI, fin);
        }
    }
    private void intercambiar(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
     public int[] quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

}
