package com.system.util.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class DumperUtils {

    private static Boolean DUMPER_DISABLED;
    private static String DUMPER_DISABLED_KEY = "dumper";

    static public void dump(Object object) {
        if (null == DUMPER_DISABLED) {
            DUMPER_DISABLED = null != System.getenv(DUMPER_DISABLED_KEY) &&
                    System.getenv(DUMPER_DISABLED_KEY).trim().toString().equals("0");
        }
        if (DUMPER_DISABLED) {
            return;
        }

        try {
            StackTraceElement traces[] = Thread.currentThread().getStackTrace();
            System.out.println(traces[2]);
            Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().setPrettyPrinting().create();
            System.out.println(gson.toJson(object));
        } catch (Exception e) {
            System.out.println(ToStringBuilder.reflectionToString(object, ToStringStyle.MULTI_LINE_STYLE));
        }
    }

    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);

        return result.toString();

    }

}
