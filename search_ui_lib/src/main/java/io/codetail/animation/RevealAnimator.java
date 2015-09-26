package io.codetail.animation;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;

import com.nineoldandroids.animation.Animator;

import java.lang.ref.WeakReference;

import static io.codetail.animation.ViewAnimationUtils.SimpleAnimationListener;

/**
 * @hide
 */
public interface RevealAnimator{

    void setClipOutlines(boolean clip);

    void setCenter(float cx, float cy);

    void setTarget(View target);

    void setRevealRadius(float value);

    float getRevealRadius();

    void invalidate(Rect bounds);

    class RevealFinishedGingerbread extends SimpleAnimationListener {
        WeakReference<RevealAnimator> mReference;
        volatile Rect mInvalidateBounds;

        RevealFinishedGingerbread(RevealAnimator target, Rect bounds) {
            mReference = new WeakReference<>(target);
            mInvalidateBounds = bounds;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);

            RevealAnimator target = mReference.get();

            if(target == null){
                return;
            }

            target.setClipOutlines(false);
            target.setCenter(0, 0);
            target.setTarget(null);
            target.invalidate(mInvalidateBounds);
        }
    }

    class RevealFinishedIceCreamSandwich extends SimpleAnimationListener {
        WeakReference<RevealAnimator> mReference;
        volatile Rect mInvalidateBounds;

        int mLayerType;

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        RevealFinishedIceCreamSandwich(RevealAnimator target, Rect bounds) {
            mReference = new WeakReference<>(target);
            mInvalidateBounds = bounds;

            mLayerType = ((View) target).getLayerType();
        }

        @Override
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            ((View) mReference.get()).setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        @Override
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            ((View) mReference.get()).setLayerType(mLayerType, null);

            RevealAnimator target = mReference.get();

            if(target == null){
                return;
            }

            target.setClipOutlines(false);
            target.setCenter(0, 0);
            target.setTarget(null);
            target.invalidate(mInvalidateBounds);
        }
    }

    class RevealFinishedJellyBeanMr1 extends SimpleAnimationListener {
        WeakReference<RevealAnimator> mReference;
        volatile Rect mInvalidateBounds;

        int mLayerType;

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        RevealFinishedJellyBeanMr1(RevealAnimator target, Rect bounds) {
            mReference = new WeakReference<>(target);
            mInvalidateBounds = bounds;

            mLayerType = ((View) target).getLayerType();
        }

        @Override
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            ((View) mReference.get()).setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }

        @Override
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            ((View) mReference.get()).setLayerType(mLayerType, null);

            RevealAnimator target = mReference.get();

            if(target == null){
                return;
            }

            target.setClipOutlines(false);
            target.setCenter(0, 0);
            target.setTarget(null);
            target.invalidate(mInvalidateBounds);
        }
    }
}
