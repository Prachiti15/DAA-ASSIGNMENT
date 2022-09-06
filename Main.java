import java.util.*;

class Main {

  public static int findLastNonConflictingactivity(
    List<activity> activitys,
    int n
  ) {
    int low = 0;
    int high = n;

    while (low <= high) {
      int mid = (low + high) / 2;
      if (activitys.get(mid).finish <= activitys.get(n).start) {
        if (activitys.get(mid + 1).finish <= activitys.get(n).start) {
          low = mid + 1;
        } else {
          return mid;
        }
      } else {
        high = mid - 1;
      }
    }

    return -1;
  }

  public static void findMaxdifferenceactivitys(List<activity> activitys) {
    Collections.sort(activitys, Comparator.comparingInt(x -> x.finish));

    int n = activitys.size();

    if (n == 0) {
      return;
    }

    int[] maxdifference = new int[n];

    List<List<Integer>> tasks = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      tasks.add(new ArrayList<>());
    }

    maxdifference[0] = activitys.get(0).difference;
    tasks.get(0).add(0);

    for (int i = 1; i < n; i++) {
      int index = findLastNonConflictingactivity(activitys, i);

      int currentdifference = activitys.get(i).difference;
      if (index != -1) {
        currentdifference += maxdifference[index];
      }

      if (maxdifference[i - 1] < currentdifference) {
        maxdifference[i] = currentdifference;

        if (index != -1) {
          tasks.set(i, new ArrayList<>(tasks.get(index)));
        }
        tasks.get(i).add(i);
      } else {
        tasks.set(i, new ArrayList<>(tasks.get(i - 1)));
        maxdifference[i] = maxdifference[i - 1];
      }
    }
    System.out.println(
      "The maximum resource utilization is " + maxdifference[n - 1]
    );
    System.out.print(
      "The activitys involved in the maximum resource utilization are "
    );
    for (int i : tasks.get(n - 1)) {
      System.out.print("A"+(i + 1) + ">>");
    }
  }

  public static void main(String[] args) {
    List<activity> activitys = Arrays.asList(
      new activity(1, 3),
      new activity(2, 5),
      new activity(4, 7),
      new activity(1, 8),
      new activity(5, 9),
      new activity(8, 10),
      new activity(9, 11),
      new activity(11, 14),
      new activity(13, 16)
    );

    findMaxdifferenceactivitys(activitys);
  }
}
