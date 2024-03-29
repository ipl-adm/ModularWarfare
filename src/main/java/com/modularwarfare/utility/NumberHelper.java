package com.modularwarfare.utility;

import org.lwjgl.util.vector.Vector3f;

public class NumberHelper {
		
	public static float clamp(float val, float min, float max) {
	    return Math.max(min, Math.min(max, val));
	}
	
	public static boolean isNegative(float val)
	{
		if(val < 0)
		{
			return true;
		}
		
		return false;
	}
	
	public static boolean isTargetMet(final float target, float current)
	{
		if(isNegative(target))
		{
			return current <= target;
		} else
		{
			return current >= target;
		}
	}
	
	public static float addTowards(float target, float current, float value)
	{
		if(isNegative(target))
		{
			return current - value;
		} else
		{
			return current + value;
		}
	}
	
	public static float generateInRange(float val)
	{
		return (float) ((Math.random() * val) - val/2);
	}
	
	public static float determineValue(boolean bool, float value)
	{
		if(bool)
		{
			return -value;
		} else
		{
			return value;
		}
	}
	
	public static Vector3f addVector(Vector3f left, Vector3f right)
	{
		Vector3f resultVector = new Vector3f();
		resultVector = resultVector.add(left, right, resultVector);
		return resultVector;
	}
	
	public static Vector3f subtractVector(Vector3f left, Vector3f right)
	{
		Vector3f resultVector = new Vector3f();
		resultVector = resultVector.sub(left, right, resultVector);
		return resultVector;
	}
	
	public static Vector3f multiplyVector(Vector3f vector, float amount)
	{
		vector.x *= amount;
		vector.y *= amount;
		vector.z *= amount;
		return vector;
	}
	
	public static Vector3f divideVector(Vector3f vector, float amount)
	{
		Vector3f newVector = new Vector3f(vector.x, vector.y, vector.z);
		newVector.x /= amount;
		newVector.y /= amount;
		newVector.z /= amount;
		return newVector;
	}
	
	public static boolean isInRange(float maxValue, float currentValue)
	{
		return currentValue <= maxValue && currentValue >= -maxValue;
	}
	
}
