package unionfound;

public class UnionFound3 implements UF {
    private int parent[];
    /**当前索引为根节点的节点数*/
    private int sz[];
    public UnionFound3(int size) {
        parent = new int[size];
        sz =new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
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

        if (sz[parentP] < sz[parentQ]) {
            parent[parentP] = parentQ;
            sz[parentQ] += sz[parentP];
        }else {
            parent[parentQ] = parentP;
            sz[parentP] += sz[parentQ];
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
