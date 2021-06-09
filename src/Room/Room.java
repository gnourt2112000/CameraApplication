package Room;

import java.util.ArrayList;
import java.util.List;

import Camera.Camera;
import Object3D.Object3D;
import Math3D.SpaceFigure;
import Point.Point;

public class Room extends SpaceFigure {
	private List<Object3D> object3DList = new ArrayList<Object3D>();
	private List<Camera> cameraList = new ArrayList<Camera>();

	public Room(List<Point> pointList) {
		super(pointList);
	}

	public Room() {
		// TODO Auto-generated constructor stub
	}

	public List<Object3D> getObject3DList() {
		return object3DList;
	}

	public void setObject3DList(List<Object3D> object3DList) {
		this.object3DList = object3DList;
	}

	public List<Camera> getCameraList() {
		return cameraList;
	}

	public void setCameraList(List<Camera> cameraList) {
		this.cameraList = cameraList;
	}


	public boolean isIncludeObject3D(Object3D object3d) {
		int count = 0;

		for(int i = 0; i<object3d.getPointList().size(); i++){
			if(!isIncludePoint(object3d.getPointList().get(i)))
				return false;
			if(object3d.getPointList().get(0).getZ() == object3d.getPointList().get(i).getZ())
				count++;
		}

		if(count != 4)
			return false;
		return true;
	}
	
	public boolean isIncludeCamera(Camera camera) {
		Point p = camera.getPeakPoint();
		if(p.getX()==xMin() || p.getX()==xMax() && p.getY()>=yMin() && p.getY()<=yMax() && p.getZ()>=zMin() && p.getZ()<=zMax())
			return true;
		if(p.getX()>=xMin() && p.getX()<=xMax() && p.getY()==yMin() || p.getY()==yMax() && p.getZ()>=zMin() && p.getZ()<=zMax())
			return true;
		if(p.getX()>=xMin() && p.getX()<=xMax() && p.getY()>=yMin() && p.getY()<=yMax() && p.getZ()==zMax())
			return true;
		return false;
	}
	
	
}
