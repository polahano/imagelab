package com.imagelab.operator.drawing;

import java.util.HashSet;
import java.util.Set;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import com.imagelab.operator.OpenCVOperator;
import com.imagelab.operator.basic.ReadImage;
import com.imagelab.operator.basic.WriteImage;
import com.imagelab.operator.filtering.ApplyBoxFilter;
import com.imagelab.operator.filtering.ApplyImagePyramid;
import com.imagelab.operator.geotransformation.RotateImage;
import com.imagelab.operator.geotransformation.ScaleImage;
import com.imagelab.operator.imagebluring.ApplyBlurEffect;
import com.imagelab.operator.imagebluring.ApplyGaussianBlurEffect;
import com.imagelab.operator.imagebluring.ApplyMedianBlurEffect;
import com.imagelab.operator.imageconversion.ColoredImageToBinary;
import com.imagelab.operator.imageconversion.ConvertToGrayscale;

public class DrawLine extends OpenCVOperator {
	
	/**
	 * thickness of the line
	 */
	private int thickness = 5;
	/**
     * pointX value for the openCV point.
     */
    private double pointX1 = 100d;
    /**
     * pointY value of the openCV point.
     */
    private double pointY1 = 20d;
    /**
     * pointX value for the openCV point.
     */
    private double pointX2 = 200d;
    /**
     * pointY value of the openCV point.
     */
    private double pointY2 = 20d;
	/**
	 
	 * Red value of the color
	 */
    private int R = 10;
    /**
	 * Red value of the color
	 */
    private int G = 10;
    /**
	 * Red value of the color
	 */
    private int B = 10;
	/**
	 * encapsulate the fields
	 * @return
	 */
    public int getThickness() {
		return thickness;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
	public int getR() {
		return R;
	}
	public void setR(int r) {
		this.R = r;
	}
	public int getG() {
		return G;
	}
	public void setG(int g) {
		this.G = g;
	}
	public int getB() {
		return B;
	}
	public void setB(int b) {
		this.B = b;
	}
    /**
     * get the pointX value
     */
	public double getPointX1() {
        return pointX1;
    }

    /**
     * @param pointX - double.
     */
    public void setPointX1(double pointX) {
        this.pointX1 = pointX;
    }

    /**
     * @return pointY.
     */
    public double getPointY1() {
        return pointY1;
    }

    /**
     * @param pointY - double.
     */
    public void setPointY1(double pointY) {
        this.pointY1 = pointY;
    }
    /**
     * get the pointX value
     * 
     */
	public double getPointX2() {
        return pointX2;
    }

    /**
     * @param pointX - double.
     */
    public void setPointX2(double pointX) {
        this.pointX2 = pointX;
    }

    /**
     * @return pointY.
     */
    public double getPointY2() {
        return pointY2;
    }

    /**
     * @param pointY - double.
     */
    public void setPointY2(double pointY) {
        this.pointY2 = pointY;
    }

	
	@Override
	public boolean validate(OpenCVOperator previous) {
		if (previous == null) {
            return false;
        }
        return allowedOperators().contains(previous.getClass());
	}

	@Override
	public Mat compute(Mat image) {
		// TODO Auto-generated method stub
		return drawCircle(image,getPointX1(),getPointY1(),getPointX2(),getPointY2(),
				getR(),getG(),getB(), getThickness()
				);
	}

	private Mat drawCircle(Mat src,double pointX1,double pointY1,double pointX2,double pointY2,
			int r, int g , int b, int thickness) {
		// Creating an empty matrix to store the result
        Mat image = new Mat();
        Point point1 = new Point(pointX1, pointY1);
        Point point2 = new Point(pointX2, pointY2);
        Scalar scalar = new Scalar(b,g,r);
        // Applying Pyramid up filter on the Image
        Imgproc.line(src,point1,point2,scalar,thickness);
        // Return the processed image
        return src;
	}

	@Override
	public Set<Class<?>> allowedOperators() {
		Set<Class<?>> allowed = new HashSet<>();
        allowed.add(ReadImage.class);
        allowed.add(RotateImage.class);
        allowed.add(WriteImage.class);
        allowed.add(ScaleImage.class);
        allowed.add(ColoredImageToBinary.class);
        allowed.add(ConvertToGrayscale.class);
        allowed.add(ApplyBlurEffect.class);
        allowed.add(ApplyGaussianBlurEffect.class);
        allowed.add(ApplyMedianBlurEffect.class);
        allowed.add(ApplyBoxFilter.class);
        allowed.add(ApplyImagePyramid.class);
        allowed.add(DrawCircle.class);
        return allowed;
	}
	/* 
	 * Information regarding the Image Pyramids operator
	 * */
	public enum Information{
		OPERATOR_INFO{
			public String toString() {
				return "Drawing a Line \n\n "
						+"You can draw various shape like Circle, Rectangle, Line, Ellipse, polylines, Convex,"
						+"Polylines on an image using respective methods."
						+"You can draw a line on an image using the method line()";
						
			}
		}
	}
}
