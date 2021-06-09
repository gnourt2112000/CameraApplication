package Camera;

import Point.Point;
import Print.Show;
import Room.Room;
import Math3D.*;

public class Camera implements Show{

	private Point peakPoint;
	private double horizontalFieldOfView;
	private double verticalFieldOfView;
	private double height;

	public Camera() {
	}

	public Camera(Point p, double horizontalFieldOfView, double verticalFieldOfView) {
		this.peakPoint = p;
		this.horizontalFieldOfView = horizontalFieldOfView;
		this.verticalFieldOfView = verticalFieldOfView;
	}

	public Camera(Point p, double horizontalFieldOfView, double verticalFieldOfView, double height) {
		this.peakPoint = p;
		this.horizontalFieldOfView = horizontalFieldOfView;
		this.verticalFieldOfView = verticalFieldOfView;
		this.height = height;
	}

	public Point getPeakPoint() {
		return peakPoint;
	}

	public void setP(Point p) {
		this.peakPoint = p;
	}

	public double getHorizontalFieldOfView() {
		return horizontalFieldOfView;
	}

	public void setHorizontalFieldOfView(double horizontalFieldOfView) {
		this.horizontalFieldOfView = horizontalFieldOfView;
	}

	public double getVerticalFieldOfView() {
		return verticalFieldOfView;
	}

	public void setVerticalFieldOfView(double verticalFieldOfView) {
		this.verticalFieldOfView = verticalFieldOfView;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public PlaneEquation planeIncludePeakIn(SpaceFigure cuboid){
		if(cuboid.planeXNotEqualZero().isIncludePoint(peakPoint))
			return cuboid.planeXNotEqualZero();
		else if(cuboid.planeYNotEqualZero().isIncludePoint(peakPoint))
			return cuboid.planeYNotEqualZero();
		else if(cuboid.planeZNotEqualZero().isIncludePoint(peakPoint))
			return cuboid.planeZNotEqualZero();
		else if(Math3D.planeXEqualZero().isIncludePoint(peakPoint))
			return Math3D.planeXEqualZero();
		else
			return Math3D.planeYEqualZero();

	}

	public boolean includePoint(Point point,Room room){
		if(this.peakPoint.equals(point))
			return false;
		return Math3D.isPointInCameraInRoom(point,this,room);
	}

	@Override
	public void show() {
		this.getPeakPoint().show();
		System.out.println(" " + this.getHorizontalFieldOfView() + " " + this.getVerticalFieldOfView() );
		
	}
}
