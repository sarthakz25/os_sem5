public class Exp8 {
    // to allocate memory to blocks as per Best fit algorithm
    static void bestFit(int blockSize[], int m, int processSize[], int n) {
        int allocation[] = new int[n];

        // initially no block is assigned to any process
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;

        // pick each process and find suitable blocks according to its size
        for (int i = 0; i < n; i++) {
            // to find the best fit block for current process
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1)
                        bestIdx = j;
                    else if (blockSize[bestIdx] > blockSize[j])
                        bestIdx = j;
                }
            }

            // if we find a block for current process
            if (bestIdx != -1) {
                // allocate block j to p[i] process
                allocation[i] = bestIdx;

                // Reduce available memory in this block.
                blockSize[bestIdx] -= processSize[i];
            }

            // to print the process, block, and remaining sizes of blocks
            System.out.print(processSize[i] + " KB is put in ");
            if (allocation[i] != -1) {
                System.out.print((blockSize[bestIdx] + processSize[i]) + " KB partition, leaving (");
            } else {
                System.out.print("No suitable partition found, leaving (");
            }
            for (int j = 0; j < m; j++) {
                System.out.print(blockSize[j] + " KB");
                if (j < m - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");
        }
    }

    public static void main(String[] args) {
        int blockSize[] = { 300, 600, 350, 200, 750, 125 };
        int processSize[] = { 115, 500, 358, 200, 375 };
        int m = blockSize.length;
        int n = processSize.length;

        bestFit(blockSize, m, processSize, n);
    }
}
