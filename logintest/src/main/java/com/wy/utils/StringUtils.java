package com.wy.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;


/**
 * 本项目用到的通用字符串处理工具
 * 
 * @author xuesinuo
 */
@Component
public class StringUtils {
    
    /**
     * TODO 用于记录顺序数的变量，这样记录回导致重启项目重新记录，后期考虑其他记录方式：SQL、Redis
     */
    Map<String,Integer> eachNmuber = new HashMap<>();


    @Autowired
    ObjectMapper objectMapper;

    /**
     * @return 36位UUID字符串，32位有效字符，4位分隔符
     */
    public String getID() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return MD5加密值
     */
    public String getMD5(String... args) {
        String o = "";
        for (String arg : args) {
            o = o + arg;
        }
        return DigestUtils.md5DigestAsHex(o.getBytes());
    }

    /**
     * 将时间按照指定格式转字符
     * 
     * @param date
     * @param format
     * @return
     */
    public String getDateString(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    
    /**
     * 将Date对象按指定格式转为时间
     * @param date
     * @param format
     * @return
     */
    public Date getDate(Date date,String format){
        String dateString = this.getDateString(date, format);
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 将字符串按指定格式转为时间
     * @param dateString
     * @param format
     * @return
     */
    public Date getDate(String dateString,String format){
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @return 对SQL中like条件进行处理
     */
    public String sqlLike(String sqlPoerperty) {
        String result = "%" + sqlPoerperty.replaceAll("%", "\\%").replaceAll("_", "\\_").replaceAll(" ", "%") + "%";
        return result;
    }

    /**
     * JSON转Object工具，推荐用于转换Entity
     * 
     * @param <T>
     * @param json
     * @param c
     * @return
     */
    public <T> T Json2Object(String json, Class<T> c) {
        return this.Json2Object(json, c);
    }

    /**
     * JSON转List工具，推荐用于转换List_Entity
     * 
     * @param <T>
     * @param json
     * @param c
     * @return
     */
    public <T> List<T> Json2List(String json, Class<T> c) {
        return this.Json2Object(json, this.getTypeFactory().constructCollectionType(List.class, c));
    }

    /**
     * 类型工厂：用于指定Json转对象时的类型，特别适用于Json转集合（List、Map）
     * 
     * @return
     */
    public TypeFactory getTypeFactory() {
        return objectMapper.getTypeFactory();
    }

    /**
     * 
     * @param <T>
     * @param json
     * @param javaType 通过.getTypeFactory()获得javaType类型的组织方式
     * @return
     */
    public <T> T Json2Object(String json, JavaType javaType) {
        T t = null;
        try {
            t = objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 
     * @param json
     * @return
     */
    public List<Map<String, Object>> Json2ListMap(String json) {
        return this.Json2Object(json, this.getTypeFactory().constructCollectionType(List.class,
                this.getTypeFactory().constructMapType(Map.class, String.class, Object.class)));
    }

    /**
     * 
     * @param json
     * @return
     */
    public Map<String, Object> Json2Map(String json) {
        return this.Json2Object(json, this.getTypeFactory().constructMapType(Map.class, String.class, Object.class));
    }

    /**
     * Object转Json工具
     * 
     * @param o
     * @return
     */
    public String Object2Json(Object o) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
    
    /**
     * 获取顺序号
     * @return
     */
    public synchronized Integer getEachNumber(String key) {
        Integer number = eachNmuber.get(key);
        if(number == null) {
            number = 0;
        }
        eachNmuber.put(key, ++number);
        return number;
    }
}
