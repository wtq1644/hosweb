package com.mymc.tools;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class JsonUtil {

    private static JsonUtil jsonUtil;

    //直接实例化该对象
    private ObjectMapper mapper;


    public static void main(String[] args) {
        List<Map<String,Object>> maps = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("zs" , "张三");
        map1.put("ls" , "李四");
        maps.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("ww" , "王五");
        map2.put("zl" , "赵六");
        maps.add(map2);

        String json = JsonUtil.getInstance().toJson(maps);
        System.out.println(json);

        List<Map> maps1 = JsonUtil.getInstance().jsonToList(json, Map.class);
        System.out.println(maps1);

        List<Map<String, String>> maps2 = (List<Map<String, String>>)JsonUtil.getInstance().jsonToObject2(json, List.class, Map.class);
        System.out.println(maps2);//[{ls=李四, zs=张三}, {ww=王五, zl=赵六}]

        List<Map<String, String>> maps3 = JsonUtil.getInstance().jsonToObject3(json, new TypeReference<List<Map<String, String>>>() {});
        System.out.println(maps3);//[{ls=李四, zs=张三}, {ww=王五, zl=赵六}]
    }
    private JsonUtil() {
        mapper = new ObjectMapper();
        //创建并输出全部属性
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //禁止使用int代表Enum的order()來反序列化Enum,非常危險
        mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
    }

    public static JsonUtil getInstance() {
        if (jsonUtil == null) {
            synchronized (JsonUtil.class) {
                if (jsonUtil == null) {
                    jsonUtil = new JsonUtil();
                }
            }
        }
        return jsonUtil;
    }

    /**
     * 把对象转成字符串
     *
     * @param object
     * @return
     */
    public String toJson(Object object) {
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 把json字符串转成对象
     *
     * @param json
     * @param clazz
     * @return T
     */
    public <T> T jsonToObject(String json, Class<T> clazz) {
        if (com.mymc.tools.StringUtils.isNotBlank(json)) {
            try {
                return mapper.readValue(json, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 把JSON转成List<Bean>
     *
     * @param json  [{"a":"1","b":"2"},{"a":"1","b":"2"}]
     * @param clazz bean
     * @return
     */
    public <T> List<T> jsonToList(String json, Class<T> clazz) {
        if (com.mymc.tools.StringUtils.isNotBlank(json)) {
            try {
                return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将Json转化为对象数组
     *
     * @param json  [{"a":"1","b":"2","c":"3"}]
     * @param clazz Map.class
     * @param <T>   Map[]
     * @return
     */
    public <T> T[] jsonToArray(String json, Class<T> clazz) {
        if (com.mymc.tools.StringUtils.isNotBlank(json)) {
            try {
                return mapper.readValue(json, TypeFactory.defaultInstance().constructArrayType(clazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 把JSON转成Map
     *
     * @param json
     * @param keyClazz
     * @param valueClazz
     * @return
     */
    public <K, V> Map<K, V> jsonToMap(String json, Class<K> keyClazz, Class<V> valueClazz) {
        if (com.mymc.tools.StringUtils.isNotBlank(json)) {
            try {
                return mapper.readValue(json, TypeFactory.defaultInstance().constructParametricType(HashMap.class, keyClazz, valueClazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 把JSON转成Map(有序)
     *
     * @param json
     * @param keyClazz
     * @param valueClazz
     * @return
     */
    public <K, V> Map<K, V> jsonToLinkedMap(String json, Class keyClazz, Class valueClazz) {
        if (com.mymc.tools.StringUtils.isNotBlank(json)) {
            try {
                return mapper.readValue(json, TypeFactory.defaultInstance().constructParametricType(LinkedHashMap.class, keyClazz, valueClazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 把JSON转成并发Map
     *
     * @param json
     * @param keyClazz
     * @param valueClazz
     * @return
     */
    public <K, V> Map<K, V> jsonToConcMap(String json, Class keyClazz, Class valueClazz) {
        if (com.mymc.tools.StringUtils.isNotBlank(json)) {
            try {
                return mapper.readValue(json, TypeFactory.defaultInstance().constructParametricType(ConcurrentHashMap.class, keyClazz, valueClazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 把JSON转成 Map<keyClazz , Map<String , valueClazz>>
     *
     * @param json       {"a":{"key":"value"},"b":{"key1":"value1"}}
     * @param valueClazz MAP中的value CLASS
     * @return
     */
    public <T> Map<String, Map<String, T>> jsonToMapValMap(String json, Class<T> valueClazz) {
        if (com.mymc.tools.StringUtils.isNotBlank(json)) {
            try {
                return mapper.readValue(json, TypeFactory.defaultInstance().constructMapType(HashMap.class, TypeFactory.defaultInstance().uncheckedSimpleType(String.class), TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, valueClazz)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 把JSON转成Map<String,List<Bean>> value是LIST类型
     *
     * @param json       {"a":[{"key":"value"},{"key1":"value1"}]}
     * @param keyClazz
     * @param valueClazz LIST中的CLASS
     * @return
     */
    public <K, VT> Map<String, List<VT>> jsonToMapValList(String json, Class<K> keyClazz, Class<VT> valueClazz) {
        if (com.mymc.tools.StringUtils.isNotBlank(json)) {
            try {
                return mapper.readValue(json, TypeFactory.defaultInstance().constructMapType(HashMap.class, TypeFactory.defaultInstance().uncheckedSimpleType(keyClazz), TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, valueClazz)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Json字符串转为byte数组
     *
     * @param json
     * @return
     */
    public byte[] jsonTobyteArray(String json) {
        if (com.mymc.tools.StringUtils.isNotBlank(json)) {
            try {
                return mapper.readValue(json, TypeFactory.defaultInstance().constructArrayType(byte.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 把JSON转成任意的Object
     * 若需要返回 List<MyBean>, 则调用传入参数(json , ArrayList.class, MyBean.class)
     * 若需要返回 Map<String,MyBean>，则调用传入参数(json , HashMap.class, String.class, MyBean.class)
     *
     * @param json
     * @param objClazz
     * @param pclazz
     * @return
     */
    public <T> T jsonToObject2(String json, Class objClazz, Class... pclazz) {
        if (com.mymc.tools.StringUtils.isNotBlank(json)) {
            try {
                return mapper.readValue(json, TypeFactory.defaultInstance().constructParametricType(objClazz, pclazz));
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 把JSON转成任意复杂的Object
     * <p>
     * 如需要返回Map<String,String>则传入参数(json,new TypeReference<Map<String,String>>(){})
     * 如需要返回List<MyBean>则传入参数(json,new TypeReference<List<MyBean>>(){})
     *
     * @param <T>           最终返回的数据类型
     * @param json
     * @param typeReference
     * @return
     */
    public <T> T jsonToObject3(String json, TypeReference<T> typeReference) {
        T object = null;
        if (com.mymc.tools.StringUtils.isNotBlank(json)) {
            try {
                object = mapper.readValue(json, typeReference);
            } catch (Exception e) {
            }
        }
        return object;
    }

}
