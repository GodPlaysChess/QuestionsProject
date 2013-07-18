package examination.utils;

import org.apache.commons.collections.Predicate;

import java.util.*;

/**
 * author: a.savanovich
 * Date: 15.07.13
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
public class ListUtils {
    /**
     * Removes all elements from collection <tt>collection</tt> that contains collection <tt>toRemove</tt>
     *
     * @param collection a Collection to remove from
     * @param toRemove   a Collection that contains elements needed to remove
     * @param comparator a Comparator to compare objects
     * @param <T>        an Object to compare
     */
    public static <T> void removeAll(Collection<T> collection, Collection<T> toRemove,
                                     Comparator<T> comparator) {
        if (collection == null || collection.isEmpty() || toRemove == null
                || toRemove.isEmpty()) {
            return;
        }
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T collectionObject = iterator.next();
            for (T t : toRemove) {
                if (comparator.compare(collectionObject, t) == 0) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * Returns new List of the portion of the given list between the specified
     * <tt>offset</tt>, inclusive, and <tt>limit</tt>, exclusive and with predicate.
     * <p/>
     * A <code>Predicate</code> is the object equivalent of an <code>if</code> statement.
     * It uses the input object to return a true or false value, and is often used in
     * validation or filtering.
     *
     * @param sourceList List to cut
     * @param offset     fromIndex low endpoint (inclusive) of the subList
     * @param limit      toIndex high endpoint (exclusive) of the subList
     * @param predicate  object  the object to evaluate (optional)
     * @return Sub list
     */
    public static <T> List<T> subList(final List<T> sourceList,
                                      int offset, int limit, Predicate predicate) {
        List<T> subList = new ArrayList<T>(sourceList.size());

        if (offset < 0 || limit < 0) {
            return subList;
        }

        if (sourceList.size() <= offset) {
            return subList;
        }

        if (limit == 0) {
            limit = sourceList.size();
        }

        limit += offset;
        if (sourceList.size() < limit) {
            limit = sourceList.size();
        }


        if (sourceList.isEmpty()) {
            return sourceList;
        }


        if (predicate == null) {
            return sourceList.subList(offset, limit);
        }


        for (int i = offset; i < limit && i < sourceList.size(); i++) {

            if (predicate.evaluate(sourceList.get(i))) {
                subList.add(sourceList.get(i));
            }
        }
        return subList;
    }

    public static <T> List<T> subList(List<T> sourceList, int offset, int limit) {
        return subList(sourceList, offset, limit, null);
    }

    /**
     * Produces a new list of values by mapping each value in list through a transformation function (mapFunction).
     *
     * @param sourceList  the List to iterate and take Objects
     * @param mapFunction An Interface describing an Object to look for in the given List
     * @param <T>         an Objects stored in sourceList
     * @param <K>         an Objects that will be stored in the returned List
     * @return Map
     */
    public static <T, K> List<K> map(List<T> sourceList, MapFunction<T, K> mapFunction) {
        List<K> resultList = new ArrayList<K>();
        for (T tObj : sourceList) {
            resultList.add(mapFunction.map(tObj));
        }
        return resultList;
    }

    /**
     * Transformation function. Transforms source object of type T to new object of type K.
     *
     * @param <T> an Objects stored in sourceList
     * @param <K> an Object that will be stored in the returned List
     */
    public static interface MapFunction<T, K> {

        K map(T sourceObject);
    }


    public static String prettyPrint(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder("[\n");
        for (Map.Entry entry : map.entrySet()) {
            sb.append("\t").append(entry.getKey()).append(
                    " : ").append(entry.getValue()).append("\n");
        }
        return sb.append("]").toString();
    }

    public static String prettyPrint(Collection<?> c) {
        StringBuilder sb = new StringBuilder("[");
        for (Object o : c) {
            sb.append(o).append(", ");
        }
        sb.substring(0, sb.length() - 2);
        return sb.append("]").toString();
    }


}

