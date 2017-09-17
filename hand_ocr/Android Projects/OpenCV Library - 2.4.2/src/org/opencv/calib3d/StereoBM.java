
//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.calib3d;

import org.opencv.core.Mat;

// C++: class StereoBM
/**
 * <p>Class for computing stereo correspondence using the block matching algorithm.</p>
 *
 * <p>The class is a C++ wrapper for the associated functions. In particular,
 * :ocv:funcx:"StereoBM.operator()" is the wrapper for "cvFindStereoCorrespondenceBM".</p>
 *
 * @see <a href="http://docs.opencv.org/modules/calib3d/doc/camera_calibration_and_3d_reconstruction.html#stereobm">org.opencv.calib3d.StereoBM</a>
 */
public class StereoBM {

    protected final long nativeObj;
    protected StereoBM(long addr) { nativeObj = addr; }


    public static final int
            PREFILTER_NORMALIZED_RESPONSE = 0,
            PREFILTER_XSOBEL = 1,
            BASIC_PRESET = 0,
            FISH_EYE_PRESET = 1,
            NARROW_PRESET = 2;


    //
    // C++:   StereoBM::StereoBM()
    //

/**
 * <p>The constructors.</p>
 *
 * <p>The constructors initialize <code>StereoBM</code> state. You can then call
 * <code>StereoBM.operator()</code> to compute disparity for a specific stereo
 * pair.</p>
 *
 * <p>Note: In the C API you need to deallocate <code>CvStereoBM</code> state when
 * it is not needed anymore using <code>cvReleaseStereoBMState(&stereobm)</code>.</p>
 *
 * @see <a href="http://docs.opencv.org/modules/calib3d/doc/camera_calibration_and_3d_reconstruction.html#stereobm-stereobm">org.opencv.calib3d.StereoBM.StereoBM</a>
 */
    public   StereoBM()
    {

        nativeObj = StereoBM_0();

        return;
    }


    //
    // C++:   StereoBM::StereoBM(int preset, int ndisparities = 0, int SADWindowSize = 21)
    //

/**
 * <p>The constructors.</p>
 *
 * <p>The constructors initialize <code>StereoBM</code> state. You can then call
 * <code>StereoBM.operator()</code> to compute disparity for a specific stereo
 * pair.</p>
 *
 * <p>Note: In the C API you need to deallocate <code>CvStereoBM</code> state when
 * it is not needed anymore using <code>cvReleaseStereoBMState(&stereobm)</code>.</p>
 *
 * @param preset specifies the whole set of algorithm parameters, one of:
 * <ul>
 *   <li> BASIC_PRESET - parameters suitable for general cameras
 *   <li> FISH_EYE_PRESET - parameters suitable for wide-angle cameras
 *   <li> NARROW_PRESET - parameters suitable for narrow-angle cameras
 * </ul>
 *
 * <p>After constructing the class, you can override any parameters set by the
 * preset.</p>
 * @param ndisparities the disparity search range. For each pixel algorithm will
 * find the best disparity from 0 (default minimum disparity) to
 * <code>ndisparities</code>. The search range can then be shifted by changing
 * the minimum disparity.
 * @param SADWindowSize the linear size of the blocks compared by the algorithm.
 * The size should be odd (as the block is centered at the current pixel).
 * Larger block size implies smoother, though less accurate disparity map.
 * Smaller block size gives more detailed disparity map, but there is higher
 * chance for algorithm to find a wrong correspondence.
 *
 * @see <a href="http://docs.opencv.org/modules/calib3d/doc/camera_calibration_and_3d_reconstruction.html#stereobm-stereobm">org.opencv.calib3d.StereoBM.StereoBM</a>
 */
    public   StereoBM(int preset, int ndisparities, int SADWindowSize)
    {

        nativeObj = StereoBM_1(preset, ndisparities, SADWindowSize);

        return;
    }

/**
 * <p>The constructors.</p>
 *
 * <p>The constructors initialize <code>StereoBM</code> state. You can then call
 * <code>StereoBM.operator()</code> to compute disparity for a specific stereo
 * pair.</p>
 *
 * <p>Note: In the C API you need to deallocate <code>CvStereoBM</code> state when
 * it is not needed anymore using <code>cvReleaseStereoBMState(&stereobm)</code>.</p>
 *
 * @param preset specifies the whole set of algorithm parameters, one of:
 * <ul>
 *   <li> BASIC_PRESET - parameters suitable for general cameras
 *   <li> FISH_EYE_PRESET - parameters suitable for wide-angle cameras
 *   <li> NARROW_PRESET - parameters suitable for narrow-angle cameras
 * </ul>
 *
 * <p>After constructing the class, you can override any parameters set by the
 * preset.</p>
 *
 * @see <a href="http://docs.opencv.org/modules/calib3d/doc/camera_calibration_and_3d_reconstruction.html#stereobm-stereobm">org.opencv.calib3d.StereoBM.StereoBM</a>
 */
    public   StereoBM(int preset)
    {

        nativeObj = StereoBM_2(preset);

        return;
    }


    //
    // C++:  void StereoBM::operator ()(Mat left, Mat right, Mat& disparity, int disptype = CV_16S)
    //

/**
 * <p>Computes disparity using the BM algorithm for a rectified stereo pair.</p>
 *
 * <p>The method executes the BM algorithm on a rectified stereo pair. See the
 * <code>stereo_match.cpp</code> OpenCV sample on how to prepare images and call
 * the method. Note that the method is not constant, thus you should not use the
 * same <code>StereoBM</code> instance from within different threads
 * simultaneously. The function is parallelized with the TBB library.</p>
 *
 * @param left Left 8-bit single-channel image.
 * @param right Right image of the same size and the same type as the left one.
 * @param disparity Output disparity map. It has the same size as the input
 * images. When <code>disptype==CV_16S</code>, the map is a 16-bit signed
 * single-channel image, containing disparity values scaled by 16. To get the
 * true disparity values from such fixed-point representation, you will need to
 * divide each <code>disp</code> element by 16. If <code>disptype==CV_32F</code>,
 * the disparity map will already contain the real disparity values on output.
 * @param disptype Type of the output disparity map, <code>CV_16S</code>
 * (default) or <code>CV_32F</code>.
 *
 * @see <a href="http://docs.opencv.org/modules/calib3d/doc/camera_calibration_and_3d_reconstruction.html#stereobm-operator">org.opencv.calib3d.StereoBM.operator()</a>
 */
    public  void compute(Mat left, Mat right, Mat disparity, int disptype)
    {

        compute_0(nativeObj, left.nativeObj, right.nativeObj, disparity.nativeObj, disptype);

        return;
    }

/**
 * <p>Computes disparity using the BM algorithm for a rectified stereo pair.</p>
 *
 * <p>The method executes the BM algorithm on a rectified stereo pair. See the
 * <code>stereo_match.cpp</code> OpenCV sample on how to prepare images and call
 * the method. Note that the method is not constant, thus you should not use the
 * same <code>StereoBM</code> instance from within different threads
 * simultaneously. The function is parallelized with the TBB library.</p>
 *
 * @param left Left 8-bit single-channel image.
 * @param right Right image of the same size and the same type as the left one.
 * @param disparity Output disparity map. It has the same size as the input
 * images. When <code>disptype==CV_16S</code>, the map is a 16-bit signed
 * single-channel image, containing disparity values scaled by 16. To get the
 * true disparity values from such fixed-point representation, you will need to
 * divide each <code>disp</code> element by 16. If <code>disptype==CV_32F</code>,
 * the disparity map will already contain the real disparity values on output.
 *
 * @see <a href="http://docs.opencv.org/modules/calib3d/doc/camera_calibration_and_3d_reconstruction.html#stereobm-operator">org.opencv.calib3d.StereoBM.operator()</a>
 */
    public  void compute(Mat left, Mat right, Mat disparity)
    {

        compute_1(nativeObj, left.nativeObj, right.nativeObj, disparity.nativeObj);

        return;
    }


    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }



    // C++:   StereoBM::StereoBM()
    private static native long StereoBM_0();

    // C++:   StereoBM::StereoBM(int preset, int ndisparities = 0, int SADWindowSize = 21)
    private static native long StereoBM_1(int preset, int ndisparities, int SADWindowSize);
    private static native long StereoBM_2(int preset);

    // C++:  void StereoBM::operator ()(Mat left, Mat right, Mat& disparity, int disptype = CV_16S)
    private static native void compute_0(long nativeObj, long left_nativeObj, long right_nativeObj, long disparity_nativeObj, int disptype);
    private static native void compute_1(long nativeObj, long left_nativeObj, long right_nativeObj, long disparity_nativeObj);

    // native support for java finalize()
    private static native void delete(long nativeObj);

}
