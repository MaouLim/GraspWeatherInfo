package cn.bupt.util;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Maou Lim on 2017/10/30.
 */
public class Stringifier {

    public static String getString(List<?> list, String delim) {
        if (list.isEmpty()) {
            return "";
        }

        Iterator current = list.iterator();
        Iterator next    = list.iterator();
        next.next();

        StringBuilder builder = new StringBuilder();

        while (next.hasNext()) {
            builder.append(current.next());
            builder.append(delim);
            next.next();
        }

        builder.append(current.next());

        return builder.toString();
    }
}
