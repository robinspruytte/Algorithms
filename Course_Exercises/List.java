package algorithms.Course_Exercises;

public class List<E> {

		private Node<E> head;
		private int size = 1;
		List(E element){
			head = new Node<E>(element);
		}
		
		public int size(){
			return size;
		}
		
		public Node<E> head(){
			return head;
		}
		
		public void prepend(Node<E> node){
			node.setNext(head);
			head = node;
			size++;
		}
		
		/**
		 * Prepends a list to the list
		 * @param list
		 * @return returns itself
		 */
		
		public List<E> prepend(List<E> list){
			size = size + list.size;
			last(list.head).setNext(head);
			head = list.head;
			
			return this;
		}
		
		private Node<E> last(Node<E> cursor){
			if (cursor.next() == null)
				return cursor;
			return last(cursor.next());
		}
}
