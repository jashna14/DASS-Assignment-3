/**
 *
 */

import java.awt.*;
import java.awt.print.*;
import java.awt.geom.*;
import java.awt.font.*;

public class PrintableText implements Printable {
	String text;
	int POINTS_PER_INCH;

	public PrintableText(String t) {
		POINTS_PER_INCH = 72;
		text = t;
	}

	public int NosuchPage(int pageIndex) {
		if(pageIndex > 0) {
			return NO_SUCH_PAGE;
		}
		return pageIndex;
	}

	public double rettemp() {
		double tempval = 0.25 * POINTS_PER_INCH;
		return tempval;
	}

	public int TempsumValOneReturn(int j) {
		return j * 14;
	}

	public void loopfunction(Graphics2D g2d,FontRenderContext frc) {
		Font font = new Font("courier", Font.PLAIN, 12);
		String[] lines = text.split("\n");
		double tempvalue = rettemp();
		Point2D.Double pen = new Point2D.Double(tempvalue, tempvalue);
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].length() > 0) {
				TextLayout layout = new TextLayout(lines[i], font, frc);
				double tempsum = pen.y + TempsumValOneReturn(i);
				layout.draw(g2d, (float) pen.x, (float) (tempsum));
			}
		}
	}

	public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
//		if (pageIndex > 0) {
//			return NO_SUCH_PAGE;
//		}
		NosuchPage(pageIndex);

		Graphics2D g2d = (Graphics2D) g; // Allow use of Java 2 graphics on

		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		g2d.setPaint(Color.black);

//		Point2D.Double pen = new Point2D.Double(0.25 * POINTS_PER_INCH, 0.25 * POINTS_PER_INCH);

//		Font font = new Font ("courier", Font.PLAIN, 12);
   		FontRenderContext fac = g2d.getFontRenderContext();
   		loopfunction(g2d,fac);

//		String[] lines = text.split("\n");

//		for (int i=0; i < lines.length; i++) {
//			if (lines[i].length() > 0) {
//				TextLayout layout = new TextLayout(lines[i], font, frc);
//				double tempsum = pen.y + i*14;
//				layout.draw(g2d, (float) pen.x, (float) (tempsum));
//			}
//		}

		return PAGE_EXISTS;
	}

}
