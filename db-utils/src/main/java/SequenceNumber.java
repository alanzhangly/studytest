import java.net.Inet4Address;

/**
 * Created by Ke Zhang on 2017/9/14.
 */
public class SequenceNumber {

    private static ThreadLocal<Integer> slNum = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public int getNextNum(){
        slNum.set(slNum.get() + 1);
        return slNum.get();
    }

    public static void main(String[] args){
  // QueryRunner
        SequenceNumber sn = new SequenceNumber();

        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
        for (int i = 0;i<3;i++){
            System.out.println("main:"+Thread.currentThread().getName()+"  num:"+sn.getNextNum() + "  object:"+SequenceNumber.slNum);
        }
    }

    private static class TestClient extends Thread{
        private SequenceNumber sn ;
        public TestClient(SequenceNumber sn){
            this.sn = sn;
        }

        @Override
        public void run() {
            for (int i = 0;i<3;i++){
                System.out.println("thread:"+Thread.currentThread().getName()+"  num:"+sn.getNextNum() + "  object:"+SequenceNumber.slNum);
            }
        }
    }

}
