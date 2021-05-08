package me.didyoumuch.utils.settings;

public class FloatSetting extends AbstractSetting<Float>{
	private float min, max;
	private boolean round;
	public FloatSetting(String name, float def, float min, float max, boolean round) {
		super(name, def);
		this.min = min;
		this.max = max;
		this.round = round;
	}

	@Override
	public Float getCurrentValue() {
		return round ? Math.round(((Number) super.getCurrentValue()).floatValue()) : ((Number) super.getCurrentValue()).floatValue();
	}

	public float getMax() {
		return max;
	}
	public float getMin() {
		return min;
	}

	public boolean isRound() {
		return round;
	}

}
