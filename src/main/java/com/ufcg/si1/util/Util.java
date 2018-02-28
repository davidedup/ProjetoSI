package com.ufcg.si1.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Util {

	public static <T> List<T> toList(Iterable<T> iterable) {
		ArrayList<T> list = new ArrayList<T>();

		for (T t : iterable) {
			list.add(t);
		}

		return list;
	}

}
