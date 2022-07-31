package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class MyLogFormatter extends Formatter {
    public String getHead(Handler h) {
        return "START LOG\n";
    }
    
    public String format(LogRecord rec) {
        StringBuilder buf = new StringBuilder(1000);
        buf.append(calcDate(rec.getMillis()));
        
        buf.append(" [");
        buf.append(rec.getLevel());
        buf.append("] ");
        
        buf.append("[");
        buf.append(rec.getSourceMethodName());
        buf.append("] ");
        
        buf.append(rec.getMessage());
        buf.append("\n");

        return buf.toString();
    }
    public String getTail(Handler h) {
        return "END LOG\n";
    }
    
    private String calcDate(long millisecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return date_format.format(new Date(millisecs));
    }

    public static void setMyLogFormatter(java.util.logging.Logger logger) {
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }
        //=============================================

        logger.setLevel(Level.INFO);

        Handler handler = new ConsoleHandler();
        MyLogFormatter formatter = new MyLogFormatter();
        handler.setFormatter(formatter);
        logger.addHandler(handler);
    }
}