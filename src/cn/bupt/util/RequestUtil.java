package cn.bupt.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/*
 * Created by Maou Lim on 2017/10/27.
 */
public class RequestUtil {

    public static final String url = "http://api.data.cma.cn:8090/api";

    public static URL createURL(String       user,
                                String       pwd,
                                String       timeRange,
                                List<String> stationIds,
                                List<String> elementIds)
            throws MalformedURLException {

        StringBuilder urlStr = new StringBuilder(url);

        urlStr.append('?');
        urlStr.append("userId=" + user + "&");
        urlStr.append("pwd="    + pwd  + "&");

        urlStr.append("dataFormat="  + "json"                          + "&");
        urlStr.append("interfaceId=" + "getSurfEleByTimeRangeAndStaID" + "&");
        urlStr.append("dataCode="    + "SURF_CHN_MUL_HOR"              + "&");
        urlStr.append("timeRange="   + timeRange                       + "&");

        urlStr.append("staIDs="   + Stringifier.getString(stationIds, ",") + "&");
        urlStr.append("elements=" + Stringifier.getString(elementIds, ",") + "&");

        return new URL(urlStr.toString());
    }
}
