package unionfound;

/**
 * quick union
 */
public class UnionFound2 implements UF {
    private int[] parent;

    public UnionFound2(int size) {
        parent = new int[size];
        for (int i = 0; i <size ; i++) {
            parent[i] = i;
        }
    }
    @Override
    public int getSize(){
        return parent.length;
    }

    private int find(int index) {
        while (index != parent[index]) {
            index = parent[index];
        }

        return index;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void union(int p, int q) {
        int parentP = find(p);
        int parentQ = find(q);
        if (parentP == parentQ) {
            return;
        }

        parent[parentP] = parentQ;
    }
}
