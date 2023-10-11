import java.util.*;

public class SJF {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter the no of process:");
        int n = sc.nextInt();

        int pid[] = new int[n]; // process id
        int at[] = new int[n]; // arrival time
        int bt[] = new int[n]; // burst time
        int ct[] = new int[n]; // completion time
        int tat[] = new int[n]; // turn around time
        int wt[] = new int[n]; // waiting time
        int f[] = new int[n]; // flag to checks if process is completed or not

        int st = 0, tot = 0;
        float avg_wt = 0, avg_tat = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("enter process " + (i + 1) + " arrival time:");
            at[i] = sc.nextInt();
            System.out.println("enter process " + (i + 1) + " burst time:");
            bt[i] = sc.nextInt();

            pid[i] = i + 1;
            f[i] = 0;
        }

        while (true) {
            int c = n, min = Integer.MAX_VALUE;

            // loop terminates if total no of process = completed process
            if (tot == n)
                break;

            for (int i = 0; i < n; i++) {
                // process will be executed first if ith process arrival time<=system time and
                // its flag=0 and burst<min
                if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                    min = bt[i];
                    c = i;
                }
            }

            // c==n means c value cannot updated because no process arrival time<system time
            // so we increase the system time
            if (c == n)
                st++;
            else {
                ct[c] = st + bt[c];
                st += bt[c];
                tat[c] = ct[c] - at[c];
                wt[c] = tat[c] - bt[c];
                f[c] = 1;
                tot++;
            }
        }

        System.out.println("\npid  arrival  burst  complete  turn  waiting");
        for (int i = 0; i < n; i++) {
            avg_wt += wt[i];
            avg_tat += tat[i];
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        System.out.println("\naverage tat is " + (float) (avg_tat / n));
        System.out.println("average wt is " + (float) (avg_wt / n));

        sc.close();
    }
}
