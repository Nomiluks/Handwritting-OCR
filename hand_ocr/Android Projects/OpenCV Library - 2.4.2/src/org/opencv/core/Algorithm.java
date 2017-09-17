
//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.core;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import org.opencv.utils.Converters;

// C++: class Algorithm
/**
 * <p>This is a base class for all more or less complex algorithms in OpenCV,
 * especially for classes of algorithms, for which there can be multiple
 * implementations. The examples are stereo correspondence (for which there are
 * algorithms like block matching, semi-global block matching, graph-cut etc.),
 * background subtraction (which can be done using mixture-of-gaussians models,
 * codebook-based algorithm etc.), optical flow (block matching, Lucas-Kanade,
 * Horn-Schunck etc.).</p>
 *
 * <p>The class provides the following features for all derived classes:</p>
 * <ul>
 *   <li> so called "virtual constructor". That is, each Algorithm derivative is
 * registered at program start and you can get the list of registered algorithms
 * and create instance of a particular algorithm by its name (see
 * <code>Algorithm.create</code>). If you plan to add your own algorithms, it
 * is good practice to add a unique prefix to your algorithms to distinguish
 * them from other algorithms.
 *   <li> setting/retrieving algorithm parameters by name. If you used video
 * capturing functionality from OpenCV highgui module, you are probably familar
 * with <code>cvSetCaptureProperty()</code>, <code>cvGetCaptureProperty()</code>,
 * <code>VideoCapture.set()</code> and <code>VideoCapture.get()</code>.
 * <code>Algorithm</code> provides similar method where instead of integer id's
 * you specify the parameter names as text strings. See <code>Algorithm.set</code>
 * and <code>Algorithm.get</code> for details.
 *   <li> reading and writing parameters from/to XML or YAML files. Every
 * Algorithm derivative can store all its parameters and then read them back.
 * There is no need to re-implement it each time.
 * </ul>
 *
 * <p>Here is example of SIFT use in your application via Algorithm interface:</p>
 *
 * @see <a href="http://docs.opencv.org/modules/core/doc/basic_structures.html#algorithm">org.opencv.core.Algorithm</a>
 */
public class Algorithm {

    protected final long nativeObj;
    protected Algorithm(long addr) { nativeObj = addr; }


    //
    // C++: static Ptr_Algorithm Algorithm::_create(string name)
    //

    // Return type 'Ptr_Algorithm' is not supported, skipping the function


    //
    // C++:  Ptr_Algorithm Algorithm::getAlgorithm(string name)
    //

    // Return type 'Ptr_Algorithm' is not supported, skipping the function


    //
    // C++:  bool Algorithm::getBool(string name)
    //

    public  boolean getBool(String name)
    {

        boolean retVal = getBool_0(nativeObj, name);

        return retVal;
    }


    //
    // C++:  double Algorithm::getDouble(string name)
    //

    public  double getDouble(String name)
    {

        double retVal = getDouble_0(nativeObj, name);

        return retVal;
    }


    //
    // C++:  int Algorithm::getInt(string name)
    //

    public  int getInt(String name)
    {

        int retVal = getInt_0(nativeObj, name);

        return retVal;
    }


    //
    // C++: static void Algorithm::getList(vector_string& algorithms)
    //

    // Unknown type 'vector_string' (O), skipping the function


    //
    // C++:  Mat Algorithm::getMat(string name)
    //

    public  Mat getMat(String name)
    {

        Mat retVal = new Mat(getMat_0(nativeObj, name));

        return retVal;
    }


    //
    // C++:  vector_Mat Algorithm::getMatVector(string name)
    //

    public  List<Mat> getMatVector(String name)
    {
        List<Mat> retVal = new ArrayList<Mat>();
        Mat retValMat = new Mat(getMatVector_0(nativeObj, name));
        Converters.Mat_to_vector_Mat(retValMat, retVal);
        return retVal;
    }


    //
    // C++:  void Algorithm::getParams(vector_string& names)
    //

    // Unknown type 'vector_string' (O), skipping the function


    //
    // C++:  string Algorithm::getString(string name)
    //

    public  String getString(String name)
    {

        String retVal = getString_0(nativeObj, name);

        return retVal;
    }


    //
    // C++:  string Algorithm::paramHelp(string name)
    //

    public  String paramHelp(String name)
    {

        String retVal = paramHelp_0(nativeObj, name);

        return retVal;
    }


    //
    // C++:  int Algorithm::paramType(string name)
    //

    public  int paramType(String name)
    {

        int retVal = paramType_0(nativeObj, name);

        return retVal;
    }


    //
    // C++:  void Algorithm::set(string name, Ptr_Algorithm value)
    //

    // Unknown type 'Ptr_Algorithm' (I), skipping the function


    //
    // C++:  void Algorithm::set(string name, bool value)
    //

/**
 * <p>Sets the algorithm parameter</p>
 *
 * <p>The method sets value of the particular parameter. Some of the algorithm
 * parameters may be declared as read-only. If you try to set such a parameter,
 * you will get exception with the corresponding error message.</p>
 *
 * @param name The parameter name.
 * @param value The parameter value.
 *
 * @see <a href="http://docs.opencv.org/modules/core/doc/basic_structures.html#algorithm-set">org.opencv.core.Algorithm.set</a>
 */
    public  void setBool(String name, boolean value)
    {

        setBool_0(nativeObj, name, value);

        return;
    }


    //
    // C++:  void Algorithm::set(string name, double value)
    //

/**
 * <p>Sets the algorithm parameter</p>
 *
 * <p>The method sets value of the particular parameter. Some of the algorithm
 * parameters may be declared as read-only. If you try to set such a parameter,
 * you will get exception with the corresponding error message.</p>
 *
 * @param name The parameter name.
 * @param value The parameter value.
 *
 * @see <a href="http://docs.opencv.org/modules/core/doc/basic_structures.html#algorithm-set">org.opencv.core.Algorithm.set</a>
 */
    public  void setDouble(String name, double value)
    {

        setDouble_0(nativeObj, name, value);

        return;
    }


    //
    // C++:  void Algorithm::set(string name, int value)
    //

/**
 * <p>Sets the algorithm parameter</p>
 *
 * <p>The method sets value of the particular parameter. Some of the algorithm
 * parameters may be declared as read-only. If you try to set such a parameter,
 * you will get exception with the corresponding error message.</p>
 *
 * @param name The parameter name.
 * @param value The parameter value.
 *
 * @see <a href="http://docs.opencv.org/modules/core/doc/basic_structures.html#algorithm-set">org.opencv.core.Algorithm.set</a>
 */
    public  void setInt(String name, int value)
    {

        setInt_0(nativeObj, name, value);

        return;
    }


    //
    // C++:  void Algorithm::set(string name, Mat value)
    //

/**
 * <p>Sets the algorithm parameter</p>
 *
 * <p>The method sets value of the particular parameter. Some of the algorithm
 * parameters may be declared as read-only. If you try to set such a parameter,
 * you will get exception with the corresponding error message.</p>
 *
 * @param name The parameter name.
 * @param value The parameter value.
 *
 * @see <a href="http://docs.opencv.org/modules/core/doc/basic_structures.html#algorithm-set">org.opencv.core.Algorithm.set</a>
 */
    public  void setMat(String name, Mat value)
    {

        setMat_0(nativeObj, name, value.nativeObj);

        return;
    }


    //
    // C++:  void Algorithm::set(string name, vector_Mat value)
    //

/**
 * <p>Sets the algorithm parameter</p>
 *
 * <p>The method sets value of the particular parameter. Some of the algorithm
 * parameters may be declared as read-only. If you try to set such a parameter,
 * you will get exception with the corresponding error message.</p>
 *
 * @param name The parameter name.
 * @param value The parameter value.
 *
 * @see <a href="http://docs.opencv.org/modules/core/doc/basic_structures.html#algorithm-set">org.opencv.core.Algorithm.set</a>
 */
    public  void setMatVector(String name, List<Mat> value)
    {
        Mat value_mat = Converters.vector_Mat_to_Mat(value);
        setMatVector_0(nativeObj, name, value_mat.nativeObj);

        return;
    }


    //
    // C++:  void Algorithm::set(string name, string value)
    //

/**
 * <p>Sets the algorithm parameter</p>
 *
 * <p>The method sets value of the particular parameter. Some of the algorithm
 * parameters may be declared as read-only. If you try to set such a parameter,
 * you will get exception with the corresponding error message.</p>
 *
 * @param name The parameter name.
 * @param value The parameter value.
 *
 * @see <a href="http://docs.opencv.org/modules/core/doc/basic_structures.html#algorithm-set">org.opencv.core.Algorithm.set</a>
 */
    public  void setString(String name, String value)
    {

        setString_0(nativeObj, name, value);

        return;
    }


    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }



    // C++:  bool Algorithm::getBool(string name)
    private static native boolean getBool_0(long nativeObj, String name);

    // C++:  double Algorithm::getDouble(string name)
    private static native double getDouble_0(long nativeObj, String name);

    // C++:  int Algorithm::getInt(string name)
    private static native int getInt_0(long nativeObj, String name);

    // C++:  Mat Algorithm::getMat(string name)
    private static native long getMat_0(long nativeObj, String name);

    // C++:  vector_Mat Algorithm::getMatVector(string name)
    private static native long getMatVector_0(long nativeObj, String name);

    // C++:  string Algorithm::getString(string name)
    private static native String getString_0(long nativeObj, String name);

    // C++:  string Algorithm::paramHelp(string name)
    private static native String paramHelp_0(long nativeObj, String name);

    // C++:  int Algorithm::paramType(string name)
    private static native int paramType_0(long nativeObj, String name);

    // C++:  void Algorithm::set(string name, bool value)
    private static native void setBool_0(long nativeObj, String name, boolean value);

    // C++:  void Algorithm::set(string name, double value)
    private static native void setDouble_0(long nativeObj, String name, double value);

    // C++:  void Algorithm::set(string name, int value)
    private static native void setInt_0(long nativeObj, String name, int value);

    // C++:  void Algorithm::set(string name, Mat value)
    private static native void setMat_0(long nativeObj, String name, long value_nativeObj);

    // C++:  void Algorithm::set(string name, vector_Mat value)
    private static native void setMatVector_0(long nativeObj, String name, long value_mat_nativeObj);

    // C++:  void Algorithm::set(string name, string value)
    private static native void setString_0(long nativeObj, String name, String value);

    // native support for java finalize()
    private static native void delete(long nativeObj);

}
