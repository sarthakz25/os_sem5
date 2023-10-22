import java.util.*;

public class Exp10 {

    static int size = 8;
    static int disk_size = 200;

    public static void CSCAN(int arr[], int head) {
        int seek_count = 0;
        int distance, cur_track;

        Vector<Integer> left = new Vector<Integer>();
        Vector<Integer> right = new Vector<Integer>();
        Vector<Integer> seek_sequence = new Vector<Integer>();

        // appending end values which has to be visited before reversing
        left.add(0);
        right.add(disk_size - 1);

        // tracks on the left of head will be serviced when once head comes back to
        // beginning i.e. left end.
        for (int i = 0; i < size; i++) {
            if (arr[i] < head)
                left.add(arr[i]);
            if (arr[i] > head)
                right.add(arr[i]);
        }

        // sorting left and right vectors
        Collections.sort(left);
        Collections.sort(right);

        // to first service the requests on right side of the head.
        for (int i = 0; i < right.size(); i++) {
            cur_track = right.get(i);

            // append current track to seek sequence
            seek_sequence.add(cur_track);

            // to calculate absolute distance
            distance = Math.abs(cur_track - head);

            // increase total count
            seek_count += distance;

            // accessed track is now new head
            head = cur_track;
        }

        // once reached right end jump to beginning.
        head = 0;

        // adding seek count for head returning
        seek_count += (disk_size - 1);

        // to service the requests again which are left.
        for (int i = 0; i < left.size(); i++) {
            cur_track = left.get(i);

            // appending current track to seek sequence
            seek_sequence.add(cur_track);

            // to calculate absolute distance
            distance = Math.abs(cur_track - head);

            // increase the total count
            seek_count += distance;

            // accessed track is now the new head
            head = cur_track;
        }

        System.out.println("Total number of seek "
                + "operations = " + seek_count);

        System.out.println("Seek Sequence is");

        for (int i = 0; i < seek_sequence.size(); i++) {
            System.out.println(seek_sequence.get(i));
        }
    }

    public static void main(String[] args) throws Exception {

        // request array
        int arr[] = { 176, 79, 34, 60, 92, 11, 41, 114 };
        int head = 50;

        System.out.println("initial position of head: "
                + head);

        CSCAN(arr, head);
    }
}
