package com.mds.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SpecificationUtils {

    /**
     * Check whether the given property belongs to a collection.
     * If we have one property to traverse we can give it as string to improve client readability
     *
     * @param values   the values we are checking the property against
     * @param property the property to check if it is found in the collection
     */
    public static <T> Specification<T> inListIgnoreCase(Collection<String> values, String property) {
        if (values == null) return null;

        Set<String> valuesInLowerCase = values.stream().map(String::toLowerCase).collect(Collectors.toSet());

        return (root, cq, cb) -> cb.lower(root.get(property)).in(valuesInLowerCase);
    }

    public static <T> Specification<T> inListObject(Collection<?> values, String property) {
        if (values == null) return null;

        Set<?> set = new HashSet<>(values);

        return (root, cq, cb) -> cb.lower(root.get(property)).in(set);
    }

    public static <T> Specification<T> equalsIgnoreCase(String value, String property) {
        if (value == null) return null;

        return (root, cq, cb) -> cb.equal(cb.lower(root.get(property)),value);
    }

    /**
     * Equals ignore case with more than one property to traverse. The root extraction must be given through a function.
     * @param value the value we want to match
     * @param rootGetter a lambda representing a way of getting the correct expression<String></String>
     */
    public static <T> Specification<T> equalsIgnoreCase(String value, Function<Root<T>, Expression<String>> rootGetter) {
        if (value == null) return null;
        return (root, cq, cb) -> cb.equal(cb.lower(rootGetter.apply(root)), value.toLowerCase());
    }

    public static <T> Specification<T> likeIgnoreCase(String value, String property) {
        if (value == null) return null;

        return (root, cq, cb) -> cb.like(cb.lower(root.get(property)), value.replace("*", "%"));

    }

    public static <T> Specification<T> likeIgnoreCase(String value, Function<Root<T>, Expression<String>> rootGetter) {
        if (value == null) return null;

        return (root, cq, cb) -> cb.like(cb.lower(rootGetter.apply(root)), value.replace("*", "%"));

    }

    public static <T> Specification<T> greaterThanOrEqualsTo(Integer value, Function<Root<T>, Expression<Integer>> rootGetter) {
        if (value == null) return null;

        return (root, cq, cb) -> cb.greaterThanOrEqualTo(rootGetter.apply(root), value);
    }

    public static <T> Specification<T> greaterThanOrEqualsTo(Integer value, String property) {
        if (value == null) return null;

        return (root, cq, cb) -> cb.greaterThanOrEqualTo(root.get(property), value);
    }

    public static <T> Specification<T> lessThanOrEqualTo(Integer value, String property) {
        if (value == null) return null;

        return (root, cq, cb) -> cb.lessThanOrEqualTo(root.get(property), value);
    }
}
