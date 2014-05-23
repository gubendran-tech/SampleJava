/**
 * 
 */
package com.gubs.googleGuava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;

/**
 * @author gubs
 * 
 *         http://tomjefferys.blogspot.com/2011/09/multimaps-google-guava.html http
 *         ://www.javacodegeeks.com/2011/09/google-guava-libraries-essentials .html
 *         http://java.dzone.com/articles/google-guava-goodness-matching
 * 
 *         http://code.google.com/p/guava-libraries/wiki/OrderingExplained
 * 
 */
public class GoogleGuavaExamples {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		biMap();
		multiMap();
		multiSet();
		iterables();
		preConditions();
		predicateInGuavaString();
		predicateInGuavaObject();
		listCollections();
    guavaOrdering();
	}

	      /**
   * 
   * Quick => Ordering is Order by in DBMS. Compound helps for order by 2 columns.
   * 
   * Ordering is having implementation of Comparator. Were we can order the list comparing by nullFirst.
   * onNatural, with onResultOf we can apply function and based on the same ordering compound which returns an
   * ordering which uses the comparator to break ties
   * 
   * 
   */
  private static void guavaOrdering() {

    List<String> strings = Lists.newArrayList("kavi", "gubendran", "sai");

    Ordering<String> order = new Ordering<String>() {
      public int compare(String left, String right) {
        return left.compareToIgnoreCase(right);
      }
    };

    List<String> ordered = order.immutableSortedCopy(strings);
    System.out.println("Ordering in java.." + ordered.toString());

    // You can do ordering by custom function code in it.
    Ordering<String> orderFunction = Ordering.natural().onResultOf(new Function<String, Integer>() {
      public Integer apply(String text) {
        return text.length();
      }
    });

    // String will be taken as input and sort / order by based on the length
    List<String> stringsLengh = orderFunction.immutableSortedCopy(strings);
    System.out.println("Ordering function " + stringsLengh.toString());

  }

  /**
   * Lists interface as so many good features for collections
   */
	private static void listCollections() {
		
		// New mutable list creation
		List<String> listItems = Lists.newArrayList("Gubs", "Sai Theja", "Kavitha", "Lakshmanan");
		
		// List in Reverse Order
		System.out.println("Reverse Order list.." + Lists.reverse(listItems));
		
		// List parition based on the size
		int count = 0;
		for (List<String> listPartition :  Lists.partition(listItems, 2)) {
			System.out.println("List Partition " + count++ + listPartition.toString());
		}
	}

	private static void predicateInGuavaObject() {
		
		List<Item> items = new ArrayList<Item>();
		items.add(new Item(1, "Gubs"));
		items.add(new Item(2, "Kavitha"));
		items.add(new Item(3, "Sai Theja"));
    items.add(new Item(1, "Gubendran"));
		
		// Iterables.filter is the realHero. unfilteredType complete list, type to check the condition with passing construct attribute
    // This filter will collect all the item which have Id 1.
		List<Item> itemFound = Lists.newArrayList(Iterables.filter(items, new ItemPredicate(1)));
		
		// collections2 filter and i
//		List<Item> itemFound1 = Lists.newArrayList(Collections2.filter(items, new ItemPredicate(1));
		
		System.out.println("predicateInGuavaObject Found Size " + itemFound.size());
		
	}

	private static void predicateInGuavaString() {
		List<String> lists = Lists.newArrayList("Gubs", "Sai", "Kavitha");

		// predicateListData is a Type with Condition validation to validate
		// given object with matching string and return accordingly
		Predicate<String> predicateListData = new Predicate<String>() {
			@Override
			public boolean apply(String stringValue) {
				return stringValue.startsWith("G");
			}
		};

		// Iterables.filter or Collections2.filter anything you can pass the unfilteredList and type to filter and display
		List<String> listFound = Lists.newArrayList(Iterables.filter(lists,
				predicateListData));
		System.out.println("Predicate " + listFound);
	}

	/**
	 * PreConditions are good to check attributes in Object or variables
	 */
	private static void preConditions() {
		String name = "gubs";
		Preconditions.checkNotNull(name, "Name cannot be null");
		// Preconditions.checkArgument(name.length() > 2; "Its gubs");
	}

	/**
	 * Concat collections
	 */
	private static void iterables() {
		List<String> list1 = Arrays.asList(new String[] { "Gubs", "Kavi" });
		List<String> list2 = Arrays.asList(new String[] { "Sai" });

		for (String str : Iterables.concat(list1, list2)) {
			System.out.println("Iterables..." + str);
		}
	}

	/**
	 * MutiSet is allowed Duplicates and data is stored in non-guaranteed order.
	 * Helps to get count of the given objects
	 */
	private static void multiSet() {
		Multiset<String> multiSet = HashMultiset.create();

		List<String> list = Arrays.asList(new String[] { "Gubs", "Sai", "Sai",
				"Kavi", "Sai" });

		multiSet.addAll(list);

		System.out.println("How many Sai..." + multiSet.count("Sai"));
	}

	/**
	 * Based on key you can get value, based on value you can get key.
	 * Bidirectional Map
	 */

	private static void biMap() {
		BiMap<String, String> languageCodes = HashBiMap.create();

		languageCodes.put("en", "English");
		languageCodes.put("tn", "Tamil");
		languageCodes.put("fn", "French");

		System.out.println(languageCodes.get("en"));
		System.out.println(languageCodes.inverse().get("Tamil"));
	}

	/**
	 * Heterogenous data can be stored as a key Value Pair
	 */
	private static void multiMap() {
		Multimap<String, Object> multiMap = ArrayListMultimap.create();

		multiMap.put("Fruits", "apple");
		multiMap.put("Fruits", "apple");
		multiMap.put("Fruits", "Peer");
		multiMap.put("Fruits", "Orange");
		multiMap.put("Vegetables", "Carrot");
		multiMap.put("snacks",
				Arrays.asList(new String[] { "muruku", "samosa" }));

		System.out.println("Size...." + multiMap.size());

		// It returns collection since it has to support LinkedListMultiMap
		// etc..Use ListMultiMap if you need list value
		Collection<Object> fruits = multiMap.get("Fruits");

		System.out.println(Joiner.on(",").join(fruits));

		// You will get value Object
		Collection<Object> snacks = multiMap.get("snacks");
		System.out.println(snacks);

		// This will remove only Peer from fruit
		multiMap.remove("Fruits", "Peer");

		System.out.println(multiMap.size());

	}
}
