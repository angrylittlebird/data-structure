package unionfound;

public interface UF {
    public boolean isConnected(int p,int q);

    public void union(int p, int q);

    public int getSize();
}
