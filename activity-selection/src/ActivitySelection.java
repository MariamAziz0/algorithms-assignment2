import java.util.*;

public class ActivitySelection {

    public static int getMaxWeightNSquared(int[][] activities) {

        HelpingFunctions.sortActivities(activities);

        int[] c = new int[activities.length];
        Arrays.fill(c, Integer.MIN_VALUE);

        return getWeightRecursive(activities, c, 0);

    }


    private static int getWeightRecursive(int[][] activities, int[] c, int startActivity) {

        if (startActivity >= activities.length)
            return 0;

        if (c[startActivity] != Integer.MIN_VALUE)
            return c[startActivity];

        int nextActivity = getNextCompatibleActivity(activities, startActivity, activities.length - 1, activities[startActivity][1]);

        if (nextActivity == -1)
            c[startActivity] = Math.max(activities[startActivity][2], getWeightRecursive(activities, c, startActivity + 1));
        else
            c[startActivity] = Math.max(getWeightRecursive(activities, c, startActivity + 1), activities[startActivity][2] + getWeightRecursive(activities, c, nextActivity));

        return c[startActivity];

    }

    private static int getNextCompatibleActivity(int[][] activities, int low, int high, int start) {

        if (low > high)
            return -1;

        if (low == high)
            if (activities[low][0] >= start)
                return low;
            else
                return -1;

        int middle = (low + high) / 2;
        if (activities[middle][0] == start)
            return middle;

        if (activities[middle][0] > start)
            return getNextCompatibleActivity(activities, low, middle, start);

        return getNextCompatibleActivity(activities, middle + 1, high, start);

    }

}
