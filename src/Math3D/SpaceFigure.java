package Math3D;

import Point.Point;

import java.util.ArrayList;
import java.util.List;

public class SpaceFigure {
    private List<Point> pointList = new ArrayList<>();

    public SpaceFigure() {
    }

    public SpaceFigure(List<Point> pointList) {
        this.pointList = pointList;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }


    public double xMax (){
        double max = pointList.get(0).getX();
        for(int i = 0 ;i < pointList.size();i++){
            if(pointList.get(i).getX() >= max)
                max = pointList.get(i).getX();
        }
        return max;
    }

    public double yMax (){
        double max = pointList.get(0).getY();
        for(int i = 0 ;i < pointList.size();i++){
            if(pointList.get(i).getY() >= max)
                max = pointList.get(i).getY();
        }
        return max;
    }

    public double zMax (){
        double max = pointList.get(0).getZ();
        for(int i = 0 ;i < pointList.size();i++){
            if(pointList.get(i).getZ() >= max)
                max = pointList.get(i).getZ();
        }
        return max;
    }

    public double xMin (){
        double min = pointList.get(0).getX();
        for(int i = 0 ;i < pointList.size();i++){
            if(pointList.get(i).getX() <= min)
                min = pointList.get(i).getX();
        }
        return min;
    }

    public double yMin (){
        double min = pointList.get(0).getY();
        for(int i = 0 ;i < pointList.size();i++){
            if(pointList.get(i).getY() <= min)
                min = pointList.get(i).getY();
        }
        return min;
    }

    public double zMin (){
        double min = pointList.get(0).getZ();
        for(int i = 0 ;i < pointList.size();i++){
            if(pointList.get(i).getZ() <= min)
                min = pointList.get(i).getZ();
        }
        return min;
    }

    public boolean isIncludePoint(Point p){
        if(p.getX()>=xMin() && p.getX()<=xMax() && p.getY()>=yMin() && p.getY()<=yMax() && p.getZ()>=zMin() && p.getZ()<=zMax())
            return true;
        return false;
    }

    public boolean isPointInCuboidNotFace(Point p){
        if(p.getX()>xMin() && p.getX()<xMax() && p.getY()>yMin() && p.getY()<yMax() && p.getZ()>zMin() && p.getZ()<zMax())
            return true;
        return false;
    }
    public boolean isPointInFace(Point p){
        if((p.getX()==xMin() || p.getX()==xMax()) && p.getY()>=yMin() && p.getY()<=yMax() && p.getZ()>=zMin() && p.getZ()<=zMax())
            return true;
        if(p.getX()>=xMin() && p.getX()<=xMax() && (p.getY()==yMin() || p.getY()==yMax()) && p.getZ()>=zMin() && p.getZ()<=zMax())
            return true;
        if(p.getX()>=xMin() && p.getX()<=xMax() && p.getY()>=yMin() && p.getY()<=yMax() && (p.getZ()==zMax() ||p.getZ()==zMin()) )
            return true;
        return false;
    }

    public PlaneEquation planeXNotEqualZero(){
        for( int i = 0; i<pointList.size(); i++){
            if(pointList.get(i).getX()!=0)
                return new PlaneEquation(1,0,0,-pointList.get(i).getX());

        }
        return null;
    }

    public PlaneEquation planeYNotEqualZero(){
        for( int i = 0; i<pointList.size(); i++){
            if(pointList.get(i).getY()!=0)
                return new PlaneEquation(0,1,0,-pointList.get(i).getY());

        }
        return null;
    }

    public PlaneEquation planeZNotEqualZero(){
        for( int i = 0; i<pointList.size(); i++){
            if(pointList.get(i).getZ()!=0)
                return new PlaneEquation(0,0,1,-pointList.get(i).getZ());

        }
        return null;
    }

    public Point pointNotAtAllEqual(Point point){
        for(int i = 0; i<getPointList().size();i++){
            if(point.notAtAllEquals(getPointList().get(i)))
                return getPointList().get(i);
        }
        return null;
    }

    public boolean isRectangularCuboid(){
        int countX =0;
        int countY =0;
        int countZ =0;
        int countX1 =0;
        int countY1 =0;
        int countZ1 =0;
        double x = getPointList().get(0).getX();
        double y = getPointList().get(0).getY();
        double z = getPointList().get(0).getZ();
        try {
            double x1 = this.pointNotAtAllEqual(getPointList().get(0)).getX();
            double y1 = this.pointNotAtAllEqual(getPointList().get(0)).getY();
            double z1 = this.pointNotAtAllEqual(getPointList().get(0)).getZ();
            for(int i = 0; i< getPointList().size();i++){
                if(getPointList().get(i).getX() == x)
                    countX++;
                if(getPointList().get(i).getY()==y)
                    countY++;
                if(getPointList().get(i).getZ() == z)
                    countZ++;
                if(getPointList().get(i).getX() == x1)
                    countX1++;
                if(getPointList().get(i).getY()==y1)
                    countY1++;
                if(getPointList().get(i).getZ() == z1)
                    countZ1++;
            }
            if(countX ==4 && countY ==4 && countZ==4 && countX1 ==4 && countY1 ==4 && countZ1==4)
                return true;

            return false;
        }catch (Exception e){
            return false;
        }

    }

    public boolean isIncludeOrigin(){
        for(int i = 0; i< getPointList().size();i++) {
            if (getPointList().get(i).getX() == 0 && getPointList().get(i).getY() == 0 && getPointList().get(i).getZ() == 0)
                return true;
        }
        return false;
    }

    public boolean allCoordinatesGreaterZero(){
        for(int i = 0; i< getPointList().size();i++){
            if(!getPointList().get(i).isGreaterOrEqualZero())
                return false;
        }
        return true;
    }
}
