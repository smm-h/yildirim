package ir.smmh.gebra;

import android.content.Context;

/**
 * For values that we want to propagate during recursive visualization.
 * For example in a {@link ir.smmh.gebra.expressions.Power}, the power
 * expression and everything in it are smaller than the base expression.
 * This is achieved via the {@link VisualizationContext#scale} property.
 */
public class VisualizationContext {

    public final Context androidContext;
    public final float scale;

    public VisualizationContext(final Context androidContext, final float scale) {
        this.androidContext = androidContext;
        this.scale = scale;
    }

    public VisualizationContext multiplyScaleBy(float factor) {
        return new VisualizationContext(androidContext, scale * factor);
    }
}
