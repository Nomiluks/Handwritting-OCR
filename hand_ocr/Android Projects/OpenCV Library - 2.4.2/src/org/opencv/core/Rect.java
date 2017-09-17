package org.opencv.core;

/**
 * <p>Template class for 2D rectangles, described by the following parameters:</p>
 * <ul>
 *   <li> Coordinates of the top-left corner. This is a default interpretation
 * of <code>Rect_.x</code> and <code>Rect_.y</code> in OpenCV. Though, in your
 * algorithms you may count <code>x</code> and <code>y</code> from the
 * bottom-left corner.
 *   <li> Rectangle width and height.
 * </ul>
 *
 * <p>OpenCV typically assumes that the top and left boundary of the rectangle are
 * inclusive, while the right and bottom boundaries are not. For example, the
 * method <code>Rect_.contains</code> returns <code>true</code> if</p>
 *
 * <p><em>x <= pt.x < x+width,
 * y <= pt.y < y+height</em></p>
 *
 * <p>Virtually every loop over an image ROI in OpenCV (where ROI is specified by
 * <code>Rect_<int></code>) is implemented as:</p>
 *
 * <p>In addition to the class members, the following operations on rectangles are
 * implemented:</p>
 * <ul>
 *   <li> <em>rect = rect +- point</em> (shifting a rectangle by a certain
 * offset)
 *   <li> <em>rect = rect +- size</em> (expanding or shrinking a rectangle by a
 * certain amount)
 *   <li> <code>rect += point, rect -= point, rect += size, rect -= size</code>
 * (augmenting operations)
 *   <li> <code>rect = rect1 & rect2</code> (rectangle intersection)
 *   <li> <code>rect = rect1 | rect2</code> (minimum area rectangle containing
 * <code>rect2</code> and <code>rect3</code>)
 *   <li> <code>rect &= rect1, rect |= rect1</code> (and the corresponding
 * augmenting operations)
 *   <li> <code>rect == rect1, rect != rect1</code> (rectangle comparison)
 * </ul>
 *
 * <p>This is an example how the partial ordering on rectangles can be established
 * (rect1 <em>subseteq</em> rect2):</p>
 *
 * <p>For your convenience, the <code>Rect_<></code> alias is available:</p>
 *
 * @see <a href="http://docs.opencv.org/modules/core/doc/basic_structures.html#rect">org.opencv.core.Rect_</a>
 */
public class Rect {

    public int x, y, width, height;

    public Rect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rect() {
        this(0, 0, 0, 0);
    }

    public Rect(Point p1, Point p2) {
        x = (int) (p1.x < p2.x ? p1.x : p2.x);
        y = (int) (p1.y < p2.y ? p1.y : p2.y);
        width = (int) (p1.x > p2.x ? p1.x : p2.x) - x;
        height = (int) (p1.y > p2.y ? p1.y : p2.y) - y;
    }

    public Rect(Point p, Size s) {
        this((int) p.x, (int) p.y, (int) s.width, (int) s.height);
    }

    public Rect(double[] vals) {
        set(vals);
    }

    public void set(double[] vals) {
        if (vals != null) {
            x = vals.length > 0 ? (int) vals[0] : 0;
            y = vals.length > 1 ? (int) vals[1] : 0;
            width = vals.length > 2 ? (int) vals[2] : 0;
            height = vals.length > 3 ? (int) vals[3] : 0;
        } else {
            x = 0;
            y = 0;
            width = 0;
            height = 0;
        }
    }

    public Rect clone() {
        return new Rect(x, y, width, height);
    }

    public Point tl() {
        return new Point(x, y);
    }

    public Point br() {
        return new Point(x + width, y + height);
    }

    public Size size() {
        return new Size(width, height);
    }

    public double area() {
        return width * height;
    }

    public boolean contains(Point p) {
        return x <= p.x && p.x < x + width && y <= p.y && p.y < y + height;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(height);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Rect)) return false;
        Rect it = (Rect) obj;
        return x == it.x && y == it.y && width == it.width && height == it.height;
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + ", " + width + "x" + height + "}";
    }
}
