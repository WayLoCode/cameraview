/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.cameraview;

import android.view.View;
import java.util.Set;

abstract class CameraViewImpl {

    protected final Callback mCallback;

    protected final PreviewImpl mPreview;

    CameraViewImpl(Callback callback, PreviewImpl preview) {
        mCallback = callback;
        mPreview = preview;
    }

    View getView() {
        return mPreview.getView();
    }

    /**
     * @return {@code true} if the implementation was able to start the camera session.
     */
    abstract boolean start();

    abstract void stop();

    abstract boolean isCameraOpened();

    abstract int getFacing();

    abstract void setFacing(int facing);

    abstract Set<AspectRatio> getSupportedAspectRatios();

    /**
     * @return {@code true} if the aspect ratio was changed.
     */
    abstract boolean setAspectRatio(AspectRatio ratio);

    abstract AspectRatio getAspectRatio();

    abstract boolean getAutoFocus();

    abstract void setAutoFocus(boolean autoFocus);

    abstract int getFlash();

    abstract void setFlash(int flash);

    abstract void setZoom(float zoom);

    abstract float getZoom();

    abstract float getMaxZoom();

    abstract void takePicture();

    abstract void setDisplayOrientation(int displayOrientation);

    abstract boolean hasManualFocus();

    abstract void setFocusAt(int x, int y);

    abstract void resumePreview();

    interface Callback {

        void onCameraOpened();

        void onCameraClosed();

        void onCameraNotAvailable();

        void onCameraConfigured();

        void onFocusAt(int x, int y);

        void onPictureTaken(byte[] data);

        void onTakePictureFailed(Throwable throwable);

        Size onChoosePreviewSize(SizeMap availableSizes, Size suggestedSize, AspectRatio aspectRatio);

        Size onChoosePictureSize(SizeMap availableSizes, AspectRatio aspectRatio);
    }

}
