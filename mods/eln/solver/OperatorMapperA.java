package mods.eln.solver;

import java.lang.reflect.InvocationTargetException;

import mods.eln.solver.Equation.Pow;

public class OperatorMapperA implements IOperatorMapper {
	private Class operator;
	private String key;
	public OperatorMapperA(String key,Class operator) {
		this.operator = operator;
		this.key = key;
	}
	
	@Override
	public IOperator newOperator(String key,int depthDelta,java.util.List<Object> arg,int argOffset){
		if(depthDelta != 0) return null;
		if(!this.key.equals(key)) return null;
		if(argOffset-1 >= 0 && arg.get(argOffset-1) instanceof IValue) return null;
		if(argOffset+1 > arg.size()-1 || false == arg.get(argOffset+1) instanceof IValue) return null;
		
		IOperator o;

	
		try {
			o = (IOperator) operator.newInstance();
			o.setOperator(new IValue[]{(IValue)arg.get(argOffset+1)});
			arg.set(argOffset,o);
			arg.remove(argOffset+1);
			return o;

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return null;
	}

}
