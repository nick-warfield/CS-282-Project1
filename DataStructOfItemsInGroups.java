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
