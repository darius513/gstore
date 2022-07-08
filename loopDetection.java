import java.io.IOException;
import java.util.*;

public class loopDetection {

    private static List<String> neighbor(String source) throws IOException, InterruptedException {
        return Multi_Level_Equity.multi_Level_Equity(source, 1);
    }

    private static boolean BFS(String source, String target) throws IOException, InterruptedException {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()){
            String node = queue.poll();
            if(!visited.contains(node)){
                if(node.equals(target)) return true;
                visited.add(node);
                queue.addAll(neighbor(node));
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String company2 = "安邦保险集团股份有限公司";
        String company1 = "招商银行股份有限公司";
        if(BFS(company1, company2) && BFS(company2, company1)){
            System.out.println("存在环形持股");
        }else {
            System.out.println("不存在环形持股");
        }
    }
}
//INSERT DATA
//{
//<file:///F:/d2r-server-0.7/holder8.nt#holder_copy/招商银行股份有限公司> <http://localhost:2020/vocab/resource/holder_copy_holder_name> <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/安邦保险集团股份有限公司>  .
//}