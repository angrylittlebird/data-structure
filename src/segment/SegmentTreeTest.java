package segment;


import org.junit.Test;

public class SegmentTreeTest {

    @Test
    public void test(){
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> integerSegmentTree = new SegmentTree<>(nums,(a,b) -> a+b);
        System.out.println(integerSegmentTree);
        integerSegmentTree.set(4,12);
        Integer query = integerSegmentTree.query(0, 2);
        assert query == 1;
        Integer query1 = integerSegmentTree.query(0, 5);
        assert query1 == 7;
        Integer query2 = integerSegmentTree.query(2, 5);
        assert query2 == 9;
    }
}

