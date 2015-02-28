package com.achui.quick.xmlmapping;

public class IfClosure<E> implements Closure<E> {
	private final Condition cond;
	 private final Closure iTrueClosure;
    /** The closure to use if false */
    private final Closure iFalseClosure;
	public IfClosure(Condition<? super E> condition, Closure<? super E> trueClosure, Closure<? super E> falseClosure){
		this.cond = condition;
		this.iTrueClosure = trueClosure;
		this.iFalseClosure = falseClosure;
	}

	@Override
	public void execute(E input) {
		// TODO Auto-generated method stub
		if(this.cond.evaluate(input) == true){
			iTrueClosure.execute(input);
		}else{
			iFalseClosure.execute(input);
		}
		
	}

	@Override
	public String toXquery() {
		// TODO Auto-generated method stub
		return null;
	}

}
