/**
 * 
 */
package com.gubs.googleGuava;

import com.google.common.base.Predicate;

/**
 * @author gubs
 *
 */
public class ItemPredicate implements Predicate<Item>{
	
	private int id;
	
	public ItemPredicate(int id) {
		this.id = id;
	}

	@Override
	public boolean apply(Item item) {
		return (item.getId() == this.id);
	}

}
