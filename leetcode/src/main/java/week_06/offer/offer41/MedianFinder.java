package week_06.offer.offer41;

class MedianFinder {

    int[][] data;
    int rows;
    int columns;

    private static final int base = 1024;

    /** initialize your data structure here. */
    public MedianFinder() {
        data = new int[50][];
        data[0] = new int[base];

        rows = 0;
        columns = 0;
    }

    public void addNum(int num) {
        data[rows][columns] = num;
        columns++;
        if(columns == base){
            data[rows++]= new int[base];
            columns = 0;
        }
    }

    public double findMedian() {
        int lastOffset = (rows<<10)+(columns-1);
        int mid = lastOffset >> 1;
        if((lastOffset & 0x0001) == 0){
            return getData(mid);
        }
        int mid1 = getData(mid);
        int mid2 = getData(mid+1);
        return (mid1+mid2)/2.0;
    }

    private int getData(int offset){
        int row = offset>>10;
        int column = offset & 0x03ff;
        return data[row][column];
    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        System.out.println(finder.findMedian());
        finder.addNum(2);
        System.out.println(finder.findMedian());
        finder.addNum(3);
        System.out.println(finder.findMedian());
    }
}
