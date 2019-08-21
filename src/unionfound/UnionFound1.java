package unionfound;

/**
 * quick find
 */
public class UnionFound1 implements UF{
    private int[] id;

    public UnionFound1(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    @Override
    public boolean isConnected(int p,int q) {
        return id[p] == id[q];
    }

    @Override
    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);

        if (pid == qid) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == qid) {
                id[i] = pid;
            }
        }
    }

    private int find(int index){
        if (index < 0 || index >= id.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }

        return id[index];
    }

    @Override
    public int getSize() {
        return id.length;
    }
}
