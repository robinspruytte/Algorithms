package algorithms;

public class StukjeSlang<X, Y> {
	
	private X x;
	private Y y;
	private StukjeSlang<X, Y> next;
	
	public StukjeSlang(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	
	public X get(){
		return x;
	}
	
	public Y get(){
		return y;
	}
	
	public void setNext(StukjeSlang<X, Y> volgendStukje){
		next = volgendStukje;
	}
	
	public StukjeSlang<X, Y> next(){
		return next;
	}
	public int hashCode() {
		return 41 * (41 * x.hashCode()) + y.hashCode() + next.hashCode();
	}
	
	public boolean equals(Object obj){
		if(obj instanceof StukjeSlang<?, ?>){
			StukjeSlang<X, Y> that = (StukjeSlang<X, Y>) obj;
			return (this.x == that.x && this.y == that.y && this.next == that.next);
					
		}
		return false;
	}
	

}
