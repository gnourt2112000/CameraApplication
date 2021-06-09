package Math3D;

import Camera.Camera;
import Point.Point;

public class Math3D {

    static public Vector3D vectorDirectProduct(Vector3D a1, Vector3D a2){
        double t1 = a1.getY() * a2.getZ() - a2.getY() * a1.getZ();
        double t2 = a1.getZ() * a2.getX() - a1.getX() * a2.getZ();
        double t3 = a1.getX() * a2.getY() - a2.getX() * a1.getY();
        return new Vector3D(t1,t2,t3);
    }


    static public PlaneEquation planeXEqualZero(){
        return new PlaneEquation(1,0,0,0);
    }

    static public PlaneEquation planeYEqualZero(){
        return new PlaneEquation(0,1,0,0);
    }

    static public PlaneEquation planeZEqualZero(){
        return new PlaneEquation(0,0,1,0);
    }

    static public boolean isPointInCameraInRoom(Point p, Camera camera, SpaceFigure cuboid){
        if(cuboid.planeXNotEqualZero().isIncludePoint(camera.getPeakPoint()) || planeXEqualZero().isIncludePoint(camera.getPeakPoint())){
            PlaneEquation pHorizontal = new PlaneEquation(camera.getPeakPoint(),0,1,0);
            PlaneEquation pVertical = new PlaneEquation(camera.getPeakPoint(), 0,0,1);

            LineEquation pcameraTop = new LineEquation(p,camera.getPeakPoint());
            LineEquation pRightAngleZEqualZero = new LineEquation(p,0,0,1);
            LineEquation pRightAngleYEqualZero = new LineEquation(p,0,1,0);

            Vector3D directProduct1 = vectorDirectProduct(pcameraTop.vectorDirect(), pRightAngleZEqualZero.vectorDirect());
            Vector3D directProduct2 = vectorDirectProduct(pcameraTop.vectorDirect(), pRightAngleYEqualZero.vectorDirect());

            PlaneEquation planeIncludeP1 = new PlaneEquation(p,directProduct1.getX(),directProduct1.getY(),directProduct1.getZ());
            PlaneEquation planeIncludeP2 = new PlaneEquation(p,directProduct2.getX(),directProduct2.getY(),directProduct2.getZ());

            double ph = planeIncludeP1.angleWithPlane(pHorizontal);
            double pv = planeIncludeP2.angleWithPlane(pVertical);

            if(ph - 0.000001 <= camera.getHorizontalFieldOfView()/2 && pv - 0.0000001<= camera.getVerticalFieldOfView()/2)
                return true;
        }

        if(cuboid.planeYNotEqualZero().isIncludePoint(camera.getPeakPoint()) || planeYEqualZero().isIncludePoint(camera.getPeakPoint())){
            PlaneEquation pHorizontal = new PlaneEquation(camera.getPeakPoint(),1,0,0);
            PlaneEquation pVertical = new PlaneEquation(camera.getPeakPoint(), 0,0,1);

            LineEquation pcameraTop = new LineEquation(p,camera.getPeakPoint());
            LineEquation pRightAngleZEqualZero = new LineEquation(p,0,0,1);
            LineEquation pRightAngleXEqualZero = new LineEquation(p,1,0,0);

            Vector3D directProduct1 = vectorDirectProduct(pcameraTop.vectorDirect(), pRightAngleZEqualZero.vectorDirect());
            Vector3D directProduct2 = vectorDirectProduct(pcameraTop.vectorDirect(), pRightAngleXEqualZero.vectorDirect());

            PlaneEquation planeIncludeP1 = new PlaneEquation(p,directProduct1.getX(),directProduct1.getY(),directProduct1.getZ());
            PlaneEquation planeIncludeP2 = new PlaneEquation(p,directProduct2.getX(),directProduct2.getY(),directProduct2.getZ());

            double ph = planeIncludeP1.angleWithPlane(pHorizontal);
            double pv = planeIncludeP2.angleWithPlane(pVertical);

            if(ph-0.0000001 <= camera.getHorizontalFieldOfView()/2 && pv-0.000001<= camera.getVerticalFieldOfView()/2)
                return true;
        }
        if(cuboid.planeZNotEqualZero().isIncludePoint(camera.getPeakPoint())){
            PlaneEquation pHorizontal = new PlaneEquation(camera.getPeakPoint(),1,0,0);
            PlaneEquation pVertical = new PlaneEquation(camera.getPeakPoint(), 0,1,0);

            LineEquation pcameraTop = new LineEquation(p,camera.getPeakPoint());
            LineEquation pRightAngleXEqualZero = new LineEquation(p,1,0,0);
            LineEquation pRightAngleYEqualZero = new LineEquation(p,0,1,0);

            Vector3D directProduct1 = vectorDirectProduct(pcameraTop.vectorDirect(), pRightAngleYEqualZero.vectorDirect());
            Vector3D directProduct2 = vectorDirectProduct(pcameraTop.vectorDirect(), pRightAngleXEqualZero.vectorDirect());

            PlaneEquation planeIncludeP1 = new PlaneEquation(p,directProduct1.getX(),directProduct1.getY(),directProduct1.getZ());
            PlaneEquation planeIncludeP2 = new PlaneEquation(p,directProduct2.getX(),directProduct2.getY(),directProduct2.getZ());

            double ph = planeIncludeP1.angleWithPlane(pHorizontal);
            double pv = planeIncludeP2.angleWithPlane(pVertical);

            if(ph -0.0000001 <= camera.getHorizontalFieldOfView()/2 && pv - 0.0000001 <= camera.getVerticalFieldOfView()/2)
                return true;
        }
        return false;
    }




}
