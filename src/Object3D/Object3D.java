package Object3D;

import java.util.ArrayList;
import java.util.List;

import Camera.Camera;
import Point.Point;
import Math3D.*;

public class Object3D extends SpaceFigure {


    public Object3D(List<Point> listPoint) {
        super(listPoint);
    }


    public boolean IsInFloor(){
        for(int i = 0 ;i<this.getPointList().size();i++){
            if(Math3D.planeZEqualZero().isIncludePoint(this.getPointList().get(i)))
                return true;
        }
        return false;
    }

    public double height(){
        double maxRange = this.getPointList().get(0).rangeTo(this.planeZNotEqualZero());;
        for(int i =0 ;i<this.getPointList().size();i++){
            if(this.getPointList().get(i).rangeTo(this.planeZNotEqualZero())>= maxRange)
                maxRange = this.getPointList().get(i).rangeTo(this.planeZNotEqualZero());
        }
        return maxRange;
    }

    public PlaneEquation planeBottom(){
        double minZ = this.getPointList().get(0).getZ();
        for(int i = 0;i<this.getPointList().size();i++){
            if(this.getPointList().get(i).getZ()<=minZ)
                minZ = this.getPointList().get(i).getZ();
        }
        return new PlaneEquation(0,0,1,-minZ);
    }
    public PlaneEquation planeTop(){
        double maxZ = this.getPointList().get(0).getZ();
        for(int i = 0;i<this.getPointList().size();i++){
            if(this.getPointList().get(i).getZ()>=maxZ)
                maxZ = this.getPointList().get(i).getZ();
        }
        return new PlaneEquation(0,0,1,-maxZ);
    }

    public boolean isInvalidPosition(List<Object3D> list){
        List<Object3D> listObject = new ArrayList<Object3D>();
        listObject.addAll(list);
        listObject.remove(this);
        int check1 =0;
        int check2 =0;
        if(listObject.size()!=0){
            if(this.IsInFloor()){
                for(int i = 0;i<listObject.size();i++){
                    for(int j = 0;j<this.getPointList().size();j++){
                        if(listObject.get(i).isPointInCuboidNotFace(this.getPointList().get(j)))
                            return false;
                    }
                }
                return true;
            }else{
                for(int i = 0;i<listObject.size();i++){
                    for(int j = 0;j<this.getPointList().size();j++){
                        if(listObject.get(i).isPointInCuboidNotFace(this.getPointList().get(j)))
                            return false;
                        double a =this.getPointList().get(j).rangeTo(listObject.get(i).planeBottom());
                        double b = listObject.get(i).height();
                        if(Math.abs(a-b)<0.0001 && listObject.get(i).isPointInFace(this.getPointList().get(j)))
                            check1++;
                        if(Math.abs(this.getPointList().get(j).rangeTo(listObject.get(i).planeTop())-this.height())<0.0001)
                            check2++;
                    }

                }
                if(check1!=0 && check2!=0)
                    return true;
            }
        }else {
            if(this.IsInFloor())
                return true;
        }

        return false;
    }

    public List<PlaneEquation> twoFaceDirectCamera(Camera c, SpaceFigure cuboid){
        List<PlaneEquation> list = new ArrayList<>(2);
        PlaneEquation p1 = c.planeIncludePeakIn(cuboid);
        PlaneEquation p2 = new PlaneEquation(this.getPointList().get(0),p1.getA(),p1.getB(),p1.getC());
        list.add(p2);
        for(int i = 0 ;i<this.getPointList().size();i++){
            PlaneEquation p3 = new PlaneEquation(this.getPointList().get(i),p1.getA(),p1.getB(),p1.getC());
            if(!p2.equals(p3))
                list.add(p3);
        }
        return list;
    }

    public boolean isThrouthLineSegment(Camera camera, Point point, SpaceFigure cuboid){
        LineEquation line = new LineEquation(camera.getPeakPoint(),point);
        PlaneEquation plane1 = this.twoFaceDirectCamera(camera,cuboid).get(0);
        PlaneEquation plane2 = this.twoFaceDirectCamera(camera,cuboid).get(1);
        Point p1 = line.intersectionWithPlane(plane1);
        Point p2 = line.intersectionWithPlane(plane2);
        double disPeakPointP1 = (new Vector3D(camera.getPeakPoint(),p1)).length();
        double disPeakPointP2 = (new Vector3D(camera.getPeakPoint(),p2)).length();
        double disPeakPointPoint = (new Vector3D(camera.getPeakPoint(),point)).length();
        if(disPeakPointPoint+0.00001 >= disPeakPointP1 || disPeakPointPoint+0.00001 >= disPeakPointP2){
            if(this.isIncludePoint(p1))
                return true;
            if(this.isIncludePoint(p2))
                return true;
        }
        return false;
    }
	
	
}

