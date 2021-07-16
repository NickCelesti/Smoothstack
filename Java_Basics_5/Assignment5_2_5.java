package Java_Basics_5;

public class Assignment5_2_5 {
    // Given an array of ints, is it possible to choose a group of some of the ints,
    // such that the group sums to the given target, with this additional
    // constraint: if there are numbers in the array that are adjacent and the
    // identical value, they must either all be chosen, or none of them chosen. For
    // example, with the array {1, 2, 2, 2, 5, 2}, either all three 2's in the
    // middle must be chosen or not, all as a group. (one loop can be used to find
    // the extent of the identical values).

    // groupSumClump(0, [2, 4, 8], 10) → true
    // groupSumClump(0, [1, 2, 4, 8, 1], 14) → true
    // groupSumClump(0, [2, 4, 4, 8], 14) → false

    public static boolean groupSumClump(int start, int[] nums, int target) {
        if (start >= nums.length)
            return target == 0;

        int localSum = 0;
        int i;

        // i will always increase by at least 1
        for (i = start; i < nums.length; i++) {
            // this clumps together all equal adjacient values
            if (nums[start] == nums[i]) {
                localSum += nums[i];
            } else {
                break;
            }
        }

        // with the clump (could be of size 1)
        if (groupSumClump(i, nums, target - localSum))
            return true;

        // without the clump
        if (groupSumClump(i, nums, target))
            return true;

        return false;
    }

    public static void main(String[] args) {
        int nums[] = { 1, 2, 2, 2, 5, 2 };

        // shouldnt be able to reach 4 since all adjacient 2's clump together to act as
        // 6
        System.out.println(groupSumClump(0, nums, 4));

        // can reach this
        System.out.println(groupSumClump(0, nums, 8));
    }
}
