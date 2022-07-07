
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

public class TwoHop {

    public static void twoHop(String company1, String company2) throws IOException, InterruptedException {
        JSONArray jsonList = util.gstoreUtil.query(" select *\n" +
                "where{\n" +
                "<file:///F:/d2r-server-0.7/holder8.nt#holder_copy/" + company1 +
                ">  <http://localhost:2020/vocab/resource/holder_copy_holder_name> ?n .\n" +
                "?n <http://localhost:2020/vocab/resource/holder_copy_holder_name> <file:///F:/d2r-server-0.7/holder8.nt#holder_copy/" +
                company2 + ">\n" +
                "}");
        System.out.println(jsonList);
        for (int i = 0; i<jsonList.size();i++){
            String uri = JSONObject.parseObject(jsonList.getJSONObject(i).getString("n")).getString("value");
            System.out.println("[path " + i + "]: " + company1 + " -> " + uri.substring(uri.lastIndexOf("/") + 1) + " -> " + company2);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        twoHop("锦江国际（集团）有限公司", "中国石化销售有限公司");
    }
}
