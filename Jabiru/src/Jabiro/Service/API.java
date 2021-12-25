package Jabiro.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

public class API<S, T>  {
	
	    private HashMap <S,T> mMap;
	    
		public API()
		{
			this.mMap=new HashMap<S,T>();
		}

	    
		@SuppressWarnings("unchecked")
		public void restore(S key) throws ClassNotFoundException, SQLException
		{
			this.mMap.put(key, (T) JabiroService.getmInstance().getmRestore().get(key));
			JabiroService.getmInstance().getmRestore().remove(key);
		}
		
		public void add(S key,T value )
		{
			this.mMap.put(key, value);
		}
		
		
		public void delete(S key) throws ClassNotFoundException, SQLException
		{
			JabiroService.getmInstance().getmRestore().put((String) key, this.mMap.get(key));
			this.mMap.remove(key);
		}
		
		public void update(S key,T value)
		{
			this.mMap.remove(key);
			this.mMap.put(key, value);
		}
		
		public void removeall() {
			this.mMap.clear();
		}
		
		public T getobject(S key)
		{
			return this.mMap.get(key);
		}
		
		public Boolean ContainsKey(S key)
		{
			return(this.mMap.containsKey(key));
		}
		
		public Set<S> Keyset()
		{
			return this.mMap.keySet();
		}
}
