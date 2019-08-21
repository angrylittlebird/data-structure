package unionfound;

public class UnionFound4 implements UF {
    private int parent[];
    /**
     * 当前索引为根节点的深度
     */
    private int rank[];

    public UnionFound4(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
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

        if (rank[parentP] < rank[parentQ]) {
            parent[parentP] = parentQ;
        } else if (rank[parentP] > rank[parentQ]) {
            parent[parentQ] = parentP;
        } else {
            parent[parentP] = parentQ;
            rank[parentQ] += 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }

        return p;
    }
}
