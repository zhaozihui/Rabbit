package com.zzh.rabbit.beans;

import java.util.Random;

import com.zzh.rabbit.CommonString;
import com.zzh.rabbit.R;
import com.zzh.rabbit.R.drawable;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

public class MyTools extends Button {
	// private int[] colors =
	// {Color.RED,Color.GRAY,Color.GREEN,Color.BLUE,Color.CYAN,Color.DKGRAY};

	private String tag;
	public Context context;

	public MyTools(Context context1, String tag) {
		super(context1);
		this.context = context1;
		this.tag = tag;

	}
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
