package raven.sqdev.misc;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * A class containing utility methods for deling with trees
 * 
 * @author Raven
 *
 */
public class TreeUtils {
	
	/**
	 * Searches for the first <code>TreeItem</code> contained in the given
	 * <code>Tree</code> whose data is equal to the given one
	 * 
	 * @param tree
	 *            The <code>Tree</code> to search
	 * @param data
	 *            The data to search for
	 * @return The respective <code>TreeItem</code> or <code>null</code> if none
	 *         could be found
	 */
	public static TreeItem findTreeItemWithData(Tree tree, Object data) {
		for (TreeItem currentItem : getAllTreeItems(tree)) {
			if (currentItem.getData() != null && currentItem.getData().equals(data)) {
				return currentItem;
			}
		}
		
		return null;
	}
	
	/**
	 * Gets all <code>TreeItems</code> contained in the given <code>Tree</code>
	 * 
	 * @param tree
	 *            The <code>Tree</code> whose items should get retrieved
	 * @return The list of all contained <code>TreeItems</code>
	 */
	public static List<TreeItem> getAllTreeItems(Tree tree) {
		List<TreeItem> itemList = new ArrayList<TreeItem>();
		
		for (TreeItem currentItem : tree.getItems()) {
			itemList.add(currentItem);
			
			itemList.addAll(getAllSubItems(currentItem));
		}
		
		return itemList;
	}
	
	/**
	 * Gets all <code>TreeItems</code> that are direct or indirect children of
	 * the given item
	 * 
	 * @param item
	 *            The <code>TreeItem</code> to use as a root
	 * @return The list of all sub-items
	 */
	public static List<TreeItem> getAllSubItems(TreeItem item) {
		List<TreeItem> itemList = new ArrayList<TreeItem>();
		
		for (TreeItem currentItem : item.getItems()) {
			itemList.add(currentItem);
			
			itemList.addAll(getAllSubItems(currentItem));
		}
		
		return itemList;
	}
	
}
