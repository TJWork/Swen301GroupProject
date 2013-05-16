package UserInterface;

import javax.script.*;


public class Maps {

	
	public Maps() throws ScriptException{
		ScriptEngine js = new ScriptEngineManager().getEngineByName("javascript");
	    Bindings bindings = js.getBindings(ScriptContext.ENGINE_SCOPE);
	    bindings.put("stdout", System.out);
	    js.eval("stdout.println(Math.cos(Math.PI));");
	}
	
}
