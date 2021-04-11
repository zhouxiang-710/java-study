package com.base.hose;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public interface DataObject {
    interface  Indentity{
        String id();
    }
    interface HierarchyLike extends Indentity{
        String parentId();
        default boolean isRoot(){
            return parentId().isEmpty();
        }
    }
    static <N extends HierarchyLike,M> List<M> makeTree(List<N> nodes, String rootId, BiFunction<N,List<M>,M> func){
        N root = null;
        if (!rootId.isEmpty()) {
            for (N n : nodes) {
                if (n.id().equals(rootId)) {
                    root = n;
                    break;
                }
            }
            if (root == null) {
                return Collections.emptyList();
            }
        }
        Map<String, List<N>> map = new HashMap<>();
        nodes.forEach(n -> map.computeIfAbsent(n.parentId(), x -> new ArrayList<>()).add(n));
        class Util {
            private M makeTree(N n) {
                List<N> arr = map.get(n.id());
                List<M> arr2;
                if (arr == null) {
                    arr2 = Collections.emptyList();
                } else {
                    arr2 = arr.stream().map(this::makeTree).collect(Collectors.toList());
                }
                return func.apply(n, arr2);
            }
        }

        Util u = new Util();
        List<M> arr = new ArrayList<>();
        for (N n : nodes) {
            if (n.parentId().equals(rootId)) {
                arr.add(u.makeTree(n));
            }
        }
        if (!rootId.isEmpty()) {
            arr = Collections.singletonList(func.apply(root, arr));
        }
        return arr;

    }
}
