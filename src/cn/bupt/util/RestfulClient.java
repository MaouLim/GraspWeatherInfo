package cn.bupt.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/*
 * Created by Maou Lim on 2017/10/27.
 */
public class RestfulClient {

    public static final String[] stations = {
            "54398", "54399", "54406",
            "54416", "54419", "54421",
            "54424", "54431", "54433",
            "54499", "54501", "54505",
            "54511", "54513", "54514"
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

    private static final String user      = "5089874550209XyBi";
    private static final String pwd       = "MVzYxdx";
    private static final String timeRange = "[20171027000000,20171030100000]";

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

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer buffer = new StringBuffer();

            String line = null;
            while (null != (line = reader.readLine())) {
                buffer.append(line);
            }



            System.out.println(buffer.toString());

            connection.disconnect();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
