package unionfound;

import org.junit.Test;

import java.util.Random;

public class UnionFound2Test {
    @Test
    public void union() {
//        UnionFound2 unionFound2 = new UnionFound2(7);
//        UnionFound1 unionFound2 = new UnionFound1(7);
//        UnionFound3 unionFound2 = new UnionFound3(7);
//        UnionFound4 unionFound2 = new UnionFound4(7);
//        UnionFound5 unionFound2 = new UnionFound5(7);
        UnionFound6 unionFound2 = new UnionFound6(7);
        unionFound2.union(0, 1);
        unionFound2.union(1, 2);

        unionFound2.union(3, 4);
        unionFound2.union(5, 4);
        unionFound2.union(1, 4);

        assert unionFound2.isConnected(0, 5);
        assert unionFound2.isConnected(0, 3);
        assert false == unionFound2.isConnected(0, 6);
        assert false == unionFound2.isConnected(5, 6);

    }

    @Test
    public void analyzePerfomance() {
        int n = 100000000;
        int size = 1000000;

//        System.out.println("quick find:"+performance(new UnionFound1(n),size));
//        System.out.println("quick union:"+performance(new UnionFound2(n),size));
        System.out.println("optimize union by sz:"+performance(new UnionFound3(n),size));
        System.out.println("optimize union by rank:"+performance(new UnionFound4(n),size));
        System.out.println("compress path:"+performance(new UnionFound5(n),size));
        System.out.println("compress path:"+performance(new UnionFound6(n),size));
    }

    private double performance(UF uf, int size) {
        Random random = new Random();
        int length = uf.getSize();
        long starttime = System.nanoTime();

        for (int i = 0; i < size; i++) {
            int p = random.nextInt(length);
            int q = random.nextInt(length);
            uf.union(p,q);
        }

        for (int i = 1; i < size; i++) {
            int p = random.nextInt(length);
            int q = random.nextInt(length);
            uf.isConnected(p,q);
        }

        long endtime = System.nanoTime();

        return (endtime - starttime) / 100000000.0;
    }
}
