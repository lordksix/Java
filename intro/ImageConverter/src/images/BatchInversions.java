package images;
import edu.duke.*;
import java.io.*;

public class BatchInversions {
    public ImageResource makeInvert(ImageResource inImage) {
		//I made a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//set pixel's red to average
			pixel.setRed(255-inPixel.getRed());
			//set pixel's green to average
			pixel.setGreen(255-inPixel.getGreen());
			//set pixel's blue to average
			pixel.setBlue(255-inPixel.getBlue());
		}
		//outImage is your answer
		return outImage;
	}

	public void selectAndConvert () {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource gray = makeInvert(inImage);
            String fname = gray.getFileName();
            String newName="inverted-"+fname;
            gray.setFileName(newName);
            gray.save();
			gray.draw();
		}
	}

	public void testInvert() {
		ImageResource ir = new ImageResource();
		ImageResource gray = makeInvert(ir);
		gray.draw();
	}

    public static void main(String[] args) {
        BatchInversions hw = new BatchInversions();
        hw.testInvert();
    }
}
