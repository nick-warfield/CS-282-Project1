/*
COMP282 Section 16304 Project 1
Group members:
Nicholas Warfield
Javier Aguayo
John Wiesenfeld
*/

public interface DataStructOfItemsInGroups <E extends DataItem<E>> {

		public void insert(E item);
		public void delete(E item);
		public E find(E item);
		public int numInGroup(int num);
		public int sizeLargest();
		public int sizeSmallest();
		public String members(int num);
		public int numToReachAll();
}
