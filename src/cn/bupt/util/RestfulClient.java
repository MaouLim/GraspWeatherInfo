package cn.bupt.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Created by Maou Lim on 2017/10/27.
 *
 *
 * 	用户名:5089874550209XyBi
 密码:MVzYxdx
 */
public class RestfulClient {

    public static final String serverURL =
            "http://api.data.cma.cn:8090/api?userId=5089874550209XyBi&pwd=MVzYxdx&dataFormat=json&interfaceId=getSurfEleByTimeRangeAndStaID&dataCode=SURF_CHN_MUL_HOR&timeRange=[20170705030000,20170705030000]&staIDs=54511&elements=Station_Id_C,Year,Mon,Day,Hour,TEM";

    public static void main(String[] args) {
        try {
            URL restServiceURL = new URL(serverURL);
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
