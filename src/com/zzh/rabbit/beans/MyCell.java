package com.zzh.rabbit.beans;

import android.content.Context;
import android.widget.ImageButton;

import com.zzh.rabbit.CommonString;
import com.zzh.rabbit.R;

public class MyCell extends ImageButton {
	// private int[] colors =
	// {Color.RED,Color.GRAY,Color.GREEN,Color.BLUE,Color.CYAN,Color.DKGRAY};
	 
	private int level;
	private String tag;
	private int x;
	private int y;
	public Context context;

	public MyCell(Context context1, int x, int y, String tag) {
		super(context1);
		this.context = context1;
		this.x = x;
		this.y = y;
		this.tag = tag;
		this.setLevel(CommonString.map2[x][y]);
		this.setMaxHeight(50);
		this.setMaxWidth(50);
		//this.setText(level + "");

		//this.setBackgroundColor(colors[this.level]);
		//this.setBackgroundResource(CommonString.colors[this.level]);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		//this.setText(level + "");
		// this.setBackgroundColor(colors[this.level]);
		if(level == -1) {
			this.setImageResource(R.drawable.s0);
			this.setBackgroundResource(CommonString.colors[0]);
		} else {
			this.setImageResource(CommonString.colors[this.level]);
			this.setBackgroundResource(CommonString.colors[0]);
		}
		
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
