import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.plaf.IconUIResource;
import java.io.IOException;

public class Multi_Level_Equity {
    public static void multi_Level_Equity(String company1, int level) throws IOException, InterruptedException {
        StringBuilder SQARQL = new StringBuilder("select *\n" +
                "where {\n" +
                "<file:///F:/d2r-server-0.7/holder8.nt#holder_copy/" +
                 company1 + "> " +
                "<http://localhost:2020/vocab/resource/holder_copy_holder_name>");
        for(int i = 0; i < level; i++){
            if(i == 0) SQARQL.append(" ?n").append(i).append(" .\n");
            else SQARQL.append(" ?n").append(i - 1).append("<http://localhost:2020/vocab/resource/holder_copy_holder_name>").append(" ?n").append(i).append(" .\n");
        }
        SQARQL.append("}");
        JSONArray jsonList = util.gstoreUtil.query(SQARQL.toString());
        System.out.println(jsonList);
        StringBuilder result;
        for (int i = 0; i<jsonList.size();i++){
            result = new StringBuilder("");
            result.append("[path").append(i).append("]: ").append(company1);
            JSONObject path = jsonList.getJSONObject(i);
            for(int j = 0; j < level; j++){
                String uri = JSONObject.parseObject(jsonList.getJSONObject(i).getString("n" + j)).getString("value");
                result.append(" -> ").append(uri.substring(uri.lastIndexOf("/") + 1));
            }
            System.out.println(result);
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        multi_Level_Equity("上海锦江国际投资管理有限公司", 3);
    }
}
