package com.v.view.animation;

import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.view.animation.Interpolator;

/**
 * Created by zhouzhou on 2016/12/10.
 */

public class InterpolatorEvaluator {

    private static InterpolatorEvaluator self = new InterpolatorEvaluator();

    public static Interpolator getInterpolator() {
        return self.getInterpolatorInternal();
    }

    public Interpolator getInterpolatorInternal() {
        return new BounceInterpolator();
    }

    public static TypeEvaluator<Point> getEvaluator() {
        return self.getEvaluatorInternal();
    }

    public TypeEvaluator<Point> getEvaluatorInternal() {
        return new PositionEvaluator();
    }

    // MARK: Interpolator
    private class BounceInterpolator implements Interpolator {

        private float bounce(float t) {
            return t * t * 8.0f;
        }

        public float getInterpolation(float t) {
            t *= 1.1226f;
            if (t < 0.3535f) {
                return bounce(t);
            } else if (t < 0.7408f) {
                return bounce(t - 0.54719f) + 0.7f;
            } else if (t < 0.9644f) {
                return bounce(t - 0.8526f) + 0.9f;
            } else {
                return bounce(t - 1.0435f) + 0.95f;
            }
        }
    }

    // 自定义估值器
    private class PointEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            Point point = new Point();
            point.x = startValue.x + (int)(fraction * (endValue.x - startValue.x));
            point.y = startValue.y + (int)(fraction * (endValue.y - startValue.y));
            return point;
        }
    }

    private class PositionEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            // 获取起始点Y坐标
            float currentY = startValue.y;

            // 调用forCurrentX()方法计算X坐标
            float x = forCurrentX(fraction);
            // 调用forCurrentY()方法计算Y坐标
            float y = forCurrentY(fraction, currentY);

            return new Point((int)x, (int)y);
        }

        /**
         * 计算Y坐标
         */
        private float forCurrentY(float fraction, float currentY) {
            float resultY = currentY;
            if (fraction != 0f) {
                resultY = fraction * 400f + 20f;
            }
            return resultY;
        }

        /**
         * 计算X坐标
         */
        private float forCurrentX(float fraction) {
            float range = 120f;// 振幅
            float resultX = 160f + (float) Math.sin((6 * fraction) * Math.PI) * range;// 周期为3，故为6fraction
            return resultX;
        }
    }
}
