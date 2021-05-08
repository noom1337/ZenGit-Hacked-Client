package me.didyoumuch.utils.settings;

public class AbstractSetting<Value> {
	private Value currentValue, def;
	private String name;

	public AbstractSetting(String name, Value defaultValue) {
		this.name = name;
		this.def = defaultValue;
		this.currentValue = defaultValue;
	}

	public String getName() {
		return name;
	}

	public void setCurrentValue(Value value) {
		this.currentValue = value;
	}


	public Value getDefaultValue() {
		return def;
	}
	public Value getCurrentValue() {
		return currentValue;
	}
}
