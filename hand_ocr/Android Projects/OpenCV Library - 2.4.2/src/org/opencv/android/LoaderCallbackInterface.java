package org.opencv.android;

/**
 * Interface for callback object in case of asynchronous initialization of OpenCV
 */
public interface LoaderCallbackInterface
{
    /**
     * OpenCV initialization finished successfully
     */
    static final int SUCCESS = 0;
    /**
     * OpenCV library installation via Google Play service was initialized. Application restart is required
     */
    static final int RESTART_REQUIRED = 1;
    /**
     * Google Play (Android Market) cannot be invoked
     */
    static final int MARKET_ERROR = 2;
    /**
     * OpenCV library installation was canceled by user
     */
    static final int INSTALL_CANCELED = 3;
    /**
     * Version of OpenCV Manager Service is incompatible with this app. Service update is needed
     */
    static final int INCOMPATIBLE_MANAGER_VERSION = 4;
    /**
     * OpenCV library initialization failed
     */
    static final int INIT_FAILED = 0xff;

    /**
     * Callback method that is called after OpenCV library initialization
     * @param status Status of initialization. See Initialization status constants
     */
    public void onManagerConnected(int status);

    /**
     * Callback method that is called in case when package installation is needed
     * @param callback Answer object with approve and cancel methods and package description
     */
    public void onPackageInstall(InstallCallbackInterface callback);
};
