package in.raam.twsh.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Helper class providing utility functions across the application
 * @author raam
 *
 */
public abstract class Util {

    /**
     * Assembles a String containing all the elements of the input collection using the provided separator
     * suppose if we have a list of integers like 
     * <pre>
     *      List<Integer> l = new ArrayList<Integer>();
     *      l.add(1);l.add(2);l.add(3);
     * </pre>
     * Calling mkString as shown below
     * <pre>
     *      mkString(l,"|");
     * </pre>
     * will provide
     * <pre>
     *      "1|2|3"
     * </pre>
     * @param <T>
     * @param coll
     * @param separator
     * @return
     */
    public static <T> String mkString(Collection<T> coll,String separator) {
        StringBuilder sb = new StringBuilder();
        for(T t:coll)
            sb.append(t.toString()).append(separator);
        if(coll.size() > 1)
            sb.delete(sb.length() - separator.length(),sb.length());
        return sb.toString();
    }
    
    /**
     * Same as mkString but for an Array
     * @param <T>
     * @param coll
     * @param separator
     * @return
     */
    public static <T> String mkString(T[] coll,String separator) {
        return mkString(java.util.Arrays.asList(coll),separator);
    }
    
    /**
     * Same as mkString but for an InputStream
     * @param is
     * @return
     * @throws Exception
     */
    public static String mkString(InputStream is) throws Exception {
        int c;
        StringBuilder s = new StringBuilder();
        while ((c = is.read()) != -1) 
            s.append((char) c);
        return s.toString();
    }
    
    /**
     * Create a new list with one element specified
     * @param <T>
     * @param obj
     *              Single object used to create the list
     * @return
     */
    public static <T> List<T> newList(T obj){
        List<T> l = new ArrayList<T>();
        l.add(obj);
        return l;
    }
    
    /**
     * Check whether the specified string is empty or null
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str) {
        if(str!=null) {
            if(str instanceof String)
                return ((String) str).length() == 0;
        }
        return true;
    }
    
    /**
     * Equivalent to subList command but on Array
     * @param start
     * @param end
     * @param arr
     * @return
     */
    public static  String[] slice(int start, int end,String[] arr){
        if(arr.length > 1)
            return java.util.Arrays.asList(arr).subList(start, end).toArray(new String[0]);
        else
            return new String[0];
    }
    
    public static <T> Set<T> newSet(T ... array) {
        Set<T> s = new LinkedHashSet<T>();
        for(T t:array)
            s.add(t);
        return s;
    }
    

}
