import java.util.*;

class Process {
    int processId;
    int burstTime;
    int arrivalTime;

    public Process(int processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
}

public class FCFS {
    // to sort

    // to calculate waiting time
    static void calculateWaitingTime(Process pro[], int n, int wt[]) {
        Arrays.sort(pro, new Comparator<Process>() {
            public int compare(Process p1, Process p2) {
                return Integer.compare(p1.arrivalTime, p2.arrivalTime);
            }
        });

        wt[0] = 0;

        for (int i = 1; i < n; i++) {
            wt[i] = pro[i - 1].burstTime + wt[i - 1] - pro[i].arrivalTime;

            if (wt[i] < 0) {
                wt[i] = 0;
            }
        }
    }

    // to calculate turn around time
    static void calculateTurnAroundTime(Process pro[], int n, int wt[], int tat[]) {
        for (int i = 0; i < n; i++) {
            tat[i] = pro[i].burstTime + wt[i];
        }
    }

    // to calculate average time
    static void calculateAverageTime(Process pro[], int n) {
        int wt[] = new int[n];
        int tat[] = new int[n];
        int total_wt = 0;
        int total_tat = 0;

        calculateWaitingTime(pro, n, wt);
        calculateTurnAroundTime(pro, n, wt, tat);

        System.out.println("Process\t\tArrival Time\t\tBurst Time\t\tWaiting Time\t\tTurnaround Time");

        for (int i = 0; i < n; i++) {
            total_wt += wt[i];
            total_tat += tat[i];

            System.out.println(pro[i].processId + "\t\t\t" + pro[i].arrivalTime + "\t\t\t" + pro[i].burstTime +
                    "\t\t\t" + wt[i] + "\t\t\t" + tat[i]);
        }

        System.out.println("average waiting time => " + (float) total_wt / n);
        System.out.println("average turnaround time => " + (float) total_tat / n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter the number of processes:");
        int n = sc.nextInt();

        Process pro[] = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.println("enter the process id:");
            int id = sc.nextInt();

            System.out.println("enter the arrival time:");
            int at = sc.nextInt();

            System.out.println("enter the burst time:");
            int bt = sc.nextInt();

            pro[i] = new Process(id, at, bt);

        }

        FCFS.calculateAverageTime(pro, n);
        sc.close();
    }
}
