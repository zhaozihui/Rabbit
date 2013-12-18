package com.zzh.rabbit;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.zzh.rabbit.beans.MyCell;

public class RabbitActivity extends Activity {
	int maxLevel = 5;
	int sizeX = 6;
	int sizeY = 6;
	TableLayout tableLayout;
	MyCell[][] btnsList = new MyCell[sizeX][sizeY];
	ArrayList<MyCell> clearList1 = new ArrayList<MyCell>();
	ArrayList<MyCell> clearList2 = new ArrayList<MyCell>();
	// 下一个元素
	int mainlevel = 0;
	// 当前元素
	int curlevel = 0;
	// 当前工具
	int curTools = 0;
	ImageView levelView;
	TextView tvScore;
	int score = 0;
	boolean curIsStored = false;
	ImageButton storeItem, toolItem1, toolItem2, toolItem3;
	Random rand = new Random();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mainlevel = rand.nextInt(4) + 1;
		curlevel = mainlevel;
		// level = 4;
		levelView = (ImageView) this.findViewById(R.id.imageLevel);
		levelView.setImageResource(CommonString.colors[mainlevel]);
		tableLayout = (TableLayout) this.findViewById(R.id.tableLayout);
		tvScore = (TextView) this.findViewById(R.id.Score);
		storeItem = (ImageButton) this.findViewById(R.id.storeItem);
		storeItem.setTag(0);
		toolItem1 = (ImageButton) this.findViewById(R.id.toolItem1);
		toolItem1.setTag(1);
		toolItem2 = (ImageButton) this.findViewById(R.id.toolItem2);
		toolItem2.setTag(2);
		toolItem3 = (ImageButton) this.findViewById(R.id.toolItem3);
		toolItem3.setTag(3);
		curTools = 0;
		for (int i = 0; i < sizeX; i++) {

			TableRow tr = new TableRow(this);
			for (int j = 0; j < sizeY; j++) {
				MyCell cell = new MyCell(this, i, j, "");
				cell.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						System.out.println("level:" + mainlevel);
						MyCell curCell = (MyCell) arg0;
						if (curCell.getLevel() != 0
								|| curCell.getLevel() > maxLevel - 1) {
							//道具功能
							if (curTools == 0) {
							} else if (curTools == 1) {

							} else if (curTools == 2) {

							} else if (curTools == 3) {
								curCell.setLevel(0);
							}
							curTools = 0;
						} else {
							curCell.setLevel(curlevel);
							// curCell.setText(level + "");
							//System.out.println(curCell.getText());
							checkCell(curCell);
							createNext();

							score = 0;
							for (int i = 0; i < sizeX; i++) {
								for (int j = 0; j < sizeY; j++) {
									// System.out.print(btnsList[i][j].getLevel());
									score += btnsList[i][j].getLevel()
											* btnsList[i][j].getLevel() * 10;
								}
							}
							tvScore.setText("$:" + score);
						}

					}
				});
				btnsList[i][j] = cell;
				tr.addView(cell);
			}
			tableLayout.addView(tr);
		}

	}

	public void checkCell(MyCell curCell) {
		if (checkW(curCell)) {
			if (!checkW(curCell)) {
				checkH(curCell);
			}
		} else {
			if (checkH(curCell)) {
				checkW(curCell);
				checkH(curCell);
			}
		}
	}

	public void createNext() {
		if (!curIsStored) {
			mainlevel = rand.nextInt(4) + 1;
			curlevel = mainlevel;
			// level = 4;
			System.out.println("level:" + curlevel);
			levelView.setImageResource(CommonString.colors[curlevel]);

		} else {
			curIsStored = false;
			curlevel = mainlevel;
		}
	}

	public boolean checkW(MyCell curCell) {
		// clearList.add(curCell);
		if (curCell.getLevel() == maxLevel) {
			return false;
		}
		System.out.println("X0:" + curCell.getX());
		boolean returnV = false;
		for (int i = (curCell.getY() + 1); i < sizeX; i++) {
			System.out.println("X1:" + btnsList[curCell.getX()][i].getLevel());
			if (btnsList[curCell.getX()][i].getLevel() == curCell.getLevel()) {
				clearList1.add(btnsList[curCell.getX()][i]);
			} else {
				break;
			}
		}
		System.out.println("X00:" + curCell.getX());
		if (curCell.getY() >= 1) {
			for (int i = (curCell.getY() - 1); i >= 0; i--) {
				System.out.println("X2:"
						+ btnsList[curCell.getX()][i].getLevel());
				if (btnsList[curCell.getX()][i].getLevel() == curCell
						.getLevel()) {
					clearList1.add(btnsList[curCell.getX()][i]);
				} else {
					break;
				}
			}
		}

		if (clearList1.size() >= 2) {

			for (int i = (curCell.getX() + 1); i < sizeX; i++) {
				System.out.println("X1:"
						+ btnsList[i][curCell.getY()].getLevel());
				if (btnsList[i][curCell.getY()].getLevel() != 0
						&& btnsList[i][curCell.getY()].getLevel() == curCell
								.getLevel()) {
					clearList2.add(btnsList[i][curCell.getY()]);
				} else {
					break;
				}
			}
			System.out.println("X00:" + curCell.getX());
			if (curCell.getX() >= 1) {
				for (int i = (curCell.getX() - 1); i >= 0; i--) {
					System.out.println("X1:"
							+ btnsList[i][curCell.getY()].getLevel());
					if (btnsList[i][curCell.getY()].getLevel() != 0
							&& btnsList[i][curCell.getY()].getLevel() == curCell
									.getLevel()) {
						clearList2.add(btnsList[i][curCell.getY()]);
					} else {
						break;
					}
				}
			}
			for (MyCell cell : clearList1) {
				cell.setLevel(0);
			}

			for (MyCell cell : clearList2) {
				cell.setLevel(0);
			}
			curCell.setLevel(curCell.getLevel() + 1);

			returnV = true;
		}
		clearList1.clear();
		clearList2.clear();
		return returnV;
	}

	public boolean checkH(MyCell curCell) {
		// clearList.add(curCell);
		if (curCell.getLevel() == maxLevel) {
			return false;
		}
		System.out.println("Y0:" + curCell.getX());
		boolean returnV = false;
		for (int i = (curCell.getX() + 1); i < sizeX; i++) {
			System.out.println("X1:" + btnsList[i][curCell.getY()].getLevel());
			if (btnsList[i][curCell.getY()].getLevel() != 0
					&& btnsList[i][curCell.getY()].getLevel() == curCell
							.getLevel()) {
				clearList1.add(btnsList[i][curCell.getY()]);
			} else {
				break;
			}
		}
		System.out.println("X00:" + curCell.getX());
		if (curCell.getX() >= 1) {
			for (int i = (curCell.getX() - 1); i >= 0; i--) {
				System.out.println("X1:"
						+ btnsList[i][curCell.getY()].getLevel());
				if (btnsList[i][curCell.getY()].getLevel() != 0
						&& btnsList[i][curCell.getY()].getLevel() == curCell
								.getLevel()) {
					clearList1.add(btnsList[i][curCell.getY()]);
				} else {
					break;
				}
			}
		}

		if (clearList1.size() >= 2) {

			for (int i = (curCell.getY() + 1); i < sizeX; i++) {
				System.out.println("X1:"
						+ btnsList[curCell.getX()][i].getLevel());
				if (btnsList[curCell.getX()][i].getLevel() != 0
						&& btnsList[curCell.getX()][i].getLevel() == curCell
								.getLevel()) {
					clearList2.add(btnsList[curCell.getX()][i]);
				} else {
					break;
				}
			}
			System.out.println("X00:" + curCell.getX());
			if (curCell.getY() >= 1) {
				for (int i = (curCell.getY() - 1); i >= 0; i--) {
					System.out.println("X2:"
							+ btnsList[curCell.getX()][i].getLevel());
					if (btnsList[curCell.getX()][i].getLevel() != 0
							&& btnsList[curCell.getX()][i].getLevel() == curCell
									.getLevel()) {
						clearList2.add(btnsList[curCell.getX()][i]);
					} else {
						break;
					}
				}
			}
			for (MyCell cell : clearList1) {
				cell.setLevel(0);
			}

			for (MyCell cell : clearList2) {
				cell.setLevel(0);
			}
			curCell.setLevel(curCell.getLevel() + 1);
			returnV = true;

		}

		clearList1.clear();
		clearList2.clear();
		return returnV;
	}

	public void storeOrChange(View v) {
		System.out.println("当前getTag:" + storeItem.getTag());
		if (storeItem.getTag().toString().endsWith("0")) {
			storeItem.setTag(curlevel);
			storeItem.setImageResource(CommonString.colors[curlevel]);
			mainlevel = rand.nextInt(4) + 1;
			curlevel = mainlevel;
			levelView = (ImageView) this.findViewById(R.id.imageLevel);
			levelView.setImageResource(CommonString.colors[mainlevel]);
			curIsStored = false;

		} else {
			curlevel = Integer.parseInt(storeItem.getTag().toString());
			System.out.println("当前Level:" + curlevel);
			storeItem.setImageResource(CommonString.colors[0]);
			storeItem.setTag(0);
			curIsStored = true;
		}

	}

	public void takeTools(View v) {
		ImageButton btn = (ImageButton) v;
		curTools = Integer.parseInt(btn.getTag().toString());
		System.out.println("当前curTools:" + curTools);
	}
}