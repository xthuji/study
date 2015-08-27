package com.work.test.tools;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * json 简单操作的工具类
 */
public class GsonUtils {

	private static Gson gson = null;
	/**
	 * 忽略掉含有注解  {@link com.google.gson.annotations.Expose}
	 */
	private static Gson gsonWithoutExpose = null;
	static {
		gson = new Gson();
		gsonWithoutExpose = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}

	private GsonUtils() {
	}

	/**
	 * 将对象转换成json格式
	 * 
	 * @param ts
	 * @return
	 */
	public static String objectToJson(Object ts) {
		String jsonStr = null;
		if (gson != null) {
			jsonStr = gson.toJson(ts);
		}
		return jsonStr;
	}
	
	public static String object2JsonWithoutExpose(Object o){
		String jsonStr = null;
		if (gsonWithoutExpose != null) {
			jsonStr = gsonWithoutExpose.toJson(o);
		}
		return jsonStr;
	}

	/**
	 * 将对象转换成json格式(并自定义日期格式)
	 * 
	 * @param ts
	 * @return
	 */
	public static String objectToJsonDateSerializer(Object ts, final String dateformat) {
		String jsonStr = null;
		gson = new GsonBuilder().registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {
			public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
				SimpleDateFormat format = new SimpleDateFormat(dateformat);
				return new JsonPrimitive(format.format(src));
			}
		}).setDateFormat(dateformat).create();
		if (gson != null) {
			jsonStr = gson.toJson(ts);
		}
		return jsonStr;
	}

	/**
	 * 对象转json，时间格式yyyy-MM-dd HH:mm:ss
	 * @param obj
	 * @return
	 */
	public static String objectToJsonDateFomart(Object obj) {
		return objectToJsonDateSerializer(obj, TimeUtils.sdfString);
	}
	/**
	 * 将json格式转换成list对象
	 * 用下面的json2List
	 * @param jsonStr
	 * @return
	 */
	@Deprecated
	public static List<?> jsonToList(String jsonStr) {
		List<?> objList = null;
		if (gson != null) {
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<?>>() {
			}.getType();
			objList = gson.fromJson(jsonStr, type);
		}
		return objList;
	}
	
	public static <T> List<T> json2List(String jsonStr, TypeToken<List<T>> token) {
		List<T> objList = null;
		if (gson != null) {
			java.lang.reflect.Type type = token.getType();
			objList = gson.fromJson(jsonStr, type);
		}
		return objList;
	}

	/**
	 * 将json格式转换成map对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<?, ?> jsonToMap(String jsonStr) {
		Map<?, ?> objMap = null;
		if (gson != null) {
			// java.lang.reflect.Type type=new
			// test.google.gson.reflect.TypeToken<Map<?,?>>(){}.getType();
			// objMap=gson.fromJson(jsonStr, type);

			Map<String, String> map = new HashMap<String, String>();
			objMap = (Map<String, String>) gson.fromJson(jsonStr, map.getClass());

			// JSONObject jasonObject = JSONObject.fromObject(jsonStr);
			// System.out.println(jasonObject.get("status"));

		}
		return objMap;
	}

	// /**
	// * 将json转换成bean对象
	// *
	// * @param jsonStr
	// * @return
	// */
	// public static Object jsonToBean(String jsonStr, Class<?> cl) {
	// Object obj = null;
	// if (gson != null) {
	// obj = gson.fromJson(jsonStr, cl);
	// }
	// return obj;
	// }

	/**
	 * 将json转换成bean对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToBean(String jsonStr, Class<T> cl) {
		Object obj = null;
		if (gson != null) {
			obj = gson.fromJson(jsonStr, cl);
			return (T) obj;
		}
		return null;
	}


    /**
     * 将json转换成bean对象
     * 
     * @param jsonStr
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonToBean(String jsonStr, Type type) {
        Object obj = null;
        if (gson != null) {
            obj = gson.fromJson(jsonStr, type);  
            return (T) obj;
        }
        return null;
    }
	/**
	 * 将json转换成bean对象
	 * 
	 * @param jsonStr
	 * @param cl
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToBeanDateSerializer(String jsonStr, Class<T> cl, final String pattern) {
		Object obj = null;
		gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				String dateStr = json.getAsString();
				try {
					return format.parse(dateStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
		}).setDateFormat(pattern).create();
		if (gson != null) {
			obj = gson.fromJson(jsonStr, cl);
		}
		return (T) obj;
	}

	/**
	 * 按时间格式yyyy-MM-dd HH:mm:ss，转化对象
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonToBeanDateFomart(String jsonStr, Class<T> clazz) {
		return jsonToBeanDateSerializer(jsonStr, clazz, TimeUtils.sdfString);
	}
	/**
	 * 根据
	 * 
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static Object getJsonValue(String jsonStr, String key) {
		Object rulsObj = null;
		Map<?, ?> rulsMap = jsonToMap(jsonStr);
		if (rulsMap != null && rulsMap.size() > 0) {
			rulsObj = rulsMap.get(key);
		}
		return rulsObj;
	}

    /**
     * JSON 转MAP或LIST集合
     * @param json 标准JSON格式字符串
     * @return Object （主要是MAP与List集合）
     */
    public static Object parse(String json) {
        if(json==null){
            return json;
        }
        JsonElement ele  = new com.google.gson.JsonParser().parse(json);
        if  (ele.isJsonObject()){
            Set<Map.Entry<String, JsonElement>> set = ((JsonObject)ele).entrySet();
            Iterator<Map.Entry<String, JsonElement>> iterator = set.iterator();
            HashMap<String, Object> map = new HashMap<String, Object>();
            while (iterator.hasNext()) {
                Map.Entry<String, JsonElement> entry = iterator.next();
                String key = entry.getKey();
                JsonElement value = entry.getValue();
                if (!value.isJsonPrimitive()) {
                    map.put(key, parse(value.toString()));
                } else {
                    map.put(key, value.getAsString());
                }
            }
            return map;
        }else if (ele.isJsonArray()){
            JsonArray set = ele.getAsJsonArray();
            Iterator<JsonElement> iterator = set.iterator();
            List list = new ArrayList();
            while (iterator.hasNext()){
                JsonElement entry = iterator.next();
                if (!entry.isJsonPrimitive()) {
                    list.add(parse(entry.toString()));
                } else {
                    list.add(entry.getAsString());
                }
            }
            return list;
        }else if (ele.isJsonPrimitive()){
            return json;
        }
        return null;
    }

	/**
	 * JSON 转换MAP 对象
	 * @param json 标准字符串
	 * @return
	 */
	public static Map<String, Object> jsonToMapEx2(String json) {
		if(json==null){
			return null;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonElement element = (JsonElement) new com.google.gson.JsonParser().parse(json);
		if(element.isJsonObject()){
			JsonObject object = (JsonObject) new com.google.gson.JsonParser().parse(json);
			Set<Map.Entry<String, JsonElement>> set = object.entrySet();
			Iterator<Map.Entry<String, JsonElement>> iterator = set.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, JsonElement> entry = iterator.next();
				String key = entry.getKey();
				JsonElement value = entry.getValue();
				if(value.isJsonPrimitive()){
					map.put(key,value.getAsString());
				}else if(value.isJsonObject()){
					map.put(key,jsonToMapEx2(value.toString()));
				}else if(value.isJsonArray()){
					List list = new ArrayList();
					for(JsonElement arrayElement :value.getAsJsonArray()){
						if(arrayElement.isJsonPrimitive()){
							list.add(arrayElement.getAsString());
						}else if(arrayElement.isJsonObject()){
							list.add(jsonToMapEx2(arrayElement.toString()));
						} else if(arrayElement.isJsonNull()){
							list.add(null);
						} else if(arrayElement.isJsonArray()){
							list.add(jsonToMapEx2(arrayElement.toString()));
						}else{
							list.add(arrayElement.toString());
						}
					}
					map.put(key,list);
				}else if(value.isJsonNull()){
					map.put(key,null);
				}else{
					map.put(key,value.getAsString());
				}
			}
		}
		return map;
	}
	
}