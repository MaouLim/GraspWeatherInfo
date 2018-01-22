package cn.bupt.util;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
 * Created by Maou Lim on 2017/10/27.
 * Modified by Shun Lee on 2018/01/22.
 */
public class RestfulClient {

    public static final String[] stations = {
            //"54398", 
            //"54399", "54406",
            //"54416",
    		//"54419", 
    		//"54421",
            //"54424", "54431", "54433",
            //"54499", "54501", "54505",
            //"54511", "54513",
    		//"54514"
    		"50946","54063",
    		"54064","54069",
    		"54072","54161"
    };
    public static final String[] elements = {
            "Station_Id_C",
            "Year", "Mon", "Day", "Hour",
            "PRS", "PRS_Sea", "PRS_Max", "PRS_Min",
            "TEM", "TEM_Max", "TEM_Min",
            "RHU", "RHU_Min",
            "VAP", "PRE_1h",
            "WIN_D_INST_Max", "WIN_S_Max", "WIN_D_S_Max", "WIN_S_Avg_2mi", "WIN_D_Avg_2mi", "WIN_S_Inst_Max"
    };


    private static List<String> stationIds = Arrays.asList(stations);
    private static List<String> elementIds = Arrays.asList(elements);

    private static final String user      = "516604669181ejGCz";
    private static final String pwd       = "bh5kabv";
    private static final String timeRange = "[20180122070000,20180122070000]";

    public static final String outputPath = "D:\\weather.xlsx";

    public static void main(String[] args) {

        try {
            URL restServiceURL = RequestUtil.createURL(user, pwd, timeRange, stationIds, elementIds);
            HttpURLConnection connection = (HttpURLConnection) restServiceURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (200 != connection.getResponseCode()) {
                throw new RuntimeException(
                        "HTTP Get Request Failed with error code: " + connection.getResponseCode()
                );
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));

            StringBuffer buffer = new StringBuffer();

            String line = null;
            while (null != (line = reader.readLine())) {
            	
            	System.out.println(line);
            	
                buffer.append(line);
            }
            connection.disconnect();

            JSONObject obj = JSONObject.fromObject(buffer.toString());
            JSONArray data = (JSONArray) obj.get("DS");
            
            ExcelHelper helper = new ExcelHelper(outputPath);
            int count = Integer.valueOf(obj.get("colCount").toString());
            helper.writeExcelRow(count, data);

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
