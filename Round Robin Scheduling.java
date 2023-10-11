import java.util.*;

public class Exp6 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n, i, qt, count = 0, temp, sq = 0, bt[], wt[], tat[], rem_bt[];
        float awt = 0, atat = 0;

        bt = new int[10];
        wt = new int[10];
        tat = new int[10];
        rem_bt = new int[10];

        System.out.print("enter number of process (max 10): ");
        n = sc.nextInt();
        
        System.out.print("enter burst time of the process ~\n");
        for (i = 0; i < n; i++) {
            System.out.print("P" + i + " = ");
            bt[i] = sc.nextInt();
            rem_bt[i] = bt[i];
        }

        System.out.print("enter the quantum time: ");
        qt = sc.nextInt();

        while (true) {
            for (i = 0, count = 0; i < n; i++) {
                temp = qt;
                if (rem_bt[i] == 0) {
                    count++;
                    continue;
                }
                if (rem_bt[i] > qt)
                    rem_bt[i] = rem_bt[i] - qt;
                else if (rem_bt[i] >= 0) {
                    temp = rem_bt[i];
                    rem_bt[i] = 0;
                }
                sq = sq + temp;
                tat[i] = sq;
            }
            if (n == count)
                break;
        }

        System.out.print("---------------------------------------------------------------------");
        System.out.print("\nProcess\t     Burst Time\t     Turnaround Time\t     Waiting Time\n");
        System.out.print("---------------------------------------------------------------------");

        for (i = 0; i < n; i++) {
            wt[i] = tat[i] - bt[i];
            awt = awt + wt[i];
            atat = atat + tat[i];
            System.out.print("\n " + (i + 1) + "\t\t " + bt[i] + "\t\t " + tat[i] + "\t\t " + wt[i] + "\n");
        }

        awt = awt / n;
        atat = atat / n;

        System.out.println("\nAverage waiting Time = " + awt);
        System.out.println("Average turnaround time = " + atat);
        sc.close();
    }
}