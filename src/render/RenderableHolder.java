package render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	private List<IRenderable> entities ;
	
	public static RenderableHolder getInstance(){
		return instance;
	}
	
	public RenderableHolder(){
		entities = new ArrayList<IRenderable>();
	}
	
	public void add(IRenderable render){
		entities.add(render);
		Collections.sort(entities, new Comparator<IRenderable>() {
			@Override
			public int compare(IRenderable arg0, IRenderable arg1) {
				if(arg0.getZ() > arg1.getZ()) return 1;
				else return -1;
			}
		});
	}
	
	public List<IRenderable> getRenderableList(){
		return entities;
	}
}
