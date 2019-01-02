// how to run in cmd.exe
// javac CpuTest.java & java CpuTest
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CpuTest {
        public static void main(String... args){
                int vcpu = Runtime.getRuntime().availableProcessors();
                System.out.println("vCPU: " + vcpu);
                ExecutorService service = Executors.newFixedThreadPool(vcpu);
                List<Future<Double> > futures = new ArrayList<>();
                for(int i = 0; i < 100000; i++) {
                        futures.add(service.submit(()->IntStream.range(1, 100000).mapToDouble(Math::sin).sum()));
                }
                service.shutdown();
                System.out.println("dummy: " + futures.stream().mapToDouble(future->{
                        try{ return future.get(); }catch(Exception e) { return 0.0d; }
                }).sum());
        }
}
