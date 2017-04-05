package com.github.shchurov.horizontalwheelview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.animation.Interpolator;

/**
 * Created by Tomasz Gozdek on 05.04.2017.
 */

public class ValueAnimatorCompat {

    private ValueAnimator animator;
    private com.nineoldandroids.animation.ValueAnimator animatorOld;

    public static ValueAnimatorCompat ofFloat(float startAngle, float endAngle) {
        ValueAnimatorCompat animatorCompat = new ValueAnimatorCompat();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                animatorCompat.animator = ValueAnimator.ofFloat(startAngle,endAngle);
            } else {
                animatorCompat.animatorOld = com.nineoldandroids.animation.ValueAnimator.ofFloat(startAngle, endAngle);
            }
        return animatorCompat;
    }

    public ValueAnimatorCompat setDuration(int duration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            animator.setDuration(duration);
        } else {
            animatorOld.setDuration(duration);
        }

        return this;
    }

    public void setInterpolator(Interpolator interpolator) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            animator.setInterpolator(interpolator);
        } else {
            animatorOld.setInterpolator(interpolator);
        }

    }

    public void addUpdateListener(final AnimatorUpdateListenerCompat flingAnimatorListener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    flingAnimatorListener.onAnimationUpdate(ValueAnimatorCompat.this);
                }
            });
        } else {
            animatorOld.addUpdateListener(new com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(com.nineoldandroids.animation.ValueAnimator animation) {
                    flingAnimatorListener.onAnimationUpdate(ValueAnimatorCompat.this);
                }
            });
        }
    }

    public void addListener(final AnimatorListenerCompat animatorListener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    animatorListener.onAnimationEnd();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        } else {
            animatorOld.addListener(new com.nineoldandroids.animation.Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(com.nineoldandroids.animation.Animator animation) {

                }

                @Override
                public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {
                    animatorListener.onAnimationEnd();
                }

                @Override
                public void onAnimationCancel(com.nineoldandroids.animation.Animator animation) {

                }

                @Override
                public void onAnimationRepeat(com.nineoldandroids.animation.Animator animation) {

                }
            });
        }
    }

    public void start() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            animator.start();
        } else {
            animatorOld.start();
        }
    }

    public Object getAnimatedValue() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return animator.getAnimatedValue();
        } else {
            return animatorOld.getAnimatedValue();
        }
    }

    public void cancel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            animator.cancel();
        } else {
            animatorOld.cancel();
        }
    }


    interface AnimatorUpdateListenerCompat {

         void onAnimationUpdate(ValueAnimatorCompat animatorCompat);

     }

    interface AnimatorListenerCompat{
        void onAnimationEnd();
    }
}
